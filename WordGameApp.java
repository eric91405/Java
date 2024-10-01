import java.util.Scanner;   //ctrl + shift + o

public class WordGameApp {//클래스=필드+생성자+메소드
	//객체생성
   private Scanner sc;
   private String startWord="아버지";
   private Player[] players;
   
   //생성자
   WordGameApp(){
	   sc=new Scanner(System.in);
   }
   
   private void createPlayers() {
	   System.out.print("게임에 참가하는 인원? >>");
	   int n=sc.nextInt();	//인원수 입력 받기
	   players=new Player[n];	//입력받은 인원 수 
	   
	   //참가자의 이름 입력 받기 for문으로 
	   for(int i = 0; i < n; i++) {  // 참가자 이름 입력 받기
		   System.out.print("참가자의 이름을 입력하세요 >> ");
		   String name = sc.next();  // 이름 입력 받기
		   players[i] = new Player(name);  // 각 Player 객체 생성
	   }
	   
   }
   
   void run() {
      System.out.println("끝말잇기 게임을 시작합니다......");
      createPlayers();
      String lastWord=startWord;
      System.out.println("시작 단어는 "+lastWord+"입니다.");
      int next=0;
      
      while(true) {
    	  Player currentPlayer = players[next];  // 현재 차례의 플레이어
    	  String newWord = currentPlayer.getWordFromUser();  // 플레이어가 단어 입력
    	  
    	  if (!currentPlayer.checkSuccess(lastWord)) {  // 단어 체크 실패하면
    		  System.out.println(currentPlayer.getName() + "가 졌습니다.");
    		  break;  // 게임 종료
    	  }
    	  
    	  // 성공했으면 다음 사람 차례로
    	  lastWord = newWord;
    	  next = (next + 1) % players.length;  // 다음 차례 인덱스, 순환하도록 % 사용
      }
   }
   
   public static void main(String[] args) {   
      WordGameApp wordgame=new WordGameApp(); //객체 생성
      wordgame.run();
   }

}

class Player{ //클래스=필드+생성자+메소드
   //필드:변수
   private Scanner sc;
   private String name;
   private String word;

   //생성자 메소드: 초기화
   //반드시 이름이 필요하기 때문에 기본 생성자는 생성x
   Player(String name){   //매개변수 name
      this.name=name;   //왼쪽의 name은 필드의 name
      sc = new Scanner(System.in);
   }
   //get 기능 메소드
   public String getName() {
	   return name;
   }
   
   //이름 가져오기
   public String getWordFromUser() {
	   System.out.print(name+ ">>" );
	   word=sc.next();
	   return word;
   }
   
   //끝말과 앞말이 동일한지 체크
   public boolean checkSuccess(String lastWord) {
	   int lastIndex=lastWord.length()-1;
	   
	   if(lastWord.charAt(lastIndex)==word.charAt(0))
		   return true;
	   else
		   return false;
   }
}