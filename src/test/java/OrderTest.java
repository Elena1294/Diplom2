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

<<<<<<< HEAD
    private String accessToken;
    UserAPI UserAPI = new UserAPI();
    OrderAPI OrderAPI = new OrderAPI();
    User userValid = new User(TestDataUser.CREATED_LOGIN, TestDataUser.CREATED_PASSWORD);
    Order order = new Order();

    @Before
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE;
=======
    private UserAPI userAPI;
    private OrderAPI orderClient;
    private User user;
    private String accessToken;
    private ValidatableResponse response;
    private Order order;

    @Before
    public void setUp() {
        user = user.getRandomUser();
        userAPI = new UserAPI();
        orderClient = new OrderAPI();
        order = new Order();
>>>>>>> b68a0a1 (change branch develop)
    }
    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    @Description("Приходит список заказов, код ответа 200")
    public void getOrdersWithAuthTest() {
<<<<<<< HEAD

        Response response = UserAPI.loginUser(userValid);
        accessToken = response.then().extract().path("accessToken");
        ValidatableResponse validatableResponse = OrderAPI.getOrdersByAuth(accessToken);
        int statusCode = validatableResponse.extract().statusCode();
        boolean isGet = validatableResponse.extract().path("success");
=======
        response = userAPI.newUser(user);
        accessToken = response.extract().path("accessToken");
        UserAPI.loginUser(user, accessToken);
        orderClient.orderCreate(order, accessToken);
        response = orderClient.getOrdersByAuth(accessToken);
        int statusCode = response.extract().statusCode();
        boolean isGet = response.extract().path("success");
>>>>>>> b68a0a1 (change branch develop)
        assertEquals(SC_OK, statusCode);
        assertTrue(isGet);
    }
    @Test
    @DisplayName("Получение заказов неавторизованного пользователя")
    @Description("Код ошибки 401")
    public void getOrdersWithoutAuthTest(){
<<<<<<< HEAD
        OrderAPI.createOrderWithoutAuthorization(order);
        ValidatableResponse response = OrderAPI.getOrdersWithoutAuth();
=======

        OrderAPI.createOrderWithoutAuthorization(order);
        response = OrderAPI.getOrdersWithoutAuth();
>>>>>>> b68a0a1 (change branch develop)
        int statusCode = response.extract().statusCode();
        boolean isGet = response.extract().path("success");
        assertEquals(SC_UNAUTHORIZED, statusCode);
        assertFalse(isGet);

    }
}
