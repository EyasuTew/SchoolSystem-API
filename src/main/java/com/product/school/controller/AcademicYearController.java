package com.product.school.controller;

import com.product.school.data.AcademicYears;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.AcademicYearDto;
import com.product.school.message.AcademicYearMessage;
import com.product.school.repositories.AcademicYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/academicYear")
public class AcademicYearController {

    @Autowired
    private AcademicYearRepository academicYearRepository;
    private static final Logger LOGGER = Logger.getLogger(AcademicYearController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<AcademicYears>> listAll(){
        try {
            return ResponseEntity.ok().body(academicYearRepository.findAll());
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody final AcademicYearDto academicYearDto) {
        try {
            System.out.println(academicYearDto.toString());
            AcademicYears academicYears = new AcademicYears();
            academicYears.setYear(academicYearDto.getYear());
            academicYears.setIsActive(academicYearDto.getIsActive());
            academicYears.setIsCurrent(academicYearDto.getIsCurrent());
            academicYearRepository.save(academicYears);
            return ResponseEntity.ok().
                    body(new ResponseDto(true,AcademicYearMessage.CREATE_SUCCESSFUL));
        }catch (Exception ex){
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false,AcademicYearMessage.CREATE_FAILED));
        }
    }

    //TODO put role restriction
    //TODO change academic year

}
