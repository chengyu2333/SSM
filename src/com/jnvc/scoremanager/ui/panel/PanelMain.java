package com.jnvc.scoremanager.ui.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jnvc.scoremanager.ui.MainWindow;

public class PanelMain {
	
	public PanelMain() {
		// ��ҳ
		JPanel panel_main = new JPanel();
		MainWindow.tabbedPane.addTab("��ҳ", null, panel_main, null);
		panel_main.setLayout(null);
		
		JLabel label_no = new JLabel("����ɶҲû��");
		label_no.setBounds(319, 269, 110, 15);
		panel_main.add(label_no);
	}
}
