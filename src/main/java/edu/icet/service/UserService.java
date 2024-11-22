package edu.icet.service;

import edu.icet.dto.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void saveUser(User user);
    User getUserByEmail(String email);
    User authenticateUser(User user);
    boolean isUsernameAlreadyExits(String userName);
    User getUserById(String id);
    void updateAddress(User user);
    String updateProfilePicture(String id, MultipartFile image);
}
