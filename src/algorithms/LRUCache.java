package algorithms;

import java.util.HashMap;
import java.util.Map;

class DoubleLinkedListNode{
    int key;
    int val;
    DoubleLinkedListNode prev;
    DoubleLinkedListNode next;
    DoubleLinkedListNode(int key,int val){
        this.key=key;
        this.val=val;
    }
}

public class LRUCache {
    //remove least recently used item if full
    //dobuleLinkedList store most recently used to least recently used items, hashmap used to get the listNode in o(1) time
    //listNode store (key,val), map stores(key, ListNode)
    //get function just call the key but no change: return val and move node to list head as the most recently used item
    //set function call the key and change value: if key is already in map, no extra spaces needed, just get the node, modify its value,move node to list head; if key not in map, need an extra space, if full, remove least recently used item(list tail), add new Node as head, if not full, just add
    
    Map<Integer,DoubleLinkedListNode> map=new HashMap<>();
    DoubleLinkedListNode dummyHead=new DoubleLinkedListNode(0,0);
    DoubleLinkedListNode dummyEnd=new DoubleLinkedListNode(0,0);
    int capacity;//how many keys can be stored
    int len;
    
    public LRUCache(int capacity) {
       this.capacity=capacity;
       this.len=0;
       dummyHead.next=dummyEnd;
       dummyEnd.prev=dummyHead;
    }
    public void set(int key, int value) {
        if(map.containsKey(key)){
            DoubleLinkedListNode node=map.get(key);
            node.val=value;
            removeNodeFromList(node);//remove from double linkedlist
            setHead(node);
        }else{
            DoubleLinkedListNode node=new DoubleLinkedListNode(key,value);
            if(len<capacity) len++;
            else{//full, remove least recently used node
                map.remove(dummyEnd.prev.key);
                removeNodeFromList(dummyEnd.prev);
            }
            setHead(node);
            map.put(key,node);
        }
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            DoubleLinkedListNode n=map.get(key);
            removeNodeFromList(n);
            setHead(n);
            return n.val;
        }else return -1;
    }
    private void removeNodeFromList(DoubleLinkedListNode n){
        DoubleLinkedListNode prev=n.prev;
        DoubleLinkedListNode post=n.next;
        prev.next=post;
        post.prev=prev;
    }
    private void setHead(DoubleLinkedListNode n){
        n.next=dummyHead.next;
        n.prev=dummyHead;
        n.next.prev=n;
        dummyHead.next=n;
    }
    
    public static void main(String[] args){
    	LRUCache cache=new LRUCache(2);
    	cache.set(2, 1);
    	cache.set(1,1);
    	cache.get(2);
    	cache.set(4, 1);
    	cache.get(1);
    	cache.get(2);
    }
    
    
}
