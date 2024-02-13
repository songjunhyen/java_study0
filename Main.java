package com.sc;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("==프로그램 시작==");
    Scanner sc = new Scanner(System.in);

    int article_count = 1;

    while (true) {
      System.out.print("명령어 ) ");
      String cmd = sc.nextLine();
      System.out.printf("입력한 명령어 : %s\n\n", cmd);
      System.out.println("종료하시려면 system exit를 입력하세요");


      if (cmd.equals("article write")) {
        System.out.print("제목을 입력하세요 ");
        String article_name = sc.nextLine();
        System.out.printf("제목 : %s\n", article_name);
        System.out.print("내용을 입력하세요 ");
        String article_text = sc.nextLine();
        System.out.printf("내용 : %s\n\n", article_text);
        System.out.printf("제목 : %s\n내용 : %s\n", article_name, article_text);

        System.out.printf("%d 번 글이 생성되었습니다.\n", article_count);
        article_count += 1;
      }
      
      else if (cmd.equals("system exit")) {
          break;
      }
      else {
          System.out.print("존재하지 않는 명령어 입니다.\n\n");
      }
    }

    sc.close();

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