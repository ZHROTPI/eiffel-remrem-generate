/*
    Copyright 2018 Ericsson AB.
    For a full list of individual contributors, please see the commit history.
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.ericsson.eiffel.remrem.generate.config;

import com.ericsson.eiffel.remrem.generate.constants.RemremGenerateServiceConstants;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(
                null, "list", "alpha", "schema",false, true
        );
    }

    private ApiInfo metaData() {
        final StringBuilder remremDescription = new StringBuilder();
        remremDescription.append("REMReM (REST Mailbox for Registered Messages) Generate "
                + "for generating validated Eiffel messages.\n");
        remremDescription.append("<a href= " + RemremGenerateServiceConstants.DOCUMENTATION_URL + ">REMReM Generate documentation</a>");

        return new ApiInfoBuilder()
                .title("Eiffel REMReM Generate Service")
                .description(remremDescription.toString())
                .build();
    }
}
