/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.security;

import com.pet_it.program.DAO.employeeDAO;
import com.pet_it.program.domain.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ruben
 */
@Component
@Slf4j
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    @Autowired
    private employeeDAO employeeDao;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String user = request.getParameter("username");
        log.info("El usuario a accedido");
        
        Employee empleat = employeeDao.findByUsername(user);
        if(empleat.getState()== 0){
            throw new RuntimeException("el usuario esta bloqueado contacte con un administrador");
        }
        else if(empleat.getState()<3){
            empleat.setState(3);
            employeeDao.save(empleat);
            log.info("Intentos reestablecidos");
        }
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
}
