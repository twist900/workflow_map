package workflowMap;

import workflowMap.DAO;

import javax.sql.rowset.CachedRowSet;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 27.11.12
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
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
        return String.class;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
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
        }  //To change body of implemented methods use File | Settings | File Templates.
    }
    public CachedRowSet getWorksRowSet(){
        return worksRowSet;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    CachedRowSet worksRowSet;
    ResultSetMetaData metadata;
    int numOfCols;
    int numOfRows;
}
