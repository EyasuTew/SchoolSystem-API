package com.product.school.controller;

import com.product.school.data.Grades;
import com.product.school.data.Sections;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.SectionCreateDto;
import com.product.school.message.SectionMessage;
import com.product.school.repositories.GradeRepository;
import com.product.school.repositories.SectionRepository;
import com.product.school.utility.PaginationMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/section")
public class SectionController {

    @Autowired
    private PaginationMaker paginationMaker;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private GradeRepository gradeRepository;
    private static final Logger LOGGER = Logger.getLogger(SectionController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<Sections>> listAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "orderBy", required = false) String sortBy,
            @RequestParam(name = "orderBy", required = false) String sortOrder
    ) {
        try {
            if(sortBy!=null || sortOrder!=null){
                return ResponseEntity.ok().body(sectionRepository.findAll(paginationMaker.createPage(page,size,sortBy,sortOrder)));
            }else{
                return ResponseEntity.ok().body(sectionRepository.findAll(paginationMaker.createPage(page,size)));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Sections> byId(@PathVariable Long id) {
        try {
            Optional<Sections> entityById = sectionRepository.findById(id);
            if(entityById.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(entityById.get());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(
            @RequestBody SectionCreateDto sectionCreateDto) {
        try {
            Optional<Grades> gradesOptional = gradeRepository.findById(sectionCreateDto.getGradeId());
            if(gradesOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            Sections entity = new Sections();
            entity.setName(sectionCreateDto.getName());
            entity.setGrade(gradesOptional.get());
            entity.setActive(false);
            sectionRepository.save(entity);
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SectionMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SectionMessage.CREATE_FAILED));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                              @RequestParam String name) {
        try {
            Optional<Sections> entityById = sectionRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setName(name);
            sectionRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SectionMessage.UPDATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SectionMessage.UPDATE_FAILED));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            Optional<Sections> entityById = sectionRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            sectionRepository.delete(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SectionMessage.DELETE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SectionMessage.DELETE_FAILED));
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto> activate(@PathVariable Long id) {
        try {
            Optional<Sections> entityById = sectionRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(true);
            sectionRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SectionMessage.ACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SectionMessage.ACTIVATE_FAILED));
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto> deactivate(@PathVariable Long id) {
        try {
            Optional<Sections> entityById = sectionRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(false);
            sectionRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SectionMessage.DEACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SectionMessage.DEACTIVATE_FAILED));
        }
    }
    //TODO put role restriction
    //TODO restrict delete, update , activate and deactivate... based on if the entity is used by other in relationship
    //TODO more specific response message
    //TODO list group of section by grade
    //TODO update grade with restriction

}
