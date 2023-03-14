import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.*;

public class UpdateUserTest {
    private UserAPI userClient;
    private User user;
    private String accessToken;
    private ValidatableResponse response;

    @Before
    public void setUp() {
        userClient = new UserAPI();
        user = User.getRandomUser();
        response = userClient.newUser(user);
        accessToken = response.extract().path("accessToken");

    }
    @After
    public void clearState() {
        UserAPI.deleteUser(StringUtils.substringAfter(accessToken, " "));
    }

    @Test
    @DisplayName("Изменение данных пользователя")
    @Description("Данные успешно изменены код ответа 200")
    public void updateDataUserTest(){

        UserAPI.newUser(user);
        response = UserAPI.loginUser(user, accessToken);
        accessToken = response.extract().path("accessToken");
        ValidatableResponse responseUpd =  UserAPI.updateUserWithAuth(user, accessToken);
        int statusCode = responseUpd.extract().statusCode();
        boolean isChange = responseUpd.extract().path("success");
        assertEquals(SC_OK, statusCode);
        assertTrue(isChange);
        UserAPI.deleteUser(StringUtils.substringAfter(accessToken, " "));
    }
    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    @Description("Ошибка 401")
    public void updateDataUserWithoutAuthTest(){

        ValidatableResponse response = UserAPI.updateUserWithoutAuth(user);
        int statusCode = response.extract().statusCode();
        boolean isDataNotChange = response.extract().path("success");
        assertEquals(SC_UNAUTHORIZED, statusCode);
        assertFalse(isDataNotChange);
    }
}
