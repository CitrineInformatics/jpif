package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;

/**
 * Class that wraps a {@link PifObjectStream} to allow for streaming modification of the objects in the stream. Note
 * that the {@link #close()} will close the underlying stream.
 *
 * @author Kyle Michel
 */
public abstract class ModifyingPifObjectStream extends PifObjectStream {

    /**
     * Apply an operation to the current. This method can be overridden by subclasses to modify systems as they are
     * passing through the stream.
     *
     * @param system {@link System} to operate on.
     * @return The input system object.
     */
    protected abstract System operateOnSystem(final System system);

    @Override
    public void close() throws IOException {
        this.pifObjectStream.close();
    }

    /**
     * Constructor.
     *
     * @param pifObjectStream {@link PifObjectStream} to operate on.
     */
    public ModifyingPifObjectStream(final PifObjectStream pifObjectStream) {
        this.pifObjectStream = pifObjectStream;
    }

    /** Stream being operated on. */
    private final PifObjectStream pifObjectStream;
}