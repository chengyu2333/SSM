package com.jnvc.scoremanager.other;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//����Զ����������
class C extends JFrame {
    C() {
        final JTable jt = new JTable(new Object[][]{{5,1},{3,8},{9,4}}, new Object[]{'A','B'});
        jt.setAutoCreateRowSorter(true);

        add(new JScrollPane(jt));
        add(new JButton(new AbstractAction("��ʾѡ���������е�ֵ") {
            public void actionPerformed(ActionEvent ae) {
                int rowModel = jt.convertRowIndexToModel(jt.getSelectedRow()),
                    columnModel = jt.convertColumnIndexToModel(0);
                
                JOptionPane.showMessageDialog(null,
                        "<HTML>" +
                        "�� JTable.getValueAt() ��ȡ��ֵ�� " +
                        jt.getValueAt(jt.getSelectedRow(), 0) +
                        "<P>" +
                        "�� JTable.getModel().getValueAt() ��ת���������кŻ�ȡ��ֵ�� " +
                        jt.getModel().getValueAt(rowModel, columnModel));

            }
        }), BorderLayout.SOUTH);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) { new C(); }
}