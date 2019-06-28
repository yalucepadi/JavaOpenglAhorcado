/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extext;

import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author usuario
 */
public class CheckboxMenu extends Menu implements ItemListener{
      // State
  protected CheckboxMenuItem[] checks =null;

  protected int current = 0;

  protected CheckboxMenuListener listener = null;

  //  Construct
  public CheckboxMenu(String name, NameValue[] items,
      CheckboxMenuListener listen) {
    this(name, items, 0, listen);
  }

  public CheckboxMenu(String name, NameValue[] items, int cur,
      CheckboxMenuListener listen) {
    super(name);
    current = cur;
    listener = listen;

    if (items == null)
      return;

    checks = new CheckboxMenuItem[items.length];
    for (int i = 0; i < items.length; i++) {
      checks[i] = new CheckboxMenuItem(items[i].name, false);
      checks[i].addItemListener(this);
      add(checks[i]);
    }
    checks[cur].setState(true);
  }

  //  Handle checkbox changed events
  public void itemStateChanged(ItemEvent event) {
    Object src = event.getSource();

    for (int i = 0; i < checks.length; i++) {
      if (src == checks[i]) {
        // Update the checkboxes
        checks[current].setState(false);
        current = i;
        checks[current].setState(true);

        if (listener != null)
          listener.checkboxChanged(this, i);
        return;
      }
    }
  }

  // Methods to get and set state
  public int getCurrent() {
    return current;
  }

  public void setCurrent(int cur) {
    if (cur < 0 || cur >= checks.length)
      return; // ignore out of range choices
    if (checks == null)
      return;
    checks[current].setState(false);
    current = cur;
    checks[current].setState(true);
  }

  public CheckboxMenuItem getSelectedCheckbox() {
    if (checks == null)
      return null;
    return checks[current];
  }

  public void setSelectedCheckbox(CheckboxMenuItem item) {
    if (checks == null)
      return;
    for (int i = 0; i < checks.length; i++) {
      if (item == checks[i]) {
        checks[i].setState(false);
        current = i;
        checks[i].setState(true);
      }
    }
  }
}


