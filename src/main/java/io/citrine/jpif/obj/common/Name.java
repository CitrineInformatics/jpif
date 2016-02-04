package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.citrine.jpif.util.PifObjectMapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of the first and last name of a person.
 *
 * @author Kyle Michel
 */
public class Name extends Pio {

    /**
     * Set the title of the person.
     *
     * @param title String with the title of the person.
     * @return This object.
     */
    @JsonSetter(value = "title")
    public Name setTitle(final String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the title of the person.
     *
     * @return String with the title of the person.
     */
    @JsonGetter(value = "title")
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the given (first) name of a person.
     *
     * @param given String with the given name of the person.
     * @return This object.
     */
    @JsonSetter(value = "given")
    public Name setGiven(final String given) {
        this.given = given;
        return this;
    }

    /**
     * Get the given (first) name of a person.
     *
     * @return String with the given name of the person.
     */
    @JsonGetter(value = "given")
    public String getGiven() {
        return this.given;
    }

    /**
     * Set the family (last) name of a person.
     *
     * @param family String with the family name of the person.
     * @return This object.
     */
    @JsonSetter(value = "family")
    public Name setFamily(final String family) {
        this.family = family;
        return this;
    }

    /**
     * Get the family (last) name of a person.
     *
     * @return String with the family name of a person.
     */
    @JsonGetter(value = "family")
    public String getFamily() {
        return this.family;
    }

    /**
     * Set the suffix of the person.
     *
     * @param suffix String with the suffix of the person.
     * @return This object.
     */
    @JsonSetter(value = "suffix")
    public Name setSuffix(final String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * Get the suffix of the person.
     *
     * @return String with the suffix of the person.
     */
    @JsonGetter(value = "suffix")
    public String getSuffix() {
        return this.suffix;
    }

    @Override
    @JsonAnySetter
    public Name addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public Name removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public Name clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /**
     * Generate a new {@link Name} object from the input string. This function goes through a set of heuristics to
     * determine the title, given, family, and suffix of the name.
     *
     * @param input String with the value to convert to a name.
     * @return New {@link Name} object with the decomposed input string.
     */
    public static Name valueOf(final String input) {
        return (input == null)
                ? null
                : decomposeName(input);
    }

    /**
     * Generate a new {@link Name} object from the input string. This function goes through a set of heuristics to
     * determine the title, given, family, and suffix of the name.
     *
     * @param input String with the value to convert to a name.
     * @return New {@link Name} object with the decomposed input string.
     */
    protected static Name decomposeName(String input) {
        final Name name = new Name();
        input = addTitleToName(input, name);
        input = addSuffixToName(input, name);
        addGivenAndFamilyNames(input, name);
        return name;
    }

    /**
     * Determine whether the input string contains a title and save if so.
     *
     * @param input String with the full input name being decomposed.
     * @param name {@link Name} object to save to.
     * @return Modified input string with the title removed.
     */
    protected static String addTitleToName(String input, final Name name) {
        final Matcher matcher = TITLES_PATTERN.matcher(input);
        while (matcher.find()) {
            name.setTitle(matcher.group().trim());
            input = input.replaceFirst(name.getTitle(), "");
        }
        return input;
    }

    /**
     * Determine whether the input string contains a suffix and save if so.
     *
     * @param input String with the name being decomposed.
     * @param name {@link Name} object to save to.
     * @return Modified input string with the suffix removed.
     */
    protected static String addSuffixToName(String input, final Name name) {
        final Matcher matcher = SUFFIXES_PATTERN.matcher(input);
        while (matcher.find()) {
            name.setSuffix(matcher.group().trim());
            input = input.replaceFirst(name.getSuffix(), "");
        }
        return input;
    }

    /**
     * Break the input string into given and family names. This function first checks whether the input string contains
     * lastName, firstName and otherwise falls back on further processing.
     *
     * @param input String with the name to convert to given and family parts.
     * @param name {@link Name} object to save to.
     */
    protected static void addGivenAndFamilyNames(String input, final Name name) {
        input = TRAILING_CHAR_PATTERN.matcher(input).replaceAll("").trim();
        final String[] parts = NAME_SPLIT_PATTERN.split(input);
        if (parts.length == 2) {
            name.setGiven(parts[1].trim());
            name.setFamily(parts[0].trim());
        }
        else {
            splitNameAtSpaces(input, name);
        }
    }

    /**
     * Break the input string into words and try to determine which are part of the given or family names.
     *
     * @param input String with the name to convert.
     * @param name {@link Name} object to save to.
     */
    protected static void splitNameAtSpaces(String input, final Name name) {
        input = HYPHEN_REGEX.matcher(input).replaceAll("-");
        final String[] partsOfName = input.split("\\s+");
        if (partsOfName.length == 1) {
            name.setFamily(partsOfName[0].trim());
        }
        else if (partsOfName.length == 2) {
            name.setGiven(partsOfName[0].trim());
            name.setFamily(partsOfName[1].trim());
        }
        else if (partsOfName.length > 2) {
            identifyPartsOfMultiWordName(partsOfName, name);
        }
    }

    /**
     * Identify the parts of a name when there are more than 2 words in it. If the list of words starts with
     * abbreviations, this function takes all words as part of the given name until one is found that is not
     * abbreviated. Otherwise, this function takes all words except the last as part of the given name.
     *
     * @param partsOfName Array of words in the name.
     * @param name {@link Name} object being built.
     */
    protected static void identifyPartsOfMultiWordName(final String[] partsOfName, final Name name) {
        int pos = 0;
        for (; pos < partsOfName.length; ++pos) {
            if ((!partsOfName[pos].endsWith(".")) && (partsOfName[pos].length() > 1)) {
                break;
            }
        }
        if ((pos == 0) || (pos == partsOfName.length)) {
            name.setGiven(join(partsOfName, 0, partsOfName.length - 1));
            name.setFamily(partsOfName[partsOfName.length - 1]);
        }
        else {
            name.setGiven(join(partsOfName, 0, pos));
            name.setFamily(join(partsOfName, pos, partsOfName.length));
        }
    }

    /**
     * Join a list of strings separated by a space.
     *
     * @param strings Array of strings to join.
     * @param start Index of the first string to join.
     * @param end Index after the last string to join.
     * @return String with the combined values from input string.
     */
    protected static String join(final String[] strings, final int start, final int end) {
        StringBuilder res = new StringBuilder();
        for (int i = start; (i < end) && (i < strings.length); ++i) {
            if (strings[i] != null) {
                if (res.length() > 0) {
                    res.append(' ');
                }
                res.append(strings[i]);
            }
        }
        return res.toString();
    }

    /** Title of the person. */
    private String title;

    /** Given name of the person. */
    private String given;

    /** Family name of the person. */
    private String family;

    /** Suffix of the person. */
    private String suffix;

    /** List of supported titles. */
    private static final String TITLES =
            "Ms[\\. ]|Miss |Mrs[\\. ]|Mr[\\. ]|Dr[\\. ]|Doctor |Prof[\\. ]|Professor ";

    /** Regular expression to get title of a person. */
    private static final Pattern TITLES_PATTERN =
            Pattern.compile("(?<!\\p{Alpha})(" + TITLES + ")", Pattern.CASE_INSENSITIVE);

    /** List of supported suffixes. */
    private static final String SUFFIXES =
            "II|III|IV|Jr\\.?|Sr\\.?|J\\.?D\\.?|Ph\\.?D\\.?|M\\.?B\\.?A\\.?";

    /** Regular expression to get suffix of a person. */
    private static final Pattern SUFFIXES_PATTERN =
            Pattern.compile("(?<!\\p{Alpha})(" + SUFFIXES + ")(?!\\p{Alpha})", Pattern.CASE_INSENSITIVE);

    /** Regular expression to split a name at a comma or semicolon. */
    private static final Pattern NAME_SPLIT_PATTERN = Pattern.compile("[,;]");

    /** Regular expression to match spaces and commas at the end of a string. */
    private static final Pattern TRAILING_CHAR_PATTERN = Pattern.compile("([, ]+)$");

    /** Regular expression to match a hyphen with or without whitespace around it. */
    private static final Pattern HYPHEN_REGEX = Pattern.compile("\\s*\\-+\\s*");

    /**
     * Class used to deserialize a JSON value into a {@link Name} object. If the input token is a string then an
     * attempt is made to decompose it into components. If the input token is an object, then it is converted directly
     * to a {@link Name} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<Name> {

        @Override
        public Name deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return Name.valueOf(jsonParser.getValueAsString());
                case START_OBJECT:
                    return PifObjectMapper.getInstance().readValue(jsonParser, Name.class);
                default:
                    throw deserializationContext.mappingException(Name.class, jsonToken);
            }
        }
    }
}