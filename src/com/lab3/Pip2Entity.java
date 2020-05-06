package com.lab3;

import javax.persistence.*;
import java.util.Objects;
1
@Entity
@Table
public class Pip2Entity {
    /*(name = "role", schema = "s250651")*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long Id;
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private double r;
    @Column
    private boolean isinarea;

    public Pip2Entity(Double x, Double y, Double r){
        this.x = x;
        this.y = y;
        this.r = r;
        setIsArea();
    }

    public Pip2Entity(Double r, Double x, Double y, Boolean isInArea) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.isinarea = isInArea;
    }

    public Pip2Entity() {
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    

    public boolean getIsinarea() {
        return isinarea;
    }

    public void setIsinarea(boolean isinarea) {
        this.isinarea = isinarea;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Pip2Entity that = (Pip2Entity) o;
//        return id == that.id &&
//                Double.compare(that.x, x) == 0 &&
//                Double.compare(that.y, y) == 0 &&
//                Double.compare(that.r, r) == 0 &&
//                isinarea == that.isinarea;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, x, y, r, isinarea);
//    }

    private void setIsArea() {
        if(x < 0 && y > 0){
            isinarea = false;
            return;
        }
        if(x>=0 && y>=0){
            isinarea = y <= (x/2)*-1 + r/2;
            return;
        }
        if(x>0 && y<0){
            isinarea = Math.pow(x, 2) + Math.pow(y,2) <= Math.pow(r, 2);
            return;
        }
        if(x<=0 && y<=0){
            isinarea = -x <= r && -y <= r;
            return;
        }
        isinarea = false;
    }
}
