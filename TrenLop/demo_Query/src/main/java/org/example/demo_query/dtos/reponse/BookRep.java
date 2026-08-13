package org.example.demo_query.dtos.reponse;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookRep {
    private Long id;
    private String title;
    private String author;
    private String category;
    private Double price;
    private int quantity;
    private Date publistYear;
}
