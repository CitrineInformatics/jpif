package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.citrine.jpif.util.PifObjectMapper;
import io.citrine.jpif.util.PifSerializationUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Information about a file.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>relativePath - String with the relative path (from the location of this file) of the file.
 *     <li>mimeType - String with the mimetype of the file.
 *     <li>sha256 - String with the SHA-256 hash of the file.
 *     <li>md5 - String with the MD5 hash of the file.
 *     <li>tags - List of tags that apply to the file reference.
 * </ul>
 *
 * @author Kyle Michel
 */
public class FileReference extends Pio implements Serializable {

    /**
     * Set the relative path of the file.
     *
     * @param relativePath String with the relative path of the file.
     * @return This object.
     */
    @JsonSetter(value = "relativePath")
    public FileReference setRelativePath(final String relativePath) {
        this.relativePath = relativePath;
        return this;
    }

    /**
     * Get the relative path of the file.
     *
     * @return String with the relative path of the file.
     */
    @JsonGetter(value = "relativePath")
    public String getRelativePath() {
        return this.relativePath;
    }

    /**
     * Set the url pointing to the file.
     *
     * @param url String with the url pointing to the file.
     * @return This object.
     */
    @JsonSetter(value = "url")
    public FileReference setUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the url string value.
     *
     * @return String with the url pointing to the file.
     */
    @JsonGetter(value = "url")
    public String getUrl() {
        return this.url;
    }

    /**
     * Set the mime type of the file.
     *
     * @param mimeType String with the mime type of the file.
     * @return This object.
     */
    @JsonSetter(value = "mimeType")
    public FileReference setMimeType(final String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    /**
     * Get the mime type of the file.
     *
     * @return String with the mime type of the file.
     */
    @JsonGetter(value = "mimeType")
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Set the SHA-256 hash of the file.
     *
     * @param sha256 String with the SHA-256 hash of the file.
     * @return This object.
     */
    @JsonSetter(value = "sha256")
    public FileReference setSha256(final String sha256) {
        this.sha256 = sha256;
        return this;
    }

    /**
     * Get the SHA-256 hash of the file.
     *
     * @return String with the SHA-256 hash of the file.
     */
    @JsonGetter(value = "sha256")
    public String getSha256() {
        return this.sha256;
    }

    /**
     * Set the MD5 hash of the file.
     *
     * @param md5 String with the MD5 hash of the file.
     * @return This object.
     */
    @JsonSetter(value = "md5")
    public FileReference setMd5(final String md5) {
        this.md5 = md5;
        return this;
    }

    /**
     * Get the MD5 hash of the file.
     *
     * @return String with the MD5 hash of the file.
     */
    @JsonGetter(value = "md5")
    public String getMd5() {
        return this.md5;
    }

    /**
     * Generate a new {@link FileReference} object from a string. This method just creates an object with the
     * relative path of file set with the input string.
     *
     * @param value String that represents the file.
     * @return {@link FileReference} object.
     */
    public static FileReference valueOf(final String value) {
        return (value == null)
                ? null
                : new FileReference().setRelativePath(value);
    }

    @Override
    public FileReference addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public FileReference addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public FileReference addUnsupportedField(final String key, final Object value) {
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

    private static final long serialVersionUID = 2550510340810945282L;

    /** String with the relative path of the file. */
    private String relativePath;

    /** String with the mime type of the file. */
    private String mimeType;

    /** String with the SHA-256 hash of the file. */
    private String sha256;

    /** String with the MD5 hash of the file. */
    private String md5;

    /** String with the url pointing to the file reference. */
    private String url;

    /**
     * Class used to deserialize a JSON value into a {@link FileReference} object. If the input value is a string,
     * this function saves it as the relative path of the file. If the input value is an object, then it is converted
     * directly to a {@link FileReference} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<FileReference> {

        @Override
        public FileReference deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return FileReference.valueOf(jsonParser.getValueAsString());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, FileReference.class);
                default:
                    throw deserializationContext.mappingException(FileReference.class, jsonToken);
            }
        }
    }
}