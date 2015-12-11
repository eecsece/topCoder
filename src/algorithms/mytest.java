package algorithms;

import java.util.Stack;


public class mytest {

	public class TreeNode{
		  int val;
		  TreeNode left;
		  TreeNode right;
		  TreeNode(int val){
			  this.val=val;
		  }
	}
	public void preorderPrint(TreeNode root){
		  if(root==null) return;
		  Stack<TreeNode> s=new Stack<TreeNode> ();
		  s.push(root);
		  while(!s.isEmpty()){
			  TreeNode temp=s.pop();
			  System.out.print(temp.val);
			  if(temp.left!=null) s.push(temp.left);
			  if(temp.right!=null) s.push(temp.right);
		  }
	  }
	  
	  public static void main(String[] args){
		  mytest my=new mytest();
		  mytest.TreeNode root=my.new TreeNode(1);
		  
	  }
}

