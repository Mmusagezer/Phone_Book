package com.company;


import java.util.Hashtable;

public class SearchingAlgorithms {
    public static int[] linearSearch(String find, String directory) {
        int count = 0;
        String[] newDirectoryArray = turnDirectoryToArrayOfStrings(directory);

        String [] findArray = toArray(find);
        int length = findArray.length;
        for (String s : findArray) {
            for (String value : newDirectoryArray) {
                if (s.equals(value)) {
                    count++;
                }
            }
        }
        return new int[] {count, length};
    }
    public static String[] bubbleSort(String[] array, long timeLimit) {
        long startBubble = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-1-i ; j++) {
                if (array[j].compareTo(array[j+1]) > 0) {
                    String k = array[j+1];
                    array[j+1] = array[j];
                    array[j] = k;
                    if (System.currentTimeMillis() - startBubble > 10*timeLimit) {
                        return new String[] {"stopped"};
                    }
                }
            }
        }
        return array;
    }
    public static int jumpSearch(String[] array, String str) {
        int stepSize = (int) Math.floor(Math.sqrt(array.length));
        int prev = 0;
        while (array[prev].compareTo(str) < 0) {
            prev += stepSize;
            if (prev >= array.length){
                prev = array.length;
                break;
            }

        }

        for (int i = prev; i > prev - stepSize; i--) {
            if (array[i].equals(str)) {
                return i;
            }
        }
        return -1;

    }
    public static int[] turnMillisToMinSecMillis(long timeInMilliseconds){
        int min = (int) (timeInMilliseconds /60000);
        int sec = (int) ((timeInMilliseconds %60000)/1000);
        int millis = (int) (timeInMilliseconds % 1000);
        return new int[] {min, sec, millis};
    }

    public static String [] turnDirectoryToArrayOfStrings(String directory) {
        String[] directoryArray = directory.split("\n");
        String[] newDirectoryArray = new String[directoryArray.length];

        for (int i = 0; i < directoryArray.length; i++) {
            newDirectoryArray[i] = directoryArray[i].substring(directoryArray[i].indexOf(" ") + 1);
        }
        return newDirectoryArray;
    }
    public static String [] toArray (String str) {
        return str.split("\n");
    }
    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    static void swap(String[] arr, int i, int j)
    {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int partition(String[] arr, int low, int high)
    {
        String pivot = arr[high];

        int i = (low - 1);

        for(int j = low; j <= high - 1; j++) {

            if (arr[j].compareTo(pivot) < 0) {

                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static int binarySearch(String[] array, String x, int firstIndex, int lastIndex) {
        if (array[firstIndex].equals(array[lastIndex]) && !array[firstIndex].equals(x)){
            return -1;
        }
        int middleIndex = (firstIndex + lastIndex)/2;
        if (array[middleIndex].equals(x)) {
            return middleIndex;
        } else if (array[middleIndex].compareTo(x) < 0) {
            return binarySearch(array, x, middleIndex + 1, lastIndex);
        }else {
            return binarySearch(array, x, firstIndex, middleIndex);
        }
    }

    public static Hashtable<Integer, String> arrayToHashtable(String[] arr) {
        Hashtable<Integer, String> table = new Hashtable<Integer, String>(arr.length);
        for (String s : arr) {
            table.put(s.hashCode(), s);
        }
        return table;
    }
}
