package algorithms;

import java.util.HashMap;
import java.util.Map;

public class ElephantNum {
	public boolean isElephantNum(int elephant){
		//check positive
		if(elephant<=0) return false;
		//check even digit
		int digit=getDigit(elephant);
		if(digit%2!=0) return false;
		//check if qualified X,Y exist
		return testXY(elephant,digit/2);
	}
	public boolean testXY(int elephant, int digit){
		//get upperBound and lowerBound for X, e.g 2 digit X could be [10,100)
		int upperBound=(int) Math.pow(10, digit);
		int lowerBound=(int) Math.pow(10,digit-1);
		for(int X=lowerBound;X<upperBound && X<=Math.sqrt(elephant) ;++X){
			//check if there Y exists to make X*Y=elephant
			if(elephant%X!=0) continue;
			int Y=elephant/X;
			
			if(getDigit(Y)==digit){ //check Y's digit
				boolean trailingZerosX=hasTrailingZeros(X); 
				boolean trailingZerosY=hasTrailingZeros(Y);
				if(trailingZerosX^trailingZerosY && isAnagram(elephant,X,Y)){ //check trailingZeros and Anagrams
					return true;
				}
			}
		}
		return false;
	}
	private int getDigit(int num){
		int count=0;
		while(num!=0){
			count++;
			num/=10;
		}
		return count;
	}
	private boolean hasTrailingZeros(int num){
		return num%10==0;
	}
	private boolean isAnagram(int elephant, int X, int Y){
		Map<Integer,Integer> map=new HashMap<>();
		while(elephant!=0){
			int digit=elephant%10;
			if(map.containsKey(digit)){
				map.put(digit,map.get(digit)+1);
			}else{
				map.put(digit,1);
			}
			elephant/=10;
		}
		while(X!=0){
			int digit=X%10;
			if(!map.containsKey(digit)) return false;
			if(map.get(digit)==1){
				map.remove(digit);
			}else{
				map.put(digit,map.get(digit)-1);
			}
			X/=10;
		}
		while(Y!=0){
			int digit=Y%10;
			if(!map.containsKey(digit)) return false;
			if(map.get(digit)==1){
				map.remove(digit);
			}else{
				map.put(digit,map.get(digit)-1);
			}
			Y/=10;
		}
		return map.isEmpty();
	}
	public static void main(String[] args){
		ElephantNum solution=new ElephantNum();
		System.out.println("1260 is Elephant number: "+solution.isElephantNum(1260));
		System.out.println("126000 is Elephant number: "+solution.isElephantNum(126000));
	}
}
