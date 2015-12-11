package algorithms;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
	
	//3 arrays for pruning
    private int[] col; //column
    private int[] mainDiag; //main Diagon
    private int[] antiDiag; //anti Diagon
	int counter=0;
    
    public int solveNQueens(int n) {
        //List<String[] > res=new ArrayList<String[]>();
        col=new int[n];
        mainDiag=new int[2*n]; //Diag from upper right to lower left
        antiDiag=new int[2*n]; //Diag from upper left to lower right
       // int[] queens=new int[n];  //store the queen's position, index is row, value is column
        dfs(0, n);
        return counter;
        
    }
    
    private void dfs(int cur, int n){ //cur is row index, i is col index 
        if(cur==n) {
            //res.add(generateBoard(queens));
        	counter++;
            return;
        }
        for(int i=0;i<n;++i){
        	//for debug use
//        	System.out.print(i+":"+col[i]+",");
//        	System.out.print(cur+i+":"+mainDiag[cur+i]+",");
//        	System.out.print(Math.abs(cur-i)+":"+antiDiag[Math.abs(cur-i)]+",");
//        	System.out.println();
        	
        	if(col[i]==0 
        			&& mainDiag[cur+i]==0 //all items on same mainDiag has same cur+i -> (3,0),(2,1),(1,2),(0,3)
        			&& antiDiag[cur+n-i]==0){//all items on same antiDiag has same abs(cur-i),but must use (cur+n-i) because abs cannot diff diag(1,0)(2,1) with diag(0,1),(1,2)
        		col[i]=mainDiag[cur+i]=antiDiag[cur+n-i]=1;
        		
        		dfs(cur+1,n);
        		col[i]=mainDiag[cur+i]=antiDiag[cur+n-i]=0;
        	}
        	
        }
        
        
    }
    
    private String[] generateBoard(int[] queens){
    	int n=queens.length;
    	String[] board=new String[n];
    	StringBuilder sb=new StringBuilder(); //sb represents a row
    	for(int i=0;i<n;++i){
    	  sb.append('.');	//initially set string to all "."
    	}
    	for(int i=0;i<n;++i){
    		sb.setCharAt(queens[i],'Q'); //set Q position
    		board[i]=sb.toString(); //add the string 
    		sb.setCharAt(queens[i], '.');//set back to "." for next row
    	}
    	return board;
    	
    }
    
    
    
    public static void main(String[] args){
    	NQueen nq=new NQueen();
//    	List<String[]> slist=nq.solveNQueens(4);
//    	for(String[] list:slist){
//    		for(String s: list){
//    			System.out.print(s+",");
//    		}
//    		System.out.println();
//    	}
    	
    	System.out.println(nq.solveNQueens(4));
    	
    	//test generateBoard function
//    	int[] queens=new int[]{1,3,0,2};
//    	String[] board=nq.generateBoard(queens);
//        for(String s:board)
//		  System.out.println(s);
        
   }
}
