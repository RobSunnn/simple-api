package robsunApi.service;

import org.springframework.stereotype.Service;
import robsunApi.domain.entity.SubscriberEntity;
import robsunApi.domain.model.binding.SubscriberBindingModel;
import robsunApi.repository.SubscriberRepository;

import java.time.LocalDate;

@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;

    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    public boolean addSubscriber(SubscriberBindingModel subscriberBindingModel) {

        subscriberRepository.save(mapToSubscriber(subscriberBindingModel));
        return true;
    }

    private SubscriberEntity mapToSubscriber(SubscriberBindingModel subscriberBindingModel) {
        return new SubscriberEntity()
                .setEmail(subscriberBindingModel.subscriberEmail())
                .setSubscriptionTime(LocalDate.now())
                .setSubscriptionCount(1);
    }
}
