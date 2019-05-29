/**
 * 
 */
package functions.nn;

import java.util.List;

/**
 * @author Davis
 *
 */
public class Sigmoid {
	private double calculate( double p_input ) {
		return 1.0d / ( 1.0d + Math.exp( -p_input ) );
	}
	
	public List< List< Double > > calcElementwise( List< List< Double > > p_input ) {
		for( int i = 0; i < p_input.size( ); i++ ) {
			for( int j = 0; j < p_input.get( i ).size( ); j++ ) {
				p_input.get( i ).set( j, calculate( p_input.get( i ).get( j ) ) );
			}
		}
		return p_input;
	}
}
