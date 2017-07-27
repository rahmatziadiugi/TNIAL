/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.OsmTileLoader;
import org.openstreetmap.gui.jmapviewer.Style;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

/**
 *
 * @author Someone
 */
public class LihatPeta extends javax.swing.JFrame implements JMapViewerEventListener{
    private JMapViewerTree mapView;
    private JLabel zoomValue;
    private JLabel mperpLabelValue;
    private final Style legend1 = new Style(Color.BLACK, Color.GREEN, null, null);
    private boolean ok;

    /**
     * Creates new form LihatPeta
     */
    public LihatPeta(String coor) {
        initComponents();
        
        this.ok = false;
        
        //untuk mengatur posisi ketika form ini muncul
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, 0);
        this.setTitle("Pilih Lokasi Kasus");
        
        //ketika form ini diclose, maka hanya form ini saja, jika tidak, aplikasi akan terclose juga
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
        
        setVisible(true);
        
        String[] loc = coor.split(",");
        
        txLat.setText(loc[0]);
        txLon.setText(loc[1]);
        
        jPanel1.setVisible(false);
        
        mapView = new JMapViewerTree("Lokasi Kasus");
        
        setup();
        
        MapMarkerDot dadot = new MapMarkerDot(new Coordinate(Double.valueOf(loc[0]), Double.valueOf(loc[1])));
        dadot.setStyle(legend1);
        map().addMapMarker(dadot);
        
        DefaultMapController mapControl = new DefaultMapController(map()){            
            @Override   
            public void mouseClicked(MouseEvent e) {
                //System.out.println("\n"+map.getPosition(e.getPoint()));
                double lat = map.getPosition(e.getPoint()).getLat();
                double lon = map.getPosition(e.getPoint()).getLon();
                txLat.setText(String.valueOf(lat));
                txLon.setText(String.valueOf(lon));
                map().getMapMarkerList().clear();
                MapMarkerDot dadot = new MapMarkerDot(new Coordinate(lat, lon));
                dadot.setStyle(legend1);
                map().addMapMarker(dadot);
            }
        };
        
        add(mapView, BorderLayout.CENTER);
        
        jPanel1.removeAll();        
        jPanel1.add(mapView);
        jPanel1.setVisible(true);
    }
    
    private void setup(){
        //set size of map
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel(new GridLayout(0, 5));
        JScrollPane jsp = new JScrollPane(p);

        jsp.setPreferredSize(new Dimension(300,300));
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            //set map to INDONESIA
            ICoordinate to = new ICoordinate() {
            @Override
            public double getLat() {
                double lat = -2.49607;
                return lat;
            }

            @Override
            public void setLat(double lat) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public double getLon() {
                double lon = 117.89558;
                return lon;
            }

            @Override
            public void setLon(double lon) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        map().setDisplayPosition(to, 5);

        // Listen to the map viewer for user operations so components will
        // receive events and updates
        map().addJMVListener(this);

        // Set some options, e.g. tile source and that markers are visible
        map().setTileSource(new OsmTileSource.Mapnik());
        map().setTileLoader(new OsmTileLoader(map()));
        map().setMapMarkerVisible(true);
        map().setZoomContolsVisible(true);
    }
    
    public JMapViewer map() {
        return mapView.getViewer();
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txLat = new javax.swing.JTextField();
        txLon = new javax.swing.JTextField();
        btOke = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Lat: ");

        jLabel2.setText("Lon: ");

        btOke.setText("Oke");
        btOke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txLat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txLon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOke)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txLon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btOke)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkeActionPerformed
        // TODO add your handling code here:
        this.ok = true;
    }//GEN-LAST:event_btOkeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOke;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txLat;
    private javax.swing.JTextField txLon;
    // End of variables declaration//GEN-END:variables

    public String getCoor(){
        String temp = txLat.getText()+","+txLon.getText();
        this.dispose();
        return temp;
    }
    
    public boolean isOke(){
        return this.ok;
    }
    
    public void addListener(ActionListener e) {
        btOke.addActionListener(e);
    }
    
    public JButton getbtnOke() {
        return this.btOke;
    }
}
