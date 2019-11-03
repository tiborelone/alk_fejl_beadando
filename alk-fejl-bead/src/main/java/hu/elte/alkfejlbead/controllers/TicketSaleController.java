/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.entities.Event;
import hu.elte.alkfejlbead.repositories.EventRepository;
import hu.elte.alkfejlbead.entities.TicketSale;
import hu.elte.alkfejlbead.entities.User;
import hu.elte.alkfejlbead.repositories.TicketSaleRepository;
import hu.elte.alkfejlbead.repositories.UserRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticketsSale")
public class TicketSaleController {
    
    @Autowired
    private TicketSaleRepository ticketSaleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<TicketSale>> getAll() {
        return ResponseEntity.ok(ticketSaleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketSale> get(@PathVariable Integer id) {
        Optional<TicketSale> ticketSale = ticketSaleRepository.findById(id);
        if (!ticketSale.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(ticketSale.get());
    }

    @PostMapping("")
    public ResponseEntity<TicketSale> post(@RequestBody TicketSale ticketSale) {
        TicketSale newTicketSale = ticketSaleRepository.save(ticketSale);
        return ResponseEntity.ok(newTicketSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<TicketSale> ticketSale = ticketSaleRepository.findById(id);
        if (!ticketSale.isPresent()) {
            ResponseEntity.notFound();
        }
        ticketSaleRepository.delete(ticketSale.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketSale> put(@PathVariable Integer id, @RequestBody TicketSale ticketSale) {
        Optional<TicketSale> foundTicketSale = ticketSaleRepository.findById(id);
        if (!foundTicketSale.isPresent()) {
            ResponseEntity.notFound();
        }
        
        TicketSale ticketSaleToUpdate = foundTicketSale.get();
        if(ticketSale.getBarcode() != null) {
            ticketSaleToUpdate.setBarcode(ticketSale.getBarcode());
        }
        if(ticketSale.getPrice() != null) {
            ticketSaleToUpdate.setPrice(ticketSale.getPrice());
        }
        
        return ResponseEntity.ok(ticketSaleRepository.save(ticketSaleToUpdate));
    }

    /*@GetMapping("/{id}/event")
    public ResponseEntity<Event> events(@PathVariable Integer id) {
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(id);
        if (oTicketSale.isPresent()) {
            return ResponseEntity.ok(oTicketSale.get().getEvent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping("/{id}/courses")
    public ResponseEntity<TicketSale> takeCourse(@PathVariable Integer id,
            @RequestParam(value = "courseId") Integer courseId) {
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(id);
        Optional<Course> oCourse = courseRepository.findById(courseId);
        if (oTicketSale.isPresent() && oCourse.isPresent()) {
            TicketSale ticketSale = oTicketSale.get();
            ticketSale.getCourses().add(oCourse.get());
            ticketSaleRepository.save(ticketSale);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(ticketSale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/courses")
    public ResponseEntity<Course> DropCourse(@PathVariable Integer id,
            @RequestParam(value = "courseId") Integer courseId) {
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(id);
        if (oTicketSale.isPresent()) {
            Course foundCourse = oTicketSale.get().findCourseById(courseId);
            if (Objects.nonNull(foundCourse)) {
                TicketSale ticketSale = oTicketSale.get();
                ticketSale.getCourses().add(foundCourse);
                ticketSaleRepository.save(ticketSale);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundCourse);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/{id}/seller")
    public ResponseEntity<User> users(@PathVariable Integer id) {
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(id);
        if (oTicketSale.isPresent()) {
            return ResponseEntity.ok(oTicketSale.get().getSeller());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping("/{id}/exams")
    public ResponseEntity<Exam> takeExam(@PathVariable Integer id,
            @RequestParam(value = "examId") Integer examId) {
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(id);
        Optional<Exam> oExam = examRepository.findById(examId);
        if (oTicketSale.isPresent() && oExam.isPresent()) {
            TicketSale ticketSale = oTicketSale.get();
            ticketSale.getExams().add(oExam.get());
            ticketSaleRepository.save(ticketSale);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(oExam.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}/exams")
    public ResponseEntity<Exam> DropExam(@PathVariable Integer id,
            @RequestParam(value = "examId") Integer examId) {
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(id);
        if (oTicketSale.isPresent()) {
            Exam foundExam = oTicketSale.get().findExamById(examId);
            if (Objects.nonNull(foundExam)) {
                TicketSale ticketSale = oTicketSale.get();
                ticketSale.getExams().add(foundExam);
                ticketSaleRepository.save(ticketSale);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundExam);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
