package com.millky.demo.post;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


// @Document
@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Post {

    @Id
    String id;

    // @Indexed
    @NotBlank
    String subject;

    @NotBlank
    String content;

    @CreatedDate
    LocalDateTime createdAt;

    @Size(min = 1, max = 16)
    String createdBy;
}
