package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.CellReceiver;

public class Row extends Container
{
	public Row (CellReceiver receiver , int location )
	{
		super(location, i -> receiver.receiver ( i , location ) );
	}
}
