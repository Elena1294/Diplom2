
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
    public ValidatableResponse createOrderWithoutAuthorization(Order order) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .body(order)
                .log().all()
                .post(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
    public ValidatableResponse getOrdersByAuth(String accessToken) {
        return given()
                .spec(Endpoints.getBaseSpec())
                .header("Authorization", accessToken)
                .log().all()
                .get(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
    public ValidatableResponse getOrdersWithoutAuth() {
        return given()
                .spec(Endpoints.getBaseSpec())
                .log().all()
                .get(Endpoints.ORDER_PATH)
                .then()
                .log().all();
    }
}
