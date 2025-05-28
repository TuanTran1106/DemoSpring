package com.example.demobuoi1.Buoi1.repository;

import com.example.demobuoi1.Buoi1.entity.Category;
import com.example.demobuoi1.Buoi1.util.HibernateUtil;
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

    public static void main(String[] args) {
        System.out.println(new CategoryRepo().getAll());
    }
}
