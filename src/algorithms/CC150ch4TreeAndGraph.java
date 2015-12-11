package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class CC150ch4TreeAndGraph {
  //4.1 leetcode tree isBalanced
  //4.2 given a directed graph, find whether there is a route between two nodes
  //bsf, add field  similar to leetcode
  //4.3 leetcode construct bst from sorted array
  //4.4 leetcode level order traversal
  //4.5 leetcode check if a tree isBST
  //4.6 find a node's inorder successor in BST, node has a link to its parent
	//in order to run this problem, we need a TreeNode class with parent field
//	public static TreeNode inorderSuc(TreeNode n){
//		if(n==null) return null;
//		if(n.right!=null){
//			return getLeftMost(n.right);
//		}
//		TreeNode p=n.parent;
//		while(p!=null && p.left!=n){
//			n=p;
//			p=p.parent;
//		}
//		return p;
//	}
//	private static TreeNode getLeftMost(TreeNode n){
//		while(n.left!=null){
//			n=n.left;
//		}
//		return n;
//	}
	
	//need to ask if it is possilbe that p, q not even in the tree
	//4.7.1 lowest common ancestor of BST
	public static TreeNode LCABST(TreeNode root, TreeNode p, TreeNode q){
		//assume(p,q is in BST)
		if(root==null||p==null||q==null) return null;
		//if(!findNode(root,p)||!findNode(root,q)) return null; //if no assumption of pq both in tree,function return root
		if(Math.max(p.val, q.val)<root.val){
			return LCABST(root.left,p,q);
		}else if(Math.min(p.val, q.val)>root.val){
			return LCABST(root.right,p,q);
		}else{
			return root;
		}
	}
	
	//4.7.2 lowest common ancestor of BT bottom up recursion o(n):o(lgn)
	public static TreeNode LCABT(TreeNode root, TreeNode p, TreeNode q){
		if(root==null) return null;
		//if(!findNode(root,p)||!findNode(root,q)) return null; // if no assumption pq in tree
		if(root==p||root==q) return root;
		TreeNode left= LCABT(root.left,p,q);
		TreeNode right=LCABT(root.right,p,q);
		if(left!=null && right!=null) return root; //p,q in different subtree
		return left!=null? left:right;		
	}	
	
	private static boolean findNode(TreeNode root, TreeNode n){
		if(root==null||n==null) return false;
		if(root==n) return true;
		return findNode(root.left,n) || findNode(root.right,n);
	}
	//4.7.3 lowest common ancestor of BT with parent link, 
	//no assumption p,q in tree needed because it return null in that situtation
	//comment it because we dont have node class with parent 
	//method 1 o(n):o(n) use hashset to store visited nodes, return first node we find it visted
	/*
	public static TreeNode LCABTParent(TreeNode root, TreeNode p, TreeNode q){
		HashSet<TreeNode> visited=new HashSet<>();
		while(p!=null || q!=null){
			if(p!=null){
				if(visited.contains(p)) return p;
				p=p.parent;
			}
			if(q!=null){
				if(visited.contains(q)) return q;
				q=q.parent;
			}
		}
		return null; //p,q not in tree
	}*/
	//method 2 no extra space, move p,q upward, return the intersection. 
	//But they could on different level and never meet at same pace
	//so we could get the depth of p,q and move deeper node upward until they are on the same level
	/*
	private static int getHeight(TreeNode p){
		int height=0;
		while(p!=null){
			height++;
			p=p.parent;
		}
		return height;
	}
	private static LCABTParent(TeeNode p, TreeNode q){
		int h1=getHeight(p);
		int h2=getHeight(q);
		if(h1<h2) return LCABTParent(q,p);
		int diff=h1-h2;
		for(int i=0;i<diff;++i){
			p=p.parent;
		}
		while(p!=null&&q!=null){
			if(p==q) return p;
			p=p.parent;
			q=q.parent;
		}
		return null;
	}
	*/
	
	//4.8 T1 is a tree with millions nodes, T2 is a tree with hundreds nodes, determine if T2 is a subtree of T1
    public static boolean isSubtree(TreeNode T1, TreeNode T2){
    	if(T1==null) return false;
    	if(T2==null) return true;
    	String t1i=inOrderString(T1,new StringBuilder());
    	String t2i=inOrderString(T2,new StringBuilder());
    	if(t1i.indexOf(t2i)==-1) return false;
    	String t1p=preOrderString(T1,new StringBuilder()); //must use stringBuilder
    	String t2p=preOrderString(T2,new StringBuilder());
    	if(t1p.indexOf(t2p)==-1) return false;
    	return true;
    }

	private static String inOrderString(TreeNode root,StringBuilder path){
		if(root==null) return path.toString();
		inOrderString(root.left,path);
		path.append(root.val);
		inOrderString(root.right,path);
		return path.toString();
		
	}
	private static String preOrderString(TreeNode root,StringBuilder path){
		if(root==null) return path.toString();
		path.append(root.val);
		preOrderString(root.left,path);
		preOrderString(root.right,path);
		return path.toString();
		
	}
	
	//4.9 path sum equals target (no need root to leave path) different as leetcode maxPathSum
	//important only contains path downward(leetcode includes  left-root-right path
	//method 2 stackOverflow, much cleaner
	public static List<List<Integer>> pathSumNoRootToLeaf1(TreeNode root, int target){
		List<List<Integer>> res=new ArrayList<>();
		dfs(root,target,new ArrayList<Integer>(),res);
		return res;
	}
	public static void dfs(TreeNode root, int target, List<Integer> path, List<List<Integer>> res){
		if(root==null) return;
		path.add(root.val);
		if(root.val==target) {
			res.add(new ArrayList<>(path));
		}
		dfs(root.left,target-root.val,path,res);
		dfs(root.right,target-root.val,path,res);
		dfs(root.left,target,new ArrayList<Integer>(),res);
		dfs(root.right,target,new ArrayList<Integer>(),res);
		path.remove(path.size()-1);
		
	}
	//method 1 solution from book o(nlgn):o(lgn) level order traversal, each node examine all paths end at this node
    public static List<List<Integer>> pathSumNoRootToLeaf(TreeNode root,int target){
		List<List<Integer>> res=new ArrayList<>();
		helper(root,target,0,new ArrayList<Integer>(),res);
		return res;
	}
	private static void helper(TreeNode root, int target,int level, ArrayList<Integer> path, List<List<Integer>> res){
		if(root==null) return;
		if(level<path.size()){
			path.set(level, root.val);
		}else{
			path.add(root.val);
		}
		int sum=0;
		for(int i=level;i>=0;--i){
			sum+=path.get(i);
			if(sum==target){
				List<Integer> temp=new ArrayList<>();
				for(int j=i;j<=level;++j){
					temp.add(path.get(j));
				}
				res.add(temp);
			}
		}
		helper(root.left,target,level+1,path,res);
		helper(root.right,target,level+1,path,res);
		
	}
	
//	static int max=Integer.MIN_VALUE;
//    public static int maxPathSum(TreeNode root) {
//        dfs(root,0);
//        return max;
//    }
//    private static void dfs(TreeNode root, int sum){
//        if(root==null) return;
//        
//        if(sum+root.val>max) max=sum;
//        dfs(root.left,sum+root.val);
//        dfs(root.right,sum+root.val);
//        dfs(root.left,sum);
//        dfs(root.right,sum);
//    }
    public static List<List<Integer>> findPath(TreeNode root, int target) {
		List<List<Integer>> ret = new ArrayList<>();
		if(root==null) return ret;
		List<Integer> curPath = new ArrayList<>();
		helper(ret, curPath, root, target);
		return ret;
    }
	public static void helper(List<List<Integer>> ret, List<Integer> curPath, TreeNode root, int target) {
		if(root==null) return;
		curPath.add(root.val);
		int sum = 0;
		for(int i=curPath.size()-1; i>=0; i--) {
			sum += curPath.get(i);
			if(sum==target) {
				ret.add(new ArrayList<Integer>(curPath.subList(i, curPath.size())));
			}
		}
		helper(ret, curPath, root.left, target);
		helper(ret, curPath, root.right, target);
		curPath.remove(curPath.size()-1);
		return;
	}
	public static TreeNode lowestCommonAncestor(TreeNode root,TreeNode p, TreeNode q){
        while(root!=null){
            if(Math.max(p.val,q.val)<root.val) root=root.left;
            else if(Math.min(p.val,q.val)>root.val) root=root.right;
            else break; //found, return root
        }
        return root;
    }
	
	public static void main(String[] args){
		//4.9
		TreeNode root9=new TreeNode(1);
		root9.left=new TreeNode(2);
		root9.right=new TreeNode(3);
		root9.left.left=new TreeNode(4);
		root9.left.left.left=new TreeNode(-1);
		root9.left.left.left.left=new TreeNode(-2);
		root9.left.right=new TreeNode(3);
		//System.out.println(maxPathSum(root9));
		System.out.println(pathSumNoRootToLeaf(root9,6));
		System.out.println(pathSumNoRootToLeaf1(root9,6));
		//bst 0-8
		int[] inorder=new int[]{0,1,2,3,4,5,6,7,8};
		int[] preorder=new int[]{5,1,0,3,2,4,6,7,8};
		int[] postorder=new int[]{0,2,4,3,1,8,7,6,5};
		TreeNode root=new TreeNode(5);
    	root.left=new TreeNode(1);
    	root.left.left=new TreeNode(0);
    	root.left.right=new TreeNode(3);
    	root.left.right.left=new TreeNode(2);
    	root.left.right.right=new TreeNode(4);
    	root.right=new TreeNode(6);
    	root.right.right=new TreeNode(7);
    	root.right.right.right=new TreeNode(8); 
    	
    	//4.7.2
    	System.out.println(LCABT(root,root.right.right,root.left).val);
 //   	System.out.println(lowestCommonAncestor((root.left,root.right.right,root.left).val);
    	System.out.println(LCABST(root.left,root.right.right,root.left).val);
    	//4.7.1
    	//System.out.println(LCABST(root,root.right.right,root.left).val);
    	
    	//4.8
//    	TreeNode root2=new TreeNode(6);
//    	root2.right=new TreeNode(7);
//    	root2.right.left=new TreeNode(8);
//    	System.out.println(preOrderString(root,new StringBuilder()));
//    	System.out.println(inOrderString(root,new StringBuilder()));
//    	System.out.println(isSubtree(root,root2));
    	
    	
    	
	}
}
