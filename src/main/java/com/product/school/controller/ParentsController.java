package com.product.school.controller;

import com.product.school.Service.ParentsService;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.ParentsRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/parents")
public class ParentsController {
    @Autowired
    private ParentsService parentsService;

    @PostMapping("/addParents")
    public ResponseDto addParents(ParentsRequestDto parentsRequestDto) {
        return parentsService.addParents(parentsRequestDto);
    }


}
