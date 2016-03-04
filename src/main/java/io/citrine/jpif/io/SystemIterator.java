package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;
import java.util.Iterator;

/**
 * Iterator class for iterating over systems.
 *
 * @author Kyle Michel
 */
public class SystemIterator implements Iterator<System> {

    /**
     * Return whether there is another item to iterate over.
     *
     * @return True if another item can be obtained.
     * @throws RuntimeException if an {@link IOException} is thrown from within this function.
     */
    @Override
    public boolean hasNext() {
        if (this.nextSystem == null) {
            try {
                this.nextSystem = this.pifObjectStream.getNextSystem();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.nextSystem != null;
    }

    /**
     * Get the next object.
     *
     * @return {@link System} object.
     * @throws RuntimeException if an {@link IOException} is thrown from within this function.
     */
    @Override
    public System next() {
        if (this.nextSystem != null) {
            System res = this.nextSystem;
            this.nextSystem = null;
            return res;
        }
        else {
            try {
                return this.pifObjectStream.getNextSystem();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Constructor.
     *
     * @param pifObjectStream {@link PifObjectStream} being read from.
     */
    public SystemIterator(final PifObjectStream pifObjectStream) {
        this.pifObjectStream = pifObjectStream;
    }

    /** Object stream to read from. */
    private final PifObjectStream pifObjectStream;

    /** Next system in the stream. */
    private System nextSystem;
}