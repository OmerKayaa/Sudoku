package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.ArrayReceiver;
import OmerKayaa.Interfaceses.Converter;

public class Square extends Container
{
	public Square( ArrayReceiver receiver , int location )
	{
		super (location );
		Receiver = i -> Converter.ContainerConverter ( Location, i, ( x , y ) -> receiver.cellReceiver ( x , y ) ) ;
	}
}