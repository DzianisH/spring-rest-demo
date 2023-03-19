package com.example.restdemo.service;

import com.example.restdemo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// в реальности скорее всего у тебя был бы ещё BookRepository, который бы делал вызовы в базу данных за
// книгами. Или BookClient, который бы вызывал по HTTP другие приложения (серверы) чтобы получить там книги.

@Service // эта аннотация говорит Спрингу поместить объект класса BookService в контекст
public class BookService {
    private final List<Book> books = buildBooksList();
    private final Random random = new Random();

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public Book getBookByIdOrNull(long id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
// либо вот ещё один вариант как реализовать getBookById. Тут то же самое что и выше
//        books.stream().filter(book -> book.getId() == id).findAny().orElse(null);
    }

    public Book addBook(Book newBook) {
        // обычно сервер сам задаёт новые id. Так шо и тут тоже сам придумывает.
        // разве что нет никаких гарантий что не будет дубликатов айдишников, но для демо сойдёт :)
        long newId = random.nextLong();
        newBook.setId(newId);
        books.add(newBook);
        return newBook;
    }

    public Book updateBook(Book newBook) {
        for (Book book : books) {
            if (book.getId() == newBook.getId()) {
                book.setTitle(newBook.getTitle());
                book.setAuthor(newBook.getAuthor());
                book.setPublisher(newBook.getPublisher());
                book.setPublishYear(newBook.getPublishYear());
                return book;
            }
        }
        return null;
    }

    public void deleteBookById(long id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.remove(i);
                return;
            }
        }
    }


    private static List<Book> buildBooksList() {
        var books = new ArrayList<Book>();

        books.add(new Book(1, "Фантастичні історії на добраніч",
                "Ервін Мозер",
                "Видавництво 21, Чорні Вівці",
                2018));
        books.add(new Book(2, "В оточенні ідіотів, або Як зрозуміти тих, кого неможливо зрозуміти",
                "Томас Еріксон",
                "Фоліо",
                2018));
        books.add(new Book(3, "Велика книга про Коко і Кірі",
                "Ервін Мозер",
                "Видавництво Старого Лева",
                2020));

        return books;
    }
}
