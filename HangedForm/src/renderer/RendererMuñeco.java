package renderer;





import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import com.jogamp.opengl.GLEventListener;



import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import modelo.Punto;



public class RendererMuñeco implements GLEventListener {

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
    //muñeco
float elevacion =0.0f;
    float azimitud =0.0f;
float giro=0.0f;

float DEF_floorGridScale=1.0f;
float DEF_floorGridXSteps=10.0f;
 float DEF_floorGridZSteps=10.0f;

 public RendererMuñeco() {
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
       
//Muñeco

float light_ambient[]={0.75f, 0.75f, 0.75f, 1.0f};
float light_diffuse[]={1.0f, 1.0f, 1.0f, 1.0f};
float light_specular[]={1.0f, 1.0f, 1.0f, 1.0f};
float light_position[]={0.0f, 0.0f, 1.0f, 0.0f};
	
	
	gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_AMBIENT,light_ambient,0);
	gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_diffuse,0);
	gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_specular,0);
	gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position,0);
	
	
	gl.glEnable(GL2.GL_LIGHTING);
	gl.glEnable(GL2.GL_LIGHT0);
	gl.glDepthFunc(GL2.GL_LESS);
	gl.glEnable(GL2.GL_DEPTH_TEST);



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
     //material robot
     	float mat_ambient_cubo[]= {0.19225f, 0.19225f, 0.19225f, 0.4f};
	float mat_diffuse_cubo[]= {0.50754f, 0.50754f, 0.50754f, 0.4f};
	float mat_specular_cubo[]= {0.508273f, 0.508273f, 0.508273f, 0.4f};
	
	float mat_ambient_caja[]= {0.19225f, 0.19225f, 0.19225f, 0.4f};
	//tener en cuenta
        float mat_diffuse_caja[]= {0.255f, 0.412f, 0.882f, 0.4f};
	//--
        float mat_specular_caja[]= {0.508273f, 0.508273f, 0.508273f, 0.4f};
	
	float mat_ambient_esfera[]= {0.1f, 0.1f, 0.1f, 1.0f};
	float mat_diffuse_esfera[]= {1.0f, 1.0f ,1.0f, 1.0f};
	float mat_specular_esfera[]= {0.8f, 0.8f, 0.8f, 1.0f};
	
	float mat_ambient_ojo[]= {0.1f, 0.1f, 0.1f, 1.0f};
	float mat_diffuse_ojo[]= {0.0f, 0.0f ,0.0f, 0.0f};
	float mat_specular_ojo[]= {0.8f, 0.8f, 0.8f, 1.0f};
	
	
	float zExtent=0.0f, xExtent=0.0f, xLocal = 0.0f, zLocal=0.0f;
        //inicialize valores
	float loopX, loopZ;
	//se cambio de int a float
	gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
	gl.glMatrixMode(GL2.GL_MODELVIEW_MATRIX);
	gl.glLoadIdentity();
	polarView(15.0f, giro, elevacion, azimitud);
	
	gl.glPushMatrix();
	gl.glPushAttrib(GL2.GL_LIGHTING_BIT);
	gl.glDisable(GL2.GL_LIGHTING);
	gl.glColor3f(0.0f, 0.7f, 0.7f);
	gl.glBegin(GL2.GL_LINES);
	
	zExtent= DEF_floorGridScale = DEF_floorGridZSteps;
	for(loopX = -DEF_floorGridXSteps; loopX<=DEF_floorGridXSteps; loopX++){
		xLocal= DEF_floorGridScale= loopX;
		gl.glVertex3f(xLocal, 0.0f, -zExtent);
		gl.glVertex3f(xLocal, 0.0f,  zExtent);
	}
	
	
	xExtent= DEF_floorGridScale = DEF_floorGridXSteps;
	for(loopZ = -DEF_floorGridZSteps; loopZ<=DEF_floorGridZSteps; loopZ++){
		zLocal= DEF_floorGridScale= loopZ;
		gl.glVertex3f(-xExtent, 0.0f, xLocal);
		gl.glVertex3f(xExtent, 0.0f,  xLocal);
	}
	
	gl.glEnd();
	gl.glPopAttrib();
	gl.glPopMatrix();


//primera parte de las patas
float v1[]={100.0f};
gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_AMBIENT, mat_ambient_cubo,0);
gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse_cubo,0);
gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_SPECULAR, mat_specular_cubo,0);
gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_SHININESS,v1,0);
gl.glPushMatrix();
gl.glTranslated(0, 0.2, 1.0);
glut.glutSolidSphere(0.1, 50, 50);
gl.glPopMatrix();

	
//segunda parte de las patas
gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_AMBIENT, mat_ambient_cubo,0);
gl.glMaterialfv(GL2.GL_FRONT,GL2.GL_DIFFUSE, mat_diffuse_cubo,0);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo,0);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.25, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
////tercera parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.3, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////cuarta parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.35, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
////quinta parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.4, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
////sexta parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.45, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////setima parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.5, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////octava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.55, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////novena parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.6, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////decima parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.65, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
//
////onceava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.7, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
//
//
////doceava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.75, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
//
////treceava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.8, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////catorceava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.85, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
//
////quinceava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.9, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////dieciseisava parte de las patas
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.95, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////dibujar pie1
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0, 0.1, 1.0);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.2);
//glPopMatrix();
//
//
//
////dibujar pie1/2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.06, 0.1, 1.19);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.2);
//glPopMatrix();
//
//
//
////Pata izq1
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.2, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.25, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq3
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.3, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq4
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.35, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq5
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.4, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq6
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.45, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq7
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.5, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq8
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.55, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq9
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.6, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq10
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.65, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq11
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.7, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq12
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.75, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq13
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.8, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq14
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.85, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq15
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.9, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
////Pata izq16
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.95, 1.0);
//glutSolidSphere(0.1, 50, 50);
//glPopMatrix();
//
//
//
//
//
////dibujar pie2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.6, 0.1, 1.0);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.2);
//glPopMatrix();
//
//
//
////dibujar pie2/2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.65, 0.1, 1.19);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.2);
//glPopMatrix();
//
//
//
//
//
////cuerpo
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 1, 1.2);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.8);
//glPopMatrix();
//
//
////cuerpo2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_caja);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_caja);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_caja);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 1, 1.4);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.6);
//glPopMatrix();
//
//
//
////cuello1
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 1.2, 1.0);
//glutSolidSphere(0.2, 50, 50);
//glPopMatrix();
//
//
//
//
////cuello2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 1.3, 1.0);
//glutSolidSphere(0.2, 50, 50);
//glPopMatrix();
//
//
////cuello4
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 1.4, 1.0);
//glutSolidSphere(0.2, 50, 50);
//glPopMatrix();
//
//
//
////cuello5
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 1.5, 1.0);
//glutSolidSphere(0.2, 50, 50);
//glPopMatrix();
//
//
//
//
////cuerpo
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2, 1.2);
//glRotatef(15.0, 0.0, 1.0, 0.0);
//glutSolidCube(0.8);
//glPopMatrix();
//
//
//
////ojo1
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_esfera);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_esfera);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_esfera);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.22, 2, 1.6);
//glutSolidSphere(0.18, 50, 50);
//glPopMatrix();
//
//
////ojo2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_esfera);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_esfera);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_esfera);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.62, 2, 1.5);
//glutSolidSphere(0.18, 50, 50);
//glPopMatrix();
//
//
//
////pupila 1
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_ojo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_ojo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_ojo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.21, 1.95, 1.75);
//glutSolidSphere(0.05, 50, 50);
//glPopMatrix();
//
//
////pupila 2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_ojo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_ojo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_ojo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.68, 2.05, 1.65);
//glutSolidSphere(0.05, 50, 50);
//glPopMatrix();
//
//
////antena
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.4, 1.2);
//glutSolidSphere(0.08, 50, 50);
//glPopMatrix();
//
//
////antena2
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.45, 1.2);
//glutSolidSphere(0.075, 50, 50);
//glPopMatrix();
//
//
////antena3
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.5, 1.2);
//glutSolidSphere(0.07, 50, 50);
//glPopMatrix();
//
//
////antena4
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.55, 1.2);
//glutSolidSphere(0.065, 50, 50);
//glPopMatrix();
//
//
//
//
////antena5
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.6, 1.2);
//glutSolidSphere(0.06, 50, 50);
//glPopMatrix();
//
//
//
//
//
//
////antena6
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.65, 1.2);
//glutSolidSphere(0.055, 50, 50);
//glPopMatrix();
//
//
//
//
//
//
////antena7
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.7, 1.2);
//glutSolidSphere(0.05, 50, 50);
//glPopMatrix();
//
//
//
//
////antena8
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.75, 1.2);
//glutSolidSphere(0.045, 50, 50);
//glPopMatrix();
//
//
//
//
//
//
////antena9
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.8, 1.2);
//glutSolidSphere(0.04, 50, 50);
//glPopMatrix();
//
//
//
////antena10
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.85, 1.2);
//glutSolidSphere(0.035, 50, 50);
//glPopMatrix();
//
//
////antena11
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.9, 1.2);
//glutSolidSphere(0.03, 50, 50);
//glPopMatrix();
//
//
////antena12
//glMaterialfv(GL_FRONT, GL_AMBIENT, mat_ambient_cubo);
//glMaterialfv(GL_FRONT, GL_DIFFUSE, mat_diffuse_cubo);
//glMaterialfv(GL_FRONT, GL_SPECULAR, mat_specular_cubo);
//glMaterialfv(GL_FRONT, GL_SHININESS, 100.0f);
//glPushMatrix();
//glTranslate(0.3, 2.95, 1.2);
//glutSolidSphere(0.08, 50, 50);
//glPopMatrix();
//glFlush();

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
public void polarView(float distance, Float twist, float elevation, float azimuth){
	gl.glTranslated(0.0, 0.0, -distance);
	gl.glRotated(-twist, 0.0, 0.0, 1.0);
	gl.glRotated(-elevation, 1.0, 0.0, 0.0);
	gl.glRotated(-azimuth, 0.0, 0.0, 1.0);
}


   

 
   


}
