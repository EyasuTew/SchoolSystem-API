package com.product.school.controller;

import com.product.school.data.GeneralSubjects;
import com.product.school.dto.ResponseDto;
import com.product.school.message.GeneralSubjectMessage;
import com.product.school.repositories.GeneralSubjectRepository;
import com.product.school.utility.PaginationMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/generalSubject")
public class GeneralSubjectController {

    @Autowired
    private PaginationMaker paginationMaker;

    @Autowired
    private GeneralSubjectRepository generalSubjectRepository;
    private static final Logger LOGGER = Logger.getLogger(GeneralSubjectController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<GeneralSubjects>> listAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "orderBy", required = false) String sortBy,
            @RequestParam(name = "orderBy", required = false) String sortOrder
    ) {
        try {
            if(sortBy!=null || sortOrder!=null){
                return ResponseEntity.ok().body(generalSubjectRepository.findAll(paginationMaker.createPage(page,size,sortBy,sortOrder)));
            }else{
                return ResponseEntity.ok().body(generalSubjectRepository.findAll(paginationMaker.createPage(page,size)));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<GeneralSubjects> byId(@PathVariable Long id) {
        try {
            Optional<GeneralSubjects> entityById = generalSubjectRepository.findById(id);
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
            GeneralSubjects entity = new GeneralSubjects();
            entity.setName(name);
            entity.setActive(false);
            generalSubjectRepository.save(entity);
            return ResponseEntity.ok().
                    body(new ResponseDto("success", GeneralSubjectMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto("fail", GeneralSubjectMessage.CREATE_FAILED));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                              @RequestParam String name) {
        try {
            Optional<GeneralSubjects> entityById = generalSubjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setName(name);
            generalSubjectRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto("success", GeneralSubjectMessage.UPDATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto("fail", GeneralSubjectMessage.UPDATE_FAILED));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            Optional<GeneralSubjects> entityById = generalSubjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            generalSubjectRepository.delete(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto("success", GeneralSubjectMessage.DELETE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto("fail", GeneralSubjectMessage.DELETE_FAILED));
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto> activate(@PathVariable Long id) {
        try {
            Optional<GeneralSubjects> entityById = generalSubjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(true);
            generalSubjectRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto("success", GeneralSubjectMessage.ACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto("fail", GeneralSubjectMessage.ACTIVATE_FAILED));
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto> deactivate(@PathVariable Long id) {
        try {
            Optional<GeneralSubjects> entityById = generalSubjectRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(false);
            generalSubjectRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto("success", GeneralSubjectMessage.DEACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto("fail", GeneralSubjectMessage.DEACTIVATE_FAILED));
        }
    }
    //TODO put role restriction
    //TODO restrict delete, update , activate and deactivate... based on if the entity is used by other in relationship


}
