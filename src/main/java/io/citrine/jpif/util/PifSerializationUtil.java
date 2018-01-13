package io.citrine.jpif.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utilities for serialization and deserialization.
 *
 * @author Kyle Michel
 */
public class PifSerializationUtil {

    /**
     * Write the input object to the input output stream.
     *
     * @param out {@link ObjectOutputStream} to write to.
     * @param object Object to write.
     * @throws IOException if thrown while writing the object.
     */
    public static void write(final ObjectOutputStream out, final Object object) throws IOException {
        out.writeObject(PifObjectMapper.getInstance().writeValueAsBytes(object));
    }

    /**
     * Read from the input stream to the input object.
     *
     * @param in {@link ObjectInputStream} to read from.
     * @param object Object to read to.
     * @throws ClassNotFoundException if the class cannot be created.
     * @throws IOException if thrown while reading the stream.
     */
    public static void read(final ObjectInputStream in, final Object object)
            throws ClassNotFoundException, IOException {
        final byte[] inputStreamBytes = (byte[]) in.readObject();
        PifObjectMapper.getInstance().readerForUpdating(object).readValue(inputStreamBytes);
    }

    // Make sure that objects of this class cannot be instantiated
    private PifSerializationUtil() {}
}