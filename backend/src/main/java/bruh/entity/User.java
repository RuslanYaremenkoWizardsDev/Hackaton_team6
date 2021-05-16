package bruh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import static bruh.util.constants.EntityMessages.USER_CREDENTIALS;
import static bruh.util.constants.ValidationMessages.FIELD_CANNOT_BE_NULL;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = USER_CREDENTIALS, uniqueConstraints = {@UniqueConstraint(columnNames = {"login"}, name = "uk_login")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String login;

    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String password;

    @NotNull(message = FIELD_CANNOT_BE_NULL)
    private String role;

    private int games;

    private int draws;

    private int loses;

    private int power;

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
