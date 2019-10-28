package io.spring.securityjpa.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

  private String header;

  public ApiKeyAuthFilter(String header) {
    this.header = header;
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return request.getHeader(header);
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }
}
