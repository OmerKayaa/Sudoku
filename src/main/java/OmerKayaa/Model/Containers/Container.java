package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Interfaceses.Statics;
import OmerKayaa.Possibillities.Possibility;

public abstract class Container extends Possibility
{
	final boolean[] PossibleValues = Statics.TrueArrayBoolean ();
	protected final ArrayReceiver Receiver;
	byte PossibleValueCount = 9;
	int iteratorCount = 0;
	final int Location;
	
	public Container ( ArrayReceiver receiver , int location )
	{
		Receiver = receiver; Location = ( byte ) location;
	}
	
	@Override
	public boolean[] getPossibility ()
	{
		return PossibleValues;
	}
	
	@Override
	public void decreaseCount ()
	{
		PossibleValueCount--;
	}
	
	@Override
	public byte getCount ()
	{
		return PossibleValueCount;
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
	
	abstract SimpleCell getCells ( int i );
	
	interface Consumer<T>
	{
		boolean accept(T t);
	}
}
