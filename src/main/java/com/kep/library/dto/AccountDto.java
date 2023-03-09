package com.kep.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AccountDto {

  private String librarianId;

  private String username;

  private String role;

  public AccountDto(String id, String username, String role) {
    this.librarianId = id;
    this.username = username;
    this.role = role;
  }
}
