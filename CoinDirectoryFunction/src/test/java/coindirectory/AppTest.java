package coindirectory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    String result = app.handleRequest("123", null);
    System.out.println(result);
//    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
//    String content = result.getBody();
//    assertNotNull(content);
//    assertTrue(content.contains("\"message\""));
//    assertTrue(content.contains("\"hello world\""));
//    assertTrue(content.contains("\"location\""));
  }
}
