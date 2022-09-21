package com.product.school.service;

import com.product.school.data.Parents;
import com.product.school.data.Persons;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.ParentsRequestDto;
import com.product.school.message.ParentsMessage;
import com.product.school.repositories.ParentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ParentsService {


    private final ParentsRepository parentsRepository;


    @Autowired
    public ParentsService(ParentsRepository parentsRepository) {
        this.parentsRepository = parentsRepository;
    }

    private static final Logger LOGGER = Logger.getLogger(ParentsService.class.getName());

    public ResponseEntity<ResponseDto> addParents(ParentsRequestDto parentsRequestDto) {
        try {
            Parents parents = new Parents();
//            parents.setPersonid(parentsRequestDto.getPersonid());
//            //todo
//            parents.setStudentid(parentsRequestDto.getStudentid());
//            parents.setCreated_on(Instant.now());
            parentsRepository.save(parents);
            return ResponseEntity.ok().
                    body(new ResponseDto(true, ParentsMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, ParentsMessage.CREATE_FAILED));


        }
    }

    public ResponseDto updateparents(Long id, ParentsRequestDto parentsRequestDto) {
        Parents parents = parentsRepository.findById(id).get();
        if (parents == null) {
            return new ResponseDto(false, "there is id number found");
        }
//       parents.setPersonid(parentsRequestDto.getPersonid());
//        //todo
//        parents.setStudentid(parentsRequestDto.getStudentid());
//        parents.setCreated_on(Instant.now());
        parentsRepository.save(parents);
        return new ResponseDto(true, "your file has been updated");

    }

    public ResponseEntity<List<Parents>> listAll() {
        try {
            return ResponseEntity.ok().body(parentsRepository.findAll());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }

    }


    public  ResponseEntity<ResponseDto>   delete(long id) {
        try {
            Optional<Parents> parents = parentsRepository.findById(id);
            if (!parents.isPresent()) {
                ResponseEntity.notFound().build();

            }
            parentsRepository.delete(parents.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, ParentsMessage.DELETE_SUCCESSFUL));
        }
    catch (Exception ex){
        LOGGER.log(Level.SEVERE, "Exception occur ", ex);
        return ResponseEntity.badRequest().
                body(new ResponseDto(false, ParentsMessage.DELETE_FAILED));
    }



    }
}
