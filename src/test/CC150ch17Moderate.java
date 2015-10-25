package test;

import java.util.*;


public class CC150ch17Moderate {
	
	//17.1 swap two int without temp
	public static void swap(int a, int b){
		//a=1,b=2;
		a=a+b;//a=3
		b=a-b;//b=3-2=1
		a=a-b;//a=3-1=2
		System.out.println("a="+a);
		System.out.println("b="+b);	
	}
	//17.2 ??tic tac game
	//17.3 leetcode #172 count how many trailing zeros of n factorial 
	//17.4 find the max of two numbers without using comparison operator and if else
	//note need to consider overflow, below code can handle it
	public static int getMax(int a, int b){
		int c=a-b;
		int sa=sign(a);
		int sb=sign(b);
		int sc=sign(c);
		int useSignOfA=sa^sb;
		int useSignOfC=flip(sa^sb);
		int k=useSignOfA*sa+useSignOfC*sc;
		int q=flip(k);
		return a*k+b*q;
	}
	private static int sign(int a){
		return flip((a>>31) & 1);
	}
	private static int flip(int nbit){
		return 1^nbit;
	}
	
	//17.5 game of master mind
	public static void estimate(String guess, String solution){
		if(guess.length()!=solution.length()) return;
		Map<Character,Integer> map=new HashMap<>();
		int hit=0,pseudoHit=0;
		for(int i=0;i<solution.length();++i){
			char gc=guess.charAt(i);
			char sc=solution.charAt(i);
			if(gc==sc) hit++;
			else{
				int val=map.get(sc)==null?0:map.get(sc);
				map.put(sc,val+1);
			}
		}
		for(int i=0;i<solution.length();++i){
			char gc=guess.charAt(i);
			char sc=solution.charAt(i);
			if(gc==sc) continue;
			if(map.containsKey(gc)&&map.get(gc)>0) { pseudoHit++; map.put(gc, map.get(gc)-1);}
			
		}
		System.out.println("hit: "+hit+" pseudoHit: "+pseudoHit);
		
	}
	
	//17.6 given an array, find the range if we sort this range, the whole array will be sorted
	public static void findRange(int[] nums){
		if(nums==null||nums.length<=1) return;
		int i=1,n=nums.length,j=n-1;
		while(i<n-1 && nums[i]<=nums[i+1]) {i++;}
		while(j>i && nums[j]>=nums[j-1]) {j--;}
		if(i==n-1){
			System.out.println("already sorted");
			return;
		}
		System.out.println(i+" "+j);
		int k=i, min=nums[k], max=nums[k];//important, k==i to ==j, i.e. 
		while(k<=j){
			min=Math.min(nums[k],min);
			max=Math.max(nums[k],max);
			k++;
		}
		while(i>=0 && nums[i]>min) i--;
		while(j<n && nums[j]<max) j++;
		System.out.println("we need to sort from "+(i+1)+" to "+(j-1));
		
	}
	public static boolean check(String a){
		Stack<Character> s=new Stack<>();
		boolean flag=false;
		for(char c:a.toCharArray()){
			if(c=='"') flag=!flag;
			else if(c=='{'&& flag) s.push(c);
			else if(c=='}') {
				if(s.isEmpty()) return false;
				s.pop();
			}
		}
		return s.isEmpty();
	}
	
	public static void sort1(int[] nums){
		  int i=0,j=nums.length-1;
		  while(nums[i]<nums[i+1]) {i++;}
		  while(nums[j]>nums[j-1]) {j--;}
		  int temp=nums[i];
		  nums[i]=nums[j];
		  nums[j]=temp;
		}
	
	public static int jump(int[] nums){
		  if(nums==null||nums.length==0) return 0;
		  int maxPos=0, curPos=0, minSteps=0;
		  for(int i=0;i<nums.length && i<=maxPos;++i){
		    if(i>curPos){
		      minSteps++;
		      curPos=maxPos;
		    }
		    maxPos=Math.max(i+nums[i], maxPos);
		  }
		  return maxPos>=nums.length-1? minSteps:0;
		}

	public static void main(String[] args){
		//swap(1,3);
	  //System.out.println(getMax(7,5));
	  //17.5
	 // estimate("GGRR","RGBY");
	  //17.6 
	 // int[] nums={1,4,4};
	  //findRange(nums);
	  System.out.println(check("{\"{a\":\"b\"}"));
	  int[] nums={40};
	  
	  
	 sort1(nums);
	 for(int i:nums){
		 System.out.print(i+ " ");
	 }
	  
	}
	

}
