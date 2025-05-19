package projectGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import JDBC.book_car;

public class Book_a_car extends JFrame{
	private JLabel titleJLabel,carLabel,startLabel,returLabel,rentPerday,pickupLocation,dropLocation,bookStatus;
	private JTextField startDate,returnDate,rentperDayText,pickupLocationText,dropLocationText,bookStatusText;
	private JComboBox<String> carNamesBox;
	private JButton bookBTN,backBTN;
	
	public Book_a_car(int id) {
		setTitle("Book a Car");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(900, 600);
	    setLocationRelativeTo(null);
	    setLayout(null);
	    
	    
	    //set title label
	    titleJLabel=new JLabel("Book a Car");
	    titleJLabel.setBounds(400, 30, 300, 30);
	    titleJLabel.setFont(new Font("Arial", Font.BOLD, 18));
	    titleJLabel.setForeground(Color.RED);
	    add(titleJLabel);
	    
	    //set car label
	    carLabel=new JLabel("Cars : ");
	    carLabel.setBounds(300,90,100,30);
	    add(carLabel);
	    
	    //set drop-downboxes
	    String[] car= {"Select","Toyata","Hyundai","Tata","Kia","Mahindra","Maruti","Renault","Ford","Honda","Skoda","MG"};
	    carNamesBox=new JComboBox<>(car);
	    carNamesBox.setBounds(500,90,100,30);
	    add(carNamesBox);
	    
	    //set start label
	    startLabel=new JLabel("Start Date(YYYY-MM-DD) : ");
	    startLabel.setBounds(300,130,180,30);
	    add(startLabel);
	    
	    //set Text Field
	    startDate=new JTextField();
	    startDate.setBounds(500,130,100,30);
	    add(startDate);
	    
	    //set return label
	    returLabel=new JLabel("Return Date(YYYY-MM-DD) : ");
	    returLabel.setBounds(300,170,180,30);
	    add(returLabel);
	    
	    //set Text Field
	    returnDate=new JTextField();
	    returnDate.setBounds(500,170,100,30);
	    add(returnDate);
	    
	    //set rentperday label
	    rentPerday=new JLabel("Rent PerDay : ");
	    rentPerday.setBounds(300,210,100,30);
	    add(rentPerday);
	    
	    //set rent per day Text Field
	    rentperDayText=new JTextField();
	    rentperDayText.setBounds(500,210,100,30);
	    add(rentperDayText);
	    
	    //set pickupLocation label
	    pickupLocation=new JLabel("Pickup Location : ");
	    pickupLocation.setBounds(300,290,100,30);
	    add(pickupLocation);
	    
	    //set pickupLocation Text Field
	    pickupLocationText=new JTextField();
	    pickupLocationText.setBounds(500,290,100,30);
	    add(pickupLocationText);
	    
	    //set dropLocation label
	    dropLocation=new JLabel("Drop Location : ");
	    dropLocation.setBounds(300,330,100,30);
	    add(dropLocation);
	    
	    //set pickupLocation Text Field
	    dropLocationText=new JTextField();
	    dropLocationText.setBounds(500,330,100,30);
	    add(dropLocationText);
	    
	    
	    //set bookBTN 
	    bookBTN=new JButton("Book Now");
	    bookBTN.setBounds(750,500,100,30);
	    add(bookBTN);
	    
	    //set backBTN 
	    backBTN=new JButton("back");
	    backBTN.setBounds(100,500,100,30);
	    add(backBTN);
	    setVisible(true);
	    
	    //action listners
		backBTN.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            new Userdashboard(0);
	            dispose();
	        }
	    });
		
		bookBTN.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            book_car.bookNow(carNamesBox,startDate,returnDate,rentperDayText,pickupLocationText,dropLocationText,id);
	            startDate.setText("");
	            returnDate.setText("");
	            rentperDayText.setText("");
	            pickupLocationText.setText("");
	            dropLocationText.setText("");
	        }
	    });
	}
}
