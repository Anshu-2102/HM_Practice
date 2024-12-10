package com.cg.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.entity.Doctor;
import com.cg.exceptions.BadRequestException;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.service.IDoctorService;

import jakarta.validation.Valid;

@RequestMapping("/api")
@RestController
public class DoctorController {
	
	Logger log=org.slf4j.LoggerFactory.getLogger(DoctorController.class);
	
	 @RequestMapping("/orderss")
	 public List<Doctor> displayProduct()
	 { 	 log.info("Product list is going to dispaly 	");
		 List<Doctor> listProducts = doctorService.findAll();
		 log.info(" order list displayed");
		 log.debug("Missing order.png");
	
		 return listProducts;
	 }
	@Autowired
	IDoctorService doctorService;
	
	//http://localhost:8081/api/doctorlist
	@GetMapping("/doctorlist")     
	public List<Doctor> getDoctors()
	{
		return doctorService.findAll();
	}

	 //http://localhost:8081/api/doctor/
	@GetMapping("/doctor/{id}")
    public Optional<Doctor>findByDoctorId(@Valid @PathVariable int id) throws ResourceNotFoundException{
		Optional<Doctor> doctor = doctorService.findDoctorById(id);
	    //java8 lambda version
     	doctor.orElseThrow(() -> new ResourceNotFoundException("Doctor not found for this id :: " + id));
        return doctor;	
		
	}
	
    //http://localhost:8081/api/doctor
	@PostMapping("/doctor")  
	public Doctor createMyDoctor(@Validated @RequestBody Doctor doctor) throws BadRequestException {
		if (doctor.getName() == null ||doctor.getName()=="") {
            throw new BadRequestException("Doctor name is invalid.");
		}	
		return doctorService.createDoctor(doctor);
	}
	
	//http://localhost:8081/api/doctor/
	@DeleteMapping("/doctor/{id}")
	public boolean deleteDoctorById(@PathVariable int id) {
		return doctorService.deleteDoctorById(id);
	}
	
	//http://localhost:8081/api/doctor/
	@PutMapping("/doctor")
	public Doctor updateDoctor(@RequestBody Doctor doctor) {
		return doctorService.updateDoctorData(doctor);
	}
	
	//http://localhost:8081/api/doctor/
    @GetMapping("/doctors/{dname}")        
	public int findCountOfDoctors(@PathVariable String dname)
	{
		return doctorService.getCountByDoctorName(dname);
	}
	
    
    //validation 
    
	@Value("${error.doctorNotFound}")
    private String doctorNotFoundMessage;
    @Value("${error.doctorDeletionFailed}")
    private String doctorDeletionFailedMessage;
    @Value("${error.doctorAlreadyExists}")
    private String doctorAlreadyExistsMessage;
 
    @PostMapping("/doctorsValid")
    public Doctor doctorAlread(@RequestBody @Validated Doctor doctor) throws ResourceNotFoundException {
        Optional<Doctor> existingDoctor = doctorService.findDoctorById(doctor.getId());
       if (existingDoctor.isPresent()) {
            throw new ResourceNotFoundException(String.format(doctorAlreadyExistsMessage,doctor.getName()));
        }
        return doctorService.createDoctor(doctor);
    }	
    
    
    //JWT Security
    
    @GetMapping(path="/authDoctors",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Doctor> getDoctors(@RequestHeader("Authorization") String token)
    {
    	System.out.println("Hello");
    	if(doctorService.hasPermission(token))
    		return doctorService.findAll();
    	else
    		return null;
    }
	
}