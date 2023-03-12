import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
<<<<<<< HEAD
=======
import io.restassured.response.ValidatableResponse;
>>>>>>> b68a0a1 (change branch develop)
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
<<<<<<< HEAD
public class UserLoginTest {

    UserAPI UserAPI = new UserAPI();

    @Before
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE;
    }

    @Test
    @DisplayName("Успешная авторизация") // имя теста
    @Description("Успешная авторизация с существующей парой логин/пароль") // описание теста
    public void checkSuccessfulLoginTest() {
        User userValid = new User(TestDataUser.CREATED_LOGIN, TestDataUser.CREATED_PASSWORD);
        Response response = UserAPI.loginUser(userValid);
        response.then().statusCode(SC_OK);
        response.then().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Попытка авторизации юзера с некорректным логином") // имя теста
    @Description("Проверка создания юзера без логина") // описание теста
    public void loginUserWithIncorrectLoginTest() {

        User UserWithOutLogin = new User("gg@mail.ru", TestDataUser.PASSWORD, TestDataUser.NAME);
        Response response = UserAPI.loginUser(UserWithOutLogin);
        response.then().assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("Попытка авторизации юзера с некорректным паролем") // имя теста
    @Description("Проверка создания юзера без пароля") // описание теста
    public void loginUserWithIncorrectPasswordTest() {

        User UserWithOutPassword = new User(TestDataUser.LOGIN, "1111", TestDataUser.NAME);
        Response response = UserAPI.loginUser(UserWithOutPassword);
        response.then().assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("email or password are incorrect"));
=======
import static org.junit.Assert.*;

public class UserLoginTest {

    private UserAPI userAPI;
    private User user;
    private String accessToken;
    private ValidatableResponse response;

    @Before
    public void setUp() {
        user = User.getRandomUser();
        userAPI = new UserAPI();
    }

    @Test
    @DisplayName("Авторизация зарегистрированного пользователя")
    @Description("Пользователь успешно авторизуется, код ответа 200 OK")
    public void loginUserTest() {
        response = userAPI.newUser(user);
        accessToken = response.extract().path("accessToken");
        response = userAPI.loginUser(user, accessToken);
        int statusCode = response.extract().statusCode();
        boolean isUserLogin = response.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isUserLogin);
    }
    @Test
    @DisplayName("Авторизация пользователя с пустым полем email")
    @Description("Ошибка 401")
    public void loginWithEmptyEmailTest() {
        ValidatableResponse response = userAPI.newUser(user);
        accessToken = response.extract().path("accessToken");
        user.setEmail(null);
        ValidatableResponse validatableResponse = userAPI.loginUser(user, accessToken);
        int statusCode = validatableResponse.extract().statusCode();
        boolean isUserNotLogin = validatableResponse.extract().path("success");
        assertEquals(SC_UNAUTHORIZED, statusCode);
        assertFalse(isUserNotLogin);
    }
    @Test
    @DisplayName("Авторизация пользователя с пустым полем password")
    @Description("Ошибка 401")
    public void loginWithEmptyPasswordTest(){
        ValidatableResponse response = userAPI.newUser(user);
        accessToken = response.extract().path("accessToken");
        user.setPassword(null);
        ValidatableResponse validatableResponse = userAPI.loginUser(user,accessToken);
        int statusCode = validatableResponse.extract().statusCode();
        boolean isUserNotLogin = validatableResponse.extract().path("success");
        assertFalse(isUserNotLogin);
        assertEquals(SC_UNAUTHORIZED, statusCode);
>>>>>>> b68a0a1 (change branch develop)
    }
}
