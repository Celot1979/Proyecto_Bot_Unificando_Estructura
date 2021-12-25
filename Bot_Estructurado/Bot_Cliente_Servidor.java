package Bot_Estructurado;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class Cliente_II {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		marco m = new marco();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
class marco extends JFrame{
	public marco() {
		setBounds(400,400,400,600);
		add(new lamina());
		setVisible(true);
	}
}

class lamina extends JPanel implements Runnable{
	lamina(){
		setLayout(new BorderLayout());
		//Creación de la subPanel
		JPanel superior = new JPanel();
		JPanel centro = new JPanel();
		JPanel inferior= new JPanel();
		// Declaración de los componentes
		contenido = new JTextArea(20,30);
		JScrollPane laminaScrollCliente = new JScrollPane(contenido);
		contenido.setLineWrap(true);
		
		Texto = new JTextField(30);
		
		Icono = new JLabel("DEVIL SHEEP a la escucha .......                     ");
		Icono.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/Bot/src/Bot/devil.png"));
		Icono.setFont(new Font("Marker Felt", Font.BOLD, 16));
		
		Enviar = new JButton("Enviar");
		Enviar.setBackground(new Color(255,102,102));
		Enviar.setOpaque(true);
		Enviar.setBorderPainted(false);
		Enviar.setForeground(Color.BLUE);
		Enviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// Se inicia  un socket para enviar la info que nos aporta el usuario
					Socket mi_socket = new Socket("192.168.0.7" , 9993);
					// Instacia el objeto que formará el objeto que creará el paquete 
					Informacion_Cliente datos = new Informacion_Cliente();
					datos.setOpcion(Texto.getText());
					datos.setUsuario(JOptionPane.showInputDialog("Usuario: "));
					datos.setN_pedido(JOptionPane.showInputDialog("Nº Pedido: "));
					
					// Su el usario escoge la opción 1º, se incializa esté condicional.
					if(datos.getOpcion().equals("1")) {
						ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(mi_socket.getOutputStream());
						flujosSalidaPaquete.writeObject(datos);
						Texto.setText("");
						mi_socket.close();
					}else if(datos.getOpcion().equals("2")) {
						ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(mi_socket.getOutputStream());
						flujosSalidaPaquete.writeObject(datos);
						Texto.setText("");
						mi_socket.close();
					}else if(datos.getOpcion().equals("3")) {
						ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(mi_socket.getOutputStream());
						flujosSalidaPaquete.writeObject(datos);
						Texto.setText("");
						mi_socket.close();
					}else if (datos.getOpcion().equals("4")) {
						datos.setMensaje(JOptionPane.showInputDialog("Explicanos tú problema"));
						ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(mi_socket.getOutputStream());
						flujosSalidaPaquete.writeObject(datos);
						Texto.setText("");
						mi_socket.close();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
			
			
		});
		centro.add(laminaScrollCliente);
		centro.add(Texto);
		inferior.add(Enviar);
		superior.add(Icono);
		
		
		add(superior, BorderLayout.NORTH);
		add(centro, BorderLayout.CENTER);
		add(inferior, BorderLayout.SOUTH);
		Thread mihilo = new Thread(this);
		mihilo.start();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Bucle que inicia el Bot con la información que llega del Servidor.
        Escucha_cliente uno = new Escucha_cliente(9998);
		Escucha_cliente dos = new Escucha_cliente(9993);
		
		
	}
	class Escucha_cliente{
		public Escucha_cliente(int puerto) {
			while(true) {
				try {
					//Se queda a la espera de la llegada de la info primaría del Servidor
					ServerSocket socket_entrada_Info = new ServerSocket(puerto);
					while(true) {
	                //Acepta la conexión con el Servidor
					Socket miSocket =  socket_entrada_Info.accept();
					//Crea el flujo de datos que vienen del servidor
					DataInputStream flujoEntrada1 = new DataInputStream(miSocket.getInputStream());
					//Implementa en una variable la información que viene del Servidor
					String menajeTexto1 = flujoEntrada1.readUTF();
	
					//Se añade el contenido de la info que viene del servidor
					contenido.append( menajeTexto1);
					String leer = contenido.getText();
					System.out.print(leer);
					/*VoiceManager manager = VoiceManager.getInstance();
					Voice voz = manager.getVoice("Kevin16");
					voz.allocate();
					voz.speak(leer);
					voz.deallocate();*/
					socket_entrada_Info.close();
					
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("");
				}
			}
		}
	}
	
	private JTextArea contenido;
	private JTextField Texto;
	private JButton Enviar;
	private JLabel Icono;
	private String Contenido_Texto,Opcion;
	
}
//Clase que serializa los datos que se enviarán en un paquete
//El Socket que envia la infor mediante el botón.
class Informacion_Cliente implements Serializable{
	

public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	

public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	


public String getN_pedido() {
		return n_pedido;
	}

	public void setN_pedido(String n_pedido) {
		this.n_pedido = n_pedido;
	}




public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}




private String opcion,usuario,n_pedido,mensaje;
	
}


