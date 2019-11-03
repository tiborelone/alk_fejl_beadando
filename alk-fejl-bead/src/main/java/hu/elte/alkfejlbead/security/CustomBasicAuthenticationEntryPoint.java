package hu.elte.alkfejlbead.security;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hu.elte.issuetracker.security;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//
///**
// *
// * @author KeresztiKrisztián
// */
//public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
//
//    @Override
//    public void commence(final HttpServletRequest request,
//            final HttpServletResponse response,
//            final AuthenticationException authException) throws IOException, ServletException {
//        //Authentication failed, send error response.
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
//
//        PrintWriter writer = response.getWriter();
//        writer.println("HTTP Status 401 : " + authException.getMessage());
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        setRealmName("MY REALM");
//        super.afterPropertiesSet();
//    }
//}
