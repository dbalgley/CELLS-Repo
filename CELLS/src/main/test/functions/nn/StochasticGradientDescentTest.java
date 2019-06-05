/**
 * 
 */
package functions.nn;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.primitives.Pair;

import core.CELLSMain;

/**
 * @author Davis
 *
 */
class StochasticGradientDescentTest {
	
	private static List< Pair< Integer, INDArray > > m_trainingData = new ArrayList< Pair< Integer, INDArray > >( );

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass( ) throws Exception {
		for( int i = 0; i < 17; i++ ) {
			Pair< Integer, INDArray > newData = new Pair< Integer, INDArray >( );
			INDArray data = Nd4j.randn(2, 1);
			int result = i % 2;
			newData.setFirst( result );
			newData.setSecond( data );
			m_trainingData.add( newData );
		}
	}

	@Test
	void testNN( ) {
		int[ ] testLayers = new int[ ] { 2, 3, 1 };
		CELLSMain cm = new CELLSMain( );
		cm.init( testLayers );
		
		StochasticGradientDescent sgd = new StochasticGradientDescent( 5, 5, 3.0 );
		sgd.setTrainingData( m_trainingData );
		sgd.setBiases( cm.getBiases( ) );
		sgd.setWeights( cm.getWeights( ) );
		sgd.SGD( );
		
	}

}
