package com.swop4a.pua;

import java.io.FileNotFoundException;

/**
 * Special class for launching tasks.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public abstract class TaskLauncher {
    public static void launch(Task task) throws FileNotFoundException {
        task.run();
    }
}
