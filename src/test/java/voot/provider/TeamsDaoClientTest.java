package voot.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import voot.VootServiceApplication;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VootServiceApplication.class)
@WebIntegrationTest(value = "flyway.enabled=true")
public class TeamsDaoClientTest {

  private TeamsDaoClient subject;

  @Autowired
  @Qualifier("teamsDataSource")
  private DataSource dataSource;

  @Before
  public void setUp() throws Exception {
    subject = new TeamsDaoClient(dataSource);
  }

  @Test
  public void testLinkedGrouperGroupIds() throws Exception {
    List<String> ids = subject.linkedLocalGrouperGroupIds("urn:collab:group:example.org:name1", "urn:collab:group:example.org:name3", "bogus");
    assertEquals(Arrays.asList("nl:surfnet:diensten:bazen", "nl:surfnet:diensten:test123"), ids);
  }
}