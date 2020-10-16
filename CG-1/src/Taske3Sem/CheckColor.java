package Taske3Sem;

import java.awt.*;

public class CheckColor {
    private Point topLeft;
    private Point frameSize;

    public CheckColor(Point topLeft, Point frameSize) {
        this.topLeft = topLeft;
        this.frameSize = frameSize;
    }

    public Color check(Point p){
        Point botRight = new Point(frameSize.x - frameSize.x/8, frameSize.y - frameSize.y/8);  // 900 - 525
        if(p.y < topLeft.y && p.x > topLeft.x){
            return Color.ORANGE;
        }else if(p.y > topLeft.y && p.x < topLeft.x){
            return Color.GREEN;
        }else if(p.y + frameSize.y/2 > botRight.y && p.x + frameSize.x/2 < botRight.x){
            return Color.BLUE;
        }else if(p.y + frameSize.y/2 < botRight.y && p.x + frameSize.x/2 > botRight.x){
            return Color.RED;
        }
        return Color.DARK_GRAY;
    }
}
