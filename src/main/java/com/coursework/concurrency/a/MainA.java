package com.coursework.concurrency.a;

import com.coursework.concurrency.a.task.RemoveLongSentences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Курсовая работа.
 * Долбицын В.С. УИС-311
 * Вариант 8: "Исключить из текста все предложения длиной более 50 символов"
 * Программа А
 */
public class MainA {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis(); //время начала работы программы

        //добавление в списки имен файлов
        List<String> fileNames = new ArrayList<>(Arrays.asList(args).subList(0, args.length));
        List<String> newFileNames = new ArrayList<>();
        for (String arg : args) {
            int index = arg.indexOf(".txt");
            String newName = arg.substring(0, index);
            newFileNames.add(newName + "_out.txt");
        }

        List<Long> startTimes = new ArrayList<>(fileNames.size()); //массив для запоминания начала выполенения метода
        List<Long> endTimes = new ArrayList<>(fileNames.size()); //массив для запоминания конца выполенения метода

        RemoveLongSentences removeLongSentences =
                new RemoveLongSentences(startTimes, endTimes);
        //обработка файлов
        for(int i = 0; i < fileNames.size(); i++) {
            removeLongSentences.remove(fileNames.get(i), newFileNames.get(i));
        }

        long endTime = System.currentTimeMillis(); //время завершения программы
        System.out.println("Время выполенения программы:" + (endTime - startTime));
        //вывод времен обработки каждого файла
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println("Время начала и конца обработки " + fileNames.get(i) +  " файла:"
                    + startTimes.get(i) + " " + endTimes.get(i));
        }
    }
}