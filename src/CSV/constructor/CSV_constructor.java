package CSV.constructor;

import file.parser.Word;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSV_constructor {
    private final Writer writer;
    private final List<Word> wordList;
    private final int totalWordCount;
    public CSV_constructor(Writer writer, List<Word> wordList, int count) {
        this.writer = writer;
        this.wordList = wordList;
        this.totalWordCount = count;
    }

    public void makeFile() throws IOException {
        for (Word word : wordList) {
            writer.write(word.word() + ',' + word.count() + ',' + (((double) word.count() / totalWordCount) * 100) + '\n');
        }
    }
}
