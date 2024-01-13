/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.controller;

/**
 *
 * @author Jordan
 */
import com.pet_it.program.services.EmailService;
import jakarta.mail.MessagingException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/send-email", consumes = "multipart/form-data")
    public String sendEmail(@RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text,
            @RequestParam("attachment") MultipartFile attachment) throws MessagingException, IOException {
        emailService.sendEmail(to, subject, text, attachment);
        return "redirect:/Emails";
    }
}
