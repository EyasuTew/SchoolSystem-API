package com.product.school.controller;

import com.product.school.data.Sections;
import com.product.school.data.Subjects;
import com.product.school.data.TimeTables;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.TimeTableDto;
import com.product.school.message.TimeTableMessage;
import com.product.school.repositories.SectionRepository;
import com.product.school.repositories.SubjectRepository;
import com.product.school.repositories.TimeTableRepository;
import com.product.school.utility.PaginationMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/timeTable")
public class TimeTableController {

    @Autowired
    private PaginationMaker paginationMaker;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    private static final Logger LOGGER = Logger.getLogger(TimeTableController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<TimeTables>> listAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "orderBy", required = false) String sortBy,
            @RequestParam(name = "orderBy", required = false) String sortOrder
    ) {
        try {
            if(sortBy!=null || sortOrder!=null){
                return ResponseEntity.ok().body(timeTableRepository.findAll(paginationMaker.createPage(page,size,sortBy,sortOrder)));
            }else{
                return ResponseEntity.ok().body(timeTableRepository.findAll(paginationMaker.createPage(page,size)));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<TimeTables> byId(@PathVariable Long id) {
        try {
            Optional<TimeTables> entityById = timeTableRepository.findById(id);
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
    public ResponseEntity<ResponseDto> create(@RequestBody final TimeTableDto timeTableDto) {
        try {

            Optional<Sections> sectionById = sectionRepository.findById(timeTableDto.getSectionId());
            if(sectionById.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            Optional<Subjects> subjectById = subjectRepository.findById(timeTableDto.getSectionId());
            if(subjectById.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            TimeTables entity = new TimeTables();
            entity.setName(timeTableDto.getName());
            entity.setStartFrom(timeTableDto.getStartFrom());
            entity.setEndTo(timeTableDto.getEndTo());
            entity.setActive(false);
            entity.setSection(sectionById.get());
            entity.setSubject(subjectById.get());

            timeTableRepository.save(entity);
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableMessage.CREATE_FAILED));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                              @RequestBody final TimeTableDto timeTableDto) {
        try {
            Optional<TimeTables> entityById = timeTableRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Optional<Sections> sectionById = sectionRepository.findById(timeTableDto.getSectionId());
            if(sectionById.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            Optional<Subjects> subjectById = subjectRepository.findById(timeTableDto.getSectionId());
            if(subjectById.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            entityById.get().setName(timeTableDto.getName());
            entityById.get().setStartFrom(timeTableDto.getStartFrom());
            entityById.get().setEndTo(timeTableDto.getEndTo());
            timeTableRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableMessage.UPDATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableMessage.UPDATE_FAILED));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            Optional<TimeTables> entityById = timeTableRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            timeTableRepository.delete(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableMessage.DELETE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableMessage.DELETE_FAILED));
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto> activate(@PathVariable Long id) {
        try {
            Optional<TimeTables> entityById = timeTableRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(true);
            timeTableRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableMessage.ACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableMessage.ACTIVATE_FAILED));
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto> deactivate(@PathVariable Long id) {
        try {
            Optional<TimeTables> entityById = timeTableRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(false);
            timeTableRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableMessage.DEACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableMessage.DEACTIVATE_FAILED));
        }
    }

    //TODO put role restriction
    //TODO restrict delete, update , activate and deactivate... based on if the entity is used by other in relationship
    //TODO more specific response message
    //TODO list section by group
    //TODO list by subject, section
    //TODO add academic year

}
