package org.mababio.spring.vltool.utils;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

//import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

/**
 * A collection of database oriented utilities.
 *
 * Isaac
 */
public class DatabaseUtility
{
	final static Logger iLOG = Logger.getLogger(DatabaseUtility.class.getName());
  /**
   * Constructor
   */
  protected DatabaseUtility() {}

  /**
   * Closes a prepared statement logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * @since 0.5
   */
  public static void close(PreparedStatement pStmt)
  {
    if (pStmt == null) return;
    try  {pStmt.close();}
    catch (Throwable e) {iLOG.error("Prepared statement close error", e);}
  }

  /**
   * Closes a statement logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * @since 0.5
   */
  public static void close(Statement stmt)
  {
    if (stmt == null) return;
    try  {stmt.close();}
    catch (Throwable e) {iLOG.error("Statement close error", e);}
  }

  /**
   * Closes a callable statement logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * @since 0.5
   */
  public static void close(CallableStatement cStmt)
  {
    if (cStmt == null) return;
    try  {cStmt.close();}
    catch (Throwable e) {iLOG.error("Callable Statement close error", e);}
  }

  /**
   * Closes a result set logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * @since 0.5
   */
  public static void close(ResultSet rs)
  {
    if (rs == null) return;
    try  {rs.close();}
    catch (Throwable e) {iLOG.error("Resultset close error", e);}
  }

  /*public static void close(EntityManager em)
  {
    if (em == null) return;
    try  {em.close();}
    catch (Throwable e) {iLOG.error("EntityManager close error", e);}
  }*/
  /**
   * Closes a database Connection logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * @since 0.5
   */
    public static void close(Connection conn)
    {
      if (conn == null) return;
      try  {if (! conn.isClosed() ) conn.close();}
      catch (Throwable e) {iLOG.error("Connection close error", e);}
    }

  /**
   * Closes a JDBC-related object logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * <p>This method will generate an IllegalArgumentException if the object passed isn't
   * one of the following classes in java.sql:  PreparedStatement, Statement, ResultSet,
   * CallableStatement, or Connection.
   *
   * @since 0.5
   */
    public static void close(Object dbObj)
    {
      if (dbObj == null) return;
      if (dbObj instanceof PreparedStatement) close( (PreparedStatement) dbObj);
      else if (dbObj instanceof Statement) close( (Statement) dbObj);
      else if (dbObj instanceof ResultSet) close( (ResultSet) dbObj);
      else if (dbObj instanceof CallableStatement) close( (CallableStatement) dbObj);
      else if (dbObj instanceof Connection) close( (Connection) dbObj);
//     / else if (dbObj instanceof EntityManager) close( (Connection) dbObj);
      else throw new IllegalArgumentException("Close attempted on unrecognized Database Object!");
    }

  /**
   * Closes JDBC-related objects in the order provided logging a warning if a
   * SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * <p>This method will generate an IllegalArgumentException if the object passed isn't
   * one of the following classes in java.sql:  PreparedStatement, Statement, ResultSet,
   * CallableStatement, or Connection.
   *
   * <p>Objects will be closed in the order listed.
   *
   * @since 0.5
   */
    public static void close(Object dbObj1, Object dbObj2)
    {
      close (dbObj1);
      close (dbObj2);
    }

  /**
   * Closes JDBC-related objects in the order provided logging a warning if a
   * SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * <p>This method will generate an IllegalArgumentException if the object passed isn't
   * one of the following classes in java.sql:  PreparedStatement, Statement, ResultSet,
   * CallableStatement, or Connection.
   *
   * <p>Objects will be closed in the order listed.
   *
   * @since 0.5
   */
    public static void close(Object dbObj1, Object dbObj2, Object dbObj3)
    {
      close (dbObj1);
      close (dbObj2);
      close (dbObj3);
    }

  /**
   * Closes JDBC-related objects in the order provided logging a warning if a
   * SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * <p>This method will generate an IllegalArgumentException if the object passed isn't
   * one of the following classes in java.sql:  PreparedStatement, Statement, ResultSet,
   * CallableStatement, or Connection.
   *
   * <p>Objects will be closed in the order listed.
   *
   * @since 0.5
   */
    public static void close(Object dbObj1, Object dbObj2, Object dbObj3, Object dbObj4)
    {
      close (dbObj1);
      close (dbObj2);
      close (dbObj3);
      close (dbObj4);
    }

  /**
   * Closes JDBC-related objects in the order provided logging a warning if a
   * SQLException is encountered.
   *
   * <p>Use of this version of close eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * <p>This method will generate an IllegalArgumentException if the object passed isn't
   * one of the following classes in java.sql:  PreparedStatement, Statement, ResultSet,
   * CallableStatement, or Connection.
   *
   * <p>Objects will be closed in the order listed.
   *
   * @since 0.5
   */
    public static void close(Object dbObj1, Object dbObj2, Object dbObj3, Object dbObj4, Object dbObj5)
    {
      close (dbObj1);
      close (dbObj2);
      close (dbObj3);
      close (dbObj4);
      close (dbObj5);
    }

    /**
     * Converts a java.sql.Timestamp to a java.util.Date.
     *
     * <p>Null arguments generate an IllegalArgumentException.
     * @param Timestamp
     * @return Date
     * @since 0.5
     */
    public static java.util.Date convertTimestampToUtilDate(Timestamp ts)
    {
      if (ts == null)  throw new IllegalArgumentException("Null timestamp not allowed.");
      return  new java.util.Date( ts.getTime() );
    }
    
    /**
     * Converts a Calendar to a java.sql.Date.
     *
     * <p>Null arguments generate an IllegalArgumentException.
     * @param Timestamp
     * @return Date
     * @since 0.5
     */
    public static java.sql.Date convertCalendarToSQLDate(Calendar cal)
    {
      if (cal == null)  throw new IllegalArgumentException("Null calendar not allowed.");
      return  new java.sql.Date( cal.getTimeInMillis() );
    }

    /**
     * Converts a java.util.Date to a java.sql.Timestamp.
     *
     * <p>Null arguments generate an IllegalArgumentException.
     * @param Date
     * @return Timestamp
     * @since 0.5
     */
    public static Timestamp convertUtilDateToTimestamp(java.util.Date date)
    {
      if (date == null)  throw new IllegalArgumentException("Null timestamp not allowed.");
      return  new Timestamp( date.getTime() );
    }

    /**
     * Provides a java.sql.Timestamp set to the current ssytem time.
     * @return Timestamp Current
     * @since 0.5
     */
    public static Timestamp getCurrentTimestamp()
    {
      return convertUtilDateToTimestamp( new java.util.Date());
    }

   
  

  /**
   * Provides a JDBC connection given a DriverManager class name, connect string, userid, and
   * password.
   *
   * @since 0.5
   */
  public static Connection getJDBCConnection(String driverName,String connectString,
            String dbUserID, String password)
         throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
  {
    if (driverName == null)     throw new IllegalArgumentException("Null driverName not allowed.");
    if (connectString == null)  throw new IllegalArgumentException("Null connectString not allowed.");

    Class.forName(driverName).newInstance();
    Connection conn;
    
    if (dbUserID == null) conn = DriverManager.getConnection(connectString);
    else conn = DriverManager.getConnection(connectString, dbUserID, password);
    
    return conn;
  }
  
  /**
   * Generic utility that returns a database connection using a given driver.
   * @param driverName
   * @param connectString
   * @return Connection
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws SQLException
   */
  public static Connection getJDBCConnection(String driverName,String connectString)
		 throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
  {
  	return DatabaseUtility.getJDBCConnection(driverName, connectString, null, null);
  }

  /**
   * Rolls back a dataabse transaction logging a warning if a SQLException is encountered.
   *
   * <p>Use of this version of rollback eliminates the need for verbose try/catch logic
   * to handle the SQLException.
   *
   * @since 0.5
   */
  public static void rollback(Connection dbConnection)
  {
    if (dbConnection == null) return;
    try  {dbConnection.rollback();}
    catch (Throwable e) {iLOG.error("Rollback close error", e);}
  }

  
}

