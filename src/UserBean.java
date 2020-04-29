import abc.HibernateSessionFactory;
import abc.Pip2Entity;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserBean {
    private String rawX;
    private String rawY;
    private String rawR = "4";
    private List<Pip2Entity> list = new ArrayList<>();

    public String getRawX() {
        return rawX;
    }

    public void setRawX(String rawX) {
        this.rawX = rawX;
    }

    public String getRawY() {
        return rawY;
    }

    public void setRawY(String rawY) {
        this.rawY = rawY;
    }

    public String getRawR() {
        return rawR;
    }

    public void setRawR(String rawR) {
        this.rawR = rawR;
    }

    public void addToList() {
        System.out.println(rawX + ' ' + rawY + ' ' + rawR);
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            Pip2Entity point = new Pip2Entity(Double.parseDouble(rawX.replace(',', '.').trim()),
                    Double.parseDouble(rawY.replace(',', '.').trim()),
                    Double.parseDouble(rawR.replace(',', '.').trim()));
            session.save(point);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка парсинга");
        }
    }

    public List<Pip2Entity> getList() {
        List<Pip2Entity> list = null;
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
            session.beginTransaction();
            list =  (List<Pip2Entity>)session.createQuery("From Pip2Entity ").list();
            Collections.reverse(list);
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
