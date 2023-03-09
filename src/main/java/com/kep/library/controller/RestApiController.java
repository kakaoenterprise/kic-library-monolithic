package com.kep.library.controller;

import com.kep.library.dto.AccountDto;
import com.kep.library.dto.ManagerDto;
import com.kep.library.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * 관리자 계정 관리 및 인증처리를 위한 RestAPI controller 클래스
 *
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1.0/manageraccount")
public class RestApiController {

  @Autowired
  AccountService accountService;

  /**
   * 관리자에 대한 가입 정보를 받아 계정을 등록한다.
   *
   * @param manager 관리자 가입정보
   * @return boolean 정상 등록 결과
   */
  @PostMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean createManager(ManagerDto manager) {
    accountService.createManager(manager);
    return true;
  }

  /**
   * 도서 관리자 계정에 대한 상세 정보 조회
   *
   * @param librarianId 도서 관리자 Id
   * @return Manager 도서 관리자에 대한 정보
   */
  @GetMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ManagerDto> getDetailManager(String librarianId) {
    ManagerDto manager = accountService.getDetailManager(librarianId);
    if (manager == null)
      return new ResponseEntity<ManagerDto>(manager, HttpStatus.NOT_FOUND);
    return new ResponseEntity<ManagerDto>(manager, HttpStatus.OK);
  }

  /**
   * JWT 토큰을 발행한다.
   *
   * @param manager 인증토큰을 발행할 대상 정보
   * @return JWT 토큰을 발행한다.
   */
  @PostMapping(value = "/generateToken", produces = {MediaType.APPLICATION_JSON_VALUE})
  public String generateToken(ManagerDto manager) {
    return accountService.generateToken(manager);
  }

  /**
   * JWT토큰에 대한 유효성을 확인을위하여 토큰만료시간 및 관리자ID 에 대한정보를 확인하여 회신한다.
   *
   * @param token 유효성확인을 위한 JWT 토큰
   * @return 상태값 OK를 회신한다.
   */
  @GetMapping(value = "/vaidationToken", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> validationToken(@RequestHeader("jwt_token") String token) {
    AccountDto accDto = null;
    HttpHeaders resHeader = new HttpHeaders();
    String message = null;
    HttpStatus httpStatus = null;
    try {
      if (accountService.validationToken(token)) {
        resHeader.set("token", token);
        message = "유효한 토큰입니다.";
        httpStatus = HttpStatus.OK;
      } else {
        httpStatus = HttpStatus.UNAUTHORIZED;
        resHeader.set("token", null);
        message = "토큰정보가 유효하지 않습니다.";
      }
    } catch (NoSuchElementException noSuchElementException) {

      return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .headers(resHeader)
              .body("{ 'message' : '" + message + "'");
    }

    return ResponseEntity
            .status(httpStatus)
            .headers(resHeader)
            .body("{ 'message' : '" + message + "'");
  }

  /**
   * 관리자 로그인을 위해 입력한 ID/PWD를 확인한다.
   *
   * @param librarianId 관리자 아이디
   * @param password    관리자 로그인 password
   * @return jwt토큰을 발행한다.
   */
  @GetMapping(value = "/checkAuthentication/{librarianId}/{password}", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> checkAuthentication(@PathVariable String librarianId, @PathVariable String password) {
    ManagerDto manager = new ManagerDto(librarianId, password);
    String token = accountService.authentication(manager);
    if ("".equals(token))
      return new ResponseEntity<String>("{ 'message': 'ID 또는 password가 잘못되었습니다.'}", HttpStatus.FORBIDDEN);

    return ResponseEntity
            .ok()
            .header("token", token)
            .body("{ 'message': '계정정보를 정상적으로 확인하였습니다.', 'token': '" + token + "'}");
  }

}
