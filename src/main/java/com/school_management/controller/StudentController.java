package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Student;
import com.school_management.service.StudentService;
import com.school_management.util.Constant;
import com.school_management.util.Swagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "createStudent", description = "Student Created  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class) )
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createStudent(@Valid @RequestBody final Student student) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.studentService.createStudent(student));
    }
    @Operation(summary = "findAllStudent", description = "Student Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Student_Retrieved) )
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })
    @GetMapping("/student")
    public ResponseDTO getAllStudent() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.getAllStudent());
    }
    @Operation(summary = "findStudentID", description = "Student Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Student_Retrieved_ID) )
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })
    @GetMapping("/student/{id}")
    public ResponseDTO getStudentById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.findById(id));
    }
    @Operation(summary = "deleteStudent", description = "Student Deleted  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class) )
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })
    @DeleteMapping("/student/{id}")
    public ResponseDTO deleteStudentById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.studentService.deleteById(id));
    }
    @Operation(summary = "updateStudent", description = "Student Updated  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })
    @PutMapping("/student/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final Student student) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.studentService.updateById(student, id));
    }

    @Operation(summary = "studentUserPage", description = "Student Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })
    @GetMapping("/student/user-page")
    public ResponseDTO getStudentCoursePage(@RequestParam(defaultValue = "0") final int pageIndex,
                                            @RequestParam(defaultValue = "5") final int pageSize,
                                            @RequestParam(defaultValue = "ASC") boolean sort,
                                            @RequestParam(defaultValue = "id") String field,@RequestParam(defaultValue = "") final String search )
                                                       {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentService.getSortedStudentPage(pageIndex, pageSize, field, sort,search));

    }


//    @GetMapping("/retrieve-user-page")
//    public ResponseDto retrieveUserPage(@RequestParam(defaultValue = "0") final int pageNumber,
//                                        @RequestParam(defaultValue = "10") final int pageSize,
//                                        @RequestParam(defaultValue = "true") final boolean order,
//                                        @RequestParam(defaultValue = "name") final String name,
//                                        @RequestParam(defaultValue = "") final String search) {
//        return this.studentService.getStudentBySortingAndSearching(pageNumber, pageSize, order, name, search);
//    }
//
//

    @Operation(summary = "StudentCourseDetails", description = "Student Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST) ) ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE) ) )
    })

    @GetMapping("/student/{id}/courses")
    public ResponseDTO getStudent(@PathVariable int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, studentService.getStudentWithCourses(id));
    }
}
