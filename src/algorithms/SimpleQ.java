package algorithms;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class SimpleQ {
  public static int fib(int n){
	  if(n==0) return 0;
	  if(n==1) return 1;
	  return fib(n-1)+fib(n-2);	  
  }
  
  public static int fibIte(int n){ 
	 if(n==0)return 0;
	 if(n==1) return 1;
	 int fib=1,fib1=0,fib2=1;
	 for(int i=2;i<=n;++i){
		 fib=fib1+fib2;
		 fib1=fib2;
		 fib2=fib;
	 }
	 return fib;
  }
   
  public static void fibSeriesExtraSpace(int n){
	  int[] fib=new int[n+1];
	  fib[0]=0;
	  fib[1]=1;
	  for(int i=2;i<=n;++i){
		  fib[i]=fib[i-1]+fib[i-2];
	  }
	  for(int val:fib){
		  System.out.print(val+" ");
	  }
  }
  
  public static void fibSeries(int n){
	  int fib=0, fib1=0,fib2=1;
	  for(int i=0;i<n;++i){
		 if(i==0||i==1) {
			 System.out.print(i+" ");
			 continue;
		 }
		 fib=fib1+fib2;
		 System.out.print(fib+" ");
		 fib1=fib2;
		 fib2=fib;
	  }
  }
  
  public static int factorial(int n){
	  if(n<0) return -1;//invalid if n<0
	  if(n==0 || n==1) return 1;
	  return n*factorial(n-1);
  }
  public static int factorialIte(int n){
	  if(n<0) return -1;
	  int res=1;
	  for(int i=1;i<=n;++i){
		  res*=i;
	  }
	  return res;
  }
  public static int binarySearch(int[] arr, int target){
	  //must be sorted array or Arrays.sort(arr);
	  if(arr==null||arr.length==0) return -1;
	  int low=0, high=arr.length;
	  while(low<=high){
		  int mid=(low+high)/2;
		  if(arr[mid]==target) return mid;
		  else if(arr[mid]<target) low=mid+1;
		  else high=mid-1;
	  }
	  return -1;
	  
  }
  public static ListNode removeListNode(ListNode head, int target){
	  ListNode dummy=new ListNode(0);
	  dummy.next=head;
	  ListNode cur=dummy;
	  while(cur.next!=null){
		  if(cur.next.val==target){
			  cur.next=cur.next.next;
			  break;
		  }
		  cur=cur.next;
	  }
	  return dummy.next;	  
  }
  
  public static ListNode insertListNode(ListNode head, int index, ListNode target){
	  ListNode dummy=new ListNode(0);
	  dummy.next=head;
	  ListNode cur=dummy;
	  while(cur!=null && index>0) {
		  cur=cur.next;
		  index--;
      }
	  if(cur!=null) {
		  target.next=cur.next;
		  cur.next=target;
	  }
	  return dummy.next;
	  
  }
  
  
  
 //remove given char from string 
  public static String remove(char[] str, char c){
	  int i=0;
	  for(int j=0;j<str.length;++j){
		  System.out.println(str[i]);
		  if(str[j]!=c) {
			  str[i]=str[j];
			  i++;
		  }
	  }
	  return new String(str,0,i);
  }
  
  //determine if a integer is prime
  public static boolean isPrime(int x){
	  for(int i=2;i*i<=x;++i){
		  if(x%i==0) return false;
	  }
	  return true;
  }
 
  //pour water from top of the wall
  public static boolean goThroughOK(int[][] grid){
	  for(int j=0;j<grid[0].length;++j){
		  if(dfs(grid,0,j)) return true;
	  }
	  return false;
  }
  private static boolean dfs(int[][] grid, int i,int j){
	  if(i==grid.length) return true;
	  if(i<0||j<0||j>=grid[0].length) return false;
	  boolean res=false;
	  if(grid[i][j]==0){
		  grid[i][j]=-1;
		  res=dfs(grid,i+1,j) || dfs(grid,i,j-1)||dfs(grid,i,j+1);
		  grid[i][j]=0;
	  }
	  return res;
  }
  
  //clone linkedlist
  public static ListNode copyList(ListNode head) {
      if(head==null) return null;
      ListNode dummy=new ListNode(0);
      ListNode p1=head;
      ListNode p2=dummy;
      while(p1!=null){
         p2.next=new ListNode(p1.val);
         p2=p2.next;
         p1=p1.next;
      }
      return dummy.next;
  }
  
  //reverse linked list recursion
  public static ListNode reverseList(ListNode head){
	  if(head==null||head.next==null) return head;
	  ListNode second=head.next;
	  head.next=null;
	  ListNode reversed=reverseList(second);
	  second.next=head;
	  return reversed;
  }
  
  //find deepest node google bfs o(n):o(n)
  public static TreeNode findDeepest(TreeNode root){
	    if(root==null) return null;
	    Queue<TreeNode> que=new LinkedList<TreeNode>();
	    que.offer(root);
	    TreeNode p=null;
	    while(!que.isEmpty()){
	    	p=que.poll();
	    	if(p.left!=null) que.offer(p.left);
	    	if(p.right!=null) que.offer(p.right);
	    }
	    return p;  
  }
  //get the number of the nodes in a given level of binary tree
  public static int numAtLevel(TreeNode root, int level){
	  if(root==null || level<0) return 0;
	  if(level==1) return 1;
	  Queue<TreeNode> que=new LinkedList<TreeNode>();
	  que.offer(root);
	  int count=1;
	  TreeNode p=null;
	  while(!que.isEmpty()){
		  p=que.poll();
		  count--;
		  if(p.left!=null) que.offer(p.left);
		  if(p.right!=null) que.offer(p.right);
		  if(count==0){
			  level--;
			  count=que.size();
			  if(level==0) return count;
		  }
	  }
	  return 0; //level exceed root's depth
	  
  }
  
  
  
  //find unique number in an array
  public static int findUnique(int[] A){
	  Map<Integer,Integer> map=new HashMap<>();
	  for(int a:A){
		  if(map.containsKey(a)){
			  map.put(a,map.get(a)+1);
		  }else{
			  map.put(a, 1);
		  }
	  }
	  for(Entry<Integer,Integer> e:map.entrySet()){
		  if(e.getValue()==1){
			  return e.getKey();
		  }
	  }
	  return -1;
  }
  //find unqiue numbers in an array
  public static void findUnqiue(int[] A){
	  Set<Integer> set=new HashSet<>();
	  for(int a:A){
		  set.add(a);
	  }
	  for(Integer a:set){
		  System.out.println(a);
	  }
  }
  
  //find kth smallest in 2d col-wise and row-wise sorted array
  //use max heap, pq is not fixed size
  public static int findKthSmallest(int[][] grid,int k){
	  Comparator<Integer> c=new Comparator<Integer>(){
          @Override
          public int compare(Integer l1,Integer l2){
              return l2-l1;
          }
        };
	  PriorityQueue<Integer> pq=new PriorityQueue<>(k,c);
	  for(int i=0;i<grid.length;++i){
		  for(int j=0;j<grid[0].length;++j){
			  if(pq.size()<k) pq.offer(grid[i][j]);
			  else if(grid[i][j]<pq.peek()) {
				  pq.poll();//this is must
				  pq.offer(grid[i][j]);
			  }
		  }
	  }
	  return pq.peek();
  }
  //find two sums in an array equal to another two sum in the array
  public static List<Integer> findEquals(int[] arr){
	  List<Integer> res=new ArrayList<>();
	  Map<Integer,Pair> map=new HashMap<>();
	  for(int i=0;i<arr.length-1;++i){
		  for(int j=i+1;j<arr.length;++j){
			  int sum=arr[i]+arr[j];
			  if(map.containsKey(sum)) {
				  Pair p=map.get(sum);
				  if(p!=null){
				    res.add(p.first);
				    res.add(p.second);
				    map.put(sum,null);
				  }
				  res.add(i);
				  res.add(j);
			  }else{
				  map.put(sum, new Pair(i,j));
			  }
		  }
	  }
	  return res;
  }
  
  public static List<String> removeDup(List<String> list){
	  Set<String> set=new HashSet<>();
	  set.addAll(list);
	  return new ArrayList<>(set);  
  }
  
  public static void printAllMissing(int[] A){
	  Arrays.sort(A);
	  for(int i=0;i<A.length-1;++i){
	    int temp=A[i]+1;
	    while(temp<A[i+1]){
	      System.out.println(temp++);
	    }
	  }
  }
 
  
  
  //???binary search rotated array, {1,2,3,10,9,8,7,6,5,4}
  public static boolean searchRotatedArray(int[] A, int target){
	  if(A==null||A.length==0) return false;
	  int low=0,high=A.length-1;
	  while(low<=high){
		  int mid=low+(high-low)/2;
		  if(A[mid]==target) return true;
		  if((A[mid]<A[high]&&A[low]<=A[mid]&&A[mid]<target) || (A[high]<A[low] && A[mid]>target) || 
				  (A[mid]>A[low] &&A[mid]>A[high]&&target<A[mid]&&target>=A[high])){
			 low=mid+1; 
		  }
		  else high=mid-1;
	  }
	  return false;
  }
  
  
 
  public static void main(String[] args){
	  
//	  // count cycle node
//	  ListNode head=new ListNode(1);
//	  head.next=new ListNode(2);
//	  head.next.next=new ListNode(3);
//	  head.next.next.next=new ListNode(4);
//	  head.next.next.next.next=head;
//	  
//	  System.out.println(countCycle(head));
	  
	  //binary search in rotated array
	  int[] arr=new int[]{1,2,3,4,10,9};
	  System.out.println(searchRotatedArray(arr,2));
	  
	  //clock angle 
	  //System.out.println(clockDegree("4:42"));
	  
	  //convertColor
//	  int[][] matrix=new int[][]{{0,0,1,0,0,1},{0,1,0,1,0,0},{0,1,1,0,1,1},{0,1,0,0,1,1},{0,0,1,1,1,1}};
//	  convertColor(matrix,2,2,2);
//	  for(int i=0;i<matrix.length;++i){
//		  for(int j=0;j<matrix[0].length;++j){
//			  System.out.print(matrix[i][j]+" ");
//		  }
//		  System.out.println();
//	  }
	  
	  int[] num={1,1,1,1,1};
	 // System.out.println(deleteTarget(num,1));
	  
//	  System.out.println(intToBin(1));
//	  System.out.println(Integer.toBinaryString(-2));
//	  System.out.println(Integer.numberOfLeadingZeros(0));
	  
	  System.out.println(fib(6));
	  System.out.println(fibIte(6));
	  fibSeries(7);
	  
//	  int[] arr={7,5,9,14};
//	  printAllMissing(arr);
	  
	  int[][] grid={{10,20,30,40},{15,25,35,45},{24,29,35,45},{24,29,37,48}};
	  System.out.println( findKthSmallest(grid,16));
	  
	  int[] A={3,4,7,1,2,9,8};
	  List<Integer> list=findEquals(A);
	  for(int l:list){
	    System.out.print(l+" ");
	  }
	  System.out.println();
	  int res=findUnique(A);
	  System.out.print(res);
	  System.out.println();
	  //find deepest node
	  TreeNode root=new TreeNode(1);
	  root.left=new TreeNode(2);
	  root.right=new TreeNode(3);
	  root.right.left=new TreeNode(4);
	  TreeNode deepest=findDeepest(root);
	  if(deepest==null) System.out.println("null");
	  else System.out.println(deepest.val);
	  
	  //reverse linked list
	  ListNode l1=new ListNode(0);
	  l1.next=new ListNode(1);
	  l1.next.next=new ListNode(2);
	  ListNode reversedL1=reverseList(l1);
	  while(reversedL1!=null){
		  System.out.print(reversedL1.val);
		  reversedL1=reversedL1.next;
	  }
	  System.out.println();
	  //factorial
	  System.out.println(factorial(5));
	  
	  //clone linkedlist
//	  ListNode l1=new ListNode(1);
//	  l1.next=new ListNode(2);
//	  l1.next.next=new ListNode(3);
//	  ListNode l2=copyList(l1);
//	  System.out.println(l2.next.val);
	  
	  //swap two integers without temp
//	  int a,b;
//	  System.out.println("enter two numbers");
//	  Scanner in=new Scanner(System.in);
//	  a=in.nextInt();
//	  b=in.nextInt();
//	  a=a+b;
//	  b=a-b;
//	  a=a-b;
//	  System.out.println(a);
//	  System.out.println(b);
//	  
	 
	  
	  
  }
}
