package edu.icet.entity;

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

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private GenreEntity genre;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private AuthorEntity author;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private PublicationEntity publication;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "owner_user")
    private UserEntity ownerUser;
}
