package algorithms;

//public class Hackerrank {
//  
//}
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Hackerrank {

    /*
     * Complete the following function
     */
    static int maxDifference (int[] a) {
        if(a==null||a.length==0) return 0;
        int res=Integer.MIN_VALUE;
        for(int i=0;i<a.length-1;++i){
            for(int j=i+1;j<a.length;++j){
                if(a[j]>a[i]){
                    res=Math.max(res,a[j]-a[i]);
                }
            }
        }
        return res;
    }
    
    static int getComplete(int n){
    	int c=n, leftOne=0;
    	while(c!=0){
    		leftOne++;
    		c>>=1;
    	}
    	return n^((1<<leftOne)-1); //parentness is must;
    }

    public static int getDia(TreeNode root){
    	if(root==null) return 0;
    	int leftHeight=getHeight(root.left);
    	int rightHeight=getHeight(root.right);
    	return leftHeight+rightHeight+1;
    }
    private static int getHeight(TreeNode root){
    	if(root==null) return 0;
    	return Math.max(getHeight(root.left),getHeight(root.right))+1;
    }
    
    public static int count(int A, int B){
    	int count=0;	
    	for(int i=(int)Math.sqrt(A);i*i<=B;++i){
    		int val=i*i;
    		if(val>=A&&val<=B) count++;
    	}
    	return count;
    }
    List<Integer> list=new ArrayList<>();
    void push(int x){
    	list.add(x);
    	System.out.println(list.get(list.size()-1));
    }
    void pop(){
    	list.remove(list.size()-1);
    	if(list.isEmpty()) System.out.println("EMPTY");
    	else System.out.println(list.get(list.size()-1));
    }
    void inc(int pos, int val){
        for(int i=0;i<pos;++i){
        	list.set(i, list.get(i)+val);
        }
        System.out.println(list.get(list.size()-1));
    	
    }
    public static boolean findPattern(int[][] grid,int m,int n, int[][] pattern,int p,int q){
    	if(m<p||n<q) return false;
    	for(int i=0;i<=m-p;++i){
    		for(int j=0;j<=n-q;++j){
    			if(grid[i][j]!=pattern[0][0])continue;
    			if(checkPattern(grid,pattern,i,j,p,q)) return true;
    		}
    	}
    	return false;
    	
    }
    public static boolean checkPattern(int[][] grid, int[][] pattern, int i,int j,int p,int q){
    	
    	for(int k=0;k<p;k++){
    		for(int l=0;l<q;++l){
    			try{
    			if(grid[i+k][j+l]!=pattern[k][l]) return false;
    			}catch(Exception e){
    	    		System.out.println(k+" "+l+" "+i+" "+j);
    	    	}
    		}
    	}
    	return true;
    	
    	
    }
   
   
   
   public static int countLeaves(int N, int[] A){
	   int count=1;
	   for(int i=1;i<N;++i){
		   if(isLeft(i,A)) count++;
	   }
	   return count;
   }
   private static boolean isLeft(int n, int[] A){
	   for(int i=0;i<A.length;++i){
		   if(n%A[i]==0) return false;
	   }
	   return true;
   }
   
   public static void kNum(int start, int end){
	   int count=0;
	   for(int i=start;i<=end;++i){
		   if(isKNum(i)) {
			   count++;
			   System.out.println(i);
		   }
	   }
	   if(count==0) System.out.println("INVALID RANGE");
   }
   private static boolean isKNum(int n){
	   if(n<=0) return false;
	   if(n==1) return true;
	   long val=(long)n*n;
	   String s=String.valueOf(val);
	   int len=s.length();
	   if(len==1) return false;
	   int left=0, right=0;
	   left=Integer.parseInt(s.substring(0,len/2 ));  
	   right=Integer.parseInt(s.substring(len/2,len));
	   if(left>0 && right>0 && left+right==n) return true;
	   if(len%2==1) {
		   left=Integer.parseInt(s.substring(0,len/2+1 ));  
		   right=Integer.parseInt(s.substring(len/2+1,len));
		   if(left>0&&right>0&&left+right==n) return true;
	   }
	   return false;
   }
   

   

   static int triplets(int t, int[] d) {
       Arrays.sort(d);
       List<List<Integer>> res=new ArrayList<>();
       int count=0;
       for(int i=0;i<d.length-2;++i){
           int j=i+1, k=d.length-1;
           while(j<k){
             int sum=d[i]+d[j]+d[k];
             if(sum<=t){
               count+=k-j;
               j++;
               //k=d.length-1;
             }else{
            	 k--;
             }
             
           }
       }
       return count;
    }
   
   public static void findSmallest(String s){
	   s = new StringBuilder(s).reverse().toString();
       int[] expected = new int[26];
       for (int i = 0; i < s.length(); i++) {
           expected[s.charAt(i) - 'a']++;
       }
       
       for (int i = 0; i < 26; i++) {
    	   assert(expected[i]%2==0);
           expected[i]/=2;
       }
       int start=0;
       for(int i=0;i<s.length()/2;++i){
    	   for(int j=0;j<26;++j){
    		  if(expected[j]==0) continue;
    		  int k=start;
    		  while(k<s.length() && s.charAt(k)-'a'!=j) k++;
    		  int[] actual=new int[26];
    		  boolean ok=true;
    		  for(int kk=k;kk<s.length();++kk){
    			  char c=s.charAt(kk);
    			  actual[c-'a']++;	  
    		  }
    		  for(int kk=0;kk<26;++kk){
    			  if(actual[kk]<expected[kk]) {
    				  ok=false;
    				  break;
    			  }
    		  }
    		  if(ok){
    			  System.out.print((char)(j+'a'));
    			  expected[j]--;
    			  start=k+1;
    			  break;
    		  }    		   
    	   }
       }
       
   }
   
   public static void findKClosest(int[] A, int target, int k){
	   if(A==null||A.length<k) return;
	   int j=binarySearch(A,target);
	   int i=j-1;
	   if(A[j]==target) j++;
	   while(k>0 && i>=0 && j<A.length){
		   if(Math.abs(A[i]-target)<Math.abs(A[j]-target)){
			   System.out.print(A[i]+" ");
			   i--;
		   }else{
			   System.out.print(A[j]+" ");
			   j++;
		   }
		   k--;
	   }
	   
		while(i>=0 && k>0){
			   System.out.print(A[i--]);
			   k--;
		}
		while(j<A.length && k>0){
			System.out.print(A[j++]);
			k--;	   
		}
   }
   private static int binarySearch(int[] A, int target){
	   int low=0,high=A.length-1;
	   while(low<=high){
		   int mid=low+(high-low)/2;
		   if(A[mid]==target) return mid;
		   else if(A[mid]<target) low=mid+1;
		   else high=mid-1;
	   }
	   return low;
   }
	  
   
   
	   
   
  
    public static void main (String[] args) {
    	TreeNode root=new TreeNode(40);    
    	int[] d={1,2,3,4,5,6};
    	int[][] m=new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    	int [] level=new int[]{2,1,5,9,13,14,15,16,12,8,4,3,6,10,11,7};
    	int[] A={12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};
    	findKClosest(A,35,4);
 //   	findSmallest("eageaggg");
    	
    	//System.out.println();
//    	Scanner sc=new Scanner(System.in);
    	
//        int t=sc.nextInt();
//        for(int i=0;i<t;++i){
//            System.out.println(makePalindrome(sc.next()));
//        }
//    	int[] nums={363374326,364147530,61825163,1073065718,1281246024,1399469912,428047635,491595254,879792181,1069262793};
    	//System.out.println(countPairs(nums,10,1));
    	

    	
    	//System.out.println(triplets2(22,d));
    	//System.out.println(triplets(22,d));
    	//System.out.println(4879*4879);
    	//kNum(1,100);
    	//System.out.println(isKNum(3));
    	
    	//countUneatenLeaves(13,d);  	
    	
    	//Scanner sc=new Scanner(System.in);
        //int t=sc.nextInt();
        //sc.nextLine();
        
//   	int T=sc.nextInt();
//   	//sc.skip("\\s");
//   	for(int i=0;i<T;++i){
//   		int B=sc.nextInt(),W=sc.nextInt();
//   		int X=sc.nextInt(),Y=sc.nextInt(),Z=sc.nextInt();
//   		System.out.println();
//   		
//   	}
//        	int N=sc.nextInt();
//        	int K=sc.nextInt();
//        	int[] nums=new int[N];
//        	for(int j=0;j<N;++j){
//        		nums[j]=sc.nextInt();
//        	}
//        	System.out.println(countPairs(nums,N,K));
//        	
//        }
//        for(int x=0;x<t;++x){
//            int m=sc.nextInt(), n=sc.nextInt();
//            int[][] grid=new int[m][n];
//            for(int i=0;i<m;++i){
//                String temp=sc.next();
//                for(int j=0;j<n;++j){
//                	grid[i][j]=temp.charAt(j)-'0';
//                }
//            }
//           int p=sc.nextInt(), q=sc.nextInt();
//           int[][] pattern=new int[p][q];
//           for(int i=0;i<p;++i){
//               String temp=sc.next();
//               for(int j=0;j<q;++j){
//               	pattern[i][j]=temp.charAt(j)-'0';
//               }
//           }
//           
//           if(findPattern(grid,m,n,pattern,p,q)) System.out.println("YES");
//           else System.out.println("NO");
//           
//        }
    	

        
      
    }
}