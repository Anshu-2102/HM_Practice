package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.entity.Doctor;

public interface IDoctorService {
    List<Doctor> findAll();
    public Optional<Doctor> findDoctorById(int id);
    public Doctor createDoctor(Doctor doctor);
    public boolean deleteDoctorById(int id);
    public Doctor updateDoctorData(Doctor doctor);
	int getCountByDoctorName(String name);   
	
	public boolean hasPermission(String token);
	
}
