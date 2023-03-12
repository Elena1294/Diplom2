import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
<<<<<<< HEAD
=======
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
>>>>>>> b68a0a1 (change branch develop)
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
<<<<<<< HEAD

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
=======
import static org.junit.Assert.*;

public class UserRegisterTest {

    ValidatableResponse response;
    private User user;
    private UserAPI userAPI;
    private String accessToken;
    @Before
    public void setUp() {
        user = User.getRandomUser();
        userAPI = new UserAPI();
    }
    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Пользователь успешно регистрируется, код ответа 200 OK")
    public void createUserTest() {
        response = userAPI.newUser(user);
        accessToken = response.extract().path("accessToken");
        int statusCode = response.extract().statusCode();
        boolean isUserCreate = response.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isUserCreate);
        userAPI.deleteUser(StringUtils.substringAfter(accessToken, " "));
    }
    @Test
    @DisplayName("Регистрация уже зарегистрированного пользователя")
    @Description("Ошибка 403")
    public void createAlredyRegisterUserTest() {
        response = userAPI.newUser(user);
        accessToken = response.extract().path("accessToken");
        response = userAPI.newUser(user);
        int statusCode = response.extract().statusCode();
        boolean isCreate = response.extract().path("success");
        assertFalse(isCreate);
        assertEquals(SC_FORBIDDEN, statusCode);
        userAPI.deleteUser(StringUtils.substringAfter(accessToken, " "));
    }
    @Test
    @DisplayName("Регистрация пользователя без обязательных полей")
    @Description("Ошибка 403")
    public void creatingUserWithoutRequiredFieldsTest() {
        user.setPassword(null);
        response = userAPI.newUser(user);
        int statusCode = response.extract().statusCode();
        boolean isUserNotCreate = response.extract().path("success");
        assertFalse(isUserNotCreate);
        assertEquals(SC_FORBIDDEN, statusCode);// Тут падает  ошибка если добавлять удаление пользователя, т.к он не видит токен
>>>>>>> b68a0a1 (change branch develop)
    }

    @After
    public void cleanUp(){

        UserAPI.deleteUser(user);

    }
}
