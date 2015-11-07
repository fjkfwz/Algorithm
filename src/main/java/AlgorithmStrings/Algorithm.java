package AlgorithmStrings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Utiles.LinkNode;
import Utiles.LinklList;


/**
 * Created by jz on 2015/11/6.
 */
public class Algorithm {

    public static void main(String[] args) {
        LinklList linklList = new LinklList();
//        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            linklList.insertTofinal(i);
        }
        System.out.println("LinkedKist Datas:" + linklList.toString());
        linklList.deteteNodeFromPosition(9);
        System.out.println("LinkedKist Datas:" + linklList.toString());
        linklList.deteteNodeFromPosition(0);
        System.out.println("LinkedKist Datas:" + linklList.toString());
        linklList.deteteNodeFromPosition(2);
        System.out.println("LinkedKist Datas:" + linklList.toString());
//        java.lang.String string = scanner.next();
//        System.out.println("You Input:" + string);
//        string = String.valueOf(RotateStrings.LeftRotateString(string.toCharArray(), 3));
//        System.out.println("You Rotate Input:" + string);
    }

    /**
     * 问题描述：
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串
     * 的尾部，使得原字符串变成字符串“cdefab”。请写一个函数完成此功能，要求对长度为n的字符串操作的时间复杂度为 O(n)，空间
     * 复杂度为 O(1)。
     * <p/>
     * 思路：
     * 三步旋转法：(X^TY^T)^T=YX
     */
    public static class RotateStrings {
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public static char[] LeftRotateString(char[] str, int n) {
            char[] rotateLeft = ReverseString(str, 0, n - 1);
            char[] rotateRight = ReverseString(rotateLeft, n, str.length - 1);
            char[] result = ReverseString(rotateRight, 0, str.length - 1);
            return result;
        }

        public static char[] ReverseString(char[] str, int start, int end) {
            while (start < end) {
                char tmp = str[start];
                str[start] = str[end];
                str[end] = tmp;
                start++;
                end--;
            }
            return str;
        }
    }

    /**
     * Rotate LinkedList Class
     */
    public static class RotateLinkList {
        public static LinkNode rotateLinkList(LinkNode head, int n) {
            LinkNode leftEnd = head;
            int i = 0;
            while (i++ < n) {
                leftEnd = leftEnd.getNext(); //Get Life LinkList End;
            }
            LinkNode leftRotate = rotateLink(head, leftEnd);
            LinkNode rightRotate = rotateLink(leftEnd, null);
            head.setNext(rightRotate);
            return leftRotate;
        }

        public static LinkNode rotateLink(LinkNode start, LinkNode end) {
            LinkNode pre = null;
            LinkNode cur = start;
            LinkNode next = start.getNext();
            while (cur != end) {
                next = cur.getNext();
                cur.setNext(pre);
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }
}


