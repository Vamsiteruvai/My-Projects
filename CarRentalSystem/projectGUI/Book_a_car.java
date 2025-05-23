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
import javax.swing.JOptionPane;
import java.util.*;

import JDBC.*;


public class Book_a_car extends JFrame{
	private JLabel titleJLabel,carLabel,startLabel,returLabel,rentPerday,pickupLocation,dropLocation,bookStatus,seatLabel;
	private JTextField startDate,returnDate,rentperDayText,pickupLocationText,dropLocationText,bookStatusText,rentperDayText1;
	private JComboBox<String> carNamesBox,carNamesBox1;
	private JButton bookBTN,backBTN,getModel;
	private String[] model=null;
	
	public Book_a_car(int id,String name1) {
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
	    carLabel=new JLabel("Cars & Model : ");
	    carLabel.setBounds(300,90,100,30);
	    add(carLabel);
	    
	    //set drop-downboxes
	    String[] car= {"Select","Toyata","Hyundai","Tata","Kia","Mahindra","Maruti","Renault","Ford","Honda","Skoda","MG"};
	    carNamesBox=new JComboBox<>(car);
	    carNamesBox.setBounds(500,90,100,30);
	    add(carNamesBox);
		
		//set drop-downboxes1
	    model= new String[4];
	    carNamesBox1=new JComboBox<>(model);
	    carNamesBox1.setBounds(610,90,100,30);
	    add(carNamesBox1);
		
		//set getModel button
		getModel=new JButton("GetModel");
		getModel.setBounds(720,90,100,30);
		getModel.setBackground(Color.GREEN);
		getModel.setForeground(Color.white);
		getModel.setFocusPainted(false);   // Removes focus highlight (dotted border)
		getModel.setBorderPainted(false); 
		add(getModel);
		
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
	    rentPerday=new JLabel("Rent PerDay(5&7) : ");
	    rentPerday.setBounds(300,210,120,30);
	    add(rentPerday);
	    
	    //set rent per day Text Field
	    rentperDayText=new JTextField("1500");
	    rentperDayText.setBounds(500,210,100,30);
		rentperDayText.setEditable(false);
	    add(rentperDayText);
		
		//set rent per day Text1 Field
	    rentperDayText1=new JTextField("2000");
	    rentperDayText1.setBounds(610,210,100,30);
		rentperDayText1.setEditable(false);
	    add(rentperDayText1);
		
	    //set label
		seatLabel=new JLabel("Rent per day Amount will based on Car seating capacity(5/7)");
		seatLabel.setBounds(500,240,400,30);
		add(seatLabel);
		
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
	            new Userdashboard(id,name1);
	            dispose();
	        }
	    });
		
		bookBTN.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				String str=(String)carNamesBox.getSelectedItem();
				double seat=CarPrice.getSeatingNumber(str);
				String model2=(String)carNamesBox1.getSelectedItem();
				try{
					book_car.bookNow(carNamesBox,startDate,returnDate,seat,pickupLocationText,dropLocationText,id,model2);
				}
	            catch(Exception exception){
					JOptionPane.showMessageDialog(new JFrame(), "Please fill all the Fields");
				}
	            startDate.setText("");
	            returnDate.setText("");
	            pickupLocationText.setText("");
	            dropLocationText.setText("");
				carNamesBox.setSelectedItem("select");
	        }
	    });
		
		getModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String brand = (String) carNamesBox.getSelectedItem();

				// Avoid invalid brand selection
				if (brand.equals("Select")) {
					JOptionPane.showMessageDialog(new JFrame(), "Please select a valid brand.");
					return;
				}

				String[] models = CarPrice.getModels(brand);

				if (models != null && models.length > 0) {
					// Update the JComboBox with the new model array
					carNamesBox1.setModel(new javax.swing.DefaultComboBoxModel<>(models));
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "No models found for this brand.");
					carNamesBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"No Models"}));
				}
			}
		});

	}
}
