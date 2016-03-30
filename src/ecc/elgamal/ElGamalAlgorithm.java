/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc.elgamal;

import java.util.ArrayList;

/**
 *
 * @author angelynz95
 */
public class ElGamalAlgorithm {
    // Atribut
    private long a;
    private long b;
    
    // Konstruktor
    public ElGamalAlgorithm(long a, long b) {
        this.a = a;
        this.b = b;
    }
    
    // Method
    public EncryptionPoint encrypt(long plaintext, Point base, Point publicKey, long k) {
        EncryptionPoint ciphertext;
        Point firstCipher, secondCipher, messagePoint;
        
        messagePoint = new Point();
        messagePoint.setX(plaintext);
        messagePoint.setY(ellipticalCurveFunction(messagePoint.getX()));
        firstCipher = new Point(k * base.getX(), k * base.getY());
        secondCipher = new Point(k * publicKey.getX() + messagePoint.getX(), k * publicKey.getY() + messagePoint.getY());
        ciphertext = new EncryptionPoint(firstCipher, secondCipher);
        
        return ciphertext;
    }
    
    public long decrypt(EncryptionPoint ciphertext, long secretKey) {
        Point firstPlain, messagePoint;
        long plaintext;
        
        firstPlain = new Point();
        messagePoint = new Point();
        firstPlain.setX(secretKey * ciphertext.getC1().getX());
        firstPlain.setY(secretKey * ciphertext.getC1().getY());
        messagePoint.setX(ciphertext.getC2().getX() - firstPlain.getX());
        messagePoint.setY(ciphertext.getC2().getY() - firstPlain.getY());
        plaintext = ellipticalCurveReverseFunction(messagePoint.getY());
//        plaintext = messagePoint.getX();
        
        return plaintext;
    }
    
    private long ellipticalCurveFunction(long x) {
        long y;
        
        y = (long) Math.sqrt(Math.pow(x, 3) + a * x + b);
        
        return y;
    }
    
    private long ellipticalCurveReverseFunction(long y) {
        long x, d;
        
        d = b - (long) Math.pow(y, 2);
        x = (long) Math.cbrt(-d/2 + Math.sqrt(Math.pow(-d/2, 2) + Math.pow(a/3, 3))) + (long) Math.cbrt(-d/2 - Math.sqrt(Math.pow(-d/2, 2) + Math.pow(a/3, 3)));
        
        return x;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
