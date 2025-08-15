class Parent {
    final private int x;

    // Constructor
    public Parent(int x) {
        this.x = x;
    }

    // getX method
    public int getX() {
        return x;
    }
}

class Child extends Parent {
    final private int y;

    // Constructor
    public Child(int x, int y) {
        super(x);
        this.y = y;
    }

    // getY method
    public int getY() {
        return y;
    }
}

class Stranger {
    final private int x;
    final private int y;

    // Constructor
    public Stranger(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getX method
    public int getX() {
        return x;
    }

    // getY method
    public int getY() {
        return y;
    }
}

public class Main {
    // * 継承関係のパターン * //
    static void inheritance(Child human) {
        System.out.println("x: " + human.getX());
        System.out.println("y: " + human.getY());
    }

    public static void main(String[] args) {
        Child child = new Child(0, 0);
        // 当たり前だが、以下は実行可能
        inheritance(child);

        Stranger stranger = new Stranger(1, 1);
        // 以下はエラーになる。
        // inheritance(stranger);
    }
}
