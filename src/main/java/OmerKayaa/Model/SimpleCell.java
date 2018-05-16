package OmerKayaa.Model;

import OmerKayaa.Interfaceses.CloneAble;
import OmerKayaa.Interfaceses.Statics;
import OmerKayaa.Possibillities.Possibility;

public class SimpleCell extends Possibility implements CloneAble<SimpleCell>
{
	final byte LocationX, LocationY;
	byte Value, PossibleValueCount;
	boolean[] PossibleValues;
	
	public SimpleCell ( int value , int locationX , int locationY )
	{
		setValue ( value ); this.LocationX = ( byte ) locationX; this.LocationY = ( byte ) locationY;
		PossibleValues = Statics.FalseArrayBoolean (); PossibleValueCount = 0;
	}
	
	public SimpleCell ( int locationX , int locationY )
	{
		this.LocationX = ( byte ) locationX; this.LocationY = ( byte ) locationY;
		PossibleValues = Statics.TrueArrayBoolean (); PossibleValueCount = 9;
	}
	
	public int getValue ()
	{
		return Value;
	}
	
	@Override
	public void setValue ( int value )
	{
		Value = ( byte ) value;
	}
	
	@Override
	public SimpleCell Clone ()
	{
		return Value == 0 ? new SimpleCell ( LocationX , LocationY ) :new SimpleCell ( Value , LocationX , LocationY );
	}
	
	@Override
	public boolean[] getPossibility ()
	{
		return PossibleValues;
	}
	
	@Override
	public void decreaseCount ()
	{
		PossibleValueCount -- ;
	}
	
	@Override
	public byte getCount ()
	{
		return PossibleValueCount;
	}
}
