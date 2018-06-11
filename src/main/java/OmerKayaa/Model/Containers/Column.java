package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.CellReceiver;

public class Column extends Container
{
	public Column (CellReceiver receiver , int location )
	{
		super(location , i -> receiver.receiver ( location , i ));
	}
}
