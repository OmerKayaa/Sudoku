package OmerKayaa;

import OmerKayaa.Interfaceses.Converter;
import OmerKayaa.Interfaceses.Solution;
import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Model.Sudoku;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class SudokuSolutionTester extends TestCase
{
	int[][] unSolvedSudoku,solvedSudoku,oneMissingElement;
	Sudoku Experiment;
	
	public void setUp ()
	{
		unSolvedSudoku = new int[][]
				                 {
				                       { 5,3,0 , 0,7,0 , 0,0,0 } ,
				                       { 6,0,0 , 1,9,5 , 0,0,0 } ,
				                       { 0,9,8 , 0,0,0 , 0,6,0 } ,
						
				                       { 8,0,0 , 0,6,0 , 0,0,3 } ,
				                       { 4,0,0 , 8,0,3 , 0,0,1 } ,
				                       { 7,0,0 , 0,2,0 , 0,0,6 } ,
						                
				                       { 0,6,0 , 0,0,0 , 2,8,0 } ,
				                       { 0,0,0 , 4,1,9 , 0,0,5 } ,
				                       { 0,0,0 , 0,8,0 , 0,7,9 }
				                 };
		solvedSudoku = new int[][]
				               {
						               { 5,3,4 , 6,7,8 , 9,1,2 } ,
						               { 6,7,2 , 1,9,5 , 3,4,8 } ,
						               { 1,9,8 , 3,4,2 , 5,6,7 } ,
						
						               { 8,5,9 , 7,6,1 , 4,2,3 } ,
						               { 4,2,6 , 8,5,3 , 7,9,1 } ,
						               { 7,1,3 , 9,2,4 , 8,5,6 } ,
						
						               { 9,6,1 , 5,3,7 , 2,8,4 } ,
						               { 2,8,7 , 4,1,9 , 6,3,5 } ,
						               { 3,4,5 , 2,8,6 , 1,7,9 }
				               };
		
		oneMissingElement = new int[][]
				                    {
						                    { 5,3,4 , 6,7,8 , 9,1,0 } ,
						                    { 6,0,2 , 1,9,5 , 3,4,8 } ,
						                    { 1,9,8 , 3,4,2 , 5,6,7 } ,
						
						                    { 8,5,9 , 7,6,1 , 4,2,3 } ,
						                    { 4,2,6 , 8,5,3 , 7,9,1 } ,
						                    { 0,1,3 , 9,2,4 , 8,5,6 } ,
						
						                    { 9,6,1 , 5,3,7 , 2,8,4 } ,
						                    { 2,8,7 , 4,1,9 , 6,3,5 } ,
						                    { 3,4,5 , 2,8,6 , 1,7,9 }
				                    };
	}
	
	@Test
	public void testSolution()
	{
		Experiment = new Sudoku( ( x , y ) -> new SimpleCell ( unSolvedSudoku[y][x] , x , y ) );
		Solution.findSolution (Experiment ) ;
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Assert.assertEquals ( Experiment.getCells ( j , i ).getValue () , solvedSudoku[i][j] );
			}
		}
		
	}
	
	@Test
	public void testShouldFindSingleMissingValue()
	{
		Experiment = new Sudoku ( ( x , y ) -> new SimpleCell ( oneMissingElement[y][x] , x , y ) );
		Solution.findSolution (Experiment );
		Assert.assertEquals ( Experiment.getCells ( 8 , 0 ).getValue () , solvedSudoku[0][8] );
		Assert.assertEquals ( Experiment.getCells ( 1 , 1 ).getValue () , solvedSudoku[1][1] );
		Assert.assertEquals ( Experiment.getCells ( 0 , 5 ).getValue () , solvedSudoku[0][8] );
	}
	
	@Test
	public void testLocationShouldBeRight()
	{
		Sudoku Experiment = new Sudoku ( ( x , y ) -> new SimpleCell ( solvedSudoku[y][x] , x , y ) );
		
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Assert.assertEquals ( Experiment.getCells ( j , i ).getValue () , solvedSudoku[i][j]);
			}
		}
		
		System.out.println ( "Sudoku.getCell works fine" );
		
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Assert.assertEquals ( Experiment.getColumns ( i ).getCells ( j ).getValue () , solvedSudoku[j][i] );
				Assert.assertEquals ( Experiment.getRows ( i ).getCells ( j ).getValue () , solvedSudoku[i][j] );
				Assert.assertEquals ( Experiment.getSquares ( i ).getCells ( j ).getValue () ,
				                      ( int ) ( Converter.ContainerConverter ( i , j , ( a , b ) -> solvedSudoku[b][a] ) ) );
			}
		}
		
		System.out.println ( "Sudoku.getContainers works fine" );
	}
	
	static void printer (int array [][] )
	{
		for ( int i = 0 ; i < 3 ; i++ )
		{
			for ( int j = 0 ; j < 3 ; j++ )
			{
				for ( int k = 0 ; k < 3 ; k++ )
				{
					for ( int l = 0 ; l < 3 ; l++ )
					{
						System.out.print ( array[i*3+j][k*3+l] + " " );
					}
					System.out.print ( "   " );
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
