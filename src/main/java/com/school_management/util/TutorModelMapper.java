//package com.school_management.util;
//
//import com.school_management.dto.TutorSalaryDTO;
//import com.school_management.entity.TutorSalary;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TutorModelMapper {
//    public TutorSalaryDTO dto(TutorSalary tutorSalary){
//        TutorSalaryDTO dto=new TutorSalaryDTO();
//        dto.setTutorId(tutorSalary.getTutor().getId());
//        dto.setTutorName(tutorSalary.getTutor().getName());
//        dto.setAmount(tutorSalary.getAmount());
//        dto.setSchoolId(tutorSalary.getTutor().getSchool().getId());
//        dto.setSchoolName(tutorSalary.getTutor().getSchool().getName());
//        return dto;
//    }
//}
