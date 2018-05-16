package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Interfaceses.Statics;

public class Square extends Container
{
	public Square( ArrayReceiver receiver , int location )
	{
		super ( receiver , location );
	}
	
	@Override
	SimpleCell getCells ( int i )
	{
		return Statics.vectoralConverter ( Location,i, Receiver::cellReceiver );
	}
}
