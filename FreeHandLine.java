/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aniruddha Bala
 */
import java.awt.Graphics;
import java.util.*;
public class FreeHandLine extends Shape {
    private ArrayList<Integer> X ;
    private ArrayList<Integer> Y ;
    
    public FreeHandLine()
    {
        X = new ArrayList<>();
        Y = new ArrayList<>();
    }
    public void addPoint(int x,int y)
    {
        X.add(x);
        Y.add(y);
    }
    public void draw(Graphics g)
    {
        for(int i =0; i<X.size()-1;i++)
        {
           g.drawLine(X.get(i),Y.get(i),X.get(i+1),Y.get(i+1));
        }
    }
}
