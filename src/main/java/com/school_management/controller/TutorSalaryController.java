package com.school_management.controller;


import com.school_management.dto.ResponseDTO;
import com.school_management.entity.TutorSalary;
import com.school_management.service.TutorSalaryService;
import com.school_management.service.TutorService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TutorSalaryController {

    @Autowired
    private TutorSalaryService tutorSalaryService;
    @Autowired
    private TutorService tutorService;

    @Operation(summary = "createTutorSalary", description = "TutorSalary Created  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/tutorSalary")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createTutorSalary(@Valid @RequestBody final TutorSalary tutorSalary) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.tutorSalaryService.createTutorSalary(tutorSalary));
    }

    @Operation(summary = "findAllTutorSalary", description = "TutorSalary Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Tutor_Salary_Retrieved))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/tutorSalary")
    public ResponseDTO getAllTutorSalary() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorSalaryService.getAlltutorSalary());
    }

    @Operation(summary = "findTutorSalaryID", description = "TutorSalary Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Tutor_Salary_Retrieved_ID))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })

    @PreAuthorize("hasAnyAuthority('TEACHER','ADMIN')")
    @GetMapping("/tutorSalary/{id}")
    public ResponseDTO getTutorSalaryById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorSalaryService.findById(id));
    }

    @Operation(summary = "deleteTutorSalary", description = "TutorSalary Deleted  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/tutorSalary/{id}")
    public ResponseDTO deleteTutorSalaryById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.tutorSalaryService.deleteById(id));
    }

    @Operation(summary = "updateTutorSalary", description = "TutorSalary Updated  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/tutorSalary/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final TutorSalary tutorSalary) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.tutorSalaryService.updateById(tutorSalary, id));

    }

    @Operation(summary = "TutorSalaryGreaterThan", description = "TutorSalary Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/tutorSalary/{id}/tutorAmount")
    public ResponseDTO amount(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorSalaryService.getFeeAmount(id));
    }

    @Operation(summary = "patchTutorSalary", description = "TutorSalary Updated  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error Response ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.INTERNAL_SERVER_ERROR_RESPONSE)))
    })

@PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/tutorSalary/{id}")
    public ResponseDTO patchById(@PathVariable final int id,@Valid @RequestBody final TutorSalary tutorSalary){
        return new ResponseDTO(HttpStatus.OK.value(),Constant.UPDATE,this.tutorSalaryService.patchById(tutorSalary,id));
    }
}
