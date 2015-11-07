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

    /**
     * Inner Class Node Use To Description Unit Of LinkedList
     *
     * @param <T>
     */
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

    /**
     * Insert Node To Head;
     *
     * @param point
     */
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

    /**
     * Insert Node To Tail;
     *
     * @param point
     */
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

    /**
     * Insert Node To Every Position;
     * Position Scale From 1 To LinkedList Count;
     *
     * @param position
     * @param point
     */
    public void inserToPosition(int position, T point) {
        position = position - 1;
        if (position <= getLinkedListCount() && position > -1) {
            if (position == getLinkedListCount()) {
                insertTofinal(point);
            } else if (position == 0) {
                insertToHead(point);
            } else {
                Node insertNode = new Node(point);
                Node preNode = getNodeByIndex(position);
                Node next = getNodeByIndex(position + 1);
                preNode.setNext(insertNode);
                insertNode.setNext(next);
            }
        } else {
            System.out.println("Insert To Position Error;");
        }
    }

    /**
     * Get Tail Node
     *
     * @return
     */
    public Node getFinalNode() {
        Node cur = head;
        while (cur.getNext() != null) {
            cur = cur.getNext();
        }
        return tail = cur;
    }

    /**
     * Get LinkedList Count;
     *
     * @return
     */
    public int getLinkedListCount() {
        Node cur = head;
        int i = 0;
        while (cur != null) {
            cur = cur.getNext();
            i += 1;
        }
        return i + 1;
    }

    /**
     * Override To Output LinkedList Data;
     *
     * @return
     */
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

    /**
     * Rotate LinkedList
     *
     * @param n
     */
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

    /**
     * delete Node From Every Position;
     * Position Scale From 1 To LinkedList Count;
     * When Position equal 0, Delete Head Node;
     * When Position equal LinkedList Count, Delete Tail Node;
     * @param position
     */
    public void deteteNodeFromPosition(int position) {
        position = position - 1;
        if (position <= getLinkedListCount() && position >= 0) {

            if (position == 0) {
                Node next = getNodeByIndex(position + 1);
                head.setNext(null);
                head = next;
            } else if (position == getLinkedListCount()) {
                Node pre = getNodeByIndex(position - 1);
                pre.setNext(null);
                tail = pre;
            } else {
                Node pre = getNodeByIndex(position - 1);
                Node next = getNodeByIndex(position + 1);
                pre.setNext(next);
            }
        } else {
            System.out.println("Delete From Position Error");
        }
    }

    /**
     * Get Node By Position
     * @param position
     * @return
     */
    public Node getNodeByIndex(int position) {
        Node node = head;
        for (int i = 0; i < position; i++) {
            node = node.getNext();
        }
        return node;
    }
}
