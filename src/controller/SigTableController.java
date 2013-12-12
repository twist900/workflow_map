package controller;

import model.SigTableModel;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 10.06.13
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class SigTableController {
    private SigTableModel sigTableModel;
    private JTable sigTable;

    public SigTableController(JTable sigTable, SigTableModel sigTableModel){
        this.sigTable = sigTable;
        this.sigTableModel = sigTableModel;
    }
}
