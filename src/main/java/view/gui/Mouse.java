package view.gui;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// work in progress
// tracks mouse movement
// somewhere needs e.getX() and e.getY() for x1,y1  x2,y2 pairs
// application state should probably know about MouseListener?

public class Mouse implements MouseListener {
    // .addMouseListener(this);
    MouseEvent e;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e){
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
