package com.example.book.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
        // 전체 조회
        bookRepository.findAll().forEach(items -> System.out.println(items));
    }

    @Test
    public void testList2() {
        // 페이지 나누기
        Pageable pageable = PageRequest.of(1, 10, Sort.by("code").descending());

        Page<Book> result = bookRepository.findAll(pageable);
        result.getContent().forEach(book -> System.out.println(book));
        System.out.println("전체 행 개수 " + result.getTotalElements());
        System.out.println("전체 페이지 수 " + result.getTotalPages());
    }

    @Test
    public void testGet() {
        // 하나 조회
        Book book = bookRepository.findById(1L).get();
        System.out.println(book);
    }

    @Test
    public void testUpdate() {
        // 가격 수정
        // Book book = bookRepository.findById(10L).get();
        // book.setPrice(10000);
        // bookRepository.save(book);
        List<Book> book = bookRepository.findAll();
        book.forEach(item -> {
            item.replaceTitle(item.getTitle());
            bookRepository.save(item);
        });
    }

    @Test
    public void testRemove() {
        bookRepository.deleteById(20L);
    }

}
