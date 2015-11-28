package test;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HackerrankEnv {
	public static void print(int n){
	    for(int i=0;i<n;++i){
	    	//int spaces=6;
	        int spaces=n-i-1;
	        int stars=i+1;
	        while(spaces>0){
	            System.out.print("*");
	            spaces--;
	        }
	        while(stars-->0){
	            System.out.print("#");
	        }
	        System.out.println();
	    }
	}    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        Scanner sc=new Scanner(System.in);
//        int target=sc.nextInt();
//        int N=sc.nextInt();
//        int[] nums=new int[N];
//        for(int i=0;i<N;++i){
//            nums[i]=sc.nextInt();
//        }
    	print(6);
    }
}
