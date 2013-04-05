package workflowMap;

import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//Класс workflowMap.WorksTableModel определяет как объект типа JTable
// извлекает и отображает данные из объекта CachedRowSet
public class WorksTableModel implements TableModel {

    public WorksTableModel(DAO dataObject)
            throws SQLException {
        this.worksRowSet = dataObject.getAllWorks();
        this.metadata = this.worksRowSet.getMetaData();
        this.numOfCols = metadata.getColumnCount();

        // Найдем количество строк
        this.worksRowSet.beforeFirst();
        this.numOfRows = 0;
        while (this.worksRowSet.next()) {
            this.numOfRows++;
        }
        this.worksRowSet.beforeFirst();
    }

    @Override
    public int getRowCount() {
        return numOfRows;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getColumnCount() {
        return numOfCols;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return this.metadata.getColumnLabel(columnIndex + 1);
        } catch (SQLException e) {
            return e.toString();
        }  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            this.worksRowSet.absolute(rowIndex + 1);
            Object o = this.worksRowSet.getObject(columnIndex + 1);
            if (o == null)
                return null;
            else
                return o.toString();
        } catch (SQLException e) {
            return e.toString();
        }
    }

    public Work getWorkFromRow(int rowIndex){
        Work work = new Work();
        if(getValueAt(rowIndex, 0) != null ){
            work.setWorkId(getValueAt(rowIndex, 0).toString());
        }
        if(getValueAt(rowIndex, 1) != null ){
            work.setWorkName(getValueAt(rowIndex, 1).toString());
        }
        if(getValueAt(rowIndex, 2) != null ){
            work.setStartTime(getValueAt(rowIndex, 2).toString());
        }
        if(getValueAt(rowIndex, 3) != null ){
            work.setEndTime(getValueAt(rowIndex, 3).toString());
        }
        if(getValueAt(rowIndex, 4) != null ){
            work.setMainConn(getValueAt(rowIndex, 4).toString());
        }
        if(getValueAt(rowIndex, 5) != null ){
            work.setSecondaryConn(getValueAt(rowIndex, 5).toString());
        }
        return work;
    }
    public CachedRowSet getWorksRowSet(){
        return worksRowSet;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }



    CachedRowSet worksRowSet;
    ResultSetMetaData metadata;
    int numOfCols;
    int numOfRows;
}
