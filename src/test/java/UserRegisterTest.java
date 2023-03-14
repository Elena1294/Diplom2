import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
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

    }

    @After
    public void cleanUp(){

        UserAPI.deleteUser(String.valueOf(user));

    }
}
