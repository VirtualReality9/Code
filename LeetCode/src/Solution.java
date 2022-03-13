import java.math.BigDecimal;
import java.util.*;

public class Solution {

    /**
     * 无重复长度的最长子串
     * @param s:输入字符串
     * @return 最长子串长度
     */
    public int lengthOfLongestSubstring(String s) {
        /**
         滑动窗口 */
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int start = -1, max = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {
                // start = map.get(ch);
                start = Math.max(map.get(ch), start);
            }
            max = Math.max(max, end - start);
            map.put(ch, end);
        }
        return max;
    }

    /**
     * 最长回文子串：动态规划
     * @param s：字符串
     * @return String：最长回文子串
     */
    /*
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        // L代表子串的长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
    */

    /**
     * 最长回文子串：中心扩展算法
     * @param s： 字符串
     * @return String：最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    /**
     * 最小的k个数
     * @param arr：数组
     * @param k：k个数
     * @return int[]：前k最小数组成的新数组
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = getMiddle(arr, low, high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid + 1, high);
        }
    }

    public int getMiddle(int[] arr, int low, int high) {
        if (high < 0 && low > arr.length) return 0;
        int temp = arr[low];
        while (low < high) {
            while (arr[high] >= temp && high > low) high--;
            arr[low] = arr[high];
            while (arr[low] <= temp && low < high) low++;
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }

    /**
     * 大数阶乘
     */
    public BigDecimal getN() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入N：");
        int n = scanner.nextInt();
        BigDecimal res = jieCheng(n);
        scanner.close();
        return res;
    }

    public BigDecimal jieCheng(int n) {
        BigDecimal x = BigDecimal.valueOf(n);
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(2);
        BigDecimal res = a;
        while (x.compareTo(a) > 0) {
            res = res.multiply(x.multiply(x.subtract(a)));
            x = x.subtract(b);
        }
        return res;
    }


    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        Scanner scanner = new Scanner(System.in);
//        /*System.out.println("请输入待确定最长长度的字符串：");
//        String s = scanner.nextLine();
//        int res = solution.lengthOfLongestSubstring(s);
//        System.out.println("无重复长度最长子串：" + res);*/
//
//        /*System.out.println("请输入待确定最长回文子串的字符串：");
//        String s = scanner.nextLine();
//        String res = solution.longestPalindrome(s);
//        System.out.println("最长回文子串：" + res);*/
//
//        int[] arr = new int[] {0,0,1,2,4,2,2,3,1,4};
//        int k = 8;
//        int[] res = solution.getLeastNumbers(arr, k);
//        System.out.println("原数组前k个数：");
//        for (int n : res) {
//            System.out.print(n + " ");
//        }
//        BigDecimal res = solution.getN();
//        System.out.println("阶乘为：" + res);
        String[] res = solution.permutation("abc");
        for (String s : res) {
            System.out.println(s + " ");
        }
    }
}
