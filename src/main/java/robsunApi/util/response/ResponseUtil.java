package robsunApi.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseUtil {
    private static final String FIELD = "field";
    private static final String DEFAULT_MESSAGE = "defaultMessage";

    public static ResponseEntity<?> genericSuccessResponse(String redirectUrl) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("redirectUrl", redirectUrl);
        return ResponseEntity.ok().body(response);
    }

    public static ResponseEntity<?> genericFailResponse(BindingResult bindingResult) {
        List<Map<String, String>> errors = bindingResult.getFieldErrors().stream().map(error -> {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("message", error.getDefaultMessage());
            return errorDetails;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);  // Add status code
        response.put("message", "Validation failed");
        response.put("errors", errors);  // Add the array of errors

        return ResponseEntity.badRequest().body(response);
    }

    public static ResponseEntity<Map<String, Object>> genericFailResponse(Map<String, String> errorMessages) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);

        List<Map<String, String>> errors = new ArrayList<>();
        for (Map.Entry<String, String> entry : errorMessages.entrySet()) {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put(FIELD, entry.getKey());
            errorDetails.put(DEFAULT_MESSAGE, entry.getValue());
            errors.add(errorDetails);
        }

        response.put("errors", errors);
        return ResponseEntity.badRequest().body(response);
    }

    public static ResponseEntity<ErrorResponse> errorResponse(HttpStatus status, String message) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}
