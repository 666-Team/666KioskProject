import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Display {
    // 필드
    static List<Order> orderList = new ArrayList<>();
    static List<Menu> menuList = new ArrayList<>();
    static Map<String, List<Product>> products = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);
    static Order order = new Order();

    static AdminDisplay adminDisplay = new AdminDisplay();

    static {
        // 데이터 초기화
        menuList.add(new Menu("햄버거 세트", "햄버거 세트입니다."));
        menuList.add(new Menu("햄버거 단품", "햄버거 단품입니다."));
        menuList.add(new Menu("사이드", "사이드 메뉴입니다."));

        products.put("햄버거 세트", new ArrayList<>());
        List<Product> HamburgerSetList = products.get("햄버거 세트");
        HamburgerSetList.add(new Product("콰트로 치즈 와퍼 세트", "네가지 고품격 치즈와 100% 순쇠고기 패티로 탄생한 버거킹의 스테디 셀러", 11300.0));
        HamburgerSetList.add(new Product("비프 불고기 버거 세트", "불고기 소스와 불맛 가득 100% 순쇠고기 패티의 감칠맛나는 조화", 7900.0));
        HamburgerSetList.add(
                new Product("트러플 머쉬룸 와퍼 세트", "트러플소스 2배로 더욱 깊어진 풍미, 네 가지 머쉬룸이 선사하는 깊고 풍부한 맛의 향연", 11900.0));

        products.put("햄버거 단품", new ArrayList<>());
        List<Product> HamburgerList = products.get("햄버거 단품");
        HamburgerList.add(new Product("콰트로 치즈 와퍼", "네가지 고품격 치즈와 100% 순쇠고기 패티로 탄생한 버거킹의 스테디 셀러", 8800.0));
        HamburgerList.add(new Product("비프 불고기 버거", "불고기 소스와 불맛 가득 100% 순쇠고기 패티의 감칠맛나는 조화", 5900.0));
        HamburgerList.add(new Product("트러플 머쉬룸 와퍼", "트러플소스 2배로 더욱 깊어진 풍미, 네 가지 머쉬룸이 선사하는 깊고 풍부한 맛의 향연", 9400.0));

        products.put("사이드", new ArrayList<>());
        List<Product> sideList = products.get("사이드");
        sideList.add(new Product("치즈 프라이", "바삭한 프렌치프라이에 고소한 치즈가 듬뿍 치즈프라이", 3900.0));
        sideList.add(new Product("너겟킹 4조각", "바삭하고 촉촉한 부드러운 너겟킹 (4EA)", 3100.0));
        sideList.add(new Product("크리미모짜볼", "겉은 바삭~ 속은 부드러운 크림치즈가 쏘옥, 크리미모짜볼 (10EA)", 5600.0));
    }

    // 메소드
    public void printMain() throws InterruptedException {

        printRecentOrderList();

        System.out.println("\n버커킹 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요\n");
        System.out.println("[ BURGERKING MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(i + 1 + ". " + menuList.get(i).getName() + " | " + menuList.get(i).getExplain());
        }
        System.out.println("\n[ ORDER MENU ]");
        System.out.println(menuList.size() + 1 + ". 장바구니 확인");
        System.out.println(menuList.size() + 2 + ". 주문 취소\n");

        int number = Integer.parseInt(scanner.nextLine());
        if (number == 0) {
            adminDisplay.printAdminLogin();
        }
        else if (number >= 1 && number <= menuList.size()) {
            printProductList(number);
        } else if (number == menuList.size() + 1) {
            printBasket();
        } else if (number == menuList.size() + 2) {
            printCancelBasket();
        }
        else {
            System.out.println("잘못된 값을 입력했습니다. 메인으로 돌아갑니다.");
        }
    }

    private void printRecentOrderList() {

        List<Order> completedList = new ArrayList<>();
        List<Order> waitingOrderList = new ArrayList<>();

        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderStatus() == OrderStatus.WAITING) {
                waitingOrderList.add(currentOrder);
            } else {
                completedList.add(currentOrder);
            }
        }

        completedList.sort(Comparator.comparing(Order::getOrderCompleteTime).reversed());
        waitingOrderList.sort(Comparator.comparing(Order::getOrderTime).reversed());

        printCompletedOrderListLimit3(completedList);
        printWaitingOrderList(waitingOrderList);
    }

    private void printCompletedOrderListLimit3(List<Order> completedList) {
        if (!completedList.isEmpty()) {
            int cnt = 0;
            System.out.println("[ 최근 완료 주문 목록 ]");
            for (Order completedOrder : completedList) {
                if (cnt >= 3) {
                    break;
                }
                List<Product> completedOrderBasket = completedOrder.getBasket();
                Product firstProduct = completedOrderBasket.get(0);
                System.out.println(
                        completedOrder.getNumber() + "번 완료 주문 | 주문 품목: " + firstProduct.getName() + " 등...\t | 주문 일시: "
                                + completedOrder.getOrderTime()
                                + "\t | 주문 완료 일시: " + completedOrder.getOrderCompleteTime());
                cnt++;
            }
        }
    }

    private void printWaitingOrderList(List<Order> waitingOrderList) {
        if (!waitingOrderList.isEmpty()) {
            System.out.println("\n[ 대기 주문 목록 ]");
            for (Order waitingOrder : waitingOrderList) {
                List<Product> waitingOrderBasket = waitingOrder.getBasket();
                Product firstProduct = waitingOrderBasket.get(0);
                System.out.println(waitingOrder.getNumber() + "번 대기 주문 | 주문 품목: " +
                        firstProduct.getName() + " 등...\t | 주문 일시: " + waitingOrder.getOrderTime());
            }
        }
    }

}
