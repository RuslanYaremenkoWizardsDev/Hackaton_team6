package bruh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticDto {

    private String login;
    private int games;
    private int draws;
    private int loses;
    private int wins;
    private int power;
}
