import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserAPI extends Endpoints {
    public static ValidatableResponse newUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .log().all()
                .post(Endpoints.API_CREATE)
                .then()
                .log().all();
    }
    public static ValidatableResponse loginUser(User user, String accessToken) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .body(user)
                .log().all()
                .post(Endpoints.API_LOGIN)
                .then()
                .log().all();
    }

    public static ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .auth().oauth2(accessToken)
                .log().all()
                .delete(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }

    public static ValidatableResponse updateUserWithAuth(User user, String accessToken) {

        return given()
                .spec(Endpoints.getBaseSpec())
                .header("Authorization", accessToken)
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }

    public static ValidatableResponse updateUserWithoutAuth(User user) {

        return given()
                .spec(Endpoints.getBaseSpec())
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
}
