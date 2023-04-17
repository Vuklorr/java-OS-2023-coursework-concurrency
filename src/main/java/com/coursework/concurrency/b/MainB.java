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
        //проверка на количество аргументов
        if(args.length < 2) {
            System.out.println("Введите 2 аргумента:");
            return;
        }

        String path = args[0];
        String newPath = args[1];
        List<String> fileNames = new ArrayList<>();
        List<String> newFileNames = new ArrayList<>();
        //добавление в списки имен файлов
        for(int i = 1; i < 11; i++) {
            fileNames.add(path + (i) + ".txt");
            newFileNames.add(newPath + (i) + ".txt");
        }
        Thread[] threads = new Thread[fileNames.size()];
        Long[] startTimes = new Long[fileNames.size()]; //массив для запоминания начала выполенения метода
        Long[] endTimes = new Long[fileNames.size()]; //массив для запоминания конца выполенения метода

        long startTime = System.currentTimeMillis(); //время начала работы программы

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
