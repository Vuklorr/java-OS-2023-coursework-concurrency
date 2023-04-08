package com.coursework.concurrency.a;

import com.coursework.concurrency.a.task.RemoveLongSentences;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Курсовая работа.
 * Долбицын В.С. УИС-311
 * Вариант 8: "Исключить из текста все предложения длиной более 50 символов"
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //проверка на ввод 2-х аргументов
        if(args.length < 2) {
            System.out.println("Введите 2 аргумента:");
            return;
        }
        List<Long> methodTimes = new ArrayList<>();
        RemoveLongSentences removeLongSentences =
                new RemoveLongSentences(methodTimes);
        long startTime = System.currentTimeMillis(); //время начала работы программы
        String path = args[0];
        String newPath = args[1];
        //обработка 10 файлов
        for(int i = 0; i < 10; i++) {
            removeLongSentences.remove(new File(path + (i + 1) + ".txt")
                    , newPath + (i + 1) + ".txt");
        }

        long endTime = System.currentTimeMillis(); //время завершения программы
        System.out.println("Время выполенения программы:" + (endTime - startTime));
        //вывод времен обработки каждого файла
        int listIndex = 0;//индекс в списке
        int fileNumber = 1;//номер файла
        while (fileNumber < 11) {
            System.out.println("Время начала и конца обработки " + fileNumber +  " файла:"
                    + methodTimes.get(listIndex) + " " + methodTimes.get(++listIndex));
            listIndex++;
            fileNumber++;
        }
    }
}