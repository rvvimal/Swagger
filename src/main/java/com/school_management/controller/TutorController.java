package com.school_management.controller;


import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Tutor;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @Operation(summary = "createTutor", description = "Tutor Created  Successfully")
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

    @PostMapping("/tutor")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createTutor(@Valid @RequestBody final Tutor tutor) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.tutorService.createTutor(tutor));
    }
    @Operation(summary = "findAllTutor", description = "Tutor Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Tutor_Retrieved) )
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
    @GetMapping("/tutor")
    public ResponseDTO getAllTutor() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorService.getAlltutor());
    }
    @Operation(summary = "findTutorID", description = "Tutor Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.Tutor_Retrieved_ID) )
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
    @GetMapping("/tutor/{id}")
    public ResponseDTO getTutorById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorService.findById(id));
    }

    @Operation(summary = "deleteTutor", description = "Tutor Deleted  Successfully")
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

    @DeleteMapping("/tutor/{id}")
    public ResponseDTO deleteTutorById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.tutorService.deleteById(id));
    }

    @Operation(summary = "updateTutor", description = "Tutor Updated  Successfully")
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

    @PutMapping("/tutor/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final Tutor tutor) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.tutorService.updateById(tutor, id));

    }
}

