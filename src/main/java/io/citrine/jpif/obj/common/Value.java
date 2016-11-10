package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.citrine.jpif.util.PifObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Information about a scalar, vector, or matrix, or a list of one of those.
 *
 * <p>Supported fields:
 * <ul>
 *     <li>name - Name of the value.
 *     <li>scalars - List of {@link Scalar}s representing scalar values.
 *     <li>vectors - List of arrays of {@link Scalar}s, each representing a vector.
 *     <li>matrices - List of arrays of arrays of {@link Scalar}s, each representing a matrix.
 *     <li>units - Units of the values.
 *     <li>tags - List of tags that apply to the value.
 * </ul>
 * @author Kyle Michel
 */
public class Value extends Pio {

    /**
     * Set the name of this value.
     *
     * @param name String with the name of this value.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public Value setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the name of this value.
     *
     * @param names String with the name of this value.
     */
    @JsonSetter(value = "names")
    protected void setNames(final String names) { // Private since only Jackson should use it
        this.setName(names);
    }

    /**
     * Get the name of this value.
     *
     * @return String with the name of this value.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the list of scalar values.
     *
     * @param scalars List of {@link Scalar} objects.
     */
    @JsonSetter(value = "scalars")
    @JsonDeserialize(contentUsing = Scalar.Deserializer.class)
    protected void setScalars(final List<Scalar> scalars) { // Private since only Jackson should use it
        this.scalars = scalars;
    }

    /**
     * Set the list of scalar values.
     *
     * @param scalar List of {@link Scalar} objects.
     */
    @JsonSetter(value = "scalar")
    @JsonDeserialize(contentUsing = Scalar.Deserializer.class)
    protected void setScalar(final List<Scalar> scalar) { // Private since only Jackson should use it
        setScalars(scalar);
    }

    /**
     * Add a scalar to this value.
     *
     * @param scalar {@link Scalar} object to add to this value.
     * @return This object.
     */
    public Value addScalar(final Scalar scalar) {
        if (this.scalars == null) {
            this.scalars = new ArrayList<>();
        }
        this.scalars.add(scalar);
        return this;
    }

    /**
     * Insert a single scalar for this value.
     *
     * @param index Index at which to insert the input scalar.
     * @param scalar {@link Scalar} object to add to this value.
     * @return This object.
     */
    public Value addScalar(final int index, final Scalar scalar) {
        if (this.scalars == null) {
            this.scalars = new ArrayList<>();
        }
        this.scalars.add(index, scalar);
        return this;
    }

    /**
     * Add a string to this value. This function uses {@link Scalar#valueOf(String)} to convert the string to a
     * {@link Scalar} object.
     *
     * @param scalar String to add as a scalar.
     * @return This object.
     */
    public Value addScalar(final String scalar) {
        return addScalar(Scalar.valueOf(scalar));
    }

    /**
     * Insert a single scalar for this value.
     *
     * @param index Index at which to insert the input scalar.
     * @param scalar String to add to this value.
     * @return This object.
     */
    public Value addScalar(final int index, final String scalar) {
        return addScalar(index, Scalar.valueOf(scalar));
    }

    /**
     * Add a number to this value. This function uses {@link Scalar#valueOf(Number)} to convert the number to a
     * {@link Scalar} object.
     *
     * @param scalar Number to add as a scalar.
     * @return This object.
     */
    public Value addScalar(final Number scalar) {
        return addScalar(Scalar.valueOf(scalar));
    }

    /**
     * Insert a single scalar for this value.
     *
     * @param index Index at which to insert the input scalar.
     * @param scalar Number to add to this value.
     * @return This object.
     */
    public Value addScalar(final int index, final Number scalar) {
        return addScalar(index, Scalar.valueOf(scalar));
    }

    /**
     * Remove a scalar from the value.
     *
     * @param scalar {@link Scalar} object to delete.
     * @return True if the object was removed.
     */
    public boolean removeScalar(final Scalar scalar) {
        return (this.scalars != null) && this.scalars.remove(scalar);
    }

    /**
     * Get the number of scalars stored in this value.
     *
     * @return Number of scalars stored in this value.
     */
    public int numScalars() {
        return (this.scalars == null) ? 0 : this.scalars.size();
    }

    /**
     * Get a scalar value at a set index.
     *
     * @param index Index of the scalar to get.
     * @return {@link Scalar} object at the set index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the scalar list.
     */
    @JsonIgnore
    public Scalar getScalar(final int index) {
        if (this.scalars == null) {
            throw new IndexOutOfBoundsException("Attempting to access scalar " + index + " of " + this.numScalars());
        }
        return this.scalars.get(index);
    }

    /**
     * Get an {@link Iterable} object over the scalars stored in this value.
     *
     * @return {@link Iterable} object for iterating over the scalars in this value.
     */
    public Iterable<Scalar> scalars() {
        return (this.scalars == null) ? Collections.emptyList() : this.scalars;
    }

    /**
     * Get the full list of scalar values.
     *
     * @return List of {@link Scalar} objects stored in this value.
     */
    @JsonGetter(value = "scalars")
    protected List<Scalar> getScalars() { // Private since only Jackson should use it
        return this.scalars;
    }

    /**
     * Set the list of vectors stored by this value.
     *
     * @param vectors List of {@link Scalar} arrays that represent the vectors stored by this value.
     */
    @JsonSetter(value = "vectors")
    @JsonDeserialize(contentUsing = VectorsDeserializer.class)
    protected void setVectors(final List<Scalar[]> vectors) { // Private since only Jackson should use it
        this.vectors = vectors;
    }

    /**
     * Set the list of vectors stored by this value.
     *
     * @param vector List of {@link Scalar} arrays that represent the vectors stored by this value.
     */
    @JsonSetter(value = "vector")
    @JsonDeserialize(contentUsing = VectorsDeserializer.class)
    protected void setVector(final List<Scalar[]> vector) { // Private since only Jackson should use it
        setVectors(vector);
    }

    /**
     * Add a single vector to this value.
     *
     * @param vector {@link Scalar} array with the vector to add.
     * @return This object.
     */
    public Value addVector(final Scalar[] vector) {
        if (this.vectors == null) {
            this.vectors = new ArrayList<>();
        }
        this.vectors.add(vector);
        return this;
    }

    /**
     * Insert a single vector for this value.
     *
     * @param index Index at which to insert the input vector.
     * @param vector {@link Scalar} array to add to this value.
     * @return This object.
     */
    public Value addVector(final int index, final Scalar[] vector) {
        if (this.vectors == null) {
            this.vectors = new ArrayList<>();
        }
        this.vectors.add(index, vector);
        return this;
    }

    /**
     * Add a string vector to this value. This function uses {@link Scalar#valueOf(String)} to convert each string to a
     * {@link Scalar} object.
     *
     * @param vector String array with the vector to add.
     * @return This object.
     */
    public Value addVector(final String[] vector) {
        return addVector(toScalarVector(vector));
    }

    /**
     * Insert a single vector for this value.
     *
     * @param index Index at which to insert the input vector.
     * @param vector String array to add to this value.
     * @return This object.
     */
    public Value addVector(final int index, final String[] vector) {
        return addVector(index, toScalarVector(vector));
    }

    /**
     * Add a numeric vector to this value. This function uses {@link Scalar#valueOf(Number)} to convert each number to a
     * {@link Scalar} object.
     *
     * @param vector Numeric array with the vector to add.
     * @return This object.
     */
    public Value addVector(final Number[] vector) {
        return addVector(toScalarVector(vector));
    }

    /**
     * Insert a single vector for this value.
     *
     * @param index Index at which to insert the input vector.
     * @param vector Number array to add to this value.
     * @return This object.
     */
    public Value addVector(final int index, final Number[] vector) {
        return addVector(index, toScalarVector(vector));
    }

    /**
     * Remove a vector from the value.
     *
     * @param vector {@link Scalar} array object to delete.
     * @return True if the object was removed.
     */
    public boolean removeVector(final Scalar[] vector) {
        return (this.vectors != null) && this.vectors.remove(vector);
    }

    /**
     * Get the number of vectors stored by this value.
     *
     * @return Number of vectors stored by this value.
     */
    public int numVectors() {
        return (this.vectors == null) ? 0 : this.vectors.size();
    }

    /**
     * Get a vector stored by this value at a set index.
     *
     * @param index Index of the vector to get.
     * @return {@link Scalar} array with the vector at the set index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the vector list.
     */
    @JsonIgnore
    public Scalar[] getVector(final int index) {
        if (this.vectors == null) {
            throw new IndexOutOfBoundsException("Attempting to access vector " + index + " of " + this.numVectors());
        }
        return this.vectors.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the vectors stored by this value.
     *
     * @return {@link Iterable} object for iterating over the vectors stored by this value.
     */
    public Iterable<Scalar[]> vectors() {
        return (this.vectors == null) ? Collections.emptyList() : this.vectors;
    }

    /**
     * Get the list of vectors stored by this value.
     *
     * @return List of {@link Scalar} arrays with the vectors stored by this value.
     */
    @JsonGetter(value = "vectors")
    protected List<Scalar[]> getVectors() { // Private since only Jackson should use it
        return this.vectors;
    }

    /**
     * Set the list of matrices stored by this value.
     *
     * @param matrices List of {@link Scalar} arrays of arrays that represent the matrices stored by this value.
     */
    @JsonSetter(value = "matrices")
    @JsonDeserialize(contentUsing = MatricesDeserializer.class)
    protected void setMatrices(final List<Scalar[][]> matrices) { // Private since only Jackson should use it
        this.matrices = matrices;
    }

    /**
     * Set the list of matrices stored by this value.
     *
     * @param matrix List of {@link Scalar} arrays of arrays that represent the matrices stored by this value.
     */
    @JsonSetter(value = "matrix")
    @JsonDeserialize(contentUsing = MatricesDeserializer.class)
    protected void setMatrix(final List<Scalar[][]> matrix) { // Private since only Jackson should use it
        setMatrices(matrix);
    }

    /**
     * Add a single matrix to this value.
     *
     * @param matrix {@link Scalar} array of arrays with the matrix to add.
     * @return This object.
     */
    public Value addMatrix(final Scalar[][] matrix) {
        if (this.matrices == null) {
            this.matrices = new ArrayList<>();
        }
        this.matrices.add(matrix);
        return this;
    }

    /**
     * Insert a single matrix for this value.
     *
     * @param index Index at which to insert the input matrix.
     * @param matrix {@link Scalar} array of arrays to add to this value.
     * @return This object.
     */
    public Value addMatrix(final int index, final Scalar[][] matrix) {
        if (this.matrices == null) {
            this.matrices = new ArrayList<>();
        }
        this.matrices.add(index, matrix);
        return this;
    }

    /**
     * Add a string matrix to this value. This function uses {@link Scalar#valueOf(String)} to convert each string to a
     * {@link Scalar} object.
     *
     * @param matrix String array of arrays with the matrix to add.
     * @return This object.
     */
    public Value addMatrix(final String[][] matrix) {
        return addMatrix(toScalarMatrix(matrix));
    }

    /**
     * Insert a single matrix for this value.
     *
     * @param index Index at which to insert the input matrix.
     * @param matrix String array of arrays to add to this value.
     * @return This object.
     */
    public Value addMatrix(final int index, final String[][] matrix) {
        return addMatrix(index, toScalarMatrix(matrix));
    }

    /**
     * Add a numeric matrix to this value. This function uses {@link Scalar#valueOf(Number)} to convert each number to a
     * {@link Scalar} object.
     *
     * @param matrix Numeric array of arrays with the matrix to add.
     * @return This object.
     */
    public Value addMatrix(final Number[][] matrix) {
        return addMatrix(toScalarMatrix(matrix));
    }

    /**
     * Insert a single matrix for this value.
     *
     * @param index Index at which to insert the input matrix.
     * @param matrix Number array of arrays to add to this value.
     * @return This object.
     */
    public Value addMatrix(final int index, final Number[][] matrix) {
        return addMatrix(index, toScalarMatrix(matrix));
    }

    /**
     * Remove a matrix from the value.
     *
     * @param matrix {@link Scalar} array of arrays object to delete.
     * @return True if the object was removed.
     */
    public boolean removeMatrix(final Scalar[][] matrix) {
        return (this.matrices != null) && this.matrices.remove(matrix);
    }

    /**
     * Get the number of matrices stored by this value.
     *
     * @return Number of matrices stored by this value.
     */
    public int numMatrices() {
        return (this.matrices == null) ? 0 : this.matrices.size();
    }

    /**
     * Get a matrix stored by this value at a set index.
     *
     * @param index Index of the matrix to get from this value.
     * @return {@link Scalar} array of arrays for the matrix at the set index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the list of matrices.
     */
    @JsonIgnore
    public Scalar[][] getMatrix(final int index) {
        if (this.matrices == null) {
            throw new IndexOutOfBoundsException("Attempting to access matrix " + index + " of " + this.numMatrices());
        }
        return this.matrices.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the list of matrices stored by this value.
     *
     * @return {@link Iterable} object for iterating over the matrices stored by this value.
     */
    public Iterable<Scalar[][]> matrices() {
        return (this.matrices == null) ? Collections.emptyList() : this.matrices;
    }

    /**
     * Get the list of matrices stored by this value.
     *
     * @return List of {@link Scalar} arrays of arrays, each being a single matrix stored by this value.
     */
    @JsonGetter(value = "matrices")
    protected List<Scalar[][]> getMatrices() { // Private since only Jackson should use it
        return this.matrices;
    }

    /**
     * Set the units of this value.
     *
     * @param units String with the units of this value.
     * @return This object.
     */
    @JsonSetter(value = "units")
    public Value setUnits(final String units) {
        this.units = units;
        return this;
    }

    /**
     * Set the units of this value.
     *
     * @param unit String with the units of this value.
     */
    @JsonSetter(value = "unit")
    public void setUnit(final String unit) { // Private since only Jackson should use it
        setUnits(unit);
    }

    /**
     * Get the units stored by this value.
     *
     * @return String with the units stored by this value.
     */
    @JsonGetter(value = "units")
    public String getUnits() {
        return this.units;
    }

    @Override
    public Value addTag(final String tag) {
        super.addTag(tag);
        return this;
    }

    @Override
    public Value addTag(final int index, final String tag) {
        super.addTag(index, tag);
        return this;
    }

    @Override
    @JsonAnySetter
    public Value addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    /**
     * Convert from an array of strings to an array of {@link Scalar} objects.
     *
     * @param vector Array of strings to convert to scalars.
     * @return Array of scalars.
     */
    protected Scalar[] toScalarVector(final String[] vector) {
        final Scalar[] scalarVector = new Scalar[vector.length];
        for (int i = 0; i < vector.length; ++i) {
            scalarVector[i] = Scalar.valueOf(vector[i]);
        }
        return scalarVector;
    }

    /**
     * Convert from an array of numbers to an array of {@link Scalar} objects.
     *
     * @param vector Array of numbers to convert to scalars.
     * @return Array of scalars.
     */
    protected Scalar[] toScalarVector(final Number[] vector) {
        final Scalar[] scalarVector = new Scalar[vector.length];
        for (int i = 0; i < vector.length; ++i) {
            scalarVector[i] = Scalar.valueOf(vector[i]);
        }
        return scalarVector;
    }

    /**
     * Convert from an array of arrays of strings to an array of arrays of {@link Scalar} objects.
     *
     * @param matrix Array of arrays of strings to convert to scalars.
     * @return Array of arrays of scalars.
     */
    protected Scalar[][] toScalarMatrix(final String[][] matrix) {
        final Scalar[][] scalarMatrix = new Scalar[matrix.length][];
        for (int i = 0; i < matrix.length; ++i) {
            scalarMatrix[i] = new Scalar[matrix[i].length];
            for (int j = 0; j < matrix[i].length; ++j) {
                scalarMatrix[i][j] = Scalar.valueOf(matrix[i][j]);
            }
        }
        return scalarMatrix;
    }

    /**
     * Convert from an array of arrays of numbers to an array of arrays of {@link Scalar} objects.
     *
     * @param matrix Array of arrays of numbers to convert to scalars.
     * @return Array of arrays of scalars.
     */
    protected Scalar[][] toScalarMatrix(final Number[][] matrix) {
        final Scalar[][] scalarMatrix = new Scalar[matrix.length][];
        for (int i = 0; i < matrix.length; ++i) {
            scalarMatrix[i] = new Scalar[matrix[i].length];
            for (int j = 0; j < matrix[i].length; ++j) {
                scalarMatrix[i][j] = Scalar.valueOf(matrix[i][j]);
            }
        }
        return scalarMatrix;
    }

    /** String with the name of the value. */
    private String name;

    /** List of scalar values. */
    private List<Scalar> scalars;

    /** List of vector values. */
    private List<Scalar[]> vectors;

    /** List of matrix values. */
    private List<Scalar[][]> matrices;

    /** Units of the value. */
    private String units;

    /**
     * Class to deserialize into a list of arrays of {@link Scalar} objects.
     *
     * @author Kyle Michel
     */
    public static class VectorsDeserializer extends JsonDeserializer<List<Scalar[]>> {

        @Override
        public List<Scalar[]> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            jsonParser.setCodec(PifObjectMapper.getInstance());
            final JsonNode jsonNode = jsonParser.readValueAsTree();
            if (jsonNode.isArray()) {
                return fromArrayNode(jsonNode, deserializationContext);
            }
            throw deserializationContext.mappingException("Cannot deserialize to list of vectors");
        }

        /**
         * Convert from a JSON array to a list of {@link Scalar} arrays.
         *
         * @param jsonNode {@link JsonNode} that represents the array to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @return List of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[]> fromArrayNode(
                final JsonNode jsonNode, final DeserializationContext deserializationContext) throws IOException {
            if (jsonNode.size() == 0) {
                return Collections.emptyList();
            }
            else if (jsonNode.get(0).isArray()) {
                return fromList(jsonNode, deserializationContext);
            }
            else {
                return Collections.singletonList(fromVector(jsonNode, deserializationContext));
            }
        }

        /**
         * Convert from a list of lists to a list of {@link Scalar} arrays.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @return List of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[]> fromList(
                final JsonNode jsonNode, DeserializationContext deserializationContext) throws IOException {
            final List<Scalar[]> res = new ArrayList<>(jsonNode.size());
            for (JsonNode i : jsonNode) {
                res.add(fromVector(i, deserializationContext));
            }
            return res;
        }

        /**
         * Convert from a list of values to an array of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @return Array of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected Scalar[] fromVector(final JsonNode jsonNode, final DeserializationContext deserializationContext)
                throws IOException {
            final Scalar[] res = new Scalar[jsonNode.size()];
            final Scalar.Deserializer deserializer = new Scalar.Deserializer();
            for (int i = 0; i < jsonNode.size(); ++i) {
                final JsonParser jsonParser = jsonNode.get(i).traverse();
                jsonParser.nextToken();
                res[i] = deserializer.deserialize(jsonParser, deserializationContext);
            }
            return res;
        }
    }

    /**
     * Class to deserialize into a list of arrays of arrays of {@link Scalar} objects.
     *
     * @author Kyle Michel
     */
    public static class MatricesDeserializer extends JsonDeserializer<List<Scalar[][]>> {

        @Override
        public List<Scalar[][]> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            jsonParser.setCodec(PifObjectMapper.getInstance());
            final JsonNode jsonNode = jsonParser.readValueAsTree();
            if (jsonNode.isArray()) {
                return fromArrayNode(jsonNode, deserializationContext, jsonParser.getCodec());
            }
            throw deserializationContext.mappingException("Cannot deserialize to list of matrices");
        }

        /**
         * Convert from a JSON array to a list of arrays of arrays of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @param objectCodec {@link ObjectCodec} for the parser being used.
         * @return List of arrays of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[][]> fromArrayNode(
                final JsonNode jsonNode, final DeserializationContext deserializationContext,
                final ObjectCodec objectCodec) throws IOException {
            if (jsonNode.size() == 0) {
                return Collections.emptyList();
            }
            else if (!jsonNode.get(0).isArray()) {
                throw deserializationContext.mappingException("Cannot deserialize to list of matrices");
            }
            else if ((jsonNode.get(0).size() > 0) && (jsonNode.get(0).get(0).isArray())) {
                return fromList(jsonNode, deserializationContext, objectCodec);
            }
            else {
                return Collections.singletonList(fromMatrix(jsonNode, deserializationContext, objectCodec));
            }
        }

        /**
         * Convert from a {@link JsonNode} object into a list of arrays of arrays of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @param objectCodec {@link ObjectCodec} for the parser being used.
         * @return List of arrays of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[][]> fromList(
                final JsonNode jsonNode, final DeserializationContext deserializationContext,
                final ObjectCodec objectCodec) throws IOException {
            final List<Scalar[][]> res = new ArrayList<>(jsonNode.size());
            for (JsonNode i : jsonNode) {
                res.add(fromMatrix(i, deserializationContext, objectCodec));
            }
            return res;
        }

        /**
         * Convert from a {@link JsonNode} object into an array of arrays of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} object to convert into a matrix.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @param objectCodec {@link ObjectCodec} for the parser being used.
         * @return Array of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected Scalar[][] fromMatrix(
                final JsonNode jsonNode, final DeserializationContext deserializationContext,
                final ObjectCodec objectCodec) throws IOException {
            final JsonParser jsonParser = jsonNode.traverse(objectCodec);
            jsonParser.nextToken();
            final VectorsDeserializer deserializer = new VectorsDeserializer();
            final List<Scalar[]> matrix = deserializer.deserialize(jsonParser, deserializationContext);
            final Scalar[][] res = new Scalar[matrix.size()][];
            return matrix.toArray(res);
        }
    }
}