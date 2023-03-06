import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.*;
public class OrderTest {

    private String accessToken;
    UserAPI UserAPI = new UserAPI();
    OrderAPI OrderAPI = new OrderAPI();
    User userValid = new User(TestDataUser.CREATED_LOGIN, TestDataUser.CREATED_PASSWORD);
    Order order = new Order();

    @Before
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE;
    }
    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    @Description("Приходит список заказов, код ответа 200")
    public void getOrdersWithAuthTest() {

        Response response = UserAPI.loginUser(userValid);
        accessToken = response.then().extract().path("accessToken");
        ValidatableResponse validatableResponse = OrderAPI.getOrdersByAuth(accessToken);
        int statusCode = validatableResponse.extract().statusCode();
        boolean isGet = validatableResponse.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isGet);
    }
    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    @Description("Код ошибки 401")
    public void getOrdersWithoutAuthTest(){
        OrderAPI.createOrderWithoutAuthorization(order);
        ValidatableResponse response = OrderAPI.getOrdersWithoutAuth();
        int statusCode = response.extract().statusCode();
        boolean isGet = response.extract().path("success");
        assertEquals(SC_UNAUTHORIZED, statusCode);
        assertFalse(isGet);

    }
}
