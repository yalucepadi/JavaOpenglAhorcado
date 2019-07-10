/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangedform;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.gl2.GLUT;
import extext.ExText;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import renderer.RendererMuñeco;


public class AppHangedIU1 extends javax.swing.JFrame {

    public static GLCanvas glCanvas;
    public static Animator animador;
    public RendererMuñeco rendererPunto;
    public ExText extext;
    public JButton btns[];
    public String msgsP[];
    public String msgsA[];
    public String msgsC[];
    public String msgs[];
    public int ran = 0;
    public int err = -1;
    //public int err2 = 0;
    public String res[];
    public String pal[];
    public ImageIcon imgs[];

 
    public AppHangedIU1() {

        initComponents();
        rendererPunto = new RendererMuñeco();
        initCanvas();
        //imagenes para controlar errores
        imgs = new ImageIcon[6];

        imgs[0] = new ImageIcon(getClass().getResource("/imagenes/fallo0.png"));
        imgs[1] = new ImageIcon(getClass().getResource("/imagenes/fallo1.png"));
        imgs[2] = new ImageIcon(getClass().getResource("/imagenes/fallo2.png"));
        imgs[3] = new ImageIcon(getClass().getResource("/imagenes/fallo3.png"));
        imgs[4] = new ImageIcon(getClass().getResource("/imagenes/fallo4.png"));
        imgs[5] = new ImageIcon(getClass().getResource("/imagenes/fallo5.png"));

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
//palabras categoria Paises
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
        int h = this.panelJOGL.getHeight();
        glCanvas.setSize(w, h);
        animador = new Animator(glCanvas);
        System.out.println("\nAncho:" + w + "Altura:" + h);
    }
  public String a(String pal) {
   
    return pal;
}
    //funcion para comenzar los parametros del juego o iniciar una nueva partida
    public void iniciar(String msgs[]) {
        //ERRORES EN 0
        String pal[]=null;
        err = 0;
        txtPalabra.setText("");
        //para activar las letras del tablero
        for (int i = 1; i < 28; i++) {
            btns[i].setEnabled(true);
        }
        //para generar una palabra aleatoriamente xD
        ran = (int) Math.floor(0 + (int) (Math.random() * ((msgs.length - 1) + 1)));
        //SEPARA EL MENSAJE POR PALABRAS
        System.out.println(ran);

        pal= msgs[ran].split(" ");      
             a(String.valueOf(msgs[ran]));
        res = new String[msgs[ran].length() + 1];
        int j = 0;
        // seran los guiones que van debajo de las letras como una separacion
        for (String pal1 : pal) {
            for (int i = 0; i < pal1.length(); i++) {
                txtPalabra.setText(txtPalabra.getText() + "_ ");
                res[j++] = "_";
            }
            txtPalabra.setText(txtPalabra.getText() + "\n");
            res[j++] = " ";
        }

    }

    public void OpcionElegida(ActionEvent e) {

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
 rendererPunto.er = 0;
                          errores.setIcon(imgs[rendererPunto.er]);
                iniciar(msgsA);

                break;
            case "Paises":
                for (int i = 1; i < 28; i++) {
                    btns[i].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            checarLetra(e, msgsP);

                        }
                    });
                }
 rendererPunto.er = 0;
                          errores.setIcon(imgs[rendererPunto.er]);
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
 rendererPunto.er = 0;
                          errores.setIcon(imgs[rendererPunto.er]);
                iniciar(msgsC);

                break;
        }

    }

    public void checarLetra(ActionEvent e, String msgs[]) {
 
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
                    ExText ex = new ExText();
                    String args[]={""};
                     ex.initialize(args,String.valueOf(msgs[ran]));
                      ex.buildUniverse();
                      ex.showFrame();
                      rendererPunto.er = 0;
                          errores.setIcon(imgs[rendererPunto.er]);
                        iniciar(msgs);
                       
                    
                    
                        return;
                    }
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {
                    //err++;
                    /*Dibujo.setIcon(imgs[++err]);*/
                    errores.setIcon(imgs[++rendererPunto.er]);//////////////////////////////////////_----
                    //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:

                    System.out.println("Error nº:" + rendererPunto.er);

                    if (rendererPunto.er == 5) {
                        JOptionPane.showMessageDialog(this, "HAS PERDIDO\n Intenta con otra palabra la respuesta es: \n" + msgs[ran], "Ahorcado", JOptionPane.INFORMATION_MESSAGE);
                        rendererPunto.er = 0;
                          errores.setIcon(imgs[rendererPunto.er]);
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
                        JOptionPane.showMessageDialog(this, "FELICITACIONES GANASTE!!\n YA PUEDES RECLAMAR TU PREMIO", "Ahorcado", JOptionPane.INFORMATION_MESSAGE);
                 ExText ex = new ExText();
                    String args[]={""};
                     ex.initialize(args,a(String.valueOf(msgs[ran])));
                      ex.buildUniverse();
                      ex.showFrame();
                       rendererPunto.er = 0;
                          errores.setIcon(imgs[rendererPunto.er]);
                        iniciar(msgs);
                 
                        return;
                    }
                    //SI LA LETRA NO ESTA EN EL MENSAGE, SE INCREMENTA EL ERROR Y SE CAMBIA LA IMAGEN
                } else {

                  
                    errores.setIcon(imgs[++rendererPunto.er]);
                    //SI SE LLEGA A LOS 5 ERRORES ENTONCES SE PIERDE EL JUEGO Y SE MANDA EL MENSAGE DE:

                    System.out.println("Error nº:" + rendererPunto.er);

                    if (rendererPunto.er == 5) {
                        JOptionPane.showMessageDialog(this, "HAS PERDIDO\n Intenta con otra palabra la respuesta es: \n" + msgs[ran], "Ahorcado", JOptionPane.INFORMATION_MESSAGE);
                        rendererPunto.er = 0;
                     errores.setIcon(imgs[rendererPunto.er]);
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


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelJOGL = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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
        txtPalabra = new javax.swing.JTextField();
        errores = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            .addGap(0, 798, Short.MAX_VALUE)
        );
        panelJOGLLayout.setVerticalGroup(
            panelJOGLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 688, Short.MAX_VALUE)
        );

        getContentPane().add(panelJOGL, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 800, 690));

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descarga.jpg"))); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 232, -1, -1));

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Animales", "Paises", "Cosas" }));
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 40, 85, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/descarga.jpg"))); // NOI18N
        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, -1, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/23249.jpg"))); // NOI18N
        jButton2.setText("Parar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 650, -1, -1));

        button_1.setBackground(new java.awt.Color(0, 0, 0));
        button_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/IMG_7127.JPG"))); // NOI18N
        button_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_1ActionPerformed(evt);
            }
        });
        getContentPane().add(button_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, 20));

        button_2.setBackground(new java.awt.Color(0, 0, 0));
        button_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/b.JPG"))); // NOI18N
        getContentPane().add(button_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, -1, 20));

        button_3.setBackground(new java.awt.Color(0, 0, 0));
        button_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/c.JPG"))); // NOI18N
        button_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_3ActionPerformed(evt);
            }
        });
        getContentPane().add(button_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, -1, 20));

        button_4.setBackground(new java.awt.Color(0, 0, 0));
        button_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/d.JPG"))); // NOI18N
        button_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_4ActionPerformed(evt);
            }
        });
        getContentPane().add(button_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));

        button_5.setBackground(new java.awt.Color(0, 0, 0));
        button_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/e.JPG"))); // NOI18N
        button_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_5ActionPerformed(evt);
            }
        });
        getContentPane().add(button_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, -1, -1));

        button_6.setBackground(new java.awt.Color(0, 0, 0));
        button_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/f.JPG"))); // NOI18N
        button_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_6ActionPerformed(evt);
            }
        });
        getContentPane().add(button_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, -1, -1));

        button_7.setBackground(new java.awt.Color(0, 0, 0));
        button_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/g.JPG"))); // NOI18N
        button_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_7ActionPerformed(evt);
            }
        });
        getContentPane().add(button_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        button_8.setBackground(new java.awt.Color(0, 0, 0));
        button_8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/h.JPG"))); // NOI18N
        button_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_8ActionPerformed(evt);
            }
        });
        getContentPane().add(button_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, -1, -1));

        button_9.setBackground(new java.awt.Color(0, 0, 0));
        button_9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/i.JPG"))); // NOI18N
        button_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_9ActionPerformed(evt);
            }
        });
        getContentPane().add(button_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, -1, -1));

        button_10.setBackground(new java.awt.Color(0, 0, 0));
        button_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/j.JPG"))); // NOI18N
        button_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_10ActionPerformed(evt);
            }
        });
        getContentPane().add(button_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        button_11.setBackground(new java.awt.Color(0, 0, 0));
        button_11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/k.JPG"))); // NOI18N
        button_11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_11ActionPerformed(evt);
            }
        });
        getContentPane().add(button_11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, -1, -1));

        button_12.setBackground(new java.awt.Color(0, 0, 0));
        button_12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/l.JPG"))); // NOI18N
        button_12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_12ActionPerformed(evt);
            }
        });
        getContentPane().add(button_12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, -1, -1));

        button_13.setBackground(new java.awt.Color(0, 0, 0));
        button_13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/m.JPG"))); // NOI18N
        button_13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_13ActionPerformed(evt);
            }
        });
        getContentPane().add(button_13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, -1));

        button_14.setBackground(new java.awt.Color(0, 0, 0));
        button_14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/n.JPG"))); // NOI18N
        button_14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_14ActionPerformed(evt);
            }
        });
        getContentPane().add(button_14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, -1));

        button_15.setBackground(new java.awt.Color(0, 0, 0));
        button_15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/ñ.jpeg"))); // NOI18N
        button_15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_15ActionPerformed(evt);
            }
        });
        getContentPane().add(button_15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, -1, -1));

        button_16.setBackground(new java.awt.Color(0, 0, 0));
        button_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/o.JPG"))); // NOI18N
        button_16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_16ActionPerformed(evt);
            }
        });
        getContentPane().add(button_16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, -1, -1));

        button_17.setBackground(new java.awt.Color(0, 0, 0));
        button_17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/p.JPG"))); // NOI18N
        button_17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_17ActionPerformed(evt);
            }
        });
        getContentPane().add(button_17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, -1, -1));

        button_18.setBackground(new java.awt.Color(0, 0, 0));
        button_18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/q.JPG"))); // NOI18N
        button_18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_18ActionPerformed(evt);
            }
        });
        getContentPane().add(button_18, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, -1, -1));

        button_19.setBackground(new java.awt.Color(0, 0, 0));
        button_19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/r.JPG"))); // NOI18N
        button_19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_19ActionPerformed(evt);
            }
        });
        getContentPane().add(button_19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        button_20.setBackground(new java.awt.Color(0, 0, 0));
        button_20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/s.JPG"))); // NOI18N
        button_20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_20ActionPerformed(evt);
            }
        });
        getContentPane().add(button_20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, -1, -1));

        button_21.setBackground(new java.awt.Color(0, 0, 0));
        button_21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/t.JPG"))); // NOI18N
        button_21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_21ActionPerformed(evt);
            }
        });
        getContentPane().add(button_21, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, -1, -1));

        button_22.setBackground(new java.awt.Color(0, 0, 0));
        button_22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/u.JPG"))); // NOI18N
        button_22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_22ActionPerformed(evt);
            }
        });
        getContentPane().add(button_22, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, -1, -1));

        button_23.setBackground(new java.awt.Color(0, 0, 0));
        button_23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/v.JPG"))); // NOI18N
        button_23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_23ActionPerformed(evt);
            }
        });
        getContentPane().add(button_23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 510, -1, -1));

        button_24.setBackground(new java.awt.Color(0, 0, 0));
        button_24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/w.JPG"))); // NOI18N
        button_24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_24ActionPerformed(evt);
            }
        });
        getContentPane().add(button_24, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, -1, -1));

        button_25.setBackground(new java.awt.Color(0, 0, 0));
        button_25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/x.JPG"))); // NOI18N
        button_25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_25ActionPerformed(evt);
            }
        });
        getContentPane().add(button_25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, -1));

        button_26.setBackground(new java.awt.Color(0, 0, 0));
        button_26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/y.JPG"))); // NOI18N
        button_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_26ActionPerformed(evt);
            }
        });
        getContentPane().add(button_26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, -1, -1));

        button_27.setBackground(new java.awt.Color(0, 0, 0));
        button_27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenBotones/z.JPG"))); // NOI18N
        button_27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_27ActionPerformed(evt);
            }
        });
        getContentPane().add(button_27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, -1, -1));
        getContentPane().add(txtPalabra, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 210, 60));

        errores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(errores, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 220, 50));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/wood.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 370, 200));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/wood.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 340, 110));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/wood.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 360, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed


   

        if (JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro de querer una palabra nueva?",
                "Ahorcado", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
            OpcionElegida(evt);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        animador.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        animador.stop();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void panelJOGLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJOGLMouseExited

    }//GEN-LAST:event_panelJOGLMouseExited

    private void panelJOGLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJOGLMouseEntered

    }//GEN-LAST:event_panelJOGLMouseEntered

    private void panelJOGLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJOGLMouseClicked

    }//GEN-LAST:event_panelJOGLMouseClicked

    private void button_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_1ActionPerformed

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
            java.util.logging.Logger.getLogger(AppHangedIU1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppHangedIU1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppHangedIU1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppHangedIU1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
 /*ExText ex = new ExText();
    ex.initialize(args);
    ex.buildUniverse();
    ex.showFrame();*/
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppHangedIU1().setVisible(true);
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
    private javax.swing.JLabel errores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel panelJOGL;
    private javax.swing.JTextField txtPalabra;
    // End of variables declaration//GEN-END:variables
}
