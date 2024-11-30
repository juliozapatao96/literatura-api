package com.literaturagutendex.literaturagutendex.principal;

import com.literaturagutendex.literaturagutendex.model.AllData;
import com.literaturagutendex.literaturagutendex.model.Author;
import com.literaturagutendex.literaturagutendex.model.Book;
import com.literaturagutendex.literaturagutendex.model.BooksData;
import com.literaturagutendex.literaturagutendex.service.ApiResponse;
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
        var menu = """
            \n################ Menu de opciones ################
            
            1 - Obtener lista de libros
            2 - Buscar libro por título
            0 - Salir
            
            ##################################################
            """;
        while (opcion != 0) {


            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcion) {
                case 1 -> {
                    ApiResponse response = consumeApi.getData(BASE_URL);
                    if (response.isSuccess()) {
                        AllData data = converter.getData(response.getData(), AllData.class);
                        System.out.println(data);
                    } else {
                        System.err.println("Error al consultar la API: " + response.getErrorMessage()
                                +". Por favor, verifica la información ingresada e intenta nuevamente");
                    }

                }
                case 2 -> {

                    ApiResponse response = consumeApi.getData(BASE_URL+"666666666666666666666666666666/");
                    if (response.isSuccess()){
                        BooksData booksData = converter.getData(response.getData(), BooksData.class);
                        System.out.println(booksData);
                        Book book = new Book(booksData);
                        System.out.println(book);

                        Author author = new Author(book.getAuthors().getFirst());
                        System.out.println(author);
                    } else {
                        System.err.println("Error al consultar la API: " + response.getErrorMessage()
                                +". Por favor, verifica la información ingresada e intenta nuevamente");
                    }
                }
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }
}
