/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Components.TextBoxElement;

import java.beans.PropertyEditorSupport;
import javax.swing.JTextField;

/**
 *
 * @author Alex
 */
public class HorizontalAlignmentEditor extends PropertyEditorSupport {

    private static final String[] TAGS = { "LEFT", "CENTER", "RIGHT", "LEADING", "TRAILING" };

    @Override
    public String[] getTags() {
        return TAGS;
    }

    @Override
    public String getAsText() {
        int value = (Integer) getValue();
        return switch (value) {
            case JTextField.LEFT -> "LEFT";
            case JTextField.CENTER -> "CENTER";
            case JTextField.RIGHT -> "RIGHT";
            case JTextField.TRAILING -> "TRAILING";
            case JTextField.LEADING -> "LEADING";
            default -> "LEFT";
        };
    }

    @Override
    public void setAsText(String text) {
        switch (text) {
            case "LEFT" -> setValue(JTextField.LEFT);
            case "CENTER" -> setValue(JTextField.CENTER);
            case "RIGHT" -> setValue(JTextField.RIGHT);
            case "LEADING" -> setValue(JTextField.LEADING);
            case "TRAILING" -> setValue(JTextField.TRAILING);
            default -> setValue(JTextField.LEFT);
        }
    }
}