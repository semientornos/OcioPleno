/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import ociopleno.Actividad;
import ociopleno.Cliente;
import ociopleno.NewHibernateUtil;
import ociopleno.Participa;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author alesanderlopezgil
 */
public class DetallesActividades extends javax.swing.JDialog {

    Session sesion = NewHibernateUtil.getSessionFactory().openSession();
    public Actividad actividad = new Actividad();
    public String nombreActividad;
    
    /**
     * Creates new form DetallesActividades
     */
    public DetallesActividades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
    }
    
    
    public void setActividad(String actividad) {
        sesion.beginTransaction();

        Query q = sesion.createQuery("FROM Actividad where nombre = :nombre");
        q.setParameter("nombre", actividad);

        Actividad actividadObject = (Actividad) q.list().iterator().next();
        sesion.getTransaction().commit();       
       
        this.nombreActividad = actividadObject.getNombre();
        this.bodyLabel.setText(actividadObject.getDescripcion());
        this.setTitle(nombreActividad);
        tablaActividades(getClientesByIdActividad(actividadObject)); 
    }

    public List<Cliente> getClientesByIdActividad(Actividad actividadObject) {
        List<Cliente> lista = new ArrayList<Cliente>();
        //System.out.println("getParticipas: " + actividadObject.getParticipas().size());
        
        if (actividadObject.getParticipas().size() > 0) {
            Iterator it = actividadObject.getParticipas().iterator();
            while (it.hasNext()) {
                Participa participa = (Participa) it.next();
                lista.add(participa.getCliente());
                //System.out.println("cliente " + participa.getCliente().getNick());
            }
        }
        //System.out.println("clientes: " + Integer.toString(lista.size()));
        return lista;
    }
    
    
    public DefaultTableModel tablaActividades(List<Cliente> lst) {

        Iterator it = lst.iterator();

        DefaultTableModel modelo;
        String[] titulos = {"idc", "nick", "fnacimiento", "sexo", "lugar", "email"};//titulos de las columnas
        String[] registro = new String[7];
        modelo = new DefaultTableModel(null, titulos); //creamos un modelo de la tabla

        Cliente cliente = new Cliente();
        Object[] objeto = new Object[6];

        while (it.hasNext()) {
            cliente = (Cliente) it.next();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(cliente.getFnacimiento());
            String m,d,a;
            d = Integer.toString(calendar.get(Calendar.DATE));
            m = Integer.toString(calendar.get(Calendar.MONTH)+1);
            a = Integer.toString(calendar.get(Calendar.YEAR));
            String fecha = d+" de "+m+" de "+a;

            objeto[0] = cliente.getIdc();
            objeto[1] = cliente.getNick();
            objeto[2] = fecha;
            objeto[3] = cliente.getSexo().toString();
            objeto[4] = cliente.getLugar();
            objeto[5] = cliente.getEmail();            
            //objeto[3] = actividad.getDescripcion();            
            
            for (int i = 0; i < objeto.length; i++) {
                if (objeto[i] == null) {
                    registro[i] = "";
                } else {
                    registro[i] = String.valueOf(objeto[i]);
                }
            }//fin del for i
            modelo.addRow(registro);
            tablaClientes.setModel(modelo);            
        }
        return modelo;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salirButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        labelText = new javax.swing.JLabel();
        bodyLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        salirButton.setText("Salir");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaClientes);

        labelText.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        labelText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelText.setText("Clientes asociados a la actividad:");
        labelText.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bodyLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bodyLabel.setText("jLabel1");
        bodyLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(bodyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(labelText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(bodyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(382, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salirButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(salirButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        super.dispose();
    }//GEN-LAST:event_salirButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DetallesActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetallesActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetallesActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetallesActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetallesActividades dialog = new DetallesActividades(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel bodyLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelText;
    private javax.swing.JButton salirButton;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
