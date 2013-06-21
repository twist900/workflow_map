package workflowMap.model;

import workflowMap.dataAccess.DAO;
import workflowMap.dataAccess.Position;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 13.06.13
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class PosTableModel extends AbstractTableModel implements Observer {

    private DAO dataObject;
    private ArrayList<Position> positions;
    private ArrayList<Position> newPositions;
    private ArrayList<Position> deletedPositions;
    private ArrayList<Position> editedPositions;


    private String[] columnNames = {"Отдел", "Квартал", "Количество часов"};

    public PosTableModel(){

    }

    public PosTableModel(DAO dataObject) {
        this.dataObject = dataObject;
        positions = new ArrayList<Position>();
        setPositionArray(dataObject.getAllPositionsArray(dataObject.getCurrentWorkId()));
        dataObject.addObserver(this);
    }

    public void setPositionArray(ArrayList<Position> positions){
        this.positions = positions;
        fireTableDataChanged();
    }

    public void addPositionArray(ArrayList<Position> positions){
        if(this.positions != null){
            this.positions.addAll(positions);
        }
        if(newPositions == null){
            newPositions = new ArrayList<Position>();
        }
        newPositions.addAll(positions);
        fireTableDataChanged();
    }

    public void addPosition(Position position){
        if(positions != null){
            this.positions.add(position);
        }
        if(newPositions == null){
            newPositions = new ArrayList<Position>();
        }
        newPositions.add(position);
        fireTableDataChanged();
    }

    public Position getPositionAt(int rowIndex){
        return this.positions.get(rowIndex);
    }

    public Position removePositionAt(int rowIndex){
        Position position = positions.remove(rowIndex);
        if(deletedPositions == null){
            deletedPositions = new ArrayList<Position>();
        }
        deletedPositions.add(position);
        fireTableDataChanged();
        return position;
    }

    @Override
    public void update(Observable o, Object arg) {
        setPositionArray(this.dataObject.getAllPositionsArray(dataObject.getCurrentWorkId()));
    }


    @Override
    public int getRowCount() {
        return positions.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Position position = positions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return position.getExecutor().getName();
            case 1:
                return position.getQuarter();
            case 2:
                return position.getHours();
            default:
                return null;
        }
    }

    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    public void setCurrentWorkId(int currentWorkId) {
        dataObject.setCurrentWorkId(currentWorkId);
    }
}
