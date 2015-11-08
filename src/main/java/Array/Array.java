package Array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jz on 2015/11/8.
 */
public class Array {

    public static void main(String args[]) {
        int[] array = {5, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 10};
//        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(findFibonacci(1));
    }

    /**
     * Remove Element
     * Given an array and a value, remove all instances of that > value in place and return the new length.
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     *
     * @param array
     * @param elem
     * @return
     */
    public static int removeElement(int[] array, int elem) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int pointer = array.length - 1;
        int i = 0;
        while (i <= pointer) {
            if (array[i] == elem) {
                array[i] = array[pointer];
                pointer--;
            } else {
                i++;
            }
        }
        return pointer + 1;
    }

    /**
     * Remove Duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that > each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * For example, Given input array A = [1,1,2],
     * Your function should return length = 2, and A is now [1,2].
     *
     * @param array
     * @return
     */
    public static int removeDuplicates(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int pointer = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[pointer] != array[i]) {
                array[++pointer] = array[i];
            }
            System.out.println(array.toString());
        }
        return pointer + 1;
    }

    /**
     * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
     * For example, Given sorted array A = [1,1,1,2,2,3],
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     *
     * @param array
     * @return
     */
    public static int removeDuplicatesTwice(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int pointer = 0;
        int count = 1;
        for (int i = 0; i < array.length; i++) {
            if (array[pointer] == array[i]) {
                count++;
                if (count > 2) {
                    array[++pointer] = array[i];
                }
            } else {
                count = 1;
                pointer++;
            }
        }
        return pointer + 1;
    }

    /**
     * Plus One
     * Given a non-negative number represented as an array of digits, plus one to the number.
     * The digits are stored such that the most significant digit is at the head of the list.
     *
     * @param digits
     * @return
     */

    public static int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0 && carry > 0; i--) {
            digits[i] = digits[i] + carry;
            carry = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        if (carry == 0) {
            return digits;
        }
        int[] exdigits = new int[digits.length + 1];
        exdigits[0] = 1;
        for (int i = 0; i < digits.length - 1; i++) {
            exdigits[i + 1] = digits[i];
        }
        return exdigits;
    }

    /**
     * Given numRows, generate the first numRows of Pascal's triangle. For example, given numRows = 5, Return [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
     *
     * @param numRows
     * @return
     */
    public static ArrayList<ArrayList<Integer>> pascalTriangle(int numRows) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>(numRows);
        if (numRows < 1) {
            return triangle;
        }
        ArrayList<Integer> firstArrary = new ArrayList<Integer>(1);
        firstArrary.add(0, 1);
        triangle.add(firstArrary);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> rowArrary = new ArrayList<Integer>(i + 1);
            rowArrary.add(1);
            ArrayList<Integer> pre = triangle.get(i - 1);
            for (int j = 1; j < i; j++) {
                rowArrary.add(pre.get(j - 1) + pre.get(j));
            }
            rowArrary.add(1);
            triangle.add(i, rowArrary);
        }
        return triangle;
    }

    /**
     * Pascal's Triangle II
     * Given an index k, return the kth row of the Pascal's triangle.
     * For example, given k = 3, Return [1,3,3,1].
     *
     * @param numRows
     * @return
     */
    public static ArrayList<Integer> getPascalTriangleRow(int numRows) {
        ArrayList<Integer> rowArrary = new ArrayList<Integer>();
        if (numRows < 1) {
            return rowArrary;
        }
        rowArrary.add(0, 1);
        for (int i = 1; i < numRows; i++) {
            rowArrary.add(1);
            for (int j = i - 1; j > 0; j--) {
                rowArrary.set(j, rowArrary.get(j - 1) + rowArrary.get(j));
            }
        }
        return rowArrary;
    }

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted from left to right. The first integer of each row is greater than the last integer of the previous row. For example,
     * Consider the following matrix:
     * [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int end = matrix.length - 1;
        if (matrix[end][matrix[end].length - 1] < target) {
            return false;
        }

        if (matrix[0][matrix[0].length - 1] >= target) {
            System.out.println("OK");
            for (int i = 0; i < matrix[0].length; i++) {
                if (target == matrix[0][i]) {
                    return true;
                }
                return false;
            }
        }

        for (int i = matrix.length - 1; i > -1; i--) {
            if (matrix[i][matrix[i].length - 1] >= target && matrix[i - 1][matrix[i - 1].length - 1] < target) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Search for a Range
     * Given a sorted array of integers, find the starting and ending position of a given target value.
     * If the target is not found in the array, return [-1, -1].
     * For example,
     * Given [5, 7, 7, 8, 8, 10] and target value 8,
     * return [3, 4].
     *
     * @param array
     * @param target
     * @return
     */
    public static int[] searchRange(int[] array, int target) {
        int[] bound = new int[]{-1, -1};
        if (array == null || array.length == 0) {
            return bound;
        }
        int prepoint = 0;
        int endpoint = array.length - 1;
        int midpoint;
        while (prepoint + 1 < endpoint) {
            midpoint = prepoint + (endpoint - prepoint) / 2;
            if (array[midpoint] == target) {
                endpoint = midpoint;
            } else if (array[midpoint] < target) {
                prepoint = midpoint;
            } else {
                endpoint = midpoint;
            }
        }
        if (array[prepoint] == target) {
            bound[0] = prepoint;
        } else if (array[endpoint] == target) {
            bound[0] = endpoint;
        } else {
            bound[0] = bound[1] = -1;
        }

        prepoint = 0;
        endpoint = array.length - 1;
        while (prepoint + 1 < endpoint) {
            midpoint = prepoint + (endpoint - prepoint) / 2;
            if (array[midpoint] == target) {
                prepoint = midpoint;
            } else if (array[midpoint] > target) {
                endpoint = midpoint;
            } else {
                prepoint = midpoint;
            }
        }
        if (array[endpoint] == target) {
            bound[1] = endpoint;
        } else if (array[prepoint] == target) {
            bound[1] = prepoint;
        } else {
            bound[0] = bound[1] = -1;
        }

        System.out.print(Arrays.toString(bound));
        return bound;
    }

    /**
     * Fibonacci Number
     * @param n
     * @return
     */
    public static int findFibonacci(int n) {
        int i = 1;
        int j = 0;
        int x = 0;
        if (n == 1) {
            return j;
        }
        if (n == 2) {
            return i;
        }
        for (int z = 2; z < n; z++) {
            x = i + j;
            j = i;
            i = x;
        }
        return x;
    }
}

