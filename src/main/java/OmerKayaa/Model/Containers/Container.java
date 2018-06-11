package OmerKayaa.Model.Containers;

import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Possibillities.Possibility;
import OmerKayaa.Interfaceses.Consumer;

public class Container extends Possibility
{
	private final GetCellFromContainer Receiver;
	private final int Location;
	private short a=0,b=0;
	
	protected Container (int location , GetCellFromContainer receiver)
	{
		Location = ( byte ) location;
		Receiver = receiver;
	}
	
	@Override
	public void setValue(byte number)
	{
		forEachCell(Cell -> {if(Cell.getValue () == 0 && Cell.getBit(number) )
		{Cell.setValue (number ); return true;} else return
				false;} );
	}

	public void forEachCell (Consumer<SimpleCell> action )
	{
		for ( int i = 0 ; i < 9 ; i++ )
		{
			if(action.accept ( getCells ( i ))) return;
		}
	}

	public short checkOnePossibleSolution()
	{
		a=b=0;
		forEachCell(cell ->
		{
			a = (short) ((~(a | b) & cell.getPossibleValues())|((a & b) &~ (cell.getPossibleValues())));
			b |= cell.getPossibleValues();
			return false;
		});
		return a;
	}
	
	public SimpleCell getCells ( int i )
	{
		return Receiver.getCells ( i );
	}
	
	protected interface GetCellFromContainer
	{
		SimpleCell getCells ( int i );
	}
}