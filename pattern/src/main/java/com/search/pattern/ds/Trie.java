package com.search.pattern.ds;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.search.core.utility.Constants;

public class Trie<T> {

	private static List<Character> INDEX=Constants.INDEX;

	private static int SIZE=Trie.INDEX.size();


	public TrieNode<T> root=new TrieNode<T>();
	
	public Trie() {
		Trie.INDEX=Constants.INDEX;
		Trie.SIZE=Trie.INDEX.size();
	}
	
	public Trie(List<Character> index) {
		Trie.INDEX=index;
		Trie.SIZE=Trie.INDEX.size();
	}
	
	
	@SuppressWarnings("hiding")
	class TrieNode<T>{
		
		@SuppressWarnings("unchecked")
		TrieNode<T>[] children=new TrieNode[indexSize()];
		
		Set<T> records;
		
		
        TrieNode(){ 
        	records=new HashSet<T>(); 
            for (int i = 0; i < SIZE; i++) 
                children[i] = null; 
        } 
        
	}
	public List<Character> index() {
		return Trie.INDEX;
	}
	
	public int indexSize() {
		return Trie.SIZE;
	}
	

	// If not present, inserts key into trie 
    // If the key is prefix of trie node,  
    // just marks leaf node 
    public void insert(String key, T value) { 
    	
    	
        int level,length = key.length(),index;        
		TrieNode<T> pCrawl = this.root; 
        for (level = 0; level < length; level++){ 
            index = index().indexOf(key.toLowerCase().charAt(level)); 
            if (pCrawl.children[index] == null) 
                pCrawl.children[index] = new TrieNode<T>(); 
       
            pCrawl = pCrawl.children[index]; 
        } 
       
        // mark last node as leaf 
        pCrawl.records.add(value);
    } 

    public Set<T> search(String key){ 
    	
    	Set<T> records=
    			new TreeSet<T>(new TrieComparator<T>());
    	
        int level, length = key.length(),index=-1; 
        TrieNode<T> pCrawl = root; 
       
        for (level = 0; level < length; level++){ 
            index = index().indexOf(key.toLowerCase().charAt(level));  
       
            if (pCrawl.children[index] == null) {
                break; 
            }
       
            pCrawl = pCrawl.children[index]; 
        } 
       
        if(pCrawl != null && pCrawl.records!=null) {
        	records.addAll(pCrawl.records);
        }
    	
    	return records;
    }
    
    

    //Returns true if key presents in trie, else false 
    public Set<T> searchAll(String key){ 
    	
    	Set<T> records=
    			new TreeSet<T>(new TrieComparator<T>());
    	
        int level, length = key.length(),index=-1; 
        TrieNode<T> pCrawl = root; 
       
        for (level = 0; level < length; level++){ 
            index = index().indexOf(key.toLowerCase().charAt(level));  
       
            if (pCrawl.children[index] == null) {
                break; 
            }
       
            pCrawl = pCrawl.children[index]; 
        } 
       
        if(pCrawl != null && pCrawl.records!=null) {
        	records.addAll(pCrawl.records);
        }
        

        //Implementing BFS for iterating in to trie
        Queue<TrieNode<T>> queue = new LinkedList<TrieNode<T>>();
        queue.add(pCrawl);
        
        while(!queue.isEmpty()) {
        	TrieNode<T> node=queue.poll();
        	
        	for(int i=0; i<SIZE; i++) {
        		if(node != null && node.children[i]!=null) {

       	        	if(node.children[i].records!=null 
	       	        		&& node.children[i].records.size()>0) {

	       	        	records.addAll(node.children[i].records);
	       	        	
       	        	}
       	        	
        			queue.add(node.children[i]);
        		}
        	}
        	
        }

        return records;
    }
    

    
    public boolean contain(String key) {
    	
        int level, length = key.length(),index=-1; 
        TrieNode<T> pCrawl = this.root; 
       
        for (level = 0; level < length; level++){ 
            index = index().indexOf(key.toLowerCase().charAt(level));  
       
            if (pCrawl.children[index] == null)
            	return false; 
            
            pCrawl = pCrawl.children[index]; 
        } 
       
        if(pCrawl != null && pCrawl.records!=null)
        	return true;
        
    	return false;
    }
    
    public void update(String key, T value) {
    	
    	if(this.contain(key)) {
    		
            int level, length = key.length(),index=-1; 
            TrieNode<T> pCrawl = root; 
           
            for (level = 0; level < length; level++){ 
                index = index().indexOf(key.toLowerCase().charAt(level));  
           
                if (pCrawl.children[index] == null) {
                	this.insert(key, value);
                }
           
                pCrawl = pCrawl.children[index]; 
            } 
            
            // mark last node as leaf 
            for(T record : pCrawl.records) {
            	if(record.equals(value)) {
            		pCrawl.records.remove(record);
            	}
            }
            pCrawl.records.add(value);
            
    	}else {
    		this.insert(key, value);
    	}
    }
    
    
	
}
