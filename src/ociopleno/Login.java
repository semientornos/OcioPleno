/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jesus
 */
public class Login extends javax.swing.JFrame {
    
    private static Session sesion; // variable con la sesion de Hibernate
    private static Cliente cliente;// Variable con el objeto cliente que inicia sesión

    /**
     * Creates new form VentanaLogin
     */
    public Login() {
        
        initComponents();
        
        //Inicia sesion Hibernate
        sesion = NewHibernateUtil.getSessionFactory().openSession(); 
        setSesion(sesion);
        
        C_usuario.requestFocus();// sitúa el cursor en caja
   
    }


    /**
     * VALIDA EL USUARIO Y LA CONTRASEÑA
     */
    private boolean validar(){
        
        String usu = C_usuario.getText();
        Boolean seguir = false;
        
        char passArray[] = C_pass.getPassword();
        String pwd = new String(passArray);  
       
        // --- HIBERNATE        
        Transaction tx = null;
        
        try{
             tx = sesion.beginTransaction();

             // Validación del NICK
             SQLQuery q= sesion.createSQLQuery("SELECT * FROM cliente WHERE nick='"+usu+"'");
             q.addEntity(Cliente.class);
             
             List resultados=q.list();
             Iterator iter=resultados.iterator();
             // Si entra en el while es que existe el nick
             while(iter.hasNext()){
                Cliente c;
                c=(Cliente)iter.next();
                // Validación de la PASS
                if(c.getPass().equals(pwd)){
                    seguir = true;
                    setCliente(c);// Se almacena el cliente logueado
                }

            }
            //Si hay algun error:
            if(!seguir){
                JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrecta");
                C_usuario.setText("");
                C_pass.setText("");
                C_usuario.requestFocus();
            }

         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         //e.printStackTrace(); 
         JOptionPane.showMessageDialog(null,"Se ha producido un error: "+e);
            return false;
      }
      return seguir;
    }
    /**
     * Valida el usuario
     * @return 
     */
    private boolean validarUsuario(){
        
       // Se valida el usuario entrado
        String usu = C_usuario.getText();
        Boolean seguir = false;
        //Si no se ha entrado un usuario 
        if(usu.equals("")){
            JOptionPane.showMessageDialog(null,"No se ha entrado ningún usuario");
            C_usuario.requestFocus();
            return false;
        }
        
       // --- HIBERNATE        
        Transaction tx = null;
        
        try{
             tx = sesion.beginTransaction();

             // Validación del NICK
             SQLQuery q= sesion.createSQLQuery("SELECT * FROM cliente WHERE nick='"+usu+"'");
             q.addEntity(Cliente.class);
             
             List resultados=q.list();
             Iterator iter=resultados.iterator();

             if(resultados.size()==1){// Si ya esta dado de alta el nick entrado => size =1
                while(iter.hasNext()){
                    Cliente c;
                    c=(Cliente)iter.next();
                    setCliente(c);// Se almacena el cliente logueado
                    tx.commit();
                    return true;
                }
             }else if(resultados.isEmpty()){ // Si no existe =>size =0        
                 JOptionPane.showMessageDialog(null,"El usuario no existe");
                 C_usuario.requestFocus();
                 tx.commit();
                 return false;
             }else if(resultados.size()>1){ // Si hay mas de un mismo nick => ERROR         
                 JOptionPane.showMessageDialog(null,"Se ha producido en Error");
                 tx.commit();
                 return false;
             }

             tx.commit();
          }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             JOptionPane.showMessageDialog(null,"Se ha producido un Error: "+e);
                return false;
          }
          return seguir;
    }
    
    public static void setCliente(Cliente c){
        cliente=c;
    }
    public static Cliente getCliente(){
        return cliente;
    }
    private void setSesion(Session s){
        sesion=s;
    }
    public static Session getSesion(){
        return sesion;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        C_usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        B_iniciar = new javax.swing.JButton();
        B_recuperar = new javax.swing.JButton();
        B_salir = new javax.swing.JButton();
        C_pass = new javax.swing.JPasswordField();
        B_registrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario");

        C_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_usuarioActionPerformed(evt);
            }
        });
        C_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                C_usuarioKeyPressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña");

        B_iniciar.setText("Iniciar Sesión");
        B_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_iniciarActionPerformed(evt);
            }
        });
        B_iniciar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B_iniciarKeyPressed(evt);
            }
        });

        B_recuperar.setText("Recuperar Contraseña");
        B_recuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_recuperarActionPerformed(evt);
            }
        });
        B_recuperar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B_recuperarKeyPressed(evt);
            }
        });

        B_salir.setText("Salir");
        B_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_salirActionPerformed(evt);
            }
        });
        B_salir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B_salirKeyPressed(evt);
            }
        });

        C_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_passActionPerformed(evt);
            }
        });
        C_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                C_passKeyPressed(evt);
            }
        });

        B_registrar.setText("Registrarse");
        B_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_registrarActionPerformed(evt);
            }
        });
        B_registrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                B_registrarKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("OCIO PLENO");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(B_recuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(C_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(C_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B_iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(B_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(120, 120, 120))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(C_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(C_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(B_iniciar)
                .addGap(18, 18, 18)
                .addComponent(B_registrar)
                .addGap(18, 18, 18)
                .addComponent(B_recuperar)
                .addGap(18, 18, 18)
                .addComponent(B_salir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_iniciarActionPerformed
        // TODO add your handling code here:
        if(validar()){
            this.dispose();
            Principal  principal = new Principal();//crea la ventana Principal
            principal.setLocationRelativeTo(null); // ventana centrada
            principal.setResizable(false);// No se puede cambiar el tamaño
            principal.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
            principal.setVisible(true);//visualiza la Ventana
        }
        
    }//GEN-LAST:event_B_iniciarActionPerformed

    private void C_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_usuarioActionPerformed
        // TODO add your handling code here:
        C_pass.requestFocus();
    }//GEN-LAST:event_C_usuarioActionPerformed

    private void B_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_salirActionPerformed
        // TODO add your handling code here:
        getSesion().close();
        System.runFinalization();
        System.exit(0);
    }//GEN-LAST:event_B_salirActionPerformed

    private void C_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_usuarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            C_pass.requestFocus();
        }
    }//GEN-LAST:event_C_usuarioKeyPressed

    private void C_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_passActionPerformed
        // TODO add your handling code here:
        B_iniciar.requestFocus();
    }//GEN-LAST:event_C_passActionPerformed

    private void C_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_passKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            B_iniciar.requestFocus();
        }

    }//GEN-LAST:event_C_passKeyPressed

    private void B_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_registrarActionPerformed
        // Abre la ventana de Registro
        this.dispose();
        Registro vRegistro = new Registro();//crea la ventana de registro
        vRegistro.setLocationRelativeTo(null); // ventana centrada
        vRegistro.setResizable(false);// No se puede cambiar el tamaño
        vRegistro.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
        vRegistro.setVisible(true);//visualiza la Ventana
       
    }//GEN-LAST:event_B_registrarActionPerformed

    private void B_iniciarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_iniciarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(validar()){
                this.dispose();
                Principal  principal = new Principal();//crea la ventana Principal
                principal.setLocationRelativeTo(null); // ventana centrada
                principal.setResizable(false);// No se puede cambiar el tamaño
                principal.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
                principal.setVisible(true);//visualiza la Ventana
            }
        }
    }//GEN-LAST:event_B_iniciarKeyPressed

    private void B_recuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_recuperarActionPerformed
        // TODO add your handling code here:
        if(validarUsuario()){
            String pass=getCliente().getPass();
            String email=getCliente().getEmail();
            
            int opcion = JOptionPane.showConfirmDialog(this, "Enviar la contraseña al correo: "+email, 
                                                    "Seleccione una opción", JOptionPane.YES_NO_OPTION);
            if(opcion==0){ // ENVIA EL CORREO
                EnviarCorreo correo = new EnviarCorreo(pass,email);
                setCliente(null);//borra el cliente
            }
   
        }
        C_usuario.setText("");
        C_pass.setText("");
        C_usuario.requestFocus();
    }//GEN-LAST:event_B_recuperarActionPerformed

    private void B_salirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_salirKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            getSesion().close();
            System.runFinalization();
            System.exit(0);
        }
    }//GEN-LAST:event_B_salirKeyPressed

    private void B_recuperarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_recuperarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(validarUsuario()){
                String pass=getCliente().getPass();
                String email=getCliente().getEmail();

                int opcion = JOptionPane.showConfirmDialog(this, "Enviar la contraseña al correo: "+email, 
                                                        "Seleccione una opción", JOptionPane.YES_NO_OPTION);
                if(opcion==0){ // ENVIA EL CORREO
                    EnviarCorreo correo = new EnviarCorreo(pass,email);
                    setCliente(null);//borra el cliente
                }
   
        }
        C_usuario.setText("");
        C_pass.setText("");
        C_usuario.requestFocus();
        }
    }//GEN-LAST:event_B_recuperarKeyPressed

    private void B_registrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_registrarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.dispose();
            Registro vRegistro = new Registro();//crea la ventana de registro
            vRegistro.setLocationRelativeTo(null); // ventana centrada
            vRegistro.setResizable(false);// No se puede cambiar el tamaño
            vRegistro.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
            vRegistro.setVisible(true);//visualiza la Ventana
        }
    }//GEN-LAST:event_B_registrarKeyPressed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_iniciar;
    private javax.swing.JButton B_recuperar;
    private javax.swing.JButton B_registrar;
    private javax.swing.JButton B_salir;
    private javax.swing.JPasswordField C_pass;
    private javax.swing.JTextField C_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
