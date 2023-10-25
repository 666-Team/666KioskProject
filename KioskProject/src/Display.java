import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Display {
    // 필드
    int password = 1004;
    public static List<Order> orderList = new ArrayList<>();
    static List<Menu> menuList = new ArrayList<>();
    static Map<String, List<Product>> products = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);
    static Order order = new Order();

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
    void printMain() throws InterruptedException {
        System.out.println();
        System.out.println("버커킹 에 오신걸 환영합니다.");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요");
        System.out.println();
        System.out.println("[ BURGERKING MENU ]");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println(i + 1 + ". " + menuList.get(i).getName() + " | " + menuList.get(i).getExplain());
        }
        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(menuList.size() + 1 + ". 장바구니 확인");
        System.out.println(menuList.size() + 2 + ". 주문 취소");
        System.out.println();
        Scanner mainScanner = new Scanner(System.in);
        int number = mainScanner.nextInt();

        if (number == 0) {
            printLoginAdmin();
        } else if (number >= 1 && number <= menuList.size()) {
            printProductList(number);
        } else if (number == menuList.size() + 1) {
            printBasket();
        } else if (number == menuList.size() + 2) {
            printCancelBasket();
        } else {
            System.out.println("잘못된 값을 입력했습니다. 메인으로 돌아갑니다.");
        }
    }

    void printProductList(int number) throws InterruptedException {

        Menu menu = menuList.get(number - 1);
        String menuName = menu.getName();

        System.out.println();
        System.out.println("버커킹 에 오신걸 환영합니다.");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요");
        System.out.println();
        System.out.println("[ " + menuName + " MENU ]");

        List<Product> productList = Display.products.get(menuName);
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(
                    i + 1 + ". " + productList.get(i).getName() + "\t | " + productList.get(i).getPrice()
                            + "\t | " + productList.get(i).getExplain());
        }

        System.out.println();
        System.out.println("[ ORDER MENU ]");
        System.out.println(productList.size() + 1 + ". 장바구니 확인");
        System.out.println(productList.size() + 2 + ". 주문 취소");
        System.out.println();

        int setOrder = scanner.nextInt();
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

    void printLoginAdmin() throws InterruptedException {
        System.out.println();
        System.out.println("[ ONLY ADMIN ]");
        System.out.println();
        System.out.println("1. 비밀번호 입력  2.돌아가기");
        System.out.println();
        int mainSelect = scanner.nextInt();
        if (mainSelect == 1) {
            System.out.println();
            System.out.println("[ 비밀번호를 입력하세요 ]");
            System.out.println();
            int passwordInput = scanner.nextInt();
            if (passwordInput == password) {
                printAdminMenu();
            } else {
                System.out.println();
                System.out.println("비밀번호를 틀렸습니다.");
                printLoginAdmin();
            }
        } else if (mainSelect == 2) {
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
        } else {
            System.out.println("잘못된 값을 입력했습니다.");
            printLoginAdmin();
        }
    }

    void printAdminMenu() throws InterruptedException {
        Scanner mainScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("[ ADMIN MENU ]");
        System.out.println();
        System.out.println("1. 총 판매 금액 현황  2. 총 판매 상품 현황  3. 비밀번호 변경 4. 상품 삭제 5. 돌아가기");
        System.out.println();
        int mainSelect = mainScanner.nextInt();
        if (mainSelect == 1) {
            printSalesTotalPrice();
        } else if (mainSelect == 2) {
            printSalesTotalProduct();
        } else if (mainSelect == 3) {
            setAdminPassword();
        } else if (mainSelect == 4) {
            printProduct();
            System.out.println("삭제할 상품 ID: ");
            int delId = scanner.nextInt();
            deleteMenuproduct(delId);
        } else if (mainSelect == 5) {
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
        } else {
            System.out.println();
            System.out.println("잘못된 값을 입력했습니다.");
            printAdminMenu();
        }
    }

    void printSalesTotalPrice() throws InterruptedException {
        Scanner mainScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("[ 총 판매 금액 현황 ]");
        System.out.println();
        System.out.println("현재까지 총 판매된 금액은 [ " + getSaleTotalPrice() + " ] 입니다.");
        System.out.println();
        System.out.println("1. ADMIN MENU로 돌아가기  2. 메인으로 돌아가기");
        System.out.println();
        int mainSelect = mainScanner.nextInt();
        if (mainSelect == 1) {
            printAdminMenu();
        } else if (mainSelect == 2) {
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
        } else {
            System.out.println("잘못된 값을 입력했습니다.");
            printSalesTotalPrice();
        }
    }

    void printSalesTotalProduct() throws InterruptedException {
        Scanner mainScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println();
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.");
        System.out.println();
        for (Order order : orderList) {
            for (Product product : order.getBasket()) {
                System.out.println(
                        "- " + product.getName() + "\t | " + product.getPrice());
            }
        }
        System.out.println();
        System.out.println("1. ADMIN MENU로 돌아가기  2. 메인으로 돌아가기");
        System.out.println();
        int mainSelect = mainScanner.nextInt();
        if (mainSelect == 1) {
            printAdminMenu();
        } else if (mainSelect == 2) {
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
        } else {
            System.out.println("잘못된 값을 입력했습니다.");
            printSalesTotalPrice();
        }
    }

    void setAdminPassword() throws InterruptedException {
        Scanner mainScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("[ ADMIN 비밀번호 변경 ]");
        System.out.println();
        System.out.println("1. 비밀번호 변경하기  2. ADMIN MENU로 돌아가기");
        System.out.println();
        int mainSelect = mainScanner.nextInt();
        if (mainSelect == 1) {
            System.out.println();
            System.out.println("[ ADMIN 비밀번호 변경 ]");
            System.out.println();
            System.out.print("현재 비밀번호 입력: ");
            int inputPassword = mainScanner.nextInt();
            if (inputPassword == password) {
                System.out.println();
                System.out.println("[ ADMIN 비밀번호 변경 ]");
                System.out.println();
                System.out.print("변경할 비밀번호 입력: ");
                password = mainScanner.nextInt();
                printAdminMenu();
            } else {
                System.out.println();
                System.out.println("비밀번호를 틀렸습니다.");
                setAdminPassword();
            }
        } else if (mainSelect == 2) {
            printAdminMenu();
        } else {
            System.out.println("잘못된 값을 입력했습니다.");
            setAdminPassword();
        }
    }

    void deleteMenuproduct(int delId) {
        for (int i =0; i < menuList.size(); i++) {
            if(menuList.get(i).getId()==delId){
                System.out.println(
                        "ID : " + menuList.get(i).getId() + "\t | " + menuList.get(i).getName()
                                + "\t | " + menuList.get(i).getExplain()+"메뉴가 삭제되었습니다.");
                menuList.remove(i);
            }
        }
        for (Menu menu : menuList) {
            String menuName = menu.getName();
            List<Product> productList = Display.products.get(menuName);
            for (int k = 0; k < productList.size(); k++) {
                if (productList.get(k).getId() == delId) {
                    System.out.println("ID : " + productList.get(k).getId() + "\t | " + productList.get(k).getName()
                            + "\t | " + productList.get(k).getPrice() + "\t | " + productList.get(k).getExplain()
                            + "상품이 삭제되었습니다.");
                    productList.remove(k);

                }
            }
        }

    }

    void printProduct() {
        for (Menu value : menuList) {
            System.out.println(
                    "ID : " + value.getId() + "\t | " + value.getName()
                            + "\t | " + value.getExplain());
        }
        for (Menu menu : menuList) {
            String menuName = menu.getName();
            List<Product> productList = Display.products.get(menuName);
            for (Product product : productList) {
                System.out.println(
                        "ID : " + product.getId() + "\t | " + product.getName()
                                + "\t | " + product.getPrice() + "\t | " + product.getExplain());
            }
        }
    }


    public void printAddBasket(Product orderProduct) {
        System.out.println();
        // 여기에 주문을 추가하는 메소드가 들어갈 것
        // basket에 담고 메인으로 돌아가야함

        System.out.println(
                orderProduct.getName() + " | " + orderProduct.getPrice() + " | " + orderProduct.getExplain());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인  2. 취소");
        System.out.println();
        int orderSelect = scanner.nextInt();
        if (orderSelect == 1) {
            List<Product> basket = order.getBasket();
            basket.add(orderProduct);
            System.out.println();
            System.out.println(orderProduct.getName() + "가 장바구니에 추가되었습니다.");
        } else if (orderSelect == 2) {
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
        }
    }

    public void printBasket() throws InterruptedException {
        System.out.println();
        // 주문 내역 확인 및 토탈 가격 확인
        System.out.println("[ Orders ]");
        if (order.getBasket().isEmpty()) {
            {
                System.out.println("장바구니가 비어있습니다.");
            }
        } else {
            List<Product> basket = order.getBasket();
            for (Product product : basket) {
                double price = product.getPrice();
                String name = product.getName();
                String explain = product.getExplain();
                System.out.println(name + " | " + price + " | " + explain);
                order.addPrice(price);
            }
        }
        System.out.println();
        System.out.println("[ Total ]");
        System.out.println(order.getBasketTotalPrice());
        System.out.println();
        System.out.println("1. 주문하기  2. 메뉴판");
        System.out.println();
        int orderSelect = scanner.nextInt();
        if (orderSelect == 1) {
            List<Product> basket = order.getBasket();
            if (basket.isEmpty()) {
                System.out.println();
                System.out.println("장바구니가 비어있어서 주문을 할 수 없습니다.");
                printBasket();
            } else {
                printCompletedOrder();
            }
        }
        if (orderSelect == 2) {
            order = new Order();
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
        }
    }

    public void printCancelBasket() {
        System.out.println();
        // 여기에 주문을 전부 취소하는 메소드가 들어갈 것
        // 진행하던 주문을 취소하시겠습니까?
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.print("1. 확인  2. 취소");
        System.out.println();
        // 스캐너로 확인, 취소
        int orderSelect = scanner.nextInt();
        if (orderSelect == 1) {
            order = new Order();
            System.out.println();
            System.out.println("진행하던 주문이 취소되었습니다.");
            // 캔슬 하면 메인으로 다시 돌아가야 함
        } else if (orderSelect == 2) {
            System.out.println();
            System.out.println("메인화면으로 돌아갑니다.");
            // 캔슬 안하면 다시 원래 메뉴로 돌아갈 것
        }
    }

    private void printCompletedOrder() throws InterruptedException {
        System.out.println();
        // 여기에 주문 번호를 넣어주는 메소드가 들어갈 것
        // orderNumber++..?
        System.out.println("주문이 완료되었습니다!");
        System.out.println();
        Display.orderList.add(order);
        Order.orderNumber++;
        System.out.println("대기번호는 [ " + Order.orderNumber + " ] 번 입니다.");
        order = new Order();
        System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
        // 주문 번호 주고 3초 뒤 메인으로 다시 돌아가야 함
        // Timer 유틸 쓸거임!! 공부하고 적용 / 어떻게 쓰지..?
        Thread.sleep(3000);
    }

    private static double getSaleTotalPrice() {

        int saleTotalPrice = 0;
        for (Order order : orderList) {
            saleTotalPrice += order.getBasketTotalPrice();
        }

        return saleTotalPrice;
    }


}
