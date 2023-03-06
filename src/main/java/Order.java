import java.util.List;
import java.util.ArrayList;

public class Order {

        private List<String> ingredients;
        public Order(List<String> ingredients) {
            this.ingredients = ingredients;
        }
        public Order() {
            ingredients = new ArrayList<>();
        }
        public List<String> getIngredients() {
            return ingredients;
        }
        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;

    }
}
