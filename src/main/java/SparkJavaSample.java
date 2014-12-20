import org.apache.spark.*;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;

public class SparkJavaSample {
  public static void main(String[] args) {
    SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("WordCount");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> file = sc.textFile("YOUR_TEXT_FILE_PATH").cache();

    Function<String,Boolean> filterFunc = new Function<String,Boolean>() {
      public Boolean call(String s) { return s.contains("spark"); }
    };

    long counts = file.filter(filterFunc).count();
    System.out.println(counts);
  }
}
