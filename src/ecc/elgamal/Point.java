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
    
    public Point(Point p) {
        x = p.getX();
        y = p.getY();
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
    
    public boolean isEquals(Point p) {
        return (p.getX()==x && p.getY() == y);
    }
    
    public Point addPoints(Point p1) {
        return new Point (p1.getX()+x, p1.getY()+y);  
    }
    
    public Point substractPoints(Point p1) {
        return new Point (x-p1.getX(), y-p1.getY());  
    }
    
    public Point multiplePoints(Point p1){
        return new Point (x*p1.getX(), y*p1.getY());
    }
    
    public Point dividePoints(Point p1){
        return new Point (x/p1.getX(), y/p1.getY());
    }
    
    public Point multiply(int n) {
        return new Point (x*n, y*n);
    }
    
    public boolean isOrigin() {
        return (x==0 && y ==0);
    }
}
