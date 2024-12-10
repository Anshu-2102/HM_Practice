package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.dto.AppointmentDTO;
import com.cg.entity.Appointment;

public interface IAppointmentService {
    List<Appointment> findAll();
    public Optional<Appointment> findAppointmentById(int id);
    public Appointment CreateAppointment(Appointment a);
    public String DeleteAppointmentbyId(int id);
    public Appointment UpdateData(Appointment a);
    public AppointmentDTO getAppointmentwithPatientandDoctor(int appid);

}
