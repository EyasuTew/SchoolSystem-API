package com.product.school.controller;

import com.product.school.Service.ParentsService;
import com.product.school.data.Parents;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.ParentsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/parents")
public class ParentsController {
    @Autowired
    private ParentsService parentsService;

    @PostMapping("/addParents")
    public ResponseEntity<ResponseDto> addParents(ParentsRequestDto parentsRequestDto) {
        return parentsService.addParents(parentsRequestDto);
    }

    @PutMapping("/updateparents")
    public ResponseDto updateparents(@RequestParam long id, ParentsRequestDto parentsRequestDto) {
        return parentsService.updateparents(id, parentsRequestDto);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Parents>> listAll() {
        return parentsService.listAll();
    }


    @GetMapping("/listAll")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        return parentsService.delete(id);
    }
}
