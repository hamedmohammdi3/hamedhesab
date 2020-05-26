package com.gmail.hamed.baseClass;

import com.gmail.hamed.dbCon.PDate;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class kala {
    private int mid=0;
   private int code;
    private String name="o";
    private String color="o";
    private int size=0;
    private int priceIn=0;
    private String dateIn;

    public int getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(int priceIn) {
        this.priceIn = priceIn;
    }

    public int getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(int priceOut) {
        this.priceOut = priceOut;
    }

    private int priceOut;

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    private String dateOut;
    private String tolydi="o";
    private String noe;
    private String time;
    public int getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = mid;
    }
    public String getTime() {
        return time;
    }
 public void setDateIn(String date){this.dateIn=date;}
    public void setTime(String time) {
        this.time = time;
    }

    public String getNoe() {
        return noe;
    }

    public void setNoe(String noe) {
        this.noe = noe;
    }


    public String getDateIn() {
        return dateIn;
    }

    public String getTolydi() {
        return tolydi;
    }

    public void setTolydi(String tolydi) {
        this.tolydi = tolydi;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


  

    @Override
    public int hashCode() {
        return Objects.hash(code, name, color, size, dateIn, tolydi, noe, time);
    }

    @Override
    public String toString() {
        return "Manto{" +
                "mid=" + code +
                "Noe="+noe+
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                '}';
    }
}
