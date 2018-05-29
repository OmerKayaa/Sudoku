package OmerKayaa.Model.Containers;

import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Possibillities.Possibility;

public class Container extends Possibility
{
	protected GetCellFromContainer Receiver;
	protected final int Location;
	
	public Container (int location )
	{
		Location = ( byte ) location;
	}
	
	@Override
	protected void setValue(int number)
	{
		forEach ( Cell -> {if(Cell.getValue () == 0) {setValue ( number ); return true;} else return false;} );
	}
	
	public void forEach ( Consumer<SimpleCell> action )
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			if(action.accept ( getCells ( i ))) return;
		}
	}
	
	public SimpleCell getCells ( int i )
	{
		return Receiver.getCells ( i );
	}
	
	public interface Consumer<T>
	{
		boolean accept(T t);
	}
	
	protected interface GetCellFromContainer
	{
		SimpleCell getCells ( int i );
	}
}
