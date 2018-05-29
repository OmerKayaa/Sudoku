package OmerKayaa;

import OmerKayaa.Display.Panel;

public class App
{
	public static void main ( String[] args )
	{
		Panel window = new Panel ();
		new Thread ( window ).run ();
	}
	
}
