package projectGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Admindashboard extends JFrame {

	private JLabel carsCountLabel;
	private JLabel customersCountLabel;
	private JLabel bookedCountLabel;

	public Admindashboard() {
		setTitle("Admin Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setLayout(null);

		// Side Panel
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(172, 160, 255));
		sidePanel.setBounds(0, 0, 150, 600);
		sidePanel.setLayout(new GridLayout(8, 1, 0, 10));

		String[] menuItems = {"User Info","Show Cars","Show Bookings","Add Car","Delete Car","Update Car","Logout"};
		for (String item : menuItems) {
			JButton button = new JButton(item);
			button.setForeground(Color.WHITE);
			button.setBackground(new Color(172, 160, 255));
			button.setFocusPainted(false);
			button.setBorderPainted(false);
			button.setFont(new Font("Arial", Font.BOLD, 14));
			button.setCursor(new Cursor(Cursor.HAND_CURSOR));

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand().equals("Show Cars")) {
						showCarDetailsFrame();
					}
					else if(e.getActionCommand().equals("User Info")) {
						showUserInfoDetailsFrame();
					}
					else if(e.getActionCommand().equals("Show Bookings")) {
						showBookingDetailsFrame();
					}
					else if(e.getActionCommand().equals("Add Car")) {
						addCar();
					}
					else if(e.getActionCommand().equals("Delete Car")) {
						removeCar();
					}
					else if(e.getActionCommand().equals("Update Car")) {
						new carUpdationGUI();
					}
					else if(e.getActionCommand().equals("Logout")) {
						new LoginGUI();
						dispose();
					}
				}
			});

			sidePanel.add(button);
		}

		// Admin Label
		JLabel adminLabel = new JLabel("Admin");
		adminLabel.setFont(new Font("Arial", Font.BOLD, 18));
		adminLabel.setBounds(160, 10, 200, 30);

		// Dashboard Cards Panel
		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setLayout(null);
		dashboardPanel.setBounds(160, 50, 800, 500);

		carsCountLabel = createCard(dashboardPanel, "Cars", getCarsCount(), new Color(255, 153, 51), 0);
		customersCountLabel = createCard(dashboardPanel, "Customers", getCustomersCount(), new Color(102, 204, 0), 1);
		bookedCountLabel = createCard(dashboardPanel, "Booked", getBookedCount(), new Color(153, 102, 204), 2);

		// Refresh Button (Optional to simulate updates)
		JButton refreshButton = new JButton("Refresh Counts");
		refreshButton.setBounds(700, 10, 150, 30);
		refreshButton.addActionListener(e -> updateCounts());
		add(refreshButton);

		add(sidePanel);
		add(adminLabel);
		add(dashboardPanel);

		setVisible(true);
	}

	private JLabel createCard(JPanel panel, String title, String count, Color color, int position) {
		JPanel card = new JPanel();
		card.setBackground(color);
		card.setLayout(new BorderLayout());

		JLabel titleLabel = new JLabel(" " + title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
		titleLabel.setForeground(Color.WHITE);

		JLabel countLabel = new JLabel(count, SwingConstants.CENTER);
		countLabel.setFont(new Font("Arial", Font.BOLD, 24));
		countLabel.setForeground(Color.WHITE);

		card.add(titleLabel, BorderLayout.NORTH);
		card.add(countLabel, BorderLayout.CENTER);
		card.setBounds(10 + position * 260, 50, 240, 120);
		panel.add(card);

		return countLabel;
	}

	private void updateCounts() {
		carsCountLabel.setText(getCarsCount());
		customersCountLabel.setText(getCustomersCount());
		bookedCountLabel.setText(getBookedCount());
	}

	//cars details
	private void showCarDetailsFrame() {
		JFrame carFrame = new JFrame("Car Details");
		carFrame.setSize(600, 400);
		carFrame.setLocationRelativeTo(null);

		String[] columnNames = {"Car ID", "Brand", "price", "colour", "model","mileage","seating_capacity"};

		JTable carTable = new JTable(getData1(), columnNames);
		JScrollPane scrollPane = new JScrollPane(carTable);
		carFrame.add(scrollPane);

		carFrame.setVisible(true);
	}

	//UserInfo details
	private void showUserInfoDetailsFrame() {
		JFrame carFrame = new JFrame("Car Details");
		carFrame.setSize(600, 400);
		carFrame.setLocationRelativeTo(null);
		String[] columnNames = {"User Id", "Firstname", "Lastname", "Phone", "Email", "Adhaar","Address","Designation","Password"};

		JTable carTable = new JTable(getData(), columnNames);
		JScrollPane scrollPane = new JScrollPane(carTable);
		carFrame.add(scrollPane);

		carFrame.setVisible(true);
	}

	//delete car
	private void removeCar() {
		JFrame carFrame = new JFrame("Remove Car");
		carFrame.setSize(600, 400);
		carFrame.setLocationRelativeTo(null);

		//main heading label
		JLabel title=new JLabel("Delete Car");
		title.setBounds(250, 10, 300, 30);
		title.setFont(new Font("Arial", Font.BOLD, 21));
		carFrame.add(title);

		//carId label
		JLabel carLabel=new JLabel("Car_Id : ");
		carLabel.setBounds(200, 50, 300, 30);
		carFrame.add(carLabel);

		//carText
		JTextField carTextField=new JTextField();
		carTextField.setBounds(300, 50, 100, 30);
		carFrame.add(carTextField);

		//delete btn
		JButton delButton=new JButton("Remove");
		delButton.setBounds(300, 90, 90, 30);
		carFrame.add(delButton);
		
		
		carFrame.setLayout(null);
		carFrame.setVisible(true);
		
		//action listners
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delRecord(carTextField);
				carFrame.dispose();
			}
		});
	}
	
	//db request for delete car
	private void delRecord(JTextField id) {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
        String user = "root";
        String dbpassword = "@@@Vamsi143";

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, dbpassword);
            String sql = "delete from car where car_id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,Integer.parseInt(id.getText().trim()));
            int row=stmt.executeUpdate();

            if(row>0) {
            	JOptionPane.showMessageDialog(new JFrame(), "Deleted Successfully");
            }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),e.getMessage());
		}
	}
	
	//add car details
	private void addCar() {
		JFrame carFrame = new JFrame("Car Details");
		carFrame.setSize(600, 400);
		carFrame.setLocationRelativeTo(null);

		//title label
		JLabel titleJLabel=new JLabel("Add Car");
		titleJLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleJLabel.setBounds(200, 10, 300, 30);
		titleJLabel.setForeground(Color.red);
		carFrame.add(titleJLabel);

		//brand label
		JLabel carIdJLabel=new JLabel("Brand : ");
		carIdJLabel.setBounds(140, 50, 300, 30);
		carFrame.add(carIdJLabel);

		//brand text
		JTextField jTextField=new JTextField();
		jTextField.setBounds(250, 50, 100, 30);
		carFrame.add(jTextField);

		//price label
		JLabel priceJLabel=new JLabel("Price : ");
		priceJLabel.setBounds(140, 90, 300, 30);
		carFrame.add(priceJLabel);

		//price text
		JTextField priceJTextField=new JTextField();
		priceJTextField.setBounds(250, 90, 100, 30);
		carFrame.add(priceJTextField);

		//colour label
		JLabel colourJLabel=new JLabel("Colour : ");
		colourJLabel.setBounds(140, 130, 300, 30);
		carFrame.add(colourJLabel);

		//colour text
		JTextField colouJTextField=new JTextField();
		colouJTextField.setBounds(250, 130, 100, 30);
		carFrame.add(colouJTextField);

		//model label
		JLabel modeJLabel=new JLabel("Model : ");
		modeJLabel.setBounds(140, 170, 300, 30);
		carFrame.add(modeJLabel);

		//model text
		JTextField modelTextField=new JTextField();
		modelTextField.setBounds(250, 170, 100, 30);
		carFrame.add(modelTextField);

		//Mileage label
		JLabel mileageJLabel=new JLabel("Mileage : ");
		mileageJLabel.setBounds(140, 210, 300, 30);
		carFrame.add(mileageJLabel);

		//mileage text
		JTextField mileTextField=new JTextField();
		mileTextField.setBounds(250, 210, 100, 30);
		carFrame.add(mileTextField);

		//seating label
		JLabel seaJLabel=new JLabel("Seating_capacity : ");
		seaJLabel.setBounds(140, 250, 300, 30);
		carFrame.add(seaJLabel);

		//seating text
		JTextField seaTextField=new JTextField();
		seaTextField.setBounds(250, 250, 100, 30);
		carFrame.add(seaTextField);

		//add btn
		JButton adButton=new JButton("Add Car");
		adButton.setBounds(450, 300, 100, 30);
		carFrame.add(adButton);

		//action listners
		adButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postCars(jTextField,priceJTextField,colouJTextField,modelTextField,mileTextField,seaTextField);
				carFrame.dispose();
			}
		});
		carFrame.setLayout(null);
		carFrame.setVisible(true);
	}

	//bookings details
	private void showBookingDetailsFrame() {
		JFrame carFrame = new JFrame("Car Details");
		carFrame.setSize(600, 400);
		carFrame.setLocationRelativeTo(null);

		String[] columnNames = {"rent_id", "rent_start_date", "rent_end_date","total_days","rent_price_per_day","total_amount","booked_on","pickupLocation","dropLocation"};

		JTable carTable = new JTable(getData2(), columnNames);
		JScrollPane scrollPane = new JScrollPane(carTable);
		carFrame.add(scrollPane);

		carFrame.setVisible(true);
	}
	// Sample dynamic count logic
    private String getCarsCount() {
        int count=JDBC.DisplayCount.carCount();
        return String.valueOf(count);
    }

    private String getCustomersCount() {
    	int count=JDBC.DisplayCount.userCount();
        return String.valueOf(count);
    }

    private String getBookedCount() {
    	int count=JDBC.DisplayCount.bookCount();
        return String.valueOf(count);
    }

	//getting data for users
	private static String[][] getData() {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String dbpassword = "@@@Vamsi143";
		java.util.List<String[]> dataList = new java.util.ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, dbpassword);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String[] row = new String[9];
				row[0] = String.valueOf(rs.getInt("user_id"));
				row[1] = rs.getString("first_name");
				row[2] = rs.getString("last_name");
				row[3] = rs.getString("phone");
				row[4] = rs.getString("email");
				row[5] = rs.getString("adhaar");
				row[6] = rs.getString("address");
				row[7] = rs.getString("designation");
				row[8] = rs.getString("password");
				dataList.add(row);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}

		// Convert list to 2D array
		String[][] dataArr = new String[dataList.size()][];
		return dataList.toArray(dataArr);
	}

	//getting data for cars
	private static String[][] getData1() {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String dbpassword = "@@@Vamsi143";
		java.util.List<String[]> dataList = new java.util.ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, dbpassword);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM car";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String[] row = new String[7];
				row[0] = String.valueOf(rs.getInt("car_id"));
				row[1] = rs.getString("brand");
				row[2] = String.valueOf(rs.getDouble("price"));
				row[3] = rs.getString("colour");
				row[4] = rs.getString("model");
				row[5] = rs.getString("mileage");
				row[6] = String.valueOf(rs.getInt("seating_capacity"));
				dataList.add(row);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}

		// Convert list to 2D array
		String[][] dataArr = new String[dataList.size()][];
		return dataList.toArray(dataArr);
	}

	//getting data for bookings
	private static String[][] getData2() {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String dbpassword = "@@@Vamsi143";
		java.util.List<String[]> dataList = new java.util.ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, dbpassword);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM rent";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String[] row = new String[9];
				row[0] = String.valueOf(rs.getInt("rent_id"));
				row[1] = String.valueOf(rs.getDate("rent_start_date"));
				row[2] = String.valueOf(rs.getDate("rent_end_date"));
				row[3] = String.valueOf(rs.getInt("total_days"));
				row[4] = String.valueOf(rs.getDouble("rent_price_per_day"));
				row[5] = String.valueOf(rs.getDouble("total_amount"));
				row[6] = String.valueOf(rs.getTimestamp("booked_on"));
				row[7] = rs.getString("pickupLocation");
				row[8] = rs.getString("dropLocation");
				dataList.add(row);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}

		// Convert list to 2D array
		String[][] dataArr = new String[dataList.size()][];
		return dataList.toArray(dataArr);

	}

	//post cars into db
	private void postCars(JTextField brand,JTextField price,JTextField color,JTextField model,JTextField mileage,JTextField seating) {
		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
		String user = "root";
		String dbpassword = "@@@Vamsi143";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, dbpassword);
			String sql = "insert into car(Brand,price,colour,model,mileage,seating_capacity) values(?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1,String.valueOf(brand.getText().trim()));
			stmt.setDouble(2,Double.parseDouble(price.getText().trim()));
			stmt.setString(3,String.valueOf(color.getText().trim()));
			stmt.setString(4,String.valueOf(model.getText().trim()));
			stmt.setString(5,String.valueOf(mileage.getText().trim()));
			stmt.setInt(6,Integer.parseInt(seating.getText().trim()));
			int row=stmt.executeUpdate();

			if(row>0) {
				JOptionPane.showMessageDialog(new JFrame(), "Registered Successfully");
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
		}
	}

}
