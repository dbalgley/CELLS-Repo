/**
 * 
 */
package functions.nn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Davis
 *
 */
public class StochasticGradientDescent {
	private int m_epochs;
	private int m_miniBatchSize;
	private double m_eta;
	
	private List< Double > m_trainingData = new ArrayList< Double >( );
	private List< Double > m_testData = new ArrayList< Double >( );
	
	public StochasticGradientDescent( int p_epochs, int p_miniBatchSize, double p_eta ) {
		m_epochs = p_epochs;
		m_miniBatchSize = p_miniBatchSize;
		m_eta = p_eta;
	}
	
	public void SGD( ) {
		for( int i = 0; i < m_epochs; i++ ) {
			Collections.shuffle( m_trainingData );
			List< List< Double > > batches = generateMiniBatches( );
			
			for( List< Double > batch : batches ) {
				updateMiniBatch( batch, m_eta );
			}
			
			/*if test data evaluate*/
			
			System.out.println( "Epoch " + i + " of " + m_epochs + " complete. " );
		}
	}
	
	private List< List< Double > > generateMiniBatches( ) {
		List< List< Double > > batchedData = new ArrayList< List< Double > >( );
		
		int iter = 0;
		for( int i = 0; i < m_trainingData.size( ); i += m_miniBatchSize + 1 ) {
			List< Double > batch = m_trainingData.subList( i, i + m_miniBatchSize );
			batchedData.add( batch );
			iter++;
		}
		
		if( ( iter * m_miniBatchSize ) < ( m_trainingData.size( ) - 1 ) ) {
			System.err.println( "CHECK THIS" );
			List< Double > batch = m_trainingData.subList( iter * m_miniBatchSize, m_trainingData.size( ) );
			batchedData.add( batch );
		}
		return batchedData;
	}
	
	private void updateMiniBatch( List< Double > p_data, double p_eta ) {
		//INDArray blWeights = 
	}
}
