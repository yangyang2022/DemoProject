package com.yangyang.Algo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
public class Demo_nsum {

    private static int maxSubSum(int[] arr){
        int maxSum = arr[0];
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i+1; j < arr.length; ++j) {
                int sum = 0;
                for (int k = i; k<=j; ++k) {
                    sum += arr[k];
                }
                if(sum > maxSum) maxSum = sum;
            }
        }
        return maxSum;
    }
    private static int maxSunSum2(int[] arr,int lo,int hi){
        if(lo == hi) return arr[lo];
        int mid = (lo + hi)/2;
        int m1 = maxSunSum2(arr,lo,mid);
        int m2 = maxSunSum2(arr,mid+1,hi);

        int i,left = arr[mid],now = arr[mid];
        for(i = mid;i>=lo;i--){
            arr[i] += now;
            left = Integer.max(left,now);
        }
        int right = arr[mid+1];
        now = arr[mid+1];
        for(i = mid+1;i<hi;i++){
            arr[i] += now;
            right = max(right,now);
        }
        int m3 = left+right;
        return max(max(m1,m2),m3);
    }
    private static int max3(int[] arr){
        int endHere = arr[0];
        int ans = arr[0];
        for (int i = 0; i < arr.length; ++i) {
            endHere = Integer.max(endHere + arr[i], arr[i]);
            ans = Integer.max(ans,endHere);
        }
        return ans;
    }
    @Test
    public void testDemo1() {
        int[] arr = {1,2,-3,4,-5,7};
        
        System.out.println("maxSubSum: "+maxSubSum(arr));
        System.out.println("max2: "+maxSunSum2(arr,0,arr.length-1));
        //System.out.println("max3: "+max3(arr));

    }

    private static int findMin(int[] arr,int[] brr){
        boolean[] have = new boolean[100];
        for (int i = 0; i < arr.length; ++i) {
            have[arr[i]] = true;
        }
        int best = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 0; i < brr.length; ++i) {
            if (have[brr[i]]) {
                best = min(best,brr[i]);
                count++;
            }
        }
        System.out.println("count: "+count);
        return best;
    }
    public static int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int maxgap = 0;
        for (int i = 1; i < nums.length; ++i) {
            maxgap = Integer.max(maxgap,nums[i]-nums[i-1]);
        }
        return maxgap;
    }
    public static int singleNumber(int[] nums) {

        if(nums.length == 1) return nums[0];

        Map<Integer,Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (maps.get(nums[i]) == null) maps.put(nums[i], 1);
            else maps.put(nums[i],0);
        }
        int ans = nums[0];
        for(Map.Entry<Integer,Integer> e:maps.entrySet()){
            if(e.getValue().intValue() == 1) ans = e.getKey().intValue();
        }
        return ans;
    }

    private static void multiArray(int[] arr){
        int[] brr = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            brr[i] = i == 0 ? 1 : brr[i - 1] * arr[i - 1];
            //b[i] = a[0]*a[1] ... a[i-1]
        }
        for (int i = arr.length - 2; i>=0; i--) {
            arr[i] *= arr[i+1];
        }
        for (int i = 0; i < brr.length-1; ++i) {
            brr[i] *= arr[i + 1];
        }
        for (int i = 0; i < brr.length; ++i) {
            System.out.println(brr[i]);
        }
    }
    private static int getMinIndex(int[] arr){
        boolean[] have = new boolean[100];
        int r = 0;
        for (int i= 0;i < arr.length; ++i) {
            if(!have[arr[i]]) {
                have[arr[i]] = true;
                r = i;
            }
        }
        return r;
    }

    private static int pivote(int[] arr){
        int sum = 0,left = 0 ;
        for (int i = 0; i < arr.length; ++i) {
            sum += arr[i];
        }
        for (int i = 0; i < arr.length; ++i) {
            if (left == sum - left - arr[i]) return i;
            left += arr[i];
        }
        return -1;
    }
    private static void swap(int[] nums,int i,int j){
        int temp = nums[i];nums[i] = nums[j];nums[j] = temp;
    }
    public static void sortColors(int[] nums,int lo,int hi) {
        int lt =lo,i = lo+1,gt = hi;
        int v = nums[lo];
        while (i <= gt){
            int cmp = nums[i] - nums[lo];
            if(cmp <0) swap(nums,lt++,i);
            else if(cmp > 0) swap(nums,i,gt--);
            else i++;
        }
    }

    private static void quick3Way(int[] arr,int lo,int hi){
        if(hi <= lo) return;
        int lt = lo,i = lo+1,gt = hi;
        int v = arr[lo];
        while (i <= gt){
            int cmp = arr[i] - v;
            if(cmp <0) swap(arr,lt++,i);
            else if(cmp>0) swap(arr,i,gt--);
            else i++;
        }
        quick3Way(arr,lo,lt-1);
        quick3Way(arr,gt+1,hi);
    }

    private static void showArr(int[] arr){
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static double myPow(double x, int n) {
        if(n == 1) return x;
        double last = x;
        int r = n;
        if(n < 0 ){
            last = 1/x;
            r = Math.abs(n);
        }
        double temp = n < 0 ? 1/myPow(x,r >>1) : myPow(x,r >>1);
        return (r&1) == 0 ? temp*temp : temp*temp*last;
    }
    @Test
    public void testDemo2() {
        //int[] arr = {10,12,3,4,5,6,7,8,9,0};
        //int[] brr = {1,2,3,4,5,6,7,8,9,99};
        //System.out.println("min: "+findMin(arr,brr));
        //int[] arr = {1,1,2,2,3,4,4};
        //System.out.println(singleNumber(arr));

        //int[] arr = {1};
        //System.out.println(singleNumber(arr));

        //int[] arr = {1,2,3,4,5,4,3,2,1};
        //multiArray(arr);
        //System.out.println("getMin: "+getMinIndex(arr));
        //System.out.println("pivote: "+pivote(arr));
        //int[] arr = {0,1,0,1,0,1,0,2,1,0,2,2,0,1};
        //sortColors(arr,0,arr.length-1);
    }

    private static void showList(ListNode list){
        if(list != null){
            System.out.print(list.val+"->");
            showList(list.next);
        }
    }
    private static ListNode addTwoNumber(ListNode list1,ListNode list2){
        ListNode root = new ListNode(-1);
        ListNode newList = root;
        int carry = 0;
        for(;list1!=null || list2!=null;
            list1 = list1==null?null:list1.next,
                list2 = list2 == null?null:list2.next){
            int a = list1 == null ? 0 :list1.val;
            int b = list2 == null ? 0 :list2.val;
            ListNode node = new ListNode((carry+a+b)%10);
            carry = (carry+a+b)/10;
            root.next = node;
            root = root.next;
        }
        if(carry >0) root.next = new ListNode(1);
        return newList.next;
    }
    @Test
    public void testDemo3() {
        // 2 4 3 -- 5 6 4
        ListNode list1 = new ListNode(1);
        list1.next = null;

        ListNode list2 = new ListNode(9);
        ListNode list3 = new ListNode(9);
        list2.next = list3;list3.next=null;
        showList(addTwoNumber(list1,list2));

    }

    public static ListNode reverseList(ListNode current) {
        if (current == null || current.next == null) return current;
        ListNode nextNode = current.next;
        current.next = null;
        ListNode reverseRest = reverseList(nextNode);
        nextNode.next = current;
        return reverseRest;
    }
    public static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode reverseNode = reverse(head.next);
        head.next = head;
        //head.next = null;
        return reverseNode;
    }
    @Test
    public void testDemo4() {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(3);
        list1.next = list2;list2.next=list3;list3.next=null;

        //showList(reverseList(list1));
        showList(reverse(list1));

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i+1; j < nums.length; ++j) {
                if(Arrays.binarySearch(nums,j+1,nums.length,-nums[i]-nums[j]) > j){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);temp.add(nums[j]);temp.add(-nums[i]-nums[j]);
                    ans.add(temp);
                }
            }
        }
        ans = ans.stream().distinct().collect(Collectors.toList());
        return ans;
    }
    @Test
    public void testDemo5() {
        int[] nums = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
        //-4 -1 -1 0 1 2
        System.out.println(threeSum(nums));
    }

    private static int toDec(String s){
        int x = 0;
        for(char c: s.toCharArray()){
            x = x*2 + (c == '1' ? 1 : 0);
        }
        return x;
    }
    public static String addBinary(String a, String b) {
        return Integer.toBinaryString(toDec(a)+toDec(b));
    }
    @Test
    public void testDemo6() {
        String a = "11";
        String b = "1";
        String c = "110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000";
        System.out.println(addBinary(a,b));
        System.out.println(toDec(c));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

    }

    private static int daije(int m){
        int[] dp = new int[m+3];
        //dp[1] = 1;dp[2] = 1;
        //for (int i = 3; i <= m; ++i) {
        //    dp[i] = dp[i-1]+dp[i-2];
        //}
        //return dp[m];

        dp[1] = 1;
        for (int i = 1; i <= m; ++i) {
            dp[i+1] += dp[i];
            dp[i+2] += dp[i];
        }
        return dp[m];
    }

    private static void showTree(TreeNode root){
        if(root != null){
            showTree(root.left);
            System.out.print(root.val+" ");
            showTree(root.right);
        }
    }
    private static int s = 0;
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        s+=root.val;
        if(s == sum) return true;
        else {
            if(!hasPathSum(root.left,sum))
                return hasPathSum(root.right,sum);
        }
        return true;
    }

    private static int maxDepth(TreeNode root){
        return root == null ? 0:Integer.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    private static int minDepth(TreeNode root){
        if(root == null) return 0;
        if(root.left != null){
            if(root.right != null)
                return Integer.min(minDepth(root.left),minDepth(root.right))+1;
            else return minDepth(root.left)+1;
        }else if(root.right!=null){
            return minDepth(root.right)+1;
        }else return 1;//only root
    }

    private boolean helpPathSum(TreeNode node,int num,int cursum){
        if(node == null) return false;
        if(node.left == null && node.right == null) return cursum+node.val == num;
        return helpPathSum(node.left,num,cursum+node.val) || helpPathSum(node.right,num,cursum+node.val);
    }
    private boolean pathSum(TreeNode root,int num){
        return helpPathSum(root,num,0);
    }
    static int h1 = 0,h2 = 0;
    private boolean issame(TreeNode root,int h){
        if(root == null) {
            return true;
        }
        if(!issame(root.left,h1) || !issame(root.right,h2)) return false;
        h = Integer.max(h1,h2) +1;
        return h1 <= h2+1 && h2 <= h1+1;
    }
    private boolean same(TreeNode root){
        System.out.println("h1: "+h1+" h2: "+h2);

        return issame(root,0);
    }
    @Test
    public void testDemo7() {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);

        /**
         *       5
         *     4    8
         *   11   13  4
         *  7  2        1
         *
         */
        root.left = node1;root.right = node2;
        node1.left = node3;
        node3.left = node6;node3.right = node7;
        node2.left = node4;node2.right = node5;
        node5.right = node8;
        
        showTree(root);
        System.out.println(hasPathSum(root, 22));
        System.out.println("maxDepth: "+maxDepth(root));      //4
        System.out.println("minDepth: "+minDepth(root.right));//2
        System.out.println("pathSum: "+pathSum(root,22));
        System.out.println("same: "+same(root.right));

    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}