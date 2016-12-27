package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;

/**
 * Class that wraps a {@link PifSystemStream} to allow for streaming modification of the objects in the stream.
 *
 * @author Kyle Michel
 */
public abstract class ModifyingPifSystemStream extends SystemStreamWrappingPifObjectStream {

    @Override
    public <T extends System> T getNextSystem(final Class<T> systemClass) throws IOException {
        final T system = super.getNextSystem(systemClass);
        if (system != null) {
            modify(system);
        }
        return system;
    }

    /**
     * Operate on the next system going through the stream.
     *
     * @param system {@link System} to operate on.
     * @throws IOException if the system cannot be modified.
     */
    protected abstract void modify(final System system) throws IOException;

    /**
     * Constructor.
     *
     * @param pifSystemStream {@link PifSystemStream} to operate on.
     */
    public ModifyingPifSystemStream(final PifSystemStream pifSystemStream) {
        super(pifSystemStream);
    }
}