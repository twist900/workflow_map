package view;

import com.mxgraph.swing.mxGraphComponent;
import controller.*;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 03.06.13
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public class MainForm {
    private JPanel panel1;
    private JPanel diagramPanel;
    private JPanel tablePanel;
    private JPanel cardPanel;
    private JPanel btnPanel;
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton saveBtn;
    private JPanel workTablePanel;
    private JPanel execTablePanel;
    public JTabbedPane tabbedPane1;
    public JTable worksTable;
    private JScrollPane scrollPane;
    private JSplitPane hSplitPane;
    private JScrollPane scrollPane1;
    private JTable execTable;
    private JButton savePDFBtn;
    private JButton zoomInBtn;
    private JButton zoomOutBtn;
    private JButton openMapBtn;
    private JButton editBtn;
    static public JFrame frame;

    private MapModel mapModel;
    private AppModel appModel;
    private MapTableModel mapTableModel;
    public SigTableModel sigTableModel;
    public WorksTableModel worksTableModel;

    public MapDialog mapDialog;
    public SigDialog sigDialog;
    public MapView mapView;


    public MainForm(AppModel appModel) {
        frame = new JFrame("РљР°СЂС‚Р° РҐРѕРґР° Р Р°Р±РѕС‚");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        this.appModel = appModel;
        mapModel = new MapModel(appModel.getDataObject());
        mapTableModel = new MapTableModel(appModel.getDataObject());
        sigTableModel = new SigTableModel(appModel.getDataObject());
        worksTableModel = new WorksTableModel(appModel.getDataObject());

        iniDiagram(mapModel);
        iniWorksTable(worksTableModel);
        iniExecsTable(sigTableModel);
        iniButtons();
        iniMapDialog(mapTableModel);
        iniSigDialog(sigTableModel);


        frame.setVisible(true);

    }

    private void iniSigDialog(SigTableModel sigTableModel) {
        sigDialog = new SigDialog(sigTableModel);
        SigDialogController sigDialogController = new SigDialogController(sigDialog, sigTableModel);
    }

    private void iniMapDialog(MapTableModel mapTableModel) {
        mapDialog = new MapDialog(mapTableModel);
        MapDialogController mapDialogController = new MapDialogController(mapDialog, mapTableModel);
    }

    public void iniDiagram(MapModel mapModel) {
        mapView = new MapView(mapModel);
        MapController mapController = new MapController(mapView, mapModel);
        final mxGraphComponent graphComponent = new mxGraphComponent(mapView);
        graphComponent.setGridStyle(mxGraphComponent.GRID_STYLE_DOT);
        graphComponent.getViewport().setBackground(Color.WHITE);
        graphComponent.setGridVisible(true);
        diagramPanel.add(graphComponent, BorderLayout.CENTER);

    }

    public void iniWorksTable(WorksTableModel worksTableModel) {
        WorksTableController worksTableController = new WorksTableController(worksTable, worksTableModel);
        addBtn.addActionListener(worksTableController.new AddBtnListener());
        deleteBtn.addActionListener(worksTableController.new DeleteBtnListener());
        saveBtn.addActionListener(worksTableController.new SaveBtnListener());
        scrollPane.setViewportView(worksTable);
    }

    public void iniExecsTable(SigTableModel sigTableModel) {
        execTable.setModel(sigTableModel);
        scrollPane1.setViewportView(execTable);
    }

    public void iniButtons() {
        try {
            addBtn.setContentAreaFilled(false);
            File fAdd = new File(System.getProperty("user.dir"));
            BufferedImage addIcon = ImageIO.read(new File(fAdd.getPath() + "//images//add-icon.png"));
            addBtn.setIcon(new ImageIcon(addIcon));

            deleteBtn.setContentAreaFilled(false);
            File fDelete = new File(System.getProperty("user.dir"));
            BufferedImage deleteIcon = ImageIO.read(new File(fDelete.getPath() + "//images//Delete-icon.png"));
            deleteBtn.setIcon(new ImageIcon(deleteIcon));

            saveBtn.setContentAreaFilled(false);
            File fSave = new File(System.getProperty("user.dir"));
            BufferedImage saveIcon = ImageIO.read(new File(fSave.getPath() + "//images//check-icon.png"));
            saveBtn.setIcon(new ImageIcon(saveIcon));

            savePDFBtn.setContentAreaFilled(false);
            File fPDF = new File(System.getProperty("user.dir"));
            BufferedImage pdfIcon = ImageIO.read(new File(fPDF.getPath() + "//images//pdf-icon.png"));
            savePDFBtn.setIcon(new ImageIcon(pdfIcon));

            zoomInBtn.setContentAreaFilled(false);
            File fZoomIn = new File(System.getProperty("user.dir"));
            BufferedImage zoomInIcon = ImageIO.read(new File(fPDF.getPath() + "//images//Zoom-In-Icon.png"));
            zoomInBtn.setIcon(new ImageIcon(zoomInIcon));

            zoomOutBtn.setContentAreaFilled(false);
            File fZoomOut = new File(System.getProperty("user.dir"));
            BufferedImage zoomOutIcon = ImageIO.read(new File(fPDF.getPath() + "//images//Zoom-Out-Icon.png"));
            zoomOutBtn.setIcon(new ImageIcon(zoomOutIcon));

            openMapBtn.setContentAreaFilled(false);
            File fOpenMap = new File(System.getProperty("user.dir"));
            BufferedImage openMapIcon = ImageIO.read(new File(fPDF.getPath() + "//images//diagram-icon.png"));
            openMapBtn.setIcon(new ImageIcon(openMapIcon));

            editBtn.setContentAreaFilled(false);
            File fEdit = new File(System.getProperty("user.dir"));
            BufferedImage editIcon = ImageIO.read(new File(fEdit.getPath() + "//images//edit-icon.png"));
            editBtn.setIcon(new ImageIcon(editIcon));


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    public void addDeleteBtnListener(ActionListener listenerForDeleteBtn) {

        deleteBtn.addActionListener(listenerForDeleteBtn);


    }

    public void addAddBtnListener(ActionListener listenerForAddBtn) {

        addBtn.addActionListener(listenerForAddBtn);

    }

    public void addEditBtnListener(ActionListener listenerForEditBtn) {

        editBtn.addActionListener(listenerForEditBtn);

    }

    public void addSaveBtnListener(ActionListener listenerForSaveBtn) {

        saveBtn.addActionListener(listenerForSaveBtn);

    }

    public void addSavePdfBtnListener(ActionListener listenerForSavePDFBtn) {

        savePDFBtn.addActionListener(listenerForSavePDFBtn);

    }

    public void addOpenMapBtnListener(ActionListener listenerForOpenMapBtn) {

        openMapBtn.addActionListener(listenerForOpenMapBtn);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        //iniWorksTable(dataObject);
        // iniDiagram(dataObject);


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        hSplitPane = new JSplitPane();
        hSplitPane.setContinuousLayout(false);
        hSplitPane.setDividerLocation(256);
        hSplitPane.setDividerSize(2);
        hSplitPane.setOrientation(0);
        panel1.add(hSplitPane, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(0, 0));
        hSplitPane.setRightComponent(panel2);
        tablePanel = new JPanel();
        tablePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(tablePanel, BorderLayout.CENTER);
        tabbedPane1 = new JTabbedPane();
        tablePanel.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 200), null, 0, false));
        workTablePanel = new JPanel();
        workTablePanel.setLayout(new BorderLayout(0, 0));
        tabbedPane1.addTab("Р Р°Р±РѕС‚С‹", workTablePanel);
        scrollPane = new JScrollPane();
        workTablePanel.add(scrollPane, BorderLayout.CENTER);
        worksTable = new JTable();
        worksTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(worksTable);
        execTablePanel = new JPanel();
        execTablePanel.setLayout(new BorderLayout(0, 0));
        tabbedPane1.addTab("Р�СЃРїРѕР»РЅРёС‚РµР»Рё", execTablePanel);
        scrollPane1 = new JScrollPane();
        execTablePanel.add(scrollPane1, BorderLayout.CENTER);
        execTable = new JTable();
        execTable.setFillsViewportHeight(true);
        scrollPane1.setViewportView(execTable);
        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
        tablePanel.add(btnPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addBtn = new JButton();
        addBtn.setText("Р”РѕР±Р°РІРёС‚СЊ");
        btnPanel.add(addBtn);
        editBtn = new JButton();
        editBtn.setText("Р РµРґР°РєС‚РёСЂРѕРІР°С‚СЊ");
        btnPanel.add(editBtn);
        deleteBtn = new JButton();
        deleteBtn.setText("РЈРґР°Р»РёС‚СЊ");
        btnPanel.add(deleteBtn);
        saveBtn = new JButton();
        saveBtn.setHorizontalTextPosition(11);
        saveBtn.setText("РЎРѕС…СЂР°РЅРёС‚СЊ");
        btnPanel.add(saveBtn);
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        btnPanel.add(toolBar$Separator1);
        diagramPanel = new JPanel();
        diagramPanel.setLayout(new BorderLayout(0, 0));
        hSplitPane.setLeftComponent(diagramPanel);
        diagramPanel.setBorder(BorderFactory.createTitledBorder(""));
        final JToolBar toolBar1 = new JToolBar();
        diagramPanel.add(toolBar1, BorderLayout.WEST);
        final JToolBar toolBar2 = new JToolBar();
        panel1.add(toolBar2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        openMapBtn = new JButton();
        openMapBtn.setText("");
        toolBar2.add(openMapBtn);
        final JToolBar.Separator toolBar$Separator2 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator2);
        savePDFBtn = new JButton();
        savePDFBtn.setText("");
        toolBar2.add(savePDFBtn);
        final JToolBar.Separator toolBar$Separator3 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator3);
        zoomInBtn = new JButton();
        zoomInBtn.setText("");
        toolBar2.add(zoomInBtn);
        final JToolBar.Separator toolBar$Separator4 = new JToolBar.Separator();
        toolBar2.add(toolBar$Separator4);
        zoomOutBtn = new JButton();
        zoomOutBtn.setText("");
        toolBar2.add(zoomOutBtn);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
