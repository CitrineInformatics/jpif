package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.citrine.jpif.util.PifSerializationUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Information about a software package.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>name - Name of the software.
 *     <li>version - Version of the software.
 *     <li>producer - Producer of the software.
 *     <li>url - URL at which the software can be accessed.
 *     <li>tags - List of tags that apply to the software.
 * </ul>
 * @author Kyle Michel
 */
public class Software extends Pio implements Serializable {

    /**
     * Set the name of the software package.
     *
     * @param name String with the name of the software package.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public Software setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the software package.
     *
     * @return Name of the software package.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Get the version of the software package.
     *
     * @param version String with the version of the software package.
     * @return This object.
     */
    @JsonSetter(value = "version")
    public Software setVersion(final String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the version of the software package.
     *
     * @return Version of the software package.
     */
    @JsonGetter(value = "version")
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the producer of the software package.
     *
     * @param producer String with the producer of the software package.
     * @return This object.
     */
    @JsonSetter(value = "producer")
    public Software setProducer(final String producer) {
        this.producer = producer;
        return this;
    }

    /**
     * Get the producer of the software package.
     *
     * @return Producer of the software package.
     */
    @JsonGetter(value = "producer")
    public String getProducer() {
        return this.producer;
    }

    /**
     * Set the URL to the software package website.
     *
     * @param url String with the URL to the software package website.
     * @return This object.
     */
    @JsonSetter(value = "url")
    public Software setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the URL to the software package website.
     *
     * @return URL to the software package website.
     */
    @JsonGetter(value = "url")
    public String getUrl() {
        return this.url;
    }

    @Override
    public Software addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Software addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Software addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /**
     * Write this object to the output output stream.
     *
     * @param out {@link ObjectOutputStream} to write to.
     * @throws IOException if this object cannot be written.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        PifSerializationUtil.write(out, this);
    }

    /**
     * Read into this object from the input stream.
     *
     * @param in {@link ObjectInputStream} to read from.
     * @throws IOException if thrown while reading the stream.
     * @throws ClassNotFoundException if thrown while reading the stream.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        PifSerializationUtil.read(in, this);
    }

    /**
     * Read an object with no data.
     *
     * @throws ObjectStreamException if thrown while reading the stream.
     */
    private void readObjectNoData() throws ObjectStreamException {}

    private static final long serialVersionUID = 3894059382410712736L;

    /** Name of the software package. */
    private String name;

    /** Version of the software package. */
    private String version;

    /** Producer of the software package. */
    private String producer;

    /** URL to the software package. */
    private String url;
}