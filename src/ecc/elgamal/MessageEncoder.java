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
    private ArrayList<EncryptionPoint> encriptionRes = new ArrayList<>();
        
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
    
    public void encript(Point publicKey, int rand) {
        KeyGenerator kg = new KeyGenerator(prime,max);
        kg.ellipticalCurveFunc(a, b);
        //check if the publicKey is valid
        for (int i = 0; i < encriptedPoints.size(); i++) {
            //kg.printEllipticalPoints();
            //publicKey.print();
            if (kg.isElementOfPoints(publicKey)) {
                //Encription
                Point C1 = new Point(kg.getPointVect().get(rand));
                Point C2 = new Point(kg.getPointVect().get(rand + kg.checkKeyPosition(publicKey)));
                C2 = C2.addPoints(encriptedPoints.get(i));
                EncryptionPoint ep = new EncryptionPoint(C1,C2);
                
                encriptionRes.add(ep);

            } else {
                System.out.println("wrong public key");
            }
        }
    }
    
    public void printEncriptedPoints() {
        for (int i = 0; i < encriptedPoints.size(); i++) {
            encriptedPoints.get(i).print();
        }
    }
    
    public void printEncriptionRes() {
        for (int i = 0; i < encriptionRes.size(); i++) {
            encriptionRes.get(i).print();
            
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        MessageEncoder me = new MessageEncoder();
        KeyGenerator kg = new KeyGenerator(me.getPrime(),me.getMax());
        kg.ellipticalCurveFunc(me.getA(), me.getB());
        me.messageEncoding("aku");
        System.out.println("Encripted Points : ");
        me.printEncriptedPoints();
        System.out.println("Elliptical Points : ");
        kg.printEllipticalPoints();
        System.out.println("Public Key : ");
        kg.generatePublicKey(3).print();
        me.encript(kg.generatePublicKey(3),1);
        me.printEncriptionRes();
    }
    
    
}
