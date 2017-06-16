/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ControlTunDinas;
import Model.TunDinas;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Someone
 */
public class PanelTunDinas extends javax.swing.JPanel {

    /**
     * Creates new form TunDinas
     */
    public PanelTunDinas() {
        initComponents();
        this.setVisible(true);
        
        LabelAtas.setText("Tambah data");
        txDateField.setDate(new Date());
                
        //get DB
        control = new ControlTunDinas();
        control.getDataDB();
        data = control.getDatanya();
        this.n = data.size();
        
        //set button
        if(this.n<1){
            btAdd.setName("Tambah");
        }
        btCancel.setVisible(false);
        btDelete.setVisible(false);
        
        //set Table
        setIsiTable();
        
        //set format tanggal
        txDateField.setFormats("MM/dd/yyyy");
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txLokasiNTanah = new javax.swing.JTextArea();
        txDasar = new javax.swing.JTextField();
        txNoSurat = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txPermasalahan = new javax.swing.JTextArea();
        btAdd = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        txDateField = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableTunDinas = new javax.swing.JTable();
        LabelAtas = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("TUN DINAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("<html>Lokasi dan<br />data tanah</html>");

        jLabel3.setText("Dasar");

        jLabel4.setText("No. Surat");

        jLabel6.setText("Tgl Surat");

        jLabel7.setText("Permasalahan");

        txLokasiNTanah.setColumns(20);
        txLokasiNTanah.setRows(5);
        jScrollPane1.setViewportView(txLokasiNTanah);

        txPermasalahan.setColumns(20);
        txPermasalahan.setRows(5);
        jScrollPane2.setViewportView(txPermasalahan);

        btAdd.setText("Tambah");
        btAdd.setPreferredSize(new java.awt.Dimension(100, 35));
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btCancel.setText("batal");
        btCancel.setPreferredSize(new java.awt.Dimension(100, 35));
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btDelete.setText("Hapus");
        btDelete.setPreferredSize(new java.awt.Dimension(100, 35));
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txDasar)
                        .addComponent(txNoSurat)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addComponent(txDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txDasar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txNoSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TableTunDinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableTunDinas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(TableTunDinas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        LabelAtas.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelAtas)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(LabelAtas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:
        btAdd.setName("Simpan");
    }//GEN-LAST:event_btAddActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        // TODO add your handling code here:
        
        resetField();
        btAdd.setText("Tambah");
        btCancel.setVisible(false);
        btDelete.setVisible(false);
        LabelAtas.setText("Tambah data");
    }//GEN-LAST:event_btCancelActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
        
        resetField();
        btAdd.setText("Tambah");
        btCancel.setVisible(false);
        btDelete.setVisible(false);
        LabelAtas.setText("Tambah data");
    }//GEN-LAST:event_btDeleteActionPerformed

    public void setIsiTable(){
        control.getDataDB();
        data = control.getDatanya();
        Collections.reverse(data);
        this.n = data.size();
        resetTable(this.n);
        TableTunDinas.setModel(resetTable);  
        
        this.thisTableProperties();
        
        for(int i=0; i<this.n; i++){
            TableTunDinas.setValueAt(i+1, i, 0);
            TableTunDinas.setValueAt(data.get(i).getLokasiDT(), i, 1);
            TableTunDinas.setValueAt(data.get(i).getDasar(), i, 2);
            TableTunDinas.setValueAt(data.get(i).getPermasalahan(), i, 3);
            TableTunDinas.setValueAt(control.getKetTingkat(data.get(i).getkdTIngkat()), i, 4);
            TableTunDinas.setValueAt(control.getStatusnya(data.get(i).getidStatusTingkat()), i, 5);
            TableTunDinas.setValueAt("Tingkat", i, 6);
        }        
    }
    
    private void thisTableProperties(){        
        TableTunDinas.getTableHeader().setReorderingAllowed(false);
        
        //set column width
        TableTunDinas.getColumnModel().getColumn(0).setPreferredWidth(50);        
        TableTunDinas.getColumnModel().getColumn(1).setPreferredWidth(275);
        TableTunDinas.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableTunDinas.getColumnModel().getColumn(3).setPreferredWidth(140);
        TableTunDinas.getColumnModel().getColumn(4).setPreferredWidth(100);
        TableTunDinas.getColumnModel().getColumn(5).setPreferredWidth(80);
        TableTunDinas.getColumnModel().getColumn(6).setPreferredWidth(80);
        //set button column
        TableTunDinas.getColumnModel().getColumn(6).setCellRenderer(new ClientsTableButtonRenderer());
        TableTunDinas.getColumnModel().getColumn(6).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
        //onclick event
        TableTunDinas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TableTunDinas.rowAtPoint(evt.getPoint());
                int col = TableTunDinas.columnAtPoint(evt.getPoint());
                if (col < 6) {
                    if(isFieldModified(data.get(row))){
                        int reply = JOptionPane.showConfirmDialog(
                                null, //posisi kayaknya
                                "Apakah anda yakin ingin membuka ini?\nPerubahan yang belum disimpan akan hilang.", //pesan
                                "Peringatan",  //judul
                                JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            txLokasiNTanah.setText(data.get(row).getLokasiDT());
                            txDasar.setText(data.get(row).getDasar());
                            txNoSurat.setText(data.get(row).getnoSurat());
                            txDateField.setDate(data.get(row).gettglDasar());
                            txPermasalahan.setText(data.get(row).getPermasalahan());

                            btAdd.setText("Perbarui");
                            btCancel.setVisible(true);
                            btDelete.setVisible(true);
                            LabelAtas.setText("Ubah data");
                        }
                        else {
                           //kembali aja, gak ngefek
                        }
                    }
                }
            }
        });
        
        //can only select one row at a time
        TableTunDinas.setRowSelectionAllowed(true);
        TableTunDinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public boolean isFieldModified(TunDinas datanya){
        boolean temp = false;
        
        
        
        return temp;
    }
    
    public void resetField(){
        txLokasiNTanah.setText(null);
        txDasar.setText(null);
        txNoSurat.setText(null);
        txDateField.setDate(new Date());
        txPermasalahan.setText(null);
    }
    
    public void resetTable(int i){
        String[] header = {"No","Lokasi & Data Tanah","Dasar","Permasalahan","Tingkat","Status","Data Tingkat"};
        resetTable = new DefaultTableModel(null, header){
            public boolean isCellEditable(int row, int column)      //override isCellEditable
                //PRE:  row > 0, column > 0
                //POST: FCTVAL == false always
            {
                return column==6; //kolom 6 editable
            }
            
            
        };
        resetTable.setRowCount(i);        
    }
    
    private DefaultTableModel resetTable;
    private ControlTunDinas control;
    ArrayList<TunDinas> data = new ArrayList<>();
    private int n;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelAtas;
    private javax.swing.JTable TableTunDinas;
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txDasar;
    private org.jdesktop.swingx.JXDatePicker txDateField;
    private javax.swing.JTextArea txLokasiNTanah;
    private javax.swing.JTextField txNoSurat;
    private javax.swing.JTextArea txPermasalahan;
    // End of variables declaration//GEN-END:variables

    //button in cell
    
    class ClientsTableButtonRenderer extends JButton implements TableCellRenderer{
    public ClientsTableButtonRenderer(){
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        setBackground( table.getBackground() );
        setForeground(Color.black);
        setBackground(UIManager.getColor("Button.background"));
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

public class ClientsTableRenderer extends DefaultCellEditor{
    private JButton button;
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;

    public ClientsTableRenderer(JCheckBox checkBox){
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fireEditingStopped();
            }
        });
    }
    
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
        this.table = table;
        this.row = row;
        this.col = column;
        
        button.setForeground(Color.black);
        button.setBackground(UIManager.getColor("Button.background"));
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }
    
    public Object getCellEditorValue(){
        if (clicked){
            JOptionPane.showMessageDialog(null, "Column with Value: "+table.getValueAt(row, 1) + " -  Clicked!");
            
            btAdd.setText("Perbarui");
            btCancel.setVisible(true);
            btDelete.setVisible(true);
            LabelAtas.setText("Ubah data");
            
        }
        clicked = false;
        return new String(label);
    }

    public boolean stopCellEditing(){
        clicked = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped(){
        super.fireEditingStopped();
    }
}

}
