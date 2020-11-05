package Taske3Sem;

import Taske3Sem.XiaolineLine.XiaolinLine;

import java.awt.*;

public class BezierCurve {

    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;
    private double t;
    private XiaolinLine line = new XiaolinLine();

    public BezierCurve(Point p1, Point p2, Point p3, double t) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.t = t;
    }

    public BezierCurve(Point p1, Point p2, Point p3, Point p4, double t) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.t = t;
    }

    public void drawCurve(Graphics2D g) {
        if(p4 == null){
            double x = p1.x; double y = p1.y;
            Point oldPoint = p1;
            for (double i = 0; i < 1; i+= t) {
                line.drawLine(g,oldPoint.x,oldPoint.y,(int)x,(int)y);
                oldPoint = new Point((int)x,(int)y);
                x = Math.pow((1-i),2)* p1.x + 2*i*(1-i)*p2.x + Math.pow(i,2)*p3.x;
                y = Math.pow((1-i),2)* p1.y + 2*i*(1-i)*p2.y + Math.pow(i,2)*p3.y;
            }
        }else{
            double x = p1.x; double y = p1.y;
            Point oldPoint = p1;
            for (double i = 0; i < 1; i+= t) {
                line.drawLine(g,oldPoint.x,oldPoint.y,(int)x,(int)y);
                oldPoint = new Point((int)x,(int)y);
                double v = Math.pow(-i, 3) + 3 * Math.pow(i, 2);
                x = p1.x*(v -3*i+1)
                        +3*p2.x*i*(Math.pow(i,2)-2*i+1)
                        +3*p3.x*Math.pow(i,2)*(1-i)+p4.x*Math.pow(i,3);
                y = p1.y*(v -3*i+1)
                        +3*p2.y*i*(Math.pow(i,2)-2*i+1)
                        +3*p3.y*Math.pow(i,2)*(1-i)+p4.y*Math.pow(i,3);
            }
        }
    }

    private double countLen(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x-p1.x,2) + Math.pow(p2.y-p1.y, 2));
    }
}
