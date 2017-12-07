package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Course;
import java.sql.SQLException;
import java.util.Vector;

/**
 * �γ�ҵ��ӿ�
 * @author ����
 * @serialData 2016��4��25��:����8:05:13
 * @version 1.0.0
 */
public interface CourseDao {
	/** 
	 *  ��ӿγ���Ϣ
	 * @param cou
	 * @return �²����¼��id
	 * @author ����
	 * @serialData 2016��4��25��:����9:22:58
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addCourse(Course cou) throws SQLException;
	/** 
	 *  ɾ���γ���Ϣ
	 * @param cou
	 * @return boolean �����Ƿ�ɾ���ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:23:16
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean deleteCourse(int id) throws SQLException;
	/** 
	 *  ���¿γ���Ϣ
	 * @param cou
	 * @return boolean �����Ƿ���³ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:23:45
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean updateCourse(Course cou) throws SQLException;
	/** 
	 *  ѧ���б�
	 * @param �༶��
	 * @return Vector ���ض��пγ̼���
	 * @author ����
	 * @serialData 2016��4��25��:����9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector termList(String cla) throws SQLException;
	/** 
	 *  ɸѡȫ���γ�
	 * @param term
	 * @return Vector ���ض��пγ̼���
	 * @author ����
	 * @serialData 2016��4��25��:����9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourse() throws SQLException;
	/**�����������ҿγ̱��
	 * @param subject
	 * @param term
	 * @return
	 * @throws SQLException
	 */
	public int selectCourseId(String subject,String term) throws SQLException;
	/** 
	 *  ��ѧ��ɸѡ�γ�
	 * @param term
	 * @return Vector ���ض��пγ̼���
	 * @author ����
	 * @serialData 2016��4��25��:����9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourseByTerm(String term) throws SQLException;
	/** 
	 *  ���༶��ɸѡ�γ�
	 * @param term
	 * @return Vector ���ض��пγ̼���
	 * @author ����
	 * @serialData 2016��4��25��:����9:24:05
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourseByClasses(String cla) throws SQLException;
	/** 
	 *  ����Ŀɸѡ�γ�
	 * @param sub
	 * @return Vector ���ض��пγ̼���
	 * @author ����
	 * @serialData 2016��4��25��:����9:24:30
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectCourseBySubject(String sub) throws SQLException;
	/**��ȡ��һ����¼��id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
