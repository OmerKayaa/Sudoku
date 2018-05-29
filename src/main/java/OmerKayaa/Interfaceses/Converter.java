package OmerKayaa.Interfaceses;

/**
 *  <p>
 *      I used two type of array locating system
 *      <ol>
 *          <li>
 *              Cartesian: normal cartesian location system
 *              0.0 - > 1.0 -> 2.0 -> 3.0 ..... 9.0
 *              v - - - - - - - - - - - - - - - - ^
 *              0.1 - > 1.1 -> 2.1 -> 3.1 ..... 9.1
 *          </li>
 *          <li>
 *              Square: square location system
 *              0.0 -> 0.1 -> 0.2 > 1.0 -> 1.1 -> 1.2       0 | 1 | 2
 *              v - - - - - - - ^ | v - - - - - - - ^       ---------
 *              0.3 -> 0.4 -> 0.5 | 1.3 -> 1.4 -> 1.5       3 | 4 | 5
 *              v - - - - - - - ^ | v - - - - - - - ^       ----------
 *              0.6 -> 0.7 -> 0.7 ^                         6 | 7 | 8
 *          </li>
 *      </ol>
 *      square location system is used due to my design for
 *      treat each square as one dimension array this is
 *      converter interface to convert each system
 *  </p>
 * @author github.com/OmerKayaa
 */
public interface Converter<Type>
{
	/**
	 * <p>
	 *     this method converts square locating system to cartesian location system
	 *     consumer interface gives x , y as output in cartesian system it also
	 *     can return a result of a type you desire
	 * </p>
	 * @param So        outer square
	 * @param Si        inner square
	 * @param accept    interface for passing conversion result
	 * @param <Type>    any desired return type can be used
	 * @return          return type is generic this method can return any type depend on implementation
	 * @see             for example implementation look OmerKayaa/Model/Container/Sqaure
	 * */
	static <Type> Type ContainerConverter ( int So , int Si , Consumer<Type> accept )
	{
		return accept.accept ( ( So % 3 ) * 3 + ( Si % 3 ) ,( So / 3 ) * 3 + ( Si / 3 ) );
	}
	
	/**
	 * <p>
	 *     this method converts cartesian locating system to square location system
	 *     consumer interface gives outer square number and inner square number as
	 *     output in square location system it return a result of a type you desire
	 * </p>
	 * @param x         cartesian system x
	 * @param y         cartesian system y
	 * @param accept    interface for passing conversion result
	 * @param <Type>    any desired return type can be used
	 * @return
	 */
	static <Type> Type CartesianConverter ( int x , int y , Consumer<Type> accept )
	{
		return accept.accept ( ( x / 3 ) + ( y / 3 ) * 3 ,( x % 3 ) + ( y % 3 ) * 3 );
	}
	
	/**
	 * <p>
	 *     instead of using an class for holding x , y and passing, i used this interface
	 * </p>
	 * @param <T>   any desired return type can be used
	 */
	interface Consumer<T>
	{
		T accept(int a,int b);
	}
}
