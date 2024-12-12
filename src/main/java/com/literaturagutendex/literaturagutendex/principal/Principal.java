package com.literaturagutendex.literaturagutendex.principal;

import com.literaturagutendex.literaturagutendex.model.AllData;
import com.literaturagutendex.literaturagutendex.model.Author;
import com.literaturagutendex.literaturagutendex.model.Book;
import com.literaturagutendex.literaturagutendex.model.BooksData;
import com.literaturagutendex.literaturagutendex.repository.AuthorRepository;
import com.literaturagutendex.literaturagutendex.repository.BookRepository;
import com.literaturagutendex.literaturagutendex.service.ApiResponse;
import com.literaturagutendex.literaturagutendex.service.ConsumeAPI;
import com.literaturagutendex.literaturagutendex.service.ConvertData;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner keyboard = new Scanner(System.in);
    private ConsumeAPI consumeApi = new ConsumeAPI();
    private ConvertData converter = new ConvertData();
    private final String BASE_URL = "https://gutendex.com/books/";
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    private List<Book> books;

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        int opcion = -1;
        var menu = """
            \n################ Menu de opciones ################
            
            1 - Obtener lista de todos los libros
            2 - Buscar libro por título
            3 - Obtener lista de libros por lenguaje
            4 - Obtener lista de todos los autores
            5 - Obtener lista de autores vivos en el año ingresado
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
                case 3 -> {
                    searchBooksByLanguage();
                }
                case 4 -> {
                    showAllAuthors();
                }
                case 5 -> {
                    showLivingAuthorsInCertainYear();
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



    private void searchBookByTitle() {
        AllData allData = getBookData();
        if (!allData.books().isEmpty()) {
            Book book = new Book(allData.books().getFirst());

            //verifica si el libro ya existe en la bases de datos
            Optional<Book> optionalBook = bookRepository.findByTitleContainsIgnoreCase(book.getTitle());

            if (!optionalBook.isPresent()) {
                Author author = book.getAuthorTemp();

                // Verifica si el autor ya existe en la base de datos
                Optional<Author> optionalAuthor = authorRepository.findByNameContainsIgnoreCase(author.getName());
                if (!optionalAuthor.isPresent()) {
                    authorRepository.save(author);
                } else {
                    // obtiene el autor que ya está en la base de datos
                    author = optionalAuthor.get();
                }

                // Asocia el autor al libro
                book.setAuthor(author);

                // Guarda el libro
                bookRepository.save(book);
            }
            System.out.println("El libro encontrado es: " + book);

        } else {
            System.err.println("No se encontró un libro con el título ingresado. Por favor, verifica la información ingresada e intenta nuevamente.");
        }
    }

    private void searchBooksByLanguage(){
        System.out.println("Ingrese el idioma para buscar libros: ");
        var languageSearch = keyboard.nextLine();

        List<Book> books = bookRepository.findByLanguage(languageSearch);

        if (!books.isEmpty()){
            books.stream()
                    .forEach(System.out::println);
        } else {
            System.out.println("No se encontraron libros con el lenguaje : "+languageSearch);
        }

    }

    private void showLivingAuthorsInCertainYear() {
        try {
            System.out.println("Ingrese el año: ");
            var year = keyboard.nextInt();

            if (year > 0) {
                List<Author> authors = authorRepository.authorsByYearTheyWereAlive(year);

                if (!authors.isEmpty()){
                    System.out.println("El/Los autor(es) que se encontraban vivos para el año "+year+" son: ");
                    authors.stream()
                            .forEach(System.out::println);
                } else {
                    System.out.println("No se encontraron autores vivos para el año "+year);
                }
            } else {
                System.out.println("Por favor, ingresa un año válido (mayor a 0).");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingresa un número entero.");
            keyboard.nextLine(); // Limpiar el buffer del scanner
        }
    }

    private void showAllBooks(){
        books = bookRepository.findAll();

        books.stream()
                .forEach(System.out::println);

    }

    private void showAllAuthors(){
        List<Author> authors = authorRepository.findAll();

        authors.stream()
                .forEach(System.out::println);
    }

}
