package fx.fixgia.userms.dto;

import fx.fixgia.userms.model.GeneralConstraints;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.UUID;



public record UserEntityDto(
        UUID ID,
        @Length(max = GeneralConstraints.VARIABLE_LENGTH_30, message = "Max Length must be 30 characters")
        @Pattern(regexp = GeneralConstraints.PATTERN_ALPHABET_CHARACTERS,
                message = "must be alphanumeric, minimum 2 characters")
        @NotBlank(message = " Username is mandatory")
        String firstname,
        @Length(max = GeneralConstraints.VARIABLE_LENGTH_30, message = "Max Length must be 30 characters")
        @Pattern(regexp = GeneralConstraints.PATTERN_ALPHABET_CHARACTERS,
                message = "must be alphanumeric, minimum 2 characters")
        @NotBlank(message = " Username is mandatory")
        String lastname,
                            @Length(max = GeneralConstraints.VARIABLE_LENGTH_125, message = "Max Length must be 125 characters")
        @Pattern(regexp = GeneralConstraints.VARIABLE_EMAIL_PATTERN, message = "Must be a valid email address")
        @NotBlank(message = "email is mandatory") String email,
                            String status,
                            @Length(max = GeneralConstraints.VARIABLE_LENGTH_30, message = "Max Length must be 30 characters")
        @NotBlank(message = " password is mandatory")
        @Pattern(regexp = GeneralConstraints.PATTERN_PASSWORD, message = "The password must contain at least"
                + " 8 characters that includes"
                + " any one uppercase letter,"
                + " any one number and"
                + " any one symbol ( & ~ # @ = * - + € ^ $ £ µ % )")
        String password) implements Serializable {

}
