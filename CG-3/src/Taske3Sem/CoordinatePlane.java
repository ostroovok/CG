package Taske3Sem;

import Taske3Sem.XiaolineLine.XiaolinLine;

import java.awt.*;

public class CoordinatePlane {
    private int unitSegment;
    private int width;
    private int height;
    XiaolinLine line = new XiaolinLine();

    public CoordinatePlane(int unitSegment, int width, int height) {
        this.unitSegment = unitSegment;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics2D g){
        createAxis(g);
        int unitSegmentLine = unitSegment/2;
        for (int i = height/2+unitSegmentLine; i < height; i+=unitSegment) {
            line.drawLine(g,width/2-unitSegmentLine/2,i,width/2+unitSegmentLine/2,i);
        }
        for (int i = width/2+unitSegmentLine; i < width; i+=unitSegment) {
            line.drawLine(g,i,height/2-unitSegmentLine/2, i,height/2+unitSegmentLine/2);
        }
        for (int i = height/2-unitSegmentLine; i >= 0; i-=unitSegment) {
            line.drawLine(g,width/2-unitSegmentLine/2,i,width/2+unitSegmentLine/2,i);
        }
        for (int i = width/2-unitSegmentLine; i >= 0; i-=unitSegment) {
            line.drawLine(g,i,height/2-unitSegmentLine/2, i,height/2+unitSegmentLine/2);
        }
    }

    private void createAxis(Graphics2D g) {
        line.drawLine(g,width/2,0,width/2,height);
        line.drawLine(g,0,height/2,width,height/2);
    }
}
