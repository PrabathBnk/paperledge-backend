package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {
    private String id;
    private String name;
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }
}
