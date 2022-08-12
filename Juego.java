import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Juego implements ActionListener {
    
    private JFrame frame;
    private JPanel panel;
    private JButton[] btn_botones;
    private JButton btn_iniciar, btn_temp, btn_validar, btn_iniciar2, btn_consultar;
    private JTextField txtf_contador, txtf_move, txtf_nombre;
    private JLabel lbl_contador, lbl_move, lbl_nombre, lbl_nombreII;
    private int x, y, xx, yy, j, cambio, dir, cont, move, btn1, btn2;
    private boolean orden = false, volver = false;
    private String cadena;
    Random random = new Random();
    
    
    
    DefaultListModel<String> listModel;
    JList<String> lst_lista;
    JScrollPane listScroller;
    
    
    private static ArrayList jugador = new ArrayList<>();
    
    Timer timer = new Timer(50, new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            int x, y;
            
            x = btn_temp.getLocation().x;
            y = btn_temp.getLocation().y;
            
            btn_temp.setLocation(x, y + dir);
            
            if (y + dir == btn_botones[15].getLocation().y) {
                timer.stop();
                btn_botones[15].setLocation(xx, yy);
            }
            
        }
        
    }
    );
    
    Timer timer2 = new Timer(50, new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            
            x = btn_temp.getLocation().x;
            y = btn_temp.getLocation().y;
            
            btn_temp.setLocation(x + dir, y);

            //System.out.println("X: "+xx+" Y: "+yy);
            if (x + dir == btn_botones[15].getLocation().x) {
                timer2.stop();
                btn_botones[15].setLocation(xx, yy);
            }
            
        }
        
    }
    );
    
    Timer timer3 = new Timer(1000, new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            //System.out.println("En el Timer");
            cont = Integer.parseInt(txtf_contador.getText());
            txtf_contador.setText(String.valueOf(cont + 1));
            
        }
        
    });
    
    public Juego() {
        
        interfaz();
	presentacion();
        
    }

public void presentacion() {
        JOptionPane.showMessageDialog(null, 
                  "Universidad Tecnologica de Panama\n\n"
                + "Ingieneria de Sistemas Computacionales\n\n"
                + "Licenciantura en Desarrollo de Software\n\n"
                + "Desarrollo de Software III\n\n"
                + "Ricardo Chang\n\n"
                + "Isaias Castillo\n\n"
                + "8-1004-1416\n\n"
                + "1LS122\n\n"
                + "29 de junio de 2022\n\n");
    }
    
    public void interfaz() {

        //fuente y colores
        Font font = new Font("Monospaced", Font.BOLD, 12);
        Font fonttxt = new Font("Serif", Font.BOLD, 15);
        Color lablel = new Color(255, 0, 0);
        Color botones = new Color(146, 180, 236);
        Color botones1 = new Color(255, 230, 154);
        Color fondo = new Color(255, 255, 255);
        
        frame = new JFrame("ROMPE CABEZAS");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(500, 500);
        panel.setBackground(fondo);
        frame.add(panel);
        
        lbl_nombre = new JLabel("Jugador:");
        lbl_nombre.setBounds(10, 10, 70, 20);
        lbl_nombre.setFont(font);
        lbl_nombre.setForeground(Color.BLACK);
        panel.add(lbl_nombre);
        
        txtf_nombre = new JTextField();
        txtf_nombre.setBounds(75, 10, 105, 20);
        panel.add(txtf_nombre);
        
        btn_iniciar = new JButton("Iniciar");
        btn_iniciar.setBounds(10, 40, 80, 20);
        btn_iniciar.setBackground(botones1);
        btn_iniciar.setForeground(Color.BLACK);
        btn_iniciar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        btn_iniciar.setFont(font);
        btn_iniciar.setActionCommand("inicio");
        btn_iniciar.addActionListener(this);
        panel.add(btn_iniciar);
        
        btn_iniciar2 = new JButton("Iniciar II");
        btn_iniciar2.setBounds(100, 40, 80, 20);
        btn_iniciar2.setBackground(botones1);
        btn_iniciar2.setForeground(Color.BLACK);
        btn_iniciar2.setBorder(new BevelBorder(BevelBorder.LOWERED));
        btn_iniciar2.setFont(font);
        btn_iniciar2.setActionCommand("iniciar2");
        btn_iniciar2.addActionListener(this);
        panel.add(btn_iniciar2);
        
        lbl_contador = new JLabel("Tiempo:");
        lbl_contador.setBounds(10, 80, 60, 20);
        lbl_contador.setFont(font);
        lbl_contador.setForeground(Color.BLACK);
        panel.add(lbl_contador);
        
        txtf_contador = new JTextField("0");
        txtf_contador.setBounds(65, 80, 40, 20);
        panel.add(txtf_contador);
        
        lbl_move = new JLabel("Movimietos:");
        lbl_move.setBounds(10, 110, 105, 20);
        lbl_move.setFont(font);
        lbl_move.setForeground(Color.BLACK);
        panel.add(lbl_move);
        
        txtf_move = new JTextField("0");
        txtf_move.setBounds(90, 110, 40, 20);
        panel.add(txtf_move);
        
        btn_validar = new JButton("Validar");
        btn_validar.setBounds(10, 155, 80, 20);
        btn_validar.setBackground(botones1);
        btn_validar.setForeground(Color.BLACK);
        btn_validar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        btn_validar.setFont(font);
        btn_validar.setActionCommand("validar");
        btn_validar.addActionListener(this);
        panel.add(btn_validar);
        
        btn_consultar = new JButton("Rankig");
        btn_consultar.setBounds(10, 195, 80, 20);
        btn_consultar.setBackground(botones1);
        btn_consultar.setForeground(Color.BLACK);
        btn_consultar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        btn_consultar.setFont(font);
        btn_consultar.setActionCommand("ranking");
        btn_consultar.addActionListener(this);
        panel.add(btn_consultar);
        
        btn_botones = new JButton[16];
        
        for (int i = 0; i < 16; i++) {
            btn_botones[i] = new JButton(String.valueOf(i + 1));
            btn_botones[i].setBounds(190 + 50 * (i % 4), 10 + 50 * (i / 4), 50, 50);
            btn_botones[i].setBackground(botones);
            btn_botones[i].setBorderPainted(false);
            btn_botones[i].setActionCommand("botones");
            btn_botones[i].addActionListener(this);
            panel.add(btn_botones[i]);
        }
        
        btn_botones[15].setVisible(false);
        
        listModel = new DefaultListModel<String>();
        lst_lista = new JList<String>(listModel);
        listScroller = new JScrollPane(lst_lista);
        listScroller.setBounds(10, 225, 160, 100);
        panel.add(listScroller);
        
    }
    
    public void reiniciar() {
        
        txtf_nombre.setText("");
        txtf_contador.setText("");
        txtf_move.setText("");
        for (int i = 0; i < 16; i++) {
            
            btn_botones[i].setBounds(190 + 50 * (i % 4), 10 + 50 * (i / 4), 50, 50);
        }
        
        listModel.clear();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_iniciar) {
            timer3.start();
            for (int i = 0; i < 15; i++) {
                j = random.nextInt(15);
                
                x = btn_botones[i].getLocation().x;
                y = btn_botones[i].getLocation().y;
                
                btn_botones[i].setLocation(btn_botones[j].getLocation().x, btn_botones[j].getLocation().y);
                btn_botones[j].setLocation(x, y);
                
            }
            
        }
        
        if (e.getActionCommand().equals("botones")) {
            
            btn_temp = (JButton) e.getSource();
            
            xx = btn_temp.getLocation().x;
            yy = btn_temp.getLocation().y;

            //System.out.println("Tocado " + xx + "," +yy);
            x = btn_botones[15].getLocation().x;
            y = btn_botones[15].getLocation().y;

            //System.out.println("Espacio " + x+ "," +y);
            if (xx == x && yy + 50 == y) {
                dir = 10;
                timer.start();
                move = Integer.parseInt(txtf_move.getText());
                txtf_move.setText(String.valueOf(move + 1));
                
            } else if (xx == x && yy - 50 == y) {
                dir = -10;
                timer.start();
                move = Integer.parseInt(txtf_move.getText());
                txtf_move.setText(String.valueOf(move + 1));
                
            } else if (yy == y && xx + 50 == x) {
                dir = 10;
                timer2.start();
                move = Integer.parseInt(txtf_move.getText());
                txtf_move.setText(String.valueOf(move + 1));
                
            } else if (yy == y && xx - 50 == x) {
                dir = -10;
                timer2.start();
                move = Integer.parseInt(txtf_move.getText());
                txtf_move.setText(String.valueOf(move + 1));
                
            }
            
        }
        
        if (e.getActionCommand().equals("iniciar2")) {
            
            timer3.start();
            for (int i = 0; i < 15; i++) {
                j = random.nextInt(15);
                
                x = btn_botones[14].getLocation().x;
                y = btn_botones[14].getLocation().y;
                
                btn_botones[14].setLocation(btn_botones[15].getLocation().x, btn_botones[15].getLocation().y);
                btn_botones[15].setLocation(x, y);
                
            }
        }
        
        if (e.getActionCommand().equals("validar")) {
            
            int vx, vy, bx, by;
            
            for (int i = 0; i < 15; i++) {
                vx = 190 + 50 * (i % 4);
                vy = 10 + 50 * (i / 4);
                bx = btn_botones[i].getLocation().x;
                by = btn_botones[i].getLocation().y;
                
                if (bx == vx && by == vy) {
                    orden = true;
                    
                } else {
                    orden = false;
                    break;
                    
                }
            }
            
            if (orden == true) {
                System.out.println("Ordenado.");
                timer3.stop();
                grabar();
            } else {
                System.out.println("Desordenado.");
            }
        }
        
        if (e.getActionCommand().equals("ranking")) {
            leer();
            
        }
    }
    
    public void grabar() {
        FileWriter fw;
        try {
            fw = new FileWriter("ranking.txt", true);
            fw.write(txtf_nombre.getText() + "\r\n");
            fw.write(txtf_contador.getText() + "\r\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error al grabar " + e.toString());
        }
    }
    
    public void leer() {
        int k=0;
        
        try {
            File file = new File("ranking.txt");
            Scanner scanner;
            String nombre;
            int tiempo;
            
            scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                nombre = scanner.nextLine();
                tiempo = Integer.parseInt(scanner.nextLine());
                
                jugador.add(new Jugador(nombre, tiempo));
                k++;
                
            }
            
            Collections.sort(jugador, new Comparator<Jugador>() {
                @Override
                public int compare(Jugador j1, Jugador j2) {
                    return new Integer(j1.getTiempo()).compareTo(new Integer(j2.getTiempo()));
                }
            });
            
        } catch (Exception e) {
            System.out.println("Error de Lectura" + e.toString());
        }
        
        for (int i = 0; i < 5; i++) {
            System.out.println(jugador.get(i));
            listModel.addElement(jugador.get(i).toString());
        }
        
        JOptionPane.showMessageDialog(null, "¡FELICIDADES GANASTE!");

        if (JOptionPane.showConfirmDialog(null, "¿Quieres Volver a Intentar?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            reiniciar();
        } else {
            System.exit(0);
        }
        
    }
}
        
    
