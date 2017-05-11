import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main extends JFrame implements ActionListener{
   
	private DefaultListModel data;
	private JList treeList;
	private JScrollPane scroll;
	private JButton addMovie;
    private JButton getMovie;
    private JButton changeStatus;
    private JButton remove;
    private JLabel movie, tell;
    private JTextField enter;
    private String PATH = "MovieListNoelle.txt";
    private ArrayList movieList = new ArrayList();
    
	public static void main(String[] args){
		Main main = new Main();
		main.setVisible(true);
	}
	
	public Main(){
		setLayout(new BorderLayout());
	    setSize(700, 500);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    JPanel p1 = new JPanel();
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    JPanel p4 = new JPanel();
	    JPanel p5 = new JPanel(new BorderLayout());
	    JPanel p6 = new JPanel();
	    JPanel p9 = new JPanel();
	    JPanel p8 = new JPanel();
	    JPanel p10 = new JPanel();
	    JPanel p7 = new JPanel(new GridLayout(1,2));
	    data = new DefaultListModel();
	    data.addElement("---------------------------------------------Movies---------------------------------------------");
	    data.addElement("________________________________________________________________________________________________");
	    treeList = new JList(data);
	    treeList.setFont(new Font("Monospaced", 0, 12));
	    scroll = new JScrollPane(treeList);
	    addMovie = new JButton("Add Movie");
	    changeStatus = new JButton("Change Status");
	    getMovie = new JButton("Random Movie");
	    remove = new JButton("remove");
		addMovie.addActionListener(this);
		getMovie.addActionListener(this);
		changeStatus.addActionListener(this);
		remove.addActionListener(this);
	    movie = new JLabel("---------");
	    tell = new JLabel("Enter new film here ---->");
	    enter = new JTextField(20);
	    add("North",p5);
	    	p5.setBackground(Color.BLACK);
		    p5.add("North", p1);
		    	p1.add(getMovie);
			    	getMovie.setBackground(Color.DARK_GRAY);
			    	getMovie.setContentAreaFilled(true);
			    	getMovie.setOpaque(true);
			    	getMovie.setBorderPainted(false);
			    	getMovie.setFont(new Font("Monospaced", 0, 12));
			    	getMovie.setForeground(Color.GREEN);
		    	p1.setBackground(Color.BLACK);
		    p5.add("Center", p10);
		    	p10.setBackground(Color.BLACK);
			    p10.add(p2);
			    	p2.add(addMovie);
				    	addMovie.setBackground(Color.DARK_GRAY);
				    	addMovie.setContentAreaFilled(true);
				    	addMovie.setOpaque(true);
				    	addMovie.setBorderPainted(false);
				    	addMovie.setFont(new Font("Monospaced", 0, 12));
				    	addMovie.setForeground(Color.GREEN);
			    	p2.setBackground(Color.BLACK);
			    p10.add(p9);
			    	p9.add(tell);
			    	p9.setBackground(Color.BLACK);
				    	tell.setBackground(Color.BLACK);
				    	tell.setOpaque(true);
				    	tell.setFont(new Font("Monospaced", 0, 12));
				    	tell.setForeground(Color.GREEN);
			    p10.add(p3);
			    	p3.add(enter);
				    	enter.setBackground(Color.DARK_GRAY);
				    	enter.setOpaque(true);
				    	enter.setFont(new Font("Monospaced", 0, 12));
				    	enter.setForeground(Color.GREEN);
			    	p3.setBackground(Color.BLACK);
		    p5.add("South", p4);
		    	p4.add(movie);
			    	movie.setBackground(Color.BLACK);
			    	movie.setOpaque(true);
			    	movie.setFont(new Font("Monospaced", 0, 12));
			    	movie.setForeground(Color.GREEN);
		    	p4.setBackground(Color.BLACK);
	    add("Center",scroll);
	    treeList.setBackground(Color.BLACK);
	    treeList.setOpaque(true);
	    treeList.setFont(new Font("Monospaced", 0, 12));
	    treeList.setForeground(Color.GREEN);
	    scroll.setBackground(Color.BLACK);
	    scroll.setOpaque(true);
	    scroll.setFont(new Font("Monospaced", 0, 12));
	    scroll.setForeground(Color.GREEN);
	    add("South", p7);
	    	p7.setBackground(Color.BLACK);
	    	p7.add(p6);
	    		p6.add(changeStatus);
		    		changeStatus.setBackground(Color.DARK_GRAY);
		    		changeStatus.setContentAreaFilled(true);
		    		changeStatus.setOpaque(true);
		    		changeStatus.setBorderPainted(false);
		    		changeStatus.setFont(new Font("Monospaced", 0, 12));
		    		changeStatus.setForeground(Color.GREEN);
	    		p6.setBackground(Color.BLACK);
	    	p7.add(p8);
	    		p8.add(remove);
	    			remove.setBackground(Color.DARK_GRAY);
	    			remove.setContentAreaFilled(true);
	    			remove.setOpaque(true);
	    			remove.setBorderPainted(false);
	    			remove.setFont(new Font("Monospaced", 0, 12));
	    			remove.setForeground(Color.GREEN);
	    		p8.setBackground(Color.BLACK);
		EasyReader reader = new EasyReader(PATH);
		String currentFilm = "";
		int i = 0;
		while(currentFilm!=null){
			currentFilm = reader.readLine();
			data.addElement(currentFilm);
			movieList.add(currentFilm);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addMovie){
			addMovieNow();
			save();
			reload();
			enter.setText("");
		}
		else if(e.getSource()==getMovie){
			getMovieNow();
		}
		else if(e.getSource()==changeStatus){
			changeStatus();
			save();
			reload();
		}
		else if(e.getSource()==remove){
			remove();
			save();
			reload();
		}
	}

	private void remove() {
		int i = treeList.getSelectedIndex()-2;
		movieList.remove(i);
	}

	private void changeStatus() {
		int i = treeList.getSelectedIndex()-2;
		StringTokenizer tok = new StringTokenizer(""+movieList.get(i), " ");
		String k = tok.nextToken(), v;
		if(k.equals("[")){
			k = "";
			tok.nextToken();
			while(tok.hasMoreElements())
				k = k+tok.nextToken().trim()+" ";
			k = "[X] " + k;
		}
		else {
			k = "";
			while(tok.hasMoreElements())
				k = k +tok.nextToken().trim()+" ";
			k = "[ ] " + k;
		}
		movieList.remove(i);
		movieList.add(movieList.size()-1,k);
	}

	public void reload(){

		data.clear();
		data.addElement("----------------------------------Movies----------------------------------");
	    data.addElement("__________________________________________________________________________");
		EasyReader reader = new EasyReader(PATH);
		String currentFilm = "";
		while(currentFilm!=null){
			currentFilm = reader.readLine();
			data.addElement(currentFilm);
		}
	}
	
	private void addMovieNow() {
		String i = "[ ] "+enter.getText().trim();
		if(!i.equals("[ ] ")){
		System.out.println(i);
		movieList.add(movieList.size()-1,i);
		System.out.println(""+movieList.get(movieList.size()-1));
		}
	}

	private void getMovieNow() {
		int i = movieList.size();
		while(true){
			int j = (int)System.currentTimeMillis();
			i = (-1*j)%i;
			StringTokenizer tok = new StringTokenizer(""+movieList.get(i), " ");
			String k = tok.nextToken();
			if(k.equals("[")) break; 
		}
		movie.setText(""+movieList.get(i));
	}
	
  public void save()
  {
    String dataInfo = "";
    int i = 0;
    String s;
    s = ""+movieList.get(i);
    while (true) {
      dataInfo = dataInfo + s + "\n";
      i++;
      if(movieList.get(i)!=null)
    	  s = ""+movieList.get(i);
      else break;
    }
    try
    {
      PrintWriter writer = new PrintWriter("MovieListNoelle.txt");
      writer.print("");
      writer.close();
      FileWriter countyFile = new FileWriter("MovieListNoelle.txt", true);
      BufferedWriter bufferedWriter = new BufferedWriter(countyFile);
      bufferedWriter.write(dataInfo);
      bufferedWriter.flush();
      bufferedWriter.close();
      countyFile.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
