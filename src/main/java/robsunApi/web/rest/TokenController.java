//package robsunApi.web.rest;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import robsunApi.service.TokenService;
//
//import java.util.Map;
//
//@RestController
//public class TokenController {
//
//    private final TokenService tokenService;
//
//    public TokenController(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
//
//    @PostMapping("/store-token")
//    public ResponseEntity<?> storeToken(@RequestBody Map<String, String> body) {
//        String token = body.get("token");
//        tokenService.addToken(token); // Add token to the service
//        return ResponseEntity.ok("Token stored successfully.");
//    }
//
//    @PostMapping("/remove-token")
//    public ResponseEntity<?> removeToken(@RequestBody Map<String, String> body) {
//        String token = body.get("token");
//        tokenService.removeToken(token); // Remove token from the service
//        return ResponseEntity.ok("Token removed successfully.");
//    }
//
//}
