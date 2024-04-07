package com.wxmf.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.Serializable;

public class CheckListCellRenderer extends JCheckBox implements ListCellRenderer, Serializable {
    protected static Border noFocusBorder;

    /**
     * Constructs a default renderer object for an item
     * in a list.
     */
    public CheckListCellRenderer() {
        super();
        if (noFocusBorder == null) {
            noFocusBorder = new EmptyBorder(1, 1, 1, 1);
        }
        setOpaque(true);
        setBorder(noFocusBorder);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        if (value instanceof CheckValue) {
            CheckValue ckValue = (CheckValue) value;
            this.setText(ckValue.value == null ? "" : ckValue.value);
            this.setSelected(ckValue.bolValue);
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setBorder((cellHasFocus) ?
                UIManager.getBorder("List.focusCellHighlightBorder") :
                noFocusBorder);
        return this;
    }
}
