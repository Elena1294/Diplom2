import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
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
    }
}
