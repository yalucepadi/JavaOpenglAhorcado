/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangedform;

import com.jogamp.opengl.GL2;
import renderer.RendererMuñeco;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import modelo.Punto;
import renderer.RendererMuñeco;

/**
 *
 * @author Administrador
 */
public class AppHangedIU extends javax.swing.JFrame {

    public static GLCanvas glCanvas;
    public static Animator animador;
    public RendererMuñeco rendererPunto;
    private GL2 gl;
    GLU glu = new GLU();
    GLUT glut = new GLUT();
    public JButton btns[];
    public String msgsP[];
    public String msgsA[];
    public String msgsC[];
    public String msgs[];
    public int ran = 0;
    public int err = 0;
    public int err2 = 0;
    public String res[];
    public String pal[];

    /**
     *
     * Creates new form AppHangedi
     */
    public AppHangedIU() {

        initComponents();
        rendererPunto = new RendererMuñeco();
        initCanvas();
        //botones para las letras
        btns = new JButton[28];
        btns[1] = button_1;
        btns[2] = button_2;
        btns[3] = button_3;
        btns[4] = button_4;
        btns[5] = button_5;
        btns[6] = button_6;
        btns[7] = button_7;
        btns[8] = button_8;
        btns[9] = button_9;
        btns[10] = button_10;
        btns[11] = button_11;
        btns[12] = button_12;
        btns[13] = button_13;
        btns[14] = button_14;
        btns[15] = button_16;
        btns[16] = button_17;
        btns[17] = button_18;
        btns[18] = button_19;
        btns[19] = button_20;
        btns[20] = button_21;
        btns[21] = button_22;
        btns[22] = button_23;
        btns[23] = button_24;
        btns[24] = button_25;
        btns[25] = button_26;
        btns[26] = button_27;
        btns[27] = button_15;
//palabras categoria Paices
        msgsP = new String[10];
        msgsP[0] = "Peru".toUpperCase();
        msgsP[1] = "Chile".toUpperCase();
        msgsP[2] = "Alemania".toUpperCase();
        msgsP[3] = "Venezuela".toUpperCase();
        msgsP[4] = "Ecuador".toUpperCase();
        msgsP[5] = "Belgica".toUpperCase();
        msgsP[6] = "Islandia".toUpperCase();
        msgsP[7] = "Australia".toUpperCase();
        msgsP[8] = "Nueva Zelanda".toUpperCase();
        msgsP[9] = "Mongolia".toUpperCase();

//palabras categoria Animales
        msgsA = new String[10];
        msgsA[0] = "Perro".toUpperCase();
        msgsA[1] = "Gato".toUpperCase();
        msgsA[2] = "Ñandu".toUpperCase();
        msgsA[3] = "Chita".toUpperCase();
        msgsA[4] = "Gazela".toUpperCase();
        msgsA[5] = "Ornitorrinco".toUpperCase();
        msgsA[6] = "Caracol".toUpperCase();
        msgsA[7] = "Dragon de comodo".toUpperCase();
        msgsA[8] = "Tosanoides obama".toUpperCase();
        msgsA[9] = "Guacamayo".toUpperCase();

//palabras categoria Cosas
        msgsC = new String[10];
        msgsC[0] = "Mesa".toUpperCase();
        msgsC[1] = "Lapiz".toUpperCase();
        msgsC[2] = "Cocina".toUpperCase();
        msgsC[3] = "Ropero".toUpperCase();
        msgsC[4] = "Llaves".toUpperCase();
        msgsC[5] = "Televisor".toUpperCase();
        msgsC[6] = "Acha".toUpperCase();
        msgsC[7] = "Mota".toUpperCase();
        msgsC[8] = "Revista".toUpperCase();
        msgsC[9] = "Sabanas".toUpperCase();

    }

    private void initCanvas() {
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities glcaps = new GLCapabilities(profile);
        glCanvas = new GLCanvas(glcaps);
        glCanvas.addGLEventListener(rendererPunto);
        glCanvas.addKeyListener(rendererPunto);
        this.panelJOGL.add(glCanvas);
        int w = this.panelJOGL.getWidth();
        int h = this.panelJOGL.getWidth();
        glCanvas.setSize(w, h);
        animador = new Animator(glCanvas);
        System.out.println("\nAncho:" + w + "Altura:" + h);
    }

    //funcion para comenzar los parametros del juego o iniciar una nueva partida
    public void iniciar(String msgs[]) {
        //ERRORES EN 0
        String pal[] = null;
        err = 0;
        err2 = 6;
        //Dibujo.setIcon(imgs[0]);
        txtPalabra.setText("");
        //errores.setIcon(imgs[6]);//:___________________________________----
        //para activar las letras del tablero
        for (int i = 1; i < 28; i++) {
            btns[i].setEnabled(true);
        }
        //para generar una palabra aleatoriamente xD
        ran = (int) Math.floor(0 + (int) (Math.random() * ((msgs.length - 1) + 1)));
        //SEPARA EL MENSAJE POR PALABRAS
        System.out.println(ran);

        pal = msgs[ran].split(" ");

        res = new String[msgs[ran].length() + 1];
        int j = 0;
        // seran los guiones que van debajo de las letras como una separacion_
        for (String pal1 : pal) {
            for (int i = 0; i < pal1.length(); i++) {
                txtPalabra.setText(txtPalabra.getText() + "_ ");
                res[j++] = "_";
            }
            txtPalabra.setText(txtPalabra.getText() + "\n");
            res[j++] = " ";
        }

    }

    public void OpcionElejida(ActionEvent e) {

        String itemSelecionado = (String) jComboBoxCategoria.getSelectedItem();
        System.out.println(itemSelecionado);
        switch (itemSelecionado) {
            case "Animales":
                for (int i = 1; i < 28; i++) {
                    btns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            checarLetra(e, msgsA);

                        }
                    });
                }

                iniciar(msgsA);

                break;
            case "Paices":
                for (int i = 1; i < 28; i++) {
                    btns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            checarLetra(e, msgsP);

                        }
                    });
                }

                iniciar(msgsP);

                break;
            case "Cosas":
                for (int i = 1; i < 28; i++) {
                    btns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            checarLetra(e, msgsC);

                        }
                    });
                }

                iniciar(msgsC);

                break;
        }

    }
//    private void dibujarPunto(int x, int y, int z) {
//        // Recuperar los valores del Punto
//
//        do {
//            x++;
//            y++;
//            z++;
//            Punto punto = new Punto(x, y, z);
//            System.out.println(x + " " + y + " " + z);
//
//            this.rendererPunto.cambiarFactoresTraslacion(x, y, z);
//
//            // Actualizar la venta de visualizacion
//            glCanvas.repaint();
//        } while (x < 10);
//    }

    public void checarLetra(ActionEvent e, String msgs[]) {
        /*Icon cp=new ImageIcon(getClass().getResource("/imagenes/copa.png"));//icono de la copa
        Icon cara=new ImageIcon(getClass().getResource("/imagenes/cara.png"));//icono de la copa*/
        JButton bt = (JButton) e.getSource();
        char c[];
        //busca la letra en la palabra despues de haber sido presionada

        for (int i = 1; i < 28; i++) {
            if (bt == btns[i]) {
                //la tecla es inicializada
                c = Character.toChars(64 + i);
                System.out.println(c);
                //busca si la letra esta en la frase
                boolean esta = false;
                for (int j = 0; j < msgs[ran].length(); j++) {
                    if (c[0] == msgs[ran].charAt(j)) {
                        res[j] = c[0] + "";
                        esta = true;
                    }
                }
                //SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
                if (esta) {
                    txtPalabra.setText("");
                    for (String re : res) {
                        if (" ".equals(re)) {
                            txtPalabra.setText(txtPalabra.getText() + "\n");
                        } else {
                            txtPalabra.setText(txtPalabra.getText() + re + " ");
                        }
                    }
                    //hace una comprobacion de las letras restantes y faltantes, en caso de que ya no haya letras sera ganador :D
                    boolean gano = true;
                    for (String re : res) {
                        if (re.equals("_")) {
                            gano = false;
                            break;
                        }
                    }
                    //al ser correcta se muestra un mensaje y se reinicia el juego

                    if (gano) {
                        JOptionPane.showMessageDialog(this, "FELICITACIONES GANASTES!!\n YA PUEDES RECLAMAR TU PREMIO", "Ahorcado", JOptionPane.INFORMATION_MESSAGE);

                        iniciar(msgs);
                        return;
                    }
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {

                    /*Dibujo.setIcon(imgs[++err]);
                    errores.setIcon(imgs[++err2]);//////////////////////////////////////_----*/
                    //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:
                    
                    err++;
                    //rendererPunto.controlarErrores(err);

                    System.out.println("Error nº:" + err);

                    if (err == 5) {
                        JOptionPane.showMessageDialog(this, "HAS PERDIDO\n Intenta con otra palabra la respuesta es: \n" + msgs[ran], "Ahorcado", JOptionPane.INFORMATION_MESSAGE);

                        iniciar(msgs);
                        return;
                    }
                }
                //esta es la linea que desactiva las letras despues de ser usadas :3
                bt.setEnabled(false);
                break;
            }

            if (bt == btns[27]) {
                //la tecla es inicializada
                char letraeñe[] = {'\u00d1'};
                c = letraeñe;
                System.out.println(c);
                //busca si la letra esta en la frase
                boolean esta = false;
                for (int j = 0; j < msgs[ran].length(); j++) {
                    if (c[0] == msgs[ran].charAt(j)) {
                        res[j] = c[0] + "";
                        esta = true;
                    }
                }
                //SI LA LETRA ESTA EN EL MENSAJE SE MUESTRA EN EL TEXTPANEL
                if (esta) {
                    txtPalabra.setText("");
                    for (String re : res) {
                        if (" ".equals(re)) {
                            txtPalabra.setText(txtPalabra.getText() + "\n");
                        } else {
                            txtPalabra.setText(txtPalabra.getText() + re + " ");
                        }
                    }
                    //hace una comprobacion de las letras restantes y faltantes, en caso de que ya no haya letras sera ganador :D
                    boolean gano = true;
                    for (String re : res) {
                        if (re.equals("_")) {
                            gano = false;
                            break;
                        }
                    }
                    //al ser correcta se muestra un mensaje y se reinicia el juego

                    if (gano) {
                        JOptionPane.showMessageDialog(this, "FELICITACIONES GANASTES!!\n YA PUEDES RECLAMAR TU PREMIO", "Ahorcado", JOptionPane.INFORMATION_MESSAGE);

                        iniciar(msgs);
                        return;
                    }
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {
                    /*Dibujo.setIcon(imgs[++err]);
                    errores.setIcon(imgs[++err2]);//////////////////////////////////////_----*/
                    //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:

                    err++;
                    //rendererPunto.controlarErrores(err);

                    System.out.println("Error nº:" + err);

                    if (err == 5) {
                        JOptionPane.showMessageDialog(this, "HAS PERDIDO\n Intenta con otra palabra la respuesta es: \n" + msgs[ran], "Ahorcado", JOptionPane.INFORMATION_MESSAGE);

                        iniciar(msgs);
                        return;
                    }
                }
                //esta es la linea que desactiva las letras despues de ser usadas :3
                bt.setEnabled(false);
                break;

            }
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

        panelJOGL = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        button_1 = new javax.swing.JButton();
        button_2 = new javax.swing.JButton();
        button_3 = new javax.swing.JButton();
        button_4 = new javax.swing.JButton();
        button_5 = new javax.swing.JButton();
        button_6 = new javax.swing.JButton();
        button_7 = new javax.swing.JButton();
        button_8 = new javax.swing.JButton();
        button_9 = new javax.swing.JButton();
        button_10 = new javax.swing.JButton();
        button_11 = new javax.swing.JButton();
        button_12 = new javax.swing.JButton();
        button_13 = new javax.swing.JButton();
        button_14 = new javax.swing.JButton();
        button_15 = new javax.swing.JButton();
        button_16 = new javax.swing.JButton();
        button_17 = new javax.swing.JButton();
        button_18 = new javax.swing.JButton();
        button_19 = new javax.swing.JButton();
        button_20 = new javax.swing.JButton();
        button_21 = new javax.swing.JButton();
        button_22 = new javax.swing.JButton();
        button_23 = new javax.swing.JButton();
        button_24 = new javax.swing.JButton();
        button_25 = new javax.swing.JButton();
        button_26 = new javax.swing.JButton();
        button_27 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtPalabra = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelJOGL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelJOGL.setPreferredSize(new java.awt.Dimension(1000, 1000));
        panelJOGL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelJOGLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelJOGLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelJOGLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelJOGLLayout = new javax.swing.GroupLayout(panelJOGL);
        panelJOGL.setLayout(panelJOGLLayout);
        panelJOGLLayout.setHorizontalGroup(
            panelJOGLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );
        panelJOGLLayout.setVerticalGroup(
            panelJOGLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 998, Short.MAX_VALUE)
        );

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Animales", "Paices", "Cosas" }));
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        button_1.setText("A");

        button_2.setText("B");

        button_3.setText("C");
        button_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_3ActionPerformed(evt);
            }
        });

        button_4.setText("D");
        button_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_4ActionPerformed(evt);
            }
        });

        button_5.setText("E");
        button_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_5ActionPerformed(evt);
            }
        });

        button_6.setText("F");
        button_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_6ActionPerformed(evt);
            }
        });

        button_7.setText("G");
        button_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_7ActionPerformed(evt);
            }
        });

        button_8.setText("H");
        button_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_8ActionPerformed(evt);
            }
        });

        button_9.setText("I");
        button_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_9ActionPerformed(evt);
            }
        });

        button_10.setText("J");
        button_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_10ActionPerformed(evt);
            }
        });

        button_11.setText("K");
        button_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_11ActionPerformed(evt);
            }
        });

        button_12.setText("L");
        button_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_12ActionPerformed(evt);
            }
        });

        button_13.setText("M");
        button_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_13ActionPerformed(evt);
            }
        });

        button_14.setText("N");
        button_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_14ActionPerformed(evt);
            }
        });

        button_15.setText("Ñ");
        button_15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_15ActionPerformed(evt);
            }
        });

        button_16.setText("O");
        button_16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_16ActionPerformed(evt);
            }
        });

        button_17.setText("P");
        button_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_17ActionPerformed(evt);
            }
        });

        button_18.setText("Q");
        button_18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_18ActionPerformed(evt);
            }
        });

        button_19.setText("R");
        button_19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_19ActionPerformed(evt);
            }
        });

        button_20.setText("S");
        button_20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_20ActionPerformed(evt);
            }
        });

        button_21.setText("T");
        button_21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_21ActionPerformed(evt);
            }
        });

        button_22.setText("U");
        button_22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_22ActionPerformed(evt);
            }
        });

        button_23.setText("V");
        button_23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_23ActionPerformed(evt);
            }
        });

        button_24.setText("W");
        button_24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_24ActionPerformed(evt);
            }
        });

        button_25.setText("X");
        button_25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_25ActionPerformed(evt);
            }
        });

        button_26.setText("Y");
        button_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_26ActionPerformed(evt);
            }
        });

        button_27.setText("Z");
        button_27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(button_26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_27))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(button_19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button_20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button_21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button_22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button_23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_24))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(button_1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(button_2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(button_3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(button_4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(button_5))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(button_7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(button_8))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(button_13)
                                                .addComponent(button_25))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(button_14)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(button_9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(button_10))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(button_15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(button_16)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(button_17)
                                        .addComponent(button_11))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button_6)
                                    .addComponent(button_12))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(button_18)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_1)
                    .addComponent(button_2)
                    .addComponent(button_3)
                    .addComponent(button_4)
                    .addComponent(button_5)
                    .addComponent(button_6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_7)
                    .addComponent(button_8)
                    .addComponent(button_9)
                    .addComponent(button_10)
                    .addComponent(button_11)
                    .addComponent(button_12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_13)
                    .addComponent(button_14)
                    .addComponent(button_15)
                    .addComponent(button_16)
                    .addComponent(button_17)
                    .addComponent(button_18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_19)
                    .addComponent(button_20)
                    .addComponent(button_21)
                    .addComponent(button_22)
                    .addComponent(button_23)
                    .addComponent(button_24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_25)
                    .addComponent(button_26)
                    .addComponent(button_27))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPalabra, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(txtPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Parar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnIniciar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelJOGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelJOGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        int x1 = 0;
        int y1 = 0;
        int z1 = 0;

        //dibujarPunto(x1, y1, z1);
        System.out.println("\nx1:" + x1);
        if (JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de querer una palabra nueva?",
                "Ahorcado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
            OpcionElejida(evt);

        } else {
            setDefaultCloseOperation(0);
        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void button_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_3ActionPerformed

    private void button_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_4ActionPerformed

    private void button_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_5ActionPerformed

    private void button_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_6ActionPerformed

    private void button_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_7ActionPerformed

    private void button_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_8ActionPerformed

    private void button_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_9ActionPerformed

    private void button_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_10ActionPerformed

    private void button_11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_11ActionPerformed

    private void button_12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_12ActionPerformed

    private void button_13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_13ActionPerformed

    private void button_14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_14ActionPerformed

    private void button_15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_15ActionPerformed

    private void button_16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_16ActionPerformed

    private void button_17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_17ActionPerformed

    private void button_18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_18ActionPerformed

    private void button_19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_19ActionPerformed

    private void button_20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_20ActionPerformed

    private void button_21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_21ActionPerformed

    private void button_22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_22ActionPerformed

    private void button_23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_23ActionPerformed

    private void button_24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_24ActionPerformed

    private void button_25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_25ActionPerformed

    private void button_26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_26ActionPerformed

    private void button_27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_27ActionPerformed

    private void jComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaActionPerformed
//        jComboBoxCategoria.addItem("Animales");
//        jComboBoxCategoria.addItem("Paices");
//        jComboBoxCategoria.addItem("Cosas");


    }//GEN-LAST:event_jComboBoxCategoriaActionPerformed

    private void panelJOGLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJOGLMouseClicked

    }//GEN-LAST:event_panelJOGLMouseClicked

    private void panelJOGLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJOGLMouseEntered

    }//GEN-LAST:event_panelJOGLMouseEntered

    private void panelJOGLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJOGLMouseExited

    }//GEN-LAST:event_panelJOGLMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        animador.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        animador.stop();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AppHangedIU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppHangedIU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppHangedIU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppHangedIU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppHangedIU().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton button_1;
    private javax.swing.JButton button_10;
    private javax.swing.JButton button_11;
    private javax.swing.JButton button_12;
    private javax.swing.JButton button_13;
    private javax.swing.JButton button_14;
    private javax.swing.JButton button_15;
    private javax.swing.JButton button_16;
    private javax.swing.JButton button_17;
    private javax.swing.JButton button_18;
    private javax.swing.JButton button_19;
    private javax.swing.JButton button_2;
    private javax.swing.JButton button_20;
    private javax.swing.JButton button_21;
    private javax.swing.JButton button_22;
    private javax.swing.JButton button_23;
    private javax.swing.JButton button_24;
    private javax.swing.JButton button_25;
    private javax.swing.JButton button_26;
    private javax.swing.JButton button_27;
    private javax.swing.JButton button_3;
    private javax.swing.JButton button_4;
    private javax.swing.JButton button_5;
    private javax.swing.JButton button_6;
    private javax.swing.JButton button_7;
    private javax.swing.JButton button_8;
    private javax.swing.JButton button_9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelJOGL;
    private javax.swing.JTextField txtPalabra;
    // End of variables declaration//GEN-END:variables
}
