package com.ceragem.iot.cms.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${project.properties.swagger-host}")
    private String swaggerHost;

    @Autowired
    ServerProperties serverProperties;
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
//                .paths(PathSelectors.ant("/api/**")) // 그중 /api/** 인 URL들만 필터링
//                .build();
//    }
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.omnicns.omnifit2.api.controller.api"))
//                .paths(PathSelectors.any())
//                .build();
//
//    }
@Autowired
private TypeResolver typeResolver;
    @Bean
    public Docket api(TypeResolver typeResolver) {
//        ResolvedRecursiveType resolvedRecursiveType = new ResolvedRecursiveType(Integer.class, null);
//        Optional<ResolvedType> typeInt = Optional.of(resolvedRecursiveType);
//        resolvedRecursiveType = new ResolvedRecursiveType(String.class, null);
//        Optional<ResolvedType> typeString = Optional.of(resolvedRecursiveType);
//        AllowableListValues osTypeAllowableList = new AllowableListValues(Stream.of(OsType.values()).map(it -> it.name()).collect(Collectors.toList()), "string");
//        Parameter cponId        = new Parameter("X-cponId",     "폰 아이디",       "test-cponId",           false, false, new ModelRef("string"), typeString, null, "header" , null, false, new ArrayList<>());
//        Parameter serialNo      = new Parameter("X-serialNo",   "디바이스 고유 번호",   "test-serialNo",      false, false, new ModelRef("string"), typeString, null, "header" , null, false, new ArrayList<>());
//        Parameter cponModel     = new Parameter("X-cponModel",  "폰 모델명",      "test-cponModel",         false, false, new ModelRef("string"), typeString, null, "header" , null, false, new ArrayList<>());
//        Parameter osTypeCd      = new Parameter("X-osTypeCd",   "폰 OS 타입",     "OST001",                false, false, new ModelRef("string"), typeString, osTypeAllowableList, "header" , null, false, new ArrayList<>());
//        Parameter osVersion     = new Parameter("X-osVer",       "폰 OS 버전",         "test-cponModel",   false, false, new ModelRef("string"), typeString, null, "header" , null, false, new ArrayList<>());
//        Parameter pkgNm         = new Parameter("X-pkgNm",      "어플 패키지명",       "test-pkgNm",         false, false, new ModelRef("string"), typeString, null, "header" , null, false, new ArrayList<>());
//        Parameter pkgVersion    = new Parameter("X-pkgVer",      "어플 VERSION",      "test-pkgVer",       false, false, new ModelRef("string"), typeString, null, "header" , null, false, new ArrayList<>());
//        Parameter userSeq       = new Parameter("X-userSeq",    "서버 회원 고유 아이디", "1",                  false, false, new ModelRef("int"), typeInt, null, "header" , null, false, new ArrayList<>());
        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerHost)
                .apiInfo(apiInfo())
//                .ignoredParameterTypes(MedicineHeader.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ceragem.iot.cms.controller"))
                .paths(PathSelectors.any())
//                .paths(Predicates.not(PathSelectors.regex("/basic-error-controller.*")))
                .build()
//                .additionalModels(typeResolver.resolve(Msg.class, Error.class))

//                .additionalModels(typeResolver.resolve(OsType.class))
//                .globalOperationParameters(Arrays.asList(cponId, serialNo, cponModel, osTypeCd, osVersion, pkgNm, pkgVersion, userSeq))
//                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
//                .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
//                .globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages())
//                .globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
                .useDefaultResponseMessages(false);
//                .globalResponseMessage(RequestMethod.GET,
//                        new ArrayList(new ResponseMessageBuilder()
//                                        .code(500)
//                                        .message("Internal Server Error")
//                                        .responseModel(new ModelRef("Error"))
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(400)
//                                        .message("Bad Request")
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(404)
//                                        .message("Not Found")
//                                        .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("project-cms")
                .description("project-cms documents")
//                .license("omnicns")
                .build();

    }
    private List<ResponseMessage> getCustomizedResponseMessages(){
        ModelRef modelRef = new ModelRef(Error.class.getSimpleName());
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(500).message("Server has crashed!! and Error").responseModel(modelRef).build());
//        responseMessages.add(new ResponseMessageBuilder().code(403).message("You shall not pass!!").responseModel(modelRef).build());
        return responseMessages;
    }
}
