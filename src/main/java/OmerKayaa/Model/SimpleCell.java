package OmerKayaa.Model;

import OmerKayaa.Interfaceses.CloneAble;
import OmerKayaa.Possibillities.Possibility;

public class SimpleCell extends Possibility implements CloneAble<SimpleCell>
{
	final byte LocationX, LocationY;
	byte Value;
	
	public SimpleCell ( int value , int locationX , int locationY )
	{
		super(value);
		this.LocationX = ( byte ) locationX; this.LocationY = ( byte ) locationY;
	}
	
	public SimpleCell ( int locationX , int locationY )
	{
		this.LocationX = ( byte ) locationX; this.LocationY = ( byte ) locationY;
	}
	
	public int getValue ()
	{
		return Value;
	}
	
	@Override
	public void setValue ( int value )
	{
		Value = ( byte ) (value);
	}
	
	@Override
	public SimpleCell Clone ()
	{
		return Value == 0 ? new SimpleCell ( LocationX , LocationY ) :new SimpleCell ( Value , LocationX , LocationY );
	}
	
	@Override
	public String toString()
	{
		return "x:"+LocationX + " y:" + LocationY + " Value:" + Value;
	}
}
