package com.coursework.concurrency.a.task;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class RemoveLongSentences {

    /**
     * Массив для сохранения начала времени обработки.
     */
    private final List<Long> startTimes;

    /**
     * Массив для сохранения конца времени обработки.
     */
    private final List<Long> endTimes;

    public RemoveLongSentences(List<Long> startTimes, List<Long> endTimes) {
        this.startTimes = startTimes;
        this.endTimes = endTimes;
    }

    /**
     * Метод удаления предложения, состоящее из более 50 символов.
     * @param fileName - имя файла, который нужно обработать
     * @param newFileName - новое имя файла, в который будет записан обработанный файл
     */
    public void remove(String fileName, String newFileName) throws IOException {
        startTimes.add(System.currentTimeMillis()); // время начала выполения метода

        try(Scanner in = new Scanner(new FileInputStream(fileName));
            FileWriter fileWriter = new FileWriter(newFileName)) {
            //выполняется, пока файл не пуст
            while (in.hasNextLine()) {
                String sentence = in.nextLine();
                //найти знак завершения предложения (.?!...)
                if((sentence.endsWith(".")) || (sentence.endsWith("!")) || sentence.endsWith("?") || sentence.endsWith("...")) {
                    //если пердложение состоит из 50 или меньше символов - записать его в новый файл
                    if(sentence.length() <= 50) {
                        fileWriter.write(sentence + '\n');
                    }
                }
            }
        }
        endTimes.add(System.currentTimeMillis()); //время завершение метода
    }
}