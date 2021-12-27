package Destructuracion_Fallo;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Ingeneria_Inversa_Fallo {

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
	}
	private JTextArea contenido;
	private JTextField Texto;
	private JButton Enviar;
	private JLabel Icono,mensaje_inicial;
	private String Contenido_Texto,Opcion;
}

			
		
