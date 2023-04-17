package com.coursework.concurrency.b.task;

import java.io.*;
import java.util.*;

public class RemoveLongSentences extends Thread {

    /**
     * Индекс потока.
     */
    private final int index;

    /**
     * Массив для сохранения начала времени обработки.
     */
    private final Long[] startTimes;

    /**
     * Массив для сохранения конца времени обработки.
     */
    private final Long[] endTimes;

    /**
     * Имя файла, который нужно обработать.
     */
    private final String fileName;

    /**
     * Имя файла, в который нужно записать обработанный файл.
     */
    private final String newFileName;

    public RemoveLongSentences(int index, Long[] startTimes, Long[] endTimes, String fileName, String newFileName) {
        this.index = index;
        this.startTimes = startTimes;
        this.endTimes = endTimes;
        this.fileName = fileName;
        this.newFileName = newFileName;
    }

    @Override
    public void run()  {
        startTimes[index] = System.currentTimeMillis(); // время начала выполнения метода

        File file = new File(fileName);
        try(Scanner in = new Scanner(new FileInputStream(file));
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        endTimes[index] = System.currentTimeMillis(); // время конца выполнения метода
    }
}
