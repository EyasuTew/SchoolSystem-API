package com.product.school.controller;

import com.product.school.data.GeneralSubjects;
import com.product.school.data.Grades;
import com.product.school.data.Subjects;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.SubjectCreateDto;
import com.product.school.message.SubjectMessage;
import com.product.school.repositories.GeneralSubjectRepository;
import com.product.school.repositories.GradeRepository;
import com.product.school.repositories.SubjectRepository;
import com.product.school.utility.PaginationMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private PaginationMaker paginationMaker;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GeneralSubjectRepository generalSubjectRepository;

    private static final Logger LOGGER = Logger.getLogger(SubjectController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<Subjects>> listAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "orderBy", required = false) String sortBy,
            @RequestParam(name = "orderBy", required = false) String sortOrder
    ) {
        try {
            if(sortBy!=null || sortOrder!=null){
                return ResponseEntity.ok().body(subjectRepository.findAll(paginationMaker.createPage(page,size,sortBy,sortOrder)));
            }else{
                return ResponseEntity.ok().body(subjectRepository.findAll(paginationMaker.createPage(page,size)));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Subjects> byId(@PathVariable Long id) {
        try {
            Optional<Subjects> entityById = subjectRepository.findById(id);
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
            @RequestBody SubjectCreateDto subjectCreateDto) {
        try {
            Optional<Grades> gradesOptional = gradeRepository.findById(subjectCreateDto.getGradeId());
            if(gradesOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            Optional<GeneralSubjects> generalSubjectsOptional = generalSubjectRepository.findById(subjectCreateDto.getGeneralSubjectId());
            if(generalSubjectsOptional.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            Subjects entity = new Subjects();
            entity.setName(subjectCreateDto.getName());
            entity.setGrade(gradesOptional.get());
            entity.setGeneralSubject(generalSubjectsOptional.get());
            entity.setActive(false);
            subjectRepository.save(entity);
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SubjectMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SubjectMessage.CREATE_FAILED));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                              @RequestParam String name) {
        try {
            Optional<Subjects> entityById = subjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            entityById.get().setName(name);
            subjectRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SubjectMessage.UPDATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SubjectMessage.UPDATE_FAILED));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            Optional<Subjects> entityById = subjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            subjectRepository.delete(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SubjectMessage.DELETE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SubjectMessage.DELETE_FAILED));
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto> activate(@PathVariable Long id) {
        try {
            Optional<Subjects> entityById = subjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(true);
            subjectRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SubjectMessage.ACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SubjectMessage.ACTIVATE_FAILED));
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto> deactivate(@PathVariable Long id) {
        try {
            Optional<Subjects> entityById = subjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(false);
            subjectRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, SubjectMessage.DEACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, SubjectMessage.DEACTIVATE_FAILED));
        }
    }
    //TODO put role restriction
    //TODO restrict delete, update , activate and deactivate... based on if the entity is used by other in relationship
    //TODO more specific response message
    //TODO list group of section by grade
    //TODO update grade with restriction

}
