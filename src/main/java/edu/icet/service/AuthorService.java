package edu.icet.service;

import edu.icet.dto.Author;

public interface AuthorService {
    Author getAuthorByName(String name);
    Author addAuthor(Author author);
}
