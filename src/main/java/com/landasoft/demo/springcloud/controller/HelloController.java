package com.landasoft.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 伍林云
 * @date 2019/7/17  17:22
 */
@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String demoInfo()
    {
        return "demoInfo";
    }
    @GetMapping("/demo-a/info")
    public String demo2b() {
        return (String)this.restTemplate.getForObject("http://springcloud-demo-b/", String.class, new Object[0]);
    }
    @GetMapping("/demo-b/info")
    public String demo2a() {
        return (String)this.restTemplate.getForObject("http://springcloud-demo-a/", String.class, new Object[0]);
    }
    @GetMapping("/demo-a2b")
    public String demoa2b() {
        return (String)this.restTemplate.getForObject("http://springcloud-demo-b/demo-a2b", String.class, new Object[0]);
    }
    @GetMapping("/demo-b2a")
    public String demob2a() {
        return (String)this.restTemplate.getForObject("http://springcloud-demo-a/demo-b2a", String.class, new Object[0]);
    }
    @GetMapping({"/b/info"})
    public String binfo() {
        return (String)this.restTemplate.getForObject("http://tracing-demo-b/", String.class, new Object[0]);
    }
    @GetMapping({"/c/info"})
    public String cinfo() {
        return (String)this.restTemplate.getForObject("http://tracing-demo-c/", String.class, new Object[0]);
    }
    @GetMapping({"/a2c"})
    public String a2c() {
        return (String)this.restTemplate.getForObject("http://tracing-demo-c/", String.class, new Object[0]);
    }
    @GetMapping({"/b2c"})
    public String b2c() {
        return (String)this.restTemplate.getForObject("http://tracing-demo-b/b2c", String.class, new Object[0]);
    }
    @GetMapping({"/layers"})
    public String complex() {
        String info1 = (String)this.restTemplate.getForObject("http://tracing-demo-b/b2c", String.class, new Object[0]);
        String info2 = (String)this.restTemplate.getForObject("http://tracing-demo-c/", String.class, new Object[0]);
        return info1 + " -> " + info2;
    }
}
