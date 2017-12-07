package com.jnvc.scoremanager.ui;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.jnvc.scoremanager.other.Config;

/**
 * ����ĵĹ��������
 */

public class MyTable {
	private Vector data;
	private Vector columnname;
	private Vector temp;
	private DefaultTableModel dtm;
	private JTable jtable;

	/**
	 * ��ʼ��һ���ձ�����
	 * 
	 * @return JTable ������
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public JTable initialize() {
		data = new Vector();
		dtm = new DefaultTableModel(data, columnname);
		jtable = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if("true".equals(Config.getConfig("sort"))){
			jtable.setAutoCreateRowSorter(true);
		}
		TableColumn firsetColumn = jtable.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(30);
		return jtable;
	}

	/**
	 * ��ʼ��һ������ͷ�ı�����
	 * 
	 * @param columnname
	 *            ��ͷVector����
	 * @return JTable ������
	 */
	public JTable initialize(Vector columnname) {
		this.columnname = columnname;
		data = new Vector();
		dtm = new DefaultTableModel(data, columnname);

		jtable = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if("true".equals(Config.getConfig("sort"))){
			jtable.setAutoCreateRowSorter(true);
		}

		TableColumn firsetColumn = jtable.getColumnModel().getColumn(0);
		firsetColumn.setMinWidth(30);
		firsetColumn.setMaxWidth(50);
		return jtable;
	}

	/**
	 * ��ʼ��һ�������ݵı�����
	 * 
	 * @param columnname
	 *            ��ͷVector����
	 * @param data
	 *            ����Vector����
	 * @return JTable ������
	 */
	public JTable initialize(Vector columnname, Vector data) {
		this.columnname = columnname;
		this.data = data;
		dtm = new DefaultTableModel(data, columnname);
		jtable = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if("true".equals(Config.getConfig("sort"))){
			jtable.setAutoCreateRowSorter(true);
		}
		TableColumn firsetColumn = jtable.getColumnModel().getColumn(0);
		firsetColumn.setMaxWidth(30);
		return jtable;
	}

	/**
	 * ��ȡTableModel
	 * 
	 * @return DefaultTableModel
	 */
	public DefaultTableModel getDefaultTableModel() {
		return dtm;
	}

	public void refresh() {
		dtm.fireTableStructureChanged();
		dtm.fireTableDataChanged();
	}

	/**
	 * �滻�������
	 * 
	 * @param Vector
	 *            data
	 * @return �����ݵ�����
	 */
	public int setData(Vector newdata) {
		data.clear();
		int i = 0;
		for (i = 0; i < newdata.size(); i++) {
			data.add(newdata.get(i));
		}
		return i;
	}

	/**
	 * ׷������
	 * 
	 * @param data
	 * @return int ׷�����ݵ�����
	 */
	public int appendData(Vector data) {
		int i;
		for (i = 0; i < data.size(); i++) {
			this.data.add(data.get(i));
		}
		return i + 1;
	}

	/**
	 * ��ȡ������������
	 * 
	 * @return Vector
	 */
	public Vector getData() {
		return this.data;
	}

	/**
	 * ��ձ������
	 */
	public void clear() {
		dtm.getDataVector().clear();
	}

	/**
	 * ����һ������
	 * 
	 * @param row
	 *            Vector����
	 */
	public void setRowVector(Vector row) {
		data.add(row);
	}

	/**
	 * ��ȡһ������
	 * 
	 * @param row
	 * @return Vector����
	 */
	public Vector getRowVector(int row) {
		temp = new Vector();
		for (int i = 0; i < dtm.getColumnCount(); i++) {
			
			row = jtable.convertRowIndexToModel(row);
			i = jtable.convertColumnIndexToModel(i);
			
			 temp.add(dtm.getValueAt(row, i));
		}
		return temp;
	}

	/**
	 * ����һ������
	 * 
	 * @param row
	 * @param newrow
	 */
	public void updateRowVector(int row, Vector rowdata) {
		temp = new Vector();
		for (int i = 0; i < dtm.getColumnCount(); i++) {
			dtm.setValueAt(rowdata.get(i), row, i);
		}
	}

	/**
	 * ɾ��һ������
	 * 
	 * @param row
	 */
	public void removeRow(int row) {
		dtm.removeRow(row);
	}

	/**
	 * ��ȡ��Ԫ������
	 * 
	 * @param row
	 *            ��
	 * @param column
	 *            ��
	 * @return Object
	 */
	public Object getValueAt(int row, int column) {
		
		row = jtable.convertRowIndexToModel(row);
		column = jtable.convertColumnIndexToModel(column);
		
		return dtm.getValueAt(row, column);
	}

	/**
	 * ���õ�Ԫ������
	 * 
	 * @param row
	 *            ��
	 * @param column
	 *            ��
	 * @return Object
	 */
	public void setValueAt(Object obj, int row, int column) {
		dtm.setValueAt(obj, row, column);
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public int getColumnCount() {
		return dtm.getColumnCount();
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public int getRowCount() {
		return dtm.getRowCount();
	}

	/**
	 * ��ȡĳֵ������
	 * 
	 * @param column
	 *            ��
	 * @param value
	 *            ֵ
	 * @return �����У�-1δ�ҵ�
	 */
	public int getRowNumber(int column, Object value) {
		int i;
		for (i = 0; i < jtable.getRowCount(); i++) {
			if (dtm.getValueAt(i, column) == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * ���������ͷ��
	 * 
	 * @param vec
	 *            vector����
	 * @param obj
	 *            ���������
	 * @return vector����
	 */
	public Vector insertHead(Vector vec, Object obj) {
		Vector temp = new Vector();
		temp.add(obj);
		for (int i = 0; i < vec.size(); i++) {
			temp.add(vec.get(i));
		}
		return temp;
	}

	/**
	 * Ϊ�����ʾ�Զ���ŵ�id
	 */
	public void showId() {
		int rowcount = dtm.getRowCount();
		Vector rowid = new Vector();
		for (int i = 1; i <= rowcount; i++) {
			rowid.add(i);
		}
		dtm.addColumn("id", rowid);
	}
}
