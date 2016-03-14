import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportModel {
	
	private int id;
	
	public int getID()
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
	
	private int kod;
	
	public int getKod()
	{
		return this.kod;
	}
	
	public void setKod(int newKod)
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
						r.kod = rs.getInt("Kod");
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
	
	public void save()
	{
		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			if (this.id != 0)
			{
				ResultSet rs = connector.executeSQL("UPDATE reports "
						+ "SET OperationDate=" + this.operationDate + "' AND Kod='" + this.kod
						+ "' AND Overpayment='" + this.overpayment
						+ "' AND Paid='"+this.paid + "' AND Returned='" + this.returned
						+ "' AND Sum='" + this.sum + " WHERE ID=" + this.id);
						
			}
			else
			{
				ResultSet rs = connector.executeSQL("INSERT INTO reports(OperationDate,Kod,Overpayment,Paid,Returned,Sum)"
						+" VALUES (" + this.operationDate + ",'" + this.kod + ",'"+this.overpayment
						+"','"+this.paid+"','"+this.returned+"','"+this.sum+")");
			}

		}
		connector.SQLDisconnect();
	}
}
