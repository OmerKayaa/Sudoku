package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Interfaceses.Statics;

public class Square extends Container
{
	public Square( ArrayReceiver receiver , int location )
	{
		super (location );
		Receiver = i -> Statics.vectoralConverter ( Location,i, receiver::cellReceiver ) ;
	}
}

