package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Person;

import java.sql.SQLException;
import java.util.Vector;

/**
 * �û�ҵ��ӿ�
 * @author ����
 * @serialData 2016��4��22��:����8:04:38
 * @version 1.0.0
 */
public interface AdminDao extends PersonDao{
	/** 
	 * ��ӽ�ʦ
	 * @param Person per
	 * @return ����Ӽ�¼��id
	 * @author ����
	 * @serialData 2016��4��22��:����8:45:20
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addTeacher(Person per) throws SQLException;
	/** 
	 * ɾ����ʦ
	 * @param String ��ʦ���
	 * @return boolean �����Ƿ�ɾ���ɹ�
	 * @author ����
	 * @serialData 2016��4��22��:����8:45:20
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public boolean deleteTeacherByNumber(String number) throws SQLException;
	/**�޸Ľ�ʦ��Ϣ
	 * @param per
	 * @return
	 * @throws SQLException
	 */
	public boolean updateTeacher(Person per) throws SQLException;
	/** 
	 * �Խ�ʦ������Ȩ
	 * @param teacher
	 * @return boolean �����Ƿ���˳ɹ�
	 * @author ����
	 * @serialData 2016��4��22��:����8:45:20
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public boolean permit(String number,int power) throws Exception;
	
	/** 
	 *  ɸѡ��ʦ
	 * @return List<Person> ���ؽ�ʦ����
	 * @author ����
	 * @serialData 2016��4��22��:����8:38:18
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public Vector selectTeacher() throws Exception;
	
	/** 
	 *  ������ɸѡ��ʦ
	 * @return Person ���ؽ�ʦ����
	 * @author ����
	 * @serialData 2016��4��22��:����8:38:18
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public Vector selectTeacherByName(String name) throws Exception;

	/** 
	 *  ������ɸѡ��ʦ
	 * @return Person ���ؽ�ʦ����
	 * @author ����
	 * @serialData 2016��4��22��:����8:38:18
	 * @version 1.0.0
	 * @throws Exception 
	 */ 
	public Vector selectTeacherByNumber(String number) throws Exception;
	/**��ȡ��һ����¼��id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
