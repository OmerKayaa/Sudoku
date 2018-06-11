package OmerKayaa;

import OmerKayaa.Interfaceses.Converter;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class StaticMethodTester
{
    @Test
    public void testCartesianConverters()
    {
        System.out.println ( "Testing Cartesian Location System" );
        assertTrue ( Converter.CartesianConverter ( 2,1 , ( So , Si ) -> So==0&&Si==5 ) );
        assertTrue ( Converter.CartesianConverter ( 4,6 , ( So , Si ) -> So==7&&Si==1 ) );
        assertTrue ( Converter.CartesianConverter ( 7,5 , ( So , Si ) -> So==5&&Si==7 ) );
        assertTrue ( Converter.CartesianConverter ( 2,4 , ( So , Si ) -> So==3&&Si==5 ) );
        System.out.println ( "Test Passed" );
    }
    
    @Test
    public void testSquareConverters()
    {
        System.out.println ( "Testing Square Location System" );
        assertTrue ( Converter.ContainerConverter ( 2,1 , ( x , y ) -> x==7&&y==0 ) );
        assertTrue ( Converter.ContainerConverter ( 4,5 , ( x , y ) -> x==5&&y==4 ) );
        assertTrue ( Converter.ContainerConverter ( 1,8 , ( x , y ) -> x==5&&y==2 ) );
        assertTrue ( Converter.ContainerConverter ( 6,3 , ( x , y ) -> x==0&&y==7 ) );
        System.out.println ( "Test Passed" );
    }

}
