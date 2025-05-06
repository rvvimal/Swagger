package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.School;
import com.school_management.service.SchoolService;
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
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @Operation(summary = "createSchool", description = "School Created  Successfully")
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

    @PostMapping("/school")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createSchool(@Valid @RequestBody final School school) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.schoolService.createSchool(school));
    }
    @Operation(summary = "findAllSchool", description = "School Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.School_Retrieved) )
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
    @GetMapping("/school")
    public ResponseDTO getAllSchool() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.schoolService.getAllSchool());
    }
    @Operation(summary = "findSchoolID", description = "School Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.School_Retrieved_ID) )
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
    @GetMapping("/school/{id}")
    public ResponseDTO getSchoolById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.schoolService.findById(id));
    }

    @Operation(summary = "deleteSchool", description = "School Deleted  Successfully")
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

    @DeleteMapping("/school/{id}")
    public ResponseDTO deleteSchoolById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.schoolService.deleteById(id));
    }

    @Operation(summary = "updateSchool", description = "School Updated  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.School_Retrieved) )
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

    @PutMapping("/school/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final School school) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.schoolService.updateById(school, id));
    }

}
