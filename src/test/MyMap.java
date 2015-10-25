package test;

public class MyMap<K,V> {
  Entry[] table;
  int value;
  double loadFactor=0.75;
  int modCount;//count modification times, used for fail-fast mechanism
  int threshold;
  int size; //# of key-value mappings in the map
  static class Entry<K,V>{
	  final K key;
	  V value;
	  Entry<K,V> next;
	  int hash;
	  public Entry(K key, V value, Entry<K,V> next, int hash){
		  this.key=key;
		  this.value=value;
		  this.next=next;
		  this.hash=hash;
	  }
  }
  public MyMap(){
	  table=new Entry[16];
	  threshold=(int)(loadFactor*table.length);
  }
  public V put(K key, V value){
	  if(key==null) return putForNullKey(value); //allows null key and null value, put null entry in first bucket of the table
	  int hash=hash(key.hashCode());//calculate hash value according to key.hashcode()
	  int i=indexFor(hash,table.length);
	  for(Entry<K,V> e=table[i];e!=null;e=e.next){ //locate exact object in linkedlist
		  if(e.hash==hash&&(e.key==key||key.equals(e.key))){
			  V oldValue=e.value;
			  e.value=value;
			  return oldValue; //modify and return old value
		  }
	  }
	  //not found key
	  modCount++;//increment modification times, note no modCount change if just update value
	  addEntry(hash,key,value,i); //add it to front of table[i] 
	  return null;
  }
  public V get(K key){
	  //if(key==null) return getForNullKey();
	  int hash=hash(key.hashCode());
	  int i=indexFor(hash,table.length);
	  for(Entry<K,V> e=table[i];e!=null;e=e.next){
		  if(e.hash==hash&&(e.key==key)||key.equals(e.key)){
			  return e.value;
		  }
	  }
	  return null;
  }
  public void clear(){
	  modCount++;
	  Entry[] tab=table;
	  for(int i=0;i<tab.length;++i){
		  tab[i]=null;
	  }
	  size=0;
  }
  public boolean isEmpty(){
	  return size==0;
  }
  public int hash(int h){
	  h^=(h>>>20)^(h>>>12);
	  return h^(h>>>7)^ (h>>>4);
  }
  public int indexFor(int h, int length){
	  return h&(length-1);
  }
  public void addEntry(int hash,K key, V value, int bucketIndex){
	  Entry<K,V> e=table[bucketIndex];
	  table[bucketIndex]=new Entry<K,V> (key,value,e,hash);
	  if(size++>=threshold){ //size++ not ++size
		  resize(2*table.length);
	  }
  }
  public void resize(int newCapacity){
//	  Entry[] oldTable=table;
//	  int oldCapacity=oldTable.length;
	  Entry[] newTable=new Entry[newCapacity];
	  for(int i=0;i<table.length;++i){
		  Entry<K,V> e=table[i];
		  if(e!=null){
			  //
		  }
	  }
	  table=newTable;
	  threshold=(int)(newCapacity*loadFactor);
	  
  }
  private V putForNullKey(V value){
	  for(Entry<K,V> e=table[0];e!=null;e=e.next){
		  if(e.key==null){
			  V oldValue=e.value;
			  e.value=value;
			  return oldValue;
		  }
	  }
	  modCount++;
	  addEntry(0,null,value,0);
	  return null;
  }
  
}
