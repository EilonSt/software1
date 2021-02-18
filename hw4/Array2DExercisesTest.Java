package il.ac.tau.cs.sw1.ex4;



public class Array2DExercisesTest {
	

	// example arrays for testing
	private int[][] basic, allneg, nonsquare, negatives, rowmagic, colmagic, 
	                magic, notmagic1, notmagic2, latin, notlatin;

	public static void main(String[] args) {
		Array2DExercisesTest t = new Array2DExercisesTest();
		t.setUp();
		
		t.testAllRowSums();
		t.testColumnSum();
		t.testIsColumnMagicTrue();
		t.testIsLatinatrue();
		t.testIsMagicTrue();
		t.testIsRowMagicTrue();
		t.testIsSequenceTrue();
		t.testIsSquareTrue();
		t.testMax();;
		t.testRowSum();
		t.tsetIsTriangular();
	}
	 
		/**
	     * Sets up the test fixture with some arrays to test.
	     * This method is called before every test case method.
	     */
	    	public void setUp() {
	        basic = new int[][] { {1,2,3}, {4,5,6}, {7,8,9} };
	        allneg = new int[][] { {-10,-12,-3}, {-4,-5,-6,-8}, {-7,-8} }; //all neg and ragged
	        nonsquare = new int[][] { {1,2,3}, {4,5}, {6,7,8,9} };
	        negatives = new int[][] { {1,-2,3}, {4,5,6}, {-7,8,-9} };
	        rowmagic = new int[][] { {1,2,3}, {-1,5,2}, {4,0,2} };
	        colmagic = new int[][] { {1,-1,4,10}, {3,5,0,-6} };
	        latin = new int[][] { {1,2,3}, {4,5,6}, {7,8,9} };
	        notlatin = new int[][] { {2,1,3}, {2,3,1}, {3,1,2} };
	        magic = new int[][] { {2,2,2}, {2,2,2}, {2,2,2}   };
	        notmagic1 = new int[][] { {1,2,3}, {4,5,6}, {6,8,9} }; //diag sums are not equal
	        notmagic2 = new int[][] { {1,5,3}, {4,5,6}, {7,8,9} }; //diag sums are equal but rows are not


	    }
	    	
	    private boolean assertEquals(int i, int j) {
	    		if(i == j)
	    			return true;
	    		return false;
	    		
	    	}

	
	

	/**
	 * Test max correct when all vals are negative 
	 */
	public void testMax() {
		if (!assertEquals(-3,Array2DExercises.max(allneg)))
	    	System.out.println("There is a problem in max function!");
	}

	/**
	 * Test row sum calculated correctly including for nonsquare arrays
	 */
	public void testRowSum() {
	    boolean b1 = assertEquals(6, Array2DExercises.rowSum(basic, 0));
	    boolean b2 = assertEquals(15, Array2DExercises.rowSum(basic, 1));
	    boolean b3 = assertEquals(24, Array2DExercises.rowSum(basic, 2));
	    boolean b4 = assertEquals(30, Array2DExercises.rowSum(nonsquare, 2));
	    
	    if((!b1)||(!b2)||(!b3)||(!b4))
	    	System.out.println("There is a problem in rowSum function!");
	}

	/**
	 * Test column sum calculated correctly for standard cases
	 */
	public void testColumnSum() {
	    boolean b1 = assertEquals(12, Array2DExercises.columnSum(basic, 0));
	    boolean b2 = assertEquals(15, Array2DExercises.columnSum(basic, 1));
	    boolean b3 = assertEquals(9, Array2DExercises.columnSum(nonsquare, 3));
	    
	    if((!b1)||(!b2)||(!b3))
	    	System.out.println("There is a problem in columnSum function!");
	}



	/**
	 * Checks array of row sums correctly calculated
	 */
	public void testAllRowSums() {
	    int[] expected = new int[] {6,15,24};
	    int[] actual = Array2DExercises.allRowSums(basic);
	    if(!assertArrayEquals(expected, actual))
	    	System.out.println("There is a problem in allRowSums function!");
	    	
	}


	private boolean assertArrayEquals(int[] expected, int[] actual) {
		for(int i = 0; i<expected.length; i++)
			if(!(expected[i] == actual[i]))
				return false;
		return true;
		
	}

	/**
	 * Test for row magic with a valid magic square
	 */
	public void testIsRowMagicTrue() {
	    if(!assertEquals(true, Array2DExercises.isRowMagic(rowmagic) ))
	    		System.out.println("There is a problem in isRowMagic function!");
	    	
	}

	private boolean assertEquals(boolean b, boolean c) {
		// TODO Auto-generated method stub
		return b == c;
	}

	

	/**
	 * Test col magic 
	 */
	public void testIsColumnMagicTrue() {
	    if(!assertEquals(true, Array2DExercises.isColumnMagic(colmagic) ))
	    	System.out.println("There is a problem in isColumnMagic function!");
	}

	
	/**
	 * Test for square arrays
	 */
	public void testIsSquareTrue() {
	  if(!assertEquals(true, Array2DExercises.isSquare(basic)))
		  System.out.println("There is a problem in isSquare function!");
	}


	/**
	 * Test where all conditions for magic square are met
	 */
	public void testIsMagicTrue() {
	    if(!assertEquals(true, Array2DExercises.isMagic(magic) ))
	    	System.out.println("There is a problem in isMagic function!");
	    	
	}

	

	/**
	 * Test isLatin is true for a valid latin square
	 */
	public void testIsLatinatrue() {
	  if(!assertEquals(true, Array2DExercises.isLatin(latin) ))
		  System.out.println("There is a problem in isLatin function!");
		  
	}


	
	public void testIsSequenceTrue() {
	   if(!assertEquals(true, Array2DExercises.isSequence(basic) ))
		   System.out.println("There is a problem in isSequence function!");
	}
	
	
	   public void tsetIsTriangular() {
	    	if (!assertEquals(false,Array2DExercises.isTriangular(basic)))
		    	System.out.println("There is a problem in isTriangular function!");
		
	}

	




}
