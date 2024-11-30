package com.literaturagutendex.literaturagutendex.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsumeAPI {
    public ApiResponse getData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            System.out.println("Enviando solicitud a: " + url);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return new ApiResponse(true, response.body(), null);
            } else {
                return new ApiResponse(false, null, "Error: Código de respuesta " + response.statusCode());
            }
        } catch (IOException e) {
            return new ApiResponse(false, null, "Error de red o I/O: " + e.getMessage());
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return new ApiResponse(false, null, "Error: operación interrumpida");
        }
    }

}
