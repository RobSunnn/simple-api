package robsunApi.domain.model.binding;

public record UserRegisterBM(String username, String email, String password) {
    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }
}
