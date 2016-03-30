/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecc.elgamal;

/**
 *
 * @author Candy Olivia Mawalim
 */
public class EncryptionPoint {
    private Point C1;
    private Point C2;

    public EncryptionPoint(Point c1, Point c2) {
        C1 = c1;
        C2 = c2;
    }
    
    public Point getC1() {
        return C1;
    }

    public void setC1(Point C1) {
        this.C1 = C1;
    }

    public Point getC2() {
        return C2;
    }

    public void setC2(Point C2) {
        this.C2 = C2;
    }
    
    public void print() {
        System.out.print("C1 : ");
        C1.print();
        System.out.print("C2 : ");
        C2.print();
        
    }
}
