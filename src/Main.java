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

public class Main {
    // * 情報欠損するパターン1 * //
    // Parent型の変数に代入されたChildクラスのインスタンスを引数に取れる
    // しかし、ParentクラスにはgetY()メソッドがないため、エラーになる
    static void printXY(Parent human) {
        System.out.println("x: " + human.getX());
        // 以下はエラーになる。
        // * これが情報の欠損に値する。 * //
        // System.out.println("y: " + human.getY());
    }

    // * 情報欠損するパターン2 * //
    static Parent throwClass(Parent human) {
        return human;
    }

    // 以下のようにすれば良いのでは？と思う人もいるかもしれないが、
    // OCamlのthrowClassと同じ条件？になるようにするためには、
    // Parent classも返せる必要があるので却下した。
    static Child throwChild(Child human) {
        return human;
    }

    public static void main(String[] args) {
        {
            // *** 情報欠損するパターン1 ***//
            // * Parentクラスのインスタンスを生成する場合 * //
            Parent parent = new Parent(0);
            // 以下は当然、情報の欠損は発生していない
            printXY(parent);

            // * Parentクラスと継承の関係になる場合 (Child class) * //
            Child child = new Child(1, 1);
            // 以下は実行可能
            System.out.println("x: " + child.getX());
            System.out.println("y: " + child.getY());

            // * ChildクラスはParentクラスを継承しているので、Parent型の変数に代入可能 * //
            // Child classのインスタンスを引数として与えたにも関わらず、`int getY()` メソッドが呼び出せない
            // これがサブタイプ多相の情報の欠損である
            printXY(child);
        }

        {
            // *** 情報欠損するパターン2 ***//
            // * Parentクラスのインスタンスを生成する場合 * //
            Parent parent = new Parent(0);
            // 以下は当然、情報の欠損は発生していない
            Parent returnedParent = throwClass(parent);
            System.out.println("returnedParent.x: " + returnedParent.getX());
            // 当たり前だが、以下は実行不可
            // Child returnedParent2 = throwChild(parent);
            // System.out.println("returnedParent2.x: " + returnedParent2.getX());
            // System.out.println("returnedParent2.y: " + returnedParent2.getY());

            // * Parentクラスと継承の関係になる場合 (Child class) * //
            Child child = new Child(1, 1);
            // 以下は実行可能
            System.out.println("x: " + child.getX());
            System.out.println("y: " + child.getY());

            // * ChildクラスはParentクラスを継承しているので、Parent型の変数に代入可能 * //
            // Child classのインスタンスを引数として与えたにも関わらず、`int getY()` メソッドが呼び出せない
            // これがサブタイプ多相の情報の欠損である
            Parent returnedChild = throwClass(child);
            System.out.println("returnedChild.x: " + returnedChild.getX());
            // 以下はエラーになる。
            // System.out.println("returnedChild.y: " + returnedChild.getY());
            // 当たり前だが、以下は実行可能
            Child returnedChild2 = throwChild(child);
            System.out.println("returnedChild2.x: " + returnedChild2.getX());
            System.out.println("returnedChild2.y: " + returnedChild2.getY());
        }
    }
}
