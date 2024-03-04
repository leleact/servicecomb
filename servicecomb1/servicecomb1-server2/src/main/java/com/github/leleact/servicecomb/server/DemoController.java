package com.github.leleact.servicecomb.server;

import com.github.leleact.servicecomb.server.bean.DemoRequest;
import com.github.leleact.servicecomb.server.bean.DemoResponse;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestSchema(schemaId = "demo")
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    @PostMapping("/person")
    public DemoResponse person(@RequestBody DemoRequest request) {
        DemoResponse response = new DemoResponse();
        response.setAddress("add for age:" + request.getAge());
        response.setEmail("xx@yy");
        return response;
    }
}
