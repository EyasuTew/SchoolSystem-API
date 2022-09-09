package com.product.school.controller;

import com.product.school.data.Grades;
import com.product.school.dto.ResponseDto;
import com.product.school.message.GradeMessage;
import com.product.school.repositories.GradeRepository;
import com.product.school.utility.PaginationMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/grade")
public class GradeController {

    @Autowired
    private PaginationMaker paginationMaker;

    @Autowired
    private GradeRepository gradeRepository;
    private static final Logger LOGGER = Logger.getLogger(GradeController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<Grades>> listAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "orderBy", required = false) String sortBy,
            @RequestParam(name = "orderBy", required = false) String sortOrder
    ) {
        try {
            if(sortBy!=null || sortOrder!=null){
                return ResponseEntity.ok().body(gradeRepository.findAll(paginationMaker.createPage(page,size,sortBy,sortOrder)));
            }else{
                return ResponseEntity.ok().body(gradeRepository.findAll(paginationMaker.createPage(page,size)));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Grades> byId(@PathVariable Long id) {
        try {
            Optional<Grades> entityById = gradeRepository.findById(id);
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
    public ResponseEntity<ResponseDto> create(@RequestParam String name) {
        try {
            Grades entity = new Grades();
            entity.setName(name);
            entity.setActive(false);
            gradeRepository.save(entity);
            return ResponseEntity.ok().
                    body(new ResponseDto(true, GradeMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, GradeMessage.CREATE_FAILED));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                              @RequestParam String name) {
        try {
            Optional<Grades> entityById = gradeRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setName(name);
            gradeRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, GradeMessage.UPDATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, GradeMessage.UPDATE_FAILED));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            Optional<Grades> entityById = gradeRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            gradeRepository.delete(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, GradeMessage.DELETE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, GradeMessage.DELETE_FAILED));
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto> activate(@PathVariable Long id) {
        try {
            Optional<Grades> entityById = gradeRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(true);
            gradeRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, GradeMessage.ACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, GradeMessage.ACTIVATE_FAILED));
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto> deactivate(@PathVariable Long id) {
        try {
            Optional<Grades> entityById = gradeRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(false);
            gradeRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, GradeMessage.DEACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, GradeMessage.DEACTIVATE_FAILED));
        }
    }
    //TODO put role restriction
    //TODO restrict delete, update , activate and deactivate... based on if the entity is used by other in relationship
    //TODO more specific response message

}
