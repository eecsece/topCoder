package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CC150ch3StackAndQueue {
  //3.1 use single array to implement 3 stacks
  //method 1 fixed length; method 2 flexible size, design array as circular
	class ArrayStack<E>{
		int stackSize=100;
		Object[] buffer=new Object[stackSize*3];
		int[] stackPointer={-1,-1,-1};
		void push(int i,int stackId)throws Exception{
			if(stackPointer[stackId]+1>=stackSize) throw new Exception("stack full");
		}
		E pop(int stackId) throws Exception{
			if(isEmpty(stackId)) throw new Exception("stack is Empty");
			E temp=(E)buffer[stackId+stackId*stackSize];
			buffer[stackId+stackId*stackSize]=null;
			--stackPointer[stackId];
			return temp;
		}
		E peek(int stackId){
			E temp=(E)buffer[stackId+stackId*stackSize];
			return temp;
		}
		boolean isEmpty(int stackId){
			return stackPointer[stackId]==-1;
		}
		
	}
	
	//3.2 leetcode min stack
	
	//3.3 stackSet, create new stack if reaches some threshold
	//follow up: pop specific stack, issue here is after poping, middle stack might not full, 
	//we need to choose either move over items on following stacks or choose leave it not full and add extra code to handle complexity in push 
	class StackSet{
		List<Stack> stacks=new ArrayList<>();
		int capacity;
		StackSet(int cap){
			capacity=cap;
		}
		public void push(int v){
			Stack last=stacks.size()==0? null:stacks.get(stacks.size()-1);
			//suedo code since java does not provide is full
//			if(last!=null||!last.isFull()){
//				last.push(v);
//			}else{
//				Stack<Integer> s=new Stack<Integer>(capacity);
//				s.push(v);
//				stacks.add(s);
//			}
			
			//if(last.isFull())
		}
		public int pop(){
			if(stacks.size()==0) return -1;
			Stack<Integer> last=stacks.get(stacks.size()-1);
			if(!last.isEmpty()) {
				int temp=last.pop();
				if(last.isEmpty()) stacks.remove(stacks.size()-1);
				return temp;
			}
			return -1;
		}
		//public int popAt(int stackId){
			//issue here is after poping, middle stack might not full, 
			//we need to choose either move over items on following stacks 
			//or choose leave it not full and add extra code to handle complexity in push 

		//}
	}
	
	//3.4 hanoi tower
	//3.5 implement a queue using two stacks, 
	//key is in dequeue:f s2 not empty, pop s2, otherwise push all of s1 to s2 and then pop s2
	public class MyQueue<E>{
		Stack<E> s1=new Stack<>();
		Stack<E> s2=new Stack<>();
		
		void enqueue(E elem){
			if(elem!=null){
				s1.push(elem);
			}
		}
		E dequeue(){
			if(s2.isEmpty()){ //if s2 not empty, pop s2, otherwise push all of s1 to s2 and then pop s2
			  while(!s1.isEmpty()){
				s2.push(s1.pop());
			  }
			}
			return s2.pop();
		}
		boolean isEmpty(){
			return s1.isEmpty() && s2.isEmpty();
		}
		
	}
	//3.6 sort a stack ascendingly(biggest on top), can only use one extra stack, no other data structures allowed
	//when input stack is not empty, pop top, if top>res.peek,push to res, otherwise pop res and push to input until res.top<top
	public static Stack<Integer> sort(Stack<Integer> s){
		Stack<Integer> res=new Stack<>();
		if(s==null||s.isEmpty()) return res;
		while(!s.isEmpty()){
			int top=s.pop();
			while(!res.isEmpty() && res.peek()>top){
				s.push(res.pop());
			}
			res.push(top);
		}
		return res;
	}
	
	//3.7 create a datastructure for dog/cat adoption, can choose earliest arrival dog or cat or both
	//dog queue and cat queue
	
	public static void main(String[] args){
		//3.6 sort a stack with an extra helper stack
		Stack<Integer> s=new Stack<>();
		s.push(1);
		s.push(2);
		s.push(4);
		s.push(3);
		Stack<Integer> res=sort(s);
		while(!res.isEmpty()){
			System.out.print(res.pop());
		}
	}
}
