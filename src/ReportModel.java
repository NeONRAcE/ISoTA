import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ReportModel {
	
	private Integer id;
	
	public Integer getID()
	{
		return this.id;
	}
		
	private Date operationDate;
	
	public Date getOperationDate()
	{
		return this.operationDate;
	}

	public void setOperationDate(Date newOperationDate)
	{
		this.operationDate = newOperationDate;
	}
	
	private String kod;
	
	public String getKod()
	{
		return this.kod;
	}
	
	public void setKod(String newKod)
	{
		this.kod = newKod;
	}
	
	private float sum;
	
	public float getSum()
	{
		return this.sum;
	}
	
	public void setSum(float newSum)
	{
		this.sum = newSum;
	}
	
	private float paid;
	
	public float getPaid()
	{
		return this.paid;
	}
	
	public void setPaid(float newPaid)
	{
		this.paid = newPaid;
	}
	
	private float returned;
	
	public float getReturned()
	{
		return this.returned;
	}
	
	public void setReturned(float newReturned)
	{
		this.returned = newReturned;
	}
	
	private float overpayment;
	
	public float getOverpayment()
	{
		return this.overpayment;
	}
	
	public void setOverpayment(float newOverpayment)
	{
		this.overpayment = newOverpayment;
	}
	
	public static ReportModel[] findReportsAll()
	{
		List<ReportModel> res = null;

		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			ResultSet rs = connector.executeSQL("SELECT * FROM reports"); // TODO занести в базу + допилить поля
			if (rs != null)
			{
				res = new ArrayList<ReportModel>();

				try
				{
					while (rs.next())
					{
						ReportModel r = new ReportModel();
						r.id = rs.getInt("ID");
						r.operationDate = rs.getDate("Operation Date");
						r.kod = rs.getString("Kod");
						r.overpayment = rs.getFloat("Overpayment");
						r.paid = rs.getFloat("Paid");
						r.returned = rs.getFloat("Returned");
						r.sum = rs.getFloat("Sum");
						res.add(r);
					}
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		connector.SQLDisconnect();
		
		ReportModel[] result = new ReportModel[res.size()];
		result = res.toArray(result);
		
		return result;
	}
	
	public static DefaultTableModel findReportsAllTM() throws SQLException
	{
		ResultSet res = null;

		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			res = connector.executeSQL("SELECT * FROM reports"); // TODO занести в базу + допилить поля
			
		}
		DefaultTableModel dtm = buildTableModel(res);
		connector.SQLDisconnect();
		
		return dtm;
	}
	
	public void save()
	{
		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			if (this.id != 0)
			{
				Integer rs = connector.executeUpdate("UPDATE reports "
						+ "SET OperationDate=" + this.operationDate + " AND Kod='" + this.kod
						+ "' AND Overpayment=" + this.overpayment
						+ " AND Paid="+this.paid + " AND Returned=" + this.returned
						+ " AND Sum=" + this.sum + " WHERE ID=" + this.id);
						
			}
			else
			{
				Integer rs = connector.executeUpdate("INSERT INTO reports(OperationDate,Kod,Overpayment,Paid,Returned,Sum)"
						+" VALUES (" + this.operationDate + ",'" + this.kod + "',"+this.overpayment
						+","+this.paid+","+this.returned+","+this.sum+")");
			}

		}
		connector.SQLDisconnect();
	}
	
	public boolean delete()
	{
		boolean res = false;
		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			if (this.getID() == 0)
			{
				res = false;
			}
			else
			{
				ResultSet rs = connector.executeSQL("DELETE FROM clients WHERE ID="+this.id);
				res = true;
			}
		}
		connector.SQLDisconnect();
		return res;
	}
	
	private static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    Integer columnCount = metaData.getColumnCount();
	    for (Integer column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }
	    //columns.add(columnNames);
	    Vector<Object> vo = new Vector<Object>();
	    //for (int i=0; i<columnNames.size(); i++) vo.add(columnNames.get(i));
	    vo.add("ID");
	    vo.add("Дата операции");
	    vo.add("Код");
	    vo.add("Сумма");
	    vo.add("Оплачено");
	    vo.add("Возвращено");
	    vo.add("Переплата");
	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    data.add(vo);
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (Integer columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
}
