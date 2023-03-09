package com.kep.library.controller;

import com.kep.library.dto.ManagerDto;
import com.kep.library.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountViewController {

  private static final Logger logger = LoggerFactory.getLogger(AccountViewController.class);
  @Autowired
  AccountService accountService;

  @RequestMapping("/login")
  public String showLoginForm(RequestEntity requestEntity, Model model) {
    logger.info("referer is {}", requestEntity.getHeaders().get("referer"));

    model.addAttribute("reqUrl", requestEntity.getHeaders().get("referer"));
    model.addAttribute("manager", new ManagerDto(null, ""));
    return "login";
  }

  @RequestMapping(value = "/join")
  public String showJoinForm(Model model) {
    model.addAttribute("manager", new ManagerDto(null, ""));
    return "join";
  }

  @RequestMapping(value = "/search")
  public String showSearchForm(Model model) {
    model.addAttribute("manager", new ManagerDto(null, ""));
    return "search";
  }

  @PostMapping(value = "/authenticate")
  public String login(ManagerDto manager, Model model) {

    if ("".equals(manager.getLibrarianId()) || "".equals(manager.getPassword())) {
      logger.info("{} do not entered password.", manager.getLibrarianId());
      model.addAttribute("loginErr", "fail to login.");
      return "login";
    }

    String token = accountService.authentication(manager);
    if ("".equals(token)) {
      logger.info("{}'s token is invalid.", manager.getLibrarianId());
      model.addAttribute("loginErr", "fail to login.");
      return "login";
    }
    return "redirect:authenticated";
  }

  @GetMapping(value = "/authenticated")
  public String authenticated(Model model, HttpSession session) {
//        String token = (String)model.getAttribute("result");
//        session.setAttribute("token", token);
    model.addAttribute("authenticated", "true");
    session.setAttribute("authenticated", "true");
    return "index";
  }

  /*
      @GetMapping(value = "/logout")
      public String logout(HttpSession session, SessionStatus sessionStatus) {
          logger.info("{} is logout.", manager.getLibrarianId());
          sessionStatus.setComplete();
          session.setAttribute("authenticated", "false");
          return "redirect:/";
      }
  */
  @PostMapping(value = "/join")
  public String join(@Validated ManagerDto manager, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "join";
    }

    boolean response = accountService.createManager(manager);
    if (!response) {
      return "join";
    }

    model.addAttribute("joined", "success");
    return "login";
  }
}
