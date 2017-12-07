package com.jnvc.scoremanager.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.jnvc.scoremanager.model.Log;
import com.jnvc.scoremanager.model.Person;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.Login;
import com.jnvc.scoremanager.dao.AdminDao;
import com.jnvc.scoremanager.dao.LogDao;

/**
 * ����Աҵ��ʵ����
 * @author ����
 * @serialData 2016��4��25��
 * @version 1.0.0
 */
public class AdminDaoImpl extends PersonDaoImpl implements AdminDao {
	private String sql;
	private LogDao logdao;
	
	public AdminDaoImpl() throws Exception {
		super();
	}
	public int addTeacher(Person per) throws SQLException{
		sql="insert into admin(number,name,password,power,email) values('"+per.getNumber()+"','"+per.getName()+"','"+per.getPassword()+"','2','"+per.getEmail()+"')";
		int i = stm.executeUpdate(sql);
		if(i>0){
			sql = "select LAST_INSERT_ID() as id";
			ResultSet rs=stm.executeQuery(sql);
			if(rs.next()){
				return rs.getInt("id");
			}
			//д����־
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("���");
				log.setTarget("��ʦ");
				log.setNewvalue(per.getName());
				log.setOldvalue("");
				log.setPerson("admin");
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
			
		}
		return 0;
	}
	
	public boolean deleteTeacherByNumber(String number) throws SQLException{
		sql="delete from admin where number='"+number+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			//д����־
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("ɾ��");
				log.setTarget("��ʦ");
				log.setNewvalue("");
				log.setOldvalue(number);
				log.setPerson("admin");
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	public boolean updateTeacher(Person per) throws SQLException{
		sql="update admin set number='"+per.getNumber()+"',name='"+per.getName()+"',power='"+per.getPower()+"',email='"+per.getEmail()+"' where number='"+per.getNumber()+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			//д����־
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("����");
				log.setTarget("��ʦ");
				log.setNewvalue(per.getName());
				log.setOldvalue(String.valueOf(per.getId()));
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	public boolean permit(String number,int power) throws Exception{
		sql="update admin set power='"+power+"' where number='"+number+"'";
		int i = stm.executeUpdate(sql);
		if(i>0){
			//д����־
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("��Ȩ");
				log.setTarget("��ʦ");
				log.setNewvalue(String.valueOf(power));
				log.setOldvalue(number);
				log.setPerson("admin");
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public Vector selectTeacher() throws Exception{
		Vector data = new Vector();
		sql = "select * from admin";
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("name"));
			row.add(rs.getString("power"));
			row.add(rs.getString("email"));
			data.add(row);
		}
		return data;
	}
	public Vector selectTeacherByName(String name) throws Exception{
		Vector data = new Vector();
		sql = "select * from admin where name='"+name+"'";
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("name"));
			row.add(rs.getString("power"));
			row.add(rs.getString("email"));
			data.add(row);
		}
		return data;
	}
	public Vector selectTeacherByNumber(String number) throws Exception{
		Vector data = new Vector();
		sql = "select * from admin where number='"+number+"'";
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("number"));
			row.add(rs.getString("name"));
			row.add(rs.getString("power"));
			row.add(rs.getString("email"));
			data.add(row);
		}
		return data;
	}
	public int getNextId() throws SQLException{
		sql = "select MAX(id)+1 as id from admin";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return rs.getInt("id");
		}
		return 0;
	}
}
