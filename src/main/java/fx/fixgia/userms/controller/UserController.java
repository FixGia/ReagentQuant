package fx.fixgia.userms.controller;

import fx.fixgia.userms.dto.UserEntityDto;
import fx.fixgia.userms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/update/{id}")
    public UserEntityDto updateUser(@PathVariable("id") UUID UserId, @Valid @RequestBody final UserEntityDto user) {


        log.debug("Controller UserMS: updateUser - Called");

        userService.updateUser(UserId, user);

        return user;


    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntityDto createUser(@Valid @RequestBody final UserEntityDto user) {

        log.debug("Controller: create User - Called");

        userService.createUser(user);

        return user;

    }

    @GetMapping("/deleteUser/{id}")
    public void deletePatient(@PathVariable("id") UUID userID){

        log.debug("Controller: deletePatient - called");

       userService.deleteUser(userID);

    }

    @GetMapping("/home")
    public String home() {

        return "hello";
    }

}
