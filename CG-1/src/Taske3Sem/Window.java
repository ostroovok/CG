package Taske3Sem;

import javafx.util.Pair;

import java.awt.*;

public class Window {

    private Color color;
    private HouseBody parent;
    private int width;
    private int height;
    private Point p1;
    private int leftOrRight;

    Window(Color color, HouseBody parent, int width, int height, int leftOrRight) {
        this.color = color;
        this.parent = parent;
        this.width = width;
        this.height = height;
        this.leftOrRight = leftOrRight;
    }

    void print(Graphics g){
        printObj(g,color,parent,width,height, leftOrRight);
    }

    private void printObj(Graphics g, Color color, HouseBody parent, int width, int height, int leftOrRight){
        g.setColor(color);
        Point ofPoint = parent.getOfPoint();
        Pair<Integer,Integer> temp = parent.getOldP1();
        Point mainPoint = parent.getP1();
        int X = Math.max(0,ofPoint.x-temp.getKey());
        int Y = Math.max(0,ofPoint.y-temp.getValue());
        g.drawRect(X + (parent.getCenterPoint().x/2*leftOrRight),Y, mainPoint.x/12, mainPoint.y/8);
    }
}
