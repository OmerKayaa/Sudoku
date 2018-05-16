package OmerKayaa.Model;

import OmerKayaa.Model.Containers.*;
import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Interfaceses.CloneAble;

public class Sudoku implements CloneAble<Sudoku>
{
	SimpleCell[][] Cells = new SimpleCell[9][9];
	Container [] Columns = new Column[9];
	Container [] Squares = new Square[9];
	Container [] Rows = new Row[9];
	
	public Sudoku ( ArrayReceiver receiver )
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Cells[i][j] = receiver.cellReceiver ( i, j );
			}
			Columns [i] = new Column ( receiver , i  );
			Rows    [i] = new Row ( receiver , i  );
			Squares [i] = new Square ( receiver , i  );
		}
		
	}
	
	@Override
	public Sudoku Clone ()
	{
		return new Sudoku ( ( x , y ) -> Cells[y][x].Clone () );
	}
}
