/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extext;
import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Enumeration;
import java.util.EventListener;

import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Group;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import hangedform.AppHangedIU1;

/**
 *
 * @author usuario
 */
public class ExText extends Java3DFrame{
  //--------------------------------------------------------------
  //  SCENE CONTENT
  //--------------------------------------------------------------

  //
  //  Nodes (updated via menu)
  //
  private FontExtrusion extrusion = null;

  private Shape3D shape = null;

  private Group scene = null;

  private BranchGroup textGroup = null;
AppHangedIU1 app;
  //
  //  Build scene
  //
  public Group buildScene() {
    // Get the current font attributes
    Font font = (Font) fonts[currentFont].value;
    String textString = fonts[currentFont].name;

    // Turn on the example headlight
    setHeadlightEnable(true);

    // Build the scene group
    scene = new Group();
    scene.setCapability(Group.ALLOW_CHILDREN_EXTEND);
    scene.setCapability(Group.ALLOW_CHILDREN_WRITE);

    // Build a branch group to hold the text shape
    // (this allows us to remove the text shape later,
    // change it, then put it back, all under menu control)
    textGroup = new BranchGroup();
    textGroup.setCapability(BranchGroup.ALLOW_DETACH);
    scene.addChild(textGroup);

    // BEGIN EXAMPLE TOPIC
    // Create a font extrusion with a default extrusion shape
    extrusion = new FontExtrusion();

    // Define a 3D font with a default extrusion path
    Font3D font3d = new Font3D(font, extrusion);

    // Build 3D text geometry using the 3D font
    Text3D tex = new Text3D();
    tex.setFont3D(font3d);
    tex.setString(textString);
    tex.setAlignment(Text3D.ALIGN_CENTER);

    // Define a generic shaded appearance
    Appearance app = new Appearance();
    Material mat = new Material();
    mat.setLightingEnable(true);
    app.setMaterial(mat);

    // Assemble geometry and appearance into a shape
    // and add it to the scene
    shape = new Shape3D(tex, app);
    shape.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
    textGroup.addChild(shape);
    // END EXAMPLE TOPIC

    return scene;
  }

  //--------------------------------------------------------------
  //  USER INTERFACE
  //--------------------------------------------------------------

  //
  //  Main
  //
  public static void main(String[] args,String palab) {
    ExText ex = new ExText();
    ex.initialize(args,palab);
    ex.buildUniverse();
    ex.showFrame();
  }

  //  Font menu choices
  private NameValue[] fonts = null;
String palabras;
  private int currentFont = 0;

  private CheckboxMenu fontMenu = null;

  //
  //  Initialize the GUI (application and applet)
  //
  public void initialize(String[] args,String palab) {
       // Initialize the window, menubar, etc.
    super.initialize(args);
    exampleFrame.setTitle("Java 3D Text Example");

    //
    //  Add a menubar menu to change node parameters
    //    Font -->
    //

    Menu m = new Menu("Font3D");

    // Get a list of available fonts
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    //String[] fontNames = ge.getAvailableFontFamilyNames();
    //String pala=app.a(palab);
    String[] fontNames ={palab};
    fonts = new NameValue[fontNames.length];
    Font newFont = null;

    // Add those fonts to a list and build the font menu
    if (debug)
      System.err.println("Loading fonts...");
    for (int i = 0; i < fontNames.length; i++) {
      if (debug)
        System.err.println("  " + fontNames[i]);
      newFont = new Font(fontNames[i], Font.PLAIN, 1);
      fonts[i] = new NameValue(fontNames[i], newFont);
    }
    fontMenu = new CheckboxMenu("Font", fonts, currentFont, this);
    m.add(fontMenu);

    exampleMenuBar.add(m);
  }

  //
  //  Handle checkboxes and menu choices
  //
  public void checkboxChanged(CheckboxMenu menu, int check) {
    if (menu == fontMenu) {
      // Change the 2D font used to build a 3D font
      currentFont = check;
      String fontName = fonts[currentFont].name;
      Font font = (Font) fonts[currentFont].value;
      Font3D font3d = new Font3D(font, extrusion);

      Text3D tex = new Text3D();
      tex.setFont3D(font3d);
      tex.setString(fontName);
      tex.setAlignment(Text3D.ALIGN_CENTER);

      scene.removeChild(0);
      shape.setGeometry(tex);
      scene.addChild(textGroup);

      if (debug)
        System.err.println("Font set to " + fontName);
      return;
    }

    // Handle all other checkboxes
    super.checkboxChanged(menu, check);
  }

}