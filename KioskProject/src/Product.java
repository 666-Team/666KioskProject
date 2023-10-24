// Menu를 상속 받는 Product

public class Product extends Menu {

    // 필드
    private final Double price; // 버거 세트 가격

    // 생성자
    public Product(String name, String explain, Double price) {
        super(name, explain);
        this.price = price;
        // 부모 생성자가 실행이 안된 상태에서 자식 생성자에 넣으려니 안되는 것
    }

    // 메소드

    public Double getPrice() {
        return price;
    }

    // 하위 객체

}
