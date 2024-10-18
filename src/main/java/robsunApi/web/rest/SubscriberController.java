package robsunApi.web.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import robsunApi.domain.model.binding.SubscriberBindingModel;
import robsunApi.service.SubscriberService;
import robsunApi.service.TokenService;
import robsunApi.util.response.ResponseUtil;

@RestController
public class SubscriberController {

    private final SubscriberService subscriberService;
    private final TokenService tokenService;

    public SubscriberController(SubscriberService subscriberService, TokenService tokenService) {
        this.subscriberService = subscriberService;
        this.tokenService = tokenService;
    }

    @PostMapping("/add-subscriber")
    public ResponseEntity<?> addSubscriber(
            @RequestHeader("x-api-token") String token,
            @Valid SubscriberBindingModel subscriberBindingModel,
            BindingResult bindingResult
    ) {
        if (!tokenService.isValidToken(token)) {
            return ResponseUtil.errorResponse(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        return subscriberService.addNewSubscriber(subscriberBindingModel, bindingResult);
    }

}
