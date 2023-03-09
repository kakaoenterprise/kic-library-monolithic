package com.kep.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kep.library.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

  @Id
  @JsonProperty
  private String bookId;

  @JsonProperty
  private String category;

  @JsonProperty
  private String name;

  @JsonProperty
  //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date publishDate;

  @JsonProperty
  private String company;

  @JsonProperty
  private String writer;

  @JsonProperty
  private String status;

  @JsonProperty
  private int quantity;

  @JsonProperty
  private boolean recommended;

  @JsonProperty
  private String imageUrl;

  public BookEntity toEntity() {
    return new BookEntity(bookId, category, name, publishDate, company, writer, status, quantity, recommended, imageUrl);
  }
}
