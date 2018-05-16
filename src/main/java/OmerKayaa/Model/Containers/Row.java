package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;

public class Row extends Container
{
	public Row ( ArrayReceiver receiver , int location )
	{
		super(receiver , location);
	}
	
	@Override
	SimpleCell getCells ( int i )
	{
		return this.Receiver.cellReceiver ( this.Location , i );
	}
	
}
