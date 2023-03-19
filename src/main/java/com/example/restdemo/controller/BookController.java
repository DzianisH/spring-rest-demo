package com.example.restdemo.controller;

import com.example.restdemo.model.Book;
import com.example.restdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Это контроллел, REST контроллер. Сюда приходят HTTP запросы. Если запрос подходит под
// "описание" из аннотаций, то спринг вызывает соответствующий метод.
// Например при запросе с методом GET и путём в адресе /api/v1/book/123 вызавется метод
// getBook() и аргумент id будет равен 123.
@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired // магия Спринга. Означает "впердолить сюда BookService из контекста"
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBookByIdOrNull(id);
    }

    @PostMapping
    // @RequestBody означет что Book book нужно взять из тела запроса
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}") // вызвать это метод если пришёл запрос на /api/v1/book/{id} где id это long
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
    }
}
