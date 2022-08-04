package fx.fixgia.userms.service;

import fx.fixgia.userms.dto.UserEntityDto;
import fx.fixgia.userms.model.UserEntity;
import fx.fixgia.userms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserEntityDto user) {

        UserEntity getUser = userRepository.findUserEntityByEmail(user.email());

        if (getUser == null) {

            UserEntity newUser = new UserEntity();
            newUser.setEmail(user.email());
            newUser.setFirstname(user.firstname());
            newUser.setLastname(user.lastname());
            newUser.setPassword(user.password());
            newUser.setStatus(user.status());
            userRepository.save(newUser);
            log.info("Service : create new User {} - OK !", newUser.getEmail());
        }

        else log.error("Service : create new User {} - FAIL ! This user already exist", getUser.getEmail());

    }

    @Override
    public void deleteUser(UUID userID) {

        Optional<UserEntity> getUser = userRepository.findById(userID);

        if(getUser.isPresent()) {
            userRepository.deleteById(userID);
            log.info("Service : delete User{} with UUID {} - OK!", getUser.get().getEmail(), userID);
        }

        else log.error ("Service : delete User with UUID {} - FAIL! This user doesn't present in DB", userID);

    }

    @Override
    public void updateUser(UUID userID, UserEntityDto user) {

        Optional<UserEntity> userToUpdate = userRepository.findById(userID);

        if(userToUpdate.isPresent()) {

            userToUpdate.get().setFirstname(user.firstname());
            userToUpdate.get().setLastname(user.lastname());
            userToUpdate.get().setStatus(user.status());
            userRepository.save(userToUpdate.get());
            log.info("Service : update User{} - OK", user.email());
        }
        else log.error("Service : update User{} - FAIL", user.email());

    }

    @Override
    public UserEntity getUserByEmail(UserEntityDto user) {

        return userRepository.findUserEntityByEmail(user.email());

    }
        @Override
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll();

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findUserEntityByEmail(email);

        if(user == null) {
            log.error("Service : loadUserByUsername User {} - FAIL", email);
            throw new UsernameNotFoundException("User not exist in DB");
        } else {
            log.info("Service : loadUserByUsername User {} - FAIL", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new User(user.getEmail(),user.getPassword(),authorities);
        }

    }
}
