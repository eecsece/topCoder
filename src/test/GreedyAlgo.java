package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Job implements Comparable<Job> {
	String id;
	int deadline;
	int profit;
	
	public Job(String id, int deadline, int profit){
		this.id=id;
		this.deadline=deadline;
		this.profit=profit;
	}
	@Override
	public int compareTo(Job o) {
		return o.deadline-this.deadline;
	}
	
}

public class GreedyAlgo {
  public static List<String> maxJobProfit(Job[] jobs ){
	  List<String> res=new ArrayList<>();
	  //we can do customer Comparator or implements Comparable Interface in Job Class
	  Comparator<Job> c=new Comparator<Job>(){
		  public int compare(Job j1, Job j2){
			  return j2.profit-j1.profit;//decreasing order
		  }
	  };
	  Arrays.sort(jobs,c);
	  boolean[] slot=new boolean[jobs.length];
	  res.add(jobs[0].id);
	  slot[0]=true;
	  for(int i=1;i<jobs.length;++i){
		  Job cur=jobs[i];
		  for(int j=Math.min(jobs.length, cur.deadline)-1;j>=0;--j){
			  if(slot[j]==false){
				  res.add(cur.id);
				  slot[j]=true;
				  break;
			  }
		  }
		  
	  }
	  return res;
  }
	
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
	  //Job[] jobs=new Job[]{new Job("a",2,100),new Job("b",1,19),new Job("c",2,27),new Job("d",1,25),new Job("e",3,15)};
	  Job[] jobs=new Job[]{new Job("a",4,20),new Job("b",1,10),new Job("c",1,40),new Job("d",1,30)};
	  System.out.println(maxJobProfit(jobs));
	  
	  
	int start[]  =  {1, 3, 0, 5, 8, 5};
	int finish[] =  {2, 4, 6, 7, 9, 9};
	System.out.println(selectActivity(start,finish));
  }
}
