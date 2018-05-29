package OmerKayaa.Possibillities;

public abstract class Possibility
{
	final boolean[] PossibleValues;
	byte PossibleValueCount;
	
	public Possibility (int value)
	{
		setValue ( value );
		PossibleValues = FalseArrayBoolean ();
		PossibleValueCount = 0;
	}
	
	public Possibility()
	{
		PossibleValues = TrueArrayBoolean ();
		PossibleValueCount = 9;
	}
	
	protected abstract void setValue(int number);
	
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
	
	static boolean[] TrueArrayBoolean ()
	{
		return new boolean[] { true , true , true , true , true , true , true , true , true };
	}
	
	static boolean[] FalseArrayBoolean ()
	{
		return new boolean[] { false , false , false , false , false , false , false , false , false };
	}
}