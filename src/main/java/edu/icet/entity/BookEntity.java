package edu.icet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private double price;
    private double discount;
    private int year;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private String mode;
    private String description;
    @Column(nullable = false)
    private String image;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JsonBackReference("genre")
    private GenreEntity genre;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JsonBackReference("author")
    private AuthorEntity author;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JsonBackReference("publication")
    private PublicationEntity publication;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_user")
    @JsonBackReference("user")
    private UserEntity ownerUser;
}
