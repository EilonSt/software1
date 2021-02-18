package il.ac.tau.cs.sw1.hw3;

public class ArrayUtils {
	/**
	 * @param m an integer matrix that is not null and it's values is not null 
	 * @return the transposed matrix
	 * 
	 */
	public static int[][] transposeMatrix(int[][] m)
	{
		//TODO
		int[][] transpose= new int [m[0].length][m.length];
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[0].length;j++) {
				transpose[j][i]=m[i][j];
			}
		}
		return transpose;
		
	}
	/**
	 @param an integer array
	 @return the array with one cyclic shift to the right
	 */
	private static int[] moveRight(int[]array) {
		int prev=array[0];
		for(int i=0;i<array.length;i++) {
			 int next=array[(i+1)%array.length];
			 array[(i+1)%array.length]=prev;
			 prev=next;
		}
		return array;
	}
	/**
	 @param an integer array
	 @return the array with one cyclic shift to the left
	 */
	private static int[] moveLeft(int[]array) {
		int last=array[0];
		for(int i=0;i<array.length-1;i++) {
			array[i]=array[i+1];
		}
		array[array.length-1]=last;
		return array;
		
	}
	/**
	 *@param an integer array that is not null
	 *@param move an integer that is the number of cyclic moves for the array
	 *@param direction a character that represents the direction of the cyclic rotation
	 * @return the array after moves rotations of the direction we get as parameter
	 * if moves is a negative number or the direction is not "R" or "L" we return the original array
	 */
	public static int[] shiftArrayCyclic(int[] array, int move, char direction)
	{
		//TODO
		if(array.length>0) {
		move=move%array.length;
		
		if(direction=='R') {
			for( int i=0;i<move;i++) {
				moveRight(array);
			}
		}
		else if(direction=='L'){
			for( int i=0;i<move;i++) {
				moveLeft(array);
			}
		}
		}
		return array;
		
	}
	/**
	 * @param an integer array that is not null
	 * @return the maximal alternating sum in the array
	 */
	public static int alternateSum(int[] array)
	{
		int maxSum=0;
		int curSum=0;
		for(int j=0;j<array.length;j++) {
			for(int i=j;i<array.length;i++) {
				curSum+=(Math.pow(-1, (i+j)) * array[i]);		
			if(maxSum<curSum) {
				maxSum=curSum;
			}
			}
			curSum=0;
		}
		//TODO
		return maxSum;
		
	}
	/**
	 * @param m an integer matrix that is not null with values 0 or 1
	 * @param node number i 
	 * @param node number j
	 * @return 1 if there is a path in the graph (that m is it's adjacency matrix) between the nodes i and j
	 */
	public static int findPath(int[][] m, int i, int j)
	{
		if(m[i][j]==1) {
			return 1;
		}

		for (int k =0;k<m.length;k++) {
			if(m[i][k]==1&&k!=i) {
				m[i][k]=0;
				m[k][i]=0;
				return findPath(m, k, j);
			}
		}
		//TODO
		return 0;
		
	}
}
