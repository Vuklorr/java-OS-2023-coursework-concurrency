package com.coursework.concurrency.b;

import com.coursework.concurrency.b.task.RemoveLongSentences;

import java.util.*;

/**
 * Курсовая работа.
 * Долбицын В.С. УИС-311
 * Вариант 8: "Исключить из текста все предложения длиной более 50 символов"
 * Программа B
 */
public class MainB {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //время начала работы программы

        //добавление в списки имен файлов
        List<String> fileNames = new ArrayList<>(Arrays.asList(args).subList(0, args.length));
        List<String> newFileNames = new ArrayList<>();
        for (String arg : args) {
            int index = arg.indexOf(".txt");
            String newName = arg.substring(0, index);
            newFileNames.add(newName + "_out.txt");
        }
        Thread[] threads = new Thread[fileNames.size()];
        Long[] startTimes = new Long[fileNames.size()]; //массив для запоминания начала выполенения метода
        Long[] endTimes = new Long[fileNames.size()]; //массив для запоминания конца выполенения метода


        //создание и выполнение дочерних потоков
        for (int i = 0; i < fileNames.size(); i++) {
            threads[i] = new RemoveLongSentences(i, startTimes, endTimes
                    , fileNames.get(i), newFileNames.get(i));

            threads[i].start();//вызов дочернего потока
        }

        try {
            // Соединение всех потоков в один
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); //время завершения программы
        System.out.println("Время выполенения программы:" + (endTime - startTime));
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println("Время начала и конца обработки " + fileNames.get(i) +  " файла:"
                    + startTimes[i] + " " + endTimes[i]);
        }
    }
}
