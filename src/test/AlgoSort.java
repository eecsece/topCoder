package test;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AlgoSort {
	
	public static void swap(int[] A, int i, int j){
		int temp=A[i];
		A[i]=A[j];
		A[j]=temp;
	}
	
    // bubble sort:repeatedly swapping adjacent elems if they are in wrong order
	//stable,  o(n^2):o(1) , it takes minimal time if already sorted
	public static void bubbleSort(int[] A){
		int n=A.length;
		for(int i=0;i<n;++i){
			boolean swapped=false;
			for(int j=1;j<n-i;++j){
				if(A[j]<A[j-1]){
					swap(A,j,j-1);
					swapped=true;
				}
			}
			if(!swapped) break;
		}
	}
	
	// insertiong sort: like sort cards in hand
	// o(n^2):o(1) stable, good for small amount or almost sorted except a few
	public static void insertionSort(int[] A){
		for(int i=1;i<A.length;++i){
			int j=i-1, temp=A[i];
			while(j>=0 && A[j]>temp) {
				A[j+1]=A[j];
				j--;
			}
			A[j+1]=temp;
		}
	}
	
	//selection sort: repeatedly finding the min of the unsorted part and put it to the beginning
	//o(n^2):o(1) unstable, because swap could change the relative position 4,2,4,1 ->1,2,4,4 first 4 become 2nd 4, order is not maintained
	//could be stable if we change the implementation, not use swap, insert the min in front
	public static void selectionSort(int[] A){
		for(int i=0;i<A.length;++i){
			int minIndex=i;
			for(int j=i+1;j<A.length;++j){
				if(A[j]<A[i]) minIndex=j;
			}
			swap(A,i,minIndex);
		}
	}
	
	//merge sort:devide and conquer, repeatedly devide input as two halves and merge the 2 sorted part
	//o(nlgn):o(n) stable
	//good for sort linkedlist, other nlgn algo do not apply to linkedlist
	public static void mergeSort(int[] A, int l, int r){
		if(l<r){
			int mid=l+(r-l)/2;
			mergeSort(A,l,mid);
			mergeSort(A,mid+1,r);
			merge(A,l,mid,r);
		}
	}
	private static void merge(int[] A, int l, int mid, int r){
		int n1=mid-l+1, n2=r-mid;
		int[] L=new int[n1], R=new int[n2];
		for(int i=0;i<n1;++i) L[i]=A[l+i];
		for(int i=0;i<n2;++i) R[i]=A[mid+1+i];
		int i=0,j=0,k=l;
		while(i<n1 && j<n2){
			if(L[i]<R[j]) A[k++]=L[i++];
			else  A[k++]=R[j++];
		}
		while(i<n1) A[k++]=L[i++];
		while(j<n2) A[k++]=R[j++];	
	}
	
	//heap sort: similar to selection sort,
	//divides input to sorted and unsorted part, use heap to repeatedly remove largest elem and move it to sorted part
	//o(nlgn):o(1) unstable
	public static void heapSort(int[] A){
		int n=A.length;
		//create max heap
		for(int i=n/2-1;i>=0;--i){ //swap from node with leaf, node n/2...n are leaves, 
			heapify(A,i,n);
		}
		//extract largest and reheapify
		for(int i=0;i<n;++i){
			int j=n-1-i; //note 0
			swap(A,0,j);
			heapify(A,0,j);	
		}	
	}
	private static void heapify(int[] A, int i, int size){
		int l=2*i+1, r=2*i+2;
		int maxIndex=i;
		if(l<size && A[l]>A[maxIndex]) maxIndex=l;
		if(r<size && A[r]>A[maxIndex]) maxIndex=r;
		if(maxIndex!=i){
			swap(A,i,maxIndex);
			heapify(A,maxIndex,size);
		}	
	}
	
	//quick sort: divide and conquer, pick a pivot and partition array, 
	//put smaller before pivot and larger after pivot
	//worst case o(n^2 always pick max or min as pivot, already sorted or all same) average: o(nlgn)
	//space o(lgn) for recursion, unstable
	public static void quickSort(int[] A, int l, int r){
		if(l<r){
			int p=partition(A,l,r);
			quickSort(A,l,p-1);
			quickSort(A,p+1,r);
		}
	}
	private static int partition(int[] A, int l, int r){
		//pick last elem as pivot
		int pivot=A[r], i=l;
		for(int j=l;j<=r-1;++j){
			if(A[j]<=pivot){
				swap(A,i,j);
				i++;
			}
		}
		swap(A,i,r);
		return i;
	}
	
	//bucket sort:map elems into buckets, sort(quick) each bucket and connect elems all buckets
	//best case o(n):o(n+buckets), stable
	public static void bucketSort(float[] A){
		int n=A.length;
		LinkedList[] buckets=new LinkedList[n];
		//map to bucket
		for(int i=0;i<n;++i){
			int bucketIndex= (int)(n*A[i]);
			if(buckets[bucketIndex]==null){
				buckets[bucketIndex]=new LinkedList();
			}
			buckets[bucketIndex].add(A[i]);
		}
		//sort each bucket
		for(int i=0;i<n;++i){
			if(buckets[i]!=null)Collections.sort(buckets[i]);
		}
		//concatenate all buckets
		int k=0;
		for(int i=0;i<n;++i){
			for(int j=0;buckets[i]!=null && j<buckets[i].size();++j){
				A[k++]=(float)buckets[i].get(j);
			}
		}
	}
	
	//shell sort: a variation of insertion sort, instead of moving contiguous elem, swap elems far from each other, 
	//sort every kth elems and reduce k until it reaches 1
	//1st k is n/2, 2nd is n/4,...3rd n/8...till k==1
	//average o(n^1.2):o(1),unstable;
	public static void shellSort(int[] A){
		int n=A.length;
		for(int k=n/2;k>=1;k/=2){
			for(int i=k;i<n;++i){
				for(int j=i-k;j>=0;j-=k){
					if(A[j]>A[j+k]){
						swap(A,j,j+k);
					}
				}
			}
		}
	}

	//counting sort: count each input and create output according to count
	//o(n):o(n) stable,works when n elems are in range [0,k) and k<=n
	//following implement according to algo book is stable
	public static void countingSort(int[] A,int k)  
	{  
		//A={2,5,3,4,2,3,0,3,1}, B={0,0...};
		int n=A.length;
	    int[] C = new int[k];
	    int[] B=new int[A.length];
	    //Count A=> c={1,1,2,3,1,1}
	    for(int i = 0; i < A.length; i++)  
	        C[A[i]]++;  
	    //get elems' rank
	    //=>c={1,2,3,7,8,9}
	    for(int i = 1; i < k; i++)  
	        C[i] += C[i - 1]; 
	    //put value to correct place
	    for(int j = A.length-1; j >= 0; j--)  
	    {  
	        B[C[A[j]] - 1] = A[j];  //eg. c[5]=9 means value 5 is the 9th item in A， should be put in output at index 8
	        C[A[j]]--;  
	    }  
	    for(int i=0;i<A.length;++i){
	    	A[i]=B[i];
	    }
	}  
	
	//radix sort: sort according to digit significance
	//make all elems with same length of digits, padding 0s to front, 
	//sort from least signicant digit to most significant digit(use counting sort)
	//o(kn):o(1) stable
	
	public static void sweepBomb(int[][] matrix, int n){
		for(int i=0;i<n;++i){
			for(int j=0;j<n;++j){
				if(matrix[i][j]==9){
					if(j>0)
						matrix[i][j-1]++;
					if(j<n-1)
						matrix[i][j+1]++;
					if(i>0)
						matrix[i-1][j]++;
					if(i<n-1)
						matrix[i+1][j]++;
					
				}
			}
		}
		

		
		
	}
	
	public static int[][] generateBomb(int n){
		int[][] matrix=new int[n][n];
		Random r=new Random();
		for(int i=0;i<r.nextInt(1000);++i){
			matrix[r.nextInt(n)][r.nextInt(n)]=9; //9 means bomb;
		}
		return matrix;
		
	}
	
	
	public static void main(String[] args){
		
		

		float[] E={0.78f,0.17f,0.39f,0.26f,0.72f,0.94f,0.21f,0.12f,0.23f,0.68f};
		int[] B={1,3,2,4};
		int[] C={1,4,3,2};
		C=new int[]{6,2,4,1,5,9};
		int[] D={34,936,775,228,742,782,448,868,126,945,722,324,138,38,570,290,762,271,32};
		
		//bubbleSort(A);
		//insertionSort(A);
		//selectionSort(A);
		//mergeSort(A,0,A.length-1);
		//heapSort(A);
		
//		bucketSort(E);
//		for(float a:E){
//			System.out.print(a+" ");
//		}
		System.out.println();
		shellSort(B);
		//quickSort(B,0,B.length-1);
		for(int a:B){
			System.out.print(a+" ");
		}
		System.out.println();
		//quickSort(C,0,C.length-1);
		shellSort(C);
		for(int a:C){
			System.out.print(a+" ");
		}
		System.out.println();
		shellSort(D);
		for(int a:D){
			System.out.print(a+" ");
		}
		System.out.println();
		
	}
}
