package com.product.school.controller;

import com.product.school.service.ParentsService;
import com.product.school.data.Parents;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.ParentsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentsController {
    @Autowired
    private ParentsService parentsService;

    @PostMapping
    public ResponseEntity<ResponseDto> add(@RequestBody ParentsRequestDto parentsRequestDto) {
        return parentsService.addParents(parentsRequestDto);
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable long id,@RequestBody ParentsRequestDto parentsRequestDto) {
        return parentsService.updateparents(id, parentsRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<Parents>> listAll() {
        return parentsService.listAll();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        return parentsService.delete(id);
    }
}
