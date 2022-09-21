package com.product.school.service;

import com.product.school.data.Persons;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.PersonsRequestDto;
import com.product.school.message.PersonsMessage;
import com.product.school.repositories.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonsService {

    private PersonsRepository personsRepository;

    @Autowired
    public PersonsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    private static final Logger LOGGER = Logger.getLogger(ParentsService.class.getName());

    public ResponseEntity<ResponseDto> addPersons(PersonsRequestDto personsRequestDto) {
        try {
            Persons persons = new Persons();
            persons.setFristName(personsRequestDto.getFristName());
//            persons.setMidName(personsRequestDto.getMidName());
//            persons.setLastName(personsRequestDto.getLastName());
//            persons.setEmailAddress(personsRequestDto.getEmailAddress());
//            persons.setPhoneNumber(personsRequestDto.getPhoneNumber());
            persons.setGender(personsRequestDto.getGender());
            persons.setSubcity(personsRequestDto.getSubcity());
            persons.setKebele(personsRequestDto.getKebele());
            persons.setCreatedOn(Instant.now());
            personsRepository.save(persons);
            return ResponseEntity.ok().body(new ResponseDto(true, "created Successfully"));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, PersonsMessage.CREATE_FAILED));
        }

    }

    public ResponseDto updatepersons(Long id, PersonsRequestDto personsRequestDto) {
        Persons persons = personsRepository.findById(id).get();
        if (persons == null) {
            return new ResponseDto(false, "there is id number found");
        }
        persons.setFristName(personsRequestDto.getFristName());
//        persons.setMidName(personsRequestDto.getMidName());
//        persons.setLastName(personsRequestDto.getLastName());
//        persons.setEmailAddress(personsRequestDto.getEmailAddress());
//        persons.setPhoneNumber(personsRequestDto.getPhoneNumber());
        persons.setGender(personsRequestDto.getGender());
        persons.setSubcity(personsRequestDto.getSubcity());
        persons.setKebele(personsRequestDto.getKebele());
//        persons.setUpdatedOn(Instant.now());


        personsRepository.save(persons);
        return new ResponseDto(true, "your file has been updated");

    }
    public ResponseEntity<List<Persons>> listAll() {
        try {
            return ResponseEntity.ok().body(personsRepository.findAll());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }

    }


    public  ResponseEntity<ResponseDto>   delete(long id) {
        try {
            Optional<Persons> persons = personsRepository.findById(id);
            if (!persons.isPresent()) {
                ResponseEntity.notFound().build();

            }
            personsRepository.delete(persons.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, PersonsMessage.DELETE_SUCCESSFUL));
        }
        catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, PersonsMessage.DELETE_FAILED));
        }



    }
}
