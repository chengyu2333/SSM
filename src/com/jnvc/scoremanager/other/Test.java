package com.jnvc.scoremanager.other;

import java.sql.SQLException;

import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.model.Log;

/**
 * ������
 * @author ����
 * @serialData 2016��4��25��:����8:35:47
 * @version 1.0.0
 */
public class Test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		/*
		AdminDao admin = Factory.getAdminDao();
		System.out.println("���Խ�ʦ����");
		List<Teacher> list = admin.selectTeacher();
		for(int i=0;i<list.size();i++){
			Teacher tea = list.get(i);
			System.out.println(tea.getUsername());
		}
		
		Iterator<Teacher> it = list.iterator();
		System.out.println("���Ե�����");
		while(it.hasNext()){
			Teacher tea = it.next();
			System.out.println(tea.getUsername());
		}
		*/
		//FileOperate f =  new FileOperate();
		//f.creatFile();
//		ConfigDao configdao = Factory.getConfigDao();
//		if(configdao.setConfig("aaa", "bbbb")){
//			System.out.println("д�����óɹ�");
//		}
//		System.out.println("��ȡ���ã�"+configdao.getConfig("aaa"));
		//DBConnection dbconnection = Factory.getConnection();
		
		/*
		Person teac = new Person("0","0","123","0");
		teac.setPower(1);
		try {
			System.out.println(Factory.getAdminDao().check(teac));
			List<Person> list = Factory.getAdminDao().selectTeacher();
			Iterator<Person> it = list.iterator();
			while(it.hasNext()){
				Person tea = (Person) it.next();
				System.out.println(tea.getName()+"  \t  "+tea.getPower());
				
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		
//		
//		try {
//			AdminDao admindao = Factory.getAdminDao();
//			
//			Person per = new Person("00001","����","123456","123@qq.com");
//			if(admindao.register(per)){
//				System.out.println("ע��ɹ�");
//			}
//			System.out.println("��¼�ɹ����û�Ȩ��Ϊ��"+admindao.login(per));
//			Vector tea = new Vector();
//			tea = admindao.selectTeacher();
//			System.out.println(tea);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//Date date = new Date(0, 0, 0);
		
		try {
			LogDao logdao = Factory.getLogDao();
			Log log = new Log();
			log.setNewvalue("90");
			log.setOldvalue("80");
			log.setOperate("�޸�");
			log.setTarget("�ɼ�");
			log.setPerson("admin");
			logdao.addLog(log);
			System.out.println();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
