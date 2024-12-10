package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	@Query
	("select count(*) from Doctor d where d.name = :name")
	public int getCountByDoctorName(String name);
	
}
