package OmerKayaa;

import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Model.Sudoku;
import org.checkerframework.common.value.qual.ArrayLen;

public class App
{
	public static void main ( String[] args )
	{
		int[][] sudoku = new int[][] { { 1,0,0 , 0,0,0 , 0,0,0 } ,
		                               { 0,2,0 , 0,0,0 , 0,0,0 } ,
		                               { 0,0,3 , 0,0,0 , 0,0,0 } ,
		                               
		                               { 0,0,0 , 4,0,0 , 0,0,0 } ,
		                               { 0,0,0 , 0,5,0 , 0,0,0 } ,
		                               { 0,0,0 , 0,0,6 , 0,0,0 } ,
		                               
		                               { 0,0,0 , 0,0,0 , 7,0,0 } ,
		                               { 0,0,0 , 0,0,0 , 0,8,0 } ,
		                               { 0,0,0 , 0,0,0 , 0,0,9 } ,
		                               { 0,0,0 , 0,0,0 , 0,0,9 }};
		printer ( sudoku );
		new Sudoku ( ( x , y ) -> new SimpleCell ( sudoku[x][y] , x , y ) );
	}
	
	static void printer (int array @ArrayLen ( 9 )[] @ArrayLen ( 9 )[] )
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
