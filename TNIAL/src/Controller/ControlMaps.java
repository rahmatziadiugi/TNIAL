package Controller;

import Model.TunDinas;
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
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MouseInputListener;
import org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.LayerGroup;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;
import org.openstreetmap.gui.jmapviewer.Style;


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
    private ArrayList<TunDinas> dataTunDinas = new ArrayList<>();
    private Database.DB4SQLServer db = new Database.DB4SQLServer();


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
        
        
        addMarkTunDinas();
        
        add(treeMap, BorderLayout.CENTER);
        
    }
    
    public ControlMaps(String x, String y){
        treeMap = new JMapViewerTree("Zones");
        setupJFrame();
        map().addJMVListener(this);
        map().setTileSource(new OsmTileSource.Mapnik());
        map().setTileLoader(new OsmTileLoader(map()));
        map().setMapMarkerVisible(true);
        map().setZoomContolsVisible(true);
                
        Style legend1 = new Style(Color.BLACK, Color.GREEN, null, null);
        MapMarkerDot dadot = new MapMarkerDot(new Coordinate(Double.valueOf(y), Double.valueOf(x)));
        dadot.setStyle(legend1);
        map().addMapMarker(dadot);
                                
        add(treeMap, BorderLayout.CENTER);
    }
    
    private void setupJFrame() {
        setSize(600, 600);
        setLayout(new BorderLayout());
    }    

    public JMapViewer map() {
        return treeMap.getViewer();
    }
    
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
    
    public void addMarkTunDinas(){
        Style legend1 = new Style(Color.BLACK, Color.GREEN, null, null);
        getDataDBTunDinas();
        ArrayList<MapMarkerDot> dotList = new ArrayList<>();
        
        for(int i=0; i< dataTunDinas.size(); i++){
            dotList.add(new MapMarkerDot(dataTunDinas.get(i).getDasar(), dataTunDinas.get(i).getCoor()));
            dotList.get(i).setStyle(legend1);
            map().addMapMarker(dotList.get(i));
        }        
    }
    
    public void getDataDBTunDinas(){
        try {
            //Class.forName(driver);
            Connection con = DriverManager.getConnection(this.db.getURL());
            PreparedStatement st = con.prepareStatement("SELECT * FROM bankum_tundinas");
            ResultSet rs = st.executeQuery();
            //rs.beforeFirst();
            int n = 0;            
            while(rs.next()){
                this.dataTunDinas.add(new TunDinas(
                        rs.getString("idTundinas"),
                        rs.getString("lokasiDT"),
                        rs.getString("Dasar"),
                        rs.getString("noSurat"),
                        rs.getDate("tglDasar"),
                        rs.getString("Permasalahan")
                ));
                String[] coor = new String[2];
                if(rs.getString("Koordinat")!=null){
                    coor = rs.getString("Koordinat").split(",");
                }else{
                    coor[0] = "0";
                    coor[1] = "0";
                }   
                this.dataTunDinas.get(n).setCoor(Double.valueOf(coor[0]), Double.valueOf(coor[1]));
                n++;
            }                
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TunDinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}