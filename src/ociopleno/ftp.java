/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ociopleno;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author gmg
 */
public class ftp 
{    
    String IP = "files.000webhost.com";
    int PUERTO = 21;
    String USUARIO = "testclasedam";
    String CONTRASENA = "1qa2ws3ed4rf";

    public ftp() 
    {
    
    }
    
    
    public boolean descargar(String remoto,String local)
    {
        
    FTPClient clienteFtp = null;
	
		try 
                {
			clienteFtp = new FTPClient();
			clienteFtp.connect(IP, PUERTO);
			clienteFtp.login(USUARIO, CONTRASENA);
			
                        
			clienteFtp.enterLocalPassiveMode();
			clienteFtp.setFileType(FTPClient.BINARY_FILE_TYPE);

                        //Fichero remoto y local
                        String ficheroRemoto =remoto;
                        
                        File ficheroLocal = new File(local + File.separator + remoto);
	
                        
			// Descarga un fichero del servidor FTP
			OutputStream os = new BufferedOutputStream(new FileOutputStream(ficheroLocal));
			
                        if (clienteFtp.retrieveFile(ficheroRemoto, os))
                        {
				//System.out.println("El fichero se ha Descargado correctamente");			
                                os.close();
                                return true;
                        }
                        else
                        {
                           // System.out.println("El fichero NO se ha Descargado correctamente");
                            return false;
                        }
			
		}
                catch (IOException ioe) 
                {
			ioe.printStackTrace();
                         return false;
		}
                finally
                {
			
			if (clienteFtp != null)
                        {
				try
                                {
					
					if (clienteFtp.isConnected())
                                        {
						clienteFtp.logout();
						clienteFtp.disconnect();
					}
				}
                                catch (IOException ioe)
                                {
					ioe.printStackTrace();
				}
                        }
                }
                
    }
    
    
    public boolean subir(String remoto,String local)
    {
        
        
         FTPClient clienteFtp = null;
	
		try 
                {
			
			clienteFtp = new FTPClient();
			clienteFtp.connect(IP, PUERTO);
			clienteFtp.login(USUARIO, CONTRASENA);
			
			clienteFtp.enterLocalPassiveMode();
			clienteFtp.setFileType(FTPClient.BINARY_FILE_TYPE);
			

                        //Fichero remoto y local
			String ficheroRemoto = remoto;
			File ficheroLocal = new File(local);
			
			
			//subir un fichero al servidor FTP
			InputStream os =new BufferedInputStream(new FileInputStream(ficheroLocal));
                        
			
                        if (clienteFtp.storeFile(ficheroRemoto, os))
                        {
				//JOptionPane.showMessageDialog(null, "Archivo Subido");			
                                os.close();
                                return true;
                        }
                        else
                        {
                                //JOptionPane.showMessageDialog(null, "Archivo NO Subido");
                                return false;
                        }

			
		}
                catch (IOException ioe) 
                {
			
                        return false;
		}
                finally
                {
		
			if (clienteFtp != null)
                        {
				try
                                {
					
					if (clienteFtp.isConnected())
                                        {
						clienteFtp.logout();
						clienteFtp.disconnect();
					}
				}
                                catch (IOException ioe)
                                {
					ioe.printStackTrace();
				}
                        }
		}
        
    }
    
    
    public int comprobar(String nombrefichero)
    {
        FTPClient clienteFtp = null;
        String nombre="";
        int res=0;
	
		try 
                {
			
                        clienteFtp = new FTPClient();
			clienteFtp.connect(IP, PUERTO);
			clienteFtp.login(USUARIO, CONTRASENA);
			
			clienteFtp.enterLocalPassiveMode();
			clienteFtp.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			// Lista el directorio del servidor FTP
                        
			
			FTPFile[] ficheros = clienteFtp.listFiles();
                        
			for (int i = 0; i < ficheros.length; i++) 
                        {
                            nombre=ficheros[i].getName();
                            
                            if(nombre.equals(nombrefichero))
                            {
                                i=ficheros.length;
                                res++;
                            }
			}
			
			




			
		}
                catch (IOException ioe) 
                {
			ioe.printStackTrace();
                        res=1;
		}
                finally
                {
			
			if (clienteFtp != null)
                        {
				try
                                {
					
					if (clienteFtp.isConnected())
                                        {
						clienteFtp.logout();
						clienteFtp.disconnect();
					}
				}
                                catch (IOException ioe)
                                {
					ioe.printStackTrace();
				}
                        }
		}
        
      //si el metodo nos devuelve  superior a 0 significa que hay un fichero con el mismo nombre en el servidor remoto
      
      return res;
    }
    
    
    public int eliminar(String nombrefichero)
    {
    
        FTPClient clienteFtp = null;
        int res=0;
	
		try 
                {
			
                        clienteFtp = new FTPClient();
			clienteFtp.connect(IP, PUERTO);
			clienteFtp.login(USUARIO, CONTRASENA);
			
			clienteFtp.enterLocalPassiveMode();
			clienteFtp.setFileType(FTPClient.BINARY_FILE_TYPE);
			
                        
			
			clienteFtp.deleteFile(nombrefichero);
			




			
		}
                catch (IOException ioe) 
                {
			ioe.printStackTrace();
                        res=1;
		}
                finally
                {
			
			if (clienteFtp != null)
                        {
				try
                                {
					
					if (clienteFtp.isConnected())
                                        {
						clienteFtp.logout();
						clienteFtp.disconnect();
					}
				}
                                catch (IOException ioe)
                                {
					ioe.printStackTrace();
				}
                        }
		}
                
        return res;   
    }
    
    
    
    
    
    
    
}
