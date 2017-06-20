/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;

/**
 *
 * @author gmg
 */
public class Presidente extends javax.swing.JDialog {

    /**
     * Creates new form Presidente
     */
    DefaultTableModel modelo = new DefaultTableModel();
    public Presidente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        //DefaultTableModel modelo = new DefaultTableModel();

            tabla.setModel(modelo);

            //creo 3 columnas con sus etiquetas
            //estas son las columnas del JTable
             modelo.addColumn("ID");
            modelo.addColumn("Actividad");
            modelo.addColumn("Estado");
            
            
            Object [] datos=new Object[3];
            
        //org.hibernate.Transaction tran= sesion.beginTransaction();    
        Query q = Login.getSesion().createQuery("from Cliente where nick="+"\'"+Login.getCliente().getNick()+"\'");
        Cliente cl=(Cliente)q.uniqueResult();
        //System.out.println(cl.getIdc());
        
        q = Login.getSesion().createQuery("from Participa where idc="+cl.getIdc()+" and responsable="+1);
        Participa partc; //Antes Valores p=null; por si da problemas
        List <Participa> lista= q.list();
        Iterator <Participa> iter= lista.iterator();
        
        while(iter.hasNext())
        {
            partc=iter.next();
            datos[0]=partc.getActividad().getIda();
            datos[1]=partc.getActividad().getNombre();
           
            if(partc.getActividad().getEstado()==0)
            {
                datos[2]="En Curso";
            }
            else if (partc.getActividad().getEstado()==1)
            {
                datos[2]="Finalizada";
            }
            
            
            modelo.addRow(datos);
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );    
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla);

        jButton3.setText("Subir Video");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setText("Ver Videos");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        if (tabla.getSelectedRow() >= 0)
        {
            Query activo=Login.getSesion().createQuery("from Actividad where ida="+tabla.getValueAt(tabla.getSelectedRow(),0));
            Actividad muyactivo=(Actividad)activo.uniqueResult();
            //Creamos el objeto JFileChooser
            JFileChooser fc=new JFileChooser();
            
            //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
            int seleccion=fc.showDialog(fc, null);

            //Si el usuario, pincha en aceptar
            if(seleccion==JFileChooser.APPROVE_OPTION)
            {

                 File archivo = fc.getSelectedFile(); 
                 String archivo2 = fc.getName(archivo);     


                   if ((archivo == null) || (archivo.getName().equals(""))) 
                   {


                   }
                   else
                   {


                         String ruta=archivo.toString();
                         ftp ftp=new ftp();
                         if(ftp.comprobar(archivo2)==0){
                             if (ftp.subir(archivo2,ruta))//+"\'"+
                            {
                                org.hibernate.Transaction tran= Login.getSesion().beginTransaction();
                                Multimedia multi=new Multimedia(archivo2,muyactivo);
                                multi.altaMultimedia();
                               // Query upload=OcioPleno.sesion.createQuery("insert into Multimedia(\'"+archivo2+"\',"+tabla.getValueAt(tabla.getSelectedRow(), 0)+")");
                                //Query upload=OcioPleno.sesion.createQuery("insert into Multimedia(nombreArchivo,ida)"+"\'"+archivo2+"\',"+tabla.getValueAt(tabla.getSelectedRow(), 0));
                                //upload.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Archivo Subido");
                                tran.commit();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Archivo NO Subido");
                            }

                        }else{
                             JOptionPane.showMessageDialog(null, "El archivo ya existe");
                         }


                    }
            }
                         
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tabla.getSelectedRow() >= 0)
        {
            ///
            Mact mact=new Mact(this,true, (int) tabla.getValueAt(tabla.getSelectedRow(), 0));
            mact.setResizable(false);
            mact.setLocationRelativeTo(null);
            mact.setTitle("Edicion de Actividades");
            mact.setVisible(true);

        }
        for(int i=0;i<tabla.getRowCount();i++){
            modelo.removeRow(i);
            i-=1;
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );  
        
        
        
        
         Object [] datos=new Object[3];
            
        //org.hibernate.Transaction tran= sesion.beginTransaction();    
        Query q = Login.getSesion().createQuery("from Cliente where nick="+"\'"+Login.getCliente().getNick()+"\'");
        Cliente cl=(Cliente)q.uniqueResult();
        //System.out.println(cl.getIdc());
        
        q = Login.getSesion().createQuery("from Participa where idc="+cl.getIdc()+" and responsable="+1);
        Participa partc; //Antes Valores p=null; por si da problemas
        List <Participa> lista= q.list();
        Iterator <Participa> iter= lista.iterator();
        
        while(iter.hasNext())
        {
            partc=iter.next();
            datos[0]=partc.getActividad().getIda();
            datos[1]=partc.getActividad().getNombre();
            
            if(partc.getActividad().getEstado()==0)
            {
                datos[2]="En Curso";
            }
            else if (partc.getActividad().getEstado()==1)
            {
                datos[2]="Finalizada";
            }
            
            
            modelo.addRow(datos);
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (tabla.getSelectedRow() >= 0)
        {
            org.hibernate.Transaction tran= Login.getSesion().beginTransaction();
            Query delete=Login.getSesion().createQuery("delete Actividad where ida="+tabla.getValueAt(tabla.getSelectedRow(), 0)); 
            
            delete.executeUpdate();
            tran.commit();
            for(int i=0;i<tabla.getRowCount();i++){
            modelo.removeRow(i);
            i-=1;
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );  
        
        
        
        
         Object [] datos=new Object[3];
            
        //org.hibernate.Transaction tran= sesion.beginTransaction();    
        Query q = Login.getSesion().createQuery("from Cliente where nick="+"\'"+Login.getCliente().getNick()+"\'");
        Cliente cl=(Cliente)q.uniqueResult();
        //System.out.println(cl.getIdc());
        
        q = Login.getSesion().createQuery("from Participa where idc="+cl.getIdc()+" and responsable="+1);
        Participa partc; //Antes Valores p=null; por si da problemas
        List <Participa> lista= q.list();
        Iterator <Participa> iter= lista.iterator();
        
        while(iter.hasNext())
        {
            partc=iter.next();
            datos[0]=partc.getActividad().getIda();
            datos[1]=partc.getActividad().getNombre();
            
            if(partc.getActividad().getEstado()==0)
            {
                datos[2]="En Curso";
            }
            else if (partc.getActividad().getEstado()==1)
            {
                datos[2]="Finalizada";
            }
            
            
            modelo.addRow(datos);
        }
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );    
        
        
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (tabla.getSelectedRow() >= 0)
        {
            ///
            VerMulti mult=new VerMulti(this,true, (int) tabla.getValueAt(tabla.getSelectedRow(), 0));
            mult.setResizable(false);
            mult.setLocationRelativeTo(null);
            mult.setTitle("Ver Multimedia");
            mult.setVisible(true);

        }
              
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Presidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Presidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Presidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Presidente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Presidente dialog = new Presidente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}