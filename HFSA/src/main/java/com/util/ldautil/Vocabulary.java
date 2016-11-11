package com.util.ldautil;

import java.util.Map;
import java.util.TreeMap;

/**
 * set of words
 * @author ice_city
 *
 */
public class Vocabulary {
	private Map<String, Integer> word2idMap;
	private String[] id2wordMap;
	
	public Map<String, Integer> getWord2idMap() {
		return word2idMap;
	}

	public void setWord2idMap(Map<String, Integer> word2idMap) {
		this.word2idMap = word2idMap;
	}

	public String[] getId2wordMap() {
		return id2wordMap;
	}

	public void setId2wordMap(String[] id2wordMap) {
		this.id2wordMap = id2wordMap;
	}

	public Vocabulary() {
		word2idMap = new TreeMap<String,Integer>();
		id2wordMap = new String[1024];
	}
	
	public Integer getId(String word) {
		return getId(word,false);
	}
	
	public String getWord(int id) {
		return id2wordMap[id];
	}
	
	public Integer getId(String word,boolean create) {
		Integer id = word2idMap.get(word);
		if (!create) {
			return id;
		}
		if (id == null) {
			id = word2idMap.size();
		}
		word2idMap.put(word, id);
		if (id2wordMap.length - 1 < id) {
			//调整id2wordMap的大小
			resize(word2idMap.size()*2);
		}
		id2wordMap[id] = word;
		return id;
	}
	
	private void resize(int n) {
		String[] newArray = new String[n];
		System.arraycopy(id2wordMap, 0, newArray, 0, id2wordMap.length);
		id2wordMap = newArray;
	}
	
	private void loseWeight() {
		if (size() == id2wordMap.length) {
			return;
		}
		resize(word2idMap.size());
	}
	
	public int size() {
		return word2idMap.size();
	}
	
	@Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < id2wordMap.length; i++)
        {
            if (id2wordMap[i] == null) break;
            sb.append(i).append("=").append(id2wordMap[i]).append("\n");
        }
        return sb.toString();
    }
}
