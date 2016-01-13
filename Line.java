
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aniruddha Bala
 */
public class Line extends Shape {
    
    private int X1,X2,Y1,Y2;
    
    public void setOrigin(int x,int y)
    {
        X1=x;
        Y1=y;
    }
    public void setEndCoord(int x,int y)
    {
        X2=x;
        Y2=y;
    }
    
    public void draw(Graphics g)
    {
        g.drawLine(X1, Y1, X2, Y2);
    }
}
