package ex0004;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;



public class Brick 
{

    //Größe bzw. Koordinate vom Brick
    private double x, y, width, height;
   
    private boolean visible;

    //Collisionserkennung
    private Rectangle2D.Double brick,
            bottom, upper, left, right;

    // <editor-fold desc="Brick">
    public Brick(double x, double y, double width, double height) 
    {
        //Daten vom jeweiligen Brick
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        visible = true;

        brick = new Rectangle2D.Double(x, y, width, height);

        bottom = new Rectangle2D.Double(x, y + height - 3, width, 3);

        upper = new Rectangle2D.Double(x, y, width, 3);

        left = new Rectangle2D.Double(x, y, 3, height);

        right = new Rectangle2D.Double(x + width, y, 3, height);

    }
//</editor-fold>

    // <editor-fold desc="Brick zeichnen">
    public void draw(Graphics2D g2d) 
    {         
        //zeichnen vom Brick
        if (visible) 
        {          
              g2d.setPaint(Color.cyan);
              g2d.fill(brick);
       }
    }
//</editor-fold>
    
    // <editor-fold desc="Collisionserkennung">
    public Collision checkCollision(double xBall, double yBall, double radius) 
    {
        //Collisionserkennung
        Collision c = Collision.NO;
        if (visible) 
        {
            Rectangle2D.Double ball = new Rectangle2D.Double(xBall, yBall, 2 * radius, 2 * radius);

            if (upper.intersects(ball) || bottom.intersects(ball)) //obere Kante
            {      
                c = Collision.UPPER;
                visible = false;
                
            } 
            else if (left.intersects(ball) || right.intersects(ball)) // linke Kante
            {
                
                c = Collision.LEFT;
                visible = false;
            }
        }
        return c;
    }
    //</editor-fold>
}
