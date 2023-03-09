package com.kep.library.service.implementation;

import com.kep.library.dto.BookDto;
import com.kep.library.dto.CategoryDto;
import com.kep.library.entity.BookEntity;
import com.kep.library.repository.BookRepository;
import com.kep.library.repository.CategoryRepository;
import com.kep.library.service.ManagementService;
import com.kep.library.util.DateFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Service("ManagementService")
public class ManagementServiceImple implements ManagementService {

  BookEntity book;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public boolean createBook(BookDto book) {
    if (0 < book.getQuantity())
      book.setStatus("최초등록");
    for (int i = 1; i <= book.getQuantity(); i++) {
      book.setBookId(book.getCategory() + "-" + DateFormatter.getDateToString(book.getPublishDate()) + "-" + i);
      bookRepository.save(book.toEntity());
    }
    return true;
  }

  public boolean deleteBook(String bookId) {
    bookRepository.deleteById(bookId);
    return true;
  }

  public boolean updateBook(BookDto book) {
    bookRepository.save(book.toEntity());
    return true;
  }

  public boolean updateBookStatus(String bookId, String status) {
    bookRepository.save(book);
    return true;
  }

  public BookDto findBookStatus(String bookId) {
    ModelMapper modelMapper = new ModelMapper();
    BookDto book = modelMapper.map(bookRepository.findByStatus(bookId), BookDto.class);
    return book;
  }

  public List<BookDto> findBookList(String search) {
    String name = search, catetory = search, company = search;
    ModelMapper modelMapper = new ModelMapper();
    List<BookEntity> bookList = bookRepository.findByNameContainingOrCategoryOrCompany(name, catetory, company);
    List<BookDto> books = bookList.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    return books;
  }

  /*{"도서명", "카테고리", "저자"}*/
  public List<BookDto> findBookList(String searchType, String searchData) {
    List<BookDto> books = null;
    ModelMapper modelMapper = new ModelMapper();
    if ("도서명".equals(searchType))
      books = bookRepository.findByNameContainsIgnoreCase(searchData)
              .stream()
              .map(book -> modelMapper.map(book, BookDto.class))
              .collect(Collectors.toList());
    if ("카테고리".equals(searchType))
      books = bookRepository.findByCategory(searchData)
              .stream()
              .map(book -> modelMapper.map(book, BookDto.class))
              .collect(Collectors.toList());
    if ("저자".equals(searchType))
      books = bookRepository.findByWriter(searchData)
              .stream()
              .map(book -> modelMapper.map(book, BookDto.class))
              .collect(Collectors.toList());
    return books;
  }

  public BookDto findBookDetail(String bookId) {
    ModelMapper modelMapper = new ModelMapper();
    BookDto bookDto = modelMapper.map(bookRepository.findByBookId(bookId), BookDto.class);

    return bookDto;
  }

  public List<BookDto> findAll() {
    ModelMapper modelMapper = new ModelMapper();
    List<BookDto> books = bookRepository.findAll()
            .stream()
            .map(book -> modelMapper.map(book, BookDto.class))
            .collect(Collectors.toList());
    return books;
  }

  @Override
  public List<CategoryDto> getCategoryList() {
    ModelMapper modelMapper = new ModelMapper();
    List<CategoryDto> categorys = categoryRepository
            .findAll()
            .stream()
            .map(category -> modelMapper.map(category, CategoryDto.class))
            .collect(Collectors.toList());
    return categorys;
  }

  @Override
  public List<BookDto> findBookListByCategory(String category) {
    ModelMapper modelMapper = new ModelMapper();
    List<BookDto> books = bookRepository
            .findByCategory(category)
            .stream()
            .map(book -> modelMapper.map(book, BookDto.class))
            .collect(Collectors.toList());
    return books;
  }

  @Override
  public List<BookDto> findBookByRecommendedTrue() {
    ModelMapper modelMapper = new ModelMapper();
    List<BookDto> books = bookRepository
            .findByRecommendedTrue()
            .stream()
            .map(book -> modelMapper.map(book, BookDto.class))
            .collect(Collectors.toList());
    return books;
  }

}
