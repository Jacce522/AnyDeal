import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jsoup.Jsoup;
import javax.swing.JScrollPane;






public class AnyDeal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		
		intializeFrame();
		

	}
	
	public static void intializeFrame()
	{
		JFrame mainFrame = new JFrame("Any Deal");
		mainFrame.setSize(500,500);
		mainFrame.setLayout(new GridLayout(3,1));
		JPanel p = new JPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Container contentPane = mainFrame.getContentPane();
		
		
		//mainFrame.getContentPane().add(new JButton("Free Deals"));
		JTextField searchField = new JTextField(20);
		
		JButton button = new JButton("Search");
		JButton button2 = new JButton("Free Deals");
		JButton button3 = new JButton("Less than");
		JButton button4 = new JButton("Top 10 Cheapest Deals");
		p.add(searchField);
		p.add(button);
		p.add(button2);
		p.add(button3);
		p.add(button4);
		mainFrame.add(p);
		//mainFrame.getContentPane().add(button);
	
		
		button.addActionListener(e -> {
			try {
				search(searchField, mainFrame);
			} catch (IOException e1) {
				e1.printStackTrace();
			} //searchbar(mainFrame);
		});
		
		
		
				button2.addActionListener(e -> {try {
			free(mainFrame); //freeDeals(mainFrame);
		} catch (MalformedURLException e1) {
				e1.printStackTrace();
		} catch (IOException e1) {
				e1.printStackTrace();
		}});


		button3.addActionListener(e -> {
		try {
			lessThan(searchField, mainFrame);
			} catch (IOException e1) {
						e1.printStackTrace();
					
			}});
				
		
		button4.addActionListener(e -> {
			try {
				top5Cheapest(mainFrame);
				} catch (IOException e1) {
							e1.printStackTrace();
						
				}});
		
		
		
				
	
	
		mainFrame.setVisible(true);
		
	}
	
	
	
	public static JFrame freeDeals(JFrame x) throws IOException
	{
			
		        
		
		JTextField t = new JTextField(16);
		 Font fo = new Font("Serif", Font.BOLD, 20);
		 t.setFont(fo);
		x.getContentPane().add(t);
		x.setVisible(true);
		return x;
	}
	
	
	
	
	public static JFrame search(JTextField x, JFrame y) throws IOException
	{
		String answer = x.getText();
		
		
		
		try {

			
		
			org.jsoup.nodes.Document doc = Jsoup.connect("https://www.isthereanydeal.com").userAgent("Mozilla/5.0").maxBodySize(10*1024*1024).timeout(600000).get();
		  org.jsoup.select.Elements titles = doc.select("div.title"); //getting the titles of games
		  org.jsoup.select.Elements prices = doc.select("div.deals.dyn-semi"); //getting the prices
		    
		    
		     
		   String searchDeal ="";
		     

		    
		     
		     for(org.jsoup.nodes.Element r : titles)
		     {
		    	 
		    	
		    	if(r.text().toLowerCase().contains(answer))
		    	{
		    		
		    		System.out.println(r.text().replaceAll("steam|\\?",""));
		    		searchDeal += r.text().replaceAll("steam|\\?","") + "\n";
		    		

		    		
		    		
		    	
		    	}//end of if
		    		
		    	
		    	
		     }//end of for
		    
		     searchDeal += "---------------------------\n";
		     
		    
		     
		     for(org.jsoup.nodes.Element t : prices)
	    		{
		    	 
   	 
	    			
	    			String tstring = t.toString();
	    			
	    			    			
	    			
	    			
	    			if(tstring.contains(answer.toLowerCase().replaceAll("\\s", "")))
	    			{
	    				
	    				System.out.println(t.text().replaceAll("\\$", " \\$"));
	    				
	    				searchDeal += t.text().replaceAll("\\$", " \\$") + "\n";
	    				   				
	    				  				
	   	    		 
	    			}
	    			
				
	    				
	    			
	    			
	    		}
		     
		    
		     
		     
		     
		  JTextArea t = new JTextArea(searchDeal);
		  Font fo = new Font("Serif", Font.BOLD, 16);
			t.setFont(fo);
			y.getContentPane().add(t);
			y.setVisible(true);
		   
		    
		     
		}catch (Exception e)
		{
			System.out.println(e);
		}
		
		
		
		return y; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static JFrame free(JFrame y) throws IOException
	{
		
		try {

		org.jsoup.nodes.Document doc = Jsoup.connect("http://www.isthereanydeal.com").timeout(6000).get();
					
	     
	     org.jsoup.select.Elements titles = doc.select("div.title"); //getting the titles of games
	     org.jsoup.select.Elements prices = doc.select("div.deals.dyn-semi"); //getting the prices
	     int count = 0;
	     int totalCount=0;
	     String freeDeals = "";
	     String var ="";
	      
	     
	   
	     for(org.jsoup.nodes.Element x : prices)
	     {
	    	
	    	 char gameArray[] = x.toString().toCharArray();
	    		

	    
	    	 
	    	  
	    	 if(x.text().contains("$0.00"))
	    	 {
	    		
	    		 var += x;
	    		 count = prices.indexOf(x) + 5;
	    		 System.out.println(titles.get(count).text().replaceAll("steam|\\?",""));	
	    		 freeDeals += titles.get(count).text().replaceAll("steam|\\?","") + " ";
	    		 totalCount++;
	    		 freeDeals += "\n";
	    		
	    		 
	    		 for(int h = 0; h < gameArray.length; h++)
					{
						//System.out.print(gameArray[h]);
						if(gameArray[h] == 'c' && gameArray[h+1] == '=')
						{
							
							System.out.print(gameArray[h+3]);
							System.out.print(gameArray[h+4]);
							System.out.print(gameArray[h+5]);
							System.out.print(gameArray[h+6]);
							System.out.print(gameArray[h+7]);
							System.out.print(gameArray[h+8] + " ");
							
							
						}
					}
	    		 System.out.println();
			    
	    	
	    		
	    	 }
	    	
	    	
	     }
	     
	     if(totalCount == 0)
	    	 freeDeals+= "There are no free games!";
	     else if(totalCount == 1)
	    	 freeDeals+= "There is " + totalCount + " free game!";
	     else
	    	 freeDeals+= "There are " + totalCount + " free games!";
	   
	     
	     
	 
	  
	  JTextArea t = new JTextArea(freeDeals);
		 Font fo = new Font("Serif", Font.BOLD, 16);
		 t.setFont(fo);
		y.getContentPane().add(t);
		y.setVisible(true);
	     
	  	     
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return y;
		
	}
	
	
	
	
	
	

	public static JFrame lessThan(JTextField str, JFrame x) throws IOException
	{
		
		String answer = str.getText(); //get what user types in
		
		double number = Double.parseDouble(answer); //convert answer to a double
		int count = 0; 
		String finalproduct = "";
		
		org.jsoup.nodes.Document doc = Jsoup.connect("http://www.isthereanydeal.com").timeout(6000).get();
	    org.jsoup.select.Elements titles = doc.select("div.title"); //getting the titles of games
	    org.jsoup.select.Elements prices = doc.select("div.deals.dyn-semi"); //getting the prices
		
	    finalproduct +="Titles less than " + answer + "\n";
	    finalproduct += "---------------------------\n";
	    System.out.println("Titles less than " + answer);
	    System.out.println("---------------------------");
		for(org.jsoup.nodes.Element o : prices)
		{
			
			//if(o.toString().contains("data-slc"))
				
			
			char array[] = o.text().toCharArray();
			boolean flag = false;
			int digit, digit2;
			int pricePosition = 0;
			//System.out.println(o.text());
			for(int i=0; i < array.length; i++)
			{
				
				
				if(o.text().contains("."))
				{
					pricePosition = prices.indexOf(o) + 5;
					//System.out.println("price" + pricePosition);
				}
				
				if(array[i] == '.')
				{
					
					if(Character.isDigit(array[i-1]) && !Character.isDigit(array[i-2]) && flag == false)
					{
						 
						digit = Character.getNumericValue(array[i-1]);
						char gameArray[] = o.toString().toCharArray();
						//System.out.println("digit " + digit);
						if(digit < number)
						{
							
							
							
								
							System.out.println(titles.get(pricePosition).text().replaceAll("steam|\\?|bundled", ""));
								System.out.println(o.text());
								finalproduct+=titles.get(pricePosition).text().replaceAll("steam|\\?|bundled", "");
								finalproduct += "\n";
								finalproduct +=	o.text();
								finalproduct += "\n";
								finalproduct += "\n";
								
								
								for(int h = 0; h < gameArray.length; h++)
								{
									//System.out.print(gameArray[h]);
									if(gameArray[h] == 'c' && gameArray[h+1] == '=')
									{
										
										System.out.print(gameArray[h+3]);
										System.out.print(gameArray[h+4]);
										System.out.print(gameArray[h+5]);
										System.out.print(gameArray[h+6]);
										System.out.print(gameArray[h+7]);
										System.out.print(gameArray[h+8] + " ");
										
										
									}
								}
								System.out.println();				
								System.out.println();
								count++;
								flag = true;
							
								
						}
						
						
						
					}
					
					
					else if(Character.isDigit(array[i-2]) && Character.isDigit(array[i-1]) && flag == false)
					{
						
						digit = Character.getNumericValue(array[i-2]) * 10;
						digit2 = Character.getNumericValue(array[i-1]);
						digit += digit2;
						//System.out.println("digit " + digit);
						char gameArray[] = o.toString().toCharArray();
						
						if(digit < number)
						{
							
							
						
							
					
							
								System.out.println(titles.get(pricePosition).text().replaceAll("steam|\\?|bundled",""));
								System.out.println(o.text());
								finalproduct+=titles.get(pricePosition).text().replaceAll("steam|\\?|bundled", "");
								finalproduct += "\n";
								finalproduct +=	o.text();
								finalproduct += "\n";
								finalproduct += "\n";
															
								for(int h = 0; h < gameArray.length; h++)
								{
								
									if(gameArray[h] == 'c' && gameArray[h+1] == '=')
									{
										
										System.out.print(gameArray[h+3]);
										System.out.print(gameArray[h+4]);
										System.out.print(gameArray[h+5]);
										System.out.print(gameArray[h+6]);
										System.out.print(gameArray[h+7]);
										System.out.print(gameArray[h+8] + " ");
										
										
									}
								}
								System.out.println();
								System.out.println();
								
								
								count++;
								flag = true;
								
						}
		
					
					}
				
				}
				
			}
			
			
			
			

				

	
			
		}
		
		
		finalproduct +=	"There are: " + count + " games for under $" + answer + " dollars";		
		
		System.out.println("There are: " + count + " games for under $" + answer + " dollars");
		
		//System.out.println(finalproduct);
		JTextArea product = new JTextArea(finalproduct);
		JScrollPane sampleScrollPane = new JScrollPane (product);
		
		 Font fo = new Font("Serif", Font.BOLD, 16);
		 product.setFont(fo);
		x.getContentPane().add(sampleScrollPane);
		x.setVisible(true);
	     
	  	     
		
		
		
		return x;
		
	}
	
	
	
	
	
	public static JFrame top5Cheapest(JFrame x) throws IOException
	{
		
		
		
		
		  org.jsoup.nodes.Document doc = Jsoup.connect("http://www.isthereanydeal.com").get();
	    org.jsoup.select.Elements titles = doc.select("div.title"); //getting the titles of games
	    org.jsoup.select.Elements prices = doc.select("div.deals.dyn-semi"); //getting the prices
	    int count = 0;
	    
	    ArrayList<AbstractMap.SimpleEntry<Integer, Integer> > gameArray = new ArrayList<AbstractMap.SimpleEntry<Integer, Integer>>();
	   
	   ArrayList<Float> priceArray = new ArrayList<>();
	  ArrayList<Integer> positionArray = new ArrayList<>();
	 
	  
	  
		float digit;
		int pricePosition = 0;
		float cents = 0;
		
	    for(org.jsoup.nodes.Element o : prices)
	    { 
	    	char array[] = o.text().toCharArray();
	    	boolean flag = false;

	    	for(int i =0; i < array.length; i++)
	    	{
	    		
	    		if(o.text().contains("."))
		    	{
		    		pricePosition = prices.indexOf(o)+ 5;
		    		
		    	}
	    		if(array[i] == '.')
	    		{
	    			if(Character.isDigit(array[i-1]) && !Character.isDigit(array[i-2]) && flag == false)
	    			{
	    				//System.out.println("otext " + o.text());
	    				digit = Character.getNumericValue(array[i-1]);
	    				cents = Character.getNumericValue(array[i+1])*10;
	    				//System.out.println("multi 10 :" +cents);
	    				cents += Character.getNumericValue(array[i+2]);
	    				
	    				digit += cents/100;
	    				//System.out.println("this is digit: " + digit);
	    				
	    				if(digit >= 0.01)
	    				{
	    					//System.out.println(o.text());
	    					priceArray.add(digit);
	    					
	    				 positionArray.add(pricePosition);
	      	
	    					
	    				}
	    				    				

	    				
	    			

	    				
	    			}

	    		}
	    		
	    		
	    		
	    		
	    		
	    	}
	    	
	    
	    	
	    		
	    }
	   


	  sorted(priceArray, positionArray, titles, prices);
	  

	   
	
		return x;
		
	}
	
	
	
	
	public static void sorted(ArrayList<Float> price, ArrayList<Integer> position, org.jsoup.select.Elements titles, org.jsoup.select.Elements prices)
	{
		
		float[] priceyArray = new float[price.size()];
		int[] posArray = new int[position.size()];
		
		for(int i=0; i < position.size(); i++)
		{
			priceyArray[i]=(float) price.get(i);
			posArray[i]=(int) position.get(i);
		
		}
	
		
		int temp;
		float pricetemp;
		
		
		
		
		for(int j = 0; j < priceyArray.length - 1; j++)
		{
			for(int k = 0; k < priceyArray.length - j - 1; k++)
			{
				if(priceyArray[k] > priceyArray[k+1])
				{
					pricetemp = priceyArray[k];
					priceyArray[k] = priceyArray[k+1];
					priceyArray[k+1] = pricetemp;
					
					temp = posArray[k];
					posArray[k] = posArray[k+1];
					posArray[k+1] = temp;
				}
			}
	
			
		}
		

		int count = 0;
		for(int e = 0 ; e < posArray.length; e++)
		{
			
			System.out.println(e+1 + ": " +titles.get(posArray[e]).text());
			
			System.out.println(prices.get(posArray[e]-5).text());
		
			System.out.printf("$%.02f\n\n",  priceyArray[e]);
			count++;
		}
		
	}// end of method
	
	
	
}
