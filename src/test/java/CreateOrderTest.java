import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import java.util.List;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;

public class CreateOrderTest {
<<<<<<< HEAD
    private ValidatableResponse validatableResponse;
    private String accessToken;

    UserAPI userAPI = new UserAPI();
    OrderAPI orderAPI = new OrderAPI();
    User userValid = new User(TestDataUser.CREATED_LOGIN, TestDataUser.CREATED_PASSWORD);
    Order order = new Order();

    @Before
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE;
=======
    private UserAPI userAPI;
    private OrderAPI orderAPI;
    private ValidatableResponse response;
    private Order order;
    private User user;

    @Before
    public void setUp() {
        userAPI = new UserAPI();
        orderAPI = new OrderAPI();
        order = new Order();
        user = User.getRandomUser();
>>>>>>> b68a0a1 (change branch develop)
    }

    @Test
    @DisplayName("Создание заказа после авторизации пользователя")
    @Description("Заказ создан, код ответа 200")
    public void orderCreateWithAuthTest(){
        fillListIngredients();
<<<<<<< HEAD
        Response response = UserAPI.loginUser(userValid);
        accessToken = response.then().extract().path("accessToken");
        validatableResponse = orderAPI.orderCreate(order,accessToken);
        int statusCode = validatableResponse.extract().statusCode();
        boolean isCreate = validatableResponse.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isCreate);
=======
        response = userAPI.newUser(user);
        String accessToken = response.extract().path("accessToken");
        userAPI.loginUser(user, accessToken);
        response = orderAPI.orderCreate(order,accessToken);
        int statusCode = response.extract().statusCode();
        boolean isCreate = response.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isCreate);
        userAPI.deleteUser(StringUtils.substringAfter(accessToken, " "));
>>>>>>> b68a0a1 (change branch develop)
    }


    @Test
    @DisplayName("Создание заказа без авторизации пользователя")
    @Description("Заказ создан, код ответа 200")
    public void orderCreateWithoutAuthorization(){
        fillListIngredients();
<<<<<<< HEAD
        validatableResponse = orderAPI.createOrderWithoutAuthorization(order);
=======
        ValidatableResponse validatableResponse = orderAPI.createOrderWithoutAuthorization(order);
>>>>>>> b68a0a1 (change branch develop)
        int statusCode = validatableResponse.extract().statusCode();
        boolean isCreate = validatableResponse.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isCreate);
    }
    @Test
    @DisplayName("Создание заказа без авторизации пользователя и без ингредиентов")
    @Description("Ошибка 400")
    public void orderCreateWithoutAuthorizationAndIngredients(){
<<<<<<< HEAD
        validatableResponse = orderAPI.createOrderWithoutAuthorization(order);
=======
        ValidatableResponse validatableResponse = orderAPI.createOrderWithoutAuthorization(order);
>>>>>>> b68a0a1 (change branch develop)
        int statusCode = validatableResponse.extract().statusCode();
        boolean isCreate = validatableResponse.extract().path("success");
        assertEquals(SC_BAD_REQUEST, statusCode);
        assertFalse(isCreate);
    }

    @Test
    @DisplayName("Создние заказа без авторизации пользователя и с неверным хешом ингредиентов")
    @Description("Ошибка 500")
    public void orderCreateWithoutAuthorizationAndWrongHashIngredient(){
<<<<<<< HEAD
        validatableResponse = userAPI.getAllIngredients();
=======
        ValidatableResponse validatableResponse = orderAPI.getAllIngredients();
>>>>>>> b68a0a1 (change branch develop)
        List<String> list = validatableResponse.extract().path("data._id");
        List<String> ingredients = order.getIngredients();
        ingredients.add(list.get(0));
        ingredients.add(list.get(5).replaceAll("a", "l"));
        ingredients.add(list.get(0));
        validatableResponse = orderAPI.createOrderWithoutAuthorization(order);
        int statusCode = validatableResponse.extract().statusCode();
        assertEquals(SC_INTERNAL_SERVER_ERROR, statusCode);
    }
    private void fillListIngredients() {
<<<<<<< HEAD
        validatableResponse = userAPI.getAllIngredients();
=======
        ValidatableResponse validatableResponse = orderAPI.getAllIngredients();
>>>>>>> b68a0a1 (change branch develop)
        List<String> list = validatableResponse.extract().path("data._id");
        List<String> ingredients = order.getIngredients();
        ingredients.add(list.get(0));
        ingredients.add(list.get(5));
        ingredients.add(list.get(0));
    }
}
