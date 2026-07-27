package com.banking.paypulse_banking.controller;


import com.banking.paypulse_banking.dto.UsersDto;
import com.banking.paypulse_banking.dto.request.UsersUpdateDto;
import com.banking.paypulse_banking.service.UserService;
import com.banking.paypulse_banking.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    //user side
    @PostMapping(path = "/saveUser")
    public ResponseEntity<StandardResponse> saveUser(@RequestBody UsersDto usersDto) {

        String message = userService.saveUser(usersDto);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "successful", message), HttpStatus.CREATED
        );

    }


    //user side -update account
    @PutMapping
    public ResponseEntity<StandardResponse> updateUser(@RequestBody UsersUpdateDto usersUpdateDto) {

        String message=userService.UpdateUserByNic(usersUpdateDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Update successful", message), HttpStatus.OK
        );
    }

}
