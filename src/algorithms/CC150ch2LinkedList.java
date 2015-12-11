package algorithms;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class CC150ch2LinkedList {
  //2.1 remove duplicates from unsorted linked list	o(n):o(n)
	public static void removeDuplicate(ListNode head){
		if(head==null||head.next==null) return;
		ListNode cur=head;
		HashSet<Integer> set=new HashSet<>();
		set.add(cur.val);
		while(cur.next!=null){
			if(set.contains(cur.next.val)){ 
				cur.next=cur.next.next;
			}else{
				set.add(cur.next.val);
				cur=cur.next;
			}		
		}
	}
  //2.1 remove duplicates from unsorted linked list in place o(n^2):o(1)
  public static void removeDuplicateInPlace(ListNode head){
	  if(head==null||head.next==null) return;
	  ListNode cur=head;
	  while(cur!=null){
		  ListNode p=cur;
		  while(p.next!=null){
			  if(p.next.val==cur.val){
				  p.next=p.next.next;
			  }else{///only move p in else
			      p=p.next;
			  }
		  }
		  cur=cur.next;	  
	  }
  }
  //2.2 get kth to Last elem (k is 1 base) 
  public static ListNode kthToLast(ListNode head,int k){
	  if(head==null||k<=0) return null;
	  ListNode fast=head,slow=head;
	  while(fast!=null&&k!=0){
		  --k;
		  fast=fast.next;
	  }
	  if(k!=0) return null;
	  while(fast!=null){
		  fast=fast.next;
		  slow=slow.next;
	  }
	  return slow;
  }
  //2.3 given only access to a node of the linkedlist(not the end) delete it
  public static boolean delete(ListNode nodeToDel){
	  if(nodeToDel==null || nodeToDel.next==null) return false;
	  nodeToDel.val=nodeToDel.next.val;
	  nodeToDel.next=nodeToDel.next.next;
	  return true;
  }
  //2.4 leetcode partition List 
  
  //2.5 LeetCode add two numbers represented as linkedlist( store in reverse order) 
  //2.5.2 if numbers are stored in regular order (reverse or padding zeros, pass carry with ListNode in PartialSum)
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      if(l1==null) return l2;
      if(l2==null) return l1;
      int len1=countNodes(l1);
      int len2=countNodes(l2);
      if(len1<len2) l1=padZeros(l1,len2-len1);
      else if(len2<len1) l2=padZeros(l2,len1-len2);
      PartialSum pSum=helper(l1,l2);
      if(pSum.carry!=0) {
      	ListNode n=new ListNode(pSum.carry);
      	n.next=pSum.node;
      	return n;
      }
      return pSum.node;
      
  }
  private PartialSum helper(ListNode l1,ListNode l2){
      if(l1==null&&l2==null) {
      	return new PartialSum();//base case; done 
      }
      PartialSum pSum=helper(l1.next,l2.next);
      int val=pSum.carry+l1.val+l2.val;
      ListNode n=new ListNode(val%10);
      n.next=pSum.node;
      pSum.node=n;
      pSum.carry=val/10;
      return pSum;
  } 
  private int countNodes(ListNode l){
  	int count=0;
  	while(l!=null){ 
  		count++;
  		l=l.next;
  	}
  	return count;
  }
  private ListNode padZeros(ListNode l, int count){
  	ListNode dummy=new ListNode(0);
  	ListNode p=dummy;
  	while(count>0){
  		p.next=new ListNode(0);
  		p=p.next;
  		count--;
  	}
  	p.next=l;
  	return dummy.next;    	
  }
  
  //2.6 leetcode get linkedlist cycle start
  //2.7 palindrom linkedlist
  public static boolean isPalindrome(ListNode head){
	  if(head==null||head.next==null) return true;
	  ListNode fast=head,slow=head;
	  Stack<Integer> s=new Stack<>();
	  while(fast!=null&&fast.next!=null){
		  s.push(slow.val);
		  slow=slow.next;
		  fast=fast.next.next;
	  }
	  if(fast!=null) slow=slow.next; //odd
	  while(!s.isEmpty()){
		  if(s.pop()!=slow.val) return false;
		  slow=slow.next;
	  }
	  return true;
  }
  
  
  public static void main(String[] args){
	  
	try {
		Thread.sleep(10);
		TimeUnit.SECONDS.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	//2.1 remove duplicates from unsorted linked list
	  ListNode head=new ListNode(1);
	  head.next=new ListNode(1);
	  head.next.next=new ListNode(5);
	  head.next.next.next=new ListNode(4);
	  head.next.next.next.next=new ListNode(1);
	  //removeDuplicate(head);
	  //removeDuplicateInPlace(head);
//	  while(head!=null){
//		  System.out.println(head.val);
//		  head=head.next;
//	  }
	  
	  //2.2
	  //System.out.println(kthToLast(head,3));
	  
	  //2.7
	  System.out.println(isPalindrome(head));
	  
  }
}
