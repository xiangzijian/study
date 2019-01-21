package com.xxx;

import java.util.*;

public class ArrayDeleteRepeat {
    public static void main(String[] args) {
        int[] i = {2, 7, 11, 15};
        twoSum(i, 9);
    }

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

  /*  *//**
     * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *//*
    public boolean isValidSudoku(char[][] board) {

    }*/
}
