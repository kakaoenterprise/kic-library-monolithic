package com.kep.library.controller;

import com.kep.library.dto.BookDto;
import com.kep.library.dto.CategoryDto;
import com.kep.library.service.ManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "BookManagementController V1")
@RequestMapping("/api/v1.0/management")
public class ManagementController {

  @Autowired
  ManagementService managementService;

  //모든 북 조회

  /**
   * 등록되어 있는 모든 도서 정보를 반환한다.
   *
   * @return BookList 모든 도서 정보목록
   */
  @ApiOperation(value = "getAllBooks", nickname = "getAllBooks", notes = "모든 도서정보목록 조회")
  @GetMapping(value = "/book/all", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<BookDto>> getAllBooks() {
    List<BookDto> books = managementService.findAll();
    return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
  }

  //모든 category 조회

  /**
   * 등록되어 있는 모든 카테고리 정보를 조회한다.
   *
   * @return CategoryList 모든 카테고리 목록
   */
  @ApiOperation(value = "", nickname = "getAllCategorys")
  @GetMapping(value = "/category/all", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<CategoryDto>> getAllCategorys() {
    List<CategoryDto> categorys = managementService.getCategoryList();
    return new ResponseEntity<List<CategoryDto>>(categorys, HttpStatus.OK);
  }

  /**
   * 신규 도서 정보를 생성한다.
   *
   * @param book 신규 도서 정보
   * @return 도서 등록 성공 여부
   */
  @ApiOperation(value = "", nickname = "createBook")
  @PostMapping(value = "/book", produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean createBook(BookDto book) {
    boolean result = managementService.createBook(book);
    return result;
  }

  /**
   * 도서 정보를 삭제처리 요청에 대하여 status 결과를 폐기로 변경한다.
   *
   * @param bookId 폐기 대상 도서 코드
   * @return boolean 도서 폐기 성공 여부
   */
  @ApiOperation(value = "", nickname = "deleteBook")
  @DeleteMapping(value = "/book", produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean deleteBook(String bookId) {
    boolean result = managementService.deleteBook(bookId);
    return result;
  }

  /**
   * 도서 코드외 모든 도서 정보를 변경 한다.
   *
   * @param book 변경할 모든 도서 정보
   * @return boolean 도서변경 성공 여부
   */
  @ApiOperation(value = "", nickname = "updateBook")
  @PutMapping(value = "/book", produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean updateBook(BookDto book) {
    boolean result = managementService.updateBook(book);
    return result;
  }

  /**
   * 책수리, 폐기, 또는 상태불능 경우에 대한 도서 상태를 변경한다.
   *
   * @param bookId 변경할 도서
   * @param status 변겨할 도서 상태(수리중, 폐기 등)
   * @return boolean 도서변경 성공 여부
   */
  @ApiOperation(value = "", nickname = "updateBookStatus")
  @PutMapping(value = "/book/status/{bookId}/{status}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean updateBookStatus(@PathVariable String bookId, @PathVariable String status) {
    boolean result = managementService.updateBookStatus(bookId, status);
    return result;
  }

  /**
   * 특정 책에 대한 상태를 조회한다.
   *
   * @param bookId 검색할 bookId
   * @return 책 상태 정보
   */
  @ApiOperation(value = "", nickname = "findBookStatus")
  @GetMapping(value = "/book/status/{bookId}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<BookDto> findBookStatus(String bookId) {
    BookDto book = managementService.findBookStatus(bookId);
    return new ResponseEntity<BookDto>(book, HttpStatus.OK);
  }

  /**
   * 도서명으로 검색 되는 도서 목록을 조회한다.
   *
   * @param name 검색할 도서명
   * @return bookList
   */
  @ApiOperation(value = "", nickname = "findBookList")
  @GetMapping(value = "/books/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<BookDto>> findBookList(String name) {
    List<BookDto> books = managementService.findBookList(name);
    return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
  }

  /**
   * 도서코드로 도서 상세정보를 조회한다.
   *
   * @param bookId 도서코드
   * @return Book 상세도서 정보
   */
  @ApiOperation(value = "", nickname = "findBookDetail")
  @GetMapping(value = "/book/{bookId}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<BookDto> findBookDetail(String bookId) {
    BookDto book = managementService.findBookDetail(bookId);
    return new ResponseEntity<BookDto>(book, HttpStatus.OK);
  }

  /**
   * 카테고리 분류로 도서목록을 조회한다.
   *
   * @param name 카테고리명
   * @return bookList 검색 카테고리로 분류되는 도서목록
   */
  @ApiOperation(value = "", nickname = "findBooksByCategory")
  @GetMapping(value = "/book/category/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<BookDto>> findBooksByCategory(String name) {
    List<BookDto> books = managementService.findBookListByCategory(name);
    return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
  }
}
