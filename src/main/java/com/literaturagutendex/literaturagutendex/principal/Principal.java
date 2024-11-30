package com.literaturagutendex.literaturagutendex.principal;

import com.literaturagutendex.literaturagutendex.model.AllData;
import com.literaturagutendex.literaturagutendex.model.Book;
import com.literaturagutendex.literaturagutendex.model.BooksData;
import com.literaturagutendex.literaturagutendex.repository.BookRepository;
import com.literaturagutendex.literaturagutendex.service.ApiResponse;
import com.literaturagutendex.literaturagutendex.service.ConsumeAPI;
import com.literaturagutendex.literaturagutendex.service.ConvertData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner keyboard = new Scanner(System.in);
    private ConsumeAPI consumeApi = new ConsumeAPI();
    private ConvertData converter = new ConvertData();
    private final String BASE_URL = "https://gutendex.com/books/";
    private BookRepository bookRepository;

    private List<Book> books;

    public Principal(BookRepository repository) {
        this.bookRepository = repository;
    }

    public void showMenu() {
        int opcion = -1;
        var menu = """
            \n################ Menu de opciones ################
            
            1 - Obtener lista de todos los libros
            2 - Buscar libro por título
            0 - Salir
            
            ##################################################
            
            Ingrese la opción que deseas utilizar: 
            """;
        while (opcion != 0) {


            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcion) {
                case 1 -> {
                    showAllBooks();
                }
                case 2 -> {
                    searchBookByTitle();
                }
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private ApiResponse responseApi(String url){
        ApiResponse response = consumeApi.getData(url);
        if (!response.isSuccess()) {
            System.err.println("Error al consultar la API: " + response.getErrorMessage()
                    + ". Por favor, verifica la información ingresada e intenta nuevamente");
        }
        return response;
    }

    private AllData getBookData(){
        System.out.println("Escribre el título del libro que deseas buscar: ");
        var bookTitle = keyboard.nextLine();

        String queryParam = URLEncoder.encode(bookTitle, StandardCharsets.UTF_8)
                .replace("+", "%20");
        ApiResponse response = responseApi(BASE_URL + "?search=" + queryParam);

        if(response.isSuccess()){
            AllData allData = converter.getData(response.getData(), AllData.class);
            return allData;
        }
        return null;
    }

    private void searchBookByTitle(){
        AllData allData = getBookData();
        if (!allData.books().isEmpty()){
            Book book = new Book(allData.books().getFirst());
            System.out.println("El libro encontrado es: "+book);
            bookRepository.save(book);
        } else {
            System.err.println("No se encontró un libro con el título ingresado. Por favor, verifica la información ingresada e intenta nuevamente.");
        }
    }

    private void showAllBooks(){
        books = bookRepository.findAll();

        books.stream()
                .forEach(System.out::println);

    }

}
