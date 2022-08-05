package fx.fixgia.userms.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
    return "home page";

}

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile() {
    return "profile user";
}

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "profile admin";
    }

}
