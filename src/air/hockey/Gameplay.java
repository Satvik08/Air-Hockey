/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package air.hockey;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Satvik
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener{
    
    private boolean play = false;
    private int score1 = 0;
    private int score2 = 0;
    
    private Timer timer;
    private int delay = 10;
    private int check = 1;
    
    private int balldirX = -1;
    private int balldirY = -2;
    
    private int player1X = 290;
    private int player2X = 290;
    
    private int ballposX = 110;
    private int ballposY = 320;
    
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        
    }
    
    public void paint(Graphics g){
        
        //Background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        //Border and field
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 10, 592);
        g.fillRect(682, 0, 10, 592);
        g.fillRect(0, 290, 692, 10);
        g.fillOval(246, 196, 200, 200);
        
        g.setColor(Color.black);
        g.fillOval(261, 210, 170, 170);
        
        //paddles
        g.setColor(Color.blue);
        g.fillRect(player1X, 545, 100, 8);
        g.fillRect(player2X, 12, 100, 8);
        
        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Player 2: "+score2, 550, 30);
        g.drawString("Player 1: "+score1, 550, 325);
       
        
        //ball
        g.setColor(Color.green);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        if(ballposY <= 11)
            {
                play = false;
                player1X = 290;
                player2X = 290;
    
                ballposX = 110;
                ballposY = 320;
                
                score1 += 1;
                
            }
        
        if(score1 == 7)
        {
            balldirX = 0;
            balldirY = 0;
            g.setColor(Color.black);
            g.fillRect(100, 210, 450, 300);
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Player 1 wins", 130 , 300);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Press Enter to play again", 130, 330);
        }
        if(score2 == 7)
        {
            balldirX = 0;
            balldirY = 0;
            g.setColor(Color.black);
            g.fillRect(100, 210, 450, 300);
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Player 2 wins", 130 , 300);
            g.setFont(new Font("serif", Font.BOLD, 35));
            g.drawString("Press Enter to play again", 130, 330);
        }
        
            if(ballposY >= 546)
            {
                play = false;
                player1X = 290;
                player2X = 290;
    
                ballposX = 110;
                ballposY = 320;
                score2 += 1;
                
            }
        
        g.dispose();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
         if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(player1X >= 580)
                player1X = 580;
            else
                moveright1();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(player1X <= 10)
                player1X = 10;
            else
                moveleft1();
        }
         if(e.getKeyCode() == KeyEvent.VK_C){
            if(player2X >= 580)
                player2X = 580;
            else
                moveright2();
        }
        if(e.getKeyCode() == KeyEvent.VK_X){
            if(player2X <= 10)
                player2X = 10;
            else
                moveleft2();
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            play = true;
            score1 = 0;
            score2 = 0;
            balldirX = -1;
            balldirY = -2;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if((new Rectangle(ballposX, ballposY, 20, 20)).intersects(new Rectangle(player1X, 545, 100, 8)))
                balldirY = -balldirY;
            if((new Rectangle(ballposX, ballposY, 20, 20)).intersects(new Rectangle(player2X, 12, 100, 8)))
                balldirY = -balldirY;
            
            ballposX += balldirX;
            ballposY += balldirY;
            if(ballposX < 10)
                balldirX = -balldirX;
            if(ballposX > 670)
                balldirX = -balldirX;
        }
        repaint();
    }

    public void moveright1(){
        play = true;
        player1X += 20;
    }
    
    public void moveleft1(){
        play = true;
        player1X -= 20;
    }
    public void moveright2(){
        play = true;
        player2X += 20;
    }
    public void moveleft2(){
        play = true;
        player2X -= 20;
    }
    
}
