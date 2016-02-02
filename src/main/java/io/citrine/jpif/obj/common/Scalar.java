package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of a single scalar value that could represent an absolute point, an uncertain point, a range of
 * values, a minimum, or a maximum.
 *
 * @author Kyle Michel
 */
public class Scalar extends Pio {

    /**
     * Set the exact value.
     *
     * @param value String with the exact value.
     * @return This object.
     */
    @JsonSetter(value = "value")
    public Scalar setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * Get the exact value.
     *
     * @return String with the exact value.
     */
    @JsonGetter(value = "value")
    public String getValue() {
        return this.value;
    }

    /**
     * Set the minimum of the value.
     *
     * @param minimum String with the minimum of the value.
     * @return This object.
     */
    @JsonSetter(value = "minimum")
    public Scalar setMinimum(final String minimum) {
        this.minimum = minimum;
        return this;
    }

    /**
     * Get the minimum of the value.
     *
     * @return String with the minimum of the value.
     */
    @JsonGetter(value = "minimum")
    public String getMinimum() {
        return this.minimum;
    }

    /**
     * Set whether the minimum value is inclusive.
     *
     * @param inclusiveMinimum True if the minimum value is inclusive.
     * @return This object.
     */
    @JsonSetter(value = "inclusiveMinimum")
    public Scalar setInclusiveMinimum(final Boolean inclusiveMinimum) {
        this.inclusiveMinimum = inclusiveMinimum;
        return this;
    }

    /**
     * Get whether the minimum value is inclusive.
     *
     * @return True if the value in inclusive.
     */
    @JsonGetter(value = "inclusiveMinimum")
    private Boolean getInclusiveMinimum() { // Private since only Jackson should use it
        return this.inclusiveMinimum;
    }

    /**
     * Get whether the minimum value is inclusive. This will return false unless the minimum has explicitly been
     * set as being inclusive.
     *
     * @return True only if the minimum is inclusive.
     */
    public boolean isInclusiveMinimum() {
        return (this.inclusiveMinimum == null) ? false : this.inclusiveMinimum;
    }

    /**
     * Set the maximum of the value.
     *
     * @param maximum String with the maximum of the value.
     * @return This object.
     */
    @JsonSetter(value = "maximum")
    public Scalar setMaximum(final String maximum) {
        this.maximum = maximum;
        return this;
    }

    /**
     * Get the maximum of the value.
     *
     * @return String with the maximum of the value.
     */
    @JsonGetter(value = "maximum")
    public String getMaximum() {
        return this.maximum;
    }

    /**
     * Set whether the maximum of the value in inclusive.
     *
     * @param inclusiveMaximum True of the value of the maximum is inclusive.
     * @return This object.
     */
    @JsonSetter(value = "inclusiveMaximum")
    public Scalar setInclusiveMaximum(final Boolean inclusiveMaximum) {
        this.inclusiveMaximum = inclusiveMaximum;
        return this;
    }

    /**
     * Get whether the maximum of the value in inclusive.
     *
     * @return True if the value of the maximum is inclusive.
     */
    @JsonGetter(value = "inclusiveMaximum")
    private Boolean getInclusiveMaximum() { // Private since only Jackson should use it
        return this.inclusiveMaximum;
    }

    /**
     * Get whether the maximum value is inclusive. This will return false unless the maximum has explicitly been
     * set as being inclusive.
     *
     * @return True only if the maximum is inclusive.
     */
    public boolean isInclusiveMaximum() {
        return (this.inclusiveMaximum == null) ? false : this.inclusiveMaximum;
    }

    /**
     * Set the uncertainty of the value.
     *
     * @param uncertainty String with the uncertainty of the value.
     * @return This object.
     */
    @JsonSetter(value = "uncertainty")
    public Scalar setUncertainty(final String uncertainty) {
        this.uncertainty = uncertainty;
        return this;
    }

    /**
     * Get the uncertainty of the value.
     *
     * @return String with the uncertainty of the value.
     */
    @JsonGetter(value = "uncertainty")
    public String getUncertainty() {
        return this.uncertainty;
    }

    @Override
    @JsonAnySetter
    public Scalar addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public Scalar removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public Scalar clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /**
     * Save the input number as a value. This saves the number in the "value" field.
     *
     * @param input Number with the input value to save.
     * @return New {@link Scalar} object with the input number.
     */
    public static Scalar valueOf(final Number input) {
        return (input == null)
                ? null
                : new Scalar().setValue(input.toString());
    }

    /**
     * Break the input string into value, minimum, maximum, and uncertainty.
     *
     * @param input String to save.
     * @return New {@link Scalar} object with the input string decomposed.
     */
    public static Scalar valueOf(final String input) {
        return (input == null)
                ? null
                : decomposeString(input);
    }

    /**
     * Break apart the input string into value, minimum, maximum, and uncertainty.
     *
     * @param input String to save.
     * @return New {@link Scalar} object with the input string decomposed.
     */
    protected static Scalar decomposeString(final String input) {
        Scalar res;
        if (((res = asPlusMinus(input)) != null) ||
                ((res = asParentheses(input)) != null) ||
                ((res = asBoundedRange(input)) != null) ||
                ((res = asMinimum(input)) != null) ||
                ((res = asInclusiveMinimum(input)) != null) ||
                ((res = asMaximum(input)) != null) ||
                ((res = asInclusiveMaximum(input)) != null)) {
            return res;
        }
        return new Scalar().setValue(input);
    }

    /**
     * Try to interpret the input string as a value with a +- uncertainty.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asPlusMinus(final String input) {
        final Matcher matcher = PLUS_MINUS_UNCERTAINTY_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Scalar()
                    .setValue(matcher.group(1))
                    .setUncertainty(matcher.group(2));
        }
        return null;
    }

    /**
     * Try to interpret the input string as a value with an uncertainty inside of parentheses.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asParentheses(final String input) {
        final Matcher matcher = PARENTHESES_UNCERTAINTY_PATTERN.matcher(input);
        if (matcher.matches()) {
            try {
                final String base = matcher.group(1);
                String uncertainty = matcher.group(2);
                final int decimalIndex = base.indexOf(".");
                if (decimalIndex != -1) {
                    final int numToPad = (base.length() - decimalIndex - 1) - uncertainty.length();
                    final char[] padding = new char[numToPad];
                    Arrays.fill(padding, '0');
                    uncertainty = "0." + String.valueOf(padding) + uncertainty;
                }
                return new Scalar()
                        .setValue(base)
                        .setUncertainty(uncertainty);
            }
            catch (Exception e) {
                // Ignore this exception and just return that the string did not fit the correct format
            }
        }
        return null;
    }

    /**
     * Try to interpret the input string as a bounded range.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asBoundedRange(final String input) {
        final Matcher matcher = RANGE_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Scalar()
                    .setMinimum(matcher.group(1))
                    .setMaximum(matcher.group(2));
        }
        return null;
    }

    /**
     * Try to interpret the input string as a minimum.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asMinimum(final String input) {
        final Matcher matcher = MINIMUM_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Scalar().setMinimum(matcher.group(1));
        }
        return null;
    }

    /**
     * Try to interpret the input string as an inclusive minimum.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asInclusiveMinimum(final String input) {
        final Matcher matcher = INCLUSIVE_MINIMUM_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Scalar()
                    .setMinimum(matcher.group(1))
                    .setInclusiveMinimum(true);
        }
        return null;
    }

    /**
     * Try to interpret the input string as a maximum.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asMaximum(final String input) {
        final Matcher matcher = MAXIMUM_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Scalar().setMaximum(matcher.group(1));
        }
        return null;
    }

    /**
     * Try to interpret the input string as an inclusive maximum.
     *
     * @param input String with the value to interpret.
     * @return New {@link Scalar} object or a null pointer if the input string was not in the correct format.
     */
    protected static Scalar asInclusiveMaximum(final String input) {
        final Matcher matcher = INCLUSIVE_MAXIMUM_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Scalar()
                    .setMaximum(matcher.group(1))
                    .setInclusiveMaximum(true);
        }
        return null;
    }

    /** Exact value. */
    private String value;

    /** Minimum of the value. */
    private String minimum;

    /** Whether or not the minimum value in inclusive. */
    private Boolean inclusiveMinimum;

    /** Maximum of the value. */
    private String maximum;

    /** Whether or not the maximum value in inclusive. */
    private Boolean inclusiveMaximum;

    /** Uncertainty of the value. */
    private String uncertainty;

    /** Regular expression to match a digit. */
    private static final String DIGITS_REGEX = "(?:\\p{Digit}+)";

    /** Regular expression to match a hex digit. */
    private static final String HEX_DIGITS_REGEX = "(?:\\p{XDigit}+)";

    /** Regular expression to match an exponential. */
    private static final String EXP_REGEX = "[eE][+-]?" + DIGITS_REGEX;

    /** Regular expression to match a number. This is taken from the javadoc for java.lang.Double.valueOf(). */
    private static final String NUMBER_REGEX = "[+-]?(?:NaN|Infinity|(?:(?:(?:" + DIGITS_REGEX + "(?:\\.)?(?:"
            + DIGITS_REGEX + "?)(?:" + EXP_REGEX + ")?)|(?:\\.(?:" + DIGITS_REGEX + ")(?:" + EXP_REGEX
            + ")?)|(?:(?:" + "(?:0[xX]" + HEX_DIGITS_REGEX + "(?:\\.)?)|(?:0[xX]" + HEX_DIGITS_REGEX + "?(?:\\.)"
            + DIGITS_REGEX + ")" + ")[pP][+-]?" + DIGITS_REGEX + "))[fFdD]?))";

    /** Regular expression for matching +- symbols. */
    private static final String PLUS_MINUS_REGEX = "\\s*(?:\\+\\-|±|\\$?\\\\pm\\$?)\\s*";

    /** Pattern for matching range in the form e.g. 1 +- 0.1. */
    private static final Pattern PLUS_MINUS_UNCERTAINTY_PATTERN = Pattern.compile(
            "^\\s*(" + NUMBER_REGEX + ")" + PLUS_MINUS_REGEX + "(" + NUMBER_REGEX + ")\\s*$");

    /** Pattern for matching uncertainty in the form of e.g. 1.051(13). */
    private static final Pattern PARENTHESES_UNCERTAINTY_PATTERN = Pattern.compile(
            "^\\s*(?:(" + NUMBER_REGEX + ")\\(([0-9]+)\\))\\s*$");

    /** Regular expression for matching symbol in bounded ranges. */
    private static final String RANGE_REGEX = "\\s*(?:-|–|to)\\s*";

    /** Pattern for matching ranges in the form of e.g. 1.3-1.5. */
    private static final Pattern RANGE_PATTERN = Pattern.compile(
            "^\\s*(" + NUMBER_REGEX + ")" + RANGE_REGEX + "(" + NUMBER_REGEX + ")\\s*$");

    /** Regular expression for matching minimum values. */
    private static final String MINIMUM_REGEX = "\\s*(?:>|\\$\\\\gt\\$|\\\\gt)\\s*";

    /** Pattern for matching minimum values. */
    private static final Pattern MINIMUM_PATTERN = Pattern.compile(
            "^" + MINIMUM_REGEX + "(" + NUMBER_REGEX + ")\\s*$");

    /** Regular expression for matching inclusive minimum values. */
    private static final String INCLUSIVE_MINIMUM_REGEX = "\\s*(?:>=|≥|\\$\\\\geq?\\$|\\\\geq?)\\s*";

    /** Pattern for matching inclusive minimum values. */
    private static final Pattern INCLUSIVE_MINIMUM_PATTERN = Pattern.compile(
            "^" + INCLUSIVE_MINIMUM_REGEX + "(" + NUMBER_REGEX + ")\\s*$");

    /** Regular expression for matching maximum values. */
    private static final String MAXIMUM_REGEX = "\\s*(?:<|\\$\\\\lt\\$|\\\\lt)\\s*";

    /** Pattern for matching maximum values. */
    private static final Pattern MAXIMUM_PATTERN = Pattern.compile(
            "^" + MAXIMUM_REGEX + "(" + NUMBER_REGEX + ")\\s*$");

    /** Regular expression for matching inclusive maximum values. */
    private static final String INCLUSIVE_MAXIMUM_REGEX = "\\s*(?:<=|≤|\\$\\\\leq?\\$|\\\\leq?)\\s*";

    /** Pattern for matching inclusive maximum values. */
    private static final Pattern INCLUSIVE_MAXIMUM_PATTERN = Pattern.compile(
            "^" + INCLUSIVE_MAXIMUM_REGEX + "(" + NUMBER_REGEX + ")\\s*$");

    /**
     * Class used to deserialize a JSON value into a {@link Scalar} object. If the input token is a string or number
     * then it is saved as the value of the {@link Scalar} object. If the input token is an object, then it is
     * converted directly to an {@link Scalar} object.
     *
     * @author Kyle Michel
     */
    public static class Deserializer extends JsonDeserializer<Scalar> {

        @Override
        public Scalar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonToken jsonToken = jsonParser.getCurrentToken();
            switch (jsonToken) {
                case VALUE_STRING:
                    return Scalar.valueOf(jsonParser.getValueAsString());
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return Scalar.valueOf(jsonParser.getNumberValue());
                case START_OBJECT:
                    return OBJECT_MAPPER.readValue(jsonParser, Scalar.class);
                default:
                    throw deserializationContext.mappingException(Scalar.class, jsonToken);
            }
        }
    }
}