/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extext;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnElapsedFrames;

/**
 *
 * @author usuario
 */
public class ExamineViewerBehavior extends ViewerBehavior  {
  // Previous cursor location
  protected int previousX = 0;

  protected int previousY = 0;

  // Saved standard cursor
  protected Cursor savedCursor = null;

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into a
   * transform group given later with the setTransformGroup( ) method.
   */
  public ExamineViewerBehavior() {
    super();
  }

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into a
   * transform group given later with the setTransformGroup( ) method.
   * 
   * @param parent
   *            The AWT Component that contains the area generating mouse
   *            events.
   */
  public ExamineViewerBehavior(Component parent) {
    super(parent);
  }

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into the
   * given transform group.
   * 
   * @param transformGroup
   *            The transform group to be modified by the behavior.
   */
  public ExamineViewerBehavior(TransformGroup transformGroup) {
    super();
    subjectTransformGroup = transformGroup;
  }

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into the
   * given transform group.
   * 
   * @param transformGroup
   *            The transform group to be modified by the behavior.
   * @param parent
   *            The AWT Component that contains the area generating mouse
   *            events.
   */
  public ExamineViewerBehavior(TransformGroup transformGroup, Component parent) {
    super(parent);
    subjectTransformGroup = transformGroup;
  }

  /**
   * Respond to a button1 event (press, release, or drag).
   * 
   * @param mouseEvent
   *            A MouseEvent to respond to.
   */
  public void onButton1(MouseEvent mev) {
    if (subjectTransformGroup == null)
      return;

    int x = mev.getX();
    int y = mev.getY();

    if (mev.getID() == MouseEvent.MOUSE_PRESSED) {
      // Mouse button pressed: record position
      previousX = x;
      previousY = y;

      // Change to a "move" cursor
      if (parentComponent != null) {
        savedCursor = parentComponent.getCursor();
        parentComponent.setCursor(Cursor
            .getPredefinedCursor(Cursor.HAND_CURSOR));
      }
      return;
    }
    if (mev.getID() == MouseEvent.MOUSE_RELEASED) {
      // Mouse button released: do nothing

      // Switch the cursor back
      if (parentComponent != null)
        parentComponent.setCursor(savedCursor);
      return;
    }

    //
    // Mouse moved while button down: create a rotation
    //
    // Compute the delta in X and Y from the previous
    // position. Use the delta to compute rotation
    // angles with the mapping:
    //
    //   positive X mouse delta --> positive Y-axis rotation
    //   positive Y mouse delta --> positive X-axis rotation
    //
    // where positive X mouse movement is to the right, and
    // positive Y mouse movement is **down** the screen.
    //
    int deltaX = x - previousX;
    int deltaY = y - previousY;

    if (deltaX > UNUSUAL_XDELTA || deltaX < -UNUSUAL_XDELTA
        || deltaY > UNUSUAL_YDELTA || deltaY < -UNUSUAL_YDELTA) {
      // Deltas are too huge to be believable. Probably a glitch.
      // Don't record the new XY location, or do anything.
      return;
    }

    double xRotationAngle = deltaY * XRotationFactor;
    double yRotationAngle = deltaX * YRotationFactor;

    //
    // Build transforms
    //
    transform1.rotX(xRotationAngle);
    transform2.rotY(yRotationAngle);

    // Get and save the current transform matrix
    subjectTransformGroup.getTransform(currentTransform);
    currentTransform.get(matrix);
    translate.set(matrix.m03, matrix.m13, matrix.m23);

    // Translate to the origin, rotate, then translate back
    currentTransform.setTranslation(origin);
    currentTransform.mul(transform1, currentTransform);
    currentTransform.mul(transform2, currentTransform);
    currentTransform.setTranslation(translate);

    // Update the transform group
    subjectTransformGroup.setTransform(currentTransform);

    previousX = x;
    previousY = y;
  }

  /**
   * Respond to a button2 event (press, release, or drag).
   * 
   * @param mouseEvent
   *            A MouseEvent to respond to.
   */
  public void onButton2(MouseEvent mev) {
    if (subjectTransformGroup == null)
      return;

    int x = mev.getX();
    int y = mev.getY();

    if (mev.getID() == MouseEvent.MOUSE_PRESSED) {
      // Mouse button pressed: record position
      previousX = x;
      previousY = y;

      // Change to a "move" cursor
      if (parentComponent != null) {
        savedCursor = parentComponent.getCursor();
        parentComponent.setCursor(Cursor
            .getPredefinedCursor(Cursor.MOVE_CURSOR));
      }
      return;
    }
    if (mev.getID() == MouseEvent.MOUSE_RELEASED) {
      // Mouse button released: do nothing

      // Switch the cursor back
      if (parentComponent != null)
        parentComponent.setCursor(savedCursor);
      return;
    }

    //
    // Mouse moved while button down: create a translation
    //
    // Compute the delta in Y from the previous
    // position. Use the delta to compute translation
    // distances with the mapping:
    //
    //   positive Y mouse delta --> positive Y-axis translation
    //
    // where positive X mouse movement is to the right, and
    // positive Y mouse movement is **down** the screen.
    //
    int deltaY = y - previousY;

    if (deltaY > UNUSUAL_YDELTA || deltaY < -UNUSUAL_YDELTA) {
      // Deltas are too huge to be believable. Probably a glitch.
      // Don't record the new XY location, or do anything.
      return;
    }

    double zTranslationDistance = deltaY * ZTranslationFactor;

    //
    // Build transforms
    //
    translate.set(0.0, 0.0, zTranslationDistance);
    transform1.set(translate);

    // Get and save the current transform
    subjectTransformGroup.getTransform(currentTransform);

    // Translate as needed
    currentTransform.mul(transform1, currentTransform);

    // Update the transform group
    subjectTransformGroup.setTransform(currentTransform);

    previousX = x;
    previousY = y;
  }

  /**
   * Respond to a button3 event (press, release, or drag).
   * 
   * @param mouseEvent
   *            A MouseEvent to respond to.
   */
  public void onButton3(MouseEvent mev) {
    if (subjectTransformGroup == null)
      return;

    int x = mev.getX();
    int y = mev.getY();

    if (mev.getID() == MouseEvent.MOUSE_PRESSED) {
      // Mouse button pressed: record position
      previousX = x;
      previousY = y;

      // Change to a "move" cursor
      if (parentComponent != null) {
        savedCursor = parentComponent.getCursor();
        parentComponent.setCursor(Cursor
            .getPredefinedCursor(Cursor.MOVE_CURSOR));
      }
      return;
    }
    if (mev.getID() == MouseEvent.MOUSE_RELEASED) {
      // Mouse button released: do nothing

      // Switch the cursor back
      if (parentComponent != null)
        parentComponent.setCursor(savedCursor);
      return;
    }

    //
    // Mouse moved while button down: create a translation
    //
    // Compute the delta in X and Y from the previous
    // position. Use the delta to compute translation
    // distances with the mapping:
    //
    //   positive X mouse delta --> positive X-axis translation
    //   positive Y mouse delta --> negative Y-axis translation
    //
    // where positive X mouse movement is to the right, and
    // positive Y mouse movement is **down** the screen.
    //
    int deltaX = x - previousX;
    int deltaY = y - previousY;

    if (deltaX > UNUSUAL_XDELTA || deltaX < -UNUSUAL_XDELTA
        || deltaY > UNUSUAL_YDELTA || deltaY < -UNUSUAL_YDELTA) {
      // Deltas are too huge to be believable. Probably a glitch.
      // Don't record the new XY location, or do anything.
      return;
    }

    double xTranslationDistance = deltaX * XTranslationFactor;
    double yTranslationDistance = -deltaY * YTranslationFactor;

    //
    // Build transforms
    //
    translate.set(xTranslationDistance, yTranslationDistance, 0.0);
    transform1.set(translate);

    // Get and save the current transform
    subjectTransformGroup.getTransform(currentTransform);

    // Translate as needed
    currentTransform.mul(transform1, currentTransform);

    // Update the transform group
    subjectTransformGroup.setTransform(currentTransform);

    previousX = x;
    previousY = y;
  }

  /**
   * Respond to an elapsed frames event (assuming subclass has set up a wakeup
   * criterion for it).
   * 
   * @param time
   *            A WakeupOnElapsedFrames criterion to respond to.
   */
  public void onElapsedFrames(WakeupOnElapsedFrames timeEvent) {
    // Can't happen
  }
}