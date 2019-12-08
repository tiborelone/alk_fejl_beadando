/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.alkfejlbead.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private Integer rating;
    
    @Column(nullable = false)
    private boolean enabled;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public enum Role {
        ROLE_GUEST, ROLE_USER, ROLE_ADMIN
    }
    
    @JsonManagedReference
    @OneToMany
    private List<TicketSale> sale;

    @JsonManagedReference
    @OneToMany
    private List<TicketWanted> wanted;
    
    public TicketSale findTicketSaleById(Integer saleId) {
        for(TicketSale sale : sale) {
            if(sale.getId().equals(saleId)) {
                return sale;
            }
        }
        return null;
    }

    public TicketWanted findTicketWantedById(Integer wantedId) {
        for(TicketWanted wanted : wanted) {
            if(wanted.getId().equals(wantedId)) {
                return wanted;
            }
        }
        return null;
    }
    
}
