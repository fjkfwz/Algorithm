package Utiles;

/**
 * Created by jz on 2015/11/6.
 */
public class LinkNode {
    private int value;
    private LinkNode next;

    public LinkNode(int value, LinkNode next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
