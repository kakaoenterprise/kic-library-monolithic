package com.kep.library.controller;

import com.kep.library.dto.BookDto;
import com.kep.library.service.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MainViewController {

  private static final Logger logger = LoggerFactory.getLogger(MainViewController.class);
  ManagementService managementService;

  public MainViewController() {
    super();
  }

  @Autowired
  public void setAccountService(ManagementService managementService) {
    this.managementService = managementService;
  }

  @RequestMapping(value = "/")
  public String index(Model model, HttpSession session, @RequestHeader Map<String, Object> requestHeader) {
    logger.info("Enter the user to search page.");
    String[] searchTypes = {"도서명", "카테고리", "저자"};
    model.addAttribute("searchTypes", searchTypes);
    model.addAttribute("category", managementService.getCategoryList());
    model.addAttribute("books", managementService.findBookByRecommendedTrue());
//    if (null == session.getAttribute("authenticated")) {
//        logger.info("This user is not authenticated.", session.getAttribute("authenticated") );
//        session.setAttribute("authenticated", "false");
//    } else {
//        logger.info("This user ({}) is authenticated.", session.getAttribute("authenticated") );
//    }
    return "content/home";
  }

  @RequestMapping(value = "/result")
  public String result(Model model, HttpSession session) {
    String[] searchTypes = {"도서명", "카테고리", "저자"};
    model.addAttribute("searchTypes", searchTypes);
    model.addAttribute("books", managementService.findAll());
    return "content/result";
  }

  @RequestMapping(value = "/result/{searchType}/{searchData}")
  public String result(Model model, HttpSession session, @PathVariable("searchType") String searchType, @PathVariable("searchData") String searchData) {
    String[] searchTypes = {"도서명", "카테고리", "저자"};
    model.addAttribute("searchTypes", searchTypes);
    model.addAttribute("category", managementService.getCategoryList());
    model.addAttribute("books", managementService.findBookList(searchType, searchData));
    return "content/result";
  }

  @RequestMapping(value = "/detail/{bookId}")
  public String detail(Model model, HttpSession session, @PathVariable("bookId") String bookId) {
    String[] searchTypes = {"도서명", "카테고리", "저자"};
    model.addAttribute("searchTypes", searchTypes);
    model.addAttribute("category", managementService.getCategoryList());
    model.addAttribute("book", managementService.findBookDetail(bookId));
    return "content/detail";
  }

//    @RequestMapping("/login")
//    public String redirectLoginServer(Model model) {
//        URI redirectUri = new URI("http://localhost/login");
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(redirectUri);
//        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//        return "login";
//        return "redirect:http://localhost/login";
//    }

  @RequestMapping("/logout")
  public String logout(Model model, HttpSession session, HttpServletResponse response) {
    if (null != session.getAttribute("authenticated")) {
      session.setAttribute("authenticated", "false");
      Cookie logoutCookie = new Cookie("jwt_token", null);
      logoutCookie.setMaxAge(0);         // 쿠키의 expiration 타임을 0으로 하여 없앤다.
      logoutCookie.setPath("/");         // 모든 경로에서 삭제 됬음을 알린다.
      response.addCookie(logoutCookie);
    }
    return "index";
  }

  @GetMapping(value = "/search")
  public String search(Model model) {
    String[] searchTypes = {"도서명", "카테고리", "저자"};
    model.addAttribute("searchTypes", searchTypes);
    model.addAttribute("category", managementService.getCategoryList());
    return "search";
  }

  @GetMapping(value = "/searchResult/{searchType}/{searchData}")
  public String searchResult(Model model, @PathVariable("searchType") String searchType, @PathVariable("searchData") String searchData) {
    model.addAttribute("books", managementService.findBookList(searchType, searchData));
    return "searchResult";
  }

  @GetMapping(value = "/register")
  public String register(Model model) {
    model.addAttribute("book", new BookDto());
    model.addAttribute("category", managementService.getCategoryList());
    return "register";
  }

  @GetMapping(value = "/home")
  public String home(Model model) {
    return "home";
  }

  @GetMapping(value = "/header")
  public String header() {
    return "fragment/header";
  }
}
