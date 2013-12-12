package view;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.io.mxCodecRegistry;
import com.mxgraph.io.mxObjectCodec;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;
import com.mxgraph.view.mxStylesheet;
import dataAccess.Relation;
import dataAccess.Work;
import model.MapModel;
import shapes.WorkShape;


import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 23.11.12
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class MapView extends mxGraph implements Observer {

    public static final String MY_CUSTOM_VERTEX_STYLE = "MY_CUSTOM_VERTEX_STYLE";
    private String htmlLabel;
    MapModel mapModel;

    public MapView(MapModel mapModel) {

        this.mapModel = mapModel;
        mapModel.addObserver(this);


    }

    protected mxGraphView createGraphView(){
        return new mxGraphView(this){
            public void updateLabelBounds(mxCellState state){
                super.updateLabelBounds(state);
                mxRectangle lb = state.getLabelBounds();
                if (lb != null && model.isVertex(state.getCell()))
                {

                    lb.setX(state.getX());
                    lb.setWidth(state.getWidth());
                }
            }
        };
    }

    public void createMap(){
        this.applyEdgeDefaults();
        mxGraphics2DCanvas.putShape("roundRect", new WorkShape());
        // mxGraphics2DCanvas.putTextShape("workText", new WorkTextShape());


        this.setGridEnabled(true);
        this.setCellsEditable(false);
        this.setAllowDanglingEdges(false);
        this.setCellsResizable(false);



        mxCodecRegistry.addPackage(Work.class.getPackage().toString());
        mxCodecRegistry.register(new mxObjectCodec(
                new Work()));


        Object parent = this.getDefaultParent();
        this.getModel().beginUpdate();
        try {
            ArrayList nodes = new ArrayList();
            for (int i = 0; i < mapModel.getAllWorks().size(); i++) {
                nodes.add(this.insertVertex(parent, null, mapModel.getAllWorks().get(i), 100,
                        100, 200, 160, "fontFamily=Arial;shape=roundRect;fontColor=#000000;strokeColor=#000000;"));

            }

            for (int k = 0; k < nodes.size(); k++) {
                mxCell workNode1 = (mxCell) nodes.get(k);
                Work currentWork = (Work) workNode1.getValue();
                ArrayList<Relation> currentWorkRelations = currentWork.getWorkRelations();
                if (currentWorkRelations.size() != 0) {
                    for (int m = 0; m < nodes.size(); m++) {
                        mxCell workNode2 = (mxCell) nodes.get(m);
                        Work workEntity2 = (Work) workNode2.getValue();
                        String workId = workEntity2.getWorkId();
                        for (int n = 0; n < currentWorkRelations.size(); n++)
                            if (workId.equals(String.valueOf(currentWorkRelations.get(n).getWorkIdNext()))) {
                                this.insertEdge(parent, null, "Edge", workNode1, workNode2);
                            }
                    }
                }
            }

        } finally {
            this.getModel().endUpdate();
        }


        mxHierarchicalLayout layout = new mxHierarchicalLayout(this);
        mxParallelEdgeLayout layout1 = new mxParallelEdgeLayout(this);
        layout.setOrientation(SwingConstants.WEST);
        layout.setIntraCellSpacing(50);
        layout.execute(this.getDefaultParent());
        layout.execute(this.getDefaultParent());




        moveCells(mxGraphModel.getChildren(model, this.getDefaultParent()), 100, 100, false);




        this.getModel().beginUpdate();
        try {
            double scale = this.view.getScale();
            mxRectangle rect = this.getGraphBounds();


            double x = rect.getCenterX();
            double y = rect.getY() + 40;
            String label = mapModel.getMapName("54alm45");
            this.insertVertex(parent, null, label, x, y, 700, 50,
                    "fontColor=#000000;whiteSpace=wrap;fillColor=white;fontSize=26;strokeWidth=0;");
        } finally {
            this.getModel().endUpdate();
        }

        this.setHtmlLabels(true);
        this.setAutoSizeCells(true);

        Object cell;

        this.getModel().beginUpdate();
        try {
            mxRectangle rect1 = this.getGraphBounds();
            double newX = rect1.getX()+rect1.getWidth();
            double newY = rect1.getY() + rect1.getHeight();

            CachedRowSet crsSigs = mapModel.getAllSignatures();
            String htmlSig = getHtmlLabel(crsSigs);
            cell = this.insertVertex(parent, null, htmlSig, newX, newY, 80, 30,
                    "fontColor=#000000;fillColor=white;fontSize=22;strokeColor=#111111;");
        } finally {
            this.getModel().endUpdate();
        }

        this.updateCellSize(cell);


        Vector<Object> cells = new Vector<Object>();

    }

    public String convertValueToString(Object cell)
    {
       if (cell instanceof mxCell && model.isVertex(cell))
        {
            Object value = ((mxCell) cell).getValue();

            if (!(value instanceof Work))
            {

                return value.toString();
            }


        }

       return "";


    }

    public void updateLabelBounds(){

    }



    private void applyEdgeDefaults() {
        // Settings for edges
        Map<String, Object> edge = new HashMap<String, Object>();
        edge.put(mxConstants.STYLE_ROUNDED, true);
        edge.put(mxConstants.STYLE_ORTHOGONAL, true);
        edge.put(mxConstants.EDGESTYLE_ENTITY_RELATION, true);
        edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
        edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
        edge.put(mxConstants.STYLE_FONTCOLOR, "");
        edge.put(mxConstants.STYLE_FONTFAMILY, "Times New Roman");
        edge.put(mxConstants.STYLE_ENTRY_X, "0");
        edge.put(mxConstants.STYLE_ENTRY_Y, "0.45");
        edge.put(mxConstants.STYLE_EXIT_X, "1");
        edge.put(mxConstants.STYLE_EXIT_Y, "0.45");
        edge.put(mxConstants.STYLE_EDITABLE, false);


        edge.put(mxConstants.STYLE_DELETABLE, true);

        mxStylesheet edgeStyle = new mxStylesheet();
        edgeStyle.setDefaultEdgeStyle(edge);


        Hashtable<String, Object> style;
        mxStylesheet stylesheet = this.getStylesheet();
        style = new Hashtable<String, Object>();
        style.put(mxConstants.STYLE_STROKECOLOR, "#FFFF00");
        stylesheet.putCellStyle(MY_CUSTOM_VERTEX_STYLE, style);


        //Map<String, Object> vertex = new HashMap<String, Object>();
        //vertex.put(mxConstants.STYLE_STROKECOLOR, "#000000");

        //mxStylesheet vertexStyle = new mxStylesheet();
        //edgeStyle.setDefaultVertexStyle(vertex);




        this.setStylesheet(edgeStyle);


    }

   /* public void drawState(mxICanvas canvas, mxCellState state, boolean drawLabel){
        super.drawState(canvas,state,drawLabel);
        if (!(canvas instanceof mxGraphics2DCanvas)){
            return;
        }
        if (model.isVertex(state.getCell())){
            Graphics2D g = ((mxGraphics2DCanvas)canvas).getGraphics();

            Object userValue = model.getValue(state.getCell());
            String textToDisplay ="lala"; // do something with userValue
            String textToDisplay1 ="pff";
            Font scaledFont = mxUtils.getFont(state.getStyle(), canvas.getScale());
            g.setFont(scaledFont);
            FontMetrics fm = g.getFontMetrics();
            int h = fm.getAscent();
            Color fontColor = mxUtils.getColor(state.getStyle(),mxConstants.STYLE_FONTCOLOR,Color.black);
            g.setColor(fontColor);
            if (textToDisplay != null){
                int w = SwingUtilities.computeStringWidth(fm,textToDisplay);
                int w1 =  SwingUtilities.computeStringWidth(fm, textToDisplay1);
                g.drawString(textToDisplay ,(int) state.getX() + (int) state.getWidth() + canvas.getTranslate().x - w,(int) state.getY() + (int) state.getHeight() + canvas.getTranslate().y + h);
                for(int i = 0; i < 10; i++){
                     g.drawString(textToDisplay1 ,(int) state.getX() + canvas.getTranslate().x ,(int) state.getY()  + canvas.getTranslate().y - h*i);
                }
            }
        }
    }
           */
    private void applyNodeDefaults(){

    }

    public int calcHeight(Vector<String> strings){
        int height = 0;

        return height;
    }

    public String getHtmlLabel(CachedRowSet crsSigs) {
        StringBuilder htmlBuilder = new StringBuilder();
        try {
        htmlBuilder.append("<html>");
        htmlBuilder.append("<table border='0'>");

            while(crsSigs.next()){

                htmlBuilder.append("<tr>");
                htmlBuilder.append("<td>");
                htmlBuilder.append(crsSigs.getString("position"));
                htmlBuilder.append("</td>");
                htmlBuilder.append("<td>");
                htmlBuilder.append(" ____________ ");
                htmlBuilder.append("</td>");
                htmlBuilder.append("<td>");
                htmlBuilder.append(crsSigs.getString("surname"));
                htmlBuilder.append(" ");
                htmlBuilder.append(crsSigs.getString("name"));
                htmlBuilder.append(" ");
                htmlBuilder.append(crsSigs.getString("middlename"));
                htmlBuilder.append("</td>");
                htmlBuilder.append("</tr>");

            }

        htmlBuilder.append("</table>");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return htmlBuilder.toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        createMap();
    }
}
