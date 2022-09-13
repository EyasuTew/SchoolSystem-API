package com.product.school.Service;

import com.product.school.data.Parents;
import com.product.school.data.Persons;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.PersonsRequestDto;
import com.product.school.repositories.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonsService {

    private PersonsRepository personsRepository;

    @Autowired
    public PersonsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public ResponseDto addParents(PersonsRequestDto personsRequestDto) {
        Persons persons = new Persons();
        persons.setFristName(personsRequestDto.getFristName());
        persons.setMidName(personsRequestDto.getMidName());
        persons.setLastName(personsRequestDto.getLastName());
        persons.setEmailAddress(personsRequestDto.getEmailAddress());
        persons.setPhoneNumber(personsRequestDto.getPhoneNumber());
        persons.setGender(personsRequestDto.getGender());
        persons.setSubcity(personsRequestDto.getSubcity());
        persons.setKebele(personsRequestDto.getKebele());
        personsRepository.save(persons);
        return new ResponseDto(true, "created Successfully");
    }
}
