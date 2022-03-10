package com.jie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指导要扫描接口的位置
                //.apis(RequestHandlerSelectors.basePackage("com.jie.controller"))
                //设置全部扫描全部接口
                .apis(RequestHandlerSelectors.any())
                //设置过滤路径
                //.paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                ;
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("jie", "https://www.cnblogs.com/OfflineBoy/", "sb@qq.com");
        return new ApiInfo("Swagger 标题测试",
                "佛祖保佑",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
