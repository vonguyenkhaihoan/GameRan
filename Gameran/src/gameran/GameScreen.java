/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameran;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import javax.swing.JPanel;

/**
 *
 * @author Acer NITRO 5
 */
public class GameScreen extends JPanel implements Runnable {
    
    static int[][] bg = new int  [40][40]; //tao ma tran bang mang 2 chieu
    static boolean isplaying = true;//biến dùng game
    static boolean enableTextStartGame = true; //khoi tao bien thay doi chu
    static boolean islose = false;// bien kiem tra thua game
    
    
    ConRan ran;
    Thread thread;
    
    static int CurrenLevel = 1;//bien level tro choi
    static int Diem = 0;//bien tinh diem
    public GameScreen(){
        ran = new ConRan();
        bg[15][15]=2;
        thread = new Thread(this);
        thread.start();

    }
    
    public void run(){
        long t = 0; //bien tthoi gian nhay chu
        while(true){
            
            if(System.currentTimeMillis()-t>500){
                enableTextStartGame=!enableTextStartGame;
                t=System.currentTimeMillis();
                
            }
            
            if(isplaying){
              ran.update();  
            }
            
            repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {
//                Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
    }
    
    public void paintBg(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, 802+400, 802);
        int a = 5;
        int b =bg.length-5;
        
        for(int i = 10 ; i < bg[0].length-10;i++ ){
            bg[a][i]=3;
            bg[b][i]=3;
        }
        int c = 5;
        int d =bg.length-5;
        for(int i = 5 ; i < bg[0].length-5;i++ ){
            bg[i][c]=3;
            bg[i][d]=3;
        }
        
        for(int i = 0; i < 40 ;i++){
            for(int j = 0; j< 40 ;j++){ 
                g.fillRect(i*20+1, j*20+1, 18, 18);
//                if(bg[i][j] == 0)g.setColor(Color.gray);
//                if(bg[i][j] == 1)g.setColor(Color.blue);
                if(bg[i][j] == 2){
                    g.setColor(Color.orange);
                    g.fillRect(i*20+1, j*20+1, 18, 18);
                    g.setColor(Color.black);
                }
                if(bg[i][j] == 3){
                    g.setColor(Color.red);
                    g.fillRect(i*20+1, j*20+1, 18, 18);
                    g.setColor(Color.black);
                }
                
            }
        }
    }    
    
    public void veKhung(Graphics g){
        g.setColor(Color.red);
        g.drawRect(0, 0, 800, 800);
        g.drawRect(1, 1, 801, 801);
        g.drawRect(2, 2, 800, 800);
        
        g.drawRect(0, 0, 800+400, 800);
        g.drawRect(1, 1, 801+400, 801);
        g.drawRect(2, 2, 800+400, 800);
    }
    
    @Override
    public void paint(Graphics g){
        
        paintBg(g);
        ran.veRan(g);
        veKhung(g);
        
        if(!isplaying){//thong bao dung game
            if(enableTextStartGame){
                g.setColor(Color.yellow);
                g.setFont(g.getFont().deriveFont(20.0f));
                g.drawString("STOP! PRESS PASCE TO PLAY GAME", 200, 450);
            }
        }
        
        if(islose){//thong bao dung game
            g.setColor(Color.yellow);
            g.setFont(g.getFont().deriveFont(50.0f));
            g.drawString("GAME OVER!", 250, 400);
        }
        
        g.setColor(Color.blue);
        g.setFont(g.getFont().deriveFont(30.0f));
        g.drawString("Level: "+CurrenLevel, 840, 100);
        
        g.setColor(Color.gray);
        g.setFont(g.getFont().deriveFont(25.0f));
        g.drawString("Diem: "+Diem, 850, 150);
        
        g.drawLine(800, 160, 1200, 160);
        g.drawString("Danh sách người chơi",   880, 190);
        g.setColor(Color.yellow);
        g.drawString("Ten",   880, 240);
        g.drawString("Level",   980, 240);
        g.drawString("Diem",   1080, 240);
        
//        g.drawString("Ten"+"Level"+"Diem",   880, 240);
        
        for(int i = 0 ;i < Gameran.user.size(); i++){
            g.drawString(i+1+".   "+Gameran.user.get(i).toString(),850, i*30+ 280);
        }


    }
}
