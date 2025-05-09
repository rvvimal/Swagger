package com.school_management.service;


import com.school_management.dto.TutorSalaryDTO;
import com.school_management.entity.Tutor;
import com.school_management.entity.TutorSalary;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorRepository;
import com.school_management.repository.TutorSalaryRepository;
import com.school_management.util.Constant;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorSalaryService {
    @Autowired
    private TutorSalaryRepository tutorSalaryRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public TutorSalary createTutorSalary(final TutorSalary tutorSalary) {
        int tutorId = tutorSalary.getTutor().getId();
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(() -> new SchoolNotFoundException("Tutor with ID " + tutorId + " not found"));
        tutorSalary.setTutor(tutor);
        return this.tutorSalaryRepository.save(tutorSalary);
    }

    public List<TutorSalary> getAlltutorSalary() {
        return this.tutorSalaryRepository.findAll();
    }

    public TutorSalaryDTO findById(final int id) {
        final TutorSalary tutor = this.tutorSalaryRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(tutor.getTutor().getEmail()) ||
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ADMIN"))
        ) {
//            TutorSalaryDTO dto = new TutorSalaryDTO();
            TutorSalaryDTO dto = modelMapper.map(tutor, TutorSalaryDTO.class);
            dto.setTutorId(tutor.getTutor().getId());
            dto.setTutorName(tutor.getTutor().getName());
            dto.setSchoolId(tutor.getTutor().getSchool().getId());
            dto.setSchoolName(tutor.getTutor().getSchool().getName());

            return dto;
        } else {
            throw new RuntimeException("User Does not have the access");
        }

    }


    public String deleteById(final int id) {
        if (this.tutorSalaryRepository.existsById(id)) {
            this.tutorSalaryRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }

    @Transactional
    public TutorSalary updateById(final TutorSalary tutorSalary, final int id) {
        final TutorSalary tutor = this.tutorSalaryRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (tutorSalary.getMonth() != null) {
            tutor.setMonth(tutorSalary.getMonth());
        }

        if (tutorSalary.getAmount() != 0) {
            tutor.setAmount(tutorSalary.getAmount());
        }
        if (tutorSalary.getPaid() != null) {
            tutor.setPaid(tutorSalary.getPaid());
        }
        return this.tutorSalaryRepository.save(tutor);
    }


    public List<TutorSalaryDTO> getFeeAmount(int id) {
        List<TutorSalaryDTO> tutorSalaryDTOS = new ArrayList<>();
        List<TutorSalary> tutorSalaries = this.tutorSalaryRepository.findByTutor_School_Id(id);
        for (TutorSalary tutorSalary : tutorSalaries) {
            if (tutorSalary.getAmount() > 2000) {
                TutorSalaryDTO dto = new TutorSalaryDTO();
                dto.setTutorId(tutorSalary.getTutor().getId());
                dto.setTutorName(tutorSalary.getTutor().getName());
                dto.setSchoolId(tutorSalary.getTutor().getSchool().getId());
                dto.setAmount(tutorSalary.getAmount());
                tutorSalaryDTOS.add(dto);
            }
        }
        return tutorSalaryDTOS;
    }

    @Transactional
    public TutorSalary patchById(final TutorSalary tutorSalary, final int id) {
        final TutorSalary tutor = this.tutorSalaryRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (tutorSalary.getMonth() != null) {
            tutor.setMonth(tutorSalary.getMonth());
        }

        if (tutorSalary.getAmount() != 0) {
            tutor.setAmount(tutorSalary.getAmount());
        }
        if (tutorSalary.getPaid() != null) {
            tutor.setPaid(tutorSalary.getPaid());
        }
        return this.tutorSalaryRepository.save(tutor);
    }

}
