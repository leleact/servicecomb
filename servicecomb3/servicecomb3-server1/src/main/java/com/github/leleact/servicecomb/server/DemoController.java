package com.github.leleact.servicecomb.server;

import com.github.leleact.servicecomb.server.bean.DemoRequest;
import com.github.leleact.servicecomb.server.bean.DemoResponse;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RestSchema(schemaId = "demo")
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    @PostMapping("/person")
    public DemoResponse person(@RequestBody DemoRequest request) throws InterruptedException {
        if (request.getSleepyTime() != null && request.getSleepyTime() >= 0) {
            Thread.sleep(Duration.of(request.getSleepyTime(), ChronoUnit.MILLIS));
        }
        DemoResponse response = new DemoResponse();
        response.setAddress("add for name:" + request.getName());
        response.setEmail("xx@yy");
        return response;
    }

    @GetMapping("/health")
    public DemoResponse heartbeat() {
        DemoResponse response = new DemoResponse();
        response.setAddress("ok");
        response.setEmail("ok");
        return response;
    }
}
