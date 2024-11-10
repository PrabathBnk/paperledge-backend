package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.repository.UserRepository;

import edu.icet.service.UserService;
import edu.icet.util.GenerateIdUtil;
import edu.icet.util.PasswordEncodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ObjectMapper mapper;

    @Override
    public void saveUser(User user) {
        user.setId(GenerateIdUtil.generateId(("PL" + LocalDate.now().getYear()), 4, repository.findTopId().orElse("0000")));
        user.setCreatedDate(LocalDate.now());
        user.setPassword(PasswordEncodeUtil.encode(user.getPassword()));
        user.setType("GENERAL");
        user.setProfilePicture("default_user.png");
        user.setUsername(user.getUsername().toLowerCase());
        mapper.convertValue(repository.save(mapper.convertValue(user, UserEntity.class)), User.class);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email).map(entity -> mapper.convertValue(entity, User.class)).orElse(null);
    }

    @Override
    public User authenticateUser(User user) {
        Optional<UserEntity> userEntity = repository.findByEmail(user.getEmail());
        if(userEntity.isEmpty()) return null;

        return PasswordEncodeUtil.check(user.getPassword(), userEntity.get().getPassword()) ? mapper.convertValue(userEntity.get(), User.class): null;
    }

    @Override
    public boolean isUsernameAlreadyExits(String userName) {
        return repository.findByUsername(userName).isPresent();
    }
}
