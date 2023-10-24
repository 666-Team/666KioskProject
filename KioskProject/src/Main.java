public class Main {
    public static void main(String[] args) throws InterruptedException {
        Display display = new Display();
        // 메인 화면을 불러오는 메소드
        boolean runner = false;

        while (!runner) {
            display.printMain();
        }
    }
}
