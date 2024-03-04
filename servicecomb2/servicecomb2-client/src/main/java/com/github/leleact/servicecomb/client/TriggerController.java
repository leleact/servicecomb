package com.github.leleact.servicecomb.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leleact.servicecomb.client.bean.DemoRequest;
import com.github.leleact.servicecomb.client.bean.DemoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.async.CseAsyncRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestSchema(schemaId = "trigger")
@RestController
@RequestMapping(value = "/trigger", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TriggerController {

    private static final ObjectMapper OM = new ObjectMapper();

    @GetMapping("/demo")
    public String getTrigger() {
        CseAsyncRestTemplate template = new CseAsyncRestTemplate();

        DemoRequest request = new DemoRequest();
        request.setName("abc");
        request.setAge(1);
        HttpEntity<DemoRequest> entity = new HttpEntity<>(request);

        ListenableFuture<ResponseEntity<DemoResponse>> future = template.postForEntity("cse://server/demo/person", entity, DemoResponse.class);
        try {
            ResponseEntity<DemoResponse> entity1 = future.get(10L, TimeUnit.SECONDS);
            DemoResponse response = OM.readValue(OM.writeValueAsString(entity1.getBody()), DemoResponse.class);
            log.info("{}", response);
        } catch (InterruptedException | ExecutionException | TimeoutException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "ok";
    }
}
