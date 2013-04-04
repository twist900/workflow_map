package workflowMap;

import com.mxgraph.io.mxCodecRegistry;
import com.mxgraph.io.mxObjectCodec;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 23.11.12
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class DiagramView extends mxGraph {


    DiagramView(DAO dataObj){




        this.setGridEnabled(true);
         mxCodecRegistry.addPackage(Work.class.getPackage().toString());
         mxCodecRegistry.register(new mxObjectCodec(
         	new Work()));

        this.applyEdgeDefaults();

        Object parent = this.getDefaultParent();
        this.getModel().beginUpdate();
        try
        {
            ArrayList nodes = new ArrayList();
            for(int i = 0; i < dataObj.getAllWorksEntities().size(); i++){
                nodes.add(this.insertVertex(parent, null, dataObj.getAllWorksEntities().get(i), 100, 100, 80, 30,"shape=ellipse;perimeter=ellipsePerimeter"));
            }

            for(int j = 0; j < nodes.size(); j++){

                mxCell workNode1 = (mxCell)nodes.get(j);
                Work workEntity1 = (Work)workNode1.getValue();
                String mainConn = workEntity1.getMainConn();
                if(mainConn != null){
                    for(int m = 0; m < nodes.size(); m++){
                        mxCell workNode2 = (mxCell)nodes.get(m);
                        Work workEntity2 = (Work)workNode2.getValue();
                        String workId = workEntity2.getWorkId();

                        if(workId.equals(mainConn)){
                            this.insertEdge(parent, null, "Edge", workNode1, workNode2);
                        }
                    }
                }

            }
            for(int k = 0; k < nodes.size(); k++){
                mxCell workNode1 = (mxCell)nodes.get(k);
                Work workEntity1 = (Work)workNode1.getValue();
                if(workEntity1.getSecondaryConn() != null){
                    String[] secondaryConn = workEntity1.getSecondaryConn();
                    for(int m = 0; m < nodes.size(); m++){
                        mxCell workNode2 = (mxCell)nodes.get(m);
                        Work workEntity2 = (Work)workNode2.getValue();
                        String workId = workEntity2.getWorkId();

                        for(int n = 0 ; n < secondaryConn.length ; n++)
                            if(workId.equals(secondaryConn[n])){
                                this.insertEdge(parent, null, "Edge", workNode1, workNode2);
                            }
                    }
                }
            }

        }
        finally
        {
            this.getModel().endUpdate();
        }
        mxHierarchicalLayout layout = new mxHierarchicalLayout(this);
        layout.setOrientation(SwingConstants.WEST);
        layout.setIntraCellSpacing(50);
        layout.execute(this.getDefaultParent());

        Vector<Object> cells = new Vector<Object>();


    }

    public String convertValueToString(Object cell)
    {
        if (cell instanceof mxCell)
        {
            Object value = ((mxCell) cell).getValue();

            if (value instanceof Work)
            {
                Work work = (Work) value;
                return work.getWorkName()+".) " +work.getStartTime();
                }
        }
        return super.convertValueToString(cell);
    }



    private void applyEdgeDefaults() {
        // Settings for edges
        Map<String, Object> edge = new HashMap<String, Object>();
        edge.put(mxConstants.STYLE_ROUNDED, true);
        edge.put(mxConstants.STYLE_ORTHOGONAL, false);
        edge.put(mxConstants.STYLE_EDGE, "elbowEdgeStyle");
        edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
        edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM);
        edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
        edge.put(mxConstants.STYLE_FONTCOLOR, "");

        mxStylesheet edgeStyle = new mxStylesheet();
        edgeStyle.setDefaultEdgeStyle(edge);
        edgeStyle.setDefaultVertexStyle(edge);
        this.setStylesheet(edgeStyle);
    }

    private void applyNodeDefaults(){

    }
    DiagramModel diagramModel;

}
