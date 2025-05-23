package projectGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import JDBC.postUpdatedData;
import projectClasses.Car;

public class carUpdationGUI extends JFrame{
	private JLabel titleJLabel,brandLabel,priceLabel,colorLabel,modelLabel,mileageLabel,seatLabel,idLabel;
	private JTextField brandText,priceText,colorText,modelText,mileageText,seatText,idText;
	private JButton updateBTN,getBTN;
	public carUpdationGUI() {
		setTitle("Car Updation");
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(900, 600);
	    setLocationRelativeTo(null);
	    setLayout(null);
	    
	    
	    //set title label
	    titleJLabel=new JLabel("Update");
	    titleJLabel.setBounds(400, 30, 300, 30);
	    titleJLabel.setFont(new Font("Arial", Font.BOLD, 18));
	    titleJLabel.setForeground(Color.RED);
	    add(titleJLabel);
	    
	    //set id label
	    idLabel=new JLabel("Id : ");
	    idLabel.setBounds(300,90,100,30);
	    add(idLabel);
	    
	    //set id text
	    idText=new JTextField();
	    idText.setBounds(450,90,150,30);
	    add(idText);
	    
	    //set brand label
	    brandLabel=new JLabel("Brand : ");
	    brandLabel.setBounds(300,130,100,30);
	    add(brandLabel);
	    
	    //set brand text
	    brandText=new JTextField();
	    brandText.setBounds(450,130,150,30);
	    add(brandText);
	    
	    //set price label
	    priceLabel=new JLabel("price : ");
	    priceLabel.setBounds(300,170,180,30);
	    add(priceLabel);
	    
	    //set price Text
	    priceText=new JTextField();
	    priceText.setBounds(450,170,150,30);
	    add(priceText);
	    
	    //set color label
	    colorLabel=new JLabel("color : ");
	    colorLabel.setBounds(300,210,180,30);
	    add(colorLabel);
	    
	    //set color Text
	    colorText=new JTextField();
	    colorText.setBounds(450,210,150,30);
	    add(colorText);
	    
	    //set model label
	    modelLabel=new JLabel("Model : ");
	    modelLabel.setBounds(300,250,100,30);
	    add(modelLabel);
	    
	    //set model Text
	    modelText=new JTextField();
	    modelText.setBounds(450,250,150,30);
	    add(modelText);
	    
	    //set mileage label
	    mileageLabel=new JLabel("Mileage : ");
	    mileageLabel.setBounds(300,290,100,30);
	    add(mileageLabel);
	    
	    //set mileage Text
	    mileageText=new JTextField();
	    mileageText.setBounds(450,290,150,30);
	    add(mileageText);
	    
	    //set seating label
	    seatLabel=new JLabel("Seating_Capacity : ");
	    seatLabel.setBounds(300,330,150,30);
	    add(seatLabel);
	    
	    //set seating Text
	    seatText=new JTextField();
	    seatText.setBounds(450,330,150,30);
	    add(seatText);
	    
	    //set get records 
	    getBTN=new JButton("get records");
	    getBTN.setBounds(750,500,100,30);
	    add(getBTN);
	    
	    //set updateBTN 
	    updateBTN=new JButton("update");
	    updateBTN.setBounds(600,500,100,30);
	    add(updateBTN);
	    
	    setVisible(true);
	    
	    //action listners
		getBTN.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	Car car = JDBC.carUpdation.getUpdationData(idText);
                if (car != null) {
                	idText.setText(String.valueOf(car.id));
                    brandText.setText(car.brand);
                    priceText.setText(String.valueOf(car.price));
                    colorText.setText(car.colour);
                    modelText.setText(car.model);
                    mileageText.setText(car.mileage);
                    seatText.setText(String.valueOf(car.seating_capacity));
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Student not found.");
                }
	        }
	    });
		
		updateBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int id,seating_capacity;
				Double price;
				String brand=brandText.getText().trim();
				String colour=colorText.getText().trim();
				String model=modelText.getText().trim();
				String mileage=mileageText.getText().trim();
				try {
					id=Integer.parseInt(idText.getText().trim());
					seating_capacity=Integer.parseInt(seatText.getText().trim());
					price=Double.parseDouble(priceText.getText().trim());
				}catch (Exception ex) {
					id=0;
					seating_capacity=0;
					price=0.0;
				}
				postUpdatedData.postData(id,brand,price,colour,model,mileage,seating_capacity);
				idText.setText("");
				brandText.setText("");
				priceText.setText("");
				colorText.setText("");
				modelText.setText("");
				mileageText.setText("");
				seatText.setText("");
				dispose();
			}
		});
	}
}
