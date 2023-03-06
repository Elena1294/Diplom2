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

public class UserRegisterTest {
    User user = new User(TestDataUser.LOGIN, TestDataUser.PASSWORD, TestDataUser.NAME);
    UserAPI UserAPI = new UserAPI();

    @Before
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE;
    }

    @Test
    @DisplayName("Создание нового пользователя") // имя теста
    @Description("Проверка того, что пользователь успешно создается") // описание теста
    public void createNewUserTest() {

        Response response = UserAPI.newUser(user);
        response.then().statusCode(200);
        response.then().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Попытка создания второго одинакового пользователя") // имя теста
    @Description("Проверка создания второго одинакового пользователя с существующим в базе логином") // описание теста
    public void createDoubleUserTest() {

        User userDouble = new User(TestDataUser.CREATED_LOGIN, TestDataUser.CREATED_PASSWORD, TestDataUser.CREATED_NAME);
        Response response = UserAPI.newUser(userDouble);
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Попытка создания юзера без логина") // имя теста
    @Description("Проверка создания юзера без логина") // описание теста
    public void createUserWithOutLoginTest() {

        User UserWithOutLogin = new User("", TestDataUser.PASSWORD, TestDataUser.NAME);
        Response response = UserAPI.newUser(UserWithOutLogin);
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }
    @Test
    @DisplayName("Попытка создания юзера без пароля") // имя теста
    @Description("Проверка создания юзера без пароля") // описание теста
    public void createUserWithOutPasswordTest() {

        User UserWithOutPassword = new User(TestDataUser.LOGIN, "", TestDataUser.NAME);
        Response response = UserAPI.newUser(UserWithOutPassword);
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @After
    public void cleanUp(){

        UserAPI.deleteUser(user);

    }

}
