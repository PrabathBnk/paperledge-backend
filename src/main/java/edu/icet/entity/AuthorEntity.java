package edu.icet.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference("author")
    private List<BookEntity> books;
}
