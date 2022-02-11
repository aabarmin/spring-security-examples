package dev.abarmin.spring.security.oauth.aws.web;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
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
        "authorities", authentication.getAuthorities(),
        "attributes", getAttributes(authentication)
    ));

    return modelAndView;
  }

  private Map<String, Object> getAttributes(final Authentication authentication) {
    final Object principalObject = authentication.getPrincipal();
    if (!(principalObject instanceof OAuth2User)) {
      return Map.of();
    }
    final OAuth2User principal = OAuth2User.class.cast(principalObject);
    return principal.getAttributes();
  }
}
