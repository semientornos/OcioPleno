/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.hibernate.Query;

/**
 *
 * @author gmg
 */
public class Mact extends javax.swing.JDialog {

    /**
     * Creates new form Mact
     */
    private DefaultListModel modelo;
    private DefaultListModel modeloo;
    private DefaultListModel modelooo;
    private DefaultListModel model4;
    Actividad acti;
    public Mact(javax.swing.JDialog parent, boolean modal,int id) 
    {
        super(parent, modal);
        initComponents();
        
        Query q = Login.getSesion().createQuery("from Actividad where ida="+id);
        acti=(Actividad)q.uniqueResult();
 
        titulo.setText(acti.getNombre());
        if(acti.getEdadMinima()!=null){
            emin.setValue((int)acti.getEdadMinima());
        }else{
            emin.setValue(0);
        }
        if(acti.getEdadMaxima()!=null){
            emax.setValue((int)acti.getEdadMaxima());
        }else{
            emax.setValue(200);
        }
        
        lugar.setText(acti.getLugar());
        descripcion.setText(acti.getDescripcion());
        if(acti.getEstado()==0){
            estado.setText("En curso");
            estado.setSelected(false);
        }else if(acti.getEstado()==1){
            estado.setText("Finalizada");
            estado.setSelected(true);
        }
        
    // MEJOR PREGUNTAR SI ES NULL PRIMERO.    
        if (acti.getSexo()==null)
        {
            ra.setSelected(true);
        }
        else if (acti.getSexo()=='M')
        {
            rm.setSelected(true);
        }
        else if (acti.getSexo()=='H')
        {
            rh.setSelected(true);
        } 
        
       
        String hector=acti.getFecha().toString();
        String[] cortado = hector.split(" ");
        
        if(cortado.length>2)
        {
         
                
                
               SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
               Date d1 = null;
               try
               {
                    d1 = sdf3.parse(acti.getFecha().toString());
               }
               catch (Exception e)
               { 
                   e.printStackTrace();
               }
               int puta=0;
               String[] mes = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
               for(int i=0; i< mes.length;i++ )
               {
                   if(mes[i].equals(cortado[1]))
                   {
                       puta=i+1;
                   }
               }

                System.out.println(cortado[2]+" "+puta+" "+cortado[5]);
                
            
                try 
                { 

                    String dateValue=String.valueOf(cortado[5]+"-"+puta+"-"+cortado[2]);//esta es la clave
                    java.util.Date date;
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
                    fecha.setDate(date);


                }   
                catch (ParseException ex) 
                {
                    Logger.getLogger(Mact.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (Exception e)
                {
                    System.out.println("pinga");
                }

            
            
        }
        else
        {  
               
                try 
                { 


                    String dateValue=String.valueOf(acti.getFecha());//esta es la clave

                    java.util.Date date;
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
                    //System.out.println(parsedDate);
                    fecha.setDate(date);


                }   
                catch (ParseException ex) 
                {
                    Logger.getLogger(Mact.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (Exception e)
                {
                    System.out.println("pinga");
                }
        
        }
        
        
        
       
        
        q=Login.getSesion().createQuery("from Participa where ida="+id+" and responsable="+0/*+" and vetado="+0*/);
        
        
        //INCIALIZACIÓN DE LA JLIST
    JScrollPane scrollLista;
//nos indica que nuestra lista tan solo permitirá la selección de un único elemento,
//si quisieramos seleccionar mas de un elemento utilizábamos ListSelectionModel.MULTIPLE_INTERVAL_SELECTION. 
    lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
    
//instanciamos el modelo
    modelo = new DefaultListModel();
    
//instanciamos el Scroll que tendrá la lista 
    scrollLista = new JScrollPane(); scrollLista.setBounds(20, 120,220, 80); 
//añadirmos valores al modelo. 
    
    Participa partc = null; //Antes Valores p=null; por si da problemas
        List <Participa> lista1= q.list();
        Iterator <Participa> iter= lista1.iterator();
        
        
        while(iter.hasNext())
        {
            partc=iter.next();
            modelo.addElement(partc.getCliente().getNick());
            //System.out.println(partc.getCliente().getIdc());
        }
    lista.setModel(modelo);

    
    
    
    
    
    
    
        
        q=Login.getSesion().createQuery("from Aficiones");
        
        
        //INCIALIZACIÓN DE LA JLIST
    JScrollPane scrollLista2;
//nos indica que nuestra lista tan solo permitirá la selección de un único elemento,
//si quisieramos seleccionar mas de un elemento utilizábamos ListSelectionModel.MULTIPLE_INTERVAL_SELECTION. 
    lista2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
    
//instanciamos el modelo
    modeloo = new DefaultListModel();
    
//instanciamos el Scroll que tendrá la lista 
    scrollLista2 = new JScrollPane(); 
    scrollLista2.setBounds(20, 120,220, 80); 
//añadirmos valores al modelo. 
   
    Aficiones afi = null; //Antes Valores p=null; por si da problemas
        List <Aficiones> listaart2= q.list();
        Iterator <Aficiones> iter2= listaart2.iterator();
        
        while(iter2.hasNext())
        {
            afi=iter2.next();
           modeloo.addElement(afi.getAficiones().toString());
           
            //System.out.println(afi.getAficiones().toString());
        }
    lista2.setModel(modeloo);
    
    
    
    
     q=Login.getSesion().createQuery("from Actividad where ida="+id);
        
        
    JScrollPane scrollLista3;

    lista3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

    modelooo = new DefaultListModel();
    scrollLista3 = new JScrollPane(); 
    scrollLista3.setBounds(20, 120,220, 80); 

        Actividad act =(Actividad) q.uniqueResult(); //Antes Valores p=null; por si da problemas
        Iterator <Aficiones> iter3= act.getAficioneses().iterator();
        while(iter3.hasNext()){
           modelooo.addElement(iter3.next().getAficiones().toString()); 
        }
        
    lista3.setModel(modelooo);
    
    
    
    
        
    }
    
    
    public Mact(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        titulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        estado = new javax.swing.JToggleButton();
        emin = new javax.swing.JSpinner();
        emax = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lugar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        rh = new javax.swing.JRadioButton();
        rm = new javax.swing.JRadioButton();
        ra = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista2 = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        lista3 = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        titulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });

        emin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 200, 1));

        emax.setModel(new javax.swing.SpinnerNumberModel(0, 0, 200, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Edad min");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Edad max.");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lugarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lugar");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Descripcion");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        descripcion.setColumns(20);
        descripcion.setRows(5);
        jScrollPane1.setViewportView(descripcion);

        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lista);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Participantes");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(rh);
        rh.setText("Hombre");

        buttonGroup1.add(rm);
        rm.setText("Mujer");

        buttonGroup1.add(ra);
        ra.setText("Ambos");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sexo");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fecha.setDateFormatString("yyyy-MM-dd");

        lista2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lista2);

        lista3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(lista3);

        jButton2.setText("-->");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Guardar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Cerrar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fecha");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Aficiones");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Aficiones de la Actividad");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton7.setText("Eliminar Participante");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Estado");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lugar)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(emin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(emax, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rh)
                                        .addGap(4, 4, 4)
                                        .addComponent(rm)
                                        .addGap(18, 18, 18)
                                        .addComponent(ra))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1)
                    .addComponent(titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rh)
                                .addComponent(rm)
                                .addComponent(ra))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton5))))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String respuesta = JOptionPane.showInputDialog("Escribe Nueva Titulo");
        if(respuesta!=null)
        {
        titulo.setText(respuesta);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lugarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int aux=0;
       for(int i=0;i<modelooo.size();i++){
           if(modelooo.getElementAt(i).equals(lista2.getSelectedValue())){
               aux++;
           }
       } 
        if(aux==0){
            modelooo.addElement(lista2.getSelectedValue());
        }
  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(lista3.getSelectedValue()!=null){
            int index = lista3.getSelectedIndex() ;
        modelooo.remove(index);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed
        if (estado.isSelected())
        {
            estado.setText("Finalizada");
        }
        else
        {
            estado.setText("En curso");
        }
    }//GEN-LAST:event_estadoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // ESTE BOTON GUARDA EN LA BASE DE DATOS LA INFORMACION DEL FRAME.
        org.hibernate.Transaction tran= Login.getSesion().beginTransaction();
        acti.setNombre(titulo.getText());
        
        
        SimpleDateFormat forfecha= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date t=null;
        try
        {
            int año = fecha.getCalendar().get(Calendar.YEAR);
            
            int mes = fecha.getCalendar().get(Calendar.MONTH) + 1;
            int dia = fecha.getCalendar().get(Calendar.DAY_OF_MONTH);
            int hora=fecha.getCalendar().get(Calendar.HOUR);
            int minuto=fecha.getCalendar().get(Calendar.MINUTE);
            int segundo=fecha.getCalendar().get(Calendar.SECOND);
            
           String stFecha=año+"-"+mes+"-"+dia+" "+hora+":"+minuto+":"+segundo;
           try{
                t=forfecha.parse(stFecha);
            }catch(ParseException ex){
                ex.printStackTrace();
            }
           acti.setFecha(t);
        }
        catch(NullPointerException e)
        {
            System.out.println ("pantallazo ");
            
        }
        
        
        if (estado.isSelected())
        {
            acti.setEstado((byte)1);
        }
        else
        {
            acti.setEstado((byte)0);
        }
        acti.setEdadMinima((Integer) emin.getValue());
        acti.setEdadMaxima((Integer) emax.getValue());
        if (rh.isSelected())
        {
            acti.setSexo('H');
        }
        else if (rm.isSelected())
        {
            acti.setSexo('M');
        }
        else if (ra.isSelected())
        {
            acti.setSexo(null);
        }
        acti.setLugar(lugar.getText());
        acti.setDescripcion(descripcion.getText());
        

        Query pepito;
        Query pepi2;
        Set<Aficiones> tu = new HashSet();
        Aficiones poderoso;
        for (int i=0; i<modelooo.size(); i++)//creamos un string con la query que queremos hacer con un auxiliar y luego a;adiendo siempre +or lo q sea y se podria hacer en una consulta pero habria q hacer un iterator para recorrer todos los objetos y asi se podria hacer en 1 consulta pero es una locutra asi q se queda como esta
        {
            pepito=Login.getSesion().createQuery("from Aficiones where aficiones="+"\'"+modelooo.elementAt(i).toString()+"\'");
            poderoso=(Aficiones) pepito.uniqueResult(); 
            tu.add(poderoso); 
        }
        acti.setAficioneses(tu);
        /*
        Cliente primero;
        Participa segundo=null;
        
        for (int i=0; i<modelo.size(); i++)
        {
            pepito=sesion.createQuery("from Cliente where nick="+"\'"+modelo.elementAt(i).toString()+"\'");
            primero=(Cliente) pepito.uniqueResult();
            
            pepi2=sesion.createQuery("from Participa where idc="+primero.getIdc()+" and ida="+acti.getIda());
            segundo=(Participa) pepi2.uniqueResult();
            segundo.setVetado(false);
        }

        for (int i=0; i<model4.size(); i++)
        {
            pepito=sesion.createQuery("from Cliente where nick="+"\'"+model4.elementAt(i).toString()+"\'");
            primero=(Cliente) pepito.uniqueResult();
            
            pepi2=sesion.createQuery("from Participa where idc="+primero.getIdc()+" and ida="+acti.getIda());
            segundo=(Participa) pepi2.uniqueResult();
            segundo.setVetado(true);
        }
        if(model4.size()>0){
            sesion.save(segundo);
        }*/
        

       Login.getSesion().save(acti);
       tran.commit();
       JOptionPane.showMessageDialog(null, "Actividad Modificada");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(lista.getSelectedValue()!=null)
       {

            org.hibernate.Transaction tran= Login.getSesion().beginTransaction();
            Cliente click;
            Query nombre=Login.getSesion().createQuery("from Cliente where nick="+"\'"+lista.getSelectedValue()+"\'");
            click=(Cliente) nombre.uniqueResult();
            Query delete=Login.getSesion().createQuery("delete Participa where idc="+click.getIdc()); 
            int e=delete.executeUpdate();
            tran.commit();
            if(e==1)
            {
                modelo.remove(lista.getSelectedIndex());
            }
       }
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Mact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mact dialog = new Mact(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JSpinner emax;
    private javax.swing.JSpinner emin;
    private javax.swing.JToggleButton estado;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList lista;
    private javax.swing.JList lista2;
    private javax.swing.JList lista3;
    private javax.swing.JTextField lugar;
    private javax.swing.JRadioButton ra;
    private javax.swing.JRadioButton rh;
    private javax.swing.JRadioButton rm;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
