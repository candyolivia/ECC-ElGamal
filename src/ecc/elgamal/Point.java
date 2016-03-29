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
public class Point {
    private int x;
    private int y;
    
    public Point() {
        x = 0;
        y = 0;
    }
    
    public Point(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void print() {
        System.out.println("x = " + x + " y = " + y);
    }
    
    public boolean isEquals(Point p1,Point p2) {
        return (p1.getX()==p2.getX() && p1.getY() == p2.getY());
    }
}
