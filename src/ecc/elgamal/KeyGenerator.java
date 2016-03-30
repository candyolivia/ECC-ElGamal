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
public class KeyGenerator {
    private ArrayList<Point> pointVect = new ArrayList<>();
    private int prime;
    private long max;
    private int largestOrder;
    private Point G;
    private int a = 2;
    private int b = 1;

    public KeyGenerator(int prime, long max) {
        this.prime = prime;
        this.max = max;
    }
    public KeyGenerator() {
        prime = 5;
        max = 15;
    }
    
    public ArrayList<Point> getPointVect() {
        return pointVect;
    }

    public void setPointVect(ArrayList<Point> pointVect) {
        this.pointVect = pointVect;
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

    public Point getG() {
        return G;
    }

    public void setG(Point G) {
        this.G = G;
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

    public int getLargestOrder() {
        return largestOrder;
    }

    public void setLargestOrder(int largestOrder) {
        this.largestOrder = largestOrder;
    }
   
    public void ellipticalCurveFunc() {
        int y; //y2 = x3 + ax + b
        for (int x = 0; x < max; x++) {
            y = (int)Math.sqrt(x*x*x + a*x + b);
            if (y*y == x*x*x + a*x + b) {
                int xTemp = x;
                if (xTemp > prime || y > prime) {
                    xTemp %= prime;
                    y %= prime;
                }
                
                if (y != (prime-y)) {
                    Point temp = new Point(xTemp,(prime-y));
                    pointVect.add(temp);
                }
                
                Point temp = new Point(xTemp,y);
                pointVect.add(temp);
            }
        }
        largestOrder = pointVect.size()-1;
        G = pointVect.get(largestOrder);
    }
    
    public void printEllipticalPoints() {
        for (int i = 0; i < pointVect.size(); i++) {
            pointVect.get(i).print();
        }
    }
    
    public Point generatePublicKey(Point privateKey) { //asumsi nilai n pasti lebih kecil dari prime
        Point Q = new Point(G.multiplePoints(privateKey));
        return Q;
    }
    
    public Point generatePrivateKey(int n) { //asumsi nilai n pasti lebih kecil dari prime
        Point p = new Point();
        if (n > largestOrder){
            System.out.println("Error nilai n harus lebih kecil atau sama dengan largestOrder");
            assert n>largestOrder : "number is greater than largestOrder of GeneratedPoints";
            p = null;
            System.exit(0);
            
        } else {
            p = pointVect.get(n-1);
        }
        return p;
    }
    
    public boolean publicKeyValidation(Point publicKey) {
        boolean cek = false;
        if (publicKey.isOrigin()) {
            //do nothing because key at infinity
        } else {
            //x*x*x + a*x + b
            cek = isElementOfPoints(publicKey.dividePoints(G));
        }
        return cek;
    }
    
    public int checkKeyPosition(Point key) {
        int idx = -99;
        for (int i = 0; i < pointVect.size(); i++) {
            if (key.isEquals(pointVect.get(i))) {
                idx = i;
                break;
            }
        }
        return idx;
    }
    
    public boolean isElementOfPoints(Point p) {
        boolean cek = false;
        for (int i = 0; i < pointVect.size(); i++) {
            if (p.isEquals(pointVect.get(i))){
                cek = true;
                break;
            }
        }
        return cek;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        KeyGenerator kg = new KeyGenerator();
        kg.ellipticalCurveFunc();
        kg.printEllipticalPoints();
        System.out.print("Bob's Private Key : ");
        Point bobPrivate = kg.generatePrivateKey(9);
        kg.generatePrivateKey(2).print();
        System.out.print("Bob's Public Key : ");
        Point bobPublic = kg.generatePublicKey(bobPrivate);
        kg.generatePublicKey(bobPrivate).print();
        System.out.println("Key Validation : " +kg.publicKeyValidation(bobPublic));
                
    }
    
}
