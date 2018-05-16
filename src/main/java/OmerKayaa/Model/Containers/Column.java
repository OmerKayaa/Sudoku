package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;

public class Column extends Container
{
	public Column ( ArrayReceiver receiver , int location )
	{
		super(location);
		Receiver = i -> receiver.cellReceiver ( i, location );
	}
}
