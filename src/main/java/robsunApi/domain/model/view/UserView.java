package robsunApi.domain.model.view;

public record UserView(String username,
                       String email,
                       String apiToken) {

    @Override
    public String username() {
        return username;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String apiToken() {
        return apiToken;
    }
}
