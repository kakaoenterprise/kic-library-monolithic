package com.kep.library.service;

import com.kep.library.dto.BookDto;
import com.kep.library.dto.CategoryDto;

import java.util.List;


public interface ManagementService {

  boolean createBook(BookDto book);

  boolean deleteBook(String bookId);

  boolean updateBook(BookDto book);

  boolean updateBookStatus(String bookId, String status);

  BookDto findBookStatus(String bookId);

  List<BookDto> findBookList(String name);

  List<BookDto> findBookList(String searchType, String searchData);

  BookDto findBookDetail(String bookId);

  List<BookDto> findAll();

  List<CategoryDto> getCategoryList();

  List<BookDto> findBookListByCategory(String category);

  List<BookDto> findBookByRecommendedTrue();
}
