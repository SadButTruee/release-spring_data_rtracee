package ru.edu.springdata.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edu.springdata.model.Book;
import ru.edu.springdata.model.Category;
import ru.edu.springdata.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



    @Override
    @Transactional
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Book book) {
        var bookGet = bookRepository.findById(book.getId()).orElseThrow(EntityNotFoundException::new);
        updateFields(book, bookGet);
        bookRepository.save(bookGet);
    }

    @Override
    @Transactional
    public List<Book> getByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    @Override
    @Transactional
    public List<Book> getByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    private void updateFields(Book book, Book bookGet) {
        bookGet.setActive(book.isActive());
        bookGet.setTitle(book.getTitle());
        bookGet.setAuthors(book.getAuthors());
        bookGet.setLanguage(book.getLanguage());
        bookGet.setCategory(book.getCategory());
    }
}
