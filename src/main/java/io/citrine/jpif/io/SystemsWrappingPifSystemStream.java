package io.citrine.jpif.io;

import io.citrine.jpif.obj.system.System;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Class that streams PIF systems from some in-memory source such as a list of PIF {@link System} objects or a single
 * such object.
 *
 * @author Kyle Michel
 */
public class SystemsWrappingPifSystemStream extends PifSystemStream {

    /**
     * Constructor.
     *
     * @param systems List of {@link System} objects to stream.
     */
    public SystemsWrappingPifSystemStream(final List<? extends System> systems) {
        // We do not care about the type of the objects in the list since their type will be checked by the parent class
        this.systems = systems;
        this.index = 0;
    }

    /**
     * Constructor.
     *
     * @param system {@link System} to stream.
     */
    public SystemsWrappingPifSystemStream(final System system) {
        this.systems = Collections.singletonList(system);
        this.index = 0;
    }

    @Override
    protected System advanceToNextSystem() throws IOException {
        if (this.index < this.systems.size()) {
            return this.systems.get(this.index++);
        }
        return null;
    }

    @Override
    protected boolean isFinished() {
        return (this.index >= this.systems.size());
    }

    @Override
    public void close() throws IOException {}

    /** Current index in the list of systems. */
    private int index;

    /** List of PIF systems being streamed. */
    private final List<? extends System> systems;
}