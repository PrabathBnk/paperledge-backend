package edu.icet.controller;

import edu.icet.dto.Genre;
import edu.icet.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService service;

    @GetMapping("/all")
    public List<Genre> getAll(){
        return service.getAllGenres();
    }
}
