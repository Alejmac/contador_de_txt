package Java;
//Importamos las librerias que usaremos
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

//Inicia la clase
public class Interfaz1 extends JFrame implements ActionListener {

//Declaración de variables
    public static Interfaz1 obj;
    int cont1, cont2, cont3, cont4;
    JButton buscar, limpiar, guardar;
    Container cont = getContentPane();
    JLabel eti, pal, num, lin;
    Color azul;
    JTextField ruta;
    String rut, palabra, deli;
    JTextArea area, areaPal;
    JScrollPane sc, scPal;
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    JMenuBar menu;
    JMenu arch;
    JMenuItem version; 
    JMenuItem salir;


//Inicia el constructor

    public Interfaz1() {

//Propiedades del formulario
        setSize(850, 720);
        setTitle("Aplicación");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        azul = new Color(193, 132, 255);
        cont.setBackground( azul);
        menu = new JMenuBar();
        menu.setBackground(Color.white);
        arch = new JMenu("Archivo");
        version = new JMenuItem("Versión");
        salir = new JMenuItem("Salir");

//Inicilizacion del menu
        setJMenuBar(menu);
        menu.add(arch);
        salir.addActionListener(this);
        salir.setBackground(Color.red);
        version.addActionListener(this);
        version.setBackground(Color.white);
        arch.add(version);
        arch.add(salir);

//Inicializacion de un JLabel
        eti = new JLabel("<html>Escribe la Ruta con el nombre del texto que deseas leer.<html>");
        eti.setBounds(30, 10, 290, 60);
        eti.setFont(new java.awt.Font("Tahoma", 0, 14));
        cont.add(eti);

//Inicializacion de un JTextField
        ruta = new JTextField();
        ruta.setBounds(30, 70, 280, 30);
        ruta.setFont(new java.awt.Font("Tahoma", 0, 14));
        cont.add(ruta);

//Inicializacion de un JButton
        buscar = new JButton("Elegir Txt");
        buscar.setBounds(120, 110, 100, 30);
        buscar.setFont(new java.awt.Font("Tahoma", 0, 14));
        buscar.addActionListener(this);
        cont.add(buscar);

//Inicializacion de un JButton
        limpiar = new JButton("Limpiar");
        limpiar.setBounds(640, 630, 80, 30);
        limpiar.setFont(new java.awt.Font("Tahoma", 0, 14));
        limpiar.addActionListener(this);
        cont.add(limpiar);
//Inicializacion de un JButton
        guardar = new JButton("Guardar");
        guardar.setBounds(220, 630, 90, 30);
        guardar.setFont(new java.awt.Font("Tahoma", 0, 14));
        guardar.addActionListener(this);
        cont.add(guardar);
//Inicializacion de un JLabel
        pal = new JLabel("Cantidad de palabras: ");
        pal.setBounds(30, 145, 200, 30);
        pal.setFont(new java.awt.Font("Tahoma", 0, 14));
        cont.add(pal);
//Inicializacion de un JLabel
        lin = new JLabel("Lineas del documento: ");
        lin.setBounds(30, 195, 300, 30);
        lin.setFont(new java.awt.Font("Tahoma", 0, 14));
        cont.add(lin);
//Inicializacion de un JLabel
        num = new JLabel("Cantidad de numeros: ");
        num.setBounds(30, 170, 200, 30);
        num.setFont(new java.awt.Font("Tahoma", 0, 14));
        cont.add(num);
        
 // Nuevo JLabel
JLabel otroLabel = new JLabel("Otro JLabel");
otroLabel.setBounds(30, 220, 200, 30);
otroLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
cont.add(otroLabel);

// Nuevo JLabel 2
JLabel otroLabel2 = new JLabel("dame el tiempo ");
otroLabel2.setBounds(30, 250, 200, 30);
otroLabel2.setFont(new java.awt.Font("Tahoma", 0, 14));
cont.add(otroLabel2);
        
//Inicializacion de un JTextArea
        area = new JTextArea("");
        area.setEditable(false);
        sc = new JScrollPane(area);
        sc.setBounds(330, 30, 490, 590);
        sc.setFont(new java.awt.Font("Tahoma", 0, 14));
        cont.add(sc);
        
 
    }
    
 
    public String cl(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
//Creación del metodo que abre el fichero para elegir y analiza cuantas palabras y numeros tiene
    public void seleccionarTxt() throws FileNotFoundException, IOException {
        JFileChooser fc = new JFileChooser();
//Indicamos lo que podemos seleccionar
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//Creamos el filtro
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "TXT");
//Le indicamos el filtro
        fc.setFileFilter(filtro);
//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion = fc.showOpenDialog(cont);
//Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
//Seleccionamos el fichero
            File fichero = fc.getSelectedFile();
//Ecribe la ruta del fichero seleccionado en el campo de texto
            ruta.setText(fichero.getAbsolutePath());
//Obtenemos el texto de la ruta y lo guardamos en la variable rut
            rut = ruta.getText();
            
            
            
//Hacemos uso de la clase StreamTokenizer para leer el archivo
            StreamTokenizer st = new StreamTokenizer(new FileReader("" + rut));
             
            
            //long tiempoInicial = System.currentTime
            System.currentTimeMillis();
            
//mientras el st.ttype se diferente del StreamTokenizer.TT_EOF que se refiere al final del documento 
            while (st.ttype != StreamTokenizer.TT_EOF) {
//si el siguiente token es igual a TT_WORD que son objetos de palabras aumentara nuestro contador en 1
                if (st.nextToken() == StreamTokenizer.TT_WORD) {
                    cont1++;
                //si el siguiente token es igual a TT_NUMBER que son objetos numericos aumentara nuestro contador en 1
                } else if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    cont2++;
                }
            }
            pal.setText("Cantidad de palabras: " + cont1);
            num.setText("Cantidad de numeros: " + cont2);
            try (FileReader fr = new FileReader(fichero)) {
                String cadena = "";
                int valor = fr.read();
                while (valor != -1) {
                    cadena = cadena + (char) valor;
                    valor = fr.read();
                    
                }
                area.setText(cadena);
                cont3++;
                
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void separarTxt() {

        cl(palabra = area.getText());
        deli = "[ 1234567890\\(\\)\\.\\,\\:\\;?¡=¿!|°¬<>\"\'*%&/]";
        String palabraSep[] = palabra.split(deli);
        try {
            for (int i = 0; i <= palabra.length(); i++) {
                areaPal.append("  " + palabraSep[i]);
                cont4++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent event) {

        if (buscar == event.getSource()) {
            cont1 = 0;
            cont2 = 0;
            cont3 = 0;
            cont4 = 0;

            try {
                seleccionarTxt();
                separarTxt();
                lin.setText("Lineas del documento: " + cont3 + " Palabras " + cont4);
            } catch (IOException ex) {
                Logger.getLogger(Interfaz1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (limpiar == event.getSource()) {
            cont1 = 0;
            cont2 = 0;
            cont3 = 0;
            cont4 = 0;
            ruta.setText("");
            area.setText("");
            areaPal.setText("");
            pal.setText("Cantidad de palabras: ");
            num.setText("Cantidad de numeros: ");
            lin.setText("Lineas del documento: ");
        }
        if (version == event.getSource()) {
            JOptionPane.showMessageDialog(null, "V.1");
        }
        if (salir == event.getSource()) {
            int valor;
            valor = JOptionPane.showConfirmDialog(null, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (valor == 0) {
                System.exit(0);
            }
            if (valor == 1) {
            }
    }
    }
    public static void main(String[] args) throws IOException {

        obj = new Interfaz1();
        obj.setVisible(true);
        obj.setResizable(false);

    }

}
