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

    @Override
    public boolean hasNext() {
        if (this.nextSystem == null) {
            try {
                this.nextSystem = this.pifObjectStream.getNextSystem();
            }
            catch (IOException e) {
                return false;
            }
        }
        return this.nextSystem != null;
    }

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
                return null;
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