package shapes;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.model.mxCell;
import com.mxgraph.shape.mxBasicShape;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxCellState;
import dataAccess.Work;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 17.05.13
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class WorkShape extends mxBasicShape {

    mxGraphics2DCanvas canvas;
    mxCellState state;

    public void paintShape(mxGraphics2DCanvas canvas, mxCellState state) {
        this.canvas = canvas;
        this.state = state;
        Rectangle rect = state.getRectangle();
        int x = rect.x;
        int y = rect.y;
        int w = rect.width;
        int h = rect.height;






        if (configureGraphics(canvas, state, false))
        {
            canvas.getGraphics().drawRoundRect(x,y+20, w, h-60, 80, 80);
            canvas.getGraphics().drawLine(x+37, y+20, x+37, y+h-60+20);
            canvas.getGraphics().drawLine(x+w-37, y+20, x+w-37, y+h-60+20);

            Map<String, Object> vertex = new HashMap<String, Object>();
            vertex.put(mxConstants.STYLE_STROKECOLOR, "#000000");
            canvas.createStroke(vertex);

            paintDateString();
            paintNameString();
            paintWorkIdString();
            paintDepartmentStrings();
        }
    }

    private void paintDateString(){
        if (!(this.canvas instanceof mxGraphics2DCanvas)){
            return;
        }
        mxCell cell = (mxCell)state.getCell();
        if (cell.isVertex()){
            Graphics2D g = ((mxGraphics2DCanvas)canvas).getGraphics();
            Work userValue = (Work)cell.getValue();
            String date = userValue.getStartTime();
            Font scaledFont = mxUtils.getFont(state.getStyle(), canvas.getScale());
            g.setFont(scaledFont);
            FontMetrics fm = g.getFontMetrics();
            int h = fm.getAscent();
            Color fontColor = mxUtils.getColor(state.getStyle(), mxConstants.STYLE_FONTCOLOR,Color.black);
            g.setColor(fontColor);
            if (date != null){
                int w = SwingUtilities.computeStringWidth(fm, date);
                g.drawString(date ,(int) state.getX() + (int) state.getWidth() + canvas.getTranslate().x
                        - w,(int) state.getY() - canvas.getTranslate().y + h);
            }
        }
    }

    private void paintDepartmentStrings(){
        if (!(this.canvas instanceof mxGraphics2DCanvas)){
            return;
        }
        mxCell cell = (mxCell)state.getCell();
        if (cell.isVertex()){
            Graphics2D g = ((mxGraphics2DCanvas)canvas).getGraphics();
            Work userValue = (Work)cell.getValue();
            String date = userValue.getStartTime();
            Font scaledFont = mxUtils.getFont(state.getStyle(), canvas.getScale());
            g.setFont(scaledFont);
            FontMetrics fm = g.getFontMetrics();
            int h = fm.getAscent();
            int fontHeight = fm.getHeight();
            Color fontColor = mxUtils.getColor(state.getStyle(), mxConstants.STYLE_FONTCOLOR,Color.black);
            g.setColor(fontColor);
            for(int i = 0; i<3; i++){
                if (date != null){
                    int w = SwingUtilities.computeStringWidth(fm, date);
                    g.drawString(date ,(int) state.getX() + (int) state.getWidth() + canvas.getTranslate().x
                            - w,(int) state.getY() - canvas.getTranslate().y
                            + (int) state.getHeight() + h + fontHeight*i-40);
                 }
            }
        }

    }

    private void paintWorkIdString(){
        if (!(this.canvas instanceof mxGraphics2DCanvas)){
            return;
        }
        mxCell cell = (mxCell)state.getCell();
        if (cell.isVertex()){
            Graphics2D g = ((mxGraphics2DCanvas)canvas).getGraphics();
            Work userValue = (Work)cell.getValue();
            String date = userValue.getWorkId();
            Font scaledFont = mxUtils.getFont(state.getStyle(), canvas.getScale());
            g.setFont(scaledFont);
            FontMetrics fm = g.getFontMetrics();
            int h = fm.getAscent();
            int fontHeight = fm.getHeight();
            Color fontColor = mxUtils.getColor(state.getStyle(), mxConstants.STYLE_FONTCOLOR,Color.black);
            g.setColor(fontColor);
            if (date != null){
                int w = SwingUtilities.computeStringWidth(fm, date);
                g.drawString(date ,(int) state.getX() + 20 + canvas.getTranslate().x
                        ,(int) state.getY() - canvas.getTranslate().y + h + 60);
            }

        }
    }

    private void paintNameString(){
        if (!(this.canvas instanceof mxGraphics2DCanvas)){
            return;
        }
        mxCell cell = (mxCell)state.getCell();
        if (cell.isVertex()){
            Graphics2D g = ((mxGraphics2DCanvas)canvas).getGraphics();
            Work userValue = (Work)cell.getValue();
            String date = userValue.getWorkName();
            Font scaledFont = mxUtils.getFont(state.getStyle(), canvas.getScale());
            g.setFont(scaledFont);
            FontMetrics fm = g.getFontMetrics();
            int h = fm.getAscent();
            int fontHeight = fm.getHeight();
            Color fontColor = mxUtils.getColor(state.getStyle(), mxConstants.STYLE_FONTCOLOR,Color.black);
            g.setColor(fontColor);
            if (date != null){
                int w = SwingUtilities.computeStringWidth(fm, date);
                g.drawString(date ,(int) state.getX() + 70 + canvas.getTranslate().x
                        ,(int) state.getY() - canvas.getTranslate().y + h + 60);
            }

        }
    }
}
