package bruh.util.constants;

public class RegularExpressions {
    public static final String LOGIN_REG_EXP = "[a-zA-Z0-9](_(?!(\\\\.|_))|\\\\.(?!(_|\\\\.))|[a-zA-Z0-9]){1,25}[a-zA-Z0-9]$";
    public static final String PASSWORD_REG_EXP = "[0-9a-zA-Z]{6,}";
}
