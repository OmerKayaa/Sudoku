package OmerKayaa.Model;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Interfaceses.CloneAble;
import OmerKayaa.Model.Containers.Column;
import OmerKayaa.Model.Containers.Container;
import OmerKayaa.Model.Containers.Row;
import OmerKayaa.Model.Containers.Square;

public class Sudoku implements CloneAble<Sudoku>
{
	private SimpleCell[][] Cells = new SimpleCell[9][9];
	private Container [] Columns = new Column[9];
	private Container [] Squares = new Square[9];
	private Container [] Rows = new Row[9];
	
	public Sudoku ( ArrayReceiver receiver )
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Cells[i][j] = receiver.cellReceiver ( j, i );
			}
			Columns [i] = new Column ( receiver , i  );
			Rows    [i] = new Row ( receiver , i  );
			Squares [i] = new Square ( receiver , i  );
		}
	}
	
	public SimpleCell getCells (int x,int y)
	{
		return Cells[y][x];
	}
	
	public Container getColumns (int number)
	{
		return Columns[number];
	}
	
	public Container getSquares (int number)
	{
		return Squares[number];
	}
	
	public Container getRows (int number)
	{
		return Rows[number];
	}
	
	/**
	 * <p>
	 *     This method used for recurring unsolved
	 * </p>
	 * @return complete copy of the values (not reference) of data
	 */
	@Override
	public Sudoku Clone ()
	{
		return new Sudoku ( ( x , y ) -> Cells[y][x].Clone () );
	}
}
