package com.literaturagutendex.literaturagutendex.principal;

import com.literaturagutendex.literaturagutendex.model.AllData;
import com.literaturagutendex.literaturagutendex.model.Author;
import com.literaturagutendex.literaturagutendex.model.Book;
import com.literaturagutendex.literaturagutendex.model.BooksData;
import com.literaturagutendex.literaturagutendex.service.ConsumeAPI;
import com.literaturagutendex.literaturagutendex.service.ConvertData;

import java.util.Scanner;
import java.util.prefs.BackingStoreException;

public class Principal {

    private Scanner keyboard = new Scanner(System.in);
    private ConsumeAPI consumeApi = new ConsumeAPI();
    private ConvertData converter = new ConvertData();
    private final String BASE_URL = "https://gutendex.com/books/";

    public void showMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Obtener lista de libros
                    2 - Buscar libro por título
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Llamando a la API...");
                    var json = consumeApi.getData(BASE_URL);
                    AllData data = converter.getData(json, AllData.class);
                    System.out.println(data);

                    System.out.println(data);
                }
                case 2 -> {
                    System.out.println("Llamando la API...");

                    var json = consumeApi.getData(BASE_URL+"98/");
                    BooksData booksData = converter.getData(json, BooksData.class);
                    System.out.println(booksData);
                    Book book = new Book(booksData);
                    System.out.println(book);

                    Author author = new Author(book.getAuthors().getFirst());
                    System.out.println(author);

                }
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }
}
