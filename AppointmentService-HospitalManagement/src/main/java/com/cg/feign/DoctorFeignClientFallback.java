package com.cg.feign;

import org.springframework.stereotype.Component;

import com.cg.dto.DoctorDTO;

@Component
public class DoctorFeignClientFallback implements DoctorFeignClient {

	@Override
	public DoctorDTO getDoctorById(int id) {
       DoctorDTO doctordto=new DoctorDTO();
       doctordto.setDid(1);
       doctordto.setDname("Anshu");
       doctordto.setQualification("MD");
       return doctordto;
	}
}
