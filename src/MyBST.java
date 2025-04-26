import java.util.Iterator;

public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST<K, V>.Node> {
    private Node root;
    private int size;

    public class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public MyBST() {
        root = null;
        size = 0;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        return x;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Node> iterator() {
        return new MyIterator(root);
    }

    private class MyIterator implements Iterator<Node> {
        private Object[] stack;
        private int top;

        public MyIterator(Node root) {
            stack = new Object[1000]; // Object[], потому что Node[] нельзя создать
            top = -1;
            pushLeft(root);
        }

        private void pushLeft(Node x) {
            while (x != null) {
                stack[++top] = x;
                x = x.left;
            }
        }

        @Override
        public boolean hasNext() {
            return top != -1;
        }

        @Override
        public Node next() {
            Node current = (Node) stack[top--];
            if (current.right != null) {
                pushLeft(current.right);
            }
            return current;
        }
    }
}


