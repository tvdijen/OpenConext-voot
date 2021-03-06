package voot.oidc;

import org.junit.Test;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import voot.oauth.AbstractSchacHomeAwareUserAuthenticationConverterTest;
import voot.oauth.ClientCredentialsAuthentication;
import voot.oauth.SchacHomeAuthentication;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OidcSchacHomeAwareUserAuthenticationConverterTest extends AbstractSchacHomeAwareUserAuthenticationConverterTest{

  private OidcSchacHomeAwareUserAuthenticationConverter subject = new OidcSchacHomeAwareUserAuthenticationConverter();

  @Test
  public void testExtractAuthenticationUserAuthentication() throws Exception {
    SchacHomeAuthentication authentication = (SchacHomeAuthentication) subject.extractAuthentication(readJson("json/oidc/introspect.success.json"));
    assertTrue(authentication.isAuthenticated());
    assertEquals("urn:collab:person:example.com:admin", authentication.getName());
    assertEquals("surfteams.nl", authentication.getSchacHomeAuthentication());
  }

  @Test
  public void testExtractAuthenticationClientCredentialsAuthentication() throws Exception {
    ClientCredentialsAuthentication authentication = (ClientCredentialsAuthentication) subject.extractAuthentication(readJson("json/oidc/introspect.client_credentials.json"));
    assertTrue(authentication.isAuthenticated());
    assertEquals("https@//oidc.localhost.surfconext.nl", authentication.getName());
  }

  @Test(expected = InvalidClientException.class)
  public void testInvalidClient() {
    subject.extractAuthentication(new HashMap<>());
  }

}
