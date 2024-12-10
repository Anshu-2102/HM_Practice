package com.cg.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.dto.DoctorDTO;
@FeignClient(name="DoctorService-HospitalManagement", url="${DOCTOR_SERVICE:http://localhost:8800}", fallback=DoctorFeignClientFallback.class)
public interface DoctorFeignClient {
   @GetMapping("/api/doctor/{id}")
   public DoctorDTO getDoctorById(@PathVariable int id);
}

