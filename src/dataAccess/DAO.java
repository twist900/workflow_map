package dataAccess;



import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 23.11.12
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */

public class DAO extends Observable {

    private String currentMapId;
    private int currentWorkId;
    static private Connection conn;
    private boolean workDataChanged = false;

    public DAO()  {
        try {
            getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        conn = null;
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            File f = new File(System.getProperty("user.dir"));
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

    public void setCurrentMapId(String currentMapId){
        this.currentMapId = currentMapId;
        setChanged();
        notifyObservers();
    }

    public String getCurrentMapId(){
        return this.currentMapId;
    }

    public void setCurrentWorkId(int workId){
        this.currentWorkId = workId;
        setChanged();
        notifyObservers();
    }

    public int getCurrentWorkId(){
        return this.currentWorkId;
    }
    public CachedRowSet getAllWorks(){
        Statement stmt = null;
        CachedRowSet workCRS = null;
        if(currentMapId != null){
            String query = "SELECT " + "id, " + "name, " + "start_time, " + "end_time, "
                    + "khr_number " + "FROM " + "work " + "WHERE " + "khr_number=" + qouteIt(currentMapId.trim());
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                workCRS = new CachedRowSetImpl();
                workCRS.populate(rs);
            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return workCRS;
        }
        else return null;
    }

    public ArrayList<Work> getAllWorksArray()  {
        ArrayList<Work> works = new ArrayList<Work>();
        CachedRowSet workCRS = getAllWorks();
        if( workCRS != null ){
            try {
                workCRS.beforeFirst();

                while (workCRS.next()) {
                    Work work = new Work();
                    work.setWorkId(workCRS.getString("ID"));
                    work.setWorkName(workCRS.getString("NAME"));
                    work.setStartTime(workCRS.getString("START_TIME"));
                    work.setEndTime(workCRS.getString("END_TIME"));
                    work.setWorkRelations(this.getAllWorkRelationsArray(work.getWorkId()));
                    works.add(work);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return works;
    }

    public void insertWorks(ArrayList<Work> works){
        String insertTableSQL = "INSERT INTO work"
                + "(ID, NAME, START_TIME, END_TIME) VALUES"
                + "(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        for(Work work : works){
            try {
                preparedStatement = conn.prepareStatement(insertTableSQL);
              // preparedStatement.setString(1, work.getWorkId());
                preparedStatement.setString(2, work.getWorkName());
                preparedStatement.setString(3, work.getStartTime());
                preparedStatement.setString(4, work.getEndTime());
                preparedStatement.executeUpdate();
                insertWorkRelations(work.getWorkRelations());

            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public CachedRowSet getAllWorkRelations(String workId){
        Statement stmt = null;
        CachedRowSet workConnCRS = null;
        String query = "SELECT " + "id, " + "work_id_prev, " + "work_id_next " + "FROM " + "work_relation " +
                "WHERE " + "work_id_prev=" + workId.trim();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            workConnCRS = new CachedRowSetImpl();
            workConnCRS.populate(rs);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return workConnCRS;
    }


    public ArrayList<Relation> getAllWorkRelationsArray(String workId){
        ArrayList<Relation> workRelations = new ArrayList<Relation>();
        CachedRowSet workRelationCRS = getAllWorkRelations(workId);
        try {
            workRelationCRS.beforeFirst();

            while (workRelationCRS.next()) {
                Relation workRelation = new Relation();
                workRelation.setId(workRelationCRS.getInt("id"));
                workRelation.setWorkIdNext(workRelationCRS.getInt("work_id_next"));
                workRelation.setWorkIdPrev(workRelationCRS.getInt("work_id_prev"));
                workRelations.add(workRelation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workRelations;
    }

    public void insertWorkRelations(ArrayList<Relation> workRelations){
        String insertTableSQL = "INSERT INTO work_relation"
                + "(ID, WORK_ID_PREV, WORK_ID_NEXT) VALUES"
                + "(?,?,?)";
        PreparedStatement preparedStatement = null;
        for(Relation workRelation : workRelations){
            try {
                preparedStatement = conn.prepareStatement(insertTableSQL);
                preparedStatement.setInt(1, workRelation.getId());
                preparedStatement.setInt(2, workRelation.getWorkIdPrev());
                preparedStatement.setInt(3, workRelation.getWorkIdNext());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }



    public CachedRowSet getAllSigs(){
        Statement stmt = null;
        CachedRowSet signaturesCRS = null;
        if(currentMapId != null){
            String query = "SELECT " + "id, " + "surname, " + "name, " + "middlename, " + "position " +
                    "FROM " + "signature " + "JOIN khr_sig ON signature.id=khr_sig.sig_id AND khr_sig.khr_number="
                    + qouteIt(currentMapId.trim());
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                signaturesCRS = new CachedRowSetImpl();
                signaturesCRS.populate(rs);
            } catch (SQLException e ) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            return signaturesCRS;
        }
        else return null;
    }

    public ArrayList<Signature> getAllSigsArray(){
        ArrayList<Signature> sigs = new ArrayList<Signature>();
        CachedRowSet sigCRS = getAllSigs();
        if( sigCRS != null ){
            try {
                sigCRS.beforeFirst();

                while (sigCRS.next()) {
                    Signature sig = new Signature();
                    sig.setId(sigCRS.getInt("ID"));
                    sig.setSurName(sigCRS.getString("SURNAME"));
                    sig.setName(sigCRS.getString("NAME"));
                    sig.setMiddleName(sigCRS.getString("MIDDLENAME"));
                    sig.setPosition(sigCRS.getString("POSITION"));
                    sigs.add(sig);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sigs;
    }

    public CachedRowSet getAllMaps(){
        Statement stmt = null;
        CachedRowSet mapsCRS = null;
        String query = "SELECT " + "* " + "FROM " + "khr";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            mapsCRS = new CachedRowSetImpl();
            mapsCRS.populate(rs);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mapsCRS;
    }

    public ArrayList<Map> getAllMapsArray(){
        ArrayList<Map> maps = new ArrayList<Map>();
        CachedRowSet mapCRS = getAllMaps();
        if( mapCRS != null ){
            try {
                mapCRS.beforeFirst();

                while (mapCRS.next()) {
                    Map map = new Map();
                    map.setNumber(mapCRS.getString("NUMBER"));
                    map.setName(mapCRS.getString("NAME"));
                    map.setStartDate(mapCRS.getString("START_DATE"));
                    maps.add(map);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return maps;
    }




    public CachedRowSet getAllPositions(int workId){
        Statement stmt = null;
        CachedRowSet positions = null;
        String query = "SELECT " + "* " + "FROM " + "position " + "WHERE "
                + "work_id=" + workId;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            positions = new CachedRowSetImpl();
            positions.populate(rs);
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return positions;
    }

    public ArrayList<Position> getAllPositionsArray(int workId){
        ArrayList<Position> positions = new ArrayList<Position>();
        CachedRowSet positionCRS = getAllPositions(workId);
        if( positionCRS != null ){
            try {
                positionCRS.beforeFirst();

                while (positionCRS.next()) {
                    Position position = new Position();
                    position.setId(positionCRS.getInt("ID"));
                    position.setExecId(positionCRS.getInt("EXECUTOR_ID"));
                    position.setWorkId(positionCRS.getInt("WORK_ID"));
                    position.setQuarter(positionCRS.getString("QUARTER"));
                    position.setHours(positionCRS.getString("HOURS"));
                    position.setExecutor(getExecutor(position.getExecId()));
                    positions.add(position);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return positions;
    }

    public Executor getExecutor(int execID){
        PreparedStatement statement = null;
        Executor executor = null;
        String query = "SELECT * FROM executor WHERE ID=?";
        try{
            statement = conn.prepareStatement(query);
            statement.setInt(1, execID);
            ResultSet rs = statement.executeQuery();
            if(rs != null && rs.next()) {
               executor = new Executor();
               executor.setId(rs.getInt("ID"));
               executor.setName(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if( statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return executor;
    }






    public String getMapName(String mapNumber){
        Statement stmt = null;
        String mapName = null;
        String query = "SELECT " + "name " + "FROM " + "khr " + "WHERE "
                + "number=" + qouteIt(mapNumber.trim());
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                mapName = rs.getString("NAME");
            }
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
        return (mapName != null) ? mapName : "";
    }







    public String qouteIt(String qouteMe){
        return "\'" + qouteMe + "\'";
    }

    public CachedRowSet getCurWorksRowSet() {
        return null;
    }

    public boolean workDataChanged() {
        return workDataChanged;  //To change body of created methods use File | Settings | File Templates.
    }
}
