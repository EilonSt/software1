package il.ac.tau.cs.sw1.hw3;

import java.util.Arrays;

public class StringUtils {
	/**
	 *@param String word1
	 *@param String word2
	 *@return true if word1 is coming before word2 in the lexicographic order else return false
	 *if the words are the same we return true 
	 */
	private static boolean lexi(String word1,String word2) {
		if(word1.equals(word2)) {
			return true;
		}
		for(int i=0;i<Math.min(word1.length(),word2.length());i++) {
			if(word1.charAt(i)>word2.charAt(i)) {
				return false;
			}
			if(word1.charAt(i)<word2.charAt(i)) {
				return true;
			}
		}
		if(word1.length()>word2.length()) {
			return false;
		}
		return true;
	}

	/**
	 *@param String str
	 *@return string of the longest sorted sequence of words by lexicographic order in the string
	 *if the length is tie we take the sequence that is closer to the end of the original string
	*/
	public static String findSortedSequence(String str)
	{
		int max=0;
		int cnt=0;
		int index=0;
		String[] words=str.split(" ");
		for(int i=0;i<words.length-1;i++) {
			if(lexi(words[i],words[i+1])) {
				cnt++;
				if(cnt>=max) {
					max=cnt;
					index=i+1;
				}
			}
			else cnt=0;
			
		}
		String longestString=words[index-max];
		for(int j=index+1-max;j<=index;j++) {
			longestString+=" "+words[j];
		}
		//TODO
		return longestString;
		
	}
/**
 *@param c character
 *@param String str
 *@return true if the character c has an even appearances in the String str else return false
 *if c does not appear in str we return false 
 */
	private static boolean even(String str,char c) {
		int cnt=0;
		for (int i=0;i<str.length();i++) {
			if(c==str.charAt(i)) {
				cnt++;
			}
		}
		if(cnt!=0&&cnt%2==0) {
			return true;
		}
		return false;
	}
	/**
	 *@param c character
	 *@param String str
	 *@return true if the character c has an odd appearances in the String str else return false
	 *if c does not appear in str we return false 
	 */
	private static boolean odd(String str,char c) {
		int cnt=0;
		for (int i=0;i<str.length();i++) {
			if(c==str.charAt(i)) {
				cnt++;
			}
		}
		if(cnt!=0&&cnt%2==1) {
			return true;
		}
		return false;
	}
/**
 * @param String a
 * @param String b
 * @return a string that contains only letters that has an odd appearances in a and even appearances in b
 * the order of the characters in the string will be the same order they appear in a
 */
	public static String parityXorStrings(String a, String b)
	{
		String str="";
		for(int i=0;i<a.length();i++) {
			if(odd(a,a.charAt(i)) && even(b,a.charAt(i))) { 
				str+=a.charAt(i);
			}
		
		}
		//TODO
		return str;
		
	}
	/**
	 * @param String a
	 * @param String b
	 * @return true if b is an anagram of a else return false
	 */
	public static boolean isAnagram(String a, String b)
	{
		a = a.replace(" ", "");
		b = b.replace(" ", "");
		char[] aWords=a.toCharArray();
		char[] bWords=b.toCharArray();
		Arrays.sort(aWords);
		Arrays.sort(bWords);
		if(Arrays.equals(aWords,bWords)) {
			return true;
		}
		//TODO
		return false;
		
	}

}
