package com.jnvc.scoremanager.ui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.SoftBevelBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.jnvc.scoremanager.model.Person;
import com.jnvc.scoremanager.ui.panel.*;

import javax.swing.border.EtchedBorder;
import com.jnvc.scoremanager.db.*;

public class MainWindow {

	public static Person loginer;
	public static JFrame frame;
	public static JTabbedPane tabbedPane;
	public static JLabel label_status;
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem_exit,menuItem_setting,mntmNewMenuItem_help;
	private JPanel panel_status;

	public MainWindow(Person per) {
		loginer=per;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res\\icon.png"));
		frame.setTitle("�ɼ�����ϵͳ");
		frame.setBounds(0, 0, 800, 622);
		frame.setBounds(Tools.getScreenWidth() / 2 - frame.getWidth() / 2,
				Tools.getScreenHeight() / 2 - frame.getHeight() / 2, 830, 630);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 30, 764, 522);
		frame.getContentPane().add(tabbedPane);

		// �˵�
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 357, 21);
		frame.getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("ϵͳ");
		menuBar.add(mnNewMenu);
		
		 menuItem_setting = new JMenuItem("����");
		mnNewMenu.add(menuItem_setting);

		mntmNewMenuItem_exit = new JMenuItem("ֱ���˳�");
		mnNewMenu.add(mntmNewMenuItem_exit);

		JMenu mnNewMenu_1 = new JMenu("����");
		menuBar.add(mnNewMenu_1);

		mntmNewMenuItem_help = new JMenuItem("����");
		mnNewMenu_1.add(mntmNewMenuItem_help);
		//״̬��
		panel_status = new JPanel();
		panel_status.setLocation(0, 570);
		panel_status.setSize(774, 22);
		panel_status.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel_status);
		
		 label_status = new JLabel("��¼�ɹ�");
		label_status.setBounds(207, 2, 749, 25);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 2, 200, 25);
		panel_status.setLayout(null);
		panel_status.add(label_status);
		panel_status.add(progressBar);

		//�ж�Ȩ�ޣ�������Ӧ���
		if(loginer.getPower()==2){
			new PanelMain();
			new PanelTeacher();
			new PanelClasses();
			new PanelCourse();
			new PanelStudent();
			new PanelScore();
			new PanelLog();
		}else if(loginer.getPower()==1){
			new PanelMain();
			new PanelStudent();
			new PanelScore();
		}


		eventWindow();
	}
	
	private void eventWindow() {
		// �������ڹر��¼�
		frame.addWindowListener(new WindowAdapter() {
			//TODO ����Ƿ����Զ����棬����Ƿ���δ����������ر����Զ����
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "���в���δ���棬���Ҫ�˳�ϵͳ��", "��ʾ��", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if (i == 0) {
					try {
						DBConnection.closeDB();
					} catch (SQLException e1) {
						System.out.println("�ر����ݿ�ʧ��");
					}
					System.exit(0);
				}
			}
		});
		// �������ڳߴ��¼�
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(frame.getWidth()<830||frame.getHeight()<630){
					frame.setSize(830, 630);
				}
				try{
					menuBar.setSize(frame.getWidth(), 21);
					tabbedPane.setBounds(10, 30, frame.getWidth() - 36, frame.getHeight() - 98);
					panel_status.setBounds(0, frame.getHeight()-62, frame.getWidth(), 30);
					PanelClasses.scrollPane_classes.setSize(frame.getWidth()-210,  frame.getHeight() - 230);
					PanelClasses.panel.setBounds(frame.getWidth()-190, 70, 150, frame.getHeight() - 230);
					PanelCourse.scrollPane_course.setSize(frame.getWidth()-210,  frame.getHeight() - 230);
					PanelCourse.panel.setBounds(frame.getWidth()-190, 70, 150, frame.getHeight() - 230);
					PanelStudent.scrollPane_student.setSize(frame.getWidth()-210,  frame.getHeight() - 230);
					PanelStudent.panel.setBounds(frame.getWidth()-190, 70, 150, frame.getHeight() - 230);
					PanelScore.scrollPane_score.setSize(frame.getWidth()-210,  frame.getHeight() - 230);
					PanelScore.panel.setBounds(frame.getWidth()-190, 70, 150, frame.getHeight() - 230);
					PanelTeacher.scrollPane_teacher.setSize(frame.getWidth()-210,  frame.getHeight() - 230);
					PanelTeacher.panel.setBounds(frame.getWidth()-190, 70, 150, frame.getHeight() - 230);
				}catch(Exception nullpoint){
					
				}
			}
		});
		//�˵�-����
		menuItem_setting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				new Setting();
			}
		});
		// �˵�-�˳�
		mntmNewMenuItem_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					DBConnection.closeDB();
				} catch (SQLException e1) {
					System.out.println("�ر����ݿ�ʧ��");
				}
				System.exit(0);
			}
		});
		//�˵�-����
		mntmNewMenuItem_help.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler  http://www.213.name/archives/752");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
