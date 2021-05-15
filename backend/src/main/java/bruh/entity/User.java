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

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
