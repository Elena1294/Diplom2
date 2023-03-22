import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.*;

public class UserLoginTest {

    private UserAPI userAPI;
    private User user;
    private String accessToken = null;
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
        ValidatableResponse validatableResponse = userAPI.loginUser(user,accessToken);
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

    }

    @After
    public void cleanUp(){

        if (accessToken != null) {UserAPI.deleteUser(accessToken);}

    }

    }

