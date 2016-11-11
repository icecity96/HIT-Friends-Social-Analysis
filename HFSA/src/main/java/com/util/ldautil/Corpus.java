package com.util.ldautil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 语料库
 * @author ice_city
 */
public class Corpus {
	private List<int[]> documentList;
	private Vocabulary vocabulary;

	public Corpus() {
		documentList = new LinkedList<int[]>();
		vocabulary = new Vocabulary();
	}

	public int[] addDocument(List<String> document) {
		int[] doc = new int[document.size()];
		int i = 0;
		for (String word : document) {
			doc[i++] = vocabulary.getId(word,true);
		}
		documentList.add(doc);
		return doc;
	}

	public int[][] toArray() {
		return documentList.toArray(new int[0][]);
	}

	public int getVocabularySize() {
		return vocabulary.size();
	}

	@Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        for (int[] doc : documentList)
        {
            sb.append(Arrays.toString(doc)).append("\n");
        }
        sb.append(vocabulary);
        return sb.toString();
    }
	
	/**
	 * 语料库构建
	 * @param folderPath
	 * @return
	 * @throws IOException
	 */
	public static Corpus load(String folderPath) throws IOException {
		Corpus corpus = new Corpus();
		File folder = new File(folderPath);
		for(File file : folder.listFiles()) {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			List<String> wordList = new LinkedList<String>();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				String[] words = line.split(" ");
				for (String word : words) {
					if (word.trim().length() < 2) {
						continue;
					}
					wordList.add(word);
				}
			}
			bufferedReader.close();
			corpus.addDocument(wordList);
		}
		if (corpus.getVocabularySize() == 0) {
			return null;
		}
		return corpus;
	}
	
	public Vocabulary getVocabulary() {
		return vocabulary;
	}
	
	public int[][] getDocument() {
		return toArray();
	}
	
	/**
	 * 将文档用向量／数组表示
	 * @param path
	 * @param vocabulary
	 * @return
	 * @throws IOException 
	 */
	public static int[] loadDocument(String path,Vocabulary vocabulary) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		List<Integer> wordList = new LinkedList<Integer>();
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			String[] words = line.split(" ");
			for (String word : words) {
				if (word.trim().length() < 2) {
					continue;
				}
				Integer id = vocabulary.getId(word);
				if (id != null) {
					wordList.add(id);
				}
			}
		}
		bufferedReader.close();
		int[] result = new int[wordList.size()];
		int i = 0;
		for (Integer integer : wordList) {
			result[i++] = integer.intValue();
		}
		return result;
	}
}
