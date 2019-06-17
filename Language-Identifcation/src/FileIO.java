import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileIO {
  Scanner in = new Scanner(System.in);
  File directory = new File("Learning\\");
  File directoryUn = new File("Testing\\");
  File directoryMod = new File("Models\\");
  File[] modList = directoryMod.listFiles();
  File[] dirList = directoryUn.listFiles();


  public FileIO() {

  }


  // Get models from file
  public static ArrayList<String> getModels(String file) {
    ArrayList<String> modelBigram = new ArrayList<>();
    StringBuilder fileContent = new StringBuilder();

    try {
      Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String s;
      while ((s = bufferedReader.readLine()) != null) {
        fileContent.append(modelBigram.add(s));
      }
      bufferedReader.close();
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return modelBigram;
  }


  // Read from file
  public static String readUTF8File(String filePath) {
    StringBuilder fileContent = new StringBuilder();
    try {
      Reader reader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String s;
      while ((s = bufferedReader.readLine()) != null) {
        fileContent.append(s + "\n");
      }
      bufferedReader.close();
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fileContent.toString();
  }


  // Write to file
  public static void outputWordsToUTF8File(String filePath, ArrayList wordList) {
    try {
      Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
      PrintWriter printWriter = new PrintWriter(writer);
      Collections.sort(wordList);
      for(Object word : wordList)
      {
        printWriter.println(word);
      }
      writer.close();
      printWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

