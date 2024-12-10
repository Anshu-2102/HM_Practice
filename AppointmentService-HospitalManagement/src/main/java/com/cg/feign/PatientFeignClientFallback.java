package com.cg.feign;

import org.springframework.stereotype.Component;

import com.cg.dto.PatientDTO;

@Component
public class PatientFeignClientFallback implements PatientFeignClient {

	@Override
	public PatientDTO getPatientById(int id) {
		// TODO Auto-generated method stub
		return new PatientDTO();
	}

}
