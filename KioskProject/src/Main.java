public class Main {
    public static void main(String[] args) throws InterruptedException {
        Display display = new Display();
        // 메인 화면을 불러오는 메소드
        boolean runner = false;

        while (!runner) {
            try {
                display.printMain();
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 값을 입력했습니다. 메인으로 돌아갑니다.");
            }
        }
    }
}
