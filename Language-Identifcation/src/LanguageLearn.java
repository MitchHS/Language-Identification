import java.util.*;

public class LanguageLearn {
  FileIO fileIO = new FileIO();
  double score;
  double[] modelScore = new double[fileIO.modList.length];
  int max = 0;
  Bigrams bigrams = new Bigrams();
  String unkownFile = "";
  ArrayList<String> words = new ArrayList<>();
  ArrayList<String> bigramsArr = new ArrayList<>();
  ArrayList<String> modelBigrams = new ArrayList<>();


  public LanguageLearn(String unkownFile) {
    this.unkownFile = unkownFile;
  }


  public void run() {
    if(fileIO.dirList != null){
      // Iterating through testing directory files
      String fileInput = fileIO.readUTF8File(unkownFile);
      words = bigrams.extractWords(fileInput, bigrams.unknown);
      bigramsArr = bigrams.getBigrams(words);
      Collections.sort(bigramsArr);
      if(fileIO.modList != null) {
        //iterating through model files
        for (int j =0; j < fileIO.modList.length; j++) {
          modelBigrams = fileIO.getModels(fileIO.modList[j].getPath());
          score = 1;
          for( int k = 0; k < bigramsArr.size(); k++) {
            String g = binarySearch(modelBigrams, 0, modelBigrams.size(), bigramsArr.get(k));
            int si = bigramsArr.size();
            String s = g.split(" ")[1];
            double c = Double.parseDouble(s);
            score = c*score;
          }
          modelScore[j] = score;
        }
      }
    }
    for(int x = 0; x < modelScore.length; x++){
      if(modelScore[x] > max){
        max = x;
      }
    }
    try {
      System.out.print(fileIO.modList[max].getName().replace("Model.txt", ""));
    } catch (ArrayIndexOutOfBoundsException e) {
    }
  }


  public static <T extends Comparable<T>> String binarySearch(ArrayList<T> arr, int first, int last, T target) {
    int mid; // index of the midpoint
    T midvalue; // object that is assigned arr[mid]
    int origLast = last; // save original value of last
    while (first < last) { // test for nonempty sublist
      mid = (first + last) / 2;
      midvalue = arr.get(mid);
      if (midvalue.toString().contains(target.toString())) {
        return midvalue.toString(); // have a match
      } else if (target.compareTo(midvalue) < 0) {
        last = mid; // search lower sublist. Reset last
      } else {
        first = mid + 1; // search upper sublist. Reset first
      }
    }
    return "nf 0"; // target not found
  }
}
