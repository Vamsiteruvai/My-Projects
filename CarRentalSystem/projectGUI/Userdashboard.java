package projectGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Userdashboard extends JFrame {

    private JLabel carsCountLabel;
    private JLabel customersCountLabel;
    private JLabel bookedCountLabel;

    public Userdashboard(int id,String name1) {
        setTitle("User Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        // Side Panel
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(172, 160, 255));
        sidePanel.setBounds(0, 0, 150, 600);
        sidePanel.setLayout(new GridLayout(8, 1, 0, 10));

        String[] menuItems = {"Profile","Book a Car","Bookings","Logout"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(172, 160, 255));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 14));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand().equals("Book a Car")) {
                    	new Book_a_car(id,name1);
                    	dispose();
                    }
                    else if(e.getActionCommand().equals("Logout")) {
                    	new LoginGUI();
                    	dispose();
                    }
                    else if(e.getActionCommand().equals("Bookings")) {
                    	showBookingDetailsFrame(id);
                    }
					else if(e.getActionCommand().equals("Profile")) {
                    	new ProfileGUI(id);
                    }
                }
            });

            sidePanel.add(button);
        }

        // user Label
        JLabel adminLabel = new JLabel("Welcome ");
        adminLabel.setFont(new Font("Arial", Font.BOLD, 18));
		adminLabel.setForeground(new Color(102, 204, 0));
        adminLabel.setBounds(160, 10, 200, 30);
        
        JLabel name = new JLabel();
        name.setFont(new Font("Arial", Font.BOLD, 16));
		name.setForeground(Color.BLACK);
        name.setBounds(250, 10, 200, 30);
        name.setText(name1);

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
        add(name);
        add(dashboardPanel);

        setVisible(true);
    }
    
    //show bookings
    private void showBookingDetailsFrame(int id) {
		JFrame carFrame = new JFrame("Book Car");
		carFrame.setSize(600, 400);
		carFrame.setLocationRelativeTo(null);

		String[] columnNames = {"rent_id", "rent_start_date", "rent_end_date","total_days","rent_price_per_day","total_amount","booked_on","pickupLocation","dropLocation"};

		JTable carTable = new JTable(getData2(id), columnNames);
		JScrollPane scrollPane = new JScrollPane(carTable);
		carFrame.add(scrollPane);

		carFrame.setVisible(true);
	}
    
    //getting data for bookings
  	public static String[][] getData2(int id) {
  		String url = "jdbc:mysql://localhost:3306/carrentalsystem";
  		String user = "root";
  		String dbpassword = "@@@Vamsi143";
  		java.util.List<String[]> dataList = new java.util.ArrayList<>();

  		try {
  			Class.forName("com.mysql.cj.jdbc.Driver");
  			Connection con = DriverManager.getConnection(url, user, dbpassword);
  			String sql = "SELECT rent_id,rent_start_date,rent_end_date,total_days,rent_price_per_day,total_amount,booked_on,pickupLocation,dropLocation FROM rent where user_id=?";
  			PreparedStatement stmt = con.prepareStatement(sql);
  			stmt.setInt(1,id);
  			ResultSet rs = stmt.executeQuery();

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
    
}
