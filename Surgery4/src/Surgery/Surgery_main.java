package Surgery;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

//import java.awt.event.MouseListener;
import javax.swing.*;

public class Surgery_main extends JFrame implements Runnable {

   private ImageIcon brainicon = new ImageIcon("surgery_images/호두.png");
   private ImageIcon hearticon = new ImageIcon("surgery_images/심장.png");
    private ImageIcon lungicon = new ImageIcon("surgery_images/폐.png");
    private ImageIcon livericon = new ImageIcon("surgery_images/간.png");
    private ImageIcon kidneyicon = new ImageIcon("surgery_images/콩팥.png");
   ImageIcon xicon = new ImageIcon("surgery_images/x.png");
   JButton [] lb = new JButton[7];
   JButton [] rb = new JButton[7];  
   private static final long serialVersionUID = 1L;
   private BodyPanel bodypanel = new BodyPanel(); 
   private RightPanel rightpanel = new RightPanel();
   ImageIcon backicon = new ImageIcon("surgery_images/초록배경.png");
   Image backimg = backicon.getImage();
   private ImageIcon trashicon = new ImageIcon("surgery_images/휴지통.png");
   private Image trashimg = trashicon.getImage();
   static JLabel timerLabel = new JLabel();
   static int end=0;
   static int endcheck=0;
   static int health = 100;
   static String hlthstr = String.valueOf(health);
   int toolCheck = 0; //도구 선택 확인용 0.손 1.메스 2.메스 3.메스 4.수건
   int MesDragCheck; //메스 드래그 확인용(100채우기용)
   int clkcnt=0;
   private MyPanel diepanel = new MyPanel();
   Container c = getContentPane();
   Image MesCursor;
   Image DragMesCursor;
   boolean moves = false;
   Sound Soundrunnable = new Sound();
   Thread Soundth = new Thread(Soundrunnable);
   public void customcursor() {  // 손 모양 마우스
      if(toolCheck==0||toolCheck!=0) {
         Toolkit tk = Toolkit.getDefaultToolkit();
         Image cursorimage = tk.getImage("surgery_images/손커서.png");
         Point point = new Point(10,10);
         Cursor Defaultcursor = tk.createCustomCursor(cursorimage, point, "hand");
         c.setCursor(Defaultcursor);
         System.out.println("손");
         }
   }   
   
   public Surgery_main() {
      
      setTitle("이세계에선 의사");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(null);
      
      bodypanel.setBounds(0,0,790,768);
      c.add(bodypanel);
      
      rightpanel.setBounds(790,0,234,768);
      c.add(rightpanel);
      
      customcursor();
      timer();
      
      setSize(1024,768);
      setVisible(true);
      Timer timer = new Timer();
      TimerTask task = new TimerTask() {
      
      
    	  public void run() {
    		  setContentPane(diepanel);
    		  Container d = getContentPane();
    		  d.setLayout(null);
    		  setVisible(true);
      }

   };
   timer.schedule(task,100000);
 }
   
   class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("image/수술실패.png");
		private Image img = icon.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	}

   class BloodBT extends JButton {
      ImageIcon ii;
      Image image;
      public BloodBT(ImageIcon i) {
         ii = i;
         image = ii.getImage();
      }
      @Override
      protected void paintComponent(Graphics arg0) {
         arg0.drawImage(image,0,0,getWidth(),getHeight(),this);
      }
   }
   
   class BloodListener extends MouseAdapter{

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub
         BloodBT b = (BloodBT)e.getSource();
         if(toolCheck==4) {
            if(e.getComponent()==b) {
               System.out.println("bld0 제거");
               b.setVisible(false);
            }
            else {
               System.out.println("bld1 제거");
               b.setVisible(false);
            }
         }
      }
   }

   

   //패널 만드는 곳

   class BodyPanel extends JPanel {
      private static final long serialVersionUID = 1L;
      private ImageIcon bodyicon = new ImageIcon("surgery_images/바디.png");
      Image bodyimg = bodyicon.getImage();
      JButton drag = new JButton(new ImageIcon("surgery_images/투명버튼.png")); // 몸 투명버튼
      DragListener dl = new DragListener();
      int x = dl.ex;
      int y = dl.ey;
      ImageIcon bloodicon0 = new ImageIcon("surgery_images/피_0.png");
      ImageIcon bloodicon1 = new ImageIcon("surgery_images/피_1.png");
      BloodBT bld0 = new BloodBT(bloodicon0);
      BloodBT bld1 = new BloodBT(bloodicon1);
      
      private ImageIcon leftbackicon = new ImageIcon("surgery_images/왼쪽바닥.png");
      private ImageIcon monitoricon = new ImageIcon("surgery_images/모니터.png");
      private ImageIcon trayicon = new ImageIcon("surgery_images/트레이.png");
      private ImageIcon buttonicon = new ImageIcon("surgery_images/버튼.png");
      
      private Image leftbackimg = leftbackicon.getImage();
      private Image monitorimg = monitoricon.getImage();
      private Image trayimg = trayicon.getImage();
      private Image buttonimg = buttonicon.getImage();
      ImageIcon xicon = new ImageIcon("surgery_images/x.png");
      ImageIcon holebodyicon = new ImageIcon("surgery_images/열린바디.png");
      Image holebodyimg = holebodyicon.getImage();
      ImageIcon ripicon = new ImageIcon("surgery_images/장기종합점선.png");
      Image ripimg = ripicon.getImage();
      
      Image chbuttonimg = buttonimg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
      Image chbuttonimgps = buttonimg.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
      ImageIcon chbuttonicon = new ImageIcon(chbuttonimg);
      ImageIcon chbuttoniconps = new ImageIcon(chbuttonimgps);
      
      JButton brainbt = new JButton(brainicon);
      JButton heartbt = new JButton(hearticon);
      JButton lungbt = new JButton(lungicon);
      JButton liverbt = new JButton(livericon);
      JButton kidneybt = new JButton(kidneyicon);
      
      ImageIcon holebodyicon2 = new ImageIcon("surgery_images/열린바디2.png");
      Image holebodyimg2 = holebodyicon2.getImage();
      ImageIcon delbrainicon = new ImageIcon("surgery_images/상한호두.png");
      ImageIcon delhearticon = new ImageIcon("surgery_images/상한심장.png");
      ImageIcon dellungicon = new ImageIcon("surgery_images/상한폐.png");
      ImageIcon dellivericon = new ImageIcon("surgery_images/상한간.png");
      ImageIcon delkidneyicon = new ImageIcon("surgery_images/상한콩팥.png");
   private int bpi;
      public BodyPanel() {
         setLayout(null);
         
         JLabel healthLabel = new JLabel(hlthstr);
         healthLabel.setFont(new Font("Gothic" ,Font.ITALIC, 50));
         healthLabel.setForeground(Color.white);
         healthLabel.setBounds(50,40,100,100);
         add(healthLabel);
         
         borderBT(brainbt);
         brainbt.setBounds(40,340,160,90);
         brainbt.addMouseMotionListener(new BTMoveListener());
         brainbt.addMouseListener(new BTMoveListener());
         add(brainbt);
         brainbt.setVisible(false);

        borderBT(heartbt);
         heartbt.setBounds(40,340,160,90);
         heartbt.addMouseMotionListener(new BTMoveListener());
         heartbt.addMouseListener(new BTMoveListener());
         add(heartbt);
         heartbt.setVisible(false);

        borderBT(lungbt);
         lungbt.setBounds(40,340,160,90);
         lungbt.addMouseMotionListener(new BTMoveListener());
         lungbt.addMouseListener(new BTMoveListener());
         add(lungbt); 
         lungbt.setVisible(false);

        borderBT(liverbt);
         liverbt.setBounds(40,340,160,90);
         liverbt.addMouseMotionListener(new BTMoveListener());
         liverbt.addMouseListener(new BTMoveListener());
         add(liverbt);
         liverbt.setVisible(false);
         borderBT(kidneybt);
         kidneybt.setBounds(40,340,160,90);
         kidneybt.addMouseMotionListener(new BTMoveListener());
         kidneybt.addMouseListener(new BTMoveListener());
         add(kidneybt);
         kidneybt.setVisible(false);
         
         JButton bt1 = new JButton(chbuttonicon);
         JButton bt2 = new JButton(chbuttonicon);
         JButton bt3 = new JButton(chbuttonicon);
         JButton bt4 = new JButton(chbuttonicon);
         JButton bt5 = new JButton(chbuttonicon);
         
         master(bt1,chbuttoniconps);
         master(bt2,chbuttoniconps);
         master(bt3,chbuttoniconps);
         master(bt4,chbuttoniconps);
         master(bt5,chbuttoniconps);
         
         bt1.setBounds(55,580,50,50);
         bt2.setBounds(125, 580, 50, 50);
         bt3.setBounds(20, 640, 50, 50);
         bt4.setBounds(90, 640, 50, 50);
         bt5.setBounds(160, 640, 50, 50);
         add(bt1); add(bt2);
         add(bt3); add(bt4); add(bt5);         

         drag.setBounds(495, 200, 25 ,171);
         drag.addMouseMotionListener(dl);
         drag.addMouseListener(dl);
         borderBT(drag);
         add(drag);
         bld0.addMouseListener(new BloodListener());
         bld1.addMouseListener(new BloodListener());
         System.out.println(bpi);
         
      }
       void create2p(int bpi) {
         this.bpi = bpi;
         JButton [] lb = new JButton[7];
          JButton [] rb = new JButton[7];
          for(int i=0; i<7; i++){
             lb[i] = new JButton(xicon);
             rb[i] = new JButton(xicon);
          }
         lb[0].setBounds(437, 218, 13, 12); lb[3].setBounds(427,280,10,14);
          lb[1].setBounds(434,237,13,12);   lb[4].setBounds(425,302,10,15);
          lb[2].setBounds(430,263,10,14);   lb[5].setBounds(418,322,10,15); lb[6].setBounds(417,345,10,15);
          
          rb[0].setBounds(562, 220, 13, 13); rb[3].setBounds(580,283,10,18);
          rb[1].setBounds(567,240,13,15);   rb[4].setBounds(580,304,10,18);
          rb[2].setBounds(575,263,10,14);   rb[5].setBounds(583,326,10,16); rb[6].setBounds(585,346,10,15);
          for(int i=0; i<7; i++){
              lb[i].repaint();
              rb[i].repaint();
              lb[i].addActionListener(new HammerListener());
              rb[i].addActionListener(new HammerListener());
              borderBT(lb[i]);
              borderBT(rb[i]);
              add(lb[i]);
              add(rb[i]);
              System.out.println(bpi);
          }
      }
       void create3p(int bpi) {
          this.bpi = bpi;
          JButton delbrainbt = new JButton(delbrainicon);
           JButton dellungbt = new JButton(dellungicon);
           JButton delheartbt = new JButton(delhearticon);
           JButton delliverbt = new JButton(dellivericon);
           JButton delkidneybt = new JButton(delkidneyicon);
           delbrainbt.setBounds(450, 0, 110, 100);
           dellungbt.setBounds(457, 180, 110, 100);
           delheartbt.setBounds(470,240,100,100);
           delliverbt.setBounds(435,260,100,100);
           delkidneybt.setBounds(430,310,110,100);
           borderBT(delbrainbt);
           borderBT(dellungbt);
           borderBT(delheartbt);
           borderBT(delliverbt);
           borderBT(delkidneybt);
           delbrainbt.addMouseMotionListener(new BTMoveListener());
           dellungbt.addMouseMotionListener(new BTMoveListener());
           delheartbt.addMouseMotionListener(new BTMoveListener());
           delliverbt.addMouseMotionListener(new BTMoveListener());
           delkidneybt.addMouseMotionListener(new BTMoveListener());
           
           add(delbrainbt);
           add(dellungbt);
           add(delheartbt);
           add(delliverbt);
           add(delkidneybt);
           
           endcheck = 1;
           System.out.print(endcheck);
           
       }
       
      public void paintComponent(Graphics g) {
           g.drawImage(leftbackimg,0,0,226,768, 57,50,693,956,this);
            g.drawImage(monitorimg,0,0,226,146, 148,57,849,525,this);
            g.drawImage(trayimg, 0, 295, 226, 470, 79, 69, 1143, 916, this);
            g.drawImage(backimg,226,0,790,768, 0,0,1200,1832,this);
            g.drawImage(trashimg, 660, 600, 790, 730, 0, 0, 601, 781, this);
        if(bpi==0) {
            g.drawImage(bodyimg,226,0,790,768, 227,0,790,763,this);
         }
         if(bpi==1) {
           g.drawImage(holebodyimg,226,0,790,768, 227,0,790,763,this);
            g.drawImage(ripimg,400,155,620,420, 10,10,300,300,this);
         }
         if(bpi==2) {
            g.drawImage(holebodyimg2,226,0,790,768, 227,0,790,763,this);
         }
      }
      
      class DragListener extends MouseAdapter implements MouseMotionListener{
         int ex, ey;
         @Override
         public void mouseMoved(MouseEvent e) {}

         @Override
         public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            super.mouseEntered(e);
            if(toolCheck==1) {
               System.out.println("메스 올라옴");
               Toolkit tk = Toolkit.getDefaultToolkit();
               Point point = new Point(10,10);
               DragMesCursor = tk.getImage("surgery_images/메스드래그.png");
               Cursor Dragmes = tk.createCustomCursor(DragMesCursor, point, "drag");
               drag.setCursor(Dragmes);
            }
            else {
               drag.setCursor(null);
            }
         }

         @Override
         public void mouseDragged(MouseEvent e) {
            JButton jb = (JButton)e.getSource();
            if(toolCheck==1) {
               System.out.println("드래그");
               MesDragCheck++;
               System.out.println("MesDragCheck의값="+MesDragCheck);

               if ((e.getY() > drag.getHeight()-5) && (e.getY() < drag.getHeight()) && MesDragCheck > 80) {
                  bpi = 1;
                  remove(jb);
                  create2p(bpi);
                  repaint();
                  if(bpi==1) {System.out.println(bpi+" 나옴"); }
                  }
               
               }
         }

         @Override
         public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if(toolCheck != 1) {
               System.out.println("프레스");
               ex = e.getXOnScreen();
               ey = e.getYOnScreen();
               System.out.println(ex+","+ey);
               if(ex<=515 && ex>=504) {
                  bld0.setBounds(ex-70, ey-25, 50, 50);
                  borderBT(bld0);
                  add(bld0);
                  bld0.setVisible(true);
                  health -= 10;
                  hlthstr = String.valueOf(health);
                  
                  System.out.println("왼쪽에 피 생성");
               }
               else {
                  bld1.setBounds(ex+10, ey-25, 50, 50);
                  borderBT(bld1);
                  add(bld1);
                  bld1.setVisible(true);
                  health -=10;
                  hlthstr = String.valueOf(health);
                  System.out.println("오른쪽에 피 생성");
               }
            }
         }
      }
      class HammerListener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent arg0) {
         JButton jb = (JButton)arg0.getSource();
              if(toolCheck == 2) {
                      clkcnt++;
                      System.out.println(clkcnt);
                      jb.setVisible(false);
                      if(clkcnt==14) {
                         System.out.println("갈비 다 누름");
                         bpi=2;
                         remove(jb);
                         create3p(bpi);
                         repaint();
                      }
                      }
                      else {
                         //피?
                      }

          }
          
       }
   }
   
   public void master(JButton bt, ImageIcon icon) {
       bt.setPressedIcon(icon);
       bt.setBorderPainted(false); // false 세개 뒤의 테두리 없애려고넣은것
       bt.setFocusPainted(false); 
       bt.setContentAreaFilled(false);
       bt.addActionListener(new RedButtonListener());
    }
   class RedButtonListener implements ActionListener {
       @Override
       public void actionPerformed(ActionEvent e) {
          JButton b = (JButton)e.getSource();
          int x = b.getX();
          System.out.print(x);
          
          if(x==55) { 
             b.setEnabled(false);
             System.out.println("\t뇌 꺼냄");
             bodypanel.brainbt.setVisible(true);
          }
          else if(x==125) { 
             System.out.println("\t심장 꺼냄");
             b.setEnabled(false);
             bodypanel.heartbt.setVisible(true);
             }
          else if(x==20) { 
             System.out.println("\t폐 꺼냄");
             b.setEnabled(false);
             bodypanel.lungbt.setVisible(true);
             }
          else if(x==90) { 
             System.out.println("\t간 꺼냄");
             b.setEnabled(false);
             bodypanel.liverbt.setVisible(true);
             }
          else { 
             System.out.println("\t콩팥 꺼냄");
             b.setEnabled(false);
             bodypanel.kidneybt.setVisible(true);
             }
       }
       
    }
   class RightPanel extends JPanel{
      private static final long serialVersionUID = 1L;
      private ImageIcon rightbackicon = new ImageIcon("surgery_images/서랍.png");
      private Image rightbackimg = rightbackicon.getImage();
      private ImageIcon mesicon = new ImageIcon("surgery_images/메스.png");
      private Image mesimg = mesicon.getImage();
      private ImageIcon towelicon = new ImageIcon("surgery_images/수건.png");
      ImageIcon hammericon = new ImageIcon("surgery_images/망치.png");
      Image hammerimg = hammericon.getImage();
      ImageIcon playicon = new ImageIcon("surgery_images/재생.png");
      ImageIcon muteicon = new ImageIcon("surgery_images/음소거.png");
      ImageIcon npcLeeIcon = new ImageIcon("image/doctor.png");
        Image npcLeeImg = npcLeeIcon.getImage();// 이국종 사진
      ToolListener tl = new ToolListener();
      boolean toggle = false;
      public RightPanel(){
         setLayout(null);
         Image chmesimg = mesimg.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
         Image chhammerimg = hammerimg.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
         ImageIcon chhammericon = new ImageIcon(chhammerimg);
         ImageIcon chmesicon = new ImageIcon(chmesimg);
         
         JButton mesbt = new JButton(chmesicon);
         JButton towelbt = new JButton(towelicon);
         JButton hammerbt = new JButton(chhammericon);
         
         JButton playbt = new JButton(playicon);
         playbt.setBounds(80,380,100,100);
         borderBT(playbt);
         add(playbt);
         playbt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            JButton b = (JButton)arg0.getSource();
            toggle =!toggle;
            if(toggle) {
               b.setIcon(muteicon);
               Soundth.interrupt();
            }
            else {
               b.setIcon(playicon);
               Soundth.start();
               
               
            }
         }
            
         });
         mesbt.setBounds(50,20,150,90);
         hammerbt.setBounds(50,140,150,100);
         towelbt.setBounds(50,250,150,90);
         
         borderBT(mesbt);
         borderBT(hammerbt);
         borderBT(towelbt);
         
         mesbt.addActionListener(tl);
         hammerbt.addActionListener(tl);
         towelbt.addActionListener(tl);
         
         add(mesbt);
         add(towelbt);
         add(hammerbt);
      }
      
      public void paintComponent(Graphics g) {
         g.drawImage(rightbackimg,0,0,461,500, 65,11,229,794,this);
         g.drawImage(npcLeeImg,0,500,220,230,this);
      }
      class ToolListener implements ActionListener {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            JButton b = (JButton)e.getSource();
            if(b.getY()==20) {
               if(toolCheck==1) {
                  setCursor(null);
                  toolCheck=0;
                  customcursor();
               }
               else {
                  System.out.println("메스"+toolCheck);
                  toolCheck = 1;
                  Toolkit tk = Toolkit.getDefaultToolkit();
                  MesCursor = tk.getImage("surgery_images/메스커서_수.png");
                  Point point = new Point(10,10);
                  Cursor Cursormes = tk.createCustomCursor(MesCursor, point, "mes");
                  c.setCursor(Cursormes);
               }
               
            }
            else if(b.getY()==140) {
               if(toolCheck==2) {
                  setCursor(null);
                  toolCheck=0;
                  customcursor();
               }
               else {
                  System.out.println("망치"+toolCheck);
                  toolCheck = 2;
                  Toolkit tk = Toolkit.getDefaultToolkit();
                  MesCursor = tk.getImage("surgery_images/망치.png");
                  Point point = new Point(8,8);
                  Cursor Cursormes = tk.createCustomCursor(MesCursor, point, "hammer");
                  c.setCursor(Cursormes);
               }
            }
            else if(b.getY()==250) {
               if(toolCheck==4) {
                  setCursor(null);
                  toolCheck=0;
                  customcursor();
               }
               else {
                  System.out.println("수건"+toolCheck);
                  toolCheck = 4;
                  Toolkit tk = Toolkit.getDefaultToolkit();
                  MesCursor = tk.getImage("surgery_images/수건.png");
                  Point point = new Point(10,10);
                  Cursor Cursormes = tk.createCustomCursor(MesCursor, point, "towel");
                  c.setCursor(Cursormes);
               }
            }
         }
      }
   }

   public void timer() {
      setLayout(null);
      timerLabel.setFont(new Font("Gothic" ,Font.ITALIC, 50));
      timerLabel.setForeground(Color.white);
      TimerRunnable runnable = new TimerRunnable(timerLabel);
      Thread th = new Thread(runnable);
      bodypanel.add(timerLabel);
      
      timerLabel.setBounds(50,-20,100,100);
      th.start();
   }
   public void playBgm() {

      //while(true) {
      Soundth.start();//}
   }
   public static void borderBT(JButton b){
      b.setBorderPainted(false);
      b.setFocusPainted(false);
      b.setContentAreaFilled(false);
   }
   
   public static void main(String [] args) {


   }
   class BTMoveListener extends MouseMotionAdapter implements MouseListener {

      Panel01 pan1 = new Panel01();
      @Override
      public void mouseDragged(MouseEvent e) {
         if(toolCheck==0) {
         JButton b = (JButton)e.getSource();
         moves = true;
         PointerInfo pt = MouseInfo.getPointerInfo();
            pt = MouseInfo.getPointerInfo();

            if(moves) {
               System.out.println("드래그중"+moves);

               b.setLocation(b.getX()+e.getX()-40,b.getY()+e.getY()-25);
               repaint();
            
               if((pt.getLocation().x > 650) ) {
                      if((pt.getLocation().y > 610 )) {
                         b.setVisible(false);
                         System.out.println("버림");
                      }
                }
            }
         }
      }
   @Override
   public void mouseClicked(MouseEvent arg0) {}
   @Override
   public void mouseEntered(MouseEvent arg0) {}
   @Override
   public void mouseExited(MouseEvent arg0) {}
   @Override
   public void mousePressed(MouseEvent arg0) {}
   @Override
   public void mouseReleased(MouseEvent arg0) {
      JButton b = (JButton)arg0.getSource();
      if(endcheck==1)
         if(victory(b)==1) {
            end++;
            System.out.println(end);
            // 새 장기 정해진 위치에 놓이면 소리나 표시나게 해야할듯
            if(end==5) {
                   JOptionPane.showMessageDialog(c,"수술 완료! \n이름: "+pan1.getName()+"\n기록: "+ timerLabel.getText()+"초"
                         ,"End",JOptionPane.INFORMATION_MESSAGE);
                   System.out.println("다이얼로그 나옴");
                   System.exit(0);
                   //dispose();
                }
         }
   }
   
   }
   
   //스레드용
   @Override
   public void run() {
      // TODO Auto-generated method stub
      //new Surgery_main();
      playBgm();

   }

   int victory(JButton b) {  // 새 장기 위치 검사
      if(b.getIcon()==brainicon)    // brainbt
         if( b.getX() > 420 && b.getX() <= 460 ) 
            if( b.getY() > 0 && b.getY() <= 25) {
               System.out.println("뇌 딱대");
               return 1;
   }
      if(b.getIcon()==hearticon)   // heartbt
         if(b.getX() > 460 && b.getX() <= 490)
            if(b.getY() > 220 && b.getY() <= 280) {
               System.out.println("심장 딱대");
               return 1;
            }
      if(b.getIcon()==lungicon)    // lungbt
         if( b.getX() > 418 && b.getX() <= 455 ) 
            if( b.getY() > 188 && b.getY() <= 240) {
               System.out.println("폐 딱대");
               return 1;
   }
      if(b.getIcon()==livericon)   // liverbt
         if(b.getX() > 400 && b.getX() <= 435)
            if(b.getY() > 275 && b.getY() <= 315) {
               System.out.println("간 딱대");
               return 1;
            }
      if(b.getIcon()==kidneyicon)   //kidneybt
         if(b.getX() > 398 && b.getX() <= 427)
            if(b.getY() > 320 && b.getY() <= 340) {
               System.out.println("콩팥 딱대");
               return 1;
            }

      return 0;
   }
}