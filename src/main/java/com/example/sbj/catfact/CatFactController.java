package com.example.sbj.catfact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatFactController {

    private final CatFactService catFactService;

    public CatFactController(CatFactService catFactService) {
        this.catFactService = catFactService;
    }

    @GetMapping("/catfact")
    public ResponseEntity<CatFactDTO> getCatFact(@RequestParam(defaultValue = "140") Integer max_length ) {
        return catFactService.execute(max_length);
    }
}
