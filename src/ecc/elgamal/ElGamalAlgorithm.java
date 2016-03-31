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
    public ArrayList<EncryptionPoint> encrypt(ArrayList<Long> plaintext, Point base, Point publicKey, long k) {
        ArrayList<EncryptionPoint> ciphertext = new ArrayList<>();
        Point firstCipher, secondCipher, messagePoint;
        
        for (int i = 0; i < plaintext.size(); i++) {
            messagePoint = new Point();
            messagePoint.setX(plaintext.get(i));
            messagePoint.setY(ellipticalCurveFunction(messagePoint.getX()));
            firstCipher = new Point(k * base.getX(), k * base.getY());
            secondCipher = new Point(k * publicKey.getX() + messagePoint.getX(), k * publicKey.getY() + messagePoint.getY());
            secondCipher.setY(secondCipher.getY() % 256);
            ciphertext.add(new EncryptionPoint(firstCipher, secondCipher));
        }
        
        return ciphertext;
    }
    
    public ArrayList<Long> decrypt(ArrayList<EncryptionPoint> ciphertext, long secretKey) {
        Point firstPlain, messagePoint;
        ArrayList<Long> plaintext = new ArrayList<>();
        
        firstPlain = new Point();
        messagePoint = new Point();
        for (int i = 0; i < ciphertext.size(); i++) {
            firstPlain.setX(secretKey * ciphertext.get(i).getC1().getX());
            firstPlain.setY(secretKey * ciphertext.get(i).getC1().getY());
            messagePoint.setX(ciphertext.get(i).getC2().getX());
            System.out.println(firstPlain.getX());
            messagePoint.setY(ciphertext.get(i).getC2().getY());
            plaintext.add(messagePoint.getX());
        }
        
        return plaintext;
    }
    
    private long ellipticalCurveFunction(long x) {
        long y;
        
        y = (long) Math.sqrt(Math.pow(x, 3) + a * x + b);
        
        return y;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
