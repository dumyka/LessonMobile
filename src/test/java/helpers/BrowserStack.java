package helpers;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class BrowserStack {
  public static String getVideoUrl(String sessionId) {
    String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

    return given()
        .log().all()
        .filter(withCustomTemplates())
        .auth().basic("bsuser_A57awq", "DJsvsmkXq6Gsa8zqqLPz")
        .when()
        .get(url)
        .then()
        .log().all()
        .statusCode(200)
        .extract().path("automation_session.video_url");
  }
}
