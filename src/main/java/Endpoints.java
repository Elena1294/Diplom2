import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.http.ContentType.JSON;

public class Endpoints {

    public static final String BASE = "https://stellarburgers.nomoreparties.site";
    public static final String API_CREATE = "/api/auth/register";
    public static final String API_LOGIN = "/api/auth/login";
    public static final String API_DELETE = "/api/auth/user";

    public static final String API_ORDERS = "/api/orders/all"; //получить все заказы
    public static final String ORDER_PATH = "/api/orders/";

    public static final String USER_PATH = "/api/auth/";
    public static final String INGREDIENTS_PATH = "/api/ingredients/";

    protected static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE)
                .build();
    }
}
