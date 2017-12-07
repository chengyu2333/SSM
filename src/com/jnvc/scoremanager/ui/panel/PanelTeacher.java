package com.jnvc.scoremanager.ui.panel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.jnvc.scoremanager.dao.AdminDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import com.jnvc.scoremanager.ui.dialog.DialogTeacher;

public class PanelTeacher {
	public static JScrollPane scrollPane_teacher;
	public static JPanel panel;
	private JTextField textField_searchTeacher;
	private JButton button_permit, button_removeTeacher, button_addTeaher, button_updateTeacher, button_saveTeacher,
			button_cancelTeacher, button_searchTeacher;
	private JComboBox comboBox_teacher;
	public static MyTable table_teacher;
	private JTable jtable_teacher;
	private JMenuItem menuItem_deleteTeacher;
	private JMenuItem menuItem_permit;

	public PanelTeacher() {
		// ��ʦ����
		JPanel panel_teacher = new JPanel();
		MainWindow.tabbedPane.addTab("��ʦ����", null, panel_teacher, null);
		panel_teacher.setLayout(null);

		scrollPane_teacher = new JScrollPane();
		scrollPane_teacher.setBounds(10, 70, 620, 400);
		panel_teacher.add(scrollPane_teacher);
		//��ʼ�����
		table_teacher = new MyTable();
		Vector<String> colname_teacher = new Vector<String>();
		colname_teacher.add("id");
		colname_teacher.add("����");
		colname_teacher.add("����");
		colname_teacher.add("Ȩ��");
		colname_teacher.add("�ʼ�");
		jtable_teacher = table_teacher.initialize(colname_teacher);
		scrollPane_teacher.setViewportView(jtable_teacher);
		
		// �����˵�
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jtable_teacher, popupMenu);
		scrollPane_teacher.add(popupMenu);
		
		menuItem_deleteTeacher = new JMenuItem("ɾ����ʦ");
		popupMenu.add(menuItem_deleteTeacher);
		
		JMenuItem menuItem_updateTeacher = new JMenuItem("�޸Ľ�ʦ");
		popupMenu.add(menuItem_updateTeacher);
		
		menuItem_permit = new JMenuItem("��Ȩ");
		popupMenu.add(menuItem_permit);
		
		//��ť
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(640, 70, 150, 400);
		panel_teacher.add(panel);
		panel.setLayout(null);

		button_addTeaher = new JButton("��ӽ�ʦ");
		button_addTeaher.setBounds(28, 28, 93, 40);
		panel.add(button_addTeaher);

		button_removeTeacher = new JButton("ɾ����ʦ");
		button_removeTeacher.setBounds(28, 85, 93, 23);
		panel.add(button_removeTeacher);

		button_updateTeacher = new JButton("�޸Ľ�ʦ");
		button_updateTeacher.setBounds(28, 118, 93, 23);
		panel.add(button_updateTeacher);

		button_saveTeacher = new JButton("�����޸�");
		button_saveTeacher.setBounds(28, 169, 93, 23);
		panel.add(button_saveTeacher);

		button_cancelTeacher = new JButton("ȡ���޸�");
		button_cancelTeacher.setBounds(28, 202, 93, 23);
		panel.add(button_cancelTeacher);

		button_permit = new JButton("��Ȩѡ��");
		button_permit.setBounds(28, 252, 93, 23);
		panel.add(button_permit);

		textField_searchTeacher = new JTextField();
		textField_searchTeacher.setBounds(105, 22, 100, 21);
		panel_teacher.add(textField_searchTeacher);
		textField_searchTeacher.setColumns(10);

		button_searchTeacher = new JButton("����");
		button_searchTeacher.setBounds(215, 21, 68, 23);
		button_searchTeacher.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel_teacher.add(button_searchTeacher);

		comboBox_teacher = new JComboBox();
		comboBox_teacher.setModel(new DefaultComboBoxModel(new String[] { "\u59D3\u540D", "\u5DE5\u53F7" }));
		comboBox_teacher.setBounds(21, 22, 74, 21);
		panel_teacher.add(comboBox_teacher);
		jtable_teacher.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		try {
			AdminDao admindao = Factory.getAdminDao();
			Vector data_teacher = new Vector();
			data_teacher = admindao.selectTeacher();
			table_teacher.setData(data_teacher);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(MainWindow.frame, "��ʼ����ʦ��������쳣");
			e1.printStackTrace();
		}

		event();
	}

	public void event() {

		// ˫�����
		jtable_teacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtable_teacher.rowAtPoint(e.getPoint());
				if (row >= 0)
				{
					jtable_teacher.setRowSelectionInterval(row, row);
				}
				if (e.getClickCount() == 2) {
					new DialogTeacher().initUpdate(table_teacher.getRowVector(jtable_teacher.getSelectedRow()));
				}
			}
		});
		// ����
		button_searchTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminDao admindao = Factory.getAdminDao();
					if ("".equals(textField_searchTeacher.getText())) {
						table_teacher.setData(admindao.selectTeacher());
						MainWindow.label_status.setText("��ʾȫ����ʦ");
					} else {
						if ("����".equals(comboBox_teacher.getSelectedItem())) {
							table_teacher.setData(admindao.selectTeacherByNumber(textField_searchTeacher.getText()));
							MainWindow.label_status.setText("�����Ų��ҳɹ�");
						} else if ("����".equals(comboBox_teacher.getSelectedItem())) {
							table_teacher.setData(admindao.selectTeacherByName(textField_searchTeacher.getText()));
							MainWindow.label_status.setText("���������ҳɹ�");
						}
					}
					table_teacher.refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ɾ��
		button_removeTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					AdminDao admindao = Factory.getAdminDao();
					int[] rowid = jtable_teacher.getSelectedRows();
					if(rowid.length<1){
						return;
					}
					if (JOptionPane.showConfirmDialog(MainWindow.frame, "ѡ���ˡ�" + rowid.length + "�������ݣ����Ҫɾ��ô", "��ʾ��",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						for (int i = rowid.length - 1; i >= 0; i--) {
							if (!"1".equals(table_teacher.getValueAt(rowid[i], 0).toString())&&admindao.deleteTeacherByNumber(table_teacher.getValueAt(rowid[i], 1).toString())) {
								table_teacher.removeRow(rowid[i]);
							}
						}
						MainWindow.label_status.setText("ɾ����ʦ�ɹ�");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ���
		button_addTeaher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogTeacher().initAdd();
			}
		});
		// �޸�
		button_updateTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtable_teacher.getSelectedRow()==-1){
					return;
				}
				new DialogTeacher().initUpdate(table_teacher.getRowVector(jtable_teacher.getSelectedRow()));
			}
		});
		// �����Ȩ
		button_permit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDao admindao;
				try {
					admindao = Factory.getAdminDao();
					Vector vec = new Vector();
					vec.add(1);
					int[] rowid = jtable_teacher.getSelectedRows();
					if(rowid.length<1){
						return;
					}
					if (JOptionPane.showConfirmDialog(MainWindow.frame, "ѡ���ˡ�" + rowid.length + "�������ݣ��Ƿ�Ҫ��Ȩ��", "��ʾ��",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						for (int i = rowid.length - 1; i >= 0; i--) {
							if (!"1".equals(table_teacher.getValueAt(rowid[i], 0).toString())&&admindao.permit(table_teacher.getValueAt(rowid[i], 1).toString(),
									Integer.parseInt(vec.get(0).toString()))) {
								table_teacher.setValueAt(vec.get(0), rowid[i], 3);
							}
						}
						JOptionPane.showMessageDialog(MainWindow.frame, "��Ȩ�ɹ�", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
						MainWindow.label_status.setText("��Ȩ�ɹ�");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ȡ���޸�
		button_cancelTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// �����޸�
		button_saveTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	// �Ҽ��˵�
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
