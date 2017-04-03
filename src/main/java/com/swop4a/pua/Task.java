package com.swop4a.pua;

import java.io.FileNotFoundException;

/**
 * Interface define behavior for all tasks.
 *
 * @author Pavkin Alexandr
 * @version 1.0
 */

public interface Task {
    void run() throws FileNotFoundException;
}
