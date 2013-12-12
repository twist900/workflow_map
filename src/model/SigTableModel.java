package model;

import dataAccess.DAO;
import dataAccess.Signature;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 04.06.13
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class SigTableModel extends AbstractTableModel implements Observer{

        private DAO dataObject;
        private ArrayList<Signature> sigs;
        private ArrayList<Signature> tempSigs;
        private String[] columnNames = {"РўР°Р±РµР»СЊРЅС‹Р№ РЅРѕРјРµСЂ", "Р¤Р°РјРёР»РёСЏ", "Р�РјСЏ", "РћС‚С‡РµСЃС‚РІРѕ", "Р”РѕР»Р¶РЅРѕСЃС‚СЊ"};


        public SigTableModel(){

        }

        public SigTableModel(DAO dataObject) {
            this.dataObject = dataObject;
            sigs = new ArrayList<Signature>();
            setSigArray(dataObject.getAllSigsArray());
            dataObject.addObserver(this);
        }


        public void setSigArray(ArrayList<Signature> sigs){
            this.sigs = sigs;
            fireTableDataChanged();
        }

        public void addSigArray(ArrayList<Signature> sigs){
            if(this.sigs != null){
                this.sigs.addAll(sigs);
            }
            if(tempSigs == null){
                tempSigs = new ArrayList<Signature>();
            }
            tempSigs.addAll(sigs);
            fireTableDataChanged();
        }

        public void addSig(Signature sig){
            this.sigs.add(sig);
            if(tempSigs == null){
                tempSigs = new ArrayList<Signature>();
            }
            tempSigs.add(sig);
            fireTableDataChanged();
        }

        public Signature getWorkAt(int rowIndex){
            return this.sigs.get(rowIndex);
        }

        public Signature removeWorkAt(int rowIndex){
            Signature sig = sigs.remove(rowIndex);
            fireTableDataChanged();
            return sig;
        }

        @Override
        public void update(Observable o, Object arg) {
            setSigArray(this.dataObject.getAllSigsArray());
        }


        @Override
        public int getRowCount() {
            return sigs.size();

        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Signature sig = sigs.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return String.valueOf(sig.getId());
                case 1:
                    return sig.getSurName();
                case 2:
                    return sig.getName();
                case 3:
                    return sig.getMiddleName();
                case 4:
                    return sig.getPosition();
                default:
                    return null;
            }
        }

        public String getColumnName(int columnIndex){
            return columnNames[columnIndex];
        }
    }
