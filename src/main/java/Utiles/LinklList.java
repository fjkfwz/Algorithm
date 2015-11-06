package Utiles;

/**
 * Created by jz on 2015/11/6.
 */
public class LinklList<T> {


    private Node<T> head;
    private Node<T> tail;

    public LinklList() {
        head = tail = null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;

        }

        public Node<T> getNext() {

            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }


    public void insertToHead(T point) {
        if (head == null) {
            head = new Node<>(point);
            if (tail == null) {
                head.setNext(null);
            }
        } else {
            Node insertNode = new Node(point);
            insertNode.setNext(head);
            head = insertNode;
        }
    }

    public void insertTofinal(T point) {
        if (head == null) {
            insertToHead(point);
        } else {
            Node insertNode = new Node(point);
            insertNode.setNext(null);
            getFinalNode().setNext(insertNode);
            tail = insertNode;
        }
    }

    public void inserToPosition(int position, T point) {
        if (position == getLinkedListCount()) {
            System.out.println(">>>>>>>Insert To Final>>>>>");
            insertTofinal(point);
        } else if (position == 0) {
            System.out.println(">>>>>>>Insert To Head>>>>>");
            insertToHead(point);
        } else if (position < getLinkedListCount() && position > -1) {
            System.out.println(">>>>>Insert To Position>>>>");
            Node insertNode = new Node(point);
            Node preNode = head;
            for (int i = -1; i < position - 1; i++) {
                preNode = preNode.getNext();
            }
            Node next = head;
            for (int i = -1; i < position; i++) {
                next = next.getNext();
            }
            preNode.setNext(insertNode);
            insertNode.setNext(next);
        }
    }


    public Node getFinalNode() {
        Node cur = head;
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        return tail = cur;
    }

    public int getLinkedListCount() {
        Node cur = head;
        int i = 0;
        while (cur != null) {
            cur = cur.getNext();
            i += 1;
        }
        return i + 1;
    }


    @Override
    public String toString() {
        Node cur = head;
        String string = "";
        do {
            string += cur.getData().toString();
            cur = cur.getNext();
        } while (cur != null);
        return string;
    }

    public void rotateLinkedList(int n) {
        if (n <= getLinkedListCount() - 1 && n > -1) {
            rotateLinkList(head, n);
        } else {
            System.out.println("N must Less Than ");
        }
    }

    private void rotateLinkList(Node mhead, int n) {
        Node leftEnd = mhead;
        int i = 0;
        while (i++ < n) {
            leftEnd = leftEnd.getNext(); //Get Life LinkList End;
        }
        Node leftRotate = rotateLink(mhead, leftEnd);
        Node rightRotate = rotateLink(leftEnd, null);
        mhead.setNext(rightRotate);
        head = leftRotate;
    }

    private Node rotateLink(Node start, Node end) {
        Node pre = null;
        Node cur = start;
        if (start.getNext() != null) {
            Node next = start.getNext();
            while (cur != end) {
                next = cur.getNext();
                cur.setNext(pre);
                pre = cur;
                cur = next;
            }

        } else {
            pre = start;
        }
        return pre;
    }

}
