/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 *
 * @author Usuario
 */
public class Actividades extends javax.swing.JDialog {
    
    Session sesion = Login.getSesion();
    Cliente user = Login.getCliente();
    
    ArrayList perfilesActividad = new ArrayList<String>();

    /**
     * Creates new form Actividades
     */
    public Actividades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();        
        

        //codigo para el boton del menu principal
        /* 
        Actividades modalActividades = new Actividades(this, true);
        modalActividades.setLocationRelativeTo(null);
        modalActividades.setVisible(true);
         */
        this.setTitle("Actividades");
        this.setSize(800, 480);
        this.setLocationRelativeTo(null);

        //instancia del objeto cliente conectado
        //getCliente();
        
        
        //valores iniciales del boton ver
        //y titulo de la tabla
        btnOpcion.setText("Todas las Actividades");
        txtTitulo.setText("Mis Actividades");
        
        //se inicia la tabla con los valores por filtro        
        acctividadesSegunPerfil();        

    }    
    

    //obtenemos el cliente ..... ya veremos con qué parámetro ...... según nos pasen
    //lo hice con el id ..... por ejemplo
    //este será el cliente que estará conectado durante toda la sesión
    //se obtiene "user" qeu es una variable global
    public void getCliente() {
        
        sesion.beginTransaction();

        Query q = sesion.createQuery("FROM Cliente c WHERE c.idc=1");
        List lista = q.list();
        Iterator iter = lista.iterator();

        while (iter.hasNext()) {
            user = (Cliente) iter.next();
        }
        sesion.getTransaction().commit();
        
    }

    //metodo para comparar dos listas del tipo list
    public static boolean coincidenActividades(List<String> a, List<String> b) {

        boolean coinciden = false;
        int x = 0;

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                //if (a.get(i).contains(b.get(j))) {
                if (a.get(i).equalsIgnoreCase(b.get(j))) {
                    x++;
                }
            }//fin for j           
        }//fin for i
        if (x != 0) {
            coinciden = true;
        }
        return coinciden;
    }
    

    //metodo para obtener la lista de actividades
    public List<Actividad> AllActividades() {
        
        sesion.beginTransaction();

        Query q = sesion.createQuery("FROM Actividad");
        List lista = q.list();
        sesion.getTransaction().commit();       

        return lista;
    }
    

    //metodo para crear una tabra, dado una lista, de actividades
    //sólo muestra las relativas al perfil del usuario
    //por ello no muestro los campos sexo, edad mínima y máxima
    public DefaultTableModel tablaPerfil(List<Actividad> lst) {

        Iterator it = lst.iterator();

        DefaultTableModel modelo;
        String[] titulos = {"Nombre", "Fecha", "Lugar", "Estado"};//titulos de las columnas
        String[] registro = new String[4];
        modelo = new DefaultTableModel(null, titulos); //creamos un modelo de la tabla
        
        Actividad actividad = new Actividad();
        Object[] objeto = new Object[4];
        
        while (it.hasNext()) {
            actividad = (Actividad) it.next();
            
            //para dar algo de formato a la fecha
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(actividad.getFecha());
            String m,d,a;
            d = Integer.toString(calendar.get(Calendar.DATE));
            m = Integer.toString(calendar.get(Calendar.MONTH)+1);
            a = Integer.toString(calendar.get(Calendar.YEAR));
            String fecha = d+" de "+m+" de "+a; 
            
            objeto[0] = actividad.getNombre();
            objeto[1] = fecha;
            objeto[2] = actividad.getLugar();
            objeto[3] = actividad.getEstado();

            for (int i = 0; i < objeto.length; i++) {
                if (objeto[i] == null) {
                    registro[i] = "";
                } else {
                    registro[i] = String.valueOf(objeto[i]);
                }
            }//fin del for i
            modelo.addRow(registro);
            tablaActividades.setModel(modelo);
            
        }
        return modelo;
    }
    
    
    //metodo para crear una tabra dado una lista (con todos los elementos) de actividades
    //igual que la anterior pero esta vez con los campos sexo y edades
    public DefaultTableModel tablaActividades(List<Actividad> lst) {

        Iterator it = lst.iterator();

        DefaultTableModel modelo;
        String[] titulos = {"Nombre", "Fecha", "Lugar", "Edad Mínima", "Edad Máxima", "Sexo", "Estado"};//titulos de las columnas
        String[] registro = new String[7];
        modelo = new DefaultTableModel(null, titulos); //creamos un modelo de la tabla

        Actividad actividad = new Actividad();
        Object[] objeto = new Object[7];

        while (it.hasNext()) {
            actividad = (Actividad) it.next();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(actividad.getFecha());
            String m,d,a;
            d = Integer.toString(calendar.get(Calendar.DATE));
            m = Integer.toString(calendar.get(Calendar.MONTH)+1);
            a = Integer.toString(calendar.get(Calendar.YEAR));
            String fecha = d+" de "+m+" de "+a;

            objeto[0] = actividad.getNombre();
            objeto[1] = fecha;
            objeto[2] = actividad.getLugar();
            objeto[3] = actividad.getEdadMinima();
            objeto[4] = actividad.getEdadMaxima();
            objeto[5] = actividad.getSexo();
            objeto[6] = actividad.getEstado();            
            //objeto[3] = actividad.getDescripcion();            
            
            for (int i = 0; i < objeto.length; i++) {
                if (objeto[i] == null) {
                    registro[i] = "";
                } else {
                    registro[i] = String.valueOf(objeto[i]);
                }
            }//fin del for i
            modelo.addRow(registro);
            tablaActividades.setModel(modelo);            
        }
        return modelo;
    }

    
    //lista de las aficiones del usuario
    public List<String> afionesCliente(){
        Aficiones aficion;
        List <String> listaUsuario = new ArrayList<String>();
        Iterator it= user.getAficioneses().iterator();
        while(it.hasNext()){
            aficion = (Aficiones) it.next();
            listaUsuario.add(aficion.getAficiones());
        }
        return listaUsuario;        
    }
    
    
    //metodo años de diferencia entre fechas     
    public static int diferenciaFecha(Date dateF, Date dateI){
        int anio=0;
        long milisegundos = 24*60*60*1000;
        
        long difMilisegundos = dateF.getTime() - dateI.getTime();
        double dias = difMilisegundos/milisegundos;
        anio = (int) dias/365;

        return anio;
        
    }
    
    //metodo con los filtros para solo mostras las actividades coincidentes en sexo, edad y aficiones
    public void acctividadesSegunPerfil(){
        
        Actividad actividad = new Actividad();
        Aficiones aficion;
        
        List <Actividad> listActiv = new ArrayList<>();
                
        Iterator it= AllActividades().iterator();
        while(it.hasNext()){
            List <String> listaActividad = new ArrayList<String>();
            actividad = (Actividad) it.next();
            Iterator iter= actividad.getAficioneses().iterator();
            
            while(iter.hasNext()){
                aficion = (Aficiones) iter.next();
                listaActividad.add(aficion.getAficiones());                
            }           
            
            //System.out.println(listaActividad);
            //System.out.println(coincidenActividades(afionesCliente(), listaActividad));
            
        Date date = new Date();
        Date dateUser = user.getFnacimiento();
        int edad = diferenciaFecha (date, dateUser);        
            
            if (coincidenActividades(afionesCliente(), listaActividad)||actividad.getAficioneses().isEmpty()) { //fitro por aficiones
                if (actividad.getSexo()==user.getSexo()||actividad.getSexo()==null){                            //filtro por sexo
                    if ((actividad.getEdadMinima()== null || actividad.getEdadMinima()<edad) && (actividad.getEdadMaxima()== null ||actividad.getEdadMaxima()>edad)) { //filtro por edad
                        listActiv.add(actividad);
                    }                    
                }                
            }            
        }     
        
        tablaPerfil(listActiv);    
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
        txtNick = new javax.swing.JTextField();
        btnMenuPrincipal = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaActividades = new javax.swing.JTable();
        txtTitulo = new javax.swing.JLabel();
        btnOpcion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Usuario Conectado");

        btnMenuPrincipal.setText("Menú Principal");
        btnMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPrincipalActionPerformed(evt);
            }
        });

        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Ver");

        tablaActividades.setBackground(new java.awt.Color(204, 204, 204));
        tablaActividades.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaActividades);

        txtTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(255, 255, 255));
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("Actividades");
        txtTitulo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        txtTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnOpcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrarSesion)
                .addGap(37, 37, 37))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenuPrincipal)
                    .addComponent(btnCerrarSesion))
                .addGap(34, 34, 34)
                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOpcion, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        txtNick.setText(user.getNick());

    }//GEN-LAST:event_formWindowOpened

    private void btnOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionActionPerformed

        if (!btnOpcion.getText().equalsIgnoreCase("Todas las Actividades")) {
            btnOpcion.setText("Todas las Actividades");
            txtTitulo.setText("Mis Actividades");
            
            acctividadesSegunPerfil();            
        
        } else {
            btnOpcion.setText("Mis Actividaes");
            txtTitulo.setText("Todas las actividades");
            tablaActividades(AllActividades());
        }        

    }//GEN-LAST:event_btnOpcionActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed

        int salir = JOptionPane.showConfirmDialog(rootPane,"Desea salir de la aplicacion");
        
        if (salir==0) {
           this.dispose();
           Login vLogin = new Login(); // Crea una nueva ventana de Login
           vLogin.setLocationRelativeTo(null); // ventana centrada
           vLogin.setResizable(false);// No se puede cambiar el tamaño
           vLogin.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
           vLogin.setVisible(true);//visualiza la Ventana
           
        }

    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPrincipalActionPerformed
        
        this.dispose();
        
    }//GEN-LAST:event_btnMenuPrincipalActionPerformed

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
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Actividades dialog = new Actividades(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnMenuPrincipal;
    private javax.swing.JButton btnOpcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaActividades;
    private javax.swing.JTextField txtNick;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
