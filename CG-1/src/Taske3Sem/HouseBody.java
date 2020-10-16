package Taske3Sem;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.util.Pair;

import java.awt.*;
import java.awt.Frame;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class HouseBody {

    private Point p1;
    private Point ofPoint  = new Point(0,0);
    private Point oldP1;

    private Shape Rect(int X, int Y, int x,int y) {
        return new Rectangle(X, Y, x/2,y/2 );
    }

    void print(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point p = getNewOfPoint();
        g2d.drawRect(0,0, p.x,p.y);
        CheckColor check = new CheckColor(new Point(p1.x/8, p1.y/8),new Point(p1.x,p1.y));
        g2d.setColor(check.check(new Point(p.x,p.y)));
        g2d.fill(Rect(p.x,p.y,p1.x,p1.y));
    }

    void setPoint(Point p1){
        this.p1 = p1;
    }

    public Point getP1() {
        return p1;
    }

    Point getOfPoint(){
        if (ofPoint == null){
            return new Point(0,0);
        }
        return ofPoint;
    }

    Point getNewOfPoint(){
        Pair<Integer,Integer> temp = getOldP1();
        int X = Math.max(0,ofPoint.x-temp.getKey());
        int Y = Math.max(0,ofPoint.y-temp.getValue());
        return new Point(X,Y);
    }

    Point getCenterPoint(){
        return new Point(p1.x/2,p1.y/2);
    }

    void setOfPoint(Point p){
        ofPoint = p;
    }

    void setOldP1(){
        oldP1 = p1;
    }

    Pair<Integer,Integer> getOldP1(){
        if(oldP1 == null){
            oldP1 = new Point(0,0);
        }
        return new Pair(Math.max(0,oldP1.x - p1.x),Math.max(0,oldP1.y - p1.y)); //
    }

    boolean connectShape(Point p) {
        int firstVerX = getNewOfPoint().x;
        int secondVerX = firstVerX + p1.x/2;
        int firstVerY = getNewOfPoint().y;
        int secondVerY = firstVerY + p1.y/2;
        if (p.x > firstVerX && p.x < secondVerX && p.y > firstVerY && p.y < secondVerY)
            return true;
        else return false;
    }

}
