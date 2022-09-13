package com.product.school.Service;

import com.product.school.data.Parents;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.ParentsRequestDto;
import com.product.school.repositories.ParentsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

public class ParentsService {


    private final ParentsRepository parentsRepository;


    @Autowired
    public ParentsService(ParentsRepository parentsRepository) {
        this.parentsRepository = parentsRepository;
    }

    public ResponseDto addParents(ParentsRequestDto parentsRequestDto) {
//
        Parents parents = new Parents();
        parents.setFristName(parentsRequestDto.getFristName());
        parents.setMidName(parentsRequestDto.getMidName());
        parents.setLastName(parentsRequestDto.getLastName());
        parents.setPhoneNumber(parentsRequestDto.getPhoneNumber());
        //todo
        parents.setStudentid(parentsRequestDto.getStudentid());

        parents.setCreated_on(Instant.now());


        parentsRepository.save(parents);

        return new ResponseDto(true, "created Successfully");
    }


}
