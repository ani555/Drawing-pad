/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aniruddha Bala
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
class VerticalMenuBar extends JMenuBar 
{
    private static final LayoutManager grid = new GridLayout(0,1);
    public VerticalMenuBar() 
    {
        setLayout(grid);
    }
}
public class Paint 
{   
    JFrame frame;
    JLabel statusbar;
    Color COLOR;
    Box box;
    ButtonGroup btngrp;
    JLabel shape;
    JRadioButton freehand,line,rectangle,circle;
    boolean isfreehand=true,isline,isrectangle,iscircle;
    boolean START=false;
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private int XOFFSET=7 ,YOFFSET=12;
    FreeHandLine currfhline;
    Rectangle currrect;
    Line currline;
    Circle currcircle;
    public static void main(String[] args)
    {
        new Paint().go();
    }
    public void go()
    {
        frame = new JFrame("Paint");
        statusbar = new JLabel(" ");
        box =new Box(BoxLayout.PAGE_AXIS);
        freehand = new JRadioButton("Freehand");
        freehand.setSelected(true);
        line = new JRadioButton("Line");
        rectangle = new JRadioButton("Rectangle");
        circle = new JRadioButton("Circle");
        btngrp = new ButtonGroup();
        btngrp.add(freehand);
        btngrp.add(line);
        btngrp.add(rectangle);
        btngrp.add(circle);
        shape = new JLabel("<html>Choose a <font color='red'>tool:</font></html>");
        box.add(shape);
        box.add(freehand);
        box.add(line);
        box.add(rectangle);
        box.add(circle);
        JButton setcolor = new JButton("<html><strong>Choose Color<strong></html>");
        setcolor.addActionListener(new ChangeColorListener());
        JButton clear = new JButton("<html><strong>Clear</strong></html>");
        clear.addActionListener(new ClearListener());
        box.add(setcolor);
        box.add(clear);
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        statusbar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        DrawPanel dp = new DrawPanel();
        dp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        MouseInputAdapter mouselistener = new MouseInputAdapter(){
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if(freehand.isSelected())
                {
                    currfhline.addPoint(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                }
                else if(line.isSelected())
                {
                    currline.setEndCoord(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                }
                else if(rectangle.isSelected())
                {
                    currrect.setEndCoord(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                }
                else  if(circle.isSelected())
                {
                    currcircle.setEndCoord(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                }
                if(e.getX()-box.getWidth()-XOFFSET>=0)
                statusbar.setText("("+(e.getX()-box.getWidth()-XOFFSET)+","+(e.getY()-statusbar.getHeight()-YOFFSET)+")");
                else
                statusbar.setText(" ");
                frame.repaint();
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
                if(freehand.isSelected())
                {
                    currfhline = new FreeHandLine();
                    currfhline.setColor(COLOR);
                    currfhline.addPoint(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                    shapes.add(currfhline);
                }
                else if(line.isSelected())
                {
                    currline = new Line();
                    currline.setColor(COLOR);
                    currline.setOrigin(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                    shapes.add(currline);
                }
                else if(rectangle.isSelected())
                {
                    currrect = new Rectangle();
                    currrect.setColor(COLOR);
                    currrect.setOrigin(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                    shapes.add(currrect);
                }
                else if(circle.isSelected())
                {
                    currcircle = new Circle();
                    currcircle.setColor(COLOR);
                    currcircle.setOrigin(e.getX()-box.getWidth()-XOFFSET,e.getY()-statusbar.getHeight()-YOFFSET);
                    shapes.add(currcircle);
                }
                if(e.getX()-box.getWidth()-XOFFSET>=0)
                statusbar.setText("("+(e.getX()-box.getWidth()-XOFFSET)+","+(e.getY()-statusbar.getHeight()-YOFFSET)+")");
                else
                statusbar.setText(" ");
            }
            @Override
            public void mouseMoved(MouseEvent e)
            {
                if(e.getX()-box.getWidth()-XOFFSET>=0)
                statusbar.setText("("+(e.getX()-box.getWidth()-XOFFSET)+","+(e.getY()-statusbar.getHeight()-YOFFSET)+")");
                else
                statusbar.setText(" ");
            }    
         
        };     
        frame.addMouseListener(mouselistener);
        frame.addMouseMotionListener(mouselistener);
        frame.setBackground(Color.white);
        frame.getContentPane().add(BorderLayout.CENTER,dp);
        frame.getContentPane().add(BorderLayout.SOUTH,statusbar);
        frame.getContentPane().add(BorderLayout.WEST,box);
        frame.setSize(500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    public class DrawPanel extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            if(shapes == null)
            {
                g.setColor(Color.white);
                g.fillRect(box.getWidth(), 0,frame.getWidth()-box.getWidth(),frame.getHeight()-box.getHeight());
            }
            for(Shape sh : shapes)
            {
                if(sh instanceof FreeHandLine)
                {
                    FreeHandLine fhline = (FreeHandLine)sh;
                    g.setColor(fhline.getColor());
                    fhline.draw(g);
                }
                else if(sh instanceof Line)
                {
                    Line lin = (Line)sh;
                    g.setColor(lin.getColor());
                    lin.draw(g);
                }
                else if(sh instanceof Rectangle)
                {
                    Rectangle rect = (Rectangle)sh;
                    g.setColor(rect.getColor());
                    rect.draw(g);
                }
                else if(sh instanceof Circle)
                {
                    Circle circle = (Circle)sh;
                    g.setColor(circle.getColor());
                    circle.draw(g);
                }
            }
        }
    }
   
    public class ChangeColorListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            COLOR = JColorChooser.showDialog(frame,"Choose a color",Color.red);
        }
    }
    public class ClearListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            shapes.clear();
            frame.repaint();
        }
    }
 
}
