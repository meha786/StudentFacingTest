package org.mehaexample.asdDemo.dao.alignpublic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mehaexample.asdDemo.model.alignpublic.DataCount;
import org.mehaexample.asdDemo.model.alignpublic.MultipleValueAggregatedData;

import java.util.List;

public class MultipleValueAggregatedDataDao {
  public static final String LIST_OF_EMPLOYERS = "ListOfEmployers";
//  public static final String LIST_OF_RACES = "ListOfRaces";
  public static final String LIST_OF_BACHELOR_DEGREES = "ListOfBachelorDegrees";
  public static final String LIST_OF_STUDENTS_STATES = "ListOfStudentsStates";
  public static final String LIST_OF_DEGREES = "ListOfDegrees";

  private SessionFactory factory;
  private Session session;

  public MultipleValueAggregatedDataDao() {
    this.factory = PublicSessionFactory.getFactory();
  }

  public MultipleValueAggregatedDataDao(boolean test) {
    if (test) {
      this.factory = PublicTestSessionFactory.getFactory();
    }
  }

  public boolean saveOrUpdateList(List<MultipleValueAggregatedData> list) {
    Transaction tx = null;
    for (MultipleValueAggregatedData employer : list) {
      try {
        session = factory.openSession();
        tx = session.beginTransaction();
        session.saveOrUpdate(employer);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        throw new HibernateException(e);
      } finally {
        session.close();
      }
    }
    return true;
  }

  public List<String> getTopFiveListOfEmployers() {
    return findTopFiveKeyByTerm(LIST_OF_EMPLOYERS);
  }

  public List<String> getTopFiveListOfBachelorDegrees() {
    return findTopFiveKeyByTerm(LIST_OF_BACHELOR_DEGREES);
  }

  private List<String> findTopFiveKeyByTerm(String analyticTerm) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT analyticKey " +
                      "FROM MultipleValueAggregatedData " +
                      "WHERE analyticTerm = :analyticTerm " +
                      "ORDER BY analyticValue DESC ");
      query.setParameter("analyticTerm", analyticTerm);
      query.setMaxResults(5);
      return (List<String>) query.list();
    } finally {
      session.close();
    }
  }

//  public List<DataCount> getListOfRacesCount() {
//    return findDataCountByTerm(LIST_OF_RACES);
//  }

  public List<DataCount> getListOfStudentsStatesCount() {
    return findDataCountByTerm(LIST_OF_STUDENTS_STATES);
  }

  public List<DataCount> getListOfUndergraduateMajorsCount() {
    return findDataCountByTerm(LIST_OF_BACHELOR_DEGREES);
  }

  public List<DataCount> getListOfHighestDegreesCount() {
    return findDataCountByTerm(LIST_OF_DEGREES);
  }

  private List<DataCount> findDataCountByTerm(String analyticTerm) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "SELECT NEW org.mehaexample.asdDemo.model.alignpublic.DataCount( " +
                      "analyticKey, analyticValue ) " +
                      "FROM MultipleValueAggregatedData " +
                      "WHERE analyticTerm = :analyticTerm " +
                      "ORDER BY analyticValue DESC ");
      query.setParameter("analyticTerm", analyticTerm);
      return (List<DataCount>) query.list();
    } finally {
      session.close();
    }
  }

  public boolean deleteListOfEmployers() {
    return deleteDataByTerm(LIST_OF_EMPLOYERS);
  }

  public boolean deleteListOfBachelorDegrees() {
    return deleteDataByTerm(LIST_OF_BACHELOR_DEGREES);
  }

//  public boolean deleteListOfRacesCounts() {
//    return deleteDataByTerm(LIST_OF_RACES);
//  }

  public boolean deleteListOfStudentsStatesCounts() {
    return deleteDataByTerm(LIST_OF_STUDENTS_STATES);
  }

  public boolean deleteListOfHighestDegreesCounts() {
    return deleteDataByTerm(LIST_OF_DEGREES);
  }

  private List<MultipleValueAggregatedData> findDataByTerm(String analyticTerm) {
    try {
      session = factory.openSession();
      org.hibernate.query.Query query = session.createQuery(
              "FROM MultipleValueAggregatedData " +
                      "WHERE analyticTerm = :analyticTerm " +
                      "ORDER BY analyticValue DESC ");
      query.setParameter("analyticTerm", analyticTerm);
      return (List<MultipleValueAggregatedData>) query.list();
    } finally {
      session.close();
    }
  }

  private boolean deleteDataByTerm(String analyticTerm) {
    List<MultipleValueAggregatedData> listOfData = findDataByTerm(analyticTerm);
    for (MultipleValueAggregatedData data : listOfData) {
      session = factory.openSession();
      Transaction tx = null;
      try {
        tx = session.beginTransaction();
        session.delete(data);
        tx.commit();
      } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        throw new HibernateException(e);
      } finally {
        session.close();
      }
    }

    return true;
  }
}
