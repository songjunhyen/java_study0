package com.sc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
  private static int article_count = 1;
  private static List<Article> articles = new ArrayList<>(); // articles 리스트 생성
  private static List<Member> members = new ArrayList<>(); // members 리스트 생성

  private static Member loggedInMember = null;

  public static void main(String[] args) {
    System.out.println("==프로그램 시작==");
    Scanner sc = new Scanner(System.in);

    System.out.print("1.로그인 \n2.회원가입 \n3.종료 \n번호를 입력하세요 ");
    String st = sc.nextLine().trim();
    if (st.equals("1")) {
      login(sc);
    }
    else if(st.equals("2")){
      signUpMember(sc);
    }
    else if(st.equals("3")){
      System.exit(0);
    }
    else {
      System.out.print("잘못입력하셨습니다.");
      return;
    }


    while (true) {
      System.out.print("명령어 ) ");
      System.out.print("사용가능 명령어[article write, view article index, find article for keyword, view article for number, delete article,change article]\n");
      String cmd = sc.nextLine().trim();
      System.out.printf("입력한 명령어 : %s\n\n", cmd);
      System.out.println("종료하시려면 system exit를 입력하세요");

      if (cmd.equals("article write")) {
        writeArticle(sc);
      }
      else if (cmd.equals("view article index")) {
        viewArticle(articles);
      }
      else if (cmd.equals("view article for number")){
        viewArticlebody(sc, articles); // viewArticlebody 메서드 호출 시 articles 리스트 전달;
      }
      else if (cmd.equals("find article for keyword")){
        viewArticlebody2(sc, articles); // viewArticlebody 메서드 호출 시 articles 리스트 전달;
      }
      else if (cmd.equals("delete article")){
        deleteArticle(sc, articles); // viewArticlebody 메서드 호출 시 articles 리스트 전달;
      }
      else if (cmd.equals("change article")){
        changeArticle(sc, articles); // viewArticlebody 메서드 호출 시 articles 리스트 전달;
      }
      else if (cmd.equals("system exit")) {
        break;
      } else {
        System.out.print("존재하지 않는 명령어 입니다.\n\n");
      }
    }

    sc.close();
  }

  private static void login(Scanner sc) {
    while (true) {
      System.out.print("사용자 이름을 입력하세요: ");
      String username = sc.nextLine();
      System.out.print("비밀번호를 입력하세요: ");
      String password = sc.nextLine();

      // 사용자 이름과 비밀번호가 일치하는 회원 찾기
      for (Member member : members) {
        if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
          loggedInMember = member;
          System.out.println("로그인 되었습니다.");
          return;
        }
      }
      System.out.println("일치하는 회원 정보가 없습니다.");
      System.out.println("회원가입하시려면 signup을 입력하세요: ");
      System.out.println("종료하시려면 end를 입력해주세요");
      String option = sc.nextLine().trim();
      if (option.equals("signup")) {
        signUpMember(sc);
        return;
      }
      else if(option.equals("end")){
        System.exit(0);
      }
      else {
        System.out.println("다시 로그인 화면으로 넘어가겠습니다.");
        break; // 다시 로그인 시도
      }

    }
  }

  private static void signUpMember(Scanner sc){
    System.out.print("사용자 이름을 입력하세요: ");
    String username = sc.nextLine();

    // 중복된 아이디인지 확인
    if (Member.isDuplicateUsername(username, members)) { // members중 username이 일치하는경우 true를 내보내는 Member의isDuplicateUsername메서드를 호출
      System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력하세요."); //true니깐 출력
      signUpMember(sc); // 회원가입 메서드의 처음부분 호출(가입할 사용자이름입력받는)
      return; //위에 다시 시작한 회원가입 메서드가 true면 리턴으로 도달하여 다시 중복아이디 확인으로 돌아가고 false면 다음코드로 넘어감
      // isDuplicateUsername 메서드(중복확인)에서 재귀 호출을 한 후에는 재귀 호출을 종료할 조건(false)에 도달하면 if문에 true를 반환하여 재귀 호출을 중단하고, 그렇지 않으면 다시 재귀 호출을 하게 됩니다.
      // 여기서 return 문이 사용되는 이유는, 중복된 아이디가 발견되면 회원가입 메서드를 더 이상 실행할 필요가 없기 때문입니다. 따라서 중복된 아이디가 발견되면 회원가입 메서드가 종료되어야 하므로 return 문을 사용하여 메서드 실행을 즉시 종료합니다.
      // 즉, return 문은 중복된 아이디를 찾았을 때 회원가입 메서드를 즉시 종료시키는 역할을 합니다.

      // 1. isDuplicateUsername 메서드에서 재귀 호출은 보통 종료 조건을 만족하지 않을 때 발생합니다.
      // 2. 종료 조건에 도달하지 않으면서 중복된 아이디를 찾지 못한 경우에는 다시 재귀 호출을 하여 더 많은 회원을 확인하게 됩니다.
      // 3. 종료 조건이 false에 도달하여 재귀 호출을 중단하게 되면 이후에는 해당 호출 스택에서부터 거슬러 올라가면서 처리를 계속 진행하게 됩니다.

    }

    System.out.print("비밀번호를 입력하세요: ");
    String password = sc.nextLine();

    Member member = new Member(username, password, LocalDateTime.now());
    members.add(member);

    System.out.println("회원 가입이 완료되었습니다.");
  }

  private static void writeArticle(Scanner sc) {
    System.out.print("제목을 입력하세요 ");
    String article_name = sc.nextLine();
    System.out.printf("제목 : %s\n", article_name);
    System.out.print("내용을 입력하세요 ");
    String article_text = sc.nextLine();

    Article article = new Article(article_count, article_name, article_text, 0);
    System.out.printf("제목 : %s\n내용 : %s\n", article.getTitle(), article.getBody());

    System.out.printf("%d 번 글이 생성되었습니다.\n", article_count);
    article_count += 1;
  }

  private static void viewArticlebody(Scanner sc, List<Article> articles) { // articles 리스트를 매개변수로 받음
    if (articles.isEmpty()) {
      System.out.println("게시글이 없습니다.");
    }
    else {
      System.out.print("확인할 게시글의 번호를 입력하세요 ");
      String article_id = sc.nextLine();
      int idCheck = Integer.parseInt(article_id);

      boolean found = false; // 게시글을 찾았는지 여부를 나타내는 플래그

      for (Article article : articles) {
        if (idCheck == article.getId()) {
          System.out.printf("%d\t%s\t%s\n%s\n", article.getId(), article.getTitle(), article.getBody(), article.getCreatedAt());
          found = true; // 게시글을 찾았으므로 플래그를 true로 설정
          break; // 게시글을 찾았으므로 반복문을 종료
        }
      }
      if (found == false) {
        System.out.println("해당 번호의 게시글을 찾을 수 없습니다.\n");
      }
    }
  }

  private static void viewArticlebody2(Scanner sc, List<Article> articles) { // articles 리스트를 매개변수로 받음
    if (articles.isEmpty()) {
      System.out.println("게시글이 없습니다.");
    }
    else {
      System.out.print("확인할 게시글의 제목에 관련된 단어를 입력하세요 ");
      String article_keyword = sc.nextLine();

      boolean found = false; // 게시글을 찾았는지 여부를 나타내는 플래그

      for (Article article : articles) {
        if (article.getTitle().contains(article_keyword)) {
          System.out.println("해당 게시글의 목록을 호출합니다.");
          System.out.printf("%d\t%s\t%s\n", article.getId(), article.getTitle(), article.getCreatedAt());
          viewArticlebody(sc, articles); //번호로 내용 호출하는 메서드 불러냄
          found = true; // 게시글을 찾았으므로 플래그를 true로 설정
          break; // 게시글을 찾았으므로 반복문을 종료
        }
      }
      if (found == false) {
        System.out.println("해당 번호의 게시글을 찾을 수 없습니다.\n");
      }
    }
  }

  private static void viewArticle(List<Article> articles) {
    if (articles.isEmpty()) {
      System.out.println("게시글이 없습니다.");
    } else {
      System.out.println("번호\t제목\t작성시간");
      for (Article article : articles) {
        System.out.printf("%d\t%s\t%d\t%s\n", article.getId(), article.getTitle(), article.viewCount(),article.getCreatedAt());
        // \t는 각 데이터 사이에 탭을 넣어주는 역할
        // 프로그래밍에서 탭 문자는 보통 여러 개의 공백 문자와 동일한 역할을 하며, 텍스트를 보기 좋게 정렬할 때 사용됩니다. 위의 코드에서는 번호, 제목, 내용을 출력할 때 각 항목 사이에 탭 문자를 사용하여 간격을 조절
      }
    }
  }
  private static void deleteArticle(Scanner sc, List<Article> articles){
    if (articles.isEmpty()) {
      System.out.println("게시글이 없습니다.");
    }
    else {
      System.out.println("삭제할 게시글의 번호를 입력하세요 ");
      String article_id = sc.nextLine();
      int idCheck = Integer.parseInt(article_id);

      boolean found = false; // 게시글을 찾았는지 여부를 나타내는 플래그

      for (Article article : articles) {
        if (idCheck == article.getId()) {
          articles.remove(article); // 리스트에서 해당 게시글을 제거
          System.out.println("게시글이 삭제되었습니다.");
          found = true; // 게시글을 찾았으므로 플래그를 true로 설정
          break; // 게시글을 찾았으므로 반복문을 종료
        }
      }
      if (found == false) {
        System.out.println("해당 번호의 게시글을 찾을 수 없습니다.\n");
      }
    }
  }
  private static void changeArticle(Scanner sc, List<Article> articles){
    if (articles.isEmpty()) {
      System.out.println("게시글이 없습니다.");
    }
    else {
      System.out.println("수정할 게시글의 번호를 입력하세요 ");
      String article_id = sc.nextLine();
      int idCheck = Integer.parseInt(article_id);

      boolean found = false;

      for (Article article : articles) {
        if (idCheck == article.getId()){
          System.out.println("새로운 내용을 입력해주세요.");
          String newbody = sc.nextLine();

          article.setBody(newbody); // 해당 게시글의 내용을 변경

          System.out.println("게시글이 수정되었습니다.");
          found = true;
          break;
        }
      }
      if(found==false){
        System.out.println("해당 번호의 게시글을 찾을 수 없습니다.\n");
      }
    }

  }

}
class Member{
  private String username;
  private String password;
  private LocalDateTime created;

  public Member(String username, String password, LocalDateTime created){
    this.username = username;
    this.password = password;
    this.created = LocalDateTime.now();
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  // 회원 아이디 중복 확인 메서드
  public static boolean isDuplicateUsername(String username, List<Member> members) {
    for (Member member : members) {
      if (member.getUsername().equals(username)) {
        return true; // 중복된 아이디가 있으면 true 반환
      }
    }
    return false; // 중복된 아이디가 없으면 false 반환
  }

}


class Article {
  private int id;
  private String title;
  private String body;
  private LocalDateTime createdAt; // 작성 시간 추가
  private int viewcount;

  public Article(int id, String title, String body, int viewcount) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.createdAt = LocalDateTime.now(); // 현재 시간 저장
    this.viewcount = viewcount;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    viewCount(); // 게시글 본문을 확인할 때마다 조회수 증가
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public int viewCount(){
    this.viewcount++; // viewcount 변수를 1 증가시킴
    return viewcount; // 증가된 viewcount 값을 반환
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

}

//package com.sc;: 이 부분은 해당 소스 코드의 패키지 선언입니다. 패키지는 클래스를 논리적으로 그룹화하는 데 사용됩니다. 여기서 com.sc는 패키지의 이름이며, 이 코드는 com.sc 패키지에 속한 클래스임을 나타냅니다.
//import java.util.Scanner;: Scanner 클래스를 사용하기 위해 java.util 패키지를 import 합니다.
//public class main{: main 이라는 이름의 클래스를 선언합니다. Java에서 클래스 이름의 첫 글자는 대문자로 작성하는 것이 관례입니다. 그러나 파일 이름과 클래스 이름이 일치하지 않아도 되지만, 일반적으로 클래스 이름과 파일 이름을 동일하게 하는 것이 좋습니다.
//public static void main(String[] args){: main 메소드를 정의합니다. Java 프로그램은 항상 main 메소드에서 시작하게 됩니다. main 메소드는 프로그램이 실행될 때 처음 호출되는 메소드입니다.
//Scanner sc = new Scanner(System.in);: Scanner 객체를 생성하여 키보드 입력을 읽어들일 준비를 합니다.
//String cmd = sc.nextLine();: 사용자가 입력한 문자열을 읽어들여 cmd 변수에 저장합니다.(nextline이란게 스캐너클래스에 포함된 메서드)(cmd는 변수명)
//sc.close();: Scanner 객체를 닫아서 리소스를 해제합니다. 프로그램이 종료되기 전에 사용된 Scanner 객체를 닫는 것은 좋은 습관입니다.

//equals는 Java에서 객체의 내용이 동일한지를 비교하는 메서드입니다. 보통 문자열(String) 객체의 경우 두 문자열의 내용이 동일한지를 비교할 때 사용됩니다.
//.trim() 은 문자열의 양 끝에 있는 공백을 제거하는 메서드입니다. 이 메서드를 사용하면 문자열에서 공백 문자(스페이스, 탭, 개행 등)이 제거됩니다.
//String input = sc.nextLine().replace("ㅇ", ""); replace는 첫번쨰 입력받은걸 두번째 입력받은걸로 대체하는 메서드

//추가할 내용