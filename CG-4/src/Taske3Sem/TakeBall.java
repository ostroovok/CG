package Taske3Sem;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class TakeBall {
    private Point point;
    private int size;

    public TakeBall(Point point,int size) {
        this.point = point;
        this.size = size;
    }

    public Point getPoint(){
        return point;
    }
    public void setPoint(Point point){
        this.point = point;
    }

    void paint(Graphics g, int count) {
        g.setColor(Color.BLACK);
        g.fillRect(point.x,point.y,size/3,size/3);
        g.drawRect(point.x,point.y,size,size);

        FontRenderContext frc = ((Graphics2D) g).getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 45);
        String s = Integer.toString(count);
        TextLayout tl = new TextLayout(s, f, frc);
        AffineTransform at = new AffineTransform();
        at.setToTranslation(point.x + size + 4 , point.y + size/2);
        Shape sh = tl.getOutline(at);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).draw(sh);
    }

    void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(point.x,point.y,size/3,size/3);
        g.drawRect(point.x,point.y,size,size);
    }

    public int getSize() {
        return size;
    }

    boolean connectShape(Point p) {
        int firstVerX = point.x;
        int secondVerX = point.x + size;
        int firstVerY = point.y;
        int secondVerY = point.y + size;
        return p.x > firstVerX && p.x < secondVerX && p.y > firstVerY && p.y < secondVerY;
    }
}
