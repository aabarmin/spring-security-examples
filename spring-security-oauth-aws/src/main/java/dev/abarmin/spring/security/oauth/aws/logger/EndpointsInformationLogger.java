package dev.abarmin.spring.security.oauth.aws.logger;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Aleksandr Barmin
 */
@Slf4j
@Component
public class EndpointsInformationLogger implements ApplicationListener<ContextRefreshedEvent> {
  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    final ApplicationContext context = event.getApplicationContext();
    final RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
    final Map<RequestMappingInfo, HandlerMethod> handlerMap = mapping.getHandlerMethods();
    handlerMap.forEach((key, value) -> log.info("{} {}", key, value));
  }
}
