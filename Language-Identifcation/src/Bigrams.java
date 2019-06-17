import java.util.*;
import java.io.*;
import java.text.*;

public class Bigrams {
  ArrayList<String> words = new ArrayList<>();
  ArrayList<String> bigrams = new ArrayList<>();
  ArrayList<String> countBigrams = new ArrayList<>();
  Locale[] localeLanguages = new Locale[] {Locale.ENGLISH, Locale.FRENCH, Locale.GERMAN, Locale.ITALIAN, new Locale("es")};
  Locale unknown = new Locale("Unknown");
  FileIO fileio = new FileIO();


  public Bigrams() {

  }


  public void run() {
    File[] directList = fileio.directory.listFiles();
    if (directList != null){
      for(int i = 0; i < directList.length; i++) {
        String fileInput = fileio.readUTF8File(directList[i].getPath());
        words = extractWords(fileInput, localeLanguages[i]);
        bigrams = getBigrams(words);
        Collections.sort(bigrams);
        countBigrams = countAlpha(bigrams);
        String outFileName = directList[i].getName().replace(".txt", "");
        fileio.outputWordsToUTF8File("Models\\" + outFileName + "Model.txt", countBigrams);
      }
    } else{
      System.out.println("Directory Error");
    }
  }


  public static ArrayList<String> getBigrams(ArrayList<String> words){
    ArrayList<String> bigrams = new ArrayList<>();
    for(String objects : words) {
      for(int i = 0; i < objects.length()-1; i++) {
        bigrams.add(objects.substring(i, i + 2));
      }
    } return bigrams;
  }

  public static ArrayList<String> countAlpha(ArrayList<String> letters) {
    ArrayList<String> outputResult = new ArrayList<>();
    int counter = 0;
    String current = letters.get(0);
    double total = letters.size();


    for(Object next : letters) {
      if(next.equals(current)) {
        counter++;
      }
      else {
        outputResult.add(current + " " + (counter/total));
        counter = 1;
        current = next.toString();
      }
    }  outputResult.add(current + " " + (counter/total));
    return outputResult;
  }


  public static ArrayList extractWords(String inputText, Locale currentLocale) {
    ArrayList wordList = new ArrayList();
    BreakIterator wordIterator = BreakIterator.getWordInstance(currentLocale);
    wordIterator.setText(inputText);
    int start = wordIterator.first();
    int end = wordIterator.next();


    while (end != BreakIterator.DONE) {
      String word = inputText.substring(start, end);
      word = word.toLowerCase();
      if (Character.isLetter(word.charAt(0)) && word.length() > 1) {
        wordList.add(word);
      }
      start = end;
      end = wordIterator.next();
    }
    return wordList;
  }

}
