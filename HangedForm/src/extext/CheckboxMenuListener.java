/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extext;

import java.util.EventListener;

/**
 *
 * @author usuario
 */
interface CheckboxMenuListener extends EventListener {
     public void checkboxChanged(CheckboxMenu menu, int check);
}
