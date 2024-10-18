package robsunApi.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import robsunApi.domain.entity.SubscriberEntity;
import robsunApi.domain.model.binding.SubscriberBindingModel;
import robsunApi.repository.SubscriberRepository;

import java.time.LocalDate;
import java.util.Optional;

import static robsunApi.util.response.ResponseUtil.genericFailResponse;
import static robsunApi.util.response.ResponseUtil.genericSuccessResponse;

@Service
public class SubscriberService {

    private final SubscriberRepository subscriberRepository;
    private final HttpServletRequest request;

    public SubscriberService(SubscriberRepository subscriberRepository, HttpServletRequest request) {
        this.subscriberRepository = subscriberRepository;
        this.request = request;
    }


    public ResponseEntity<?> addNewSubscriber(SubscriberBindingModel subscriberBindingModel, BindingResult bindingResult) {
        final String REDIRECT_URL = request.getHeader("referer");

        if (bindingResult.hasErrors()) {
            return genericFailResponse(bindingResult);
        }

        try {
            Optional<SubscriberEntity> checkSubscriber = subscriberRepository.findByEmail(subscriberBindingModel.subscriberEmail());

            if (checkSubscriber.isPresent()) {
                SubscriberEntity subscriber = checkSubscriber.get();
                subscriber.setSubscriptionCount(subscriber.getSubscriptionCount() + 1);
                subscriberRepository.save(subscriber);

//                if (subscriber.getCounterOfSubscriptions() == 2) {
//                    applicationEventPublisher.publishEvent(new SendBonusVoucherEvent(
//                            "SubscriberService", subscriber.getEmail())
//                    );
//                }
                return genericSuccessResponse(REDIRECT_URL);
            }

            subscriberRepository.save(mapAsSubscriber(subscriberBindingModel));
            return genericSuccessResponse(REDIRECT_URL);
        } catch (Exception e) {
            return genericFailResponse(bindingResult);
        }
    }

    private SubscriberEntity mapAsSubscriber(SubscriberBindingModel subscriberBindingModel) {
        return new SubscriberEntity()
                .setEmail(subscriberBindingModel.subscriberEmail())
                .setSubscriptionTime(LocalDate.now())
                .setSubscriptionCount(1);
    }
}
