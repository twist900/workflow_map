package model;

import dataAccess.DAO;
import dataAccess.Relation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 14.06.13
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
public class RelationTableModel extends AbstractTableModel implements Observer{

    private DAO dataObject;
    private ArrayList<Relation> relations;
    private ArrayList<Relation> newRelations;
    private ArrayList<Relation> deletedRelations;
    private ArrayList<Relation> editedRelations;

    private String[] columnNames = {"ID", "Номер работы", "Номер связанной работы"};

    public RelationTableModel(){

    }

    public RelationTableModel(DAO dataObject) {
        this.dataObject = dataObject;
        relations = new ArrayList<Relation>();
        setRelationArray(dataObject.getAllWorkRelationsArray(String.valueOf(dataObject.getCurrentWorkId())));
        dataObject.addObserver(this);
    }

    public void setRelationArray(ArrayList<Relation> relations){
        this.relations = relations;
        fireTableDataChanged();
    }

    public void addRelationArray(ArrayList<Relation> relations){
        if(this.relations != null){
            this.relations.addAll(relations);
        }
        if(newRelations == null){
            newRelations = new ArrayList<Relation>();
        }
        newRelations.addAll(relations);
        fireTableDataChanged();
    }

    public void addRelation(Relation relation){
        if(relations != null){
            this.relations.add(relation);
        }
        if(newRelations == null){
            newRelations = new ArrayList<Relation>();
        }
        newRelations.add(relation);
        fireTableDataChanged();
    }

    public Relation getRelationAt(int rowIndex){
        return this.relations.get(rowIndex);
    }

    public ArrayList<Relation> getRelations(){
        return this.relations;
    }

    public Relation removeRelationAt(int rowIndex){
        Relation relation = relations.remove(rowIndex);
        if(deletedRelations == null){
            deletedRelations = new ArrayList<Relation>();
        }
        deletedRelations.add(relation);
        fireTableDataChanged();
        return relation;
    }

    @Override
    public void update(Observable o, Object arg) {
        setRelationArray(this.dataObject.getAllWorkRelationsArray(String.valueOf(dataObject.getCurrentWorkId())));
    }


    @Override
    public int getRowCount() {
        return relations.size();

    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Relation relation = relations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return relation.getId();
            case 1:
                return relation.getWorkIdPrev();
            case 2:
                return relation.getWorkIdNext();
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
