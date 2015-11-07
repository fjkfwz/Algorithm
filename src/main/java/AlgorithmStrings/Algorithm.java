package AlgorithmStrings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import Utiles.LinkNode;
import Utiles.LinklList;


/**
 * Created by jz on 2015/11/6.
 */
public class Algorithm {

    public static void main(String[] args) {
        LinklList linklList = new LinklList();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please Input String:");
            String str = scanner.nextLine();
            int caseNum = 0;
            if (str.equals("END")) {
                break;
            } else {
                caseNum++;
                System.out.println("Case " + caseNum + ": " + getLongestPalindrome(str));

            }
        }
//        System.out.println("Plaese Input A Sentence:");
//        for (int i = 0; i < 10; i++) {
//            linklList.insertTofinal(i);
//        }
//        System.out.println("LinkedKist Datas:" + linklList.toString());
//        linklList.deteteNodeFromPosition(9);
//        System.out.println("LinkedKist Datas:" + linklList.toString());
//        linklList.deteteNodeFromPosition(0);
//        System.out.println("LinkedKist Datas:" + linklList.toString());
//        linklList.deteteNodeFromPosition(2);
//        System.out.println("LinkedKist Datas:" + linklList.toString());
//        java.lang.String string = scanner.next();
//        String string1 = "AFNKKKMKD";
//        System.out.println("You Input:" + string1 + ":" + checkIsPalindrome(string1.toCharArray()));
//        String string2 = "ABCDDCBA";
//        System.out.println("You Input:" + string2 + ":" + checkIsPalindrome(string2.toCharArray()));
//        String string3 = "AFNWERW";
//        System.out.println("You Input:" + string3 + ":" + hashCheck(string1.toCharArray(), string3.toCharArray()));
//        string = String.valueOf(rotateSentence.rotateSentences(string.toCharArray()));
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

    public static class RotateLinkList {

        /**
         * 问题描述：
         * 链表翻转。给出一个链表和一个数k，比如，链表为1→2→3→4→5→6，k=2，则翻转后2→1→6→5→4→3，若k=3，翻转后
         * 3→2→1→6→5→4，若k=4，翻转后4→3→2→1→6→5，用程序实现。
         * <p/>
         * 思路：
         * 使用反转链表的思路，将链表的前部分反转，然后将链表的后部分反转，最后将前部分链表的尾节点指向后部分链表的头节点。
         *
         * @param head 链表的头节点
         * @param n    将前n个节点反转，剩下的节点反转
         * @return 翻转后链表的头节点
         */
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


    public static class rotateSentence {

        /**
         * Rotate Sentence
         * @param sentences
         * @return
         */
        public static char[] rotateSentences(char[] sentences) {
            int start = 0;
            int end = 0;
            int i = 0;
            while (i < sentences.length) {
                while (i < sentences.length && sentences[i] != ' ') {
                    i++;
                }
                end = i - 1;
                sentences = RotateStrings.ReverseString(sentences, start, end);
                start = (++i);
            }
            sentences = RotateStrings.ReverseString(sentences, 0, sentences.length - 1);
            return sentences;
        }
    }

    /**
     * 问题描述：
     * 给定两个分别由字母组成的字符串A和字符串B，字符串B的长度比字符串A短。请问，如何最快地判断字符串B
     * 中所有字母是否都在字符串A里？为了简单起见，我们规定输入的字符串只包含大写英文字母。比如String A：ABCD，String B：BAD，
     * 返回true；string A：ABCD，string B：BCE，返回false；String A：ABCD，String B：AA，返回true。
     * <p/>
     * 如果可以使用Java中的数据结构，HashMap和Set可以很方便地解决问题；如果不能，我们可以构造一个“签名”，将每一个字
     * 符映射为整数(范围：0到26)，然后遍历A中的每一个字符，将32位整数的对应位置1(整数初始为0)，最后遍历B中的每一个字符，判断
     * 每一个字符代表的整数在整数中是否已置位。时间复杂度O(n)，空间复杂度O(1)，思路四为最优算法。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean hashCheck(char[] s1, char[] s2) {
        int mask = 0;
        for (char c : s1) {
            int b = 0;
            mask = mask | (1 << (c - 'A'));
        }
        for (char c : s2) {
            if ((mask & (1 << (c - 'A'))) == 0) {
                return false;
            }
        }
        return true;
    }



    /**
     * Check String is Palindrome
     * @param str
     * @return
     */
    public static boolean checkIsPalindrome(char[] str) {
        if (str == null) {
            return false;
        }
        int length = str.length;
        int startFlage = 0;
        int endFlage = length - 1;

        while (endFlage > startFlage) {
            if (str[startFlage] != str[endFlage]) {
                return false;
            }
            startFlage++;
            endFlage--;

        }
        return true;
    }


    /**
     * Get the Longest Palindrome From String;
     * Manancher Algorithm;
     * @param string
     * @return
     */
    public static String getLongestPalindrome(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('#');
        for (int i = 0; i < string.length(); i++) {
            stringBuilder.append(string.charAt(i));
            stringBuilder.append('#');
        }
        int[] rad = new int[stringBuilder.length()];
        int right = -1;
        int id = -1;
        int maxi = 0;
        int max = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            rad[i] = 1;
            if (i < right) {
                rad[i] = Math.min(rad[2 * id - i], right - i);
            }
            while (i - rad[i] >= 0 && i + rad[i] < stringBuilder.length() && (stringBuilder.charAt(i + rad[i]) == stringBuilder.charAt(i - rad[i]))) {
                rad[i]++;
            }
            if (i + rad[i] - 1 > right) {
                right = i + rad[i] - 1;
                id = i;
            }
            int maxtmp = Math.max(rad[i], max);
            if (maxtmp > max) {
                maxi = i;
                max = maxtmp;
            }
        }
        if (maxi % 2 == 1) {
            string = string.substring((maxi - max + 1) / 2, (maxi + max) / 2);
        } else {
            string = string.substring((maxi - max) / 2, (maxi + max) / 2);
        }
        return string;
    }

    
}


