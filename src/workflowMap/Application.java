package workflowMap;

import com.mxgraph.swing.mxGraphComponent;
import net.miginfocom.swing.MigLayout;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class Application extends JFrame implements RowSetListener {
    Application(){
        super("Карта хода работ");
        dataObject = new DAO();
        appController = new AppController(this);

        workDialog = new WorkDialog(this, false);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        Double frameWidth = dimension.getWidth()/2;
        Double frameHeight = dimension.getHeight()/2;
        setSize(frameWidth.intValue(), frameHeight.intValue());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);
        JMenuItem openMenuItem = new JMenuItem("Open...");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem printMenuItem = new JMenuItem("Print...");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        menuFile.add(openMenuItem);
        menuFile.add(saveMenuItem);
        menuFile.add(printMenuItem);
        menuFile.add(exitMenuItem);

        JMenu menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);
        JMenuItem addEditItem = new JMenuItem("Add...");
        JMenuItem deleteEditItem = new JMenuItem("Delete");
        JMenuItem applyEditItem = new JMenuItem("Apply changes");
        menuEdit.add(addEditItem);
        menuEdit.add(deleteEditItem);
        menuEdit.add(applyEditItem);

        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);
        JMenuItem aboutHelpItem = new JMenuItem("About");
        menuHelp.add(aboutHelpItem);

        JPanel mainPanel = new JPanel(new MigLayout("fill"));


        JPanel topPanel = new JPanel(new MigLayout("align left"));
        showTableBtn = new JButton("Table");
        showTableBtn.addActionListener(appController);
        showTableBtn.setActionCommand(TABLEPANEL);
        showDiagramBtn = new JButton("Diagram");
        showDiagramBtn.addActionListener(appController);
        showDiagramBtn.setActionCommand(DIAGRAMPANEL);
        topPanel.add(showTableBtn);
        topPanel.add(showDiagramBtn);
        topPanel.setBackground(new Color(200, 200, 255));
        topPanel.setBorder(BorderFactory.createLineBorder(new Color(121, 173, 188), 1));

        JPanel bottomPanel = new JPanel(new MigLayout("align right"));
        bottomPanel.setBackground(new Color(200, 200, 255));
        bottomPanel.setBorder(BorderFactory.createLineBorder(new Color(121, 173, 188), 1));
        addBtn = new JButton("Add");
        addBtn.addActionListener(appController);
        JButton deleteBtn = new JButton("Delete");
        JButton applyBtn = new JButton("Apply");
        bottomPanel.add(addBtn);
        bottomPanel.add(deleteBtn);
        bottomPanel.add(applyBtn);



        cardPanel = new JPanel(new CardLayout());


        try {
            this.worksTableModel = new WorksTableModel(dataObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        worksTable = new JTable();
        worksTable.setModel(worksTableModel);
        int rowCount = worksTable.getRowCount();
        worksTable.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               System.out.println("row num: "+worksTable.getSelectedRow());
               workDialog.setFields(worksTableModel.getWorkFromRow(worksTable.getSelectedRow()));
               workDialog.setVisible(true);

           }

           @Override
           public void mousePressed(MouseEvent e) {

           }

           @Override
           public void mouseReleased(MouseEvent e) {

           }

           @Override
           public void mouseEntered(MouseEvent e) {

           }

           @Override
           public void mouseExited(MouseEvent e) {

           }
       });

        System.out.println("number of rows: " + rowCount);

        //worksTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(worksTable);

        JPanel tableCardLayout = new JPanel(new MigLayout("fill"));
        tableCardLayout.add(scrollPane, "grow");
        cardPanel.add(tableCardLayout, TABLEPANEL);

        JPanel diagramCard = new JPanel( new MigLayout("fill"));
        diagramView = new DiagramView(dataObject);
        final mxGraphComponent graphComponent = new mxGraphComponent(diagramView);
        //graphComponent.setBackgroundImage(new ImageIcon("grid.png"));
       // graphComponent.getGraphControl().updatePreferredSize();
        graphComponent.setGridStyle(mxGraphComponent.GRID_STYLE_DOT);
        graphComponent.getViewport().setBackground(Color.WHITE);
        graphComponent.setGridVisible(true);
        diagramCard.add(graphComponent, "grow");
        cardPanel.add(diagramCard, DIAGRAMPANEL);



        JPanel cardLayoutPanel = new JPanel(new MigLayout("fill, insets 0 0 0 0"));
        cardLayoutPanel.add(cardPanel, "grow");
        cardLayoutPanel.setBackground(new Color(181, 254, 225));


        mainPanel.add(topPanel, "dock north");
        mainPanel.add(cardLayoutPanel, "dock center");
        mainPanel.add(bottomPanel, "dock south");
        add(mainPanel);

        setVisible(true);

    }

    @Override
    public void rowSetChanged(RowSetEvent event) {

    }

    @Override
    public void rowChanged(RowSetEvent event) {
        CachedRowSet currentRowSet = this.worksTableModel.worksRowSet;

        try {
            currentRowSet.moveToCurrentRow();
            worksTableModel = new WorksTableModel(dataObject);
            worksTable.setModel(worksTableModel);

        } catch (SQLException ex) {
            // Display the error in a dialog box.

            JOptionPane.showMessageDialog(this, new String[] {
                            // Display a 2-line message
                            ex.getClass().getName() + ": ",
                            ex.getMessage()
                    }
            );
        }
    }

    @Override
    public void cursorMoved(RowSetEvent event) {

    }
    WorksTableModel worksTableModel;
    AppController appController;
    WorkDialog workDialog;
    JTable worksTable;
    JPanel cardPanel;
    JButton showTableBtn;
    JButton showDiagramBtn;
    JButton addBtn;
    DiagramView diagramView;
    DAO dataObject;

    final static String TABLEPANEL = "Card with Table";
    final static String DIAGRAMPANEL = "Card with Diagram";


}
