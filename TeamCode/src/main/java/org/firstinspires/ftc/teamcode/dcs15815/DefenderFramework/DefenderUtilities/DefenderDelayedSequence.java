package org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities;

import com.qualcomm.robotcore.util.ElapsedTime;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DefenderDelayedSequence {
    public long timeout;
    private Runnable firstAction, secondAction;
    static ExecutorService es = Executors.newSingleThreadExecutor();


    public DefenderDelayedSequence(Runnable fa, Runnable sa, long t) {
        firstAction = fa;
        secondAction = sa;
        timeout = t;
    }

    public void run() {
        es.submit(() -> {
            try {
                firstAction.run();
                TimeUnit.MILLISECONDS.sleep(timeout);
                secondAction.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}