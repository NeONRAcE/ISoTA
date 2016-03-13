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
	
	private int sum;
	
	public int getSum()
	{
		return this.sum;
	}
	
	public void setSum(int newSum)
	{
		this.sum = newSum;
	}
	
	private int paid;
	
	public int getPaid()
	{
		return this.paid;
	}
	
	public void setPaid(int newPaid)
	{
		this.paid = newPaid;
	}
	
	private int overpayment;
	
	public int getOverpayment()
	{
		return this.overpayment;
	}
	
	public void setOverpayment(int newOverpayment)
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
						r.kod = rs.getInt("Kod");
						r.operationDate = rs.getDate("Operation Date");
						r.overpayment = rs.getInt("Overpayment");
						r.paid = rs.getInt("Paid");
						r.sum = rs.getInt("Sum");
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
	
	
	
	
	
	
	
	
}
