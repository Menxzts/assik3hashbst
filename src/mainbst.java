public class mainbst {
    public static void main(String[] args) {
        MyBST<Integer, String> tree = new MyBST<>();

        tree.put(10, "Ten");
        tree.put(5, "Five");
        tree.put(15, "Fifteen");
        tree.put(3, "Three");
        tree.put(7, "Seven");

        System.out.println("Tree size: " + tree.size());

        for (var elem : tree) {
            System.out.println("Key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
