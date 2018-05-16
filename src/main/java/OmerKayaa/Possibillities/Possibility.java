package OmerKayaa.Possibillities;

public abstract class Possibility
{
	public abstract boolean[] getPossibility();
	public abstract void decreaseCount();
	public abstract byte getCount();
	public abstract void setValue(int number);
	
	public void earsePossibility(int number)
	{
		boolean[] poss = getPossibility ();
		poss [number] = false; decreaseCount ();
		if(getCount ()==1)
		{
			for ( byte i = 0 ; i < 9 ; i++ )
			{
				if(poss[i]) setValue ( i );
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