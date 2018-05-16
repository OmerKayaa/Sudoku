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
	public void setValue(int number)
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
	
	SimpleCell getCells ( int i )
	{
		return Receiver.getCells ( i );
	}
	
	interface Consumer<T>
	{
		boolean accept(T t);
	}
	
	interface GetCellFromContainer
	{
		SimpleCell getCells ( int i );
	}
}
