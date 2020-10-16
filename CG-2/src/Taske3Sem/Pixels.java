package Taske3Sem;

import java.awt.*;

public class Pixels implements PixelDrawer {
    private Graphics2D g;

    public Pixels(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void setPixel(int x, int y) {
        g.fillRect(x,y,1,1);
    }

    @Override
    public void pixelSetColor(Color c) {
        g.setColor(c);
    }
}
