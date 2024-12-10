package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.entity.Appointment;
import com.cg.feign.DoctorFeignClient;
import com.cg.feign.PatientFeignClient;
import com.cg.repository.AppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService {
	@Autowired
	AppointmentRepository apprepo;
	
	@Autowired
	DoctorFeignClient docfeign;
	
	@Autowired
	PatientFeignClient patfeign;
	
	@Override
	public List<Appointment> findAll() {
		return apprepo.findAll();
	}
	public Optional<Appointment> findAppointmentById(int id) {
		return apprepo.findById(id);
	}
	
	public Appointment CreateAppointment(Appointment a) {
		return apprepo.save(a);
	}
	
	public String DeleteAppointmentbyId(int id) {
		if (apprepo.existsById(id)) {
	        apprepo.deleteById(id);;
	         return "deleted";
	    } else {
	    	 return "id not found";
	    }
			
	}
	
	public Appointment UpdateData(Appointment a) {	
		return apprepo.save(a);
	}
	
	public AppointmentDTO getAppointmentwithPatientandDoctor(int appid) {
		Appointment app=apprepo.findById(appid)
			.orElseThrow(() -> new RuntimeException("Appointment not found with id " + appid));
		DoctorDTO doctor=docfeign.getDoctorById(app.getDid());
		PatientDTO patient=patfeign.getPatientById(app.getPid());
		AppointmentDTO appDTO=new AppointmentDTO();
		appDTO.setAppid(app.getAppid());
		appDTO.setTime(app.getTime());
		appDTO.setDate(app.getDate());
		appDTO.setIssue(app.getIssue());
		appDTO.setStatus(app.getStatus());
		appDTO.setFees(app.getFees());
		appDTO.setDoctordetails(doctor);
		appDTO.setPatientdetails(patient);
		return appDTO;
	}

}



