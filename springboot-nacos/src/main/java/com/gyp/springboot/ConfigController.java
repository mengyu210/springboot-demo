package com.gyp.springboot;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 15:26 - 2021/1/8
 */
@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    @NacosValue(value = "${useLocalCache:false}" , autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}
