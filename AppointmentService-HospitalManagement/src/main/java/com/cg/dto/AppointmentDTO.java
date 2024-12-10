package com.cg.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cg.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
  private int appid;
  private LocalDateTime time;
  private LocalDate date;
  private String issue;	
  private String status;
  private double fees;
  private DoctorDTO doctordetails;
  private PatientDTO patientdetails;
 
}




//http://localhost:8802/api/appointment
//http://localhost:8802/api/app
/*{
"did" : 154,
"pid" :  1  ,
"time" :  "2024-11-04T10:40:50",
"date" : "2024-11-04",
"issue" : "Malaria",
"status" : "done",
"fees" : 25879.0

}*/
//http://localhost:8802/api/app/1    -to display app with patient and doctor details