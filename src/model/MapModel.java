package model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;
import dataAccess.DAO;
import dataAccess.Work;

import javax.sql.rowset.CachedRowSet;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: 1
 * Date: 30.11.12
 * Time: 21:58
 * To change this template use File | Settings | File Templates.
 */
public class MapModel extends Observable implements Observer {

    private ArrayList<Work> works;
    private DAO dataObject;
    private String currentMapId;

    public MapModel(DAO dataObject) {

        this.dataObject = dataObject;
        dataObject.addObserver(this);

    }

    public static void toPdf(File file, mxGraph graph) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            mxRectangle bounds = ((mxGraph) graph).getGraphBounds();
            Rectangle rectangle = new Rectangle((float) bounds.getWidth(), (float) bounds.getHeight());
            Document document = new Document(rectangle);
            PdfWriter writer = PdfWriter.getInstance(document, fos);

            document.open();
            BaseFont unicode =
                    BaseFont.createFont("c:/windows/fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            PdfCanvasFactory pdfCanvasFactory = new PdfCanvasFactory(writer.getDirectContent());
            mxGraphics2DCanvas canvas = (mxGraphics2DCanvas) mxCellRenderer
                    .drawCells((mxGraph) graph, null, 1, null, pdfCanvasFactory);
            canvas.getGraphics().dispose();

            document.close();
        }
        catch (DocumentException e) {
            throw new IOException(e.getLocalizedMessage());
        }
        finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        updateMapModel();
    }

    private void updateMapModel(){
        setAllWorks(dataObject.getCurrentMapId());
        setChanged();
        notifyObservers();

    }


    private static final class PdfCanvasFactory extends mxCellRenderer.CanvasFactory {
        private final PdfContentByte cb;

        private PdfCanvasFactory(PdfContentByte cb) {
            this.cb = cb;
        }

        public mxICanvas createCanvas(int width, int height) {
            Graphics2D g2 = cb.createGraphics(width, height);
            return new mxGraphics2DCanvas(g2);
        }
    }


    public void setCurrentMapId(String mapId){
        dataObject.setCurrentMapId(mapId);
    }

    public void setAllWorks(String currentMapId){
        if(currentMapId != null){
            CachedRowSet worksRowSet = this.dataObject.getAllWorks();
            this.works = dataObject.getAllWorksArray();
        }
    }

    public ArrayList<Work> getAllWorks(){
        return this.works;
    }

    public CachedRowSet getAllSignatures(){
        return dataObject.getAllSigs();
    }

    public String getMapName(String mapId){
        return dataObject.getMapName(mapId);
    }

}
