/**
 * 
 */
package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author Davis
 *
 */
public class CELLSMain {

	private List< INDArray > m_biases = new ArrayList< INDArray >( );
	private List< INDArray > m_weights = new ArrayList< INDArray >( );
	
	public void init( int[ ] p_layers ) {
		Random rand = new Random( );
		for( int i = 1; i < p_layers.length; i++ ) {
			System.out.println( "Rows: " + p_layers[ i ] );
			INDArray layerBiases = Nd4j.randn( p_layers[ i ], 1 );
			INDArray layerWeights = Nd4j.randn( p_layers[ i ], p_layers[ i - 1 ] );

			m_biases.add( layerBiases );
			m_weights.add( layerWeights );
		}
	}
	
	public INDArray feedforward( INDArray a ) {
		return a;
		
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
	}
}
