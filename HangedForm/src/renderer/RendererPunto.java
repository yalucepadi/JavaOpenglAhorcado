package renderer;





import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import com.jogamp.opengl.GLEventListener;



import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import modelo.Punto;



public class RendererPunto implements GLEventListener {

    // Atributos
    // Punto a ser representado
    private Punto punto;
    // Interfaz con funciones de OPenGL
    private GL2 gl;
    // Librerias para establecer en Vista de Perspectiva
    private GLU glu;
    // Libretia GLUT
    private GLUT glut;
    // Factores de traslación
    double tx;
    double ty;
    double tz;

    public RendererPunto() {
        glu = new GLU();
        glut = new GLUT();
        punto = new Punto();
        tx=0;
        ty=0;
        tz=0;
    }
    

    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }


    @Override
    public void dispose(GLAutoDrawable glad) {

    }
    public void cambiarFactoresTraslacion(double tx, double ty, double tz){
        this.tx=tx;
        this.ty = ty;
        this.tz = tz;
    }

    @Override
    public void init(GLAutoDrawable glad) {
          // Crear una Interfaz con OPENGL 2.0        
        gl = glad.getGL().getGL2();
        // Recuperar el ancho y alto de ventama de visualización
        int w = ((java.awt.Component) glad).getWidth();
        int h = ((java.awt.Component) glad).getHeight();
        // Establecer un visor en todo el area de la ventana de visualización
        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        // Aspect is width/height
        double aspect = w / h;
        glu.gluPerspective(60.0, aspect, 0.1, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Establecer el uso de matrices en Modelo-Vista
        glu.gluLookAt(
                0, 0, 50,
                0, 0, 0,
                0, 1, 0
        );
        // Habilitar el Buffer de colores RGB
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
    }

    @Override
    public void display(GLAutoDrawable glad) {
           // invocar al encargado de dibujar (canvas)
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl = glad.getGL().getGL2();
        gl.glColor3d(1, 0, 0); // Rojo
        // Trasladar el cubo
        gl.glPushMatrix();
        gl.glTranslated(tx, ty, tz);
        // Dibujar un objeto 3D
        glut.glutWireSphere(10,20,20);
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
      
    }


   

 
   


}
