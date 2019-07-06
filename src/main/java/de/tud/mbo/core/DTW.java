
package de.tud.mbo.core;

//public final class DTW {
//
//	/** Defines the result for a Dynamic Time Warping operation. */
//	public static class Result {
//		/* Member Variables. */
//		private final int[][] mWarpingPath;
//		private final double  mDistance;
//		/** Constructor. */
//		public Result(final int[][] pWarpingPath, final double pDistance) {
//			// Initialize Member Variables.
//			this.mWarpingPath = pWarpingPath;
//			this.mDistance    = pDistance;
//		}
//		/* Getters. */
//		public final int[][] getWarpingPath() { return this.mWarpingPath; }
//		public final double     getDistance() { return this.mDistance;    }
//	}
//
//	/** Default constructor for a class which implements dynamic time warping. */
//	public DTW() { }
//
//	public DTW.Result compute(final float[] pSample, final float[] pTemplate) {
//		// Declare Iteration Constants.
//		final int lN = pSample.length;
//		final int lM = pTemplate.length;
//		// Ensure the samples are valid.
//		if(lN == 0 || lM == 0) {
//			// Assert a bad result.
//			return new DTW.Result(new int[][]{ /* No path data. */ }, Double.NaN);
//		}
//		// Define the Scalar Qualifier.
//		int lK = 1;
//		// Allocate the Warping Path. (Math.max(N, M) <= K < (N + M).
//		final int[][]    lWarpingPath  = new int[lN + lM][2];
//		// Declare the Local Distances.
//		final double[][] lL            = new double[lN][lM];
//		// Declare the Global Distances.
//		final double[][] lG            = new double[lN][lM];
//		// Declare the MinimaBuffer.
//		final double[]   lMinimaBuffer = new double[3];
//		// Declare iteration variables.
//		int i, j;
//		// Iterate the Sample.
//		for(i = 0; i < lN; i++) {
//			// Fetch the Sample.
//			final float lSample = pSample[i];
//			// Iterate the Template.
//			for(j = 0; j < lM; j++) {
//				// Calculate the Distance between the Sample and the Template for this Index.
//				lL[i][j] = this.getDistanceBetween(lSample, pTemplate[j]);
//			}
//		}
//
//		// Initialize the Global.
//		lG[0][0] = lL[0][0];
//
//		for(i = 1; i < lN; i++) {
//			lG[i][0] = lL[i][0] + lG[i - 1][0];
//		}
//
//		for(j = 1; j < lM; j++) {
//			lG[0][j] = lL[0][j] + lG[0][j - 1];
//		}
//
//		for (i = 1; i < lN; i++) {
//			for (j = 1; j < lM; j++) {
//				// Accumulate the path.
//				lG[i][j] = (Math.min(Math.min(lG[i-1][j], lG[i-1][j-1]), lG[i][j-1])) + lL[i][j];
//			}
//		}
//
//		// Update iteration varaibles.
//		i = lWarpingPath[lK - 1][0] = (lN - 1);
//		j = lWarpingPath[lK - 1][1] = (lM - 1);
//
//		// Whilst there are samples to process...
//		while ((i + j) != 0) {
//			// Handle the offset.
//			if(i == 0) {
//				// Decrement the iteration variable.
//				j -= 1;
//			}
//			else if(j == 0) {
//				// Decrement the iteration variable.
//				i -= 1;
//			}
//			else {
//				// Update the contents of the MinimaBuffer.
//				lMinimaBuffer[0] = lG[i - 1][j];
//				lMinimaBuffer[1] = lG[i][j - 1];
//				lMinimaBuffer[2] = lG[i - 1][j - 1];
//				// Calculate the Index of the Minimum.
//				final int lMinimumIndex = this.getMinimumIndex(lMinimaBuffer);
//				// Declare booleans.
//				final boolean lMinIs0 = (lMinimumIndex == 0);
//				final boolean lMinIs1 = (lMinimumIndex == 1);
//				final boolean lMinIs2 = (lMinimumIndex == 2);
//				// Update the iteration components.
//				i -= (lMinIs0 || lMinIs2) ? 1 : 0;
//				j -= (lMinIs1 || lMinIs2) ? 1 : 0;
//			}
//			// Increment the qualifier.
//			lK++;
//			// Update the Warping Path.
//			lWarpingPath[lK - 1][0] = i;
//			lWarpingPath[lK - 1][1] = j;
//		}
//
//		// Return the Result. (Calculate the Warping Path and the Distance.)
//		return new DTW.Result(this.reverse(lWarpingPath, lK), ((lG[lN - 1][lM - 1]) / lK));
//	}
//
//	/** Changes the order of the warping path, in increasing order. */
//	private int[][] reverse(final int[][] pPath, final int pK) {
//		// Allocate the Path.
//		final int[][] lPath = new int[pK][2];
//		// Iterate.
//		for(int i = 0; i < pK; i++) {
//			// Iterate.
//			for (int j = 0; j < 2; j++) {
//				// Update the Path.
//				lPath[i][j] = pPath[pK - i - 1][j];
//			}
//		}
//		// Return the Allocated Path.
//		return lPath;
//	}
//
//	/** Computes a distance between two points. */
//	protected double getDistanceBetween(double p1, double p2) {
//		// Calculate the square error.
//		return (p1 - p2) * (p1 - p2);
//	}
//
//	/** Finds the index of the minimum element from the given array. */
//	protected final int getMinimumIndex(final double[] pArray) {
//		// Declare iteration variables.
//		int    lIndex = 0;
//		double lValue = pArray[0];
//		// Iterate the Array.
//		for(int i = 1; i < pArray.length; i++) {
//			// .Is the current value smaller?
//			final boolean lIsSmaller = pArray[i] < lValue;
//			// Update the search metrics.
//			lValue = lIsSmaller ? pArray[i] : lValue;
//			lIndex = lIsSmaller ?         i : lIndex;
//		}
//		// Return the Index.
//		return lIndex;
//	}
//
//}
/*
 * DTW.java
 */

/**
 * This class implements the Dynamic Time Warping algorithm
 * given two sequences
 * <pre>
 *   X = x1, x2,..., xi,..., xn
 *   Y = y1, y2,..., yj,..., ym
 *  </pre>
 *
 * @author		Cheol-Woo Jung (cjung@gatech.edu)
 * @version	1.0
 */
public class DTW {

	protected float[] seq1;
	protected float[] seq2;
	protected int[][] warpingPath;

	protected int n;
	protected int m;
	protected int K;

	protected double warpingDistance;

	public DTW(float[] sample, float[] templete) {
		seq1 = sample;
		seq2 = templete;

		n = seq1.length;
		m = seq2.length;
		K = 1;

		warpingPath = new int[n + m][2];	// max(n, m) <= K < n + m
		warpingDistance = 0.0;

		this.compute();
	}

	public void compute() {
		double accumulatedDistance = 0.0;

		double[][] d = new double[n][m];	// local distances
		double[][] D = new double[n][m];	// global distances

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				d[i][j] = distanceBetween(seq1[i], seq2[j]);
			}
		}

		D[0][0] = d[0][0];

		for (int i = 1; i < n; i++) {
			D[i][0] = d[i][0] + D[i - 1][0];
		}

		for (int j = 1; j < m; j++) {
			D[0][j] = d[0][j] + D[0][j - 1];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				accumulatedDistance = Math.min(Math.min(D[i-1][j], D[i-1][j-1]), D[i][j-1]);
				accumulatedDistance += d[i][j];
				D[i][j] = accumulatedDistance;
			}
		}
		accumulatedDistance = D[n - 1][m - 1];

		int i = n - 1;
		int j = m - 1;
		int minIndex = 1;

		warpingPath[K - 1][0] = i;
		warpingPath[K - 1][1] = j;

		while ((i + j) != 0) {
			if (i == 0) {
				j -= 1;
			} else if (j == 0) {
				i -= 1;
			} else {	// i != 0 && j != 0
				double[] array = { D[i - 1][j], D[i][j - 1], D[i - 1][j - 1] };
				minIndex = this.getIndexOfMinimum(array);

				if (minIndex == 0) {
					i -= 1;
				} else if (minIndex == 1) {
					j -= 1;
				} else if (minIndex == 2) {
					i -= 1;
					j -= 1;
				}
			} // end else
			K++;
			warpingPath[K - 1][0] = i;
			warpingPath[K - 1][1] = j;
		} // end while
		warpingDistance = accumulatedDistance / K;

		this.reversePath(warpingPath);
	}

	/**
	 * Changes the order of the warping path (increasing order)
	 *
	 * @param path	the warping path in reverse order
	 */
	protected void reversePath(int[][] path) {
		int[][] newPath = new int[K][2];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 2; j++) {
				newPath[i][j] = path[K - i - 1][j];
			}
		}
		warpingPath = newPath;
	}
	/**
	 * Returns the warping distance
	 *
	 * @return
	 */
	public double getDistance() {
		return warpingDistance;
	}

	/**
	 * Computes a distance between two points
	 *
	 * @param p1	the point 1
	 * @param p2	the point 2
	 * @return		the distance between two points
	 */
	protected double distanceBetween(double p1, double p2) {
		return (p1 - p2) * (p1 - p2);
	}

	/**
	 * Finds the index of the minimum element from the given array
	 *
	 * @param array		the array containing numeric values
	 * @return				the min value among elements
	 */
	protected int getIndexOfMinimum(double[] array) {
		int index = 0;
		double val = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] < val) {
				val = array[i];
				index = i;
			}
		}
		return index;
	}

	/**
	 *	Returns a string that displays the warping distance and path
	 */
	public String toString() {
		String retVal = "Warping Distance: " + warpingDistance + "\n";
		retVal += "Warping Path: {";
		for (int i = 0; i < K; i++) {
			retVal += "(" + warpingPath[i][0] + ", " +warpingPath[i][1] + ")";
			retVal += (i == K - 1) ? "}" : ", ";

		}
		return retVal;
	}

	/**
	 * Tests this class
	 *
	 * @param args	ignored
	 */
	public static void main(String[] args) {
		float[] n2 = {1.5f, 3.9f, 4.1f, 3.3f};
		float[] n1 = {2.1f, 2.45f, 3.673f, 4.32f, 2.05f, 1.93f, 5.67f, 6.01f};
		DTW dtw = new DTW(n1, n2);
		System.out.println(dtw);
	}
}
