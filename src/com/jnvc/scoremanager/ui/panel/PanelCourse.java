package com.jnvc.scoremanager.ui.panel;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.jnvc.scoremanager.dao.CourseDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import com.jnvc.scoremanager.ui.dialog.DialogCourse;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class PanelCourse {
	public static JPanel panel;
	public static JScrollPane scrollPane_course;
	private JTable jtable_course;
	public static MyTable table_course;
	private JTextField textField_searchCourse;
	private JComboBox comboBox_course;
	private JButton button_addCourse, button_removeCourse, button_updateCourse, button_saveCourse, button_cancelCourse,
			button_searchCourse;
	private JMenuItem menuItem_deleteCourse;
	private JMenuItem menuItem_updateCourse;

	public PanelCourse() {
		JPanel panel_course = new JPanel();
		MainWindow.tabbedPane.addTab("�γ̹���", null, panel_course, null);
		panel_course.setLayout(null);

		scrollPane_course = new JScrollPane();
		scrollPane_course.setBounds(10, 70, 620, 400);
		panel_course.add(scrollPane_course);

		table_course = new MyTable();
		Vector<String> colname_course = new Vector<String>();
		colname_course.add("id");
		colname_course.add("ѧ��");
		colname_course.add("��Ŀ");
		colname_course.add("ѧ��");
		colname_course.add("��ʦ����");
		colname_course.add("��ʦ����");

		jtable_course = table_course.initialize(colname_course);
		scrollPane_course.setViewportView(jtable_course);

		//�����˵�
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jtable_course, popupMenu);
		scrollPane_course.add(popupMenu);
		
		menuItem_deleteCourse = new JMenuItem("ɾ���γ�");
		popupMenu.add(menuItem_deleteCourse);
		
		menuItem_updateCourse = new JMenuItem("�޸Ŀγ�");
		popupMenu.add(menuItem_updateCourse);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(640, 70, 150, 400);
		panel_course.add(panel);
		panel.setLayout(null);

		button_addCourse = new JButton("��ӿγ�");
		button_addCourse.setBounds(28, 28, 93, 40);
		panel.add(button_addCourse);

		button_removeCourse = new JButton("ɾ���γ�");
		button_removeCourse.setBounds(28, 85, 93, 23);
		panel.add(button_removeCourse);

		button_updateCourse = new JButton("�޸Ŀγ�");
		button_updateCourse.setBounds(28, 118, 93, 23);
		panel.add(button_updateCourse);

		button_saveCourse = new JButton("�����޸�");
		button_saveCourse.setBounds(28, 169, 93, 23);
		panel.add(button_saveCourse);

		button_cancelCourse = new JButton("ȡ���޸�");
		button_cancelCourse.setBounds(28, 202, 93, 23);
		panel.add(button_cancelCourse);

		comboBox_course = new JComboBox();
		comboBox_course
				.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u671F", "\u79D1\u76EE", "\u73ED\u7EA7"}));
		comboBox_course.setBounds(21, 22, 74, 21);
		panel_course.add(comboBox_course);

		textField_searchCourse = new JTextField();
		textField_searchCourse.setBounds(105, 22, 100, 21);
		panel_course.add(textField_searchCourse);
		textField_searchCourse.setColumns(10);

		button_searchCourse = new JButton("����");
		button_searchCourse.setBounds(215, 21, 68, 23);
		button_searchCourse.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel_course.add(button_searchCourse);

		try {
			CourseDao coursedao = Factory.getCourseDao();
			table_course.setData(coursedao.selectCourse());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainWindow.frame, "��ʼ���γ̹�������쳣");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		event();
	}

	private void event() {
		// ˫�����
		jtable_course.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtable_course.rowAtPoint(e.getPoint());
				if (row >= 0)
				{
					jtable_course.setRowSelectionInterval(row, row);
				}
				if (e.getClickCount() == 2) {
					new DialogCourse().initUpdate(table_course.getRowVector(jtable_course.getSelectedRow()));
				}
			}
		});
		// ����
		button_searchCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CourseDao coursedao = Factory.getCourseDao();
					if ("".equals(textField_searchCourse.getText())) {
						table_course.setData(coursedao.selectCourse());
						MainWindow.label_status.setText("��ʾȫ���γ�");
					} else {
						if ("ѧ��".equals(comboBox_course.getSelectedItem())) {
							table_course.setData(coursedao.selectCourseByTerm(textField_searchCourse.getText()));
							MainWindow.label_status.setText("��ѧ�ڲ��ҳɹ�");
						} else if ("��Ŀ".equals(comboBox_course.getSelectedItem())) {
							table_course.setData(coursedao.selectCourseBySubject(textField_searchCourse.getText()));
							MainWindow.label_status.setText("����Ŀ���ҳɹ�");
						} else if ("�༶".equals(comboBox_course.getSelectedItem())) {
							table_course.setData(coursedao.selectCourseByClasses(textField_searchCourse.getText()));
							MainWindow.label_status.setText("����ʦ���ҳɹ�");
						}
					}
					table_course.refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ɾ��
		button_removeCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					CourseDao coursedao = Factory.getCourseDao();
					int[] rowid = jtable_course.getSelectedRows();
					if(rowid.length<1){
						return;
					}
					if (JOptionPane.showConfirmDialog(MainWindow.frame, "ѡ���ˡ�" + rowid.length + "�������ݣ����Ҫɾ��ô", "��ʾ��",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						for (int i = rowid.length - 1; i >= 0; i--) {
							System.out.println(rowid[i]);
							if (coursedao
									.deleteCourse(Integer.parseInt(table_course.getValueAt(rowid[i], 0).toString()))) {
								table_course.removeRow(rowid[i]);
							}
						}
						MainWindow.label_status.setText("ɾ���γ̳ɹ�");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		// ���
		button_addCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogCourse().initAdd();
			}
		});
		// �޸�
		button_updateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jtable_course.getSelectedRow()==-1){
					return;
				}
				new DialogCourse().initUpdate(table_course.getRowVector(jtable_course.getSelectedRow()));
			}
		});
		// ȡ���޸�
		button_cancelCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// �����޸�
		button_saveCourse.addActionListener(new ActionListener() {
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
