package OmerKayaa.Model.Containers;

import OmerKayaa.Interfaceses.CellReceiver;
import OmerKayaa.Interfaceses.Converter;

public class Square extends Container
{
	public Square(CellReceiver receiver , int location )
	{
		super (location , i -> Converter.ContainerConverter ( location, i, ( x , y ) -> receiver.receiver ( x , y
		) ) );
	}
}