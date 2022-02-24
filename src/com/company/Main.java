package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import static com.company.SearchingAlgorithms.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Start searching (linear search)...");
        String find = new String(Files.readAllBytes(Paths.get("src\\com\\company\\find.txt")));
        String directory = new String(Files.readAllBytes(Paths.get("src\\com\\company\\directory.txt")));
        long startTime = System.currentTimeMillis();
        int[] countAndLength = linearSearch(find, directory);
        long passedTime = System.currentTimeMillis() - startTime;
        String[] directoryArray = turnDirectoryToArrayOfStrings(directory);
        int[] minSecMillis = turnMillisToMinSecMillis(passedTime);
        int min = minSecMillis[0];
        int sec = minSecMillis[1];
        int millis = minSecMillis[2];
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. %n %n",
                countAndLength[0], countAndLength[1], min, sec, millis);

        System.out.println("Start searching (bubble sort + jump search)...");
        int count = 0;
        int length = 0;
        String[] findArray = toArray(find);

        // sorting time

        long sortedStartTime = System.currentTimeMillis();
        String[] sortedDirectoryArray = bubbleSort(directoryArray, passedTime);
        long sortedPassedTime = System.currentTimeMillis() - sortedStartTime;
        int[] sorting = turnMillisToMinSecMillis(sortedPassedTime);

        if (sortedDirectoryArray[0].equals("stopped")) {
            long startLinear = System.currentTimeMillis();
            int[] countAndLength2 = linearSearch(find, directory);
            long linearTime = System.currentTimeMillis() - startLinear;
            int[] linear = turnMillisToMinSecMillis(linearTime);
            int[] total = turnMillisToMinSecMillis(System.currentTimeMillis() - sortedStartTime);
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. %n",
                    countAndLength2[0], countAndLength2[1], total[0], total[1], total[2]);
            System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search %n",sorting[0], sorting[1], sorting[2]);
            System.out.printf("Searching time: %d min. %d sec. %d ms. %n %n", linear[0], linear[1], linear[2]);

        } else {

            //jumping time
            long jumpStartTime = System.currentTimeMillis();
            for (String str : findArray) {
                jumpSearch(sortedDirectoryArray, str);
                count++;
                length++;
            }
            long jumpedPassedTime = System.currentTimeMillis() - jumpStartTime;
            int[] sortAndJumpTime = turnMillisToMinSecMillis(sortedPassedTime + jumpedPassedTime);

            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. %n",
                    count, length, sortAndJumpTime[0], sortAndJumpTime[1], sortAndJumpTime[2]);
            int[] jumpTime = turnMillisToMinSecMillis(jumpedPassedTime);
            System.out.printf("Sorting time: %d min. %d sec. %d ms. %n", sorting[0], sorting[1], sorting[2]);
            System.out.printf("Searching time: %d min. %d sec. %d ms. %n %n", jumpTime[0], jumpTime[1], jumpTime[2]);
        }

        long startQuick = System.currentTimeMillis();
        quickSort(directoryArray,0, directoryArray.length-1);
        long passedQuick = System.currentTimeMillis() - startQuick;
        long startBinary = System.currentTimeMillis();
        int countbinary = 0;
        for (String str : findArray) {
            int i = binarySearch(directoryArray, str, 0, directoryArray.length-1);
            if (i != -1) {
                countbinary ++;
            }
        }
        long passedBinary = System.currentTimeMillis() - startBinary;
        int[] quickAndBinary = turnMillisToMinSecMillis(passedBinary+passedQuick);
        int[] quickTime = turnMillisToMinSecMillis(passedQuick);
        int[] binaryTime = turnMillisToMinSecMillis(passedBinary);
        System.out.println("Start searching (quick sort + binary search)...");
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. %n",
                countbinary, findArray.length, quickAndBinary[0], quickAndBinary[1], quickAndBinary[2]);
        System.out.printf("Sorting time: %d min. %d sec. %d ms. %n", quickTime[0], quickTime[1], quickTime[2]);
        System.out.printf("Searching time: %d min. %d sec. %d ms.", binaryTime[0], binaryTime[1], binaryTime[2]);

        System.out.println("Start searching (hash table)...");
        long htStart = System.currentTimeMillis();
        Hashtable<Integer, String> ht = arrayToHashtable(directoryArray);
        long htPassed = System.currentTimeMillis() - htStart;
        int htcount = 0;
        long htSearch = System.currentTimeMillis();
        for (String str : findArray) {
            ht.get(str.hashCode());
            htcount++;
        }
        long htSearchPassed = System.currentTimeMillis() - htSearch;
        int [] hts = turnMillisToMinSecMillis(htPassed+htSearchPassed);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms. %n",
                htcount, findArray.length, hts[0], hts[1], hts[2]);
        int [] htCreate = turnMillisToMinSecMillis(htPassed);
        int [] htSearchTime = turnMillisToMinSecMillis(htSearchPassed);
        System.out.printf("Creating time: %d min. %d sec. %d ms. %n",htCreate[0], htCreate[1], htCreate[2]);
        System.out.printf("Searching time: %d min. %d sec. %d ms.",htSearchTime[0], htSearchTime[1], htSearchTime[2]);

    }

}



