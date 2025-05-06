package com.school_management.service;

import com.school_management.dto.PaginationResponse;
import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.School;
import com.school_management.entity.Student;
import com.school_management.exception.SchoolNotFoundException;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.SchoolRepository;
import com.school_management.repository.StudentRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Transactional
    public Student createStudent(final Student student) {
        int schoolId = student.getSchool().getId();
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new SchoolNotFoundException("School with ID " + schoolId + " not found"));
        student.setSchool(school);
        return studentRepository.save(student);
}

    public List<Student> getAllStudent() {
        return this.studentRepository.findAll();
    }

    public Student findById(final int id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

    }

    public String deleteById(final int id) {
        if (this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }


    @Transactional
    public Student updateById(final Student student, final int id) {
        final Student students = this.studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));
        if (student.getFirstName() != null) {
            students.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            students.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            students.setEmail(student.getEmail());
        }
        if (student.getEnrollmentDate() != null) {
            students.setEnrollmentDate(student.getEnrollmentDate());
        }
        if (student.getContactNumber() != 0) {
            students.setContactNumber(student.getContactNumber());
        }

        return this.studentRepository.save(students);
    }

    public PaginationResponse getSortedStudentPage(final int pageIndex, final int pageSize, final String field, final boolean sort,final String search) {
        if (pageIndex < 0 || pageSize <= 0) {
            throw new RuntimeException(Constant.NOT_FOUND);
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by(sort ? Sort.Direction.ASC : Sort.Direction.DESC, field));
        Page<Student> studentPage = this.studentRepository.findStudentByName(search,pageable);
        return new PaginationResponse(
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.getSize(),
                studentPage.getContent()
        );
    }

//    public ResponseDto getStudentBySortingAndSearching(final int pageNumber, final int pageSize, final boolean order, final String name, final String search) {
//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order ? Sort.Direction.ASC : Sort.Direction.DESC, name));
//        Page<Student> studentSearch = this.studentRepository.findStudentByNameOrCity(search,pageable);
//        return ResponseDto.builder()
//                .message(Constants.RETRIEVED)
//                .data(studentSearch)
//                .statusCode(HttpStatus.OK.value())
//                .build();
//    }
//




    public List<StudentDetailsDTO> getStudentWithCourses(int id) {
        return studentRepository.findStudentWithCourses(id);
    }
}
