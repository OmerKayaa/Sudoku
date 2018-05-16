package OmerKayaa.Possibillities;

import OmerKayaa.Interfaceses.Statics;

public abstract class Possibility
{
	final boolean[] PossibleValues = Statics.TrueArrayBoolean ();
	byte PossibleValueCount = 9;
	
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

/*
private void ereasePossibaleValue ( int number )
	{
		PossibleValueCount--; PossibleValues[number] = false; if ( PossibleValueCount == 1 )
		{
			forEach ( cell -> {
				if ( cell.getValue () != 0 )
				{
					for ( int i = 0 ; i < 9 ; i++ )
					{
						if ( PossibleValues[i] ) cell.setValue ( i );
					}
				}
			} );
		}
	}
 */