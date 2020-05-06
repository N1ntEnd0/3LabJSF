package com.lab3.tests;

import com.lab3.HibernateSessionFactory;
import com.lab3.Pip2Entity;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Collections;
import java.util.List;

public class TestUserBean {
    private static Pip2Entity lab1;
    private static Pip2Entity lab2;
    private static Pip2Entity lab3;

    @BeforeClass
    public static void setUp(){
        lab1 = new Pip2Entity(0.1,2.,3.);
        lab2 = new Pip2Entity(5.,4.,9.);
        lab3 = new Pip2Entity(1.000001,6.,3.);
    }

    @Test
    public void addToList(){
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(lab1);
            session.save(lab2);
            session.save(lab3);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка парсинга");
        }
        Assert.fail("It's only for com.lab3.tests )");
    }

    @Test
    public void getList(){
        List<Pip2Entity> list = null;
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
            session.beginTransaction();
            list =  (List<Pip2Entity>)session.createQuery("From Pip2Entity ").list();
            Collections.reverse(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

}
