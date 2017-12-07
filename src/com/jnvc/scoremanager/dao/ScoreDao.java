package com.jnvc.scoremanager.dao;
import java.sql.SQLException;
import java.util.Vector;

import com.jnvc.scoremanager.model.Score;
//import java.util.List;
/**
 * �ɼ�ҵ��ӿ�
 * @author ����
 * @serialData 2016��4��25��:����8:05:49
 * @version 1.0.0
 */
public interface ScoreDao {
	/** 
	 *  ��ӳɼ���Ϣ
	 * @param sco
	 * @param num
	 * @return �²����¼��id
	 * @author ����
	 * @serialData 2016��4��25��:����9:33:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public int addScore(Score sco) throws SQLException;
	
	/** 
	 *  ���³ɼ���Ϣ
	 * @param sco
	 * @return boolean �����Ƿ���³ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:35:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean updateScore(Score sco) throws SQLException;
	/** 
	 *  ɾ���ɼ���Ϣ
	 * @param sco
	 * @return boolean �����Ƿ���³ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:35:49
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public boolean deleteScore(int id) throws SQLException;
	/**�ɼ���Ϣ�Ƿ���� 15054174817
	 * @param number
	 * @param subject
	 * @param term
	 * @return
	 * @throws SQLException
	 */
	public boolean existScore(String number,String subject,String term) throws SQLException;
	/** 
	 *  ɸѡȫ���ɼ���Ϣ
	 * @param num
	 * @return Vector ���ض��гɼ�����
	 * @author ����
	 * @serialData 2016��4��25��:����9:36:46
	 * @version 1.0.0
	 */ 
	public Vector selectScore() throws SQLException;
	/** 
	 *  ���༶ɸѡ�ɼ���Ϣ
	 * @param num
	 * @return Vector ���ض��гɼ�����
	 * @author ����
	 * @serialData 2016��4��25��:����9:36:46
	 * @version 1.0.0
	 */ 
	public Vector selectScoreBySubject(String subject) throws SQLException;
	/** 
	 *  ���༶ɸѡ�ɼ���Ϣ
	 * @param num
	 * @return Vector ���ض��гɼ�����
	 * @author ����
	 * @serialData 2016��4��25��:����9:36:46
	 * @version 1.0.0
	 */ 
	public Vector selectScoreByClasses(String name) throws SQLException;
	/** 
	 *  ��ѧ��ɸѡ�ɼ���Ϣ
	 * @param num
	 * @return Vector ���ض��гɼ�����
	 * @author ����
	 * @serialData 2016��4��25��:����9:36:46
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectScoreByNum(String num) throws SQLException;
	/** 
	 *  ������ɸѡ�ɼ���Ϣ
	 * @param num
	 * @return Vector ���ض��гɼ�����
	 * @author ����
	 * @serialData 2016��4��25��:����9:36:46
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectScoreByName(String name) throws SQLException;
	/** 
	 *  ��ѧ��ɸѡ�ɼ���Ϣ
	 * @param num
	 * @return Vector ���ض��гɼ�����
	 * @author ����
	 * @serialData 2016��4��25��:����9:36:46
	 * @version 1.0.0
	 * @throws SQLException 
	 */ 
	public Vector selectScoreByTerm(String term) throws SQLException;
	/**��ȡ��һ����¼��id
	 * @return
	 * @throws SQLException
	 */
	public int getNextId() throws SQLException;
}
