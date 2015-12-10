package test;

import java.util.ArrayList;
import java.util.List;

public class GreedyAlgo {
  public static List<Integer> selectActivity(int[] start, int[] finish){
	  //assume input is already sorted by finish time
	  List<Integer> res=new ArrayList<>();
	  res.add(0);
	  int last=0;
	  for(int i=1;i<finish.length;++i){
		  if(start[i]>=finish[last]) {
			  res.add(i);
			  last=i;
		  }		 
	  }
	  return res;
  }
  public static void main(String[] args){
	int start[]  =  {1, 3, 0, 5, 8, 5};
	int finish[] =  {2, 4, 6, 7, 9, 9};
	System.out.println(selectActivity(start,finish));
  }
}
