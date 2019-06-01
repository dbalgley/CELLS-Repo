/**
 * 
 */
package functions.nn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.linalg.primitives.Pair;

import functions.vector.VectorMath;

/**
 * @author Davis
 *
 */
public class StochasticGradientDescent {
	private int m_epochs;
	private int m_miniBatchSize;
	private double m_eta;

	private List< Pair< Integer, INDArray > > m_trainingData = new ArrayList< Pair< Integer, INDArray > >( );
	private List< Pair< Integer, INDArray > > m_testData = new ArrayList< Pair< Integer, INDArray > >( );

	private List< INDArray > m_weights;
	private List< INDArray > m_biases;

	private List< INDArray > zeroByLayerWeights;
	private List< INDArray > zeroByLayerBiases;

	/**
	 * Setup the data object that will contain the information for SGD
	 * 
	 * @param p_epochs        - Number of runs to perform
	 * @param p_miniBatchSize - Size of the batches, each batch is run independently
	 *                        of every other one to prevent overtraining
	 * @param p_eta           - Learning rate, one of the hyper-parameters that is
	 *                        configurable in the process
	 */
	public StochasticGradientDescent( int p_epochs, int p_miniBatchSize, double p_eta ) {
		m_epochs = p_epochs;
		m_miniBatchSize = p_miniBatchSize;
		m_eta = p_eta;
	}

	/**
	 * Run the stochastic gradient descent. This is (currently) the central algo in
	 * the process.
	 */
	public void SGD( ) {
		for( int i = 0; i < m_epochs; i++ ) {
			Collections.shuffle( m_trainingData );
			List< List< Pair< Integer, INDArray > > > batches = generateMiniBatches( );

			for( List< Pair< Integer, INDArray > > batch : batches ) {
				updateMiniBatch( batch, m_eta );
			}

			/* if test data evaluate */

			System.out.println( "Epoch " + i + " of " + m_epochs + " complete. " );
		}
	}

	/**
	 * Splits up the data set into smaller chunks.
	 * 
	 * @return
	 */
	private List< List< Pair< Integer, INDArray > > > generateMiniBatches( ) {
		List< List< Pair< Integer, INDArray > > > batchedData = new ArrayList< List< Pair< Integer, INDArray > > >( );

		int iter = 0;
		for( int i = 0; i < m_trainingData.size( ); i += m_miniBatchSize + 1 ) {
			List< Pair< Integer, INDArray > > batch = m_trainingData.subList( i, i + m_miniBatchSize );
			batchedData.add( batch );
			iter++;
		}

		if ( ( iter * m_miniBatchSize ) < ( m_trainingData.size( ) - 1 ) ) {
			List< Pair< Integer, INDArray > > batch = m_trainingData.subList( iter * m_miniBatchSize,
					m_trainingData.size( ) );
			batchedData.add( batch );
		}
		return batchedData;
	}

	private void updateMiniBatch( List< Pair< Integer, INDArray > > p_dataSet, double p_eta ) {
		// Fill these lists of by layer weights, biases with zeros
		List< INDArray > blWeights = fillWithZeros( m_weights );
		List< INDArray > blBiases = fillWithZeros( m_biases );

		for( Pair< Integer, INDArray > data : p_dataSet ) {
			backprop( data.getFirst( ), data.getSecond( ) );
		}

	}

	private void backprop( Integer p_x, INDArray p_y ) {
		List< INDArray > blWeights = fillWithZeros( m_weights );
		List< INDArray > blBiases = fillWithZeros( m_biases );

		// Activations, stored individually and then layer by layer
		INDArray activation = p_y;

		// Store away the activations
		List< INDArray > activations = new ArrayList< INDArray >( );
		activations.add( p_y );

		// Store z vectors
		List< INDArray > zVec = new ArrayList< INDArray >( );

		for( int i = 0; i < m_biases.size( ); i++ ) {
			INDArray z = ( m_weights.get( i ).mmul( activation ) ).add( m_biases.get( i ) );
			zVec.add( z );
			activation = Transforms.sigmoid( z );
			activations.add( activation );
		}

		INDArray delta = costDerivative( activations.get( activations.size( ) - 1 ), p_x )
				.mmul( Transforms.sigmoidDerivative( zVec.get( zVec.size( ) - 1 ) ) );
		
		for( int i = m_weights.size( ) + 1; i >= 2; i-- ) {
			INDArray z = zVec.get( zVec.size( ) - 1 );
			INDArray sigPrimeArray = Transforms.sigmoidDerivative( z );
			//INDArray delta = m_weights.get(  )
		}
	}

	private INDArray costDerivative( INDArray indArray, Integer p_x ) {
		// TODO Auto-generated method stub
		return null;
	}

	private List< INDArray > fillWithZeros( List< INDArray > p_input ) {
		List< INDArray > blValues = new ArrayList< INDArray >( );
		for( INDArray values : p_input ) {
			INDArray layerZeros = Nd4j.zeros( values.columns( ), values.rows( ) );
			blValues.add( layerZeros );
		}

		return blValues;
	}

	/**
	 * @return the m_trainingData
	 */
	public List< Pair< Integer, INDArray > > getTrainingData( ) {
		return m_trainingData;
	}

	/**
	 * @param m_trainingData the m_trainingData to set
	 */
	public void setTrainingData( List< Pair< Integer, INDArray > > p_trainingData ) {
		this.m_trainingData = p_trainingData;
	}

	/**
	 * @return the m_testData
	 */
	public List< Pair< Integer, INDArray > > getTestData( ) {
		return m_testData;
	}

	/**
	 * @param p_testData the m_testData to set
	 */
	public void setTestData( List< Pair< Integer, INDArray > > p_testData ) {
		this.m_testData = p_testData;
	}

	/**
	 * @return the m_weights
	 */
	public List< INDArray > getWeights( ) {
		return m_weights;
	}

	/**
	 * @param p_weights the m_weights to set
	 */
	public void setWeights( List< INDArray > p_weights ) {
		this.m_weights = p_weights;
		zeroByLayerWeights = fillWithZeros( this.m_weights );
	}

	/**
	 * @return the m_biases
	 */
	public List< INDArray > getBiases( ) {
		return m_biases;
	}

	/**
	 * @param p_biases the m_biases to set
	 */
	public void setBiases( List< INDArray > p_biases ) {
		this.m_biases = p_biases;
		zeroByLayerBiases = fillWithZeros( this.m_biases );
	}
}
