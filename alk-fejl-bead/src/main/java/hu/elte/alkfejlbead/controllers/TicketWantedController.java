/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.entities.Event;
import hu.elte.alkfejlbead.entities.TicketSale;
import hu.elte.alkfejlbead.entities.TicketWanted;
import hu.elte.alkfejlbead.entities.User;
import hu.elte.alkfejlbead.repositories.EventRepository;
import hu.elte.alkfejlbead.repositories.TicketSaleRepository;
import hu.elte.alkfejlbead.repositories.TicketWantedRepository;
import hu.elte.alkfejlbead.repositories.UserRepository;
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
@RequestMapping("ticketsWanted")
public class TicketWantedController {
    
    @Autowired
    private TicketWantedRepository ticketWantedRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<TicketWanted>> getAll() {
        return ResponseEntity.ok(ticketWantedRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketWanted>get(@PathVariable Integer id) {
        Optional<TicketWanted> ticketWanted = ticketWantedRepository.findById(id);
        if (!ticketWanted.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(ticketWanted.get());
    }

    @PostMapping("")
    public ResponseEntity<TicketWanted> post(@RequestBody TicketWanted ticketWanted) {
        TicketWanted newTicketWanted = ticketWantedRepository.save(ticketWanted);
        return ResponseEntity.ok(newTicketWanted);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<TicketWanted> ticketWanted = ticketWantedRepository.findById(id);
        if (!ticketWanted.isPresent()) {
            ResponseEntity.notFound();
        }
        ticketWantedRepository.delete(ticketWanted.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketWanted> put(@PathVariable Integer id, @RequestBody TicketWanted ticketWanted) {
        Optional<TicketWanted> foundTicketWanted = ticketWantedRepository.findById(id);
        if (!foundTicketWanted.isPresent()) {
            ResponseEntity.notFound();
        }
        
        TicketWanted ticketWantedToUpdate = foundTicketWanted.get();
        
        if(ticketWanted.getPriceLimit() != null) {
            ticketWantedToUpdate.setPriceLimit(ticketWanted.getPriceLimit());
        }
        
        return ResponseEntity.ok(ticketWantedRepository.save(ticketWantedToUpdate));
    }

    /*@GetMapping("/{id}/event")
    public ResponseEntity<Event> events(@PathVariable Integer id) {
        Optional<TicketWanted> oTicketWanted = ticketWantedRepository.findById(id);
        if (oTicketWanted.isPresent()) {
            return ResponseEntity.ok(oTicketWanted.get().getEvent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/seller")
    public ResponseEntity<User> users(@PathVariable Integer id) {
        Optional<TicketWanted> oTicketWanted = ticketWantedRepository.findById(id);
        if (oTicketWanted.isPresent()) {
            return ResponseEntity.ok(oTicketWanted.get().getBuyer());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
}
