/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejlbead.controllers;

import hu.elte.alkfejlbead.security.AuthenticatedUser;
import hu.elte.alkfejlbead.entities.Event;
import hu.elte.alkfejlbead.entities.TicketSale;
import hu.elte.alkfejlbead.entities.TicketWanted;
import hu.elte.alkfejlbead.entities.User;
import hu.elte.alkfejlbead.repositories.EventRepository;
import hu.elte.alkfejlbead.repositories.TicketSaleRepository;
import hu.elte.alkfejlbead.repositories.UserRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private TicketSaleRepository ticketSaleRepository;
    
    @Autowired
    private TicketSaleRepository ticketWantedRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping("")
    public ResponseEntity<User> post(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            ResponseEntity.notFound();
        }
        userRepository.delete(user.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> put(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()) {
            ResponseEntity.notFound();
        }
        
        User userToUpdate = foundUser.get();
        if(user.getUsername() != null) {
            userToUpdate.setUsername(user.getUsername());
        }
        if(user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        if(user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if(user.getRating() != null) {
            userToUpdate.setRating(user.getRating());
        }
        
        return ResponseEntity.ok(userRepository.save(userToUpdate));
    }

    @GetMapping("/{id}/selling")
    public ResponseEntity<Iterable<TicketSale>> ticketSale(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get().getSale());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/selling")
    public ResponseEntity<User> sellTicket(@PathVariable Integer id,
            @RequestParam(value = "ticketId") Integer ticketId) {
        Optional<User> oUser = userRepository.findById(id);
        Optional<TicketSale> oTicketSale = ticketSaleRepository.findById(ticketId);
        if (oUser.isPresent() && oTicketSale.isPresent()) {
            User user = oUser.get();
            user.getSale().add(oTicketSale.get());
            userRepository.save(user);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/selling")
    public ResponseEntity<TicketSale> deleteListing(@PathVariable Integer id,
            @RequestParam(value = "ticketId") Integer ticketId) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            TicketSale foundTicketSale = oUser.get().findTicketSaleById(ticketId);
            if (Objects.nonNull(foundTicketSale)) {
                User user = oUser.get();
                user.getSale().add(foundTicketSale);
                userRepository.save(user);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundTicketSale);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/buying")
    public ResponseEntity<Iterable<TicketWanted>> ticketWanted(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get().getWanted());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping("/{id}/buying")
    public ResponseEntity<User> wantTicket(@PathVariable Integer id,
            @RequestParam(value = "ticketId") Integer ticketId) {
        Optional<User> oUser = userRepository.findById(id);
        Optional<TicketWanted> oTicketWanted = ticketWantedRepository.findById(ticketId);
        if (oUser.isPresent() && oTicketWanted.isPresent()) {
            User user = oUser.get();
            user.getWanted().add(oTicketWanted.get());
            userRepository.save(user);  // have to trigger from the @JoinTable side
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
    
    @DeleteMapping("/{id}/buying")
    public ResponseEntity<TicketWanted> deleteTicketWanted(@PathVariable Integer id,
            @RequestParam(value = "ticketId") Integer ticketId) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            TicketWanted foundTicketWanted = oUser.get().findTicketWantedById(ticketId);
            if (Objects.nonNull(foundTicketWanted)) {
                User user = oUser.get();
                user.getWanted().add(foundTicketWanted);
                userRepository.save(user);  // have to trigger from the @JoinTable side
                return ResponseEntity.ok(foundTicketWanted);
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("login")
    public ResponseEntity login() {
      return ResponseEntity.ok(authenticatedUser.getUser());
    } 
}
