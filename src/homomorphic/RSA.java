package homomorphic;

import java.math.BigInteger;
import java.util.Random;

// from here: http://www.java2s.com/Code/Java/Security/SimpleRSApublickeyencryptionalgorithmimplementation.htm
// simplified a bit for readability, removed unused methods. License unclear.
/**
 * Simple RSA public key encryption algorithm implementation.
 * <P>
 * Taken from "Paj's" website:
 * <TT>http://pajhome.org.uk/crypt/rsa/implementation.html</TT>
 * <P>
 * Adapted by David Brodrick
 */
public class RSA {
	private BigInteger n, d, e;

	private int bitlen = 1024;

	/** Create an instance that can both encrypt and decrypt. */
	public RSA(int bits) {
		bitlen = bits;
		Random r = new Random(1);
		BigInteger p = new BigInteger(bitlen / 2, 100, r);
		BigInteger q = new BigInteger(bitlen / 2, 100, r);
		n = p.multiply(q);
		BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		e = new BigInteger("3");
		while (m.gcd(e).intValue() > 1) {
			e = e.add(new BigInteger("2"));
		}
		d = e.modInverse(m);
	}

	/** Encrypt the given plaintext message. */
	public BigInteger encrypt(BigInteger message) {
		return message.modPow(e, n);
	}

	/** Decrypt the given ciphertext message. */
	public BigInteger decrypt(BigInteger message) {
		return message.modPow(d, n);
	}


}