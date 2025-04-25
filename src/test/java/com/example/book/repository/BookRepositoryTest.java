package com.example.book.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.Book;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testInsert() {
        // 20권
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Book book = Book.builder()
                    .title("제목" + i)
                    .author("작가" + i)
                    .price(10000 + i * 1000)
                    .build();
            bookRepository.save(book);
        });
    }

    @Test
    public void testList() {
        bookRepository.findAll().forEach(items -> System.out.println(items));
    }

    @Test
    public void testGet() {
        Book book = bookRepository.findById(1L).get();
        System.out.println(book);
    }

    @Test
    public void testUpdate() {
        // 가격 수정
        Book book = bookRepository.findById(10L).get();
        book.setPrice(10000);
        bookRepository.save(book);
    }

    @Test
    public void testRemove() {
        bookRepository.deleteById(20L);
    }

}
