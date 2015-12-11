package algorithms;

import java.util.Stack;

 class minStack {

	    //use two stacks to implement,one stack tracks all elements, the other tracks cur minimum elements
	    Stack<Integer> stack=new Stack<Integer>();
	    Stack<Integer> minStack=new Stack<Integer>();
	    
	    public void push(int x) {
	       stack.push(x);
	       if(minStack.isEmpty() || x<=minStack.peek()) minStack.push(x); //=
	    }

	    public void pop() {
	        //if(stack.pop().equals(minStack.peek())) minStack.pop(); //note must use equals, == will only work for -127,128
	       int a=minStack.peek();
	    	if(stack.pop()==a)
	        	minStack.pop();
	    }

	    public int top() {
	        return stack.peek();
	    }

	    public int getMin() {
	        return minStack.peek();
	        
	    }
	    
	    public static void main(String[] args){
	    	minStack s=new minStack();
	    	s.push(512);
	    	s.push(-1024);
	    	s.push(-1024);
	    	s.push(512);
	    	s.pop();
	    	System.out.println(s.getMin());
	    	s.pop();
	    	System.out.println(s.getMin());
	    	s.pop();
	    	System.out.println(s.getMin());
	    	Integer a=127;
	    	Integer b=127;
	    	System.out.println(a==b);
	    }
	

}
