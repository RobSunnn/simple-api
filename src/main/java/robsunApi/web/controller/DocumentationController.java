package robsunApi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentationController {

    @GetMapping("/docs")
    public ModelAndView docs() {
        return new ModelAndView("docs");
    }

    @GetMapping("/api-endpoints")
    public ModelAndView apiEndpoints() {
        return new ModelAndView("api-endpoints");
    }
}
