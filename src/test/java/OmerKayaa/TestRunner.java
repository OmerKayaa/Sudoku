package OmerKayaa;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.LinkedList;

public class TestRunner {
	public static void main(String[] args)
	{
		LinkedList<Result> tests = new LinkedList<> (  );
		
		printLine ();
		
		tests.add ( JUnitCore.runClasses ( StaticMethodTester.class ) );
		tests.add ( JUnitCore.runClasses ( SudokuSolutionTester.class ) );
		tests.add ( JUnitCore.runClasses (	ContainerTester.class ) );
		
		tests.forEach ( TestRunner::printResult );
		
		printLine ();
	}
	
	public static void printResult(Result r)
	{
		printLine ();
		for (Failure failure : r.getFailures())
		{
			System.out.println(failure.toString ());
		}
		
		System.out.println("Test Passed - " + r.wasSuccessful());
	}
	
	public static void printLine()
	{
		System.out.println ( "---------------------------------------------" );
	}
}