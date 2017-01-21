package homomorphic;

import java.math.BigInteger;

/**
 * This simple class demonstrates RSA's homomorphic trait:
 * if a*b=c then RSA(a)*RSA(a)=RSA(c)
 * @author george georgovassilis
 *
 */
public class TestRSAHomomorphicTrait {

	public static void main(String...args) throws Exception{
		//instantiate public/private keys
		RSA rsa = new RSA(1024);
		
		BigInteger a = new BigInteger("4");
		BigInteger b = new BigInteger("17");
		BigInteger expectedProduct = a.multiply(b);

		BigInteger enca = rsa.encrypt(a);
		BigInteger encb = rsa.encrypt(b);
		
		BigInteger encc = enca.multiply(encb);
		
		BigInteger c = rsa.decrypt(encc);
		
		System.out.println(a+" * "+b+" = "+c+" which is "+(c.equals(expectedProduct)));
	}

}
