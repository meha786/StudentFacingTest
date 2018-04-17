package org.mehaexample.asdDemo.dao.alignprivate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mehaexample.asdDemo.model.alignprivate.ExtraExperiences;
import org.mehaexample.asdDemo.model.alignprivate.Privacies;

import java.util.ArrayList;
import java.util.List;

public class ExtraExperiencesDao {
  private SessionFactory factory;
  private Session session;
  private PrivaciesDao privaciesDao;

  /**
   * Default constructor.
   * it will check the Hibernate.cfg.xml file and load it
   * next it goes to all table files in the hibernate file and loads them.
   */
  public ExtraExperiencesDao() {
    privaciesDao = new PrivaciesDao();
    this.factory = StudentSessionFactory.getFactory();
  }

  public ExtraExperiencesDao(boolean test) {
    if (test) {
      privaciesDao = new PrivaciesDao(true);
      this.factory = StudentTestSessionFactory.getFactory();
    }
  }

  /**
   * Find an Extra Experience by the Experience Id.
   * This method searches the extra experience from the private database.
   *
   * @param extraExperienceId extra experience Id in private database.
   * @return Extra Experience if found.
   */
  public ExtraExperiences getExtraExperienceById(int extraExperienceId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "FROM ExtraExperiences WHERE extraExperienceId = :extraExperienceId");
      query.setParameter("extraExperienceId", extraExperienceId);
      List<ExtraExperiences> listOfExtraExperience = query.list();
      if (listOfExtraExperience.isEmpty())
        return null;
      return listOfExtraExperience.get(0);
    } finally {
      session.close();
    }
  }

  /**
   * Find extra experience records of a student in private DB.
   *
   * @param neuId the neu Id of a student; not null.
   * @return List of Extra Experiences.
   */
  public List<ExtraExperiences> getExtraExperiencesByNeuId(String neuId) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "FROM ExtraExperiences WHERE neuId = :neuId");
      query.setParameter("neuId", neuId);
      return (List<ExtraExperiences>) query.list();
    } finally {
      session.close();
    }
  }

  public List<ExtraExperiences> getExtraExperiencesWithPrivacy(String neuId) {
    Privacies privacy = privaciesDao.getPrivacyByNeuId(neuId);
    if (!privacy.isExtraExperience()) {
      return new ArrayList<>();
    } else {
      return getExtraExperiencesByNeuId(neuId);
    }
  }

  /**
   * Create a extra experience in the private database.
   * This function requires the StudentsPublic object and the Companies
   * object inside the extra experience object to be not null.
   *
   * @param extraExperience the extra experience object to be created; not null.
   * @return newly created ExtraExperience if success. Otherwise, return null;
   */
  public ExtraExperiences createExtraExperience(ExtraExperiences extraExperience) {
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.save(extraExperience);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return extraExperience;
  }

  /**
   * Delete a extra experience in the private database.
   *
   * @param extraExperienceId the extra experience Id to be deleted.
   * @return true if extra experience is deleted, false otherwise.
   */
  public boolean deleteExtraExperienceById(int extraExperienceId) {
    ExtraExperiences extraExperiences = getExtraExperienceById(extraExperienceId);
    if (extraExperiences == null) {
      throw new HibernateException("Extra Experience does not exist.");
    }
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.delete(extraExperiences);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }

  public boolean deleteExtraExperienceByNeuId(String neuId) {
    Transaction tx = null;

    try {
      session = factory.openSession();
      tx = session.beginTransaction();
      org.hibernate.query.Query query = session.createQuery("DELETE FROM ExtraExperiences " +
              "WHERE neuId = :neuId ");
      query.setParameter("neuId", neuId);
      query.executeUpdate();
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }

  /**
   * Update an extra experience in the private DB.
   *
   * @param extraExperience extra experience object; not null.
   * @return true if the extra experience is updated, false otherwise.
   */
  public boolean updateExtraExperience(ExtraExperiences extraExperience) {
    if (getExtraExperienceById(extraExperience.getExtraExperienceId()) == null) {
      throw new HibernateException("Extra Experience does not exist.");
    }
    session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.saveOrUpdate(extraExperience);
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) tx.rollback();
      throw new HibernateException(e);
    } finally {
      session.close();
    }

    return true;
  }
}
