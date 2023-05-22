/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameran;

/**
 *
 * @author Acer NITRO 5
 */
public class User {
     private String name;
    private String level;
    private String diem;
    
    public User(String name, String level , String diem){
        this.name= name;
        this.level =  level;
        this.diem = diem;
    }

//public User(String name, String level , int diem){
//        this.name= name;
//        this.level =  level;
//        this.diem = diem;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLevel() {
//        return level;
//    }
//
//    public void setLevel(String level) {
//        this.level = level;
//    }
//
//    public int getDiem() {
//        return diem;
//    }
//
//    public void setDiem(int diem) {
//        this.diem = diem;
//    }
//    
//   
////     public String toString(){
////        return name + "\t" + level + "\t" + diem; 
////    }
//     public String toString(){
//        return name + " " + level + " " + diem; 
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }
    public String toString(){
        return   name + "       " + level + "          " + diem; 
    }
}
