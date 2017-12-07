package com.jnvc.scoremanager.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.jnvc.scoremanager.model.Student;

/**
 * ��ʦҵ��ӿ�
 * @author ����
 * @serialData 2016��4��25��:����8:06:10
 * @version 1.0.0
 */
public interface TeacherDao extends PersonDao{
	/**
	 * ɸѡȫ��ѧ��
	 * @return Vector ѧ����Ϣ���м���
	 * @throws SQLException
	 */
	public Vector selectStudent() throws SQLException;
	/** 
	 * ������ɸѡѧ��
	 * @param name
	 * @return List<Studnet>
	 * @author ����
	 * @serialData May 9, 2016:2:38:04 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public Vector selectStudentByName(String name) throws SQLException;
	/** 
	 * ��ѧ��ɸѡѧ��
	 * @param Num
	 * @return Vector
	 * @author ����
	 * @serialData May 9, 2016:2:38:10 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public Vector selectStudentByNum(String Num) throws SQLException;
	/** 
	 * ���༶ɸѡѧ��
	 * @param Num
	 * @return Vector
	 * @author ����
	 * @serialData May 9, 2016:2:38:10 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public Vector selectStudentByClasses(String name) throws SQLException;
	/** 
	 * ���һ��ѧ��
	 * @param stu
	 * @return �²����¼��id
	 * @author ����
	 * @serialData May 9, 2016:2:39:59 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public int addStudent(Student stu) throws SQLException;
	/** 
	 * ����ѧ����Ϣ
	 * @param stu
	 * @return
	 * @author ����
	 * @serialData May 9, 2016:2:40:11 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public boolean updateStudent(Student stu) throws SQLException;
	/** 
	 * ɾ��ѧ��
	 * @param String
	 * @return
	 * @author ����
	 * @serialData May 9, 2016:2:40:21 PM
	 * @version 1.0.0
	 * @throws SQLException 
	 */
	public boolean deleteStudentByNumber(String number) throws SQLException;
	/**��ȡ��һ����¼��id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
