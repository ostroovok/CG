package Taske3Sem;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private Point centerPoint;
    private Color c = Color.GRAY;
    private double x;
    private double y;
    private double radius;
    private double angle;
    private double speedOfRotation;
    private int r, g, b = 5;
    private int a = 255;
    private int size;
    private boolean check;

    public Ball(Point centerPoint, double speedOfRotation, double radius, double angle, int size) {
        this.centerPoint = centerPoint;
        this.radius = radius;
        this.angle = angle;
        this.speedOfRotation = speedOfRotation;
        this.size = size;
    }

    public void update(double dt) {
        dt = 1000;
        angle += speedOfRotation / dt;
    }

    public Color getC() {
        return c;
    }

    public Point objLocation(){
        x = centerPoint.x + (radius * Math.cos(angle));
        y = centerPoint.y + (radius * Math.sin(angle));
        if(check)
            c = reUpdateColor();
        else
            c = updateColor();
        return new Point(x,y);
    }

    public void paint(Graphics2D g2d){
        //g.setColor(c);
        Point p = objLocation();
        g2d.setColor(getC());
        g2d.fill(circle(p.x,p.y,size));
    }

    private Shape circle(double x, double y, double r) {
        return new Ellipse2D.Double(x- r, y-r, 2*r, 2*r);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color updateColor(){
        if(r < 255){
            r += 5;
        }else if(g < 255){
            g += 5;
        }else if(b < 255){
            b += 5;
        }else {
           check = true;
        }
        return new Color(r,g,b,a);
    }
    public Color reUpdateColor(){
        if(b > 5){
            b -= 5;
        }else if(g > 5){
            g -= 5;
        }else if(r > 5){
            r -= 5;
        }else{
            check = false;
        }
        return new Color(r,g,b,a);
    }

    public boolean connectBall(Point p){

        return false;
    }
}
