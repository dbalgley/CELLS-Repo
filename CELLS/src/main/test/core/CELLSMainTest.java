/**
 * 
 */
package core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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
			assertTrue( cm.getBiases( ).get( i ).size( ) == testLayers[ i + 1 ] );
		}
		for( int i = 0; i < cm.getBiases( ).size( ); i++ ) {
			for( int j = 0; j < cm.getBiases( ).get( i ).size( ); j++ ) {
				System.out.print( cm.getBiases( ).get( i ).get( j ) + ", " );
			}
			System.out.println();
		}
		System.out.println();
		
		assertTrue( cm.getWeights( ).size( ) == testLayers.length - 1 );
		for( int i = 0; i < cm.getWeights( ).size( ); i++ ) {
			assertTrue( cm.getWeights( ).get( i ).size( ) == ( testLayers[ i + 1 ] ) );
			for( int j = 0; j < cm.getWeights( ).get( i ).size( ); j++ ) {

				assertTrue( cm.getWeights( ).get( i ).get( j ).size( ) == ( testLayers[ i ] ) );
			}
		}
		

		for( int i = 0; i < cm.getWeights( ).size( ); i++ ) {
			for( int j = 0; j < cm.getWeights( ).get( i ).size( ); j++ ) {
				System.out.print( cm.getWeights( ).get( i ).get( j ) + ", " );
			}
			System.out.println();
		}
		System.out.println();
	}

}
