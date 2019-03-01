package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**相当于
 @Configuration 允许上下文中注册额外的bean和导入其他配置类
 @EnableAutoConfiguration 启动spring boot自动配置的设置
 @ComponentScan 上下文开启自动扫描
 */
@SpringBootApplication

public class DemoApplication {

    @RequestMapping
    String home() {
        return "Hello World111!";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

