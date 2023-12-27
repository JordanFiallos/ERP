/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet_it.program.domain;
import java.util.Comparator;
/**
 *
 * @author Ricard
 */

public class CalendarComparator implements Comparator<Calendar> {
    @Override
    public int compare(Calendar programacionCalendario1, Calendar programacionCalendario2) {
        return programacionCalendario1.getScheduledDate().compareTo(programacionCalendario2.getScheduledDate());
    }
}
