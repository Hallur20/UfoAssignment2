package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {


    public static void main(String[] args) throws IOException {



            String fileName = "/home/hvn15/letterfrequencies/UfoAssignment2/src/main/resources/FoundationSeries.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Long chars = 0L;
        String line;
        while((line = reader.readLine()) != null){
            chars += line.length();
        }
        System.out.println("chars was: " + chars);
        System.out.println(reader.lines().count()/4);






        Map<Integer, Long> freq = new HashMap<>();
        Long charAt = 0L;
        for (int i = 0; i < 4; i++){
            TallyThread t = new TallyThread(charAt, chars/4, fileName, freq);
            t.run();
            charAt += chars/4;
        }

            //Map<Integer, Long> freq = new HashMap<>();
            //tallyChars(reader, freq);
            print_tally(freq);


    }

    private static void tallyChars(BufferedReader reader, Map<Integer, Long> freq) throws IOException {
      //  System.out.println(reader.lines().count());
        int sum = 0;
            long start = System.currentTimeMillis();

            int b;

            while ((b = reader.read()) != -1) {
                try {
                    freq.put(b, freq.get(b) + 1);

                } catch (NullPointerException np) {
                    freq.put(b, 1L);
                }
            }
            reader = new BufferedReader(new FileReader("/home/hvn15/letterfrequencies/UfoAssignment2/src/main/resources/FoundationSeries.txt"));
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;

    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));;
        }
    }
}
