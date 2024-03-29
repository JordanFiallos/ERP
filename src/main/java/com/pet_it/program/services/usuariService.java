/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.services;

import com.pet_it.program.DAO.employeeDAO;
import com.pet_it.program.domain.Role;
import com.pet_it.program.domain.Employee;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ruben
 */
@Service ("userDetails")
@Slf4j
public class usuariService implements UserDetailsService{
    @Autowired
    private employeeDAO personDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       Employee persona = personDao.findByUsername(username);
        
       if(persona == null){
           throw new UsernameNotFoundException(username);
       }
        if(persona.getState()==0){
            log.info("El usuario ha excedido los intentos");
            throw new RuntimeException("Tu usuario se ha quedado sin intentos, contacte con un administrador para poder acceder");
        }
       var rols = new ArrayList<GrantedAuthority>();
       
        for (Role rol: persona.getRols()) {
            rols.add(new SimpleGrantedAuthority(rol.getNom()));
        }
        log.info(persona.getUsername());
        log.info(persona.getPassword());
        log.info(rols.get(0).getAuthority());
        
        return new User(persona.getUsername(), persona.getPassword(), rols);
        
    }
    
    
    
}
