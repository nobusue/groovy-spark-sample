import org.apache.spark.*
import org.apache.spark.api.java.*
import org.apache.spark.api.java.function.*

public class SparkGroovySample {

  public static void main(String[] args) {

    def conf = new SparkConf().setMaster("local[2]").setAppName("WordCount")
    def sc   = new JavaSparkContext(conf)
    def file = sc.textFile("YOUR_TEXT_FILE_PATH").cache()

    def filterFunc = new Function<String,Boolean>() {
      public Boolean call(String s) {
        return s.contains('spark')
    }}

    def filterFunc2 = { it.contains('hadoop') } as Function

    def countsOfSpark = file.filter(filterFunc).count()
    def countsOfHadoop = file.filter(filterFunc2).count()

    println "Count of Spark:${countsOfSpark}, Count of Hadoop:${countsOfHadoop}"
  }

}
