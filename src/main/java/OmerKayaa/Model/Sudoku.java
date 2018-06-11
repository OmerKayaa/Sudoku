package OmerKayaa.Model;

import OmerKayaa.Interfaceses.*;
import OmerKayaa.Model.Containers.*;

public class Sudoku implements CloneAble<Sudoku>
{
	private SimpleCell[][] Cells = new SimpleCell[9][9];
	private Container [] Columns = new Column[9];
	private Container [] Squares = new Square[9];
	private Container [] Rows = new Row[9];
	
	public Sudoku ( PointReceiver receiver )
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Cells[i][j] = new SimpleCell
						(receiver.receiver ( j, i ) , j , i , this::getContainer);
			}
			Columns [i] = new Column ( (x, y) -> Cells[y][x] , i  );
			Rows    [i] = new Row ( (x, y) -> Cells[y][x] , i  );
			Squares [i] = new Square ( (x, y) -> Cells[y][x] , i  );
		}
	}

	public Sudoku ( CellReceiver receiver )
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			for ( int j = 0 ; j < 9 ; j++ )
			{
				Cells[i][j] = receiver.receiver(j,i);
			}
			Columns [i] = new Column ( receiver , i  );
			Rows    [i] = new Row ( receiver , i  );
			Squares [i] = new Square ( receiver , i  );
		}
	}

	public boolean forEachContainer( Consumer<Container> consumer )
	{
		boolean changed=false;
		for (int i = 0; i < 9; i++)
		{
			changed |= consumer.accept(Columns[i]);
			changed |= consumer.accept(Squares[i]);
			changed |= consumer.accept(Rows[i]);
		}
		return changed;
	}
	
	public SimpleCell getCells (int x,int y)
	{
		return Cells[y][x];
	}

	public CellReceiver getCellReceiver ()
	{
		return (x, y) -> getCells(x,y);
	}

	public PointReceiver getPointReceiver ()
	{
		return (x, y) -> getCells(x,y).getValue();
	}
	
	public Container getContainer (ContainerType type , int number)
	{
		if(type == ContainerType.Square)
			return Squares[number];
		else if(type == ContainerType.Column)
			return Columns[number];
		else if(type == ContainerType.Row)
			return Rows[number];
		else return null;
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
		return  new Sudoku ( (CellReceiver) ( x , y ) -> Cells[y][x]);
	}
}
