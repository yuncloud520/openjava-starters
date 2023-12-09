package cn.openjava.openapi.starter.config;


import cn.openjava.openapi.starter.enums.ApiTypeEnum;
import cn.openjava.openapi.starter.properties.OpenApiProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.WebMvcRequestHandler;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EnableOpenApi
@Configuration
public class SpringFoxSwaggerConfig {

    /**
     * openAPI属性配置
     */
    @Resource
    OpenApiProperties openApiProperties;


    /**
     * 配置基本信息
     *
     * @return
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(openApiProperties.getTitle())
                .description(openApiProperties.getDescription())
                .termsOfServiceUrl(openApiProperties.getServiceUrl())
                .contact(new Contact(openApiProperties.getAuthor(), openApiProperties.getContactUrl(), openApiProperties.getEmail()))
                .version(openApiProperties.getVersion())
                .build();
    }

    /**
     * 创建PC端接口文档
     *
     * @return 构建器对象
     */
    @Bean
    public Docket pc(ApiInfo apiInfo) {
        return docket(apiInfo, ApiTypeEnum.PC);
    }

    /**
     * 创建APP端接口文档
     *
     * @return 构建器对象
     */
    @Bean
    public Docket app(ApiInfo apiInfo) {
        return docket(apiInfo, ApiTypeEnum.APP);
    }

    /**
     * 创建feign端接口文档
     *
     * @return 构建器对象
     */
    @Bean
    public Docket feign(ApiInfo apiInfo) {
        return docket(apiInfo, ApiTypeEnum.FEIGN);
    }

    /**
     * api对外接口
     *
     * @param apiInfo
     * @return
     */
    @Bean
    public Docket api(ApiInfo apiInfo) {
        return docket(apiInfo, ApiTypeEnum.API);
    }

    /**
     * 其他接口
     *
     * @param apiInfo
     * @return
     */
    @Bean
    public Docket inner(ApiInfo apiInfo) {
        return docket(apiInfo, ApiTypeEnum.INNER);
    }

    /**
     * 创建构建器
     *
     * @param apiTypeEnum 文档类型
     * @return
     */
    private Docket docket(ApiInfo apiInfo, ApiTypeEnum apiTypeEnum) {
        return new Docket(DocumentationType.OAS_30)
                .enable(openApiProperties.isEnable())
                .apiInfo(apiInfo)
                .groupName(apiTypeEnum.getText())
                .select()
                .apis(input -> {
                    //获取文件包路径
                    String packageName = ((WebMvcRequestHandler) input).getHandlerMethod().getMethod().getDeclaringClass().getName();
                    return packageName.startsWith(openApiProperties.getBasePackage()) && packageName.contains("controller." + apiTypeEnum.getCode().toLowerCase());
                })
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
     **/
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
