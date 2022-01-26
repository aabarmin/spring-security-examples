package dev.abarmin.spring.security.user.service.web;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Aleksandr Barmin
 */
@Controller
public class HomeController {
  @GetMapping("/")
  public ModelAndView modelAndView(final ModelAndView modelAndView,
                                   final Authentication authentication) {
    modelAndView.setViewName("index");

    modelAndView.addAllObjects(Map.of(
        "name", authentication.getName(),
        "authorities", authentication.getAuthorities()
    ));

    return modelAndView;
  }
}
