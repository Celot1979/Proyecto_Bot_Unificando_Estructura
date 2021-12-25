package Bot_Estructurado;

public class Bot_Cliente_Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		marco m = new marco();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

}
class marco extends JFrame {
	public marco() {
		
        //Instrucción para que no sepueda redimensionar el JFrame(marco)
        this.setResizable(false);

		setBounds(400,400,400,600);
		//JPanel primaria
		JPanel lamina = new JPanel();
		
		
		//Componentes que formaran parte de la estructura del bot
		  // Área de texto
		contenido = new JTextArea(20,30);
		JScrollPane laminaScrollCliente = new JScrollPane(contenido);
		contenido.setLineWrap(true);
		
		  //Campo de texto
		Texto = new JTextField(30);
		
		
			//Etiqueta
		Icono = new JLabel("DEVIL SHEEP a la escucha .......                     ");
		Icono.setIcon(new ImageIcon("/Users/danielgil/Desktop/Curso_Java/Bot_Unica_Estructura/src/devil.png"));
		Icono.setFont(new Font("Marker Felt", Font.BOLD, 16));
		
			//Botón
		Enviar = new JButton("Enviar");
		Enviar.setBackground(new Color(255,102,102));
		Enviar.setOpaque(true);
		Enviar.setBorderPainted(false);
		Enviar.setForeground(Color.BLUE);
		//---------------------------------------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------------------------------------
		//----------------------------------------------- USO MÉTODO ACTIONLISTENER (botón) -----------------------------------------------------------------
		//----------------------------------------- SE IMPLEMENTA CÓDIGO PARA ENVIAR INFO -------------------------------------------------------------------
		//--------------------------------------------------- (CLIENTE - SERVIDOR )--------------------------------------------------------------------------
		Enviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*
				 * Instanciamos el Hilo de comunicación entre el cliente - Servidor(
				 * enviará la elección del usuario al servidor)
				 */
				new Thread(new hilo_Envio_Info_Cliente_Servidor ("192.168.0.7",9992)).start();
				
				
				
			}
			
		});
			
		
		
		/*
		 * Añadimos componentes a al marco general( Consultar que instrucciones implementar para 
		 * no dejar que la ventana emergente se pueda modificar su tamaño.
		 */
		lamina.add(Icono);
		lamina.add(laminaScrollCliente);
		lamina.add(Texto);
		lamina.add(Enviar);
		

		//Añadir lámina-principal al Marco
		add(lamina);
		
		setVisible(true);
	
		//---------------------------------------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------------------------------------
		//----------------------------------------------- INSTANCIACIÓN DE LOS HILOS ------------------------------------------------------------------------
		//----------------------------------------- Especial atención al nombre de cada hilo ----------------------------------------------------------------
		//-------------------------------( Se intentará ser lo más descriptible para su mayor comprensión )--------------------------------------------------
	
		new Thread(new hilo_Enviar_Info_Servidor("192.168.0.7",9991)).start();
		new Thread(new hilo_Escuchar_Info_Inicio_Cliente(9991)).start();
		new Thread(new hilo_Escucha_Info_Servidor_Cliente("192.168.0.7",9992)).start();
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------- HILO CONSTRUIDO PARA ENVIAR -----------------------------------------------------------------------
	//--------------------------------------------TEXTO INICIAL ( SERVIDOR - CLIENTE)--------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------------------------------
	
	public class hilo_Enviar_Info_Servidor implements Runnable{
		

		public hilo_Enviar_Info_Servidor(String iP, int puerto_Inicial) {
			IP = iP;
			Puerto_Inicial = puerto_Inicial;
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Socket_Inicio Inicio1 = new Socket_Inicio(IP,9991);
			
		}
		
		class Socket_Inicio{
			public Socket_Inicio(String Ip, int puerto) {
				try {
					mensaje_inicial = new JLabel("Hola! Soy Devil Sheep !!!!  " + "\n" +  "\n" + "En qué podemos ayudarte?" + "\n" +  "\n" + " 1. Problemas con el pedido " +  "\n" + " 2. Información de devoluciones " + "\n" + " 3. Puntos de recogida "  + "\n" + " 4. Ninguna de las opciones ");
					Socket socket_envio_Info = new Socket(Ip,puerto);
					DataOutputStream flujoSalida1= new DataOutputStream (socket_envio_Info.getOutputStream());
					flujoSalida1.writeUTF(mensaje_inicial.getText());
					flujoSalida1.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		String IP = "192.168.0.7";
		int Puerto_Inicial;
		
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------- HILO CONSTRUIDO PARA ESCUCHAR -----------------------------------------------------------------------
	//--------------------------------------------- TEXTO INICIAL (CLIENTE - SERVIDOR ) --------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------
    public class hilo_Escuchar_Info_Inicio_Cliente implements Runnable{
		

		public hilo_Escuchar_Info_Inicio_Cliente(int puerto_Inicial) {
			Puerto_Inicial = puerto_Inicial;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Escucha_cliente Escuchar_Cliente_1 = new Escucha_cliente(9991);
			
		}
		
		class Escucha_cliente{
			public Escucha_cliente(int puerto_Inicial) {
				while(true) {
					try {
						//Se queda a la espera de la llegada de la info primaría del Servidor
						ServerSocket socket_entrada_Info = new ServerSocket(puerto_Inicial);
						while(true) {
		                //Acepta la conexión con el Servidor
						Socket miSocket =  socket_entrada_Info.accept();
						//Crea el flujo de datos que vienen del servidor
						DataInputStream flujoEntrada1 = new DataInputStream(miSocket.getInputStream());
						//Implementa en una variable la información que viene del Servidor
						String menajeTexto1 = flujoEntrada1.readUTF();
		
						//Se añade el contenido de la info que viene del servidor
						contenido.append( menajeTexto1);
						socket_entrada_Info.close();
						
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("");
					}
				}
			}
		}
		
		String IP = "192.168.0.7";
		int Puerto_Inicial;
		
	}
  //---------------------------------------------------------------------------------------------------------------------------------------------------
  //---------------------------------------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------- HILO CONSTRUIDO PARA ENVIAR -----------------------------------------------------------------------
  //--------------------------------------------- INFORMACIÓN  (CLIENTE - SERVIDOR ) ------------------------------------------------------------------
  //------------------------------------------- EXISTEN 4 OPCIONES Y SE LE ENVÍA LA INFO --------------------------------------------------------------
    
    public class hilo_Envio_Info_Cliente_Servidor implements Runnable{
    	
		public hilo_Envio_Info_Cliente_Servidor(String iP, int puerto_Inicial) {
			IP = iP;
			Puerto_Inicial = puerto_Inicial;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 try {
				Envio_Info_Cliente Enviar_Info = new  Envio_Info_Cliente(IP,Puerto_Inicial);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		class Envio_Info_Cliente{
			public Envio_Info_Cliente(String Ip, int Puerto) throws UnknownHostException, IOException {
				Socket Socket_Envio_Info_Cliente = new Socket(IP ,Puerto_Inicial);
				Informacion_Cliente datos = new Informacion_Cliente();
				datos.setOpcion(Texto.getText());
				datos.setUsuario(JOptionPane.showInputDialog("Usuario: "));
				datos.setN_pedido(JOptionPane.showInputDialog("Nº Pedido: "));
				
				// Su el usario escoge la opción 1º, se incializa esté condicional.
				if(datos.getOpcion().equals("1")) {
					datos.setMensaje("Opción1");
					ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(Socket_Envio_Info_Cliente.getOutputStream());
					flujosSalidaPaquete.writeObject(datos);
					Texto.setText("");
					Socket_Envio_Info_Cliente.close();
				}else if(datos.getOpcion().equals("2")) {
					datos.setMensaje("Opción2");
					ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(Socket_Envio_Info_Cliente.getOutputStream());
					flujosSalidaPaquete.writeObject(datos);
					Texto.setText("Opción1");
					Socket_Envio_Info_Cliente.close();
				}else if(datos.getOpcion().equals("3")) {
					datos.setMensaje("Opción3");
					ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(Socket_Envio_Info_Cliente.getOutputStream());
					flujosSalidaPaquete.writeObject(datos);
					Texto.setText(" ");
					Socket_Envio_Info_Cliente.close();
				}else if (datos.getOpcion().equals("4")) {
					datos.setMensaje(JOptionPane.showInputDialog("Explicanos tú problema"));
					ObjectOutputStream flujosSalidaPaquete = new ObjectOutputStream(Socket_Envio_Info_Cliente.getOutputStream());
					flujosSalidaPaquete.writeObject(datos);
					Texto.setText(" ");
					Socket_Envio_Info_Cliente.close();
				}
				
		  }
		}
		String IP = "192.168.0.7";
		int Puerto_Inicial;
    }
    
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------- HILO CONSTRUIDO PARA ESCUCHAR -----------------------------------------------------------------------
    //--------------------------------------------- INFORMACIÓN  (SERVIDOR - CLIENTE ) ------------------------------------------------------------------
    //------------------------------------------- EXISTEN 4 OPCIONES Y SE LE ENVÍA LA INFO --------------------------------------------------------------
    public class hilo_Escucha_Info_Servidor_Cliente implements Runnable{
    	
    	
    	
    	public hilo_Escucha_Info_Servidor_Cliente(String iP, int puerto_Inicial) {
			IP = iP;
			Puerto_Inicial = puerto_Inicial;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Escucha_Servidor escucha_El_Servidor = new Escucha_Servidor (Puerto_Inicial);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	class Escucha_Servidor{
    		public Escucha_Servidor(int puerto) throws IOException, ClassNotFoundException {
    			ServerSocket Servidor_Escucha = new ServerSocket(puerto);
    			String Opcion_Usu, Loguear, n_Pedido, mensaje_del_cliente;
    			Informacion_Cliente datos_paquete;
    			while(true) {
    				Socket miSocket = Servidor_Escucha.accept();
    				ObjectInputStream flujoDatosEntrada = new ObjectInputStream( miSocket.getInputStream());
    				datos_paquete = (Informacion_Cliente) flujoDatosEntrada.readObject();
					Opcion_Usu = datos_paquete.getOpcion();
					Loguear = datos_paquete.getUsuario();
					n_Pedido = datos_paquete.getN_pedido();
					mensaje_del_cliente = datos_paquete.getMensaje();
					System.out.print("\n" + Opcion_Usu + "\n" + Loguear + "\n" + n_Pedido + "\n" + mensaje_del_cliente  + "\n" + "Ha llegado hasta Que el servidor Escuche la elección del cliente");
					miSocket.close();
    			}
    		}
    	}
    	String IP = "192.168.0.7";
		int Puerto_Inicial;
		
    }
    
    
  //---------------------------------------------------------------------------------------------------------------------------------------------------
  //---------------------------------------------------------------------------------------------------------------------------------------------------
  //--------------------------------------------------- CLASE QUE SERIALIZA DATOS ENVIADOS POR EL CLIENTE ---------------------------------------------
  //--------------------------------------------- EL SOCKET ENVIARÁ LA INFORMACIÓN MEDIANTE EL ACTIONLISTENER DEL BOTÓN -------------------------------
  //---------------------------------------------------------------------------------------------------------------------------------------------------

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



	
	private JTextArea contenido;
	private JTextField Texto;
	private JButton Enviar;
	private JLabel Icono,mensaje_inicial;
	private String Contenido_Texto,Opcion;
	
	

}

