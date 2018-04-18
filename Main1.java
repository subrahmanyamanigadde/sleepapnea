package hackathon1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.mysql.jdbc.ResultSetImpl;

public class Main1{
    //@SuppressWarnings("deprecation")
	static TimeSeries ts = new TimeSeries("data", Millisecond.class);
	

    public static void main(String[] args) throws InterruptedException 
    {
    	
   }
    	public Main1() 
    	{
        gen myGen = new gen();
        new Thread(myGen).start();

        TimeSeriesCollection dataset = new TimeSeriesCollection(ts);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Pulse Rate",
            "Time",
            "Rate",
            dataset,
            true,
            true,
            false
        );
        final XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);

        JFrame f = new JFrame("Pulse Rate");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel label = new ChartPanel(chart);
        f.getContentPane().add(label);
        //Suppose I add combo boxes and buttons here later

        f.pack();
        f.setVisible(true);
	}

    static class gen implements Runnable 
    {
       // private Random randGen = new Random();        
			
        
        public void run() 
        {
 			Component p3 = null;
 			JOptionPane.showMessageDialog(p3,"\t\t\t\tINFO :\nTachycardia is a fast heart rate, defined as above 100 bpm at rest."
 					+ "\nBradycardia is a slow heart rate, defined as below 60 bpm at rest."
 					+ "\nDuring sleep a slow heartbeat with rates around 72 bpm is common and is considered normal.");
        	int time = 1;
            int num = 0;
			while(true) 
            {
      
        			int valuepulse=time;
        			Connection con = null;
        					 String url = "jdbc:mysql://localhost:3306/";
        					 String db = "hackathon";
        					 String driver = "com.mysql.jdbc.Driver";
        					 String user = "root";
        					 String pass = "root";
        			try{
        			Class.forName(driver);
        			con = DriverManager.getConnection(url+db, user, pass);
        			PreparedStatement st=con.prepareStatement("select * from pulse where time=?");
        			st.setLong(1,valuepulse);
        			ResultSet res=st.executeQuery();
        			res.next();
        			//num.setText(Integer.toString(res.getInt(1)));
        			num =res.getInt(2);
        			System.out.println(num);
        			System.out.println(time);
        			con.close();
        			}
        			catch(Exception e)
        			{
        			Component p2 = null;
        			//JOptionPane.showMessageDialog(p2,"Error");
        			}
        			
        	     ts.addOrUpdate(new Millisecond(), num);
                 ++time;
                 
                 if(num<71)
                 {
     			Component p2 = null;
     			JOptionPane.showMessageDialog(p2,"Low pulse rate found");
                 }
                 
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) 
                 {
                     System.out.println(ex);
                 }
            }
        }
    }
}