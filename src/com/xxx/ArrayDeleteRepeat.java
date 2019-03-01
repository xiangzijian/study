package com.xxx;

import java.util.*;

public class ArrayDeleteRepeat {


    /**
     * 从排序数组中删除重复项
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        int b = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[b]) {
                nums[++b] = nums[i];
            }
        }
        return b + 1;
    }

    /**
     * 买卖股票的最佳时机 II
     */
    public static int maxProfit(int[] prices) {
        int sum = 0;
        int before = prices[0];
        for (int i : prices) {
            if (i > before) {
                sum = sum + i - before;
                before = i;
            } else {
                before = i;
                continue;
            }
        }
        return sum;
    }

    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
     */
    public static void rotate(int[] nums, int k) {
        int c = 0;
        int a = 0;
        k = k % nums.length;
        for (int i = 0; i < k; i++) {
            for (int j = a; j < nums.length; j += k) {
                c = nums[j];
                if (j < k) {
                    nums[j] = nums[nums.length + j - k];
                    a = c;
                    continue;
                }

            }
        }
    }

    /**
     * 存在重复
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
     */
    public boolean containsDuplicate(int[] nums) {
        Set set = new HashSet();
        for (int i : nums) {
            set.add(i);
        }
        return set.size() != nums.length;
    }

    public static boolean containsDuplicatea(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) break;
                else if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    /**
     * 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     */
    public static int singleNumber(int[] nums) {
        int p = 0;
        for (int i : nums) {
            p ^= i;
        }
        return p;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length <= 0 || nums2.length <= 0) {
            return new int[]{};
        }
        int c = 0;
        List<Integer> list = new ArrayList<>();
        for (int i : nums1) {
            for (int j = c; j < nums2.length; j++) {
                if (i == nums2[j]) {
                    list.add(i);
                    nums2[j] = nums2[c++];
                    break;
                }
            }
        }
        int[] nums = new int[list.size()];
        int cc = 0;
        for (Integer i : list) {
            nums[cc] = i;
            cc++;
        }
        return nums;
    }

    /**
     * 加一
     */
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return digits;
        }
        for (int i = 1; i <= digits.length; i++) {
            digits[digits.length - i] += 1;
            if (digits[digits.length - i] <= 9) {
                return digits;
            }
            digits[digits.length - i] = 0;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public static int[] plusOne1(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;

            }
            digits[i] = 0;
        }
        int[] result = new int[digits.length + 1];
        //初始数组的值默认为0
        result[0] = 1;
        return result;
    }

    /***
     * 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * */
    public static void moveZeroes(int[] nums) {
        int count = 0;
        int a = -1;
        int b = -1;
        if (nums.length == 1) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (b == -1) {
                    b = i + 1;
                    count = 1;
                } else {
                    a = b;
                    b = i + 1;
                    for (int j = a; j < b; j++) {
                        nums[j - count] = nums[j];
                    }
                    count++;
                }
            }
        }
        if (b == -1) {
            return;
        }
        for (int i = b; i < nums.length; i++) {
            nums[i - count] = nums[i];

        }
        for (int i = 0; i < count; i++) {
            nums[nums.length - 1 - i] = 0;
        }

    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     */

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int c = target - nums[i];
            if (hashMap.get(nums[i]) != null) {
                int[] result = {hashMap.get(nums[i]), i};
                return result;
            }
            hashMap.put(c, i);
        }
        return null;
    }

    /**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set = new HashSet<>();
        if (!checkRow(board, set)) {
            return false;
        }
        if (!checkLie(board, set)) {
            return false;
        }
        return false;
    }

    /**
     * 判断9宫格
     */
    public boolean check(char[][] board, Set<Character> set) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                set.add(board[i * 3][j * 3]);
                set.add(board[i * 3][j * 3 + 1]);
                set.add(board[i * 3][j * 3 + 2]);
                set.add(board[i * 3 + 1][j * 3]);
                set.add(board[i * 3 + 1][j * 3 + 1]);
                set.add(board[i * 3 + 1][j * 3 + 2]);
                set.add(board[i * 3 + 2][j * 3]);
                set.add(board[i * 3 + 2][j * 3 + 1]);
                set.add(board[i * 3 + 2][j * 3 + 2]);
                if (set.contains(board[i][j])) {
                    return false;
                } else {
                    set.add(board[i][j]);
                }
            }
        }
        return true;
    }

    /**
     * 判断一列
     */
    public boolean checkLie(char[][] board, Set<Character> set) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (set.contains(board[j][i])) {
                    return false;
                } else {
                    set.add(board[j][i]);
                }
            }
            set.clear();
        }
        return false;
    }

    /**
     * 判断一行
     */
    public boolean checkRow(char[][] board, Set<Character> set) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (set.contains(board[i][j])) {
                    return false;
                } else {
                    set.add(board[i][j]);
                }
            }
            set.clear();
        }
        return false;
    }

    /**
     * 984. 不含 AAA 或 BBB 的字符串
     */
    public static String strWithout3a3b(int A, int B) {
        int a = A > B ? A : B;
        int b = A > B ? B : A;
        int c = b - (a - 1) / 2;
        String max = A > B ? "a" : "b";
        String min = A > B ? "b" : "a";
        StringBuffer stringBuffer = new StringBuffer();
        int d;
        if (a % 2 == 0) {
            stringBuffer.append(max + max);
            d = 2;
        } else {
            stringBuffer.append(max);
            d = 1;
        }
        for (int i = 0; i < (A - d + B) / 3; i++) {
            if (c > 0) {
                stringBuffer.append(min + min);
                c--;
            } else {
                stringBuffer.append(min);
            }
            stringBuffer.append(max + max);
        }
        int e = (A - d + B) % 3;
        for (int i = 0; i < e; i++) {
            stringBuffer.append(min);
        }
        return stringBuffer.toString();
    }

    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * <p>
     * 将图像顺时针旋转 90 度。
     */
    public static void rotate(int[][] matrix) {
        int size = matrix.length;
        int index = 0;
        int c = matrix.length;
        while (c >= 2) {
            for (int i = 0; (i + index) < size - 1; i++) {
                int a = matrix[index][i + index];
                matrix[index][i + index] = matrix[size - 1 - i][index];
                matrix[size - 1 - i][index] = matrix[size - 1][size - 1 - i];
                matrix[size - 1][size - 1 - i] = matrix[i + index][size - 1];
                matrix[i + index][size - 1] = a;
            }
            index++;
            size--;
            c -= 2;
        }

    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public static void reverseString(char[] s) {
        int end = s.length - 1;
        int start = 0;
        while (start < end) {
            char r = s[end];
            s[end] = s[start];
            s[start] = r;
            start++;
            end--;
        }
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x = x / 10;
            if ((result == 214748364 && x > 7) || (result > 214748364 && x != 0) || (result < -214748364 && x != 0) || (result == -214748364 && x < -7)) {
                return 0;
            }
        }
        return result;
    }

    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
    public static int firstUniqChar(String s) {
        char[] cs = s.toCharArray();
        HashMap<Object, Integer> hashMap = new HashMap();
        for (char c : cs) {
            Integer i = hashMap.get(c);
            if (i == null) {
                hashMap.put(c, 1);
            } else {
                hashMap.put(c, i + 1);
            }
        }
        for (int i = 0; i < cs.length; i++) {
            if (hashMap.get(cs[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * lettcode 上的解题思路
     * 因为是字符串而且只有小写字母 所以可以用小写字母在数组中出现的次数来判断是否唯一
     */
    public static int firstUniqChar1(String s) {
        int index = -1;
        for (char i = 'a'; i <= 'z'; i++) {
            int c = s.indexOf(i);
            if (c != -1 && s.lastIndexOf(i) == c) {
                index = index == -1 ? c : index < c ? index : c;
            }
        }
        return index;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     */

    /**
     * 异或可以用来数字是否成双
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] cs1 = s.toCharArray();
        char[] cs2 = t.toCharArray();
        HashMap<Object, Integer> hashMap1 = new HashMap();
        HashMap<Object, Integer> hashMap2 = new HashMap();
        for (int i = 0; i < cs1.length; i++) {
            Integer c1 = hashMap1.get(cs1[i]);
            if (c1 == null) {
                hashMap1.put(cs1[i], 1);
            } else {
                hashMap1.put(cs1[i], c1 + 1);
            }
            Integer c2 = hashMap2.get(cs2[i]);
            if (c2 == null) {
                hashMap2.put(cs2[i], 1);
            } else {
                hashMap2.put(cs2[i], c2 + 1);
            }
        }
        for (Map.Entry<Object, Integer> entry : hashMap1.entrySet()) {
            if (hashMap2.get(entry.getKey()) == null || hashMap2.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * lettcode 解题思路是既然是使用map 其实只是单次需要一个独一无二和其他key互不影响的值 所以当char转化为数组的时候完全可以代替map使用 其实是在思考的时候得抓住核心所在
     * 如果思路是正确的那么想想有没有优化方案
     */
    public static boolean isAnagram1(String s, String t) {
        char[] cs1 = s.toCharArray();
        char[] cs2 = t.toCharArray();
        int[] cca = new int[256];
        for (int i = 0; i <= cca.length; i++) {
            cca[i] = 0;
        }
        for (char i : cs1) {
            cca[i]++;
        }
        for (char i : cs2) {
            cca[i]--;
        }
        for (int i : cca) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     */
    public static boolean isPalindrome(String s) {
        char[] cs = s.toLowerCase().toCharArray();
        int i = 0;
        int c = s.length() - 1;
        while (i < c) {
            if (!((48 <= cs[i] && cs[i] <= 57)) && !((97 <= cs[i]) && (122 >= cs[i]))) {
                i++;
                continue;
            }
            if (!((48 <= cs[c] && cs[c] <= 57)) && !((97 <= cs[c]) && (122 >= cs[c]))) {
                c--;
                continue;
            }
            if (cs[i] != cs[c]) {
                return false;
            }
            i++;
            c--;
        }
        return true;
    }

    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * <p>
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
     * <p>
     * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
     * <p>
     * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
     * <p>
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
     */
    public static int myAtoi(String str) {
        int result = 0;
        boolean isAdd = true;
        boolean start=false;
        for (int i = 0; i < str.length(); i++) {
            if((int)str.charAt(i)==45&&!start){
                isAdd=false;
                start=true;
                continue;
            }
            if((int)str.charAt(i)==43&&!start){
                start=true;
                continue;
            }
            if ((int) str.charAt(i) != 32||start) {
                if (48 <= (int) str.charAt(i) && (int) str.charAt(i) <= 57) {
                    start=true;
                    if (isAdd) {
                        if(result>214748364||(result==214748364&&(int) str.charAt(i) - 48>7)){
                            return 2147483647;
                        }
                        result = result * 10 + ((int) str.charAt(i) - 48);
                    }else {
                        if(result<-214748364||(result==-214748364&&(int) str.charAt(i) - 48>8)){
                            return -2147483648;
                        }
                        result = result * 10 - ((int) str.charAt(i) - 48);
                    }
                    continue;
                }
                return result;
            }
        }
        return result;
    }

    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * */
    public static int strStr(String haystack, String needle) {
        haystack.indexOf(needle);
        if(needle.equals("")){
            return 0;
        }
        int beginIndex=-1;
        int needleIndex=0;
        boolean check=false;
        for (int i=0;i<haystack.length();i++){
            if(haystack.charAt(i)==needle.charAt(needleIndex)){
               if(!check){
                   check=true;
                   beginIndex=i;
               }
               if(needleIndex==needle.length()-1){
                   return beginIndex;
               }
                needleIndex++;
               continue;
            }
            if(check){
                check=false;
                i=beginIndex;
                beginIndex=-1;
                needleIndex=0;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi","pi"));
    }

    public static int brokenCalc(int X, int Y) {
        int ans = 0;
        while (Y > X) {
            ans++;
            if (Y % 2 == 1)
                Y++;
            else
                Y /= 2;
        }

        return ans + X - Y;
    }


    public static class Interval {
        private int i;
        private int j;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }
    }
}
