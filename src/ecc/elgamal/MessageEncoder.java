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
    private long max = 30;
    private int a = 2;
    private int b = 1;
    private ArrayList<Point> encriptedPoints = new ArrayList<>();
    private ArrayList<EncryptionPoint> encriptionRes = new ArrayList<>();
    private KeyGenerator kg;
    
    public MessageEncoder() {
        kg = new KeyGenerator(prime,max);
        kg.setA(a);
        kg.setB(b);
        kg.ellipticalCurveFunc();
        kg.printEllipticalPoints();
    }

    public KeyGenerator getKg() {
        return kg;
    }

    public void setKg(KeyGenerator kg) {
        this.kg = kg;
    }
    
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
    
    public String messageDecoding() {
        String res = "";
        
        for (int i = 0; i < encriptedPoints.size(); i++) {
            res += (char)(encriptedPoints.get(i).getX()-encriptedPoints.get(i).getY());
        }
        return res;
    }
    
    public void encript(Point publicKey, int rand) {
        //check if the publicKey is valid
        for (int i = 0; i < encriptedPoints.size(); i++) {
            if (kg.publicKeyValidation(publicKey)) {
                Point C1 = new Point(kg.getG().multiply(rand));
                Point C2 = new Point(publicKey.multiply(rand));
                C2 = C2.addPoints(encriptedPoints.get(i));
                EncryptionPoint ep = new EncryptionPoint(C1,C2);
                
                encriptionRes.add(ep);

            }
            else {
                System.out.println("wrong public key");
            }
        }
    }
    
    public void decript(Point privateKey) {
        for (int i = 0; i < encriptionRes.size(); i++) {
            Point M = new Point(privateKey.multiplePoints(encriptionRes.get(i).getC1()));
            Point Pm = new Point(encriptionRes.get(i).getC2().substractPoints(M));
            Pm.print();
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
        me.messageEncoding("aku");
        System.out.println(me.messageDecoding());
        
        System.out.println("Encripted Points : ");
        me.printEncriptedPoints();
        System.out.println("Elliptical Points : ");
        me.getKg().printEllipticalPoints();
        
        ///NOTES : Nilai k dari Bob dan Alice tidak boleh melebihi largestOrder di KeyGenerator
        System.out.print("Bob's Private Key : ");
        Point bobPrivate = me.getKg().generatePrivateKey(1);
        me.getKg().generatePrivateKey(1).print();
        System.out.print("Bob's Public Key : ");
        Point bobPublic = me.getKg().generatePublicKey(bobPrivate);
        me.getKg().generatePublicKey(bobPrivate).print();
        
        System.out.print("Alice's Private Key : ");
        Point alicePrivate = me.getKg().generatePrivateKey(2);
        me.getKg().generatePrivateKey(2).print();
        System.out.print("Alice's Public Key : ");
        Point alicePublic = me.getKg().generatePublicKey(alicePrivate);
        me.getKg().generatePublicKey(bobPrivate).print();
        
        me.encript(bobPublic,1); //rand number = 1
        System.out.println("Encription Result :");
        me.printEncriptionRes();

        System.out.println("Decript Result Pm : ");
        me.decript(bobPrivate);
    }
    
    
}
