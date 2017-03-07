package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Class that wraps a number of {@link PifSystemStream} objects and merges them into a single stream.
 *
 * @author Kyle Michel
 */
public class MergingPifSystemStream extends PifSystemStream {

    @Override
    public void close() throws IOException {
        if (this.pifSystemStreams != null) {
            for (PifSystemStream i : this.pifSystemStreams) {
                i.close();
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return this.currentStream < 0;
    }

    @Override
    protected System advanceToNextSystem() throws IOException {

        // Make sure that we haven't already finished
        if (isFinished()) {
            return null;
        }

        // Iterate over streams until we find a record that we can return
        System systemToReturn = null;
        while (systemToReturn == null) {

            // Read the next system and move to the next stream if the end of the current one has been reached
            systemToReturn = this.pifSystemStreams.get(this.currentStream).getNextSystem();
            if (systemToReturn == null) {
                if (++this.currentStream >= this.pifSystemStreams.size()) {
                    this.currentStream = -1;
                    break;
                }
            }
        }
        return systemToReturn;
    }

    /**
     * Constructor.
     *
     * @param pifSystemStreams One or more {@link PifSystemStream}s to merge.
     */
    public MergingPifSystemStream(final PifSystemStream... pifSystemStreams) {
        if (pifSystemStreams == null) {
            this.pifSystemStreams = null;
            this.currentStream = -1;
        }
        else {
            this.pifSystemStreams = Arrays.asList(pifSystemStreams);
            this.currentStream = this.pifSystemStreams.isEmpty() ? -1 : 0;
        }
    }

    /** Current stream being operated on. */
    private int currentStream;

    /** List of streams to merge. */
    private final List<PifSystemStream> pifSystemStreams;
}