package renderer;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import com.jogamp.opengl.GLEventListener;

import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.gl2.GLUT;
import hangedform.AppHangedIU;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import modelo.Punto;

public class RendererMuñeco implements GLEventListener, KeyListener {

    static int slices = 16;
    static int stacks = 16;
    float ss = 0;
    float aa = 0;
    int er;
    // Atributos
    // Punto a ser representado
    //private Punto punto;
    // Interfaz con funciones de OPenGL
    private GL2 gl;
    // Librerias para establecer en Vista de Perspectiva
    private GLU glu;
    // Libretia GLUT
    private GLUT glut;

    // Factores de traslación
    float tx;
    float ty;
    float tz;
    float an;
    //muñeco
    //  GLUquadric

    public RendererMuñeco() {

        glu = new GLU();
        glut = new GLUT();
        //  punto = new Punto();
        tx = 0;
        ty = 0;
        tz = 0;
        an = 0;
        er = 0;
    }

//    public Punto getPunto() {
//        return punto;
//    }
//
//    public void setPunto(Punto punto) {
//        this.punto = punto;
//    }
//
    @Override
    public void dispose(GLAutoDrawable glad) {

    }

    public void cambiarFactoresTraslacion(float tx, float ty, float tz) {
        this.tx = tx;
        this.ty = ty;
        this.tz = tz;
    }

    @Override
    public void init(GLAutoDrawable glad) {
        // Crear una Interfaz con OPENGL 2.0        
        gl = glad.getGL().getGL2();

        // Recuperar el ancho y alto de ventama de visualización
        int w = ((Component) glad).getWidth();
        int h = ((Component) glad).getHeight();
        // Establecer un visor en todo el area de la ventana de visualización
        gl.glViewport(0, 0, w, h);
        // Establecer el uso de matrices en Modelo-Vista
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Carga la matriz identidad
        // gl.glLoadIdentity();
        // Posición de la camara
        glu.gluLookAt(
                0, 0, 50,
                0, 0, 0,
                0, 1, 0
        );

        // Habilitar el Buffer de colores RGB
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // Aspect is width/height
        double aspect = w / h;
        glu.gluPerspective(60.0, aspect, 0.1, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    public void sombrero() {
        gl.glPushMatrix();
        gl.glTranslated(0, 2.8, 6);
        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glColor3ub((byte) 45, (byte) 223, (byte) 13);
        glut.glutSolidCylinder(1, 1, slices, stacks);
        gl.glColor3ub((byte) 84, (byte) 153, (byte) 71);
        glut.glutSolidCube(1);
        gl.glTranslated(0, 1.6, 6);
        gl.glPopMatrix();

    }

    public void cabesa() {
        gl.glPushMatrix();
        gl.glTranslated(0, .8, -5.8);
        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(1.5, 1.5, 1.5);
        gl.glColor3ub((byte) 244, (byte) 224, (byte) 190);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();
        //mouse
        gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
        gl.glPushMatrix();
        gl.glTranslated(0, .3, -3);
        gl.glRotated(180, 0, .5, -1);
        gl.glScaled(.2, -.2, .2);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();
//white
        gl.glPushMatrix();
        gl.glTranslated(0, 0.1, -3);
        gl.glRotated(-180, 0, .5, -1);
        gl.glColor3f(1, 1, 1);
        gl.glScaled(.1, .1, .1);
        glut.glutSolidCube(.3f);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(-.04, 0.1, -3);
        gl.glRotated(-180, 0, .5, -1);
        gl.glColor3f(1, 1, 1);
        gl.glScaled(.1, .1, .1);
        glut.glutSolidCube(.3f);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(.04, 0.1, -3);
        gl.glRotated(-180, 0, .5, -1);
        gl.glColor3f(1, 1, 1);
        gl.glScaled(.1, .1, .1);
        glut.glutSolidCube(.3f);
        gl.glPopMatrix();
        //eye
//right
        gl.glPushMatrix();

        gl.glTranslated(.32, .73, -4);
        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(.1, .1, .1);
        gl.glColor3f(0, 0, 0);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();
//white
        gl.glPushMatrix();

        gl.glTranslated(.35, .8, -4.3);
        gl.glRotated(90, 0, 1, 0);
        gl.glScaled(.2, .3, .2);
        gl.glColor3f(1, 1, 1);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

        //left
        gl.glPushMatrix();

        gl.glTranslated(-.32, .73, -4);
        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(.1, .1, .1);
        gl.glColor3f(0, 0, 0);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

//white
        gl.glPushMatrix();

        gl.glTranslated(-.35, .8, -4.3);
        gl.glRotated(90, 0, 1, 0);
        gl.glScaled(.2, .3, .2);
        gl.glColor3f(1, 1, 1);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

//        upeye
        //      left
        gl.glPushMatrix();
        gl.glTranslated(-.4, 1.3, -4.3);
        gl.glRotated(120, 1, 0, 1);
        gl.glScaled(.05, .2, .2);
        gl.glColor3f(0, 0, 0);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();

        //    right
        gl.glPushMatrix();
        gl.glTranslated(.35, 1.3, -4.3);
        gl.glRotated(120, 1, 0, 1);
        gl.glScaled(.05, .2, .2);
        gl.glColor3f(0, 0, 0);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();

        gl.glPopMatrix();

    }

    public void tronco() {
        //Cloth
        gl.glPushMatrix();
        gl.glTranslated(0, -1.6, -6);
        gl.glRotated(-90, 0.1, 0, 0);
        gl.glColor3ub((byte) 255, (byte) 95, (byte) 0);
        gl.glScaled(1.5, 1.7, 2);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();

//second
        gl.glPushMatrix();
        gl.glTranslated(0, -.35, -5.7);
        gl.glRotated(-90, 0.1, 0, 0);
        gl.glColor3ub((byte) 0, (byte) 234, (byte) 0);
        gl.glScaled(1, 1, 1);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();

    }

    public void extremidadesS() {
        //hand
        gl.glColor3ub((byte) 45, (byte) 223, (byte) 13);
        gl.glPushMatrix();
        gl.glTranslated(1, -.55, -6);
        gl.glRotated(60, 0, 1, 0);
        gl.glScaled(1.3, .2, .5);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(-1, -.55, -6);
        gl.glRotated(-61, 0, 1, 0);
        gl.glScaled(1.3, .2, .5);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

    }

    public void extremidadesI() {
//left leg
        gl.glPushMatrix();

        gl.glTranslated(-.74, -2.2, -6);

        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(.9, .9, .9);
        gl.glColor3ub((byte) 128, (byte) 128, (byte) 128);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(-.74, -2.8, -6.1);
        gl.glRotated(-90, 0.1, 0, 0);
        gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
        gl.glScaled(.95, 1, 1);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();

//right leg
        gl.glPushMatrix();

        gl.glTranslated(.74, -2.2, -6);
        gl.glRotated(90, 1, 0, 0);
        gl.glScaled(.9, .9, .9);
        gl.glColor3ub((byte) 128, (byte) 128, (byte) 128);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(.74, -2.8, -6.1);
        gl.glRotated(-90, 0.1, 0, 0);
        gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
        gl.glScaled(.95, 1, 1);
        glut.glutSolidCone(1, 2, slices, stacks);
        gl.glPopMatrix();

    }

    public void controlarErrores(int er) {
        switch (er) {
            case 1:
                extremidadesI();
                break;
            case 2:
                extremidadesI();
                extremidadesS();
                break;
            case 3:
                extremidadesI();
                extremidadesS();
                tronco();

                break;
            case 4:
                extremidadesI();
                extremidadesS();
                tronco();
                cabesa();
                break;
            case 5:
                extremidadesI();
                extremidadesS();
                tronco();
                cabesa();
                sombrero();
                break;
        }
    }

    @Override
    public void display(GLAutoDrawable glad) {

// invocar al encargado de dibujar (canvas)
        gl = glad.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glRotatef(0.1f, 0, 1, 0);

        //an++;
//        tx++;
//        ty++;
        //tz++; 
        //System.out.println("Angulo:" + an + "X:" + tx + "Y:" + ty + "Z:" + tz);
        //gl.glColor3d(1, 0, 0);
        //sky
        gl.glPushMatrix();
        gl.glTranslated(2, 1, -10);
        gl.glRotated(180, 0, 1, 0);
        gl.glScaled(15, 15, 3);
        gl.glColor3ub((byte) 31, (byte) 41, (byte) 100);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();

//land
        gl.glPushMatrix();
        gl.glTranslated(2, -2.5, -10);
        gl.glRotated(180, 0, 1, 0);
        gl.glScaled(15, 10, 3);
        gl.glColor3ub((byte) 172, (byte) 209, (byte) 224);
        glut.glutSolidSphere(1, slices, stacks);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(aa, 0, ss);
        controlarErrores(er);
       
        
//cloth
        //tronco();
//3black
//        gl.glPushMatrix();
//
//        gl.glTranslated(0, -.5, -4);
//        gl.glRotated(90, 1, 0, 0);
//        gl.glScaled(.1, .1, .1);
//        gl.glColor3f(0, 0, 0);
//        glut.glutSolidSphere(1, slices, stacks);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//
//        gl.glTranslated(0, -.8, -4);
//        gl.glRotated(90, 1, 0, 0);
//        gl.glScaled(.1, .1, .1);
//        gl.glColor3f(0, 0, 0);
//        glut.glutSolidSphere(1, slices, stacks);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//
//        gl.glTranslated(0, -1.1, -4);
//        gl.glRotated(90, 1, 0, 0);
//        gl.glScaled(.1, .1, .1);
//        gl.glColor3f(0, 0, 0);
//        glut.glutSolidSphere(1, slices, stacks);
//        gl.glPopMatrix();
        //second
        //face
        //Sombrero();
        //cabesa();
//left leg
//right leg
        //extremidadesI();
        //hand
        //extremidadesS();
        //mouse
        //white
//eye
//right
//white
//white
//        upeye
        //      left
        //    right
        //System.out.println("aa:"+aa+" ss:"+ss);
    }

    @Override
    public void reshape(GLAutoDrawable glad, int w, int h, int i2, int i3) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_W:
                aa += 0.01;
                break;
            case KeyEvent.VK_S:
                ss -= 0.01;
                break;
            case KeyEvent.VK_A:
                ss -= 0.01;
                break;
            case KeyEvent.VK_D:
                ss += 0.01;
                break;
            default:
                break;
        }
        AppHangedIU.glCanvas.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
