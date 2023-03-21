package file.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    private final int bufSize = 8;
    private final Reader reader;
    private Map<String, Integer> wordMap = null;
    private char[] readBuf = null;
    private int readCount;
    private StringBuilder word;
    private int wordCount = 0;

    public FileParser(Reader reader) {
        this.reader = reader;
    }
    private void parseBuf() {
        if (!Character.isLetterOrDigit(readBuf[0]) && !word.isEmpty()) {
            wordMap.put(word.toString(), wordMap.getOrDefault(word.toString(), 0) + 1);
            word.setLength(0);
            wordCount++;
        }
        int len = 0;
        for (int i = 0; i < readCount; i++) {
            if (Character.isLetterOrDigit(readBuf[i])) {
                len++;
            } else if (len != 0) {
                word.append(readBuf, i - len, len);
                wordMap.put(word.toString(), wordMap.getOrDefault(word.toString(), 0) + 1);
                word.setLength(0);
                wordCount++;
                len = 0;
            }
        }
        if (len != 0) {
            word.append(readBuf, readCount - len, len);
        }
    }
    public Map<String, Integer> parse() throws IOException {
        wordMap = new HashMap<>();
        readBuf = new char[bufSize];
        word = new StringBuilder();

        while ((readCount = reader.read(readBuf, 0, bufSize)) > 0) {
            parseBuf();
        }

        if (!word.isEmpty()) {
            wordMap.put(word.toString(), wordMap.getOrDefault(word.toString(), 0) + 1);
            wordCount++;
        }

        return wordMap;
    }
    public int getWordCount() {
        return wordCount;
    }
}
