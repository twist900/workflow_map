package workflowMap.model;

import workflowMap.dataAccess.DAO;
import workflowMap.dataAccess.Map;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 09.06.13
 * Time: 3:51
 * To change this template use File | Settings | File Templates.
 */
public class MapTableModel extends AbstractTableModel implements Observer {

    private DAO dataObject;
    private ArrayList<Map> maps;
    private ArrayList<Map> tempMaps;
    private String[] columnNames = {"Номер", "Название", "Начало"};

    public MapTableModel(){

    }

    public MapTableModel(DAO dataObject) {
        this.dataObject = dataObject;
        maps = new ArrayList<Map>();
        setMapArray(dataObject.getAllMapsArray());
        dataObject.addObserver(this);
    }

    public void setMapArray(ArrayList<Map> maps){
        this.maps = maps;
        fireTableDataChanged();
    }

    public void addMapArray(ArrayList<Map> maps){
        if(this.maps != null){
            this.maps.addAll(maps);
        }
        if(tempMaps == null){
            tempMaps = new ArrayList<Map>();
        }
        tempMaps.addAll(maps);
        fireTableDataChanged();
    }

    public void addMap(Map map){
        this.maps.add(map);
        if(tempMaps == null){
            tempMaps = new ArrayList<Map>();
        }
        tempMaps.add(map);
        fireTableDataChanged();
    }

    public Map getMapAt(int rowIndex){
        return this.maps.get(rowIndex);
    }

    public Map removeWorkAt(int rowIndex){
        Map map = maps.remove(rowIndex);
        fireTableDataChanged();
        return map;
    }

    @Override
    public void update(Observable o, Object arg) {
        setMapArray(this.dataObject.getAllMapsArray());
    }


    @Override
    public int getRowCount() {
        return maps.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Map map = maps.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return map.getNumber();
            case 1:
                return map.getName();
            case 2:
                return map.getStartDate();
            default:
                return null;
        }
    }

    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    public void setCurrentMapId(String currentMapId) {
        dataObject.setCurrentMapId(currentMapId);
    }
}
