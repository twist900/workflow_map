package workflowMap.view;

import workflowMap.model.SigTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class SigDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField sigIdField;
    private JTextField middleNameField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField positionField;

    private SigTableModel sigTableModel;

    public SigDialog(SigTableModel sigTableModel) {

        this.sigTableModel = sigTableModel;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();

        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
        setSize((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
        setVisible(false);
    }

    public void addOkBtnListener(ActionListener okBtnListener){
        this.buttonOK.addActionListener(okBtnListener);
    }

    public void addCancelBtnListener(ActionListener cancelBtnListener){
        this.buttonCancel.addActionListener(cancelBtnListener);
    }

    public void addWindowCloseListener(WindowAdapter windowCloseListener){
        this.addWindowListener(windowCloseListener);
    }

}
