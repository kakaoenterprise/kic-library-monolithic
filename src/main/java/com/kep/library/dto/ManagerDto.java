package com.kep.library.dto;

import com.kep.library.entity.ManagerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {

  private String librarianId;

  private String name;

  private String role;

  private String gender;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthDay;

  private String address;

  private String password;

  private String phone;

  private String email;

  public ManagerDto(String librarianId, String password) {
    this.librarianId = librarianId;
    this.password = password;
  }

  public ManagerEntity toEntity() {
    return new ManagerEntity(librarianId, password, name, role, birthDay, gender,
            address, phone, email);
  }

  public ManagerEntity toEntityWithIdAndPassword() {
    return new ManagerEntity(librarianId, password);
  }

}
