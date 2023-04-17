package com.coursework.concurrency.a.task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveLongSentences {
    private final List<Long> methodTimes;

    public RemoveLongSentences(List<Long> methodTimes) {
        this.methodTimes = methodTimes;
    }

    /**
     * Метод удаления предложения, состоящее из более 50 символов.
     * @param pathNewFile - полное имя нового файла (в который будут сохраняться предложения,
     *                   в которых не более 50 символов.
     */
    public void remove(File file, String pathNewFile) throws IOException {
        methodTimes.add(System.currentTimeMillis()); // время начала выполения метода

        try(Scanner in = new Scanner(new FileInputStream(file));
            FileWriter fileWriter = new FileWriter(pathNewFile)) {
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
        methodTimes.add(System.currentTimeMillis()); //время завершение метода
    }
}