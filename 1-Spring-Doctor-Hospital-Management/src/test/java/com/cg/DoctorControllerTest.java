package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.controller.DoctorController;
import com.cg.entity.Doctor;
import com.cg.exceptions.BadRequestException;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.service.IDoctorService;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {
	
	@Mock
	private IDoctorService doctorService;
	
	@InjectMocks
	private DoctorController doctorController;
	
	private Doctor doctor;
	
	@BeforeEach
	void setUp()
	{
		doctor=new Doctor(1,"anshu","MS");
	}
	
	@Test
    void testGetAllDoctors() {
        when(doctorService.findAll()).thenReturn(Arrays.asList(doctor));

        var doctors = doctorController.getDoctors();
        assertFalse(doctors.isEmpty());
        assertEquals(1, doctors.size());
        assertEquals("anshu", doctors.get(0).getName());
    }
	
	@Test
    void testGetByDoctorId() throws ResourceNotFoundException {
        when(doctorService.findDoctorById(1)).thenReturn(Optional.of(doctor));

        var result = doctorController.findByDoctorId(1);
        assertTrue(result.isPresent());
        assertEquals("anshu", result.get().getName());
    }

   @Test
    void testCreateMyDoctor() throws BadRequestException {
        when(doctorService.createDoctor(doctor)).thenReturn(doctor);

        Doctor createdDoctor = doctorController.createMyDoctor(doctor);
        assertNotNull(createdDoctor);
        assertEquals("anshu", createdDoctor.getName());
    }

   @Test
   void testDeleteMyDoctor() {
       // Mocking the deleteDoctor method to return true
       when(doctorService.deleteDoctorById(1)).thenReturn(true);

       // Call the controller method
       doctorController.deleteDoctorById(1);

       // Verify that deleteDoctor was called exactly once with ID 1
       verify(doctorService, times(1)).deleteDoctorById(1);
   }

   @Test
    void testUpdateMyProduct()  {
        when(doctorService.updateDoctorData(doctor)).thenReturn(doctor);

        Doctor updatedDoctor = doctorController.updateDoctor(doctor);
        assertNotNull(updatedDoctor);
        assertEquals("anshu", updatedDoctor.getName());
    }
	
}
