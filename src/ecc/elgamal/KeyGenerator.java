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
    
    public void ellipticalCurveFunc(int a, int b, int p) {
        int y; //y2 = x3 + ax + b
        for (int x = 0; x < 15; x++) {
            y = (int)Math.sqrt(x*x*x + a*x + b);
            if (y*y == x*x*x + a*x + b) {
                int xTemp = x;
                if (xTemp > p || y > p) {
                    xTemp %= p;
                    y %= p;
                }
                
                if (y != (p-y)) {
                    Point temp = new Point(xTemp,(p-y));
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
    
    public static void main(String[] args) {
        // TODO code application logic here
        KeyGenerator kg = new KeyGenerator();
        kg.ellipticalCurveFunc(2, 1, 5);
        kg.printEllipticalPoints();
    }
    
}
