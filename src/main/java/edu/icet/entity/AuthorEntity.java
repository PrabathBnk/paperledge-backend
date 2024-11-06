package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "author")
public class AuthorEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;
}
