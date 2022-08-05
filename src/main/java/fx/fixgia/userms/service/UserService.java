package fx.fixgia.userms.service;

import fx.fixgia.userms.dto.UserEntityDto;
import fx.fixgia.userms.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService {


    void createUser(UserEntityDto user);

    void deleteUser(UUID userID);

    void updateUser(UUID userID, UserEntityDto user);

    UserEntity getUserByEmail(UserEntityDto user);

    List<UserEntity> getAllUsers();



}
