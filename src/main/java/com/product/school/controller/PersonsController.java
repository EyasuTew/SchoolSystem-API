package com.product.school.controller;

import com.product.school.data.Persons;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.PersonsRequestDto;
import com.product.school.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonsController {
    @Autowired
    private PersonsService personsService;

    @PostMapping
    public ResponseEntity<ResponseDto> add(@RequestBody PersonsRequestDto personsRequestDto) {
        return personsService.addPersons(personsRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable long id, @RequestBody PersonsRequestDto personsRequestDto) {
        return personsService.updatepersons(id, personsRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<Persons>> listAll() {
        return personsService.listAll();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        return personsService.delete(id);
    }
}
