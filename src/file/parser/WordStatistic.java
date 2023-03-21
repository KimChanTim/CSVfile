package file.parser;

import java.io.IOException;
import java.util.List;

public interface WordStatistic {
    void readFile() throws IOException;
    List<Word> getSortedWords();
    int getTotalWordCount();
}
