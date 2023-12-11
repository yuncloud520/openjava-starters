package cn.openjava.nacos.controller.inner;

import cn.openjava.nacos.service.IApplicationLifeCycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner/lifecycle")
@Api(tags = "k8s应用生命周期")
public class ApplicationLifeCycleController {
    /**
     * 应用生命周期服务
     */
    @Resource
    private IApplicationLifeCycleService applicationLifeCycleService;

    /**
     * 应用启动后，进行处理
     */
    @GetMapping("/post-start")
    @ApiOperation("应用启动后，进行处理")
    public void postStart() {
        applicationLifeCycleService.postStart();
    }

    /**
     * 应用停止前，进行处理
     */
    @GetMapping("/pre-stop")
    @ApiOperation("应用停止前，进行处理")
    @ApiImplicitParam(name = "isNacosDeregister", value = "是否在nacos中下线", dataType = "boolean", paramType = "query")
    public void preStop(@RequestParam(required = false) boolean isNacosDeregister) {
        applicationLifeCycleService.preStop(isNacosDeregister);
    }

    /**
     * 探活请求
     */
    @GetMapping("/live")
    @ApiOperation("探活请求")
    public String live() {
        return "success";
    }
}
