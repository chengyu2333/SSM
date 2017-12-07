package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Classes;

import java.sql.SQLException;
import java.util.Vector;

/**
 * @Description: �༶ҵ��ӿ�
 * @author ����
 * @serialData 2016��4��25��:����8:05:03
 * @version 1.0.0
 */
	public interface ClassesDao {
	/** 
	 *  ��Ӱ༶��Ϣ
	 * @param cla
	 * @return boolean ����Ӽ�¼��id
	 * @author ����
	 * @serialData 2016��4��25��:����9:19:55
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addClasses(Classes cla) throws SQLException;
	/** 
	 *  ɾ���༶��Ϣ
	 * @param id
	 * @return boolean �Ƿ�ɾ���ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:20:28
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean deleteClasses(int id) throws SQLException;
	/** 
	 *  ���°༶��Ϣ
	 * @param cla
	 * @return boolean �����Ƿ�ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:20:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean updateClasses(Classes cla) throws SQLException;
	
	/**��ȡ�༶�����б�
	 * @return
	 * @throws SQLException
	 */
	public Vector selectClassesName() throws SQLException;
	/** 
	 * ɸѡȫ���༶
	 * @param name
	 * @return Vector ���а༶���ݼ���
	 * @author ����
	 * @serialData 2016��4��25��:����9:21:12
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectClasses() throws SQLException;
	/** 
	 *  ��������ɸѡ�༶
	 * @param name
	 * @return Vector ���а༶���ݼ���
	 * @author ����
	 * @serialData 2016��4��25��:����9:21:12
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectClassesByTeacher(String name) throws SQLException;
	/** 
	 *  ���༶��ɸѡ�༶
	 * @param name
	 * @return Vector ���а༶���ݼ���
	 * @author ����
	 * @serialData 2016��4��25��:����9:21:37
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectClassesByName(String name) throws SQLException;
	/**��idɸѡ�༶
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Vector selectClassesById(int id) throws SQLException;
	/**��ȡ�´���ӵ�id
	 * @return int id
	 */
	public int getNextId() throws SQLException;
}
