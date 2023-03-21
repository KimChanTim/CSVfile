package file.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FileStatistic implements WordStatistic {
    private final Reader reader;
    private Map<String, Integer> wordMap = null;
    private int totalWordCount = 0;

    public FileStatistic(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void readFile() throws IOException {
        FileParser fileParser = new FileParser(reader);
        wordMap = fileParser.parse();
        totalWordCount = fileParser.getWordCount();
    }
    @Override
    public List<Word> getSortedWords() {
        if (wordMap == null) {
            return null;
        }
        List<Word> wordList = new ArrayList<>();
        for(Object entryObj : wordMap.entrySet()){
            Map.Entry entry =(Map.Entry) entryObj;
            Word word = new Word((String) entry.getKey(), (Integer) entry.getValue());
            wordList.add(word);
        }
        wordList.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1 == null)
                    if (o2 == null)
                        return 0;
                    else
                        return 1;
                else if (o2 == null)
                    return -1;
                return o2.count().compareTo(o1.count());
            }
        });
        return wordList;
    }
    @Override
    public int getTotalWordCount() {
        return totalWordCount;
    }
}
