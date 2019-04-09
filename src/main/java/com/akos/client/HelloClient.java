package com.akos.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import com.akos.model.Payload;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var helloClient = new HelloClient();
        System.out.println(helloClient.getData(0));
        System.out.println(helloClient.getData(1));
    }

    public String getData(Integer id) throws InterruptedException, ExecutionException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"+id.toString()))
                .build();

        var client = HttpClient.newHttpClient();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::mapBodyToPayload)
                .thenApply(optPayload -> optPayload
                        .map(Payload::toString)
                        .orElse("No payload!"))
                .get();
    }

    private Optional<Payload> mapBodyToPayload(HttpResponse<String> stringHttpResponse) {
        var body = stringHttpResponse.body();
        if (!body.isBlank()) {
            var mapper = new ObjectMapper();
            try {
                return Optional.of(mapper.readValue(body, Payload.class));
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
