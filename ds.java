import java.math.BigInteger;
import java.util.*;

public class DSA_Short {
    public static void main(String[] args) {
        Random r = new Random();
        
        BigInteger one = BigInteger.ONE;
        BigInteger zero = BigInteger.ZERO;
        
        BigInteger p = new BigInteger("10600");
        while (!p.isProbablePrime(99))
            p = p.add(one);
        
        BigInteger q = p.subtract(one);
        BigInteger f = new BigInteger("2");
        
        while (!q.isProbablePrime(99)) {
            while (!q.mod(f).equals(zero))
                f = f.add(one);
            q = q.divide(f);
        }

        BigInteger h = new BigInteger(p.bitLength(), r).mod(p);
        BigInteger g = h.modPow(p.subtract(one).divide(q), p);
        
        BigInteger x = new BigInteger(q.bitLength(), r).mod(q);
        BigInteger y = g.modPow(x, p);
        
        BigInteger k = new BigInteger(q.bitLength(), r).mod(q);
        BigInteger rVal = g.modPow(k, p).mod(q);
        
        BigInteger hVal = new BigInteger(p.bitLength(), r);
        BigInteger s = k.modInverse(q).multiply(hVal.add(x.multiply(rVal))).mod(q);
        
        System.out.println("\nSimulation of Digital Signature Algorithm");
        System.out.println("\nGlobal public key components:");
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("g = " + g);
        
        System.out.println("\nSecret information:");
        System.out.println("x = " + x);
        System.out.println("k = " + k);
        System.out.println("y = " + y);
        System.out.println("h = " + hVal);
        
        System.out.println("\nDigital Signature:");
        System.out.println("r = " + rVal);
        System.out.println("s = " + s);
        
        BigInteger w = s.modInverse(q);
        BigInteger u1 = hVal.multiply(w).mod(q);
        BigInteger u2 = rVal.multiply(w).mod(q);
        
        BigInteger v = g.modPow(u1, p).multiply(y.modPow(u2, p)).mod(p).mod(q);
        
        System.out.println("\nVerification:");
        System.out.println("w = " + w);
        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
        System.out.println("v = " + v);
        
        if (v.equals(rVal))
            System.out.println("\nSuccess: Signature verified!\n" + rVal);
        else
            System.out.println("\nError: Signature failed!");
    }
}
