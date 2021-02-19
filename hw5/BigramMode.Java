package il.ac.tau.cs.sw1.ex5;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.text.StyledEditorKit.BoldAction;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14000;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	
	/**
	 * 
	 * @param String word
	 * @param array of Strings vocabulary
	 * @param len - the number of cells that initialized in the vocabulary array
	 * @return true if the given word founded in the vocabulary else return false
	 */
	private boolean inVocabulary(String word,String[] vocabulary,int len) {
		for(int i=0;i<len;i++) {
			if(word.equals(vocabulary[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param chars array
	 * @return true if all the chars are numbers else false
	 */
	private static boolean justNumbers(char[] word) {
		boolean justNumbers= true;
		for(int j=0;j<word.length;j++) {
			if(!Character.isDigit(word[j])) {
				justNumbers=false;
			}
		}
		return justNumbers;
	}
	/**
	 * 
	 * @param String word
	 * @param array of Strings vocabulary
	 * @return the index of the word if the word is in the vocabulary else returns -1
	 */
	private static int index(String[] vocabulary,String word) {
		int index=ELEMENT_NOT_FOUND;
		for(int i=0;i<vocabulary.length;i++) {
			if(vocabulary[i].equals(word)) {
				index=i;
				break;
			}
		}
		return index;
	}
	/**
	 * 
	 * @param String word
	 * if the word is a number it will be changed to some_num
	 */
	private static void isnumber(String word) {
		char[] chars=word.toCharArray();
		if(justNumbers(chars)) {
			word=SOME_NUM;
			}
	}

	/**
	 * @param filename
	 * builds vocabulary from the legal words in the file
	 * the vocabulary size will be at most 14,000 words
	 * legal words are strings with at least one English letter or the string is a number
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		File vocaFile= new File(fileName);
		BufferedReader vReader=new BufferedReader(new FileReader(vocaFile));
		String [] vocabulary =new String[MAX_VOCABULARY_SIZE];
		String line;
		int cnt=0;
		boolean hasNum=false;
		while((line = vReader.readLine()) != null && cnt<MAX_VOCABULARY_SIZE) {
			String [] words=line.split(" ");
			for(int i=0 ; i<words.length ; i++) {
				char [] chars= words[i].toCharArray();
				boolean hasLetter= false;
				for(int j=0;j<chars.length;j++) {
					if(Character.isLetter(chars[j])) {
						hasLetter=true;
					}
				}
				if(hasLetter && !(inVocabulary(words[i].toLowerCase(), vocabulary, cnt))) {
					vocabulary[cnt]=words[i].toLowerCase();
					cnt++;
				}
				else if(justNumbers(chars) && !hasNum) {
					vocabulary[cnt]=SOME_NUM;
					cnt++;
					hasNum=true;
				}
			}
			
		}
		String[] reducedVocabulary=new String [cnt];
		for(int k=0;k<cnt;k++) {
			reducedVocabulary[k]=vocabulary[k];
		}
		vReader.close();
		return reducedVocabulary;
	}
	
	
	/**
	 * @pre legal file name
	 * @param filename
	 * @param array of strings vocabulary
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 * @return 2D array that the cell [i][j] is the number of times that the word j comes after i in the file
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int [][] bigramCounts=new int[vocabulary.length][vocabulary.length];
		File vocaFile= new File(fileName);
		BufferedReader vReader=new BufferedReader(new FileReader(vocaFile));
		String line;
		while((line = vReader.readLine()) != null) {
			String [] words=line.split(" ");
			for(int i=0 ;i<words.length-1;i++) {
				 isnumber(words[i]);
				 isnumber(words[i+1]);
				
				if(inVocabulary(words[i].toLowerCase(),vocabulary,vocabulary.length)&&inVocabulary(words[i+1].toLowerCase(), vocabulary, vocabulary.length)) {
					bigramCounts[index(vocabulary, words[i].toLowerCase())] [index(vocabulary, words[i+1].toLowerCase())]+=1;
				}
				
			}
		}
		vReader.close();
		//System.out.println(Arrays.deepToString(bigramCounts));
		return bigramCounts;

	}
	
	
	/**
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 * @param filename
	 * creates two files- 
	 * 1.filename.voc with number of words in the vocabulary and those words and their indices in the vocabulary
	 * 2.filename.counts with pairs of words that in the file and their indices and the number of times they appeared as a pair
	 * (word1 and word2 is a pair only if word2 comes right after word1 in the file and they both legal words)
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		File voc=new File (fileName+VOC_FILE_SUFFIX);
		BufferedWriter vocWriter =new BufferedWriter(new FileWriter(voc));
		vocWriter.write(mVocabulary.length+" words"+"\n");
		for(int i=0;i<mVocabulary.length;i++) {
			vocWriter.write(i+","+mVocabulary[i]+"\n");
		}
		vocWriter.close();
		File counts=new File(fileName+COUNTS_FILE_SUFFIX);
		BufferedWriter countsWriter =new BufferedWriter(new FileWriter(counts));
		for(int j=0;j<mBigramCounts.length;j++) {
			for(int k=0;k<mBigramCounts.length;k++) {
				if(mBigramCounts[j][k]>0) {
					countsWriter.write(j+","+k+":"+mBigramCounts[j][k]+"\n");
				}
			}
		}
		countsWriter.close();
		
	}
	
	
	
	/**
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		File voc=new File(fileName+VOC_FILE_SUFFIX);
		BufferedReader vocReader= new BufferedReader(new FileReader(voc));
		String[] vc=vocReader.readLine().split(" ");
		String [] loadvoc=new String[Integer.parseInt(vc[0])];
		String line;
		while ((line = vocReader.readLine()) != null) {
			String[] cellvoc=line.split(",");
			loadvoc[Integer.parseInt(cellvoc[0])]=cellvoc[1];
		}
		vocReader.close();
		File count=new File (fileName+COUNTS_FILE_SUFFIX);
		BufferedReader countReader= new BufferedReader(new FileReader(count));
		String countline;
		int[][] loadBigramCount=new int[loadvoc.length][loadvoc.length];
		while ((countline = countReader.readLine()) != null) {
			String[] cellcount=countline.split(",|\\:");
			loadBigramCount[Integer.parseInt(cellcount[0])][Integer.parseInt(cellcount[1])]=Integer.parseInt(cellcount[2]);
		}
		countReader.close();
		mVocabulary=loadvoc;
		mBigramCounts=loadBigramCount;
		
	}

	
	
	/**
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		
		return index(mVocabulary,word);
	}
	
	
	
	/**
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		isnumber(word1);
		isnumber(word2);
		if(inVocabulary(word1, mVocabulary, mVocabulary.length)&&inVocabulary(word2, mVocabulary, mVocabulary.length)) {
			return mBigramCounts[getWordIndex(word1)][getWordIndex(word2)];
		}
		return 0;
	}
	
	
	/**
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most frequently after word (if a bigram starting with
	 * word was never seen, $ret will be null)
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		isnumber(word);
		int x=getWordIndex(word);
		int max=0;
		int index=0;
		for (int i=0;i<mBigramCounts.length;i++) {
			if(mBigramCounts[x][i]>max) {
				max=mBigramCounts[x][i];
				index=i;
			}
		}
		if(max>0) {
		return mVocabulary[index];
		}
		return null;
	}
	
	
	/**
	 * @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are separated with a single space
	 * @post: if sentence is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String[] sentenceWords=sentence.split(" ");
		if(sentenceWords.length>0) {
		isnumber(sentenceWords[0]);
			if(!(inVocabulary(sentenceWords[0], mVocabulary, mVocabulary.length))) {
				return false;
		}
		}
		for(int i=0;i<sentenceWords.length-1;i++) {
			isnumber(sentenceWords[i+1]);
			if(!(inVocabulary(sentenceWords[i+1], mVocabulary, mVocabulary.length))) {
				return false;
			}
			else if(getBigramCount(sentenceWords[i], sentenceWords[i+1])==0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param array
	 * @return true if the array is not all zeros
	 */
	private static boolean notZeroVector(int[]arr) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]!=0) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param array
	 * @return the square root of the sum of the array squared elements
	 */
	private static double sigmaSqrt(int[]arr) {
		double sum=0;
		for(int i=0;i<arr.length;i++) {
			sum+=Math.pow(arr[i], 2);
		}
		return Math.sqrt(sum);
	}
	
	/**
	 * @pre: arr1.length = arr2.legnth
	 * @post if arr1 or arr2 are only filled with zeros, $ret = 0, otherwise we return the cosineSimilarity of the vectors
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		double score=0.0;
		if(notZeroVector(arr1) && notZeroVector(arr2)) {
			double sum=0;
			for(int i=0;i<arr1.length;i++) {
				sum+=(arr1[i]*arr2[i]);
			}
			score=(sum/(sigmaSqrt(arr1)*sigmaSqrt(arr2)));
		}
		return score;
	}

	
	/**
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		int x =getWordIndex(word);
		if(mVocabulary.length==1) {
			return mVocabulary[0];
		}
		int index=0;
		double maxvector=0;
		double y;
		for(int i=0;i<mVocabulary.length;i++) {
			if(i==x) {
				i++;
			}
				
			y=calcCosineSim(mBigramCounts[x], mBigramCounts[i]);
			if(y>maxvector) {
				maxvector=y;
				index =i;
			}
		}

		
		
		return mVocabulary[index];
	}
	
}
