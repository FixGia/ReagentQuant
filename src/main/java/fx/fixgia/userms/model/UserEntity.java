package fx.fixgia.userms.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;

    @Column
    @Pattern(message = "must be alphanumeric, minimum 2 characters", regexp = "[-A-Za-z]+${2}")
    @NotBlank(message = "email is mandatory")
    private String status;
    @Length(message = "Max Length must be 30 characters", max = 30)
    @Pattern(message = "must be alphanumeric, minimum 2 characters", regexp = "[-A-Za-z]+${2}")
    @NotBlank(message = " Username is mandatory")
    private String firstname;
    @Length(message = "Max Length must be 30 characters", max = 30)
    @Pattern(message = "must be alphanumeric, minimum 2 characters", regexp = "[-A-Za-z]+${2}")
    @NotBlank(message = " Username is mandatory")
    private String lastname;

    @Column
    @Length(max = GeneralConstraints.VARIABLE_LENGTH_125, message = "Max Length must be 125 characters")
    @Pattern(regexp = GeneralConstraints.VARIABLE_EMAIL_PATTERN, message = "Must be a valid email address")
    @NotBlank(message = "email is mandatory")
    private String email;



    @Length(max=GeneralConstraints.VARIABLE_LENGTH_30, message = "Max Length must be 30 characters")
    @NotBlank(message = " password is mandatory")
    @Pattern(regexp = GeneralConstraints.PATTERN_PASSWORD, message = "The password must contain at least"
                    + " 8 characters that includes"
                    + " any one uppercase letter,"
                    + " any one number and"
                    + " any one symbol ( & ~ # @ = * - + € ^ $ £ µ % )")
    private String password;

    private boolean isExpired;

    private boolean isLocked;

    private boolean isEnabled;

    private String Role;


}
