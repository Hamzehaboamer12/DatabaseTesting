import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	Connection con;
	Statement stmt;
	ResultSet rs;

	String URL = "https://magento.softwaretestingboard.com/customer/account/create/";
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void mySetup() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "hamzeh");

	}

	@Test(priority = 1)
	public void addData() throws SQLException {

		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city,country )"
				+ "VALUES (102, 'Hamzeh', 'ibraheem', 'AbuAmer', '123-456-7890', 'Amman', 'Amman' ,'jordan')";

		stmt = con.createStatement();
		int insertRow = stmt.executeUpdate(Query);

		Assert.assertEquals(insertRow > 0, true);
	}

	@Test(priority = 2)

	public void updateData() throws SQLException {
		String Query = "Update customers set contactLastName = 'Bassam' where customerNumber = 102";
		stmt = con.createStatement();
		int insertRow = stmt.executeUpdate(Query);
		Assert.assertEquals(insertRow > 0, true);
	}

	@Test(priority = 3)
	public void getData() throws SQLException {
		stmt = con.createStatement();
		String Query = "select * from customers where customerNumber = 102";
		rs = stmt.executeQuery(Query);
		String customerName = null;
		while (rs.next()) {

			customerName = rs.getString("customerName");

		}
		driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
		driver.findElement(By.id("firstname")).sendKeys(customerName);

	}

	@Test(priority = 4)
	public void DeleteData() throws SQLException {

		String Query = "delete from customers where customerNumber =102";
		;
		stmt = con.createStatement();
		int deleteRow = stmt.executeUpdate(Query);

		Assert.assertEquals(deleteRow > 0, true);
	}

}
