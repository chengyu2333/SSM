package com.jnvc.scoremanager.ui.panel;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelLog {
	public static MyTable table_log;
	private JTable jtable_log;
	private JPanel panel_log;
	public PanelLog() {

		// ϵͳ����ҳ
		 panel_log = new JPanel();
		MainWindow.tabbedPane.addTab("ϵͳ��־", null, panel_log, null);
		panel_log.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 620, 400);
		panel_log.add(scrollPane);

		table_log = new MyTable();
		Vector<String> columnname = new Vector();
		columnname.add("id");
		columnname.add("����");
		columnname.add("Ŀ��");
		columnname.add("ԭֵ");
		columnname.add("��ֵ");
		columnname.add("������");
		columnname.add("�Ƿ���Ч");
		columnname.add("ʱ��");
		jtable_log = table_log.initialize(columnname);
		scrollPane.setViewportView(jtable_log);

		JButton button_apply = new JButton("Ӧ�ò���");
		button_apply.setBounds(682, 126, 93, 23);
		panel_log.add(button_apply);

		JButton button_recover = new JButton("�ָ�����");
		button_recover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(panel_log, "�ò�����ָ��ò�������ֵ�����滻Ϊ��ֵ���Ƿ������", "��ʾ��", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			}
		});
		button_recover.setBounds(682, 174, 93, 23);
		panel_log.add(button_recover);
		JButton button_clean = new JButton("������־");
		button_clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(panel_log, "�ò���������7����ǰ����־���Ƿ������", "��ʾ��", JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			}
		});
		button_clean.setBounds(680, 222, 93, 23);
		panel_log.add(button_clean);
		
		JButton button_refrush = new JButton("ˢ��");
		button_refrush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LogDao logdao = Factory.getLogDao();
					table_log.setData(logdao.selectLog());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_refrush.setBounds(682, 70, 93, 23);
		panel_log.add(button_refrush);
		event();
	}
	public void event(){
		try {
			LogDao logdao = Factory.getLogDao();
			table_log.setData(logdao.selectLog());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
