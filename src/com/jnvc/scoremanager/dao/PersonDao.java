package com.jnvc.scoremanager.dao;

import java.sql.SQLException;

import com.jnvc.scoremanager.model.Person;

//import java.sql.SQLException;

public interface PersonDao {
	/** 
	 * �û�ע��
	 * @param oPerson
	 * @return boolean
	 * @author ����
	 * @serialData May 9, 2016:2:41:13 PM
	 * @version 1.0.0
	 */
	public abstract boolean register(Person per) throws Exception;
	
	/** 
	 * �û���¼
	 * @param Person
	 * @return Person
	 * @author ����
	 * @serialData May 9, 2016:2:41:03 PM
	 * @version 1.0.0
	 */
	public abstract Person login(Person per) throws Exception;
	
	/** 
	 * ��������
	 * @param Person
	 * @return boolean
	 * @author ����
	 * @serialData May 9, 2016:2:41:03 PM
	 * @version 1.0.0
	 */
	public abstract boolean ChangePassword(Person per) throws SQLException;
	/**
	 * �û��Ƿ����
	 * @param username Ϊ���ַ������ȫ���û�
	 * @return
	 * @throws Exception
	 */
	public abstract boolean userExist(String username) throws Exception;
}
