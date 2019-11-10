package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.util.Map;

public class TallyThread implements Runnable {
    private Long startLine;
    private Long endLine;
    private String fileName;
    private Map<Integer, Long> freq;


    public TallyThread(Long startLine, Long endLine, String fileName, Map<Integer, Long> freq) {
        this.startLine = startLine;
        this.endLine = endLine;
        this.fileName = fileName;
        this.freq = freq;
    }


    public Long getStartLine() {
        return startLine;
    }

    public void setStartLine(Long startLine) {
        this.startLine = startLine;
    }

    public Long getEndLine() {
        return endLine;
    }

    public void setEndLine(Long endLine) {
        this.endLine = endLine;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<Integer, Long> getFreq() {
        return freq;
    }

    public void setFreq(Map<Integer, Long> freq) {
        this.freq = freq;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            tallyChars(reader, freq, startLine, endLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void tallyChars(BufferedReader reader, Map<Integer, Long> freq, Long startLine, Long endLine) throws IOException {
        long start = System.currentTimeMillis();

        int b;
        int lines = 0;
        while ((b = reader.read()) != -1) {
            //      System.out.println();
            try {
                freq.put(b, freq.get(b) + 1);

            } catch (NullPointerException np) {
                freq.put(b, 1L);
            }
        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("this thread took time: " + timeElapsed);
    }
}
