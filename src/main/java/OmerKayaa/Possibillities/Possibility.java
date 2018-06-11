package OmerKayaa.Possibillities;

public abstract class Possibility
{
	short PossibleValues;
	byte PossibleValueCount;
	
	public Possibility (int value)
	{
		if(value == 0)
		{
			PossibleValues = nineBitOf1();
			PossibleValueCount = 9;
		}
		else
		{
			PossibleValues = nineBitOf0();
			PossibleValueCount = 0;
		}
	}

	public Possibility()
	{
		PossibleValues = nineBitOf1();
		PossibleValueCount = 9;
	}

	public short getPossibleValues()
	{
		return PossibleValues;
	}

	protected abstract void setValue(byte number);
	
	public void erasePossibility (byte number)
	{
		if(getBit(number))
		{
			setBitToFalse(number);
			PossibleValueCount--;
		}
	}

	public boolean checkForOneMissingValueSolution ()
	{
		if (PossibleValueCount == 1)
		{
			setValue(findTheBit());
			return true;
		}
		return false;
	}

	private void setBitToFalse(byte number)
	{
		PossibleValues &=~(1 << number-1);
	}

	public boolean getBit(byte number)
	{
		return (PossibleValues & 1 << number-1) != 0 ;
	}

	private byte findTheBit()
	{
		byte pointer = 8;
		for (byte i = 4; i >= 1 ; i/=2 )
		{
			if(PossibleValues == 1 << pointer-1 )
			{
				break;
			}
			else if(PossibleValues < 1 << pointer-1 )
			{
				pointer-=i;
			}
			else
			{
				pointer+=i;
			}
		}
		return pointer;
	}
	
	static short nineBitOf1 ()
	{
		return 0b111111111;
	}

	static short nineBitOf0 ()
	{
		return 0;
	}
}