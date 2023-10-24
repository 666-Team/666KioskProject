import java.util.ArrayList;
import java.util.List;

public class Order {

    static int orderNumber;

    // 필드
    private int number;

    private double basketTotalPrice = 0;

    private String message;

    // 하위 객체
    private List<Product> basket = new ArrayList<>();

    private OrderStatus orderStatus;

    //생성자


    public Order() {
        this.orderStatus = OrderStatus.WAITING;
    }

    // 메소드


    public int getNumber() {
        return number;
    }

    public double getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public List<Product> getBasket() {
        return basket;
    }

    public void addPrice(double price) {
        basketTotalPrice += price;
    }
}
