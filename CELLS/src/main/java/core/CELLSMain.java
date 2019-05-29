/**
 * 
 */
package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * @author Davis
 *
 */
public class CELLSMain {

	private List< List< Double > > m_biases = new ArrayList< List< Double > >( );
	private List< List< List< Double > > > m_weights = new ArrayList< List< List< Double > > >( );
	
	public void init( int[ ] p_layers ) {
		Random rand = new Random( );
		for( int i = 1; i < p_layers.length; i++ ) {
			List< Double > layerBias = new ArrayList< Double >( );
			List< List< Double > > layerWeight = new ArrayList< List< Double > >( );
			for( int j = 0; j < p_layers[ i ]; j++ ) {
				layerBias.add( rand.nextGaussian( ) );

				List< Double > connectionWeight = new ArrayList< Double >( );
				for( int k = 0; k < p_layers[ i - 1 ]; k++ ) {
					connectionWeight.add( rand.nextGaussian( ) );
				}
				layerWeight.add( connectionWeight );
			}
			m_biases.add( layerBias );
			m_weights.add( layerWeight );
		}
	}
	
	public INDArray feedforward( INDArray a ) {
		return a;
		
	}

	/**
	 * @return the m_biases
	 */
	public List< List< Double > > getBiases( ) {
		return m_biases;
	}

	/**
	 * @param p_biases the m_biases to set
	 */
	public void setBiases( List< List< Double > > p_biases ) {
		this.m_biases = p_biases;
	}

	/**
	 * @return the m_weights
	 */
	public List< List< List< Double > > > getWeights( ) {
		return m_weights;
	}

	/**
	 * @param p_weights the m_weights to set
	 */
	public void setWeights( List< List< List< Double > > > p_weights ) {
		this.m_weights = p_weights;
	}
}
