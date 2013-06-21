package workflowMap.view;

import com.mxgraph.swing.mxGraphComponent;
import workflowMap.controller.*;
import workflowMap.model.*;

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
    private JButton редактироватьButton;
    static public JFrame frame;

    private MapModel mapModel;
    private AppModel appModel;
    private MapTableModel mapTableModel;
    public SigTableModel sigTableModel;
    public WorksTableModel worksTableModel;

    public MapDialog mapDialog;
    public SigDialog sigDialog;
    public WorkDialog workDialog;
    public MapView mapView;


    public MainForm(AppModel appModel){
        frame = new JFrame("Карта Хода Работ");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0,0,screenSize.width, screenSize.height);
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
        iniWorkDialog(worksTableModel);




        frame.setVisible(true);

    }

    private void iniWorkDialog(WorksTableModel worksTableModel) {
        workDialog = new WorkDialog(worksTableModel, appModel.getDataObject());
        WorkDialogController workDialogController = new WorkDialogController(workDialog, worksTableModel);
    }

    private void iniSigDialog(SigTableModel sigTableModel) {
        sigDialog = new SigDialog(sigTableModel);
        SigDialogController sigDialogController = new SigDialogController(sigDialog, sigTableModel);
    }

    private void iniMapDialog(MapTableModel mapTableModel){
        mapDialog = new MapDialog(mapTableModel);
        MapDialogController mapDialogController = new MapDialogController(mapDialog, mapTableModel);
    }

    public void iniDiagram(MapModel mapModel){
        mapView = new MapView(mapModel);
        MapController mapController = new MapController(mapView, mapModel);
        final mxGraphComponent graphComponent = new mxGraphComponent(mapView);
        graphComponent.setGridStyle(mxGraphComponent.GRID_STYLE_DOT);
        graphComponent.getViewport().setBackground(Color.WHITE);
        graphComponent.setGridVisible(true);
        diagramPanel.add(graphComponent, BorderLayout.CENTER);

    }

    public void iniWorksTable(WorksTableModel worksTableModel){
        WorksTableController worksTableController = new WorksTableController(worksTable, worksTableModel);
        addBtn.addActionListener(worksTableController.new AddBtnListener());
        deleteBtn.addActionListener(worksTableController.new DeleteBtnListener());
        saveBtn.addActionListener(worksTableController.new SaveBtnListener());
        scrollPane.setViewportView(worksTable);
    }

    public void iniExecsTable(SigTableModel sigTableModel){
        execTable.setModel(sigTableModel);
        scrollPane1.setViewportView(execTable);
    }

    public void iniButtons(){
        try {
            addBtn.setContentAreaFilled(false);
            File fAdd = new File(System.getProperty("user.dir"));
            BufferedImage addIcon = ImageIO.read(new File(fAdd.getPath() + "//images//add-icon.png"));
            addBtn.setIcon(new ImageIcon(addIcon));

            deleteBtn.setContentAreaFilled(false);
            File fDelete = new File(System.getProperty("user.dir"));
            BufferedImage deleteIcon = ImageIO.read(new File(fDelete.getPath()+"//images//Delete-icon.png"));
            deleteBtn.setIcon(new ImageIcon(deleteIcon));

            saveBtn.setContentAreaFilled(false);
            File fSave = new File(System.getProperty("user.dir"));
            BufferedImage saveIcon = ImageIO.read(new File(fSave.getPath()+"//images//check-icon.png"));
            saveBtn.setIcon(new ImageIcon(saveIcon));

            savePDFBtn.setContentAreaFilled(false);
            File fPDF = new File(System.getProperty("user.dir"));
            BufferedImage pdfIcon = ImageIO.read(new File(fPDF.getPath()+"//images//pdf-icon.png"));
            savePDFBtn.setIcon(new ImageIcon(pdfIcon));

            zoomInBtn.setContentAreaFilled(false);
            File fZoomIn = new File(System.getProperty("user.dir"));
            BufferedImage zoomInIcon = ImageIO.read(new File(fPDF.getPath()+"//images//Zoom-In-Icon.png"));
            zoomInBtn.setIcon(new ImageIcon(zoomInIcon));

            zoomOutBtn.setContentAreaFilled(false);
            File fZoomOut = new File(System.getProperty("user.dir"));
            BufferedImage zoomOutIcon = ImageIO.read(new File(fPDF.getPath()+"//images//Zoom-Out-Icon.png"));
            zoomOutBtn.setIcon(new ImageIcon(zoomOutIcon));

            openMapBtn.setContentAreaFilled(false);
            File fOpenMap = new File(System.getProperty("user.dir"));
            BufferedImage openMapIcon = ImageIO.read(new File(fPDF.getPath()+"//images//diagram-icon.png"));
            openMapBtn.setIcon(new ImageIcon(openMapIcon));

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }



    public void addDeleteBtnListener(ActionListener listenerForDeleteBtn){

        deleteBtn.addActionListener(listenerForDeleteBtn);


    }

    public void addAddBtnListener(ActionListener listenerForAddBtn){

        addBtn.addActionListener(listenerForAddBtn);

    }

    public void addSaveBtnListener(ActionListener listenerForSaveBtn){

        saveBtn.addActionListener(listenerForSaveBtn);

    }

    public void addSavePdfBtnListener(ActionListener listenerForSavePDFBtn){

        savePDFBtn.addActionListener(listenerForSavePDFBtn);

    }

    public void addOpenMapBtnListener(ActionListener listenerForOpenMapBtn){

        openMapBtn.addActionListener(listenerForOpenMapBtn);

    }






    private void createUIComponents() {
        // TODO: place custom component creation code here
        //iniWorksTable(dataObject);
       // iniDiagram(dataObject);


    }
}
