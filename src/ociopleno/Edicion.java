/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class Edicion extends javax.swing.JFrame {
    private final String PATRON_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    private Session sesion;
    private Cliente cliente;
    
    private List <javax.swing.JCheckBox> cajas;
    private List <javax.swing.JLabel> etiquetas;
    public List <Aficiones> aficiones;
    
    private int anno,mesc,diac;// variables del JCalendar
    private boolean fechanacimiento; // Devuelve true si se ha entrado la fecha
    /**
     * CONSTRUCTOR
     * Creates new form AficionesForm
     */
    public Edicion() {
       initComponents();
       
       sesion=Login.getSesion();
       cliente = Login.getCliente();
       
       fechanacimiento=false; 
       cargarDatos();//carga de datos del cliente a editar
        
    }
    
    /**
     * Carga de los datos del cliente
     */
    private void cargarDatos(){
        C_usuario.setText(cliente.getNick());
        C_pass.setText(cliente.getPass());
        C_pass1.setText(cliente.getPass());
        fecha.setDate(cliente.getFnacimiento());
        
        C_lugar.setText(cliente.getLugar());
        C_email.setText(cliente.getEmail());
        
        
        cargaAficiones(); 
        
        // Carga de las aficiones del cliente
        Iterator it  = cliente.getAficioneses().iterator();

        while (it.hasNext()){
            Aficiones aficionescliente = (Aficiones) it.next();
            
            Iterator cursorAficion=aficiones.iterator();
            Iterator cursorCaja=cajas.iterator();
            
            while(cursorCaja.hasNext()){
                Aficiones a=(Aficiones)cursorAficion.next();
                JCheckBox cajita=(JCheckBox)cursorCaja.next();
                
                if(a.equals(aficionescliente)){
                    cajita.setSelected(true);
                }
            }
        }
    }
    /**
     * Método que carga las aficiones en la ventana de registro
     */
    private void cargaAficiones(){
        int ancho = 0; //coordenada x para las aficiones
        String nombre="";// nombre de las aficiones
        
        String q="from Aficiones a order by a.catalogo.catalogo";
        Query query=sesion.createQuery(q);
        aficiones= new ArrayList<>();
       
        for(Object o: query.list()){
            Aficiones a=(Aficiones) o;
            aficiones.add(a);
        }
       
       String anterior=""; // almacenara la etiqueta actual 
       int y=350;//40
       int x=10;//10
       
       cajas= new ArrayList<>();
       etiquetas=new ArrayList<>();
       
       for(Aficiones a: aficiones){
         
           javax.swing.JCheckBox check;
           check= new JCheckBox();//nuevo JCheckbox
           
           //check.setBounds(x(a)+20,y,100,20);// check.setBounds(x(a),y,100,20);//Lo posiciono y le doy 100 de ancho por 20 de alto.
           // Poner en mayúscula la primera letra
           nombre=a.getAficiones();
           nombre=nombre.substring(0, 1).toUpperCase() + nombre.substring(1, nombre.length());
           //ancho = nombre.length();// Ancho para el checkbox
           
           ancho=160;//140
           if(a.getCatalogo().getCatalogo().compareTo(anterior)==0){
               //No hemos cambiado de catálog. Seguimos con el mismo.
               // Imprime CheckBox
               check.setText(nombre);//El texto es el mismo que la actual afición.
               check.setBounds(x(a)+20,y,ancho,20);
               check.setVisible(true);//Lo ponemos visible

               cajas.add(check);//Lo añadimos a la lista de cajas. Imprescindible para recorrerlo.
               this.add(check);//Lo añadimos al JFrame para que se vea.
               y=y+25;  //El proximo item en la misma distancia horizontal y 25 más abajo
           }else{
               //Etiqueta nueva con el valor de catálogo actual
               javax.swing.JLabel e;
               e=new JLabel();
               e.setText(a.getCatalogo().getCatalogo().toUpperCase());
               e.setBounds(x(a)+16, 320, 100, 20);//e.setBounds(x(a), 10, 100, 20);
               e.setVisible(true);
               this.add(e);//Añadimos al JFrame para que se vea 
               etiquetas.add(e);//Añadimos a nuestra colección de etiquetas.
               
               // Imprime CheckBox
               y=350;//40 //Y lo mandamos para arriba.
               check.setBounds(x(a)+20,y,ancho,20);
               check.setText(nombre);//El texto es el mismo que la actual afición.
               check.setVisible(true);//Lo ponemos visible

               cajas.add(check);//Lo añadimos a la lista de cajas. Imprescindible para recorrerlo.
               this.add(check);//Lo añadimos al JFrame para que se vea.
           }
           anterior=a.getCatalogo().getCatalogo();//valor renovado del catálogo actual.
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

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        BG_sexo = new javax.swing.ButtonGroup();
        B_guardar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        C_usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        C_pass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        C_pass1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        R_hombre = new javax.swing.JRadioButton();
        R_mujer = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        C_lugar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        C_email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        B_salir = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        B_guardar.setText("Guardar");
        B_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_guardarActionPerformed(evt);
            }
        });

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

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Repetir Contraseña");

        C_pass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_pass1ActionPerformed(evt);
            }
        });
        C_pass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                C_pass1KeyPressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de Nacimiento");

        fecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaPropertyChange(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sexo");

        BG_sexo.add(R_hombre);
        R_hombre.setSelected(true);
        R_hombre.setText("Hombre");

        BG_sexo.add(R_mujer);
        R_mujer.setText("Mujer");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lugar ");

        C_lugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_lugarActionPerformed(evt);
            }
        });
        C_lugar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                C_lugarKeyPressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email");

        C_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_emailActionPerformed(evt);
            }
        });
        C_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                C_emailKeyPressed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DATOS DEL USUARIO:");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("AFICIONES:");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(C_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(R_hombre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(R_mujer))
                                .addComponent(C_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(C_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(C_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(C_email, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(C_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(C_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(C_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(R_mujer)
                        .addComponent(R_hombre)))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(C_lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(C_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        B_salir.setText("Salir");
        B_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(B_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 312, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_guardar)
                    .addComponent(B_salir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_guardarActionPerformed
        if(validaDatos()){
            this.dispose();
            Principal  principal = new Principal();//crea la ventana Principal
            principal.setLocationRelativeTo(null); // ventana centrada
            principal.setResizable(false);// No se puede cambiar el tamaño
            principal.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
            principal.setVisible(true);//visualiza la Ventana
        }else {
            JOptionPane.showMessageDialog(null,"El Usuario no se ha podido actualizar correctamente");
        }

  
    }//GEN-LAST:event_B_guardarActionPerformed
    
    /**
     * Método que valida los datos entrados
     * @return 
     */
    private boolean validaDatos(){

        String usu = C_usuario.getText();
        Boolean seguir = true;
        
        //Si no se ha entrado un usuario 
        if(usu.equals("")){
            JOptionPane.showMessageDialog(null,"No se ha entrado ningún usuario");
            C_usuario.requestFocus();
            return false;
        }
        //Captura las contraseñas
        char passArray[] = C_pass.getPassword();
        String pwd = new String(passArray); 
        char passArray1[] = C_pass1.getPassword();
        String pwd1 = new String(passArray1); 
        
        //Si no se ha entrado las contraseñas
        if(pwd.equals("") || pwd1.equals("")){
            JOptionPane.showMessageDialog(null,"No se ha entrado la contraseña");
            C_pass.setText("");
            C_pass1.setText("");
            C_pass.requestFocus();
            return false;
        }
        //Si las contraseñas no son iguales 
        if(!pwd.equals(pwd1)){
            JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden");
            C_pass.setText("");
            C_pass1.setText("");
            C_pass.requestFocus();
            return false;
        }
        // Comprueba la fecha de nacimiento
        if(!validaFechaNacimiento()){
            return false;
        }
        // Comprueba que se ha entrado el lugar
        if(C_lugar.equals("")){
            JOptionPane.showMessageDialog(null,"No se ha entrado ningún lugar");
            C_lugar.requestFocus();
            return false;
        }
        // Comprueba que se ha entrado el email y que es correcto
        if(C_email.equals("")){
            JOptionPane.showMessageDialog(null,"No se ha entrado ningún email");
            C_email.requestFocus();
            return false;
        }else if(!validarEmail(C_email.getText())){
            JOptionPane.showMessageDialog(null,"El email entrado es erróneo");
            C_email.requestFocus();
            return false;
        }
        
        
        
        // Comprueba que se ha seleccionado como mínimo una aficion
        //Recorremos las dos listas de manera sincronizada, Usaré dos cursores.
        Iterator cursorAficion, cursorCaja;
        cursorAficion=aficiones.iterator();
        cursorCaja=cajas.iterator();
        int cont =0; // contador de aficiones seleccionadas
        while(cursorCaja.hasNext()){
            Aficiones a=(Aficiones)cursorAficion.next();
            JCheckBox cajita=(JCheckBox)cursorCaja.next();
            //si la afición está seleccionada incrementa el contador
            if(cajita.isSelected())cont++;
        }
        if(cont==0){
            JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna afición");
            return false;
        }
        
        // Validación del NICK si se ha modificado
        if(!usu.equals(cliente.getNick())){
            // --- HIBERNATE        
            Transaction tx = null;

            try{
                 tx = sesion.beginTransaction();
                 SQLQuery q= sesion.createSQLQuery("SELECT * FROM cliente WHERE nick='"+usu+"'");
                 q.addEntity(Cliente.class);

                 List resultados=q.list();
                 // Si hay mas de un mismo nick => ERROR
                 if(resultados.size()>1){
                     JOptionPane.showMessageDialog(null,"Se ha producido en Error");
                     seguir= false;
                 // Si ya esta dado de alta el nick entrado => size =1
                 }else if(resultados.size()==1){
                     JOptionPane.showMessageDialog(null,"Nombre de Usuario existente");
                     C_usuario.requestFocus();
                     seguir= false;
                 }

                 tx.commit();
              }catch (HibernateException e) {
                 if (tx!=null) tx.rollback();
                 //e.printStackTrace(); 
                 JOptionPane.showMessageDialog(null,"Se ha producido un error: "+e);
                    return false;
              }
        }
        if(seguir){// Si todo ha ido bien crea Cliente y retorna true 
            if(actualizaCliente(pwd)){
                JOptionPane.showMessageDialog(null,"Usuario actualizado correctamente");
                return true;
            }
        }
        return seguir;
    }
    /**
     * Método que carga los datos entrados y crea un nuevo usuario
     * @param pwd
     * @return 
     */
    private boolean actualizaCliente(String pwd){
        Cliente c;
        // --- HIBERNATE        
        Transaction tx = null;
        
        //Calcula el idc máximo de los dados de alta
        int id=0; 
        try{
             tx = sesion.beginTransaction();

              //Carga de datos
              cliente.setNick(C_usuario.getText());
              cliente.setPass(pwd);

              cliente.setLugar(C_lugar.getText());
              cliente.setEmail(C_email.getText());

              //Almacena la fecha de nacimiento si se ha modificado
              if(fechanacimiento){
                    if(validaFechaNacimiento()){
                        Calendar d= Calendar.getInstance();
                        d.set(anno,mesc, diac);      
                        cliente.setFnacimiento(d.getTime());
                    }
              }
              // Elección del sexo
              if(R_hombre.isSelected())cliente.setSexo('H');
              else if(R_mujer.isSelected())cliente.setSexo('M');

              //Carga de las Aficiones seleccionadas
              //Recorremos las dos listas de manera sincronizada, Usaré dos cursores.
            
              
           cliente.getAficioneses().clear(); // Reinicia las Aficiones
            
              
              
            Iterator cursorAficion=aficiones.iterator();
            Iterator cursorCaja=cajas.iterator();

            while(cursorCaja.hasNext()){
                Aficiones a=(Aficiones)cursorAficion.next();
                JCheckBox cajita=(JCheckBox)cursorCaja.next();
                //si la afición está seleccionada y no esta inluida en las aficiones del cliente pasa al cliente c
                if(cajita.isSelected() && (!cliente.getAficioneses().contains(a)))
                    cliente.getAficioneses().add(a);
            }
            //Grabamos el cliente
            //org.hibernate.Transaction t=sesion.beginTransaction();
            sesion.update(cliente);
            tx.commit();
        }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             JOptionPane.showMessageDialog(null,"Se ha producido un error");
                return false;
        }
        return true;
    }
    
    // Métogo que asigna la coordenada x de cada columna de Aficiones
    public int x(Aficiones a){
        int r=35;//r=10
        if(a.getCatalogo().getCatalogo().compareTo("deportivas")==0)r=200;//r=175
        if(a.getCatalogo().getCatalogo().compareTo("otras")==0)r=360;//r=330
        return r;
    }
    /**
     * METODO QUE VALIDA EL EMAIL 
     */
    private boolean validarEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATRON_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
     /**
     * METODO QUE DEVUELVE TRUE SI SE HA ENTRADO FECHA DE NACIMIENTO VALIDA
     */
    private boolean validaFechaNacimiento(){
        Date dateSistema=new Date();//Instancia la fecha del sistema
        
        //Recojo la fecha del componente JDateChoose
        try{
            Date date = fecha.getDate();
        
            if(date.getTime()>= dateSistema.getTime()){//Compara si la fecha seleccionada es mayor o igual a la fecha actual
                JOptionPane.showMessageDialog(null,"Error: la fecha seleccionada es mayor o igual a la fecha actual");
                return false;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Entrar una fecha válida");
            return false;
        }
        return true;
    }
    
    private void C_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_usuarioActionPerformed
        // TODO add your handling code here:
        C_pass.requestFocus();
    }//GEN-LAST:event_C_usuarioActionPerformed

    private void C_usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_usuarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
           C_pass.requestFocus();
    }//GEN-LAST:event_C_usuarioKeyPressed

    private void C_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_passActionPerformed
        // TODO add your handling code here:
        C_pass1.requestFocus();
    }//GEN-LAST:event_C_passActionPerformed

    private void C_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_passKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
           C_pass1.requestFocus();
    }//GEN-LAST:event_C_passKeyPressed

    private void C_pass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_pass1ActionPerformed
        // TODO add your handling code here:
        fecha.requestFocus();
    }//GEN-LAST:event_C_pass1ActionPerformed

    private void C_pass1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_pass1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
           fecha.requestFocus();
    }//GEN-LAST:event_C_pass1KeyPressed

    private void fechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaPropertyChange
        // TODO add your handling code here:
        //Si se han producido cambios en la fecha:
        
        if ("date".equals(evt.getPropertyName())) {
            fechanacimiento=false;

            //Recojo la fecha del componente JDateChoose
            Date date = fecha.getDate();
   
                //Convierto la fecha a un formato determinado
                DateFormat dateaconvertir = new SimpleDateFormat("dd/MM/yyyy");
                String convertido = dateaconvertir.format(date);
                //Muestro la fecha en una etiqueta fecha completa
                convertido = dateaconvertir.format(date);
                
                if(!convertido.isEmpty()) fechanacimiento=true;// Si se ha entrado una fecha 
                //Muestro la fecha en una etiqueta fecha completa
                //etiquetafecha.setText(convertido);
                //Obtengo el día, mes y año por separado

                anno = fecha.getCalendar().get(Calendar.YEAR);
                //mesc = fecha.getCalendar().get(Calendar.MONTH) + 1;
                mesc = fecha.getCalendar().get(Calendar.MONTH);
                diac = fecha.getCalendar().get(Calendar.DAY_OF_MONTH);
                //Lo muestro en las etiquetas.
                /*
                etiquetadia.setText(Integer.toString(diac));
                etiquetames.setText(Integer.toString(mesc));
                etiquetaano.setText(Integer.toString(anno));
                */
            
        }
    }//GEN-LAST:event_fechaPropertyChange

    private void C_lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_lugarActionPerformed
        // TODO add your handling code here:
        C_email.requestFocus();
    }//GEN-LAST:event_C_lugarActionPerformed

    private void C_lugarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_lugarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
           C_email.requestFocus();
    }//GEN-LAST:event_C_lugarKeyPressed

    private void C_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_emailActionPerformed

    private void C_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_C_emailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_C_emailKeyPressed

    private void B_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal  principal = new Principal();//crea la ventana Principal
        principal.setLocationRelativeTo(null); // ventana centrada
        principal.setResizable(false);// No se puede cambiar el tamaño
        principal.setDefaultCloseOperation(0);//Evita que se cierre la ventana con X
        principal.setVisible(true);//visualiza la Ventana
    }//GEN-LAST:event_B_salirActionPerformed

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
            java.util.logging.Logger.getLogger(Edicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Edicion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BG_sexo;
    private javax.swing.JButton B_guardar;
    private javax.swing.JButton B_salir;
    private javax.swing.JTextField C_email;
    private javax.swing.JTextField C_lugar;
    private javax.swing.JPasswordField C_pass;
    private javax.swing.JPasswordField C_pass1;
    private javax.swing.JTextField C_usuario;
    private javax.swing.JRadioButton R_hombre;
    private javax.swing.JRadioButton R_mujer;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}

