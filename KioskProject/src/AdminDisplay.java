import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDisplay {
    static int password = 1004;

    public void printAdminLogin() throws InterruptedException {

        System.out.println("\n[ ONLY ADMIN ]\n");
        System.out.println("1. 비밀번호 입력\t2.돌아가기\n");

        int mainSelect;
        mainSelect = Integer.parseInt(Display.scanner.nextLine());
        switch (mainSelect) {
            case 1 -> {
                System.out.println("\n[ 비밀번호를 입력하세요 ]\n");
                int passwordInput = Integer.parseInt(Display.scanner.nextLine());
                if (passwordInput == password) {
                    printAdminMenu();
                } else {
                    System.out.println("\n비밀번호를 틀렸습니다.");
                    printAdminLogin();
                }
            }
            case 2 -> System.out.println("\n메인화면으로 돌아갑니다.");
            default -> {
                System.out.println("잘못된 값을 입력했습니다.");
                printAdminLogin();
            }
        }
    }

    private void printAdminMenu() throws InterruptedException {

        System.out.println("\n[ ADMIN MENU ]\n");
        System.out.println("1. 대기 주문 목록 조회\t2. 완료 주문 목록 조회\t3. 메뉴 및 상품 추가\t4. 메뉴 및 상품 삭제");
        System.out.println("5. 총 판매 금액 현황  \t6. 총 판매 상품 현황  \t7. 비밀번호 변경   \t8. 돌아가기");

        int mainSelect = Integer.parseInt(Display.scanner.nextLine());
        switch (mainSelect) {
            case 1 -> printAdminWaitingOrder();
            case 2 -> printCompletedOrderList();
            case 3 -> addMenuOrProduct();
            case 4 -> deleteMenuOrProduct();
            case 5 -> printSaleTotalPrice();
            case 6 -> printSaleTotalProduct();
            case 7 -> printChangeAdminPassword();
            case 8 -> System.out.println("\n메인으로 돌아갑니다.\n");
            default -> {
                System.out.println("\n잘못된 값을 입력했습니다.");
                printAdminMenu();
            }
        }
    }

    // 1. 대기 주문 목록 조회
    private void printAdminWaitingOrder() throws InterruptedException {

        System.out.println("\n[ 대기 주문 목록 ]\n");

        List<Order> waitingOrderList = getWaitingOrderList();
        for (Order currentOrder : waitingOrderList) {
            System.out.println(currentOrder.getNumber() + "번 대기 주문 | 요구 사항: " + currentOrder.getMessage() + "\t | "
                    + currentOrder.getOrderTime());
            for (Product product : currentOrder.getBasket()) {
                System.out.println("- " + product.getName());
            }
            System.out.println("--------------------------------------------------");

        }

        System.out.println("총 주문 금액");
        System.out.println(getSaleWaitingTotalPrice());
        System.out.println("\n1. 대기 주문 완료 처리 하기\t2. ADMIN MENU로 돌아가기\n");

        int input = Integer.parseInt(Display.scanner.nextLine());
        switch (input) {
            case 1 -> printAdminWaitingToCompletedInput();
            case 2 -> printAdminMenu();
            default -> {
                System.out.println("잘못된 값을 입력했습니다.");
                printAdminWaitingOrder();
            }
        }
    }

    private void printAdminWaitingToCompletedInput() throws InterruptedException {

        System.out.println("\n완료 처리 할 대기 주문 번호 입력 (0번 입력 시 취소)\n");

        int input = Integer.parseInt(Display.scanner.nextLine());

        List<Order> waitingOrderList = getWaitingOrderList();

        boolean isRemoved = false;

        if (input == 0) {
            printAdminWaitingOrder();
        }
        for (Order currentOrder : waitingOrderList) {
            if (input == currentOrder.getNumber()) {
                currentOrder.changeWaitingToCompleted();
                System.out.println("\n" + input + "번 주문 완료 처리 되었습니다.\n");
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            System.out.println("잘못된 값을 입력했습니다.");
        }
        printAdminWaitingOrder();
    }

    private static List<Order> getWaitingOrderList() {
        return Display.orderList.stream().filter(o -> o.getOrderStatus() == OrderStatus.WAITING).collect(Collectors.toList());
    }

    private double getSaleWaitingTotalPrice() {

        double saleTotalPrice = 0;
        for (Order order : Display.orderList) {
            if (order.getOrderStatus() == OrderStatus.WAITING) {
                saleTotalPrice += order.getBasketTotalPrice();
            }
        }
        return saleTotalPrice;
    }

    // 2. 완료 주문 목록 조회
    private void printCompletedOrderList() throws InterruptedException {

        List<Order> completedOrderList = getCompletedOrderList();

        System.out.println("\n[ 완료 주문 목록 ]\n");

        for (Order currentOrder : completedOrderList) {
            System.out.println(
                    currentOrder.getNumber() + "번 완료 주문 | 요구 사항: " + currentOrder.getMessage() + "\t | "
                            + currentOrder.getOrderTime()
                            + "\t | " + currentOrder.getOrderCompleteTime());
            for (Product product : currentOrder.getBasket()) {
                System.out.println("- " + product.getName());
            }
            System.out.println("--------------------------------------------------");

            System.out.println("총 주문 금액");
            System.out.println(getSaleTotalPrice());
        }
        System.out.println("\n1. ADMIN MENU로 돌아가기");
        int input = Integer.parseInt(Display.scanner.nextLine());

        if (input == 1) {
            printAdminMenu();
        } else {
            System.out.println("잘못된 값을 입력했습니다.");
        }
    }

    private static List<Order> getCompletedOrderList() {
        return Display.orderList.stream().filter(o -> o.getOrderStatus() == OrderStatus.COMPLETED).collect(Collectors.toList());
    }

    private double getSaleTotalPrice() {

        double saleTotalPrice = 0;
        for (Order currentOrder : Display.orderList) {
            saleTotalPrice += currentOrder.getBasketTotalPrice();
        }
        return saleTotalPrice;
    }

    // 3. 메뉴 및 상품 추가
    private void addMenuOrProduct() throws InterruptedException {

        System.out.println("\n어떤 항목을 추가하시겠습니까?");
        System.out.println("1.메뉴\t2.상품\t3.돌아가기");

        int num = Integer.parseInt(Display.scanner.nextLine());
        switch (num) {
            case 1 -> printAddMenu();
            case 2 -> printAddProduct();
            case 3 -> printAdminMenu();
            default -> {
                System.out.println("\n잘못된 입력입니다.");
                printAdminMenu();
            }
        }
    }

    private void printAddMenu() throws InterruptedException {

        System.out.println("\n메뉴 이름을 입력해 주세요.");
        String name = Display.scanner.nextLine();

        System.out.println("\n메뉴 설명을 입력해주세요.");
        String explain = Display.scanner.nextLine();

        Display.menuList.add(new Menu(name, explain));
        Display.products.put(name, new ArrayList<>());

        System.out.println("\n메뉴 생성 완료!");
        printAdminMenu();
    }

    private void printAddProduct() throws InterruptedException {

        System.out.println("\n메뉴를 선택해 주세요.");

        for (int i = 0; i < Display.menuList.size(); i++) {
            System.out.println(i + 1 + ". " + Display.menuList.get(i).getName());
        }
        int menuNum = Integer.parseInt(Display.scanner.nextLine());
        System.out.println("\n" + Display.menuList.get(menuNum - 1).getName() + " 이(가) 선택 되었습니다.");
        System.out.println("\n상품 이름을 입력해 주세요.");
        String name = Display.scanner.nextLine();

        System.out.println("\n상품 설명을 입력해주세요.");
        String explain = Display.scanner.nextLine();

        System.out.println("\n가격을 입력해주세요.");
        double price = Double.parseDouble(Display.scanner.nextLine());

        String menuName = Display.menuList.get(menuNum - 1).getName();
        List<Product> newList = Display.products.get(menuName);
        newList.add(new Product(name, explain, price));

        System.out.println("\n상품 생성 완료!");
        printAdminMenu();
    }

    // 4. 메뉴 및 상품 삭제
    private void deleteMenuOrProduct() throws InterruptedException {

        System.out.println("\n어떤 항목을 삭제하시겠습니까?");
        System.out.println("1. 메뉴 삭제\t2. 상품 삭제\t3. 돌아가기");
        int select = Integer.parseInt(Display.scanner.nextLine());
        switch (select) {
            case 1 -> printDeletionMenu();
            case 2 -> printDeletionProduct();
            case 3 -> printAdminMenu();
            default -> {
                System.out.println("잘못된 입력입니다.");
                printAdminMenu();
            }
        }
    }

    private void printDeletionMenu() throws InterruptedException {

        for (Menu value : Display.menuList) {
            System.out.println(
                    "ID : " + value.getId() + "\t | " + value.getName()
                            + "\t | " + value.getExplain());
        }
        System.out.println("삭제할 메뉴 ID: ");
        deleteMenu();
    }

    private void deleteMenu() throws InterruptedException {

        int delId = Integer.parseInt(Display.scanner.nextLine());
        boolean isRemoved = false;
        for (int i = 0; i < Display.menuList.size(); i++) {
            Menu menu = Display.menuList.get(i);
            if (menu.getId() == delId) {
                System.out.println(
                        "ID : " + menu.getId() + "\t | " + menu.getName()
                                + "\t | " + menu.getExplain() + " 메뉴가 삭제되었습니다.");
                Display.menuList.remove(i);
                Display.products.remove(menu.getName());
                isRemoved = true;
                printAdminMenu();
                break;
            }
        }
        if (!isRemoved) {
            System.out.println("잘못된 ID 입니다.");
            printAdminMenu();
        }
    }

    private void printDeletionProduct() throws InterruptedException {

        for (Menu menu : Display.menuList) {
            String menuName = menu.getName();
            List<Product> productList = Display.products.get(menuName);
            for (Product product : productList) {
                System.out.println(
                        "ID : " + product.getId() + "\t | " + product.getName()
                                + "\t | " + product.getPrice() + "\t | " + product.getExplain());
            }
        }
        System.out.println("삭제할 상품 ID: ");
        deleteProduct();
    }

    private void deleteProduct() throws InterruptedException {

        int delId = Integer.parseInt(Display.scanner.nextLine());
        boolean isRemoved = false;
        for (Menu menu : Display.menuList) {
            String menuName = menu.getName();
            List<Product> productList = Display.products.get(menuName);
            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);
                if (product.getId() == delId) {
                    System.out.println("ID : " + product.getId() + "\t | " + product.getName()
                            + "\t | " + product.getPrice() + "\t | " + product.getExplain()
                            + "상품이 삭제되었습니다.");
                    productList.remove(i);
                    isRemoved = true;
                    printAdminMenu();
                    break;
                }
            }
        }
        if (!isRemoved) {
            System.out.println("잘못된 ID 입니다.");
            printAdminMenu();
        }
    }

    // 5. 총 판매 금액 현황
    private void printSaleTotalPrice() throws InterruptedException {

        System.out.println("\n[ 총 판매 금액 현황 ]\n");
        System.out.println("현재까지 총 판매된 금액은 [ " + getSaleTotalPrice() + " ] 입니다.\n");
        System.out.println("1. ADMIN MENU로 돌아가기\t2. 메인으로 돌아가기\n");

        int mainSelect = Integer.parseInt(Display.scanner.nextLine());
        switch (mainSelect) {
            case 1 -> printAdminMenu();
            case 2 -> System.out.println("\n메인으로 돌아갑니다.\n");
            default -> {
                System.out.println("잘못된 값을 입력했습니다.");
                printSaleTotalPrice();
            }
        }
    }

    // 6. 총 판매 상품 현황
    private void printSaleTotalProduct() throws InterruptedException {

        System.out.println("\n[ 총 판매 상품 현황 ]\n");
        System.out.println("현재까지 총 판매된 상품은 아래와 같습니다.\n");

        for (Order currentOrder : Display.orderList) {
            for (Product product : currentOrder.getBasket()) {
                System.out.println(
                        "- " + product.getName() + "\t | " + product.getPrice());
            }
        }

        System.out.println("\n1. ADMIN MENU로 돌아가기\t2. 메인으로 돌아가기\n");
        int mainSelect = Integer.parseInt(Display.scanner.nextLine());
        switch (mainSelect) {
            case 1 -> printAdminMenu();
            case 2 -> System.out.println("\n메인으로 돌아갑니다.\n");
            default -> {
                System.out.println("잘못된 값을 입력했습니다.");
                printSaleTotalPrice();
            }
        }
    }

    // 7. 비밀번호 변경
    private void printChangeAdminPassword() throws InterruptedException {

        System.out.println("\n[ ADMIN 비밀번호 변경 ]\n");
        System.out.print("현재 비밀번호 입력: ");

        int inputPassword = Integer.parseInt(Display.scanner.nextLine());
        if (inputPassword == password) {
            System.out.println("\n[ ADMIN 비밀번호 변경 ]\n");
            System.out.print("변경할 비밀번호 입력: ");
            password = Integer.parseInt(Display.scanner.nextLine());
            printAdminMenu();
        } else {
            System.out.println();
            System.out.println("비밀번호를 틀렸습니다.");
            printChangeAdminPassword();
        }
    }

}
