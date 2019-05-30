/**
 * 
 */
package core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import functions.nn.StochasticGradientDescent;

/**
 * @author Davis
 *
 */
class CELLSMainTest {

	/**
	 * Test method for {@link core.CELLSMain#init(int[])}.
	 */
	@Test
	void testInit( ) {
		int[ ] testLayers = new int[ ] { 2, 3, 1 };
		CELLSMain cm = new CELLSMain( );
		cm.init( testLayers );
		
		assertTrue( cm.getBiases( ).size( ) == testLayers.length - 1 );
		for( int i = 0; i < cm.getBiases( ).size( ); i++ ) {
			//assertTrue( cm.getBiases( ).get( i ).size( ) == testLayers[ i + 1 ] );
		}
		for( int i = 0; i < cm.getBiases( ).size( ); i++ ) {
			System.out.println( cm.getBiases( ).get( i ).toString( ) );
			System.out.println( cm.getBiases( ).get( i ).shapeInfoToString( ) );
		}
		System.out.println();
		
		assertTrue( cm.getWeights( ).size( ) == testLayers.length - 1 );
		for( int i = 0; i < cm.getWeights( ).size( ); i++ ) {
			//assertTrue( cm.getWeights( ).get( i ).size( ) == ( testLayers[ i + 1 ] ) );

				//assertTrue( cm.getWeights( ).get( i ).get( j ).size( ) == ( testLayers[ i ] ) );
		}
		

		for( int i = 0; i < cm.getWeights( ).size( ); i++ ) {
			System.out.println( cm.getWeights( ).get( i ).toString( ) );
			System.out.println( cm.getWeights( ).get( i ).shapeInfoToString( ) );
		}
		System.out.println();
	}

}
