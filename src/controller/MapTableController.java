package controller;

import model.MapTableModel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 10.06.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class MapTableController {

    private MapTableModel mapTableModel;
    private JTable mapTable;

    public MapTableController(JTable mapTable, MapTableModel mapTableModel){
        this.mapTable = mapTable;
        this.mapTableModel = mapTableModel;
    }

}
