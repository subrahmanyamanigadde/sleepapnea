package hackathon1;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.TimeZone;

public class gui {

	private JFrame frame;
	private JTextField id;
	private JTextField name;
	private JTextField age;
	private JTextField suggestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 182, 193));
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.getContentPane().setForeground(Color.RED);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSleepApneaPredection = new JLabel("Sleep Apnea Prediction");
		lblSleepApneaPredection.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 20));
		lblSleepApneaPredection.setBounds(103, 11, 230, 25);
		frame.getContentPane().add(lblSleepApneaPredection);
		
		JButton pulse = new JButton("Pulse Rate");
		pulse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Main1 itemloader=new Main1();
			}
		});
			
		pulse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		pulse.setBackground(Color.PINK);
		pulse.setForeground(Color.BLACK);
		pulse.setBounds(262, 44, 149, 23);
		frame.getContentPane().add(pulse);
		
		JButton oxygen = new JButton("Oxygen Level");
		oxygen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		oxygen.setForeground(Color.BLACK);
		oxygen.setBackground(Color.PINK);
		oxygen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Main2 itemloader=new Main2();
			   // itemloader.setVisible(true);
			    //this.setVisible(false);
			}
		});
		frame.setVisible(true);
		oxygen.setBounds(262, 78, 149, 23);
		frame.getContentPane().add(oxygen);
		
		JButton motion = new JButton("Body Motion");
		motion.setForeground(Color.BLACK);
		motion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		motion.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) 
		{
			Main3 itemloader=new Main3();
		}
		});
		motion.setBackground(Color.PINK);
		motion.setBounds(262, 112, 149, 23);
		frame.getContentPane().add(motion);
		
		JButton result = new JButton("Result");
		result.setForeground(Color.BLACK);
		result.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		result.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String value=id.getText();
				Connection con = null;
						 String url = "jdbc:mysql://localhost:3306/";
						 String db = "hackathon";
						 String driver = "com.mysql.jdbc.Driver";
						 String user = "root";
						 String pass = "root";
				try{
				Class.forName(driver);
				con = DriverManager.getConnection(url+db, user, pass);
				PreparedStatement st=con.prepareStatement("select * from info where id=?");
				st.setString(1,value);
				ResultSet res=st.executeQuery();
				res.next();
				id.setText(Integer.toString(res.getInt(1)));
				name.setText(res.getString(2));
				age.setText(res.getString(3));
				suggestion.setText(res.getString(4));
				con.close();
				}
				catch(Exception e)
				{
				Component p2 = null;
				JOptionPane.showMessageDialog(p2,"Error");
				}
				String id1 = id.getText();
				String name1 = name.getText();
				String age1 = age.getText();
				String suggestion1 = suggestion.getText();
				JFrame f1 = new JFrame("Admission Letter");
				f1.setBounds(100, 100, 500, 300);
				f1.setVisible(true);
				JTextArea receipt = new JTextArea();
				receipt.setText("Name:" +name1+ "\nAge:" +age1+ "\nID:" +id1+ "\nSuggestion:\n\n" +suggestion1);
				f1.getContentPane().add(receipt);
				//JOptionPane.showMessageDialog(p3,"Name:" +name+ "\nAge:" +age+ "\nID:" +id+ "\nSuggestion:\n\n" +suggestion);
			}
		});
		
		result.setBackground(Color.YELLOW);
		result.setBounds(262, 182, 149, 23);
		frame.getContentPane().add(result);
		
		id = new JTextField();
		id.setBounds(129, 47, 86, 20);
		frame.getContentPane().add(id);
		id.setColumns(10);
		
		JLabel lblPatientId = new JLabel("Patient ID :");
		lblPatientId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPatientId.setBounds(33, 49, 86, 16);
		frame.getContentPane().add(lblPatientId);
		
		JLabel lblName = new JLabel("Name        :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(33, 82, 86, 14);
		frame.getContentPane().add(lblName);
		
		name = new JTextField();
		name.setBounds(129, 80, 86, 20);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblAge = new JLabel("Age          :");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge.setBounds(33, 112, 86, 14);
		frame.getContentPane().add(lblAge);
		
		age = new JTextField();
		age.setBounds(129, 111, 86, 20);
		frame.getContentPane().add(age);
		age.setColumns(10);
		
		JLabel lblSuggestion = new JLabel("Suggestion :");
		lblSuggestion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSuggestion.setBounds(85, 142, 86, 14);
		frame.getContentPane().add(lblSuggestion);
		
		suggestion = new JTextField();
		suggestion.setBounds(46, 163, 169, 76);
		frame.getContentPane().add(suggestion);
		suggestion.setColumns(10);
		
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String value1=id.getText();
				String value2=name.getText();
				String value3=age.getText();
				String value4=suggestion.getText();
				Connection con = null;
				String url = "jdbc:mysql://localhost:3306/";
				String db = "hackathon";
				String driver = "com.mysql.jdbc.Driver";
				String user = "root";
				String pass = "root";
				System.out.println(value1+value2+value3+value4);
				Component p1 = null;
				try{
				Class.forName(driver);
				con = DriverManager.getConnection(url+db, user, pass);
				PreparedStatement st=con.prepareStatement("insert into info(id, name, age, suggestion) values(?,?,?,?)");
				st.setString(1,value1);
				st.setString(2,value2);
				st.setString(3,value3);
				st.setString(4,value4);
				st.executeUpdate();
				JOptionPane.showMessageDialog(p1,"*** successfull ***");
				con.close();
				}
				catch(Exception e){
				JOptionPane.showMessageDialog(p1,"***Error***");
				}	
			}
		});
		
		add.setBounds(85, 250, 104, 23);
		frame.getContentPane().add(add);
		
		JButton doctor = new JButton("Meet Doctor");
		doctor.setBackground(Color.YELLOW);
		doctor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		doctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		doctor.setBounds(262, 216, 149, 23);
		frame.getContentPane().add(doctor);
		
		JButton temp = new JButton("Temperature");
		temp.setForeground(Color.BLACK);
		temp.setBackground(Color.PINK);
		temp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Main4 itemloader = new Main4();
			}
		});
		temp.setBounds(262, 146, 149, 25);
		frame.getContentPane().add(temp);
	}
}
