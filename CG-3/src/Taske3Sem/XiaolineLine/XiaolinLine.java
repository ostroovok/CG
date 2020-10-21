package Taske3Sem.XiaolineLine;

import java.awt.*;
import java.sql.SQLOutput;

public class XiaolinLine implements LineDrawer {

    @Override
    public void drawLine(Graphics2D g, int x1, int y1, int x2, int y2) {
        PixelDrawer g2d = new Pixels(g);
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        float gradient;

        if (dx > dy) {
            gradient = (float) dy / dx;
            float intersectY = y1 + gradient;
            g2d.pixelSetColor(Color.BLACK);
            g2d.setPixel(x2, y2);
            for (int i = x1; i < x2; i++) {
                g2d.pixelSetColor(new Color(0, 0, 0, (int) (255 - fractional(intersectY) * 255))); //Меняем прозрачность
                g2d.setPixel(i, (int) intersectY);
                g2d.pixelSetColor(new Color(0, 0, 0, (int) (fractional(intersectY) * 255)));
                g2d.setPixel(i, (int) intersectY + 1);
                intersectY += gradient;
            }
            g2d.pixelSetColor(Color.BLACK);
            g2d.setPixel(x2, y2);
        }else{
            gradient = (float) dx/dy;
            float intersectX = x1 + gradient;
            g2d.pixelSetColor(Color.BLACK);
            g2d.setPixel(x1, y1); // первая точка
            for (int i = y1; i < y2; i++) {
                //System.out.println(Math.max((int) (255 - fractional(intersectX) * 255),1));
                g2d.pixelSetColor(new Color(0, 0, 0, (int) (255 - fractional(intersectX) * 255))); //Меняем прозрачность
                g2d.setPixel((int) intersectX, i);
                g2d.pixelSetColor(new Color(0, 0, 0,(int) (fractional(intersectX) * 255)));
                g2d.setPixel((int) intersectX + 1, i);
                intersectX += gradient;
            }
            g2d.pixelSetColor(Color.BLACK);
            g2d.setPixel(x2, y2); // вторая точка
        }
    }

    private float fractional(float x) {
        int temp = (int) x;
        return Math.abs(x - temp); // получаем число после запятой
    }
}
