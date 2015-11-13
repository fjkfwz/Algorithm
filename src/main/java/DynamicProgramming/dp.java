package DynamicProgramming;

/**
 * Created by jz on 2015/11/13.
 */

public class dp {
    public static void main(String args[]) {
        int[][] array = new int[][]{
//                {1, 0, 1, 0, 0},
//                {1, 0, 1, 1, 1},
//                {1, 1, 1, 1, 1},
//                {1, 0, 0, 1, 0}};
                {2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        System.out.println(triangle(array));
    }

    public static int maximumArrary(int[] array, int n) {
        int currsum = 0, maxsum = 0;
        for (int i = 0; i < n; i++) {
            if (currsum > 0) {
                currsum += array[i];
            } else {
                currsum = array[i];
            }
            if (currsum > maxsum) {
                maxsum = currsum;
            }
        }
        return maxsum;
    }

    /**
     * Maximal Square Total Accepted: 1312 Total Submissions: 6388
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
     * For example, given the following matrix:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * Return 4.
     */
    public static int maximumSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] res = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            res[i][0] = matrix[i][0];
            ans = Math.max(res[i][0], ans);
            for (int j = 1; j < m; j++) {
                if (i > 0) {
                    if (matrix[i][j] > 0) {
                        res[i][j] = Math.min(res[i][j - 1], Math.min(res[i - 1][j - 1], res[i - 1][j])) + 1;
                    } else {
                        res[i][j] = 0;
                    }
                } else {
                    res[i][j] = matrix[i][j];
                }
                ans = Math.max(res[i][j], ans);
            }
        }
        return ans * ans;
    }

    public static int triangle(int[][] triangle) {
        if (triangle == null || triangle.length == 0) {
            return 0;
        }
        if (triangle[0] == null || triangle[0].length == 0) {
            return 0;
        }
        int n = triangle.length;
        int[][] sumtriangle = new int[n][n];
        sumtriangle[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            sumtriangle[i][0] = sumtriangle[i - 1][0] + triangle[i][0];
            sumtriangle[i][i] = sumtriangle[i - 1][i - 1] + triangle[i][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                sumtriangle[i][j] = Math.min(sumtriangle[i - 1][j], sumtriangle[i - 1][j - 1]) + triangle[i][j];
            }
        }
        int min = sumtriangle[n - 1][0];
        for (int i = 0; i < n; i++) {
            min = Math.min(sumtriangle[n - 1][i], min);
        }
        return min;
    }

    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path. Note: You can only move either down or right at any point in time.
     * @param gird
     * @return
     */
    public int minPathSum(int[][] gird){
        int n = gird.length,m=gird[0].length;
        int[][] pathmap = new int[n][m];
        pathmap[0][0] = gird[0][0];
        for (int i = 1; i < n; i++) {
            pathmap[i][0] = pathmap[i-1][0] + gird[i][0];
        }
        for (int i = 0; i < m; i++) {
            pathmap[0][i] = pathmap[0][i-1]-gird[0][i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pathmap[i][j] = Math.max(pathmap[i-1][j],pathmap[i][j-1]) + gird[i][j];
            }
        }
        return pathmap[n][m];
    }
}