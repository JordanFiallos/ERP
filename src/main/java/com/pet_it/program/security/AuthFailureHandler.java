/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.security;

import com.pet_it.program.DAO.employeeDAO;
import com.pet_it.program.controller.UserManagingController;
import com.pet_it.program.domain.Employee;
import com.pet_it.program.services.employeeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ruben
 */
@Component
@Slf4j
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private employeeDAO employeeDao;
    
    @Autowired
    private employeeServiceImpl employeService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String user = request.getParameter("username");

        Employee empleadoIniciado = employeeDao.findByUsername(user);
        int intents = employeService.getIntents(empleadoIniciado);

        if (empleadoIniciado == null) {
            throw new UsernameNotFoundException(user);
        } else {
            if (intents > 0) {
                employeService.actualitzaUsuariIntents(empleadoIniciado);
                log.info("Error contrasenya incorrecta, intentos restantes: " + empleadoIniciado.getState());
                exception = new LockedException("contrasenya incorrecta, intentos restantes: " + empleadoIniciado.getState());
            }
            else if(intents==0){
                log.info("Error su usuario esta bloqueado");
                exception = new LockedException("su usuario esta bloqueado");
                
            }
        }
        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
