
import java.awt.Color;
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
abstract class Shape 
{
    private Color color;
    public abstract void draw(Graphics g);
    public void setColor(Color c)
    {
        color = c;
    }
    public Color getColor()
    {
        return color;
    }
}
