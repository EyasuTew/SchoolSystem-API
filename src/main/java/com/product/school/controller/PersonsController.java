package com.product.school.controller;

import com.product.school.Service.PersonsService;
import com.product.school.data.Persons;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.PersonsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/persons")
public class PersonsController {
    @Autowired
    private PersonsService personsService;

    @PostMapping("/addPersons")
    public ResponseEntity<ResponseDto> addParents(PersonsRequestDto personsRequestDto) {
        return personsService.addPersons(personsRequestDto);
    }

    @PutMapping("/updatepersons")
    public ResponseDto updateparents(@RequestParam long id, PersonsRequestDto personsRequestDto) {
        return personsService.updatepersons(id, personsRequestDto);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Persons>> listAll() {
        return personsService.listAll();
    }


    @GetMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        return personsService.delete(id);
    }
}
