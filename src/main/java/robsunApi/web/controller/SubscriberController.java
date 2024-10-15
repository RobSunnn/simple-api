package robsunApi.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import robsunApi.domain.model.binding.SubscriberBindingModel;
import robsunApi.service.SubscriberService;

@Controller
public class SubscriberController {

    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @PostMapping("/add-subscriber")
    public ResponseEntity<?> addSubscriber(@RequestBody SubscriberBindingModel subscriberBindingModel) {
        subscriberService.addSubscriber(subscriberBindingModel);
        return ResponseEntity.ok("Subscriber added successfully");
    }

}
