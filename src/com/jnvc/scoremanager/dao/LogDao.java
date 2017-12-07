package com.jnvc.scoremanager.dao;
import com.jnvc.scoremanager.model.Log;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 * ��־ҵ��ӿ� 
 * @author ����
 * @serialData 2016��4��25��:����8:05:27
 * @version 1.0.0
 */
public interface LogDao {
	/** 
	 *  �����־��Ϣ
	 * @return boolean �����Ƿ���ӳɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:24:53
	 * @version 1.0.0
	 */ 
	public boolean addLog(Log log) throws SQLException;
	
	/** 
	 *  ɾ��ĳʱ����ǰ����־ 
	 * @param log
	 * @return boolean �����Ƿ�ɾ���ɹ�
	 * @author ����
	 * @serialData 2016��4��25��:����9:26:43
	 * @version 1.0.0
	 */ 
	public boolean deleteLogBytime(Date time) throws SQLException;
	
	/**��ѯȫ����־
	 * @return
	 */
	public Vector selectLog() throws SQLException;
	/** 
	 *  ��ʱ��ɸѡ��־
	 * @param datetime ����
	 * @return Vector ������־����
	 * @author ����
	 * @serialData 2016��4��25��:����9:25:14
	 * @version 1.0.0
	 */ 
	public Vector selectLogByTime(String datetime) throws SQLException;
	/** 
	 *  ��IDɸѡ��־
	 * @param id
	 * @return Vector ������־����
	 * @author ����
	 * @serialData 2016��4��25��:����9:25:50
	 * @version 1.0.0
	 */ 
	public Vector selectLogById(int id) throws SQLException;
	
	/**���Ƿ���Ч��ѯ
	 * @param effect
	 * @return Vector ������־����
	 */
	public Vector selectLogByEffect(boolean effect) throws SQLException;
}
