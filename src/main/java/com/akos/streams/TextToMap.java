package com.akos.streams;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TextToMap {

    public static final String MSG = " Circuit Breaker Exception Status: commandName=rpsClient_getCheapestRatePlans groupName=rpsClient isSuccessfullExecution=false"
            + " isExecutionComplete=false isCircuitBreakerOpen=false isFailedExecution=false isResponseFromFallback=false isResponseRejected=false"
            + " isResponseShortCircuited=false isResponseTimeOut=true";

    public static void main(String[] args) {
        final Map<String, Object> collect = Arrays.stream(MSG.split(" "))
                .filter(s -> s.contains("="))
                .collect(Collectors.toMap(s -> s.split("=")[0], s -> s.split("=")[1]));
        System.out.println("groupName -> " + collect.get("groupName"));
        System.out.println("commandName -> " + collect.get("commandName"));
        System.out.println("isSuccessfullExecution -> " + collect.get("isSuccessfullExecution"));
    }
}
