import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random; //무작위의 수를 선택하기 위해 random import

public class OddEvenCheck extends JFrame{
   //필드
   private String user;	//check의 매개변수로 주기 위해 Myaction이 아닌 OddEvenCheck로 위치 변경
   //생성자
   public OddEvenCheck() {
      super("홀짝 게임");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(300,250);
      setContentPane(new MyPanel());   //패널 부착
      setVisible(true);
   }
   //메소드
   
   //패널 클래스 정의
   class MyPanel extends JPanel{
      //필드
      private JLabel label = new JLabel("?");
      private JLabel message = new JLabel("무엇일까요?");
      private JButton btns []= {
            new JButton("홀"),
            new JButton("짝"),
            new JButton("확인"),
            new JButton("다시")
      };
      
      //생성자
      private MyPanel() {
         setLayout(null);
         
         label.setFont(new Font("고딕", Font.PLAIN, 30));
         label.setSize(70, 70);
         label.setBackground(Color.magenta);
         label.setHorizontalAlignment(JLabel.CENTER);
         label.setOpaque(true);
         label.setLocation(100,30);
         add(label);
         
         message.setSize(120, 20);
         message.setLocation(100,110);
         add(message);
         
         for(int i=0;i<btns.length;i++) {
            JButton b=btns[i];
            b.setSize(50,30);
            b.setLocation(10+i*70, 160);
            //이벤트 부착
            b.addActionListener(new MyAction());
            add(b);
         }
      }
      //메소드
   }
   
   //이벤트 리스너
   class MyAction implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();
         switch(b.getText()) {
         case "홀": 
        	user = "홀";
            break;
         case "짝": 
        	user = "짝";
            break;
         case "확인":
            check(user);   //user 매개변수로 줘서 판단
            break;
         case "다시":   
        	user = "";
            restart();
            break;
         }
      }
   }
   void check(String user) {
	   if(user == null || user.isEmpty()) {
		   ((MyPanel) getContentPane()).message.setText("홀이나 짝 먼저 선택!");   
		   return;
	   }
	   String randNum = Integer.toString((int)(Math.random() * 9) + 1);	//setText로 답 공개를 위해 String 선언
	   int result=Integer.parseInt(randNum);	//다시 정수로 변환하여 if문 조건에 사용
	   
	   if(result % 2 == 0 && user.equals("짝")) {
		   ((MyPanel) getContentPane()).message.setText("짝! 맞았어요.");
	   }
	   else if(result % 2 == 1 && user.equals("홀")) {
		   ((MyPanel) getContentPane()).message.setText("홀! 맞았어요.");
	   }
	   else if(result % 2 == 0 && user.equals("홀"))
		   ((MyPanel) getContentPane()).message.setText("짝! 아쉽군요");
	   else if(result % 2 == 1 && user.equals("짝"))
		   ((MyPanel) getContentPane()).message.setText("홀! 아쉽군요");
	   else
		   return;
	   ((MyPanel) getContentPane()).label.setText(randNum);	//답 공개
	}

   void restart() {
	   //다시 원래대로 변경
	   ((MyPanel) getContentPane()).label.setText("?");
	   ((MyPanel) getContentPane()).message.setText("무엇일까요?");
	}
   
   public static void main(String[] args) {
      new OddEvenCheck();
}

}
