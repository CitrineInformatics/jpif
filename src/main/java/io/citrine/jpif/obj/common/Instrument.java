package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Description of an instrument used in an experimental measurement.
 *
 * <p>
 * Supported fields:
 * <ul>
 *     <li>name - Name of the instrument.
 *     <li>model - Model of the instrument.
 *     <li>producer - Producer of the instrument.
 *     <li>url - URL to the website with information about the instrument.
 * </ul>
 *
 * @author Kyle Michel
 */
public class Instrument extends Pio {

    /**
     * Set the name of the instrument.
     *
     * @param name String with the name of the instrument.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public Instrument setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of the instrument.
     *
     * @return Name of the instrument.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the model of the instrument.
     *
     * @param model String with the name of the model.
     * @return This object.
     */
    @JsonSetter(value = "model")
    public Instrument setModel(final String model) {
        this.model = model;
        return this;
    }

    /**
     * Get the model of the instrument.
     *
     * @return Model of the instrument.
     */
    @JsonGetter(value = "model")
    public String getModel() {
        return this.model;
    }

    /**
     * Set the producer of the instrument.
     *
     * @param producer String with the producer of the instrument.
     * @return This object.
     */
    @JsonSetter(value = "producer")
    public Instrument setProducer(final String producer) {
        this.producer = producer;
        return this;
    }

    /**
     * Get the name of the producer of the instrument.
     *
     * @return Producer of the instrument.
     */
    @JsonGetter(value = "producer")
    public String getProducer() {
        return this.producer;
    }

    /**
     * Set the URL to the website with information about the instrument.
     *
     * @param url String with the URL to the website with information about the instrument.
     * @return This object.
     */
    @JsonSetter(value = "url")
    public Instrument setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the URL to the website with information about the instrument.
     *
     * @return URL of the website for the instrument.
     */
    @JsonGetter(value = "url")
    public String getUrl() {
        return this.url;
    }

    @Override
    @JsonAnySetter
    public Instrument addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Name of the instrument. */
    private String name;

    /** Model of the instrument. */
    private String model;

    /** Producer of the instrument. */
    private String producer;

    /** URL to the instrument website. */
    private String url;
}