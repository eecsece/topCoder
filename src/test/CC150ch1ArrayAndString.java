package test;

import java.util.HashMap;
import java.util.HashSet;

public class CC150ch1ArrayAndString {
	//1.1 unique char in string, o(n):o(n)
    public static boolean isUnique(String s){
    	HashSet<Character> set=new HashSet<>();
    	for(int i=0;i<s.length();++i){
    		if(set.contains(s.charAt(i))) return false;
    		set.add(s.charAt(i));
    	}
    	return true;
    }
    //1.1 unique char in string, o(n):o(1)
    public static boolean isUniqueNoExtraSpace(String s){
    	//assume length is less than 64 and all lower case
    	int checker=0;
    	for(int i=0;i<s.length();++i){
    		int val=s.charAt(i)-'a';
    		if((checker & (1<<val))!=0) return false;	
    		checker |= (1<<val);
    	}
    	return true;
    }
    //1.2 reverse a string
    public static void reverse(char[] s){
    	int low=0, high=s.length-1;
    	while(low<high){
    		char temp=s[low];
    		s[low++]=s[high];
    		s[high--]=temp;
    	}
    }
    //1.3 anagram
    public static boolean isAnagram(String s1, String s2){
    	if(s1==null||s2==null||s1.length()==0||s2.length()==0) return true;
    	if(s1.length()!=s2.length()) return false;
    	HashMap<Character, Integer> map=new HashMap<>();
    	for(char c: s1.toCharArray()){
    		if(map.containsKey(c)){
    			map.put(c,map.get(c)+1);
    		}else{
    			map.put(c,1);
    		}
    	}
    	for(char c:s2.toCharArray()){
    		if(!map.containsKey(c)) return false;
    		if(map.get(c)==1) map.remove(c);
    		else map.put(c, map.get(c)-1);
    	}
    	return map.isEmpty();
    }
    //1.4 replace spaces with '%20'
    public static void replace(char[] arr, int length){
    	int count=0;
    	for(int i=0;i<length;++i){
    		if(arr[i]==' ') count++;
    	}
    	int j=length+2*count-1;
    	for(int i=length-1;i>=0;--i){
    		if(arr[i]!=' '){
    			arr[j--]=arr[i];
    		}else{
    			arr[j--]='0';
    			arr[j--]='2';
    			arr[j--]='%';
    		}
    	}
    }
    //1.5 string compression
    public static String compress(String s){
    	if(s==null||s.length()==0) return s;
    	char c=s.charAt(0);
    	int count=1;
    	StringBuilder sb=new StringBuilder();
    	for(int i=1;i<s.length();++i){
    		if(s.charAt(i)==c) count++;
    		else{
    			sb.append(c);
    			sb.append(count);
    			c=s.charAt(i);
    			count=1;
    		}
    	}
    	sb.append(c);
    	sb.append(count);
    	return sb.length()<s.length()? sb.toString():s;
    }
  //1.6 rotate image by 90 leetcode
  //1.7 set zeros leetcode
  //1.8 given isSubstring(String s1,String s2) determine if s1 is rotation of s2
    /*
    public static boolean isRotation(String s1,String s2){
        if(s1==null||s2==null||s1.length()!=s2.length()) return false;
    	String s=s1+s1;
    	return isSubstring(s1,s2);
    }
    */
	public static void main(String[] args){
		//1.1 is unique char in a string
		System.out.println(isUnique("abc"));
		System.out.println(isUniqueNoExtraSpace("abc"));
		System.out.println(isUnique(""));
		System.out.println(isUniqueNoExtraSpace(""));
		System.out.println(isUnique("aba"));
		System.out.println(isUniqueNoExtraSpace("aba"));
		//1.2 reverse char array- string
		char[] s=new char[]{'a','b'};
		reverse(s);
		System.out.println(new String(s));
		//1.3 anagram
		System.out.println(isAnagram("abcabc","cbabccb"));
		//1.4 replace spaces with '%20'
		char[] s4=new char[100];
		s4[0]=' ';
		s4[1]='b';
		s4[2]=' ';
		replace(s4,3);
		System.out.println(new String(s4));
		//1.5 string compression
		System.out.println(compress("abcd"));
		System.out.println(compress("aabcccccaaa"));
		//1.6 rotate image by 90 leetcode
		//1.7 set zeros leetcode
		//1.8 given isSubstring(String s1,String s2) determine if s1 is rotation of s2
		
		
	}
}
