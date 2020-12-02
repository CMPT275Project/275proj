package com.company;
import javax.swing.*;
import java.util.*;

public class deviceList<E> extends AbstractListModel<E> {
    protected List<E> list;

    public deviceList(List<E> list) {
        this.list = list;
    }

    public void addElement(E element) {
        list.add(element);
        int index = list.size();
        fireContentsChanged(element, index, index);
    }

    public void fireDataChanged() {
        int index = list.size();
        fireContentsChanged(list.get(index - 1), index, index);
    }

    public int getSize() {
        return list.size();
    }

    public E getElementAt(int index) {
        return list.get(index);
    }
}
