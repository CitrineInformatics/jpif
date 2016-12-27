package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;

/**
 * Class that wraps another {@link PifSystemStream} object and passes through its objects. This class is not terribly
 * useful by itself, but provides a base class for other streaming classes that do act on objects as they are passed
 * through.
 *
 * @author Kyle Michel
 */
public abstract class SystemStreamWrappingPifObjectStream extends PifSystemStream {

    /**
     * Constructor.
     *
     * @param pifSystemStream {@link PifSystemStream} to stream.
     */
    protected SystemStreamWrappingPifObjectStream(final PifSystemStream pifSystemStream) {
        this.wrappedPifSystemStream = pifSystemStream;
    }

    @Override
    protected System advanceToNextSystem() throws IOException {
        return this.wrappedPifSystemStream.getNextSystem();
    }

    @Override
    protected boolean isFinished() {
        return this.wrappedPifSystemStream.isFinished();
    }

    @Override
    public void close() throws IOException {
        this.wrappedPifSystemStream.close();
    }

    /** System stream being processed. */
    private final PifSystemStream wrappedPifSystemStream;
}