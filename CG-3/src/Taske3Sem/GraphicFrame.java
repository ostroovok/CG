package Taske3Sem;

//import com.vgu.cs.course2.group11.yakovlev_n_je.algorithms.Wu;

import Taske3Sem.XiaolineLine.XiaolinLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

// ПЕЧАТАЕТ НА НЕКСТ ПИКСЕЛЬ

public class GraphicFrame extends Frame {

    private int width = 1000;//this.getWidth(); // ширина панели для графа
    private int height = 1000;//this.getHeight(); // высота панели для графа

    private JPanel contentPane;
    private JLabel label;
    private JTextField functionField;
    private JButton drawButton;
    private JPanel drawPane;

    private XiaolinLine line = new XiaolinLine();
    private int Y = height/2;
    private int X = width/2;
    private Point oldPoint = new Point(X,Y);
    private ParseString str;
    private LinkedList<Integer> listOfPoints = null;
    private int unitSegment = 10;
    private boolean check = false;

    public GraphicFrame() {
        contentPane = new JPanel();
        contentPane.setBounds(0, 0, width, height);
        contentPane.setLayout(null);

        label = new JLabel("Ввод: ");
        label.setLocation(20, 20);
        label.setSize(new Dimension(80, 30));
        contentPane.add(label);

        functionField = new JTextField();
        functionField.setLocation(100, 20);
        functionField.setSize(new Dimension(200, 30));
        functionField.setText("y = 25*x^2 + 8*x + 78");
        contentPane.add(functionField);

        drawButton = new JButton("Построить график");
        drawButton.setLocation(20, 55);
        drawButton.setSize(new Dimension(280, 30));
        contentPane.add(drawButton);

        drawPane = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                if(check){
                    paintGraph(g2d);
                }
                drawAxes(g2d);
                drawCoordinates(g2d);
            }

            private void drawAxes(Graphics2D g) {
                line.drawLine(g,width/2,0,width/2,height);
                line.drawLine(g,0,height/2,width,height/2);
            }

            private void drawCoordinates(Graphics2D g) {
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
            private void paintGraph(Graphics2D g2d){
                str = new ParseString(functionField.getText()); // "y = 25*x^2 + 8*x + 78"
                listOfPoints = str.parse();
                for (double x = -100; x < 100; x += 0.001) {
                    X = (int) (x *10) + width/2;
                    if(listOfPoints != null) {
                        for (int i = 0; i < listOfPoints.size(); i += 2) {
                            int k = listOfPoints.get(i);
                            int degree = listOfPoints.get(i + 1);
                            Y +=  (int)(k * Math.pow(x, degree));
                        }
                        Y = height/2 - Y;
                        line.drawLine(g2d,oldPoint.x,oldPoint.y,X,Y);
                        oldPoint = new Point(X,Y);
                        Y = 0;
                    }
                }
                check = false;
            }
        };
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = true;
                contentPane.repaint();
            }
        });

        drawPane.setBounds(0, 0, width, height);
        contentPane.add(drawPane);
        contentPane.setOpaque(true);
    }

    public static void run() {
        JFrame frame = new JFrame("Построение графика функции");
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setContentPane(new GraphicFrame().contentPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}