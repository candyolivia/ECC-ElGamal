/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc.elgamal;

import java.util.ArrayList;

/**
 *
 * @author Candy Olivia Mawalim
 */
public class MessageEncoder {
    private int prime = 5;
    private long max = 15;
    private int a = 2;
    private int b = 1;
    private ArrayList<Point> encriptedPoints = new ArrayList<>();
        
    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public void messageEncoding(String str) {
        KeyGenerator kg = new KeyGenerator(prime,max);
        kg.ellipticalCurveFunc(a, b);
        
        for (int i = 0; i < str.length(); i++) {
            int theta;
            int alpha;
            int x = (int) str.charAt(i);
            do {
                alpha = (x*x*x + a*x + b)%prime;
                theta = (int)(Math.pow(alpha,(prime-1)/2)%prime);
                x++;
            } while(theta!=1);
            
            int beta = (int) Math.sqrt(alpha%prime);
            int y = beta;
            
            Point pm = new Point(x,y);
            encriptedPoints.add(pm);
        }
    }
    
    public void encript(int a, Point publicKey) {
        KeyGenerator kg = new KeyGenerator(prime,max);
        kg.ellipticalCurveFunc(a, b);
        //check if the publicKey is valid
        int x;
        for (int i = 0; i < kg.getPointVect().size(); i++) {
            if (publicKey.equals(kg.getPointVect().get(i))) {
                x = (a - i) % prime;
                if ((x*x*x + a*x + b)==publicKey.getY()) {

                } else {
                    System.out.println("wrong public key");
                }
            } else {
                System.out.println("wrong public key");
                
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
}
