package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SignInRequestDTO;
import com.school_management.dto.SignUpRequestDTO;
import com.school_management.entity.User;
import com.school_management.service.UserService;
import com.school_management.util.Constant;
import com.school_management.util.Swagger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "UserAdmin", description = "UserAdmin Created  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully",
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

    @PostMapping("/auth/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO adminSignUp(@RequestBody final SignUpRequestDTO signUpRequestDTO) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.userService.adminCreate(signUpRequestDTO));
    }
    @Operation(summary = "UserTeacher", description = "UserTeacher Created  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully",
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

    @PostMapping("/auth/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO teacherSignUp(@RequestBody final SignUpRequestDTO signUpRequestDTO) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.userService.teacherCreate(signUpRequestDTO));
    }
    @Operation(summary = "UserStudent", description = "UserStudent Created  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created successfully",
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

    @PostMapping("/auth/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO studentSignUp(@RequestBody final SignUpRequestDTO signUpRequestDTO) {

        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.userService.studentCreate(signUpRequestDTO));
    }
    @Operation(summary = "UserToken", description = "User TokenGenerated  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "TokenGenerated successfully",
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
    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO login(@RequestBody final SignInRequestDTO signInRequestDTO) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.TOKEN, this.userService.signIn(signInRequestDTO));
    }
    @Operation(summary = "UserRefreshToken", description = "User RefreshTokenGenerated  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "RefreshTokenGenerated successfully",
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
    @PostMapping("/auth/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO token(@RequestParam final String refreshToken) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.TOKEN, this.userService.refreshToken(refreshToken));
    }
    @Operation(summary = "findUserAdminID", description = "UserAdmin Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.User_Retrieved_ID) )
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
    @GetMapping("/admin/{id}")
    public ResponseDTO getById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.getById(id));
    }
    @Operation(summary = "findUserAdmin", description = "UserAdmin Retrieved  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.User_Retrieved) )
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
    @GetMapping("/admin")
    public ResponseDTO findByAll() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.findByAllAndId());
    }
    @Operation(summary = "deleteUserAdmin", description = "UserAdmin Deleted  Successfully")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class),
                            examples = @ExampleObject(value = Swagger.User_Retrieved) )
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
    @DeleteMapping("/admin/{id}")
    public ResponseDTO deleteByAdminId(@PathVariable final int id ) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.userService.deleteById(id));
    }

}