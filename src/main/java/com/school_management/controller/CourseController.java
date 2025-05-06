package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Course;
import com.school_management.service.CourseService;
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
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Operation(summary = "createCourse", description = "Course Created  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created  Successfully",
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

    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createCourse(@Valid @RequestBody final Course course) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.courseService.createCourse(course));
    }

    @Operation(summary = "findAllCourse", description = "Course Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Course_Retrieved) )
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

    @GetMapping("/course")
    public ResponseDTO getAllCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.courseService.getAllCourse());
    }

    @Operation(summary = "findCourseID", description = "Course Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Course_Retrieved_ID) )
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

    @GetMapping("/course/{id}")
    public ResponseDTO getCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.courseService.findById(id));
    }
    @Operation(summary = "deleteCourseID", description = "Course Deleted  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted successfully",
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

    @DeleteMapping("/course/{id}")
    public ResponseDTO deleteCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.courseService.deleteById(id));
    }
    @Operation(summary = "updateCourse", description = "Course Updated Successfully")
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
    @PutMapping("/course/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final Course course) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.courseService.updateById(course, id));
    }
}
