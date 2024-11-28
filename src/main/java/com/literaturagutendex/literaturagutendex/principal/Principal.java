package com.literaturagutendex.literaturagutendex.principal;

import com.literaturagutendex.literaturagutendex.model.BooksData;
import com.literaturagutendex.literaturagutendex.service.ConsumeAPI;
import com.literaturagutendex.literaturagutendex.service.ConvertData;

import java.util.Scanner;

public class Principal {

    private Scanner keyboard = new Scanner(System.in);
    private ConsumeAPI consumeApi = new ConsumeAPI();
    private ConvertData converter = new ConvertData();
    private final String BASE_URL = "https://gutendex.com/books/?search=dickens";

    public void showMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Test
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Llamando a la API...");
                    var json = consumeApi.getData(BASE_URL);
                    System.out.println(json);
                    BooksData data = converter.getData(json, BooksData.class);
                    System.out.println(data);
                }
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }
}
