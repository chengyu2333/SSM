package com.jnvc.scoremanager.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.jnvc.scoremanager.model.Course;
import com.jnvc.scoremanager.model.Log;
import com.jnvc.scoremanager.other.Factory;
import com.jnvc.scoremanager.ui.Login;
import com.jnvc.scoremanager.dao.CourseDao;
import com.jnvc.scoremanager.dao.LogDao;
import com.jnvc.scoremanager.db.DBConnection;

/**
 * �γ�ҵ��ʵ����
 * @author ����
 * @serialData 2016��4��25��
 * @version 1.0.0
 */
public class CourseDaoImpl implements CourseDao{
	protected Statement stm;
	private String sql;
	protected Connection con;
	private LogDao logdao;
	public CourseDaoImpl() throws SQLException, ClassNotFoundException{
		con = DBConnection.getConnection();
		stm=(Statement) con.createStatement();
	}
	public int addCourse(Course cou) throws SQLException{
		sql = "insert into course (subject,credit,term,teacher) values('"+cou.getSubject()+"','"+cou.getCredit()+"','"+cou.getTerm()+"','"+cou.getTeacher()+"')";
		int i=stm.executeUpdate(sql);
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
				log.setTarget("�γ�");
				log.setNewvalue(cou.getTerm()+" "+cou.getSubject());
				log.setOldvalue("");
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
		}
		return 0;
	}
	public boolean deleteCourse(int id) throws SQLException{
		sql = "delete from course where id="+id;
		int i=stm.executeUpdate(sql);
		if(i>0){
			//д����־
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("ɾ��");
				log.setTarget("�γ�");
				log.setNewvalue("");
				log.setOldvalue(String.valueOf(id));
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
			System.out.println("ɾ���γ̳ɹ�");
			return true;
		}
		return false;
	}
	public boolean updateCourse(Course cou) throws SQLException{
		sql = "update course set subject='"+cou.getSubject()+"',credit='"+cou.getCredit()+"',term='"+cou.getTerm()+"',teacher='"+cou.getTeacher()+"' where id="+cou.getId();
		int i=stm.executeUpdate(sql);
		if(i>0){
			//д����־
			try {
				logdao = Factory.getLogDao();
				Log log = new Log();
				log.setOperate("����");
				log.setTarget("�γ�");
				log.setNewvalue(cou.getSubject());
				log.setOldvalue(String.valueOf(cou.getId()));
				log.setPerson(Login.person.getName());
				log.setEffect(true);
				logdao.addLog(log);
			} catch (ClassNotFoundException e) {
				System.out.println("�����־����");
				e.printStackTrace();
			}
			System.out.println("���¿γ̳ɹ�");
			return true;
		}
		return false;
	}
	public Vector termList(String cla) throws SQLException{
		if("".equals(cla)){
			sql = "select distinct term from course";
		}else{
			sql = "select distinct term from course,classes,course_classes where course.id=course_classes.id and classes.id=course_classes.id and classes.name='"+cla+"'";
		}
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			data.add(rs.getString("term"));
		}
		return data;
	}
	public Vector selectCourse() throws SQLException{
		sql = "select course.*,admin.name as teacherName from course,admin where admin.number=course.teacher";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getInt("credit"));
			row.add(rs.getString("teacher"));
			row.add(rs.getString("teacherName"));
			data.add(row);
		}
		return data;
	}
	public int selectCourseId(String subject,String term) throws SQLException{
		sql = "select id from course where subject='"+subject+"' and term='"+term+"'";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return rs.getInt("id");
		}
		return 0;
	}
	public Vector selectCourseByClasses(String cla) throws SQLException{
		sql = "select course.*,admin.name as teacherName from admin,course,classes,course_classes where admin.number=course.teacher and classes.id=course_classes.classes and course.id=course_classes.course and classes.name='"+cla+"';";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getInt("credit"));
			row.add(rs.getString("teacher"));
			row.add(rs.getString("teacherName"));
			data.add(row);
		}
		return data;
	}
	public Vector selectCourseByTerm(String term) throws SQLException{
		sql = "select course.*,admin.name as teacherName from course,admin where admin.number=course.teacher and course.term='"+term+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getInt("credit"));
			row.add(rs.getString("teacher"));
			row.add(rs.getString("teacherName"));
			data.add(row);
		}
		return data;
	}
	public Vector selectCourseBySubject(String sub) throws SQLException{
		sql = "select course.*,admin.name as teacherName from course,admin where admin.number=course.teacher and course.subject='"+sub+"'";
		ResultSet rs = stm.executeQuery(sql);
		Vector data = new Vector();
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getInt("id"));
			row.add(rs.getString("term"));
			row.add(rs.getString("subject"));
			row.add(rs.getInt("credit"));
			row.add(rs.getString("teacher"));
			row.add(rs.getString("teacherName"));
			data.add(row);
		}
		return data;
	}
	public int getNextId() throws SQLException{
		sql = "select MAX(id)+1 as id from course";
		ResultSet rs = stm.executeQuery(sql);
		if(rs.next()){
			return rs.getInt("id");
		}
		return 0;
	}
}
