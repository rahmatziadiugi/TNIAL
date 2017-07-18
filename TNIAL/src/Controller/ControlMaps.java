package Controller;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.OsmTileLoader;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import org.openstreetmap.gui.jmapviewer.Coordinate;


/**
 * A demo class to start a Swing application which shows a map and has some pre-defined options set.
 * Based on: http://svn.openstreetmap.org/applications/viewer/jmapviewer/src/org/openstreetmap/gui/jmapviewer/Demo.java by Jan Peter Stotz
 * @author aldebaran
 */
public class ControlMaps extends JPanel implements JMapViewerEventListener {

    private static final long serialVersionUID = 1L;

    private JMapViewerTree treeMap;
    private JLabel zoomLabel;
    private JLabel zoomValue;
    private JLabel mperpLabelName;
    private JLabel mperpLabelValue;
    private boolean clicked;


    /**
     * Setups the JFrame layout, sets some default options for the JMapViewerTree and displays a map in the window.
     */
    public ControlMaps() {
        //super("JMapViewer Demo");
        //setPreferredSize(new Dimension(100,100));
        treeMap = new JMapViewerTree("Zones");
        //setupPanels();
        setupJFrame();

        // Listen to the map viewer for user operations so components will
        // receive events and updates
        map().addJMVListener(this);

        // Set some options, e.g. tile source and that markers are visible
        map().setTileSource(new OsmTileSource.Mapnik());
        map().setTileLoader(new OsmTileLoader(map()));
        map().setMapMarkerVisible(true);
        map().setZoomContolsVisible(true);

        // activate map in window
        //treeMap.setTreeVisible(true);
        add(treeMap, BorderLayout.CENTER);
        
    }
    
    private void setupJFrame() {
        setSize(400, 400);
        setLayout(new BorderLayout());
    }    
    
//    public void setupPanels() {
//        // not yet called this method
//        JPanel panel = new JPanel();
//        JPanel panelTop = new JPanel();
//        JPanel panelBottom = new JPanel();
//        JPanel helpPanel = new JPanel();
//
//        mperpLabelName = new JLabel("Meters/Pixels: ");
//        mperpLabelValue = new JLabel(String.format("%s", map().getMeterPerPixel()));
//        zoomLabel = new JLabel("Zoom: ");
//        zoomValue = new JLabel(String.format("%s", map().getZoom()));
//
//        add(panel, BorderLayout.NORTH);
//        add(helpPanel, BorderLayout.SOUTH);
//        panel.add(panelTop, BorderLayout.NORTH);
//        panel.add(panelBottom, BorderLayout.SOUTH);
//        JLabel helpLabel = new JLabel("Use right mouse button to move,\n "
//                + "left double click or mouse wheel to zoom.");
//        helpPanel.add(helpLabel);
//
//        panelTop.add(zoomLabel);
//        panelTop.add(zoomValue);
//        panelTop.add(mperpLabelName);
//        panelTop.add(mperpLabelValue);
//    }


    public JMapViewer map() {
        return treeMap.getViewer();
    }
    
//    public void coordinate(){
//        if(clicked=true){
//            Coordinate coord = null;
//            map().getMapPosition(coord);
//            System.out.println(coord);
//        }
//    }

    public void updateZoomParameters() {
        if (mperpLabelValue != null)
            mperpLabelValue.setText(String.format("%s", map().getMeterPerPixel()));
        if (zoomValue != null)
            zoomValue.setText(String.format("%s", map().getZoom()));
    }


    @Override
    public void processCommand(JMVCommandEvent command) {
        if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
                command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
            updateZoomParameters();
        }
    }
}