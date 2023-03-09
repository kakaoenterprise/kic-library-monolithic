package com.kep.library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 관리자 계정을 나타내는 entity root로 repository의 manager table에 대한 정보를 나태낸다.
 *
 * @version 1.0
 */
@Data
@Entity
@Table(name = "manager")
@NoArgsConstructor
@AllArgsConstructor
public class ManagerEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  /*
      public ManagerEntity(String librarianId, String password, String name, String role, String gender, Date birthDay,
                     String address, String phone, String email){
          this.librarianId = librarianId;
          this.name = name;
          this.password = password;
          this.role = role;
          this.gender = gender;
          this.birthDay = birthDay;
          this.address = address;
          this.phone = phone;
          this.email = email;
      }
  */

  //@GeneratedValue(strategy = GenerationType.AUTO)
  //@GeneratedValue(generator="system-uuid")
  //@GenericGenerator(name="system-uuid", strategy = "uuid")
  //@NotEmpty(message = "id cannot be empty.")
  @Id
  @JsonProperty
  private String librarianId;

  @JsonProperty
  private String name;

  @JsonProperty
  private String role;

  @JsonProperty
  private String gender;

  @JsonProperty
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthDay;

  @JsonProperty
  private String address;

  @JsonProperty
  private String password;

  @JsonProperty
  private String phone;

  @JsonProperty
  private String email;

  public ManagerEntity(String librarianId, String password) {
    this.librarianId = librarianId;
    this.password = password;
  }

}
