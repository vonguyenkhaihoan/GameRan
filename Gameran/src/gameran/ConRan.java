/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameran;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer NITRO 5
 */
public class ConRan {
    
    int dodai = 3;//độ dài rắn
    int xsnack[];
    int ysnack[];
    
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;   
    
    int vector = ConRan.GO_LEFT ;//Tao huong di ban dau la di xuong
    
    long t1 = 0; 
    int tocdo =200;//biến tốc độ
    int dieukien = 5;
    boolean updateAfterchangeVt = true;
    
    public ConRan(){
        xsnack = new int[40];// do dai toi da x cua ran
        ysnack = new int[40];// do dai toi da y cua ran
        
        xsnack[0]=5;//toa do x ban dau cua ran
        ysnack[0]=3; //toa do y ban dau cua ran
        
        xsnack[1]=5;
        ysnack[1]=2;
        
        xsnack[2]=5;
        ysnack[2]=1;
    }
    
    public void resetGame(){
        xsnack = new int[40];// do dai toi da x cua ran
        ysnack = new int[40];// do dai toi da y cua ran
        
        xsnack[0]=5;//toa do x ban dau cua ran
        ysnack[0]=3; //toa do y ban dau cua ran
        
        xsnack[1]=5;
        ysnack[1]=2;
        
        xsnack[2]=5;
        ysnack[2]=1;
        
        dodai=3;

        vector = ConRan.GO_LEFT;

    }
    
    public void setVector(int v){//sua vector
        if(vector != -v && updateAfterchangeVt)
            vector = v;
            updateAfterchangeVt = false;
    }
    
    public boolean kiemtramoi(int x, int y){//ham kiem tra thuc an co trung voi than ran
        for(int i = 0; i<dodai ;i++){
            if(xsnack[i]==x && ysnack[i]==y){
                return true;}   
        }
        return false;
    }
    
    public Point toadomoi(){
        Random r = new Random();
        int x ;
        int y ;
        do{
            x = r.nextInt(39);
            y = r.nextInt(39);
        }while(kiemtramoi(x,y));
        return new Point(x,y);
    }
    
    public int getCurrenSpeed(){
        int speed = 200;
        for(int i = 0 ; i < GameScreen.CurrenLevel ; i++)
            speed = (int)(speed * 0.8);
            return speed;
        
    }
    
    public void update(){
        
        if(dodai == dieukien){//khi do dai ran bang do dai quy dinh thi tang level
            GameScreen.isplaying = false;
            resetGame();
//            tocdo = (int)(tocdo*0.85);
            dieukien = dieukien + 2;//tang do dai bien qua man sau moi man
            GameScreen.CurrenLevel++;//tang level khi qua man
            tocdo = getCurrenSpeed();// tang toc do khi qua man
        }
        
        for( int i =1 ; i < dodai; i++){// khi ran va cham than se thua
            if(xsnack[0]==xsnack[i] && ysnack[0]==ysnack[i] ){              
                GameScreen.isplaying = false;
                GameScreen.islose = true;

                resetGame();      
                String name = JOptionPane.showInputDialog("Nhap Ten Nguoi Choi:");
                Gameran.user.add(new User(name , String.valueOf(GameScreen.CurrenLevel),String.valueOf(GameScreen.Diem)));   
                
                GameScreen.Diem = 0;
                GameScreen.CurrenLevel = 1;
            } 
        }
        
        if(GameScreen.bg[ xsnack[0] ][ ysnack[0] ] == 3 || GameScreen.bg[ xsnack[0] ][ ysnack[0] ] ==  1){//cap nhat do dai khi an 
                GameScreen.isplaying = false;
                GameScreen.islose = true;

                resetGame();
                String name = JOptionPane.showInputDialog("Nhap Ten Nguoi Choi:");
                Gameran.user.add(new User(name , String.valueOf(GameScreen.CurrenLevel),String.valueOf(GameScreen.Diem)));
                
                GameScreen.Diem = 0;
                GameScreen.CurrenLevel = 1;
            }
        
        if(System.currentTimeMillis()-t1>tocdo){
            
            updateAfterchangeVt = true;
            
            if(GameScreen.bg[ xsnack[0] ][ ysnack[0] ] == 2){//cap nhat do dai khi an 
                dodai++;
                GameScreen.bg[ xsnack[0] ][ ysnack[0] ] = 0;
                GameScreen.bg[toadomoi().x][toadomoi().y] = 2; 
                GameScreen.Diem+=10;//cap nhat diem khi an moi
            }
            
//            if(GameScreen.bg[ xsnack[0] ][ ysnack[0] ] == 3){//cap nhat do dai khi an 
////                dodai++;
////                GameScreen.bg[ xsnack[0] ][ ysnack[0] ] = 0;
////                GameScreen.bg[toadomoi().x][toadomoi().y] = 2; 
////                GameScreen.Diem+=10;//cap nhat diem khi an moi
//                GameScreen.isplaying = false;
//                GameScreen.islose = true;
//                resetGame();
//
//            }
             
            for(int i = dodai-1;i>0;i--){//su ly di chuyen cua ran
                xsnack[i]=xsnack[i-1];
                ysnack[i]=ysnack[i-1]; 
            }
            
            if(vector == ConRan.GO_UP)  ysnack[0]--;// cap nhat huong di cua ran
            if(vector == ConRan.GO_DOWN)  ysnack[0]++;
            if(vector == ConRan.GO_LEFT)  xsnack[0]--;
            if(vector == ConRan.GO_RIGHT)  xsnack[0]++;
            
            if(xsnack[0]<0) xsnack[0]=39;//cho ran di xuyen man hinh
            if(xsnack[0]>39) xsnack[0]=0;
            if(ysnack[0]<0) ysnack[0]=39;
            if(ysnack[0]>39) ysnack[0]=0;
            t1 = System.currentTimeMillis();
        }
    }
    
    public void veRan(Graphics g){
        
        for(int i =1 ; i<dodai ; i++){
            g.setColor(Color.blue);
            g.fillRect(xsnack[i]*20+1, ysnack[i]*20+1, 18, 18);
        }
        g.setColor(Color.cyan);
        g.fillRect(xsnack[0]*20+1, ysnack[0]*20+1, 18, 18);
    }
}
