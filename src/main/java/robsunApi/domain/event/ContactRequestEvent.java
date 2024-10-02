package robsunApi.domain.event;

import org.springframework.context.ApplicationEvent;

public class ContactRequestEvent extends ApplicationEvent {
    private final String email;
    private final String name;

    public ContactRequestEvent(Object source, String email, String name) {
        super(source);
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
