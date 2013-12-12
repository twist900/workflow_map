package model;

import dataAccess.DAO;
import dataAccess.Work;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
* Created with IntelliJ IDEA.
* User: 1
* Date: 05.06.13
* Time: 0:24
* To change this template use File | Settings | File Templates.
*/ //Класс workflowMap.WorksTable.WorksTableModel определяет как объект типа JTable
// извлекает и отображает данные из объекта CachedRowSet
public class WorksTableModel extends AbstractTableModel implements Observer {


    private DAO dataObject;
    private ArrayList<Work> works;
    private ArrayList<Work> newWorks;
    private ArrayList<Work> editedWorks;
    private ArrayList<Work> deletedWorks;
    private String[] columnNames = {"Номер", "Название", "Начало", "Завершение"};


    public WorksTableModel(){

    }

    public WorksTableModel(DAO dataObject) {
        this.dataObject = dataObject;
        works = new ArrayList<Work>();
        addWorkArray(dataObject.getAllWorksArray());
        dataObject.addObserver(this);
    }

    public void editWorkAt(int rowIndex, Work work){
        if(this.works != null){
            works.set(rowIndex, work);
        }
        if(this.editedWorks == null){
            editedWorks = new ArrayList<Work>();
        }
        editedWorks.add(work);
        fireTableDataChanged();
    }

    public Work removeWorkAt(int rowIndex){
        Work work = works.remove(rowIndex);
        if(deletedWorks == null){
            deletedWorks = new ArrayList<Work>();
        }
        deletedWorks.add(work);
        fireTableDataChanged();
        return work;
    }


    public void setWorkArray(ArrayList<Work> works){
        this.works = works;
        fireTableDataChanged();
    }

    public void addWorkArray(ArrayList<Work> works){
        if(this.works != null){
            this.works.addAll(works);
        }
        if(newWorks == null){
            newWorks = new ArrayList<Work>();
        }
            newWorks.addAll(works);
        fireTableDataChanged();
    }

    public void addWork(Work work){
        this.works.add(work);
        if(newWorks == null){
            newWorks = new ArrayList<Work>();
        }
        newWorks.add(work);
        fireTableDataChanged();
    }

    public Work getWorkAt(int rowIndex){
        return this.works.get(rowIndex);
    }

    public ArrayList<Work> getNewWorks(){
        return this.newWorks;
    }

    public ArrayList<Work> getEditedWorks(){
        return this.editedWorks;
    }

    public ArrayList<Work> getDeletedWorks(){
        return this.deletedWorks;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(dataObject.workDataChanged()){
            setWorkArray(this.dataObject.getAllWorksArray());
        }
    }


    @Override
    public int getRowCount() {
        return works.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Work work = (Work) works.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return String.valueOf(work.getWorkId());
            case 1:
                return work.getWorkName();
            case 2:
                return work.getStartTime();
            case 3:
                return work.getEndTime();
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

    public int getCurrentWorkId(){
        return dataObject.getCurrentWorkId();
    }
}

