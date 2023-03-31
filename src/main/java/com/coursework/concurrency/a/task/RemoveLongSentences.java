package com.coursework.concurrency.a.task;

import java.io.*;
import java.util.Scanner;

public class RemoveLongSentences {
    /**
     * Поле File, который ссылается на файл, из которого будут удаляться длинные предложения.
     */
    private final File file;

    public RemoveLongSentences(File file) {
        this.file = file;
    }

    /**
     * Метод удаления предложения, состоящее из более 50 символов.
     * @param pathNewFile - полное имя нового файла (в который будут сохраняться предложения,
     *                   в которых не более 50 символов.
     * @return - время выполения метода.
     */
    public long remove(String pathNewFile) throws IOException {
        long startTime = System.currentTimeMillis(); //начало выполения метода

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
        long endTime = System.currentTimeMillis(); //время завершение метода
        return endTime-startTime;
    }
}