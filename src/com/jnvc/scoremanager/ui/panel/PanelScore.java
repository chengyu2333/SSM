package com.jnvc.scoremanager.ui.panel;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.jnvc.scoremanager.dao.ScoreDao;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.MainWindow;
import com.jnvc.scoremanager.ui.MyTable;
import com.jnvc.scoremanager.ui.dialog.DialogScore;

public class PanelScore {
	public static JScrollPane scrollPane_score;
	public static JPanel panel;
	private JTextField textField_searchScore;
	private JButton button_addScore, button_saveScore, button_cancelScore, button_removeScore, button_updateScore,
			button_searchScore;
	public JTable jtable_score;
	public static MyTable table_score;
	private JComboBox<String> comboBox_score;
	private JButton button_importScore;
	private JMenuItem menuItem_deleteScore;
	private JMenuItem menuItem_updateScore;

	public PanelScore() {
		JPanel panel_score = new JPanel();
		MainWindow.tabbedPane.addTab("�ɼ�����", null, panel_score, null);
		panel_score.setLayout(null);

		scrollPane_score = new JScrollPane();
		scrollPane_score.setBounds(10, 70, 620, 400);
		panel_score.add(scrollPane_score);

		table_score = new MyTable();
		Vector<String> colname_score = new Vector<String>();
		colname_score.add("id");
		colname_score.add("ѧ��");
		colname_score.add("�༶");
		colname_score.add("����");
		colname_score.add("ѧ��");
		colname_score.add("��Ŀ");
		colname_score.add("�ɼ�");
		jtable_score = table_score.initialize(colname_score);
		scrollPane_score.setViewportView(jtable_score);

		// �����˵�
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jtable_score, popupMenu);
		scrollPane_score.add(popupMenu);

		menuItem_deleteScore = new JMenuItem("ɾ���ɼ�");
		popupMenu.add(menuItem_deleteScore);

		menuItem_updateScore = new JMenuItem("�޸ĳɼ�");
		popupMenu.add(menuItem_updateScore);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(640, 70, 150, 400);
		panel_score.add(panel);
		panel.setLayout(null);

		button_removeScore = new JButton("ɾ���ɼ�");
		button_removeScore.setBounds(28, 85, 93, 23);
		panel.add(button_removeScore);

		button_addScore = new JButton("¼��ɼ�");
		button_addScore.setBounds(28, 28, 93, 40);
		panel.add(button_addScore);

		button_updateScore = new JButton("�޸ĳɼ�");
		button_updateScore.setBounds(28, 118, 93, 23);
		panel.add(button_updateScore);

		button_saveScore = new JButton("�����޸�");
		button_saveScore.setBounds(28, 168, 93, 23);
		panel.add(button_saveScore);

		button_cancelScore = new JButton("ȡ���޸�");
		button_cancelScore.setBounds(28, 202, 93, 23);
		panel.add(button_cancelScore);

		button_importScore = new JButton("�����ɼ�");
		button_importScore.setBounds(28, 252, 93, 23);
		panel.add(button_importScore);

		button_searchScore = new JButton("����");
		button_searchScore.setBounds(215, 21, 68, 23);
		button_searchScore.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		panel_score.add(button_searchScore);

		textField_searchScore = new JTextField();
		textField_searchScore.setBounds(105, 22, 100, 21);
		panel_score.add(textField_searchScore);
		textField_searchScore.setColumns(10);

		comboBox_score = new JComboBox<String>();
		comboBox_score.setFont(new Font("YaHei Consolas Hybrid", Font.PLAIN, 12));
		comboBox_score.setModel(new DefaultComboBoxModel<String>(
				new String[] { "\u5B66\u53F7", "\u73ED\u7EA7", "\u59D3\u540D", "\u5B66\u671F", "\u79D1\u76EE" }));
		comboBox_score.setBounds(21, 22, 74, 21);
		panel_score.add(comboBox_score);

		try {
			ScoreDao scoredao = Factory.getScoreDao();
			table_score.setData(scoredao.selectScore());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MainWindow.frame, "��ʼ���ɼ���������쳣");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		event();
	}

	private void event() {
		// ˫�����
		jtable_score.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = jtable_score.rowAtPoint(e.getPoint());
				if (row >= 0) {
					jtable_score.setRowSelectionInterval(row, row);
				}
				if (e.getClickCount() == 2) {
					new DialogScore().initUpdate(table_score.getRowVector(jtable_score.getSelectedRow()));
				}
			}
		});
		// ����
		button_importScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("D:\\");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setDialogTitle("�����ɼ�");
				fileChooser.setSelectedFile(new File("�ɼ�.csv"));
				String saveType[] = { "csv", "Excel�������" };
				fileChooser.setFileFilter(new FileNameExtensionFilter("*.csv | Excel�������", saveType));
				if (jtable_score.getRowCount() < 1) {
					JOptionPane.showMessageDialog(MainWindow.frame, "û�����ݿɵ���!", "��ʾ��", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filePath = fileChooser.getSelectedFile().getAbsolutePath();
					File file = new File(filePath);
					try {
						if (!file.exists()) {
							file.createNewFile();
						} else {
							int op = JOptionPane.showConfirmDialog(MainWindow.frame, "�ļ��Ѿ����ڣ��Ƿ񸲸ǣ�", "��ʾ��",
									JOptionPane.OK_CANCEL_OPTION);
							System.out.println(op);
							if (op == 2) {
								return;
							}
						}
						PrintWriter pfp = new PrintWriter(file);
						pfp.println("id,ѧ��,�༶,����,ѧ��,��Ŀ,�ɼ�");
						for (int i = 0; i < table_score.getRowCount(); i++) {
							String row = table_score.getValueAt(i, 0) + "," + table_score.getValueAt(i, 1) + ","
									+ table_score.getValueAt(i, 2) + "," + table_score.getValueAt(i, 3) + ","
									+ table_score.getValueAt(i, 4) + "," + table_score.getValueAt(i, 5) + ","
									+ table_score.getValueAt(i, 6);
							pfp.println(row);
						}
						JOptionPane.showMessageDialog(MainWindow.frame, "�����ɼ��ɹ�!", "��ʾ��",
								JOptionPane.INFORMATION_MESSAGE);
						MainWindow.label_status.setText("�����ɼ��ɹ�");
						pfp.close();
					} catch (IOException efile) {
						System.out.println("�����ļ�����");
					}
					System.out.println(filePath);
				}
			}
		});
		// ����
		button_searchScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScoreDao scoredao = Factory.getScoreDao();
					if ("".equals(textField_searchScore.getText())) {
						table_score.setData(scoredao.selectScore());
						MainWindow.label_status.setText("��ʾȫ���ɼ�");
					} else {
						if ("ѧ��".equals(comboBox_score.getSelectedItem())) {
							table_score.setData(scoredao.selectScoreByNum(textField_searchScore.getText()));
							MainWindow.label_status.setText("��ѧ�Ų��ҳɹ�");
						} else if ("�༶".equals(comboBox_score.getSelectedItem())) {
							table_score.setData(scoredao.selectScoreByClasses(textField_searchScore.getText()));
							MainWindow.label_status.setText("���༶���ҳɹ�");
						} else if ("����".equals(comboBox_score.getSelectedItem())) {
							table_score.setData(scoredao.selectScoreByName(textField_searchScore.getText()));
							MainWindow.label_status.setText("���������ҳɹ�");
						} else if ("ѧ��".equals(comboBox_score.getSelectedItem())) {
							table_score.setData(scoredao.selectScoreByTerm(textField_searchScore.getText()));
							MainWindow.label_status.setText("��ѧ�ڲ��ҳɹ�");
						} else if ("��Ŀ".equals(comboBox_score.getSelectedItem())) {
							table_score.setData(scoredao.selectScoreBySubject(textField_searchScore.getText()));
							MainWindow.label_status.setText("����Ŀ���ҳɹ�");
						}
						table_score.refresh();
					}
					table_score.refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ɾ��
		button_removeScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScoreDao scoredao = Factory.getScoreDao();
					int[] rowid = jtable_score.getSelectedRows();
					if (rowid.length < 1) {
						return;
					}
					if (JOptionPane.showConfirmDialog(MainWindow.frame, "ѡ���ˡ�" + rowid.length + "�������ݣ����Ҫɾ��ô", "��ʾ��",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						for (int i = rowid.length - 1; i >= 0; i--) {
							System.out.println(rowid[i]);
							if (scoredao
									.deleteScore(Integer.parseInt(table_score.getValueAt(rowid[i], 0).toString()))) {
								table_score.removeRow(rowid[i]);
							}
						}
						MainWindow.label_status.setText("ɾ���ɼ��ɹ�");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		// ��ӳɼ�
		button_addScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogScore().initAdd();
			}
		});
		// �޸ĳɼ�
		button_updateScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jtable_score.getSelectedRow() == -1) {
					return;
				}
				new DialogScore().initUpdate(table_score.getRowVector(jtable_score.getSelectedRow()));
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
