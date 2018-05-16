package OmerKayaa.Interfaceses;

import OmerKayaa.Model.SimpleCell;

public interface Statics
{
	static boolean[] TrueArrayBoolean ()
	{
		return new boolean[] { true , true , true , true , true , true , true , true , true };
	}
	
	static boolean[] FalseArrayBoolean ()
	{
		return new boolean[] { false , false , false , false , false , false , false , false , false };
	}
	
	static SimpleCell vectoralConverter ( int So , int Si , Consumer<SimpleCell> accept )
	{
		return accept.accept ( ( So % 3 ) * 3 + ( Si % 3 ) ,( So / 3 ) * 3 + ( Si / 3 ) );
	}
	
	interface Consumer<T>
	{
		T accept(int x,int y);
	}
}

