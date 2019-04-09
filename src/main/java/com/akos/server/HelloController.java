package com.akos.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.akos.model.Payload;

@RestController
public class HelloController {

    @GetMapping("/{id}")
    public Payload index(@PathVariable Integer id){
        if (id.equals(0)) {
            return Payload.builder()
                    .id(0)
                    .name("Payload0")
                    .addChild("Child1")
                    .addChild("Child2")
                    .build();
        } else {
            return null;
        }
    }
}
