package com.coursework.concurrency.a;

import com.coursework.concurrency.a.task.RemoveLongSentences;

import java.io.IOException;
import java.util.ArrayList;
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
        //проверка на ввод 2-х аргументов
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
        List<Long> startTimes = new ArrayList<>(fileNames.size()); //массив для запоминания начала выполенения метода
        List<Long> endTimes = new ArrayList<>(fileNames.size()); //массив для запоминания конца выполенения метода

        RemoveLongSentences removeLongSentences =
                new RemoveLongSentences(startTimes, endTimes);
        //обработка 10 файлов
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