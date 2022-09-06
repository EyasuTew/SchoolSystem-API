package com.product.school.controller;

import com.product.school.data.AcademicYear;
import com.product.school.data.User;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.AcademicYearDto;
import com.product.school.dto.request.UserCreateDto;
import com.product.school.message.AcademicYearMessage;
import com.product.school.repositories.AcedemicYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/academicYear")
public class AcademicYearController {

    @Autowired
    private AcedemicYearRepository acedemicYearRepository;
    private static final Logger LOGGER = Logger.getLogger(AcademicYearController.class.getName());

    @GetMapping("/listAll")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<AcademicYear>> listAll(){
        try {
            return ResponseEntity.ok().body(acedemicYearRepository.findAll());
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@RequestBody final AcademicYearDto academicYearDto) {
        try {
            System.out.println(academicYearDto.toString());
            AcademicYear academicYear = new AcademicYear();
            academicYear.setYear(academicYearDto.getYear());
            academicYear.setActive(academicYearDto.getIsActive());
            academicYear.setCurrent(academicYearDto.getIsCurrent());
            acedemicYearRepository.save(academicYear);
            return ResponseEntity.ok().
                    body(new ResponseDto("success",AcademicYearMessage.CREATE_SUCCESSFUL));
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto("fail",AcademicYearMessage.CREATE_FAILED));
        }
    }

    //TODO put role restriction
    //TODO change academic year


}
