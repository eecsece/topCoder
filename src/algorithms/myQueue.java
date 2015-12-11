package algorithms;

import java.util.Stack;

public class myQueue {
	Stack<Integer> s1=new Stack<>(); //stack represents end part of the queue, push to this stack
    Stack<Integer> s2=new Stack<>(); //stack represents front part of the queue, pop from this stack
    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(s2.isEmpty()){
          while(!s1.isEmpty()){
            s2.push(s1.pop());
          }
        }
        s2.pop();
    }

    // Get the front element.
    public int peek() {
        if(s2.isEmpty()){
          while(!s1.isEmpty()){
            s2.push(s1.pop());
          }
        }
        return s2.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    public static void main(String[] args){
    	myQueue q=new myQueue();
    	q.push(1);
    	q.push(2);
    	q.push(3);
    	q.pop();
    	q.peek();
    	q.push(4);
    	q.push(5);
    	q.pop();
    	while(!q.empty()){
    		System.out.println(q.peek());
    		q.pop();
    	}
    	
    	
    	
    }
}
