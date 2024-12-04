package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import java.util.List;

public class SessionController {
    private static final SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();
            Metadata metadata = new MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (Throwable e) {
            System.err.println("SessionFactory creation failed.");
            throw new ExceptionInInitializerError(e);
        }
    }

    void addTestData() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Tower tower1 = new Tower("tower1", 100);
            Tower tower2 = new Tower("tower2", 40);
            session.save(tower1);
            session.save(tower2);

            Mage mage1 = new Mage("mage1", 10, tower1);
            Mage mage2 = new Mage("mage2", 8, tower1);
            Mage mage3 = new Mage("mage3", 7, tower2);
            session.save(mage1);
            session.save(mage2);
            session.save(mage3);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    void addMage(Mage mage1) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(mage1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    void addTower(Tower tower) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tower);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    Tower getTowerByName(String towerName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tower> query = session.createQuery("FROM Tower WHERE name = :name", Tower.class);
            query.setParameter("name", towerName);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void deleteMageByName(String mageName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Mage mage = session.get(Mage.class, mageName);
            if (mage != null) {
                session.delete(mage);
                transaction.commit();
                System.out.println("Mage " + mageName + " deleted.");
            } else {
                System.out.println("Mage " + mageName + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    void deleteTowerByName(String towerName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Tower tower = session.get(Tower.class, towerName);
            if (tower != null) {
                List<Mage> mages = tower.getMages();
                for (Mage mage : mages) {
                    session.delete(mage);
                }
                session.delete(tower);
                transaction.commit();
                System.out.println("Tower " + towerName + " deleted successfully.");
            } else {
                System.out.println("Tower " + towerName + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    void displayAllMages() {
        try (Session session = sessionFactory.openSession()) {
            Query<Mage> query = session.createQuery("FROM Mage", Mage.class);
            List<Mage> mages = query.list();
            System.out.println("All mages:");
            for (Mage mage : mages) {
                System.out.println(mage.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void displayMagesWithLevelGreaterThan(int level) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mage> query = session.createQuery("FROM Mage WHERE level > :level", Mage.class);
            query.setParameter("level", level);
            List<Mage> mages = query.list();
            System.out.println("Mages with level greater than " + level + ":");
            for (Mage mage : mages) {
                System.out.println(mage.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void displayTowersWithHeightLowerThan(int height) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tower> query = session.createQuery("FROM Tower WHERE height < :height", Tower.class);
            query.setParameter("height", height);
            List<Tower> towers = query.list();
            System.out.println("Towers with height lower than " + height + ":");
            for (Tower tower : towers) {
                System.out.println(tower.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void displayMagesWithLevelGreaterThanFromTower(String towerName, int level) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mage> query = session.createQuery("SELECT m FROM Mage m WHERE m.level > :level AND m.tower.name = :towerName", Mage.class);
            query.setParameter("level", level);
            query.setParameter("towerName", towerName);
            List<Mage> mages = query.list();
            System.out.println("Mages with level greater than " + level + " from tower " + towerName + ":");
            for (Mage mage : mages) {
                System.out.println(mage.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}
