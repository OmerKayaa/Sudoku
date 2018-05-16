package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;

public class Column extends Container
{
	public Column ( ArrayReceiver receiver , int location )
	{
		super(receiver , location);
	}
	
	@Override
	SimpleCell getCells ( int i )
	{
		return this.Receiver.cellReceiver ( i , this.Location );
	}
}
