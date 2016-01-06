package io.citrine.jpif.object.core.general;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Information about a scalar, vector, or matrix, or a list of one of those.
 *
 * @author Kyle Michel
 */
public class Value extends Pio {

    public Value setName(final String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    @JsonSetter
    private void setScalars(final List<Scalar> scalars) { // Private since only Jackson should use it
        this.scalars = scalars;
    }

    public Value addScalar(final Scalar scalar) {
        if (this.scalars == null) {
            this.scalars = new ArrayList<>();
        }
        this.scalars.add(scalar);
        return this;
    }

    public int scalarsLength() {
        return (this.scalars == null) ? 0 : this.scalars.size();
    }

    @JsonIgnore
    public Scalar getScalar(final int index) {
        if (this.scalars == null) {
            throw new IndexOutOfBoundsException("Attempting to access scalar " + index + " of " + this.scalarsLength());
        }
        return this.scalars.get(index);
    }

    public Iterable<Scalar> scalars() {
        return this.scalars;
    }

    @JsonGetter
    private List<Scalar> getScalars() { // Private since only Jackson should use it
        return this.scalars;
    }



    @JsonSetter
    private void setVectors(final List<Scalar[]> vectors) { // Private since only Jackson should use it
        this.vectors = vectors;
    }

    public Value addVector(final Scalar[] vector) {
        if (this.scalars == null) {
            this.scalars = new ArrayList<>();
        }
        this.scalars.add(scalar);
        return this;
    }

    public int scalarsLength() {
        return (this.scalars == null) ? 0 : this.scalars.size();
    }

    @JsonIgnore
    public Scalar getScalar(final int index) {
        if (this.scalars == null) {
            throw new IndexOutOfBoundsException("Attempting to access scalar " + index + " of " + this.scalarsLength());
        }
        return this.scalars.get(index);
    }

    public Iterable<Scalar> scalars() {
        return this.scalars;
    }

    @JsonGetter
    private List<Scalar> getScalars() { // Private since only Jackson should use it
        return this.scalars;
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
}