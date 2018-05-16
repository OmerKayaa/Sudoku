package OmerKayaa.Possibillities;

import OmerKayaa.Interfaceses.Statics;

public abstract class Possibility
{
	final boolean[] PossibleValues;
	byte PossibleValueCount;
	
	public Possibility (int value)
	{
		setValue ( value );
		PossibleValues = Statics.FalseArrayBoolean ();
		PossibleValueCount = 0;
	}
	
	public Possibility()
	{
		PossibleValues = Statics.TrueArrayBoolean ();
		PossibleValueCount = 9;
	}
	
	public abstract void setValue(int number);
	
	public void earsePossibility(int number)
	{
		PossibleValues [number] = false; PossibleValueCount--;
		if(PossibleValueCount ==1)
		{
			for ( byte i = 0 ; i < 9 ; i++ )
			{
				if(PossibleValues[i]) setValue ( i );
			}
		}
	}
}