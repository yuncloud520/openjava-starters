package cn.openjava.nacos.controller.inner;

import cn.openjava.nacos.service.INacosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner/nacos")
@Api(tags = "Nacos")
public class NacosController {

    @Resource
    private INacosService nacosService;

    /**
     * 下线服务
     *
     * @return 成功或失败
     */
    @GetMapping("/deregister")
    @ApiOperation("下线")
    public boolean deregister() {
        return nacosService.deregister();
    }

    /**
     * 上线服务
     *
     * @return 成功或失败
     */
    @GetMapping("/register")
    @ApiOperation("上线")
    public boolean register() {
        return nacosService.register();
    }
}
