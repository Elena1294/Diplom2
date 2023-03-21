import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
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

    }
    @Test
    @DisplayName("Регистрация пользователя без обязательных полей")
    @Description("Ошибка 403")
    public void createUserWithoutRequiredFieldsTest() {
        user.setPassword(null);
        response = UserAPI.newUser(user);
        int statusCode = response.extract().statusCode();
        boolean isCreate = response.extract().path("success");
        assertFalse(isCreate);
        assertEquals(SC_FORBIDDEN, statusCode);

        accessToken = response.extract().path("accessToken");
        if (accessToken != null) {
            ValidatableResponse validatableResponse = UserAPI.loginUser(user, accessToken);
            int statusCodeLogin = validatableResponse.extract().statusCode();
            boolean isUserNotLogin = validatableResponse.extract().path("success");
            assertFalse(isUserNotLogin);
            assertEquals(SC_UNAUTHORIZED, statusCodeLogin);
        }
        else
        {
                UserAPI.deleteUser(StringUtils.substringAfter(accessToken, " "));
            }
        }
    @After
    public void cleanUp(){

        response = userAPI.loginUser(user, accessToken);
        accessToken = response.extract().path("accessToken");
        if (accessToken != null) {
            UserAPI.deleteUser(accessToken);
        }

    }

    }


