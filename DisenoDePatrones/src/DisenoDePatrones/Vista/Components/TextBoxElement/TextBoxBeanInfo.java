/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DisenoDePatrones.Vista.Components.TextBoxElement;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 *
 * @author Alex
 */
public class TextBoxBeanInfo extends SimpleBeanInfo {
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor fontFamily = new PropertyDescriptor("fontFamily", TextBox.class);
            fontFamily.setDisplayName("fontFamily");
            fontFamily.setShortDescription("Font used in the text field.");
            fontFamily.setValue("category", "Font");

            PropertyDescriptor text = new PropertyDescriptor("text", TextBox.class);
            text.setDisplayName("text");
            text.setShortDescription("Text displayed in the field.");
            text.setValue("category", "Font");

            PropertyDescriptor textForeground = new PropertyDescriptor("textForeground", TextBox.class);
            textForeground.setDisplayName("textForeground");
            textForeground.setShortDescription("Color of the user-entered text.");
            textForeground.setValue("category", "Font");

            PropertyDescriptor placeholder = new PropertyDescriptor("placeholder", TextBox.class);
            placeholder.setDisplayName("placeholder");
            placeholder.setShortDescription("Guide text shown when the field is empty.");
            placeholder.setValue("category", "Font");

            PropertyDescriptor placeholderForeground = new PropertyDescriptor("placeholderForeground", TextBox.class);
            placeholderForeground.setDisplayName("placeholderForeground");
            placeholderForeground.setShortDescription("Color of the placeholder text.");
            placeholderForeground.setValue("category", "Font");

            PropertyDescriptor horizontalAlignment = new PropertyDescriptor("horizontalAlignment", TextBox.class);
            horizontalAlignment.setDisplayName("horizontalAlignment");
            horizontalAlignment.setShortDescription("Horizontal alignment of the text.");
            horizontalAlignment.setPropertyEditorClass(HorizontalAlignmentEditor.class);
            horizontalAlignment.setValue("category", "Font");

            PropertyDescriptor gap = new PropertyDescriptor("gap", TextBox.class);
            gap.setDisplayName("textPadding");
            gap.setShortDescription("Internal margin between border and text.");
            gap.setValue("category", "Font");

            PropertyDescriptor backgroundColor = new PropertyDescriptor("backgroundColor", TextBox.class);
            backgroundColor.setDisplayName("backgroundColor");
            backgroundColor.setShortDescription("Background color of the component.");
            backgroundColor.setValue("category", "Properties");

            PropertyDescriptor borderColor = new PropertyDescriptor("borderColor", TextBox.class);
            borderColor.setDisplayName("borderColor");
            borderColor.setShortDescription("Color of the component border.");
            borderColor.setValue("category", "Properties");

            PropertyDescriptor borderThickness = new PropertyDescriptor("borderThickness", TextBox.class);
            borderThickness.setDisplayName("borderThickness");
            borderThickness.setShortDescription("Thickness of the component border.");
            borderThickness.setValue("category", "Properties");

            PropertyDescriptor cornerRadius = new PropertyDescriptor("cornerRadius", TextBox.class);
            cornerRadius.setDisplayName("cornerRadius");
            cornerRadius.setShortDescription("Radius of the rounded corners.");
            cornerRadius.setValue("category", "Properties");
            
            PropertyDescriptor preferredSize = new PropertyDescriptor("preferredSize", TextBox.class);
            preferredSize.setDisplayName("preferredSize");
            preferredSize.setShortDescription("Preferred size of the component.");
            preferredSize.setValue("category", "Properties");

            PropertyDescriptor minimumSize = new PropertyDescriptor("minimumSize", TextBox.class);
            minimumSize.setDisplayName("minimumSize");
            minimumSize.setShortDescription("Minimum size of the component.");
            minimumSize.setValue("category", "Properties");

            PropertyDescriptor maximumSize = new PropertyDescriptor("maximumSize", TextBox.class);
            maximumSize.setDisplayName("maximumSize");
            maximumSize.setShortDescription("Maximum size of the component.");
            maximumSize.setValue("category", "Properties");

            return new PropertyDescriptor[] {
                fontFamily,
                text,
                textForeground,
                placeholder,
                placeholderForeground,
                gap,
                backgroundColor,
                borderColor,
                borderThickness,
                cornerRadius,
                horizontalAlignment,
                preferredSize,
                minimumSize,
                maximumSize,
            };

        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
