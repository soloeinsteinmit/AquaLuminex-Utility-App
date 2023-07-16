package com.example.aqualuminexapp.splashscreen;

import javafx.concurrent.Task;

public class ProgressBarCounterTask extends Task<Long> {
    public static final long COUNT_LIMIT = 50000000L;
    public static boolean loadNewStage = false;


/*
    public ProgressBarCounterTask(long progressCounter) {
        this.progressCounter = progressCounter;
    }
*/

    @Override
    protected Long call() throws Exception {

        long sum = 0;

        for (int i = 0; i < COUNT_LIMIT; i++) {
            sum += i;

            if (isCancelled()) {
                break;
            }
            updateValue((long) i);
            updateProgress(i, COUNT_LIMIT);

        }

        loadNewStage = true;
        return sum;
    }

}