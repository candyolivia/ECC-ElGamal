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
    
    public void ellipticalCurveFunc(int a, int b) {
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
    }
    
    public void printEllipticalPoints() {
        for (int i = 0; i < pointVect.size(); i++) {
            pointVect.get(i).print();
        }
    }
    
    public Point generatePublicKey(int n) { //asumsi nilai n pasti lebih kecil dari prime
        if (n > prime) {
            System.out.println("Error nilai n harus lebih kecil atau sama dengan prime");
        }
        return pointVect.get(n-1);
    }
    
    public Point generatePrivateKey(int n, Point p) { //asumsi nilai n pasti lebih kecil dari prime
        if (n > prime) {
            System.out.println("Error nilai n harus lebih kecil atau sama dengan prime");
            
        }
        
        int idx = 0;
        for (int i = 0; i < pointVect.size(); i++) {
            if(p.equals(pointVect.get((i+n)%prime))) {
                idx = (i+n)%prime;
                break;
            }
        }
        
        return pointVect.get(idx);
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
    
//    public boolean checkKeyValidity(int a, Point privateKeyA, Point publicKeyB) {
//        
//        System.out.println((checkKeyPosition(publicKey)-a+1)%pointVect.size());
//        
//        return (pointVect.get(a-1).equals(pointVect.get((checkKeyPosition(publicKey)-a+1)%pointVect.size())));
//    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        KeyGenerator kg = new KeyGenerator();
        kg.ellipticalCurveFunc(2, 1);
        kg.printEllipticalPoints();
        System.out.print("Bob's Public Key : ");
        kg.generatePublicKey(2).print(); //Bob's Private Key
        Point p1 = new Point(kg.generatePublicKey(2).getX(),kg.generatePublicKey(2).getY());
        System.out.print("Alice's Public Key : ");
        kg.generatePublicKey(3).print(); //Alice's Private Key
        Point p2 = new Point(kg.generatePublicKey(3).getX(),kg.generatePublicKey(3).getY());
        System.out.print("Bob's Private Key : ");
        kg.generatePrivateKey(2, p2).print();
        System.out.print("Alice's Private Key : ");
        kg.generatePrivateKey(3, p1).print();
        
        System.out.println(kg.isElementOfPoints(kg.generatePublicKey(3)));
                
    }
    
}
