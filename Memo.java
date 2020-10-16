package hello;

// swing은 자바 영역에서 사용하는 look&feel 을 적용
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;    //ACtionListener 클래스 사용
import java.awt.event.ActionEvent;    //ActionEvent 클래스 사용
import java.io.*;   //저장에 필요한 임포트
//awt. 해당 운영 체제의 특징을 따라 화면 구성
//BorderLayout 기본틀
import java.awt.*;   //저장에 필요한 임포트
import javax.swing.JColorChooser;


public class testmemo extends JFrame {

   private JPanel contentPane;
   private JTextArea textArea;
   
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               testmemo frame = new testmemo();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public testmemo() {
      setTitle("test메모장");
      JFrame f = new JFrame();
      Image img=getToolkit().getImage("C:\\Users\\박건웅\\Desktop\\memoimage.png");
      //툴킷을 반환하는 메소드 : getToolkit() 
      setIconImage(img);//윈도우 아이콘 변경
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x 버튼 시 종료
      setBounds(100, 100, 450, 300);   //JFrame 크기 
      
      //메뉴 및 메뉴 아이템 생성
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      
      JMenu mnNewMenu = new JMenu("파일");
      menuBar.add(mnNewMenu);
      
      //파일 목록 생성
      JMenuItem mntmNewMenuItem = new JMenuItem("새로 만들기");
      mntmNewMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 int result = JOptionPane.showConfirmDialog(null, 
						"새로 만드시겠습니까?", "Confirm", 
						JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.CLOSED_OPTION)
			textArea.setText("Just Closed without Selection");
		else if(result == JOptionPane.YES_OPTION)
			textArea.setText("");
			setTitle("test메모장");
        	  //textArea.setText("");
         }
      });
      mnNewMenu.add(mntmNewMenuItem);
      
      JMenuItem mntmNewMenuItem_1 = new JMenuItem("열기");
      mntmNewMenuItem_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             FileDialog dialog = new FileDialog(f, "열기", FileDialog.LOAD);
      ////FileDialog 파일을 읽어오거나 저장할때에 쓰인다., f: 대화상자 소속될 프레임 , "열기": 대화상자 상단 제목, FileDialog.LOAD: 사용목적 
             	dialog.setVisible(true);        //dialog 보이기 (대화상자 보이기) 
                 
                 String path = dialog.getDirectory() + dialog.getFile();
                 // // getDirectory( )는 저장한 파일의 상위경로를, getFile( )은 저장한 파일의 이름
                 String s =""; // 문자열(String) 변수 s 
                setTitle( dialog.getFile()); //제목을 파일이름으로 
                 if( dialog.getFile() == null ) return; //선택 않았을 시 중단
                 
                 try {
                    FileReader r = new FileReader( path );  //FileReader 클래스를 생성
                    int k;              
                    
                    for( ;  ; ) {    //반복문을 통해서 문서의 내용을 읽어옴 (; ;): true로서 무한으로 실행
                        k = r.read();
                        if( k == -1) break; // 더 이상 읽을 문자가 없다고 나왔을때 반복문을 중단시키는 역할
                        s += (char)k;       //k가 -1 이 아니라면 k의 값을 문자로 바꾸어서 출력 ,FileReadr 로 읽은 k 를 문자(char)로 변환
                    }           
                    r.close();
             
                 } catch (Exception e2) { //캐치문의 매개변수 이름을 e2로 한것은 ActionEvent e 매개변수와 이름이 겹쳤기 때문
                     System.out.println("오류"+e);
                 } 
                 textArea.setText(s);   //읽어온 문자열을 텍스트 에어리어에 넣어주어야 메모장 프로그램처럼 됨. setText 메서드 이용
         }
      });
      mnNewMenu.add(mntmNewMenuItem_1);
      
      JMenuItem mntmNewMenuItem_2 = new JMenuItem("저장");
      mntmNewMenuItem_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            FileDialog dialog = new FileDialog(f, "저장", FileDialog.SAVE); 
      //FileDialog 파일을 읽어오거나 저장할때에 쓰인다., f: 대화상자 소속될 프레임 , "저장": 대화상자 상단 제목, FileDialog.SAVE: 사용목적 
                dialog.setVisible(true);    //dialog 보이기 (대화상자 보이기) 
                String path = dialog.getDirectory() + dialog.getFile();
                // getDirectory( )는 저장한 파일의 상위경로를, getFile( )은 저장한 파일의 이름
                
                try { //오류가 발생할것 같은 코드를 여기에 적는다
                   
                   FileWriter w = new FileWriter( path ); //객체를 생성해줄때 파일의 경로를 입력
                   String s = textArea.getText();    //getText() 함수는 텍스트에어리어에 입력된 텍스트를 반환
                   s = s.replace("\n","\r\n"); //개행문자 버그 
                   //replace 메서드는 String 클래스의 메서드로, 첫번째 매개변수의 문자열을 두번째 매개변수의 문자열로 바꾸어주는 기능
                   w.write( s  ); 
                   w.close();
            
                } catch (Exception e2) { //캐치문의 매개변수 이름을 e2로 한것은 ActionEvent e 매개변수와 이름이 겹쳤기 때문
                
                    System.out.println("저장오류"+e2);
                } 
         }
      });
      mnNewMenu.add(mntmNewMenuItem_2);
      
      JMenuItem mntmNewMenuItem_3 = new JMenuItem("인쇄");
      mntmNewMenuItem_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
              try{
                   textArea.print();
                  }catch(Exception e1) {}
         }
      });
      mnNewMenu.add(mntmNewMenuItem_3);
      
      JMenuItem mntmNewMenuItem_4 = new JMenuItem("종료");
      mntmNewMenuItem_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         System.exit(0);    
         }
      });
      mnNewMenu.add(mntmNewMenuItem_4);
      
      JMenu mnNewMenu_1 = new JMenu("편집");
      menuBar.add(mnNewMenu_1);
      
      JMenuItem mntmNewMenuItem_5 = new JMenuItem("복사");
      mntmNewMenuItem_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            textArea.copy();    //복사하는 기능
         }
      });
      mnNewMenu_1.add(mntmNewMenuItem_5);
      
      JMenuItem mntmNewMenuItem_6 = new JMenuItem("붙여넣기");
      mntmNewMenuItem_6.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            textArea.paste();    //붙여넣는 기능
         }
      });
      mnNewMenu_1.add(mntmNewMenuItem_6);
      
      JMenuItem mntmNewMenuItem_7 = new JMenuItem("자르기");
      mntmNewMenuItem_7.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            textArea.cut();    //자르는 기능
         }
      });
      mnNewMenu_1.add(mntmNewMenuItem_7);
      
      JMenu mnNewMenu_3 = new JMenu("서식");
      menuBar.add(mnNewMenu_3);
      
      JMenuItem mntmNewMenuItem_9 = new JMenuItem("글자 색 변경");
      mntmNewMenuItem_9.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		Container contentPane; //자바에서 컨테이너는 창(window) 역할을 한다.
            JColorChooser chooser = new JColorChooser(); //컬러 다이얼로그 생성
            String cmd = e.getActionCommand();
          if (cmd.equals("글자 색 변경")) { // 글꼴 메뉴 아이템을 누르면
             Color selectedColor = JColorChooser.showDialog(null, "Color", Color.YELLOW); // 컬러 다이얼로그를 출력하고 사용자가 선택한 색을 알아옴.
             if (selectedColor != null)
              textArea.setForeground(selectedColor); 
          }
      	}
      });
      mnNewMenu_3.add(mntmNewMenuItem_9);
      
      JMenuItem mntmNewMenuItem_8 = new JMenuItem("글꼴 변경");
      mntmNewMenuItem_8.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		 String[] selections = {"돋움", "궁서", "바탕"};
             Object selected=JOptionPane.showInputDialog(null, "글꼴 ", "글꼴변경", JOptionPane.QUESTION_MESSAGE, null, selections, selections[0]);
             String input = JOptionPane.showInputDialog("글자 사이즈 입력.");   
             int tmp = Integer.parseInt(input);
                if(selected == null)
                   JOptionPane.showMessageDialog(null, "취소합니다");
                else if(selected=="돋움") {
                   Font f1= new Font("돋움", Font.PLAIN,tmp);
                textArea.setFont(f1);
                }
                else if(selected=="궁서") {
                   Font f1= new Font("궁서", Font.BOLD,tmp);
                textArea.setFont(f1);
                }
                
                else {
                   Font f1= new Font("바탕", Font.ITALIC,tmp);
                   textArea.setFont(f1);
                }

      	}
      });
      mnNewMenu_3.add(mntmNewMenuItem_8);
      
      JMenu mnNewMenu_2 = new JMenu("도움말");
      menuBar.add(mnNewMenu_2);
      
      JMenuItem mntmNewMenuItem_10 = new JMenuItem("메모장 정보");
      mntmNewMenuItem_10.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	  String[] mesg = {
                  "메모장 ",
                  "개발자 : 박건웅 "
            };
            JOptionPane.showMessageDialog(
                  testmemo.this,
                  mesg,
                  "메모장 정보",
                  JOptionPane.INFORMATION_MESSAGE);  

      	}
      });
      mnNewMenu_2.add(mntmNewMenuItem_10);
      
      
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JScrollPane scrollPane = new JScrollPane();
      contentPane.add(scrollPane, BorderLayout.CENTER);
      
      textArea = new JTextArea();
      scrollPane.setViewportView(textArea);
   }
   
}
