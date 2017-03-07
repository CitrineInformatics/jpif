package io.citrine.jpif.obj.merge;

import io.citrine.jpif.obj.common.Pio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An enumeration of supported merge strategies.
 *
 * @author Sean Paradiso
 */
public enum MergeStrategy {

    // Conflicting objects are replaced if the merge source
    // is non-null and all objects in matching lists are appended
    REPLACE_NON_NULL_AND_APPEND {
        @Override
        public List<Object> merge(List<Object> mergeInto, List<Object> mergeFrom) {
            List<Object> result = new ArrayList<>();

            if (mergeInto != null) {
                result.addAll(mergeInto);
            }

            if (mergeFrom != null) {
                result.addAll(mergeFrom);
            }
            return result;
        }

        @Override
        public Pio merge(Pio mergeInto, Pio mergeFrom) {
            if (mergeFrom != null) {
                return mergeFrom;
            }
            return mergeInto;
        }

        @Override
        public Map<String, Object> merge(Map<String, Object> mergeInto, Map<String, Object> mergeFrom) {
            Map<String, Object> result = new HashMap<>();
            result.putAll(mergeInto);
            result.putAll(mergeFrom);
            return result;
        }

        @Override
        public Object merge(Object mergeInto, Object mergeFrom) {
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
    public abstract List<Object> merge(List<Object> mergeInto, List<Object> mergeFrom);

    /**
     * Merge two Pio objects.
     *
     * @param mergeInto the destination object.
     * @param mergeFrom the source object.
     * @return the merge result.
     */
    public abstract Pio merge(Pio mergeInto, Pio mergeFrom);

    /**
     * Merge two Maps.
     *
     * @param mergeInto the destination object.
     * @param mergeFrom the source object.
     * @return the merge result.
     */
    public abstract Map<String, Object> merge(Map<String, Object> mergeInto, Map<String, Object> mergeFrom);

    /**
     * Merge two objects that are _not_ Pio instances.
     *
     * @param mergeInto the destination object.
     * @param mergeFrom the source object.
     * @return the merge result.
     */
    public abstract Object merge(Object mergeInto, Object mergeFrom);
}
