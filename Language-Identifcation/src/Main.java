import java.util.*;

public class Main {


  public static void main(String[] args) {
    Bigrams bigram = new Bigrams();
    Scanner in = new Scanner(System.in);

    bigram.run();

    System.out.println("Enter dir of unkown file : ");
    String dir = in.nextLine();

    // Initializing Class after text files have been created
    LanguageLearn learn = new LanguageLearn(dir);

    learn.run();







  }



}
