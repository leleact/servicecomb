package com.github.leleact.servicecomb.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leleact.servicecomb.client.bean.DemoRequest;
import com.github.leleact.servicecomb.client.bean.DemoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.CseRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestSchema(schemaId = "trigger")
@RestController
@RequestMapping(value = "/trigger", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TriggerController {

    private static final ScheduledExecutorService SCHEDULED = new ScheduledThreadPoolExecutor(2);

    private static final ObjectMapper OM = new ObjectMapper();

    @GetMapping("/demo")
    public String getTrigger() throws JsonProcessingException {
        CseRestTemplate template = new CseRestTemplate();

        DemoRequest request = new DemoRequest();
        request.setName("abc");
        request.setAge(1);
        request.setSleepyTime(35_000L);
        HttpEntity<DemoRequest> entity = new HttpEntity<>(request);
        ResponseEntity<DemoResponse> responseEntity = null;
        try {
            responseEntity = template.postForEntity("cse://server/demo/person", entity, DemoResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        DemoResponse response = OM.readValue(OM.writeValueAsString(responseEntity.getBody()), DemoResponse.class);
        log.info("{}", response);

        return "ok";
    }

    @GetMapping("/demo/reactor")
    public String reactorDemo() throws JsonProcessingException {
        CseRestTemplate template = new CseRestTemplate();
        DemoRequest request = new DemoRequest();
        request.setName("abc");
        request.setAge(1);
        request.setSleepyTime(35_000L);
        HttpEntity<DemoRequest> entity = new HttpEntity<>(request);
        ResponseEntity<DemoResponse> responseEntity = null;
        try {
            responseEntity = template.postForEntity("cse://server/demo/person", entity, DemoResponse.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        DemoResponse response = OM.readValue(OM.writeValueAsString(responseEntity.getBody()), DemoResponse.class);
        log.info("{}", response);

        return "ok";
    }

    @GetMapping("/loop")
    public String getAsyncLoopTrigger() {
        CseRestTemplate template = new CseRestTemplate();
        AtomicInteger count = new AtomicInteger(0);
        SCHEDULED.scheduleAtFixedRate(() -> {
            try {
                DemoRequest request = new DemoRequest();
                request.setName("abc");
                request.setAge(count.getAndIncrement());
                HttpEntity<DemoRequest> entity = new HttpEntity<>(request);
                ResponseEntity<DemoResponse> entity1 = template.postForEntity("cse://server/demo/person", entity,
                    DemoResponse.class);
                DemoResponse response = null;
                response = OM.readValue(OM.writeValueAsString(entity1.getBody()), DemoResponse.class);
                log.info("{}", response);
            } catch (JsonProcessingException e) {
                log.info("{}", e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }, 5, 1, TimeUnit.SECONDS);
        return "ok";
    }
}
