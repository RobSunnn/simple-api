package robsunApi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "subscribers")
public class SubscriberEntity extends BaseEntity {

    private String email;

    private LocalDate subscriptionTime;

    private int subscriptionCount;

    public SubscriberEntity() {}

    public String getEmail() {
        return email;
    }

    public SubscriberEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getSubscriptionTime() {
        return subscriptionTime;
    }

    public SubscriberEntity setSubscriptionTime(LocalDate subscriptionTime) {
        this.subscriptionTime = subscriptionTime;
        return this;
    }

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public SubscriberEntity setSubscriptionCount(int subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
        return this;
    }
}
