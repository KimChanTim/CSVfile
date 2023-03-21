import CSV.constructor.CSV_constructor;
import file.parser.FileStatistic;
import file.parser.Word;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Reader reader = null;
        Writer writer = null;

        try {
            reader = new InputStreamReader(new FileInputStream("text.txt"));
            FileStatistic statistic = new FileStatistic(reader);
            statistic.readFile();
            List<Word> wordList = statistic.getSortedWords();

            writer = new OutputStreamWriter(new FileOutputStream("output.csv"));
            CSV_constructor constructor = new CSV_constructor(writer, wordList, statistic.getTotalWordCount());
            constructor.makeFile();
        }

        catch (IOException e) {
            System.err.println();
        }

        finally {

            if (null != reader) {
                try {
                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }

            if (null != writer) {
                try {
                    writer.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}
