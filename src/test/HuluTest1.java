package test;



import java.io.*;
import java.util.*;

class EmpNode{
	String name;
	String title;
	String year;
	public EmpNode(String name,String title, String year){
		this.name=name;
		this.title=title;
		this.year=year;
	}
}

public class HuluTest1 {
	
	public static void sort(List<EmpNode> list){
	  //Comparator used for sort list in alphabetical order
	  Comparator<EmpNode> c=new Comparator<EmpNode>(){
		@Override
		public int compare(EmpNode n1,EmpNode n2){
			return n1.name.compareTo(n2.name);
		}
	  };
	  Collections.sort(list,c);
	}
	
	//print chart using depth first search algorithms
	public static void dfsPrint(BufferedWriter writer,List<EmpNode> list, int dash, Map<String,List<EmpNode>> map) throws IOException{
		sort(list);
		for(EmpNode n:list){
		  for(int i=dash;i>0;--i){
			  writer.write("-");
		  }
		  writer.write(n.name+" ("+n.title+") "+n.year+"\n");
		  if(map.containsKey(n.name)){
			 list=map.get(n.name);
			 dfsPrint(writer,list,dash+1,map);	
		  }
		}	
	}
	
	public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("org_chart.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("org_chart.out"));
        
        int numCases = Integer.parseInt(reader.readLine());
        
        for (int caseNum = 1; caseNum <= numCases; caseNum++)
        {
            String[] toks = reader.readLine().split("--");
            Map<String,List<EmpNode>> map=new HashMap<>();
            for(String t:toks){
            	String[] info=t.split(",");
            	String boss=info[1];
            	EmpNode emp=new EmpNode(info[0],info[2],info[3]);
            	if(map.containsKey(boss)){
            		map.get(boss).add(emp);
            	}else{
            		List<EmpNode> item=new ArrayList<>();
            		item.add(emp);
            		map.put(boss,item);
            	}
            } 
            writer.write("Case #" + caseNum + "\n");
	        List<EmpNode> list=map.get("NULL");
	        dfsPrint(writer,list,0,map);       
        }
        
        writer.close();
        reader.close();
    }
}
