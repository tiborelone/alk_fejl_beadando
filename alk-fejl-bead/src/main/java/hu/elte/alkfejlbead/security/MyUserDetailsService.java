package hu.elte.alkfejlbead.security;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hu.elte.issuetracker.security;
//
//import hu.elte.issuetracker.entities.Student;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import hu.elte.issuetracker.repositories.StudentRepository;
//
///**
// *
// * @author KeresztiKrisztián
// */
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private StudentRepository userRepository;
//
//    @Autowired
//    private AuthenticatedUser authenticatedUser;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        /*Optional<Student> oUser = userRepository.findByUsername(username);
//        if (!oUser.isPresent()) {
//            throw new UsernameNotFoundException(username);
//        }
//        Student user = oUser.get();
//        authenticatedUser.setUser(user);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);*/
//        return null;
//    }
//}
