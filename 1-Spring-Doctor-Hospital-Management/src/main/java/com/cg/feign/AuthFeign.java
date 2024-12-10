package com.cg.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="auth-ws",url="${DOCTOR_SERVICE:http://localhost:9091}")
public interface AuthFeign {
	
	@GetMapping("/validate")
	public boolean getTokenValidity(@RequestHeader("Authorization") String token);
}
