/**
 * 
 */
package functions.vector;

import java.util.List;

import util.VectorMathException;

/**
 * @author Davis
 *
 */
public class VectorMath {
	static public double dot( List< Double > a, List< Double > b ) throws VectorMathException {
		double sum = 0;
		if ( a.size( ) != b.size( ) ) {
			throw new VectorMathException( "Dot Product requires the dimensions of the two matrices be equal in size" );
		}
		for( int i = 0; i < a.size( ); i++ ) {
			sum += a.get( i ) * b.get( i );
		}
		return sum;
	}
}
