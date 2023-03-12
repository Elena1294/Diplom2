
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class OrderAPI {

    public ValidatableResponse orderCreate(Order order, String accessToken) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .header("Authorization", accessToken)
                .body(order)
                .log().all()
                .post(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
<<<<<<< HEAD
    public ValidatableResponse createOrderWithoutAuthorization(Order order) {
=======
    public static ValidatableResponse createOrderWithoutAuthorization(Order order) {
>>>>>>> b68a0a1 (change branch develop)
        return given()
                .spec(Endpoints.getBaseSpec())
                .body(order)
                .log().all()
                .post(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
<<<<<<< HEAD
    public ValidatableResponse getOrdersByAuth(String accessToken) {
=======
    public static ValidatableResponse getOrdersByAuth(String accessToken) {
>>>>>>> b68a0a1 (change branch develop)
        return given()
                .spec(Endpoints.getBaseSpec())
                .header("Authorization", accessToken)
                .log().all()
                .get(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
<<<<<<< HEAD
    public ValidatableResponse getOrdersWithoutAuth() {
=======
    public static ValidatableResponse getOrdersWithoutAuth() {
>>>>>>> b68a0a1 (change branch develop)
        return given()
                .spec(Endpoints.getBaseSpec())
                .log().all()
                .get(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
<<<<<<< HEAD
=======
    public ValidatableResponse getAllIngredients() {
        return given()
                .spec(Endpoints.getBaseSpec())
                .log().all()
                .get(Endpoints.INGREDIENTS_PATH)
                .then()
                .log().all();
    }
>>>>>>> b68a0a1 (change branch develop)
}
