package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Representation of a display item (table or figure)
 *
 * <p>Supported fields:
 * <ul>
 *     <li>number - Figure or table number.
 *     <li>title - Title of the item.
 *     <li>caption - Caption for the item.
 *     <li>tags - List of tags that apply to the item.
 * </ul>
 *
 * @author Kyle Michel
 */
public class DisplayItem extends Pio {

    /**
     * Set the number of the display item.
     *
     * @param number String with the number of the display item.
     * @return This object.
     */
    @JsonSetter(value = "number")
    public DisplayItem setNumber(final String number) {
        this.number = number;
        return this;
    }

    /**
     * Get the number of the display item.
     *
     * @return String with the number of the display item.
     */
    @JsonGetter(value = "number")
    public String getNumber() {
        return this.number;
    }

    /**
     * Set the title of the display item.
     *
     * @param title String with the title of the display item.
     * @return This object.
     */
    @JsonSetter(value = "title")
    public DisplayItem setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the title of the display item.
     *
     * @return String with the title of the display item.
     */
    @JsonGetter(value = "title")
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the caption of the display item.
     *
     * @param caption String with the caption of the display item.
     * @return This object.
     */
    @JsonSetter(value = "caption")
    public DisplayItem setCaption(final String caption) {
        this.caption = caption;
        return this;
    }

    /**
     * Get the caption of the display item.
     *
     * @return String with the caption of the display item.
     */
    @JsonGetter(value = "caption")
    public String getCaption() {
        return this.caption;
    }

    @Override
    public DisplayItem addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public DisplayItem addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public DisplayItem addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /** Figure or table number. */
    private String number;

    /** Title of the item. */
    private String title;

    /** Caption for the item. */
    private String caption;
}