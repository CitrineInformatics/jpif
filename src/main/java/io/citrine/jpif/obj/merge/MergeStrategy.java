package io.citrine.jpif.obj.merge;

import io.citrine.jpif.obj.common.Pio;

import java.util.ArrayList;
import java.util.List;

/**
 * An enumeration of supported merge strategies.
 *
 * @author Sean Paradiso
 */
public enum MergeStrategy {

    // Conflicting objects are replaced if the merge source
    // is non-null and all objects in matching lists are appended
    REPLACE_NON_NULL_AND_APPEND {
        /**
         * Merge two lists.
         * @param mergeInto the destination list.
         * @param mergeFrom the source list.
         * @return the merge result.
         */
        @Override
        public List<Object> mergeLists(List<Object> mergeInto, List<Object> mergeFrom) {
            List<Object> result = new ArrayList<>();

            if (mergeInto != null) {
                result.addAll(mergeInto);
            }

            if (mergeFrom != null) {
                result.addAll(mergeFrom);
            }
            return result;
        }

        /**
         * Merge two Pio objects.
         *
         * @param mergeInto the destination object.
         * @param mergeFrom the source object.
         * @return the merge result.
         */
        @Override
        public Pio mergePio(Pio mergeInto, Pio mergeFrom) {
            if (mergeFrom != null) {
                return mergeFrom;
            }
            return mergeInto;
        }

        /**
         * Merge two objects.
         *
         * @param mergeInto the destination object.
         * @param mergeFrom the source object.
         * @return the merge result.
         */
        @Override
        protected Object mergeObject(Object mergeInto, Object mergeFrom) {
            if (mergeFrom != null) {
                return mergeFrom;
            }
            return mergeInto;
        }
    };

    /**
     * Merge two lists.
     *
     * @param mergeInto the destination list.
     * @param mergeFrom the source list.
     * @return the merge result.
     */
    public abstract List<Object> mergeLists(List<Object> mergeInto, List<Object> mergeFrom);

    /**
     * Merge two Pio objects.
     *
     * @param mergeInto the destination object.
     * @param mergeFrom the source object.
     * @return the merge result.
     */
    protected abstract Pio mergePio(Pio mergeInto, Pio mergeFrom);

    /**
     * Merge two objects that are _not_ Pio instances.
     *
     * @param mergeInto the destination object.
     * @param mergeFrom the source object.
     * @return the merge result.
     */
    protected abstract Object mergeObject(Object mergeInto, Object mergeFrom);

    /**
     * Merge two objects as fields from Pio instances.
     *
     * @param mergeInto the destination object.
     * @param mergeFrom the source object.
     * @return the merge result.
     */
    public Object merge(Object mergeInto, Object mergeFrom) {
        if (mergeInto instanceof Pio) {
            return mergePio((Pio) mergeInto, (Pio) mergeFrom);
        } else {
            return mergeObject(mergeInto, mergeFrom);
        }
    }
}
