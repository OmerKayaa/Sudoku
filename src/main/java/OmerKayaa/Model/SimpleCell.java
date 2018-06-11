package OmerKayaa.Model;

import OmerKayaa.Interfaceses.CloneAble;
import OmerKayaa.Interfaceses.Consumer;
import OmerKayaa.Interfaceses.ContainerReceiver;
import OmerKayaa.Interfaceses.Converter;
import OmerKayaa.Model.Containers.Container;
import OmerKayaa.Model.Containers.ContainerType;
import OmerKayaa.Possibillities.Possibility;

public class SimpleCell extends Possibility implements CloneAble<SimpleCell>
{
	final byte LocationX, LocationY;
	final private ContainerReceiver containers;

	private byte Value;
	
	public SimpleCell (int value , int locationX , int locationY , ContainerReceiver receiver)
	{
		super(value); this.Value = (byte)value; this.LocationX = ( byte ) locationX; this.LocationY = ( byte )
			locationY; containers=receiver;
	}
	
	public byte getValue ()
	{
		return Value;
	}

	@Override
	public void setValue ( byte value )
	{
		Value = value;
		forEachContainer(container -> {container.erasePossibility(value); container.forEachCell(cell -> {cell
				.erasePossibility(value); return true;}); return true;});
	}

	public void forEachContainer(Consumer<Container> consumer)
	{
		consumer.accept(containers.receiver(ContainerType.Square,
											Converter.CartesianConverter(LocationX,LocationY,(a, b) -> a)));
		consumer.accept(containers.receiver(ContainerType.Column , LocationY));
		consumer.accept(containers.receiver(ContainerType.Row , LocationX));
	}
	
	@Override
	public SimpleCell Clone ()
	{
		return new SimpleCell(getValue(),LocationX,LocationY,containers);
	}
	
	@Override
	public String toString()
	{
		return "x:"+LocationX + " y:" + LocationY + " Value:" + Value;
	}

}
