package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;

public class Row extends Container
{
	public Row ( ArrayReceiver receiver , int location )
	{
		super(location);
		Receiver = i -> receiver.cellReceiver ( i , location );
	}
}
