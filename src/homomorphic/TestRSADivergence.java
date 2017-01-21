package homomorphic;

import java.math.BigInteger;
import java.util.Random;

/**
 * Demonstration of how large number of operations on encrypted data introduce
 * errors into the computation
 * 
 * @author george georgovassilis
 *
 */

public class TestRSADivergence {

	final static int ROUNDS = 1000;

	public static void main(String... args) throws Exception {

		// instantiate public/private keys
		RSA rsa = new RSA(1024);

		Random r = new Random(123);

		BigInteger a = new BigInteger("4");
		BigInteger b = new BigInteger("17");

		BigInteger enca = rsa.encrypt(a);
		BigInteger encb = rsa.encrypt(b);

		BigInteger encProduct = enca.multiply(encb);

		BigInteger expectedProduct = a.multiply(b);

		for (int i = 0; i < ROUNDS; i++) {
			int nextNumber = 1 + r.nextInt(4);
			expectedProduct = expectedProduct.multiply(new BigInteger("" + nextNumber));
			encProduct = encProduct.multiply(rsa.encrypt(new BigInteger("" + nextNumber)));
			BigInteger decProduct = rsa.decrypt(encProduct);
			
			if (!expectedProduct.equals(decProduct)){
				System.out.println("Computation error at iteration "+i);
				System.out.println("Final     product: " + expectedProduct);
				System.out.println("Encrypted product: " + decProduct);
				break;
			}
		}

	}
}