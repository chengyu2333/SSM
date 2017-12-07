package com.jnvc.scoremanager.other;
import java.sql.SQLException;

import com.jnvc.scoremanager.dao.*;
import com.jnvc.scoremanager.dao.impl.*;

/**
 *  ������
 * @author ����
 * @serialData 2016��4��22��
 * @version 1.0.0
 */
public class Factory {
	private static AdminDao admindao = null;
	private static PersonDao persondao = null;
	private static ClassesDao classesdao = null;
	private static CourseDao coursedao = null;
	private static ScoreDao scoredao = null;
	private static LogDao logdao = null;
	private static TeacherDao teacherdao = null;
	//private static ConfigDao configdao = null;
	
	
	public static PersonDao getPersonDao() throws Exception{
		if(persondao==null){
			persondao = new PersonDaoImpl();
		}	
		return persondao;
	}
	/** 
	 *  ���ع���Աҵ�����
	 * @return AdminDao
	 * @author ����
	 * @serialData 2016��4��22��
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public static AdminDao getAdminDao() throws Exception{
		if(admindao==null){
			admindao = new AdminDaoImpl();
		}	
		return admindao;
	}

	/** 
	 *  ���ؽ���ҵ�����
	 * @return TeacherDao
	 * @author ����
	 * @serialData 2016��4��22��
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public static TeacherDao getTeacherDao() throws Exception{
		if(teacherdao==null){
			teacherdao =new TeacherDaoImpl();
		}
		return teacherdao;
	}
	
	/** 
	 *  ���ذ༶ҵ�����
	 * @return ClassesDao
	 * @author ����
	 * @serialData 2016��4��22��
	 * @version 1.0.0
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */ 
	public static ClassesDao getClassesDao() throws SQLException, ClassNotFoundException{
		if(classesdao==null){
			classesdao = new ClassesDaoImpl();
		}
		return classesdao;
	}
	
	/** 
	 *  ���ؿγ�ҵ�����
	 * @return CourseDao
	 * @author ����
	 * @serialData 2016��4��22��
	 * @version 1.0.0
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */ 
	public static CourseDao getCourseDao() throws SQLException, ClassNotFoundException{
		if(coursedao==null){
			coursedao = new CourseDaoImpl();
		}
		return coursedao;
	}
	
	/** 
	 *  ���سɼ�ҵ�����
	 * @return ScoreDao
	 * @author ����
	 * @serialData 2016��4��22��
	 * @version 1.0.0
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */ 
	public static ScoreDao getScoreDao() throws SQLException, ClassNotFoundException{
		if(scoredao==null){
			scoredao = new ScoreDaoImpl();
		}
		return scoredao;
	}
	
	
	/**
	 * ������־ҵ�����
	 * @return LogDao
	 * @author ����
	 */
	public static LogDao getLogDao()throws SQLException, ClassNotFoundException{
		if(logdao==null){
			logdao = new LogDaoImpl();
		}
		return logdao;
		
	}
	
}
