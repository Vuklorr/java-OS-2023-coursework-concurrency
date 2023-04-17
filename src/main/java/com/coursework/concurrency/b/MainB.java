package com.coursework.concurrency.b;

import com.coursework.concurrency.b.task.RemoveLongSentences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class MainB {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Введите 2 аргумента:");
            return;
        }

        String path = args[0];
        String newPath = args[1];
        List<String> fileNames = new ArrayList<>();
        List<String> newFileNames = new ArrayList<>();
        for(int i = 1; i < 11; i++) {
            fileNames.add(path + (i) + ".txt");
            newFileNames.add(newPath + (i) + ".txt");
        }
        Thread[] threads = new Thread[fileNames.size()];
        Long[] startTimes = new Long[fileNames.size()];
        Long[] endTimes = new Long[fileNames.size()];

        long startTime = System.currentTimeMillis(); //время начала работы программы

        for (int i = 0; i < fileNames.size(); i++) {
            threads[i] = new RemoveLongSentences(i, startTimes, endTimes
                    , fileNames.get(i), newFileNames.get(i));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); //время завершения программы
        System.out.println("Время выполенения программы:" + (endTime - startTime));
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println("File " + fileNames.get(i) + " start time: " + startTimes[i] + ", end time: " + endTimes[i]);
        }
    }

}
