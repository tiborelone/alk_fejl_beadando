/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.entities.Event;
import hu.elte.alkfejlbead.entities.TicketSale;
import hu.elte.alkfejlbead.entities.TicketWanted;
import hu.elte.alkfejlbead.repositories.EventRepository;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Event>> getAll() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(event.get());
    }

    @PostMapping("")
    public ResponseEntity<Event> post(@RequestBody Event event) {
        Event newEvent = eventRepository.save(event);
        return ResponseEntity.ok(newEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            ResponseEntity.notFound();
        }
        eventRepository.delete(event.get());
        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> put(@PathVariable Integer id, @RequestBody Event event) {
        Optional<Event> foundEvent = eventRepository.findById(id);
        if (!foundEvent.isPresent()) {
            ResponseEntity.notFound();
        }
        
        Event eventToUpdate = foundEvent.get();
        if(event.getName() != null) {
            eventToUpdate.setName(event.getName());
        }
        
        if(event.getDate() != null) {
            eventToUpdate.setDate(event.getDate());
        }
        
        return ResponseEntity.ok(eventRepository.save(eventToUpdate));
    }
    
    @GetMapping("/{id}/ticketsale")
    public ResponseEntity<Iterable<TicketSale>> ticketsSale(@PathVariable Integer id) {
        Optional<Event> oEvent = eventRepository.findById(id);
        if (oEvent.isPresent()) {
            return ResponseEntity.ok(oEvent.get().getSale());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/ticketwanted")
    public ResponseEntity<Iterable<TicketWanted>> ticketsWanted(@PathVariable Integer id) {
        Optional<Event> oEvent = eventRepository.findById(id);
        if (oEvent.isPresent()) {
            return ResponseEntity.ok(oEvent.get().getWanted());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
