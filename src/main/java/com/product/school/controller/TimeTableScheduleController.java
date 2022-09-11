package com.product.school.controller;

import com.product.school.data.TimeTableSchedule;
import com.product.school.data.TimeTables;
import com.product.school.dto.ResponseDto;
import com.product.school.dto.request.TimeTableScheduleDto;
import com.product.school.message.TimeTableScheduleMessage;
import com.product.school.repositories.TimeTableRepository;
import com.product.school.repositories.TimeTableScheduleRepository;
import com.product.school.utility.PaginationMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/timeTableSchedule")
public class TimeTableScheduleController {

    @Autowired
    private PaginationMaker paginationMaker;

    @Autowired
    private TimeTableScheduleRepository timeTableScheduleRepository;

    @Autowired
    private TimeTableRepository timeTableRepository;

    private static final Logger LOGGER = Logger.getLogger(TimeTableScheduleController.class.getName());

    @GetMapping
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Page<TimeTableSchedule>> listAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "orderBy", required = false) String sortBy,
            @RequestParam(name = "orderBy", required = false) String sortOrder
    ) {
        try {
            if(sortBy!=null || sortOrder!=null){
                return ResponseEntity.ok().body(timeTableScheduleRepository.findAll(paginationMaker.createPage(page,size,sortBy,sortOrder)));
            }else{
                return ResponseEntity.ok().body(timeTableScheduleRepository.findAll(paginationMaker.createPage(page,size)));
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    //@PostAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<TimeTableSchedule> byId(@PathVariable Long id) {
        try {
            Optional<TimeTableSchedule> entityById = timeTableScheduleRepository.findById(id);
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
    public ResponseEntity<ResponseDto> create(@RequestBody final TimeTableScheduleDto timeTableScheduleDto) {
        try {

            Optional<TimeTables> timeTableById = timeTableRepository.findById(timeTableScheduleDto.getTimeTimeId());
            if(timeTableById.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            TimeTableSchedule entity = new TimeTableSchedule();
            entity.setName(timeTableScheduleDto.getName());
            entity.setStarFrom(timeTableScheduleDto.getStartFrom());
            entity.setEndTo(timeTableScheduleDto.getEndTo());
            entity.setActive(false);
            entity.setTimeTable(timeTableById.get());

            timeTableScheduleRepository.save(entity);
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableScheduleMessage.CREATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableScheduleMessage.CREATE_FAILED));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                              @RequestBody TimeTableScheduleDto timeTableScheduleDto) {
        try {
            Optional<TimeTableSchedule> entityById = timeTableScheduleRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Optional<TimeTables> timeTableById = timeTableRepository.findById(timeTableScheduleDto.getTimeTimeId());
            if (timeTableById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            entityById.get().setName(timeTableScheduleDto.getName());
            entityById.get().setStarFrom(timeTableScheduleDto.getStartFrom());
            entityById.get().setEndTo(timeTableScheduleDto.getEndTo());
            entityById.get().setTimeTable(timeTableById.get());

            timeTableScheduleRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableScheduleMessage.UPDATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableScheduleMessage.UPDATE_FAILED));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        try {
            Optional<TimeTableSchedule> entityById = timeTableScheduleRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            timeTableScheduleRepository.delete(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableScheduleMessage.DELETE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableScheduleMessage.DELETE_FAILED));
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto> activate(@PathVariable Long id) {
        try {
            Optional<TimeTableSchedule> entityById = timeTableScheduleRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(true);
            timeTableScheduleRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableScheduleMessage.ACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableScheduleMessage.ACTIVATE_FAILED));
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto> deactivate(@PathVariable Long id) {
        try {
            Optional<TimeTableSchedule> entityById = timeTableScheduleRepository.findById(id);
            if (entityById.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            entityById.get().setActive(false);
            timeTableScheduleRepository.save(entityById.get());
            return ResponseEntity.ok().
                    body(new ResponseDto(true, TimeTableScheduleMessage.DEACTIVATE_SUCCESSFUL));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception occur ", ex);
            return ResponseEntity.badRequest().
                    body(new ResponseDto(false, TimeTableScheduleMessage.DEACTIVATE_FAILED));
        }
    }

    //TODO put role restriction
    //TODO restrict delete, update , activate and deactivate... based on if the entity is used by other in relationship
    //TODO more specific response message
    //TODO list section by group
    //TODO list all by section and grade and time table

}
