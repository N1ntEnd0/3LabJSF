package com.lab3.tests;

import com.lab3.Pip2Entity;
import org.junit.Assert;
import org.junit.Test;

public class TestPip2Entity{
    private Pip2Entity point;

    @Test
    public void checkFirstArea(){
        point = new Pip2Entity(-1.,1.,1.);
        Assert.assertFalse(point.getIsinarea());
    }
    @Test
    public void checkSecondArea(){
        point = new Pip2Entity(1.,1.,4.);
        Assert.assertTrue(point.getIsinarea());
    }
    @Test
    public void checkThirdArea(){
        point = new Pip2Entity(1.,-1.,1.);
        Assert.assertTrue(point.getIsinarea());
    }
    @Test
    public void checkFourthArea(){
        point = new Pip2Entity(-1.,-1.,0.);
        Assert.assertTrue(point.getIsinarea());
    }
}
