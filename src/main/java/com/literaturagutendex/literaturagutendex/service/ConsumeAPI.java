package com.literaturagutendex.literaturagutendex.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeAPI {
    public String getData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            System.out.println("Enviando solicitud a: " + url);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de respuesta: " + response.statusCode());
            System.out.println("Respuesta: " + response.body());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return "Error: Código de respuesta " + response.statusCode();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
