import java.util.List;

public class ConsumerDisplay {

    public void printProductList(int number) throws InterruptedException {

        Menu menu = Display.menuList.get(number - 1);
        String menuName = menu.getName();

        System.out.println("\n버커킹 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요\n");

        System.out.println("[ " + menuName + " MENU ]");

        List<Product> productList = Display.products.get(menuName);
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(
                    i + 1 + ". " + productList.get(i).getName() + "\t | " + productList.get(i).getPrice()
                            + "\t | " + productList.get(i).getExplain());
        }

        System.out.println("\n[ ORDER MENU ]");
        System.out.println(productList.size() + 1 + ". 장바구니 확인");
        System.out.println(productList.size() + 2 + ". 주문 취소\n");


        int setOrder = Integer.parseInt(Display.scanner.nextLine());
        if (setOrder >= 1 && setOrder <= productList.size()) {
           printAddBasket(productList.get(setOrder - 1));
        } else if (setOrder == productList.size() + 1) {
            printBasket();
        } else if (setOrder == productList.size() + 2) {
            printCancelBasket();
        } else {
            System.out.println("잘못된 값을 입력했습니다. 메인으로 돌아갑니다.");
        }
    }

    private void printAddBasket(Product orderProduct) {

        System.out.println("\n" +
                orderProduct.getName() + " | " + orderProduct.getPrice() + " | " + orderProduct.getExplain());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인  2. 취소\n");

        int orderSelect = Integer.parseInt(Display.scanner.nextLine());

        switch (orderSelect) {
            case 1 -> {
                Display.order.addProduct(orderProduct);
                System.out.println("\n" + orderProduct.getName() + "가 장바구니에 추가되었습니다.");
            }
            case 2 -> System.out.println("\n메인화면으로 돌아갑니다.");
            default -> System.out.println("잘못된 값을 입력했습니다.");
        }
    }

    public void printBasket() throws InterruptedException {

        // 주문 내역 확인 및 토탈 가격 확인
        System.out.println("\n[ Orders ]");
        if (Display.order.getBasket().isEmpty()) {
            {
                System.out.println("장바구니가 비어있습니다.");
            }
        } else {
            List<Product> basket = Display.order.getBasket();
            for (Product product : basket) {
                double price = product.getPrice();
                String name = product.getName();
                String explain = product.getExplain();
                System.out.println(name + " | " + price + " | " + explain);
            }
        }
        System.out.println("\n[ Total ]");
        System.out.println(Display.order.getBasketTotalPrice());
        System.out.println("\n고객님의 주문 요청사항: " + Display.order.getMessage() + "\n");
        System.out.println("\n고객님의 주문 요청사항: " + Display.order.getMessage() + "\n");
        System.out.println("1. 주문하기  2.주문 요청사항 입력하기  3. 메뉴판\n");
        int orderSelect = Integer.parseInt(Display.scanner.nextLine());
        if (orderSelect == 1) {
            List<Product> basket = Display.order.getBasket();
            if (basket.isEmpty()) {
                System.out.println("\n장바구니가 비어있어서 주문을 할 수 없습니다.");
                printBasket();
            } else {
                printCompletedOrder();
            }
        }
        if (orderSelect == 2) {
            printOrderMessage();
        }
        if (orderSelect == 3) {
            System.out.println("\n메인화면으로 돌아갑니다.");
        }
    }

    private void printOrderMessage() throws InterruptedException {
        System.out.println("\n요구사항을 입력하세요\n");
        String message = Display.scanner.nextLine();
        if (!isLengthTwenty(message)) {
            System.out.println("요구사항은 20자 이내 작성해주세요.");
            printOrderMessage();
        }
        Display.order.saveOrderMessage(message);
        printBasket();
    }

    private boolean isLengthTwenty(String message) {
        return message.length() <= 20;
    }

    public void printCancelBasket() throws InterruptedException {

        System.out.println("\n진행하던 주문을 취소하시겠습니까?");
        System.out.print("1. 확인  2. 취소\n");

        int orderSelect = Integer.parseInt(Display.scanner.nextLine());
        switch (orderSelect) {
            case 1 -> {
                Display.order = new Order();
                System.out.println("\n진행하던 주문이 취소되었습니다.");
            }
            case 2 -> {
                System.out.println("\n메인화면으로 돌아갑니다.");

            }
        }
    }

    private void printCompletedOrder() throws InterruptedException {

        System.out.println("\n주문이 완료되었습니다!\n");
        Display.order.setNumber();
        Display.order.setOrderTime();
        Display.orderList.add(Display.order);
        System.out.println("대기번호는 [ " + Order.orderNumber + " ] 번 입니다.");
        Display.order = new Order();
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        Thread.sleep(3000);
    }
}


