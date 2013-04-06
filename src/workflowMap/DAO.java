package workflowMap;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.io.File;
import java.sql.*;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 23.11.12
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
//номер работы, название работы, гл. исполнитель, дата начала, дата окончания,
// основная связь, второстепенные связи
public class DAO {
    public DAO()  {
        try {
            setAllWorks(getConnection());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            File f = new File(System.getProperty("user.dir"));
            //conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver" +
            //        " (*.mdb, *.accdb)}; DBQ=" + f.getPath() + "//work_flow_db.accdb", "", "");
            conn = DriverManager.getConnection("jdbc:hsqldb:" + f.getPath()+"//hsqldb//workflowjdb", "sa", "");
            return conn;
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setAllWorks(Connection conn){
        Statement stmt = null;
        String query = "SELECT " + "id, " + "name, " + "start_time, " + "end_time, " +
                 "main_conn, " + "sec_conn " + "FROM " + "work_table";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

    }
    public CachedRowSet getAllWorks(){
        return crs;
    }

    public Vector<Work> getAllWorksEntities()  {
        Vector works = new Vector<Work>();
        try {
                this.crs.beforeFirst();

            while (this.crs.next()) {
                Work work = new Work();
                work.setWorkId(crs.getString("ID"));
                work.setWorkName(crs.getString("NAME"));
                work.setStartTime(crs.getString("START_TIME"));
                work.setEndTime(crs.getString("END_TIME"));
                work.setMainConn(crs.getString("MAIN_CONN"));
                work.setSecondaryConn(crs.getString("SEC_CONN"));

                works.add(work);

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return works;
    }

    public int getWorkColumnCount(){
        int numOfCols = 0;
        try {
            ResultSetMetaData metadata = this.crs.getMetaData();
            numOfCols = metadata.getColumnCount();


        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return numOfCols;
    }

    public String getColumnName(int columnIndex) {
        try {
            ResultSetMetaData metadata = this.crs.getMetaData();
            return metadata.getColumnLabel(columnIndex + 1);
        } catch (SQLException e) {
            return e.toString();
        }  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CachedRowSet getCurWorksRowSet() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public CachedRowSet crs;
    static public Connection theConn;
}
