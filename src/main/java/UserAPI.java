import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

<<<<<<< HEAD
public class UserAPI {

    public Response newUser (User user){
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .post(Endpoints.API_CREATE);
    }
    public static Response loginUser(User user){
        return
                given()
                        .header("Content-type", "application/json")
                        .body(user)
                        .when().post(Endpoints.API_LOGIN);
    }

    public Response deleteUser (User user){
=======
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

    public static Response deleteUser(User user){
>>>>>>> b68a0a1 (change branch develop)
        return
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .delete(Endpoints.API_DELETE);
    }

<<<<<<< HEAD
    public ValidatableResponse getAllIngredients() {
        return given()
                .spec(Endpoints.getBaseSpec())
                .log().all()
                .get(Endpoints.INGREDIENTS_PATH)
                .then()
                .log().all();
    }

    public ValidatableResponse deleteUser(String accessToken) {
=======


    public static ValidatableResponse deleteUser(String accessToken) {
>>>>>>> b68a0a1 (change branch develop)
        return given()
                .spec(Endpoints.getBaseSpec())
                .auth().oauth2(accessToken)
                .log().all()
                .delete(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
<<<<<<< HEAD
    public ValidatableResponse updateUserWithAuth(User user, String accessToken) {
=======
    public static ValidatableResponse updateUserWithAuth(User user, String accessToken) {
>>>>>>> b68a0a1 (change branch develop)
        return given()
                .spec(Endpoints.getBaseSpec())
                .header("Authorization", accessToken)
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
<<<<<<< HEAD
    public ValidatableResponse updateUserWithoutAuth(User user) {
=======
    public static ValidatableResponse updateUserWithoutAuth(User user) {
>>>>>>> b68a0a1 (change branch develop)
        return given()
                .spec(Endpoints.getBaseSpec())
                .body(user)
                .log().all()
                .patch(Endpoints.USER_PATH + "user")
                .then()
                .log().all();
    }
<<<<<<< HEAD
=======

>>>>>>> b68a0a1 (change branch develop)
}
