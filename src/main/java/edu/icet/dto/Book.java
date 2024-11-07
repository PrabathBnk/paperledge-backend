package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String id;
    private String title;
    private String isbn;
    private double price;
    private double discount;
    private int year;
    private int quantity;
    private String mode;
    private String description;
    private Genre genre;
    private Author author;
    private Publication publication;
    private User ownerUser;
    private String image;
}
