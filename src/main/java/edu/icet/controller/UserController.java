package edu.icet.controller;

import edu.icet.dto.User;
import edu.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Map<String,String>> persist(@RequestBody User user){
        try {
            service.saveUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("response", "Success");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e){
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/auth")
    public User authenticate(@RequestBody User user){
        return service.authenticateUser(user);
    }

    @GetMapping("/validate-username")
    public boolean validateUsername(@RequestParam("username") String userName){
        return service.isUsernameAlreadyExits(userName);
    }

    @GetMapping("/by-email")
    public User getUserByEmail(@RequestParam("email") String email){
        return service.getUserByEmail(email);
    }

    @PutMapping("/address")
    public void updateAddress(@RequestBody User user){
        service.updateAddress(user);
    }

    @PutMapping("/profile-picture")
    public ResponseEntity<Map<String, String>> updateProfilePicture(@RequestParam("id") String id, @RequestParam("image")MultipartFile image){
        Map<String, String> response = new HashMap<>();
        response.put("image", service.updateProfilePicture(id, image));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
