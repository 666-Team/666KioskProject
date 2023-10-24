public class Menu {

    static int menuId = 1;

    // 필드
    private final int id;
    private final String name; // 이름
    private final String explain; // 설명

    // 생성자
    public Menu(String name, String explain) {
        this.id = menuId++;
        this.name = name;
        this.explain = explain;
    }

    // 메소드
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExplain() {
        return explain;
    }

    // 하위 객체

}
