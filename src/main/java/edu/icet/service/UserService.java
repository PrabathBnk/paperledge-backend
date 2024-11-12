package edu.icet.service;

import edu.icet.dto.User;

public interface UserService {
    void saveUser(User user);
    User getUserByEmail(String email);
    User authenticateUser(User user);
    boolean isUsernameAlreadyExits(String userName);
    User getUserById(String id);
    void updateAddress(User user);
}
