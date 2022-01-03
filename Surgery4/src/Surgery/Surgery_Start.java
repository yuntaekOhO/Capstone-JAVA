package Surgery;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

class Panel01 extends JPanel {
	private static final long serialVersionUID = 1L;
	Surgery_Start pro;
	static MyListener ml;
	static String name;
	JButton startbtn, helpbtn;
	ImageIcon startleeIcon = new ImageIcon("image/main_.png");
	ImageIcon starticon = new ImageIcon("surgery_images/start.png");
	ImageIcon helpicon = new ImageIcon("surgery_images/help.png");
	Image startimg = starticon.getImage();
	Image helpimg = helpicon.getImage();
	Image chstartimg = startimg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
    Image chhelpimg = helpimg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
    Image chstartimgps = startimg.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
    Image chhelpimgps = helpimg.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
    ImageIcon chstarticon = new ImageIcon(chstartimg);
    ImageIcon chhelpicon = new ImageIcon(chhelpimg);
    ImageIcon chstarticonps = new ImageIcon(chstartimgps);
    ImageIcon chhelpiconps = new ImageIcon(chhelpimgps);
	
	Image startleeImg = startleeIcon.getImage();
	public Panel01() { }
	public Panel01(Surgery_Start pro) {
		this.pro = pro;
		setLayout(null);
		startbtn = new JButton(chstarticon);
		helpbtn = new JButton(chhelpicon);
		Surgery_main.borderBT(startbtn);
		Surgery_main.borderBT(helpbtn);
		startbtn.setBounds(380,270,100,100);
		helpbtn.setBounds(580,270,100,100);
		startbtn.setPressedIcon(chstarticonps);
		helpbtn.setPressedIcon(chhelpiconps);
		add(startbtn);
		add(helpbtn);
		ml = new MyListener();
		startbtn.addActionListener(ml);
		helpbtn.addActionListener(ml);
	}
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.drawImage(startleeImg,0,0,getWidth(),getHeight(),this);
	}

	class MyListener implements ActionListener{
		private String startmsg = "이름을 입력하세요.";
		private String helpmsg = "마우스로 도구를 클릭해 수술 부위를 열고 환자의 장기들을 교체하세요!";
		
		File file = new File("c:\\text\\name.txt");
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b == helpbtn) { JOptionPane.showMessageDialog(pro,helpmsg,"Message",JOptionPane.INFORMATION_MESSAGE); }
			else {
				name = JOptionPane.showInputDialog(pro,startmsg,"Message",JOptionPane.INFORMATION_MESSAGE);
				setName(name);
				try (
					BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
					if(name.length()==0) { name = "player"; }
					writer.append(name);
					writer.newLine();
				pro.change("panel02"); 
				} catch(IOException ioe) { 
					ioe.printStackTrace();}
			}
		}
	}

	public void setName(String name) {
		Panel01.name = name;	
		}
	public String getName() {
		return name;
		}
	}

class Panel02 extends JPanel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused") // 경고 억제
	private Surgery_Start pro;
	ArrayList<String> st1List = new ArrayList<String>();
	Panel01 pan1 = new Panel01();
	int clickcount =0;
	
	JButton clickbtn;
	ImageIcon blackIcon = new ImageIcon("image/black.png");
	ImageIcon clickIcon = new ImageIcon("image/click.png");
	ImageIcon lightIcon = new ImageIcon("image/1.png");
	ImageIcon sdIcon = new ImageIcon("image/selfDialogImg.png");
	ImageIcon rdIcon = new ImageIcon("image/RectDialogImg.png");
	ImageIcon npcsayIcon = new ImageIcon("image/npcspk.png");//꺽쇠말풍선
	Image npcsayImg = npcsayIcon.getImage();
	ImageIcon npcLeeIcon = new ImageIcon("image/doctor.png");
	Image npcLeeImg = npcLeeIcon.getImage();// 이국종 사진
	Image lightImg, sdImg;
	
	public Panel02(Surgery_Start pro) {
		this.pro = pro;
		setLayout(null);
		clickbtn = new JButton(clickIcon);
		clickbtn.setBounds(850, 450,clickIcon.getIconWidth(), clickIcon.getIconHeight());
		clickbtn.setBorderPainted(true);
		
		add(clickbtn);
		clickbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				switch(clickcount) {
				case 0:
					st1List.clear();
					st1List.add("??? : !@#$%^%^$#");
					repaint(); clickcount++;
					break;
				case 1:
					st1List.clear();
					st1List.add("이게 무슨소리지..");
					repaint(); clickcount++;
					break;
				case 2:
					st1List.clear();
					st1List.add("");
					lightImg = lightIcon.getImage();
					repaint(); clickcount++;
					break;
				case 3:
					st1List.clear();
					st1List.add("으.. 눈 부셔..");
					repaint(); clickcount++;
					break;
				case 4:
					st1List.clear();
					st1List.add(pan1.getName()+"! 여기서 뭐하고 있어? 응급 환자야! 어서 수술실로 가지!");
					repaint(); clickcount++;
					break;
				case 5:
					st1List.clear();
					st1List.add("무슨 소리지? 급해보이니 일단 가보자..");
					repaint(); clickcount++;
					break;
				case 6:
					Surgery_main runnable = new Surgery_main();
					Thread mainthread = new Thread(runnable);
					mainthread.start();
					pro.dispose();	
					break;				
				}
			}
		});
	}
	Image blImg = blackIcon.getImage();
	
	@Override
	protected void paintComponent(Graphics g) {
		st1List.add("Zzz...");
		sdImg = sdIcon.getImage();
		super.paintComponent(g);
		g.drawImage(blImg,0,0,getWidth(),getHeight(),this);  //검은 배경
		g.drawImage(sdImg,0,256,getWidth(),300,this);		//말풍선
		g.drawImage(lightImg,0,0,getWidth(),getHeight(),this);
		
		if(clickcount==4) {
			st1List.clear();
			st1List.add("으.. 눈 부셔..");
			g.setClip(40, 270, 920, 210); ///검은부분 짜른 말풍선
			g.drawImage(sdImg,0,256,getWidth(),300,this);
			repaint(); 
			}
		if(clickcount>=5) {
			g.drawImage(blImg,0,0,getWidth(),getHeight(),this);  //검은 배경
			g.drawImage(npcsayImg,0,200,getWidth(),300,this);
			g.drawImage(npcLeeImg,250,10,150,250,this);
			repaint();
		}
		if(clickcount>=6) {
			g.drawImage(blImg,0,0,getWidth(),getHeight(),this);  //검은 배경
			g.drawImage(sdImg,0,256,getWidth(),300,this);
		}
	
		g.setFont(new Font("HGGothicE", Font.BOLD, 30));
		g.drawString(st1List.get(0),70,390);
		
	}
	
}

public class Surgery_Start extends JFrame {
	private static final long serialVersionUID = 1L;
	private Panel01 panel01;
	private Panel02 panel02;

	public void change(String panelname) {

		switch(panelname) {
		case "panel01" :
			getContentPane().removeAll();
			getContentPane().add(panel01);
			revalidate();
			repaint(); break;
		case "panel02" :
			getContentPane().removeAll();
			getContentPane().add(panel02);
			revalidate();
			repaint();
			break;
		}			
	}

	public static void main(String[] args) {
		Surgery_Start pro = new Surgery_Start();
		
		pro.setTitle("game");
		pro.panel01 = new Panel01(pro);
		pro.panel02 = new Panel02(pro);
		
		pro.add(pro.panel01);
		pro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pro.setSize(1024,768);
		pro.setVisible(true);
		
	}
	
}



