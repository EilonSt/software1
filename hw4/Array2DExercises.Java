package il.ac.tau.cs.sw1.ex4;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Array2DExercises {

	/**
	 * 
	 * @param a 2D integer array
	 * @return the maximum value in the 2D parameter array a.
	 */
	public static int max(int[][] a) {
		int first=0;
		while(a[first].length==0) {
			first++;
		}
		int max=a[first][0];
		for (int i=0;i< a.length;i++){
			for (int j=0;j<a[i].length;j++) {
				if(a[i][j]>max) {
					max=a[i][j];
				}
			}
		}
		// TODO Auto-generated method stub
		
		return max;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @param i a legal index of a row in a
	 * @return the sum of the elements in Row i of a
	 */
	public static int rowSum(int[][] a, int i) {
		// TODO Auto-generated method stub
		int sum=0;
		for (int j=0;j<a[i].length;j++) {
			sum+=a[i][j];
		}
		return sum;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @param i a legal index of a column in a
	 * @return the sum of the elements in Column i of a 
	 * (careful with rows of different lengths!).
	 */
	public static int columnSum(int[][] a, int i) {
		// TODO Auto-generated method stub
		int sum=0;
		for (int j=0;j<a.length;j++) {
			if(a[j].length>i) {
				sum+=a[j][i];
			}
		}
		return sum;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @return calculates the sum  of all the rows in the array and returns the array of the sums.
	 *  Index i of the return array contains the sum of elements in row i.
	 */
	public static int[] allRowSums(int[][] a) {
		// TODO Auto-generated method stub
		int [] sum=new int[a.length];
		for(int i=0;i<a.length;i++) {
			sum[i]=rowSum(a, i);
		}
		return sum;
	}

	/**
	 * 
	 * @param a 2D integer array
	 * @return checks if the array is row-magic 
	 * (this means that every row has the same row sum).
	 */
	public static boolean isRowMagic(int[][] a) {
		// TODO Auto-generated method stub
		int[] sums=allRowSums(a);
		for (int i=1;i<sums.length;i++) {
			if(sums[0]!=sums[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 * @param a 2D integer array
	 * @return length of longest row in the array
	 */
	private static int longestRow (int[][]a) {
		int max=0;
		for (int i=0;i<a.length;i++) {
			if(a[i].length>max) {
				max=a[i].length;
			}
			
		}
		return max;
	}

	/**
	 * 
	 * @param a 2D integer array
	 * @return checks if the array is column-magic
	 *  (this means that every column has the same column sum).
	 */
	public static boolean isColumnMagic(int[][] a) {
		// TODO Auto-generated method stub
		int magicSum=columnSum(a, 0);
		for (int i=1;i<longestRow(a);i++) {
			if(columnSum(a,i)!=magicSum) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @param a 2D integer array
	 * @return  checks if the 2D array is a square matrix
	 * (i.e. every row has the same length as the other rows and number of rows equals to the rows length).
	 */
	public static boolean isSquare(int[][] a) {
		// TODO Auto-generated method stub
		int rowLen=a[0].length;
		if(rowLen!=a.length) {
			return false;
		}
		for (int i=0;i<a.length;i++) {
			if(a[i].length!=rowLen) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param a 2D integer array
	 * @return checks if the array is a magic square. This means that it must be square, 
	 * and that all row sums, all column sums, and the two diagonal-sums must all be equal.
	 */
	public static boolean isMagic(int[][] a) {
		// TODO Auto-generated method stub
		int alcson1=0;
		int alcson2=0;
		if(isSquare(a)) {
			if(isRowMagic(a)&&isColumnMagic(a)) {
				if(rowSum(a, 0)==columnSum(a, 0)) {
					for (int i=0;i<a.length;i++) {
						alcson1+=a[i][i];
						alcson2+=a[a.length-1-i][i];
					}
					if(alcson1==alcson2) {
						return true;
					}
					
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @return checks to see if the array is a Latin square. This means that it must be square (suppose it is n x n), 
	 * and that each row and each column must contain the values 1, 2, ..., n with no repeats.
	 * if it is Latin returns true else return false
	 */
	public static boolean isLatin(int[][] a) {
		// TODO Auto-generated method stub
		if(isSquare(a)) {
			for (int i=1;i<=(a.length*a.length);i++) {
				Boolean inA=false;
				for(int j=0;j<a.length;j++) {
					for(int k=0;k<a.length;k++) {
						if(a[j][k]==i) {
							inA=true;
						}
					}
				}
				if(inA==false) {
					return false;
				}
			}
			
		}
			else { 
				return false;
				}
		
		return true;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @return checks to see if the array is square (suppose it is n x n), 
	 * the square matrix values is 1 to n*n where every element is the successor of the previous element
	 * i.e. 1, 2, ..., 16 for a 4 x 4 2D array.
	 * returns true if the array is a sequence array 
	 */
	public static boolean isSequence(int[][] a) {
		// TODO Auto-generated method stub
		if(a[0].length==0||a[0][0]!=1) {
		return false;
		}
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				if(j+1!=a[i].length) {
					if(a[i][j]+1!=a[i][j+1]) {
						return false;
					}
				}
				else if(i+1<a.length) {
					if(a[i][j]+1!=a[i+1][0]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @return checks to see if all the entries above the main diagonal are zero.
	 * if the matrix is square and triangular returns true else return false 
	 */
	public static boolean isTriangular(int[][] a) 
	{
		if(!isSquare(a)) {
			return false;
		}
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<a.length;j++) {
				if(j>i&&a[i][j]!=0) {
					return false;
				}
				
			}
		}
		//TODO
		return true;
	}
	
	/**
	 * 
	 * @param a 2D integer array
	 * @param columns index i
	 * @return the array a after we removed his it'h column
	 */
	public static int[][] removeColumn(int[][] a, int i) 
	{
		//TODO
		for(int j=0;j<a.length;j++) {
			if(a[j].length>i) {
				int[] array=new int[a[j].length-1];
				for (int k=0;k<i;k++) {
					array[k]=a[j][k];
			}
				for (int h=i;h<array.length;h++) {
					array[h]=a[j][h+1];
			}
			a[j]=array;
		}
		}
		return a;
	}

}
