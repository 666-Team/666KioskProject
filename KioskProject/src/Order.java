import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Order {

    static int orderNumber;

    // 필드
    private int number;

    private double basketTotalPrice = 0;

    private String message = "미 입력";

    // 하위 객체
    private List<Product> basket = new ArrayList<>();

    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    private LocalDateTime orderCompleteTime;

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

    public void productAdd(Product product) {
        basket.add(product);
        addPrice(product.getPrice());
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setNumber() {
        orderNumber++;
        number = orderNumber;
    }

    public void setOrderTime() {
        orderTime = LocalDateTime.now();
    }

    public void saveOrderMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public LocalDateTime getOrderCompleteTime() {
        return orderCompleteTime;
    }

    public void changeWaitingToCompleted() {
        this.orderCompleteTime = LocalDateTime.now();
        this.orderStatus = OrderStatus.COMPLETED;
    }

    public void addPrice(double price) {
        basketTotalPrice += price;
    }

}
