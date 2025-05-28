package com.example.demobuoi1.Buoi1.repository;

import com.example.demobuoi1.Buoi1.entity.Category;
import com.example.demobuoi1.Buoi1.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class CategoryRepo {
    private Session session;

    public CategoryRepo() {
        session = HibernateUtil.getFACTORY().openSession();
    }

    public List<Category> getAll() {
        return session.createQuery("from Category ").list();
    }

    public Category getOne(long id) {
        return session.find(Category.class, id);
    }

    public void add(Category category) {
        try {
            session.getTransaction().begin();
            session.persist(category);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void update(Category category) {
        try {
            session.getTransaction().begin();
            session.merge(category);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void delete(Category category) {
        try {
            session.getTransaction().begin();
            session.delete(category);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
    public List<Category> searchName(String name){
        String hql= "Select c from Category c where c.categoryName=?1";
        Query q = session.createQuery(hql,Category.class);
        q.setParameter(1,name);
        return q.getResultList();
    }

    public List<Category> sapxepTheoTen(){
        String hql = "Select c from Category c order by  c.categoryName";
        Query q = session.createQuery(hql,Category.class);
        return q.getResultList();
    }
    public List<Category> phanTrang(Integer pageNumber, Integer pageNo){
        Query q = session.createQuery("from Category ");
        q.setFirstResult(pageNo * pageNumber);
        q.setFirstResult(pageNumber);
        return q.getResultList();
    }

    public static void main(String[] args) {
        System.out.println(new CategoryRepo().getAll());
    }
}
