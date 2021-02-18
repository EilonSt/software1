package il.ac.tau.cs.sw1.ex4;
import il.ac.tau.cs.sw1.ex4.Array2DExercises;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	/**
	 * 
	 * @param s
	 * @return get 2d array
	 */
	public static int[][] scanArray(Scanner s){          
		String stringFile="";
		while(s.hasNext()) {
			stringFile+=(s.nextLine()+"#");
			}
		String [] numbers=stringFile.split("#");
		int [][] array=new int[numbers.length][];
		for(int i =0;i<numbers.length;i++) {
			String [] tmp=numbers[i].split(" ");
			array[i]=new int[tmp.length];
			for(int j =0;j<array[i].length;j++) {
				array[i][j]=Integer.parseInt(tmp[j]);
			}
		}
		return array;
	}
	
	public static void main(String[] args) throws Exception
	{
		Scanner s = new Scanner(new File(args[0]));
		int[][] array=scanArray(s);
		printArray(array);
		s.close();
		Scanner user=new Scanner(System.in);
		boolean procces=true;
		while(procces) {
			printMenu();
			int num=user.nextInt();
			if(num<1||num>14) {
				printError();
		}
			else {
			
				switch (num) {
				case 1:
					int max=Array2DExercises.max(array);
					System.out.println("applied max on the input array and received "+max);
					break;
				case 2:
					System.out.println("insert the parameter i:");
					int parameter=user.nextInt();
					int sum=Array2DExercises.rowSum(array, parameter);
					System.out.println("applied rowSum on the input array and received "+sum);
					break;
				case 3:
					System.out.println("insert the parameter i:");
					int param=user.nextInt();
					int colsum=Array2DExercises.columnSum(array, param);
					System.out.println("applied columnSum on the input array and received "+colsum);
					break;
				case 4:
					int[] allRowSum=Array2DExercises.allRowSums(array);
					System.out.println("applied allRowSums on the input array and received "+ Arrays.toString(allRowSum));
					break;
				case 5:
					boolean isRowMagic=Array2DExercises.isRowMagic(array);
					System.out.println("applied isRowMagic on the input array and received "+ isRowMagic);
					break;
				case 6:
					boolean isColumnMagic=Array2DExercises.isColumnMagic(array);
					System.out.println("applied isColumnMagic on the input array and received "+ isColumnMagic);
					break;
				case 7:
					boolean isMagic=Array2DExercises.isMagic(array);
					System.out.println("applied isMagic on the input array and received "+ isMagic);
					break;
				case 8:
					boolean isSquare=Array2DExercises.isSquare(array);
					System.out.println("applied isSquare on the input array and received "+ isSquare);
					break;
				case 9:
					boolean isLatin=Array2DExercises.isLatin(array);
					System.out.println("applied isLatin on the input array and received "+ isLatin);
					break;
				case 10:
					boolean isSequence=il.ac.tau.cs.sw1.ex4.Array2DExercises.isSequence(array);
					System.out.println("applied isSequence on the input array and received "+ isSequence);
					break;
				case 11:
					boolean isTriangular=Array2DExercises.isTriangular(array);
					System.out.println("applied isTriangular on the input array and received "+ isTriangular);
					break;
				case 12:
					System.out.println("insert the parameter i:");
					int par=user.nextInt();
					int [][] a=Array2DExercises.removeColumn(array, par);
					System.out.println("applied removeColumn on the input array and received "+ Arrays.deepToString(a));
					break;
				case 13:
					System.out.println("applied printArray on the input array and received ");
					printArray(array);
					break;
				case 14:
					goodBye();
					procces=false;
					break;
				}
				}
				}
				user.close();
	}
	
	/*************************************************************/
	/*********************  Don't change this ********************/
	/*************************************************************/
	
	public static void printArray(int[][] a) 
	{
		System.out.println("------------The input array is:--------------");
		
		for(int i =0; i<a.length; i++) 
		{
			for (int j = 0; j<a[i].length; j++) 
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------");
	}
	public static void printMenu() 
	{
		StringBuilder s= new StringBuilder();
		s.append("-------------Choose a service----------------\n");
		s.append("1. Max\n");
		s.append("2. RowSum\n");
		s.append("3. ColumnSum\n");
		s.append("4. AllRowSums\n");
		s.append("5. IsRowMagic\n");
		s.append("6. IsColumnMagic\n");
		s.append("7. IsMagic\n");
		s.append("8. IsSquare\n");
		s.append("9. IsLatin\n");
		s.append("10. IsSequence\n");
		s.append("11. IsTriangular\n");
		s.append("12. RemoveColumn\n");
		s.append("13. PrintArray\n");
		s.append("14. Exit\n");
		s.append("--------------------------------------------");
		System.out.println(s.toString());
		
	}
	
	public static void goodBye() 
	{
		System.out.println("Goodbye!!");
	}
	
	public static void printError() 
	{
		System.out.println("Illegal input, try again!");
	}

}
