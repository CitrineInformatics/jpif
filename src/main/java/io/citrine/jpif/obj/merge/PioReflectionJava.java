package io.citrine.jpif.obj.merge;

import io.citrine.jpif.obj.common.Pio;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A reflection utility class for Pio instances.
 *
 * @author Sean Paradiso
 */
public class PioReflectionJava {

    /**
     * The constructor parses and caches all getter and setter methods for future work.
     *
     * @param instance the Pio instance whose fields are parsed.
     */
    public PioReflectionJava(Pio instance) {
        Map<String, Method> gettersTmp = getAllMethods(instance.getClass(), "get.*");
        Map<String, Method> settersTmp = getAllMethods(instance.getClass(), "set.*");

        gettersTmp.keySet().stream()
                .filter(key -> gettersTmp.get(key).getParameterCount() == 0)
                .forEach(key -> getters.put(key, gettersTmp.get(key)));

        settersTmp.keySet().stream()
                .filter(key -> settersTmp.get(key).getParameterCount() == 1)
                .forEach(key -> setters.put(key, settersTmp.get(key)));

        getters.values().stream().forEach(method -> method.setAccessible(true));
        setters.values().stream().forEach(method -> method.setAccessible(true));
    }

    /**
     * Getter for the getters map.
     *
     * @return the method name to Method map filtered down to methods matching "get.*".
     */
    public Map<String, Method> getGetters() {
        return getters;
    }

    /**
     * Getter for the setters map.
     *
     * @return the method name to Method map filtered down to methods matching "set.*".
     */
    public Map<String, Method> getSetters() {
        return setters;
    }

    /**
     * Unified method getter to retrieve
     *
     * @param methodKey the name of the method.
     * @return the Method instance.
     */
    public Method getMethod(String methodKey) {
        return getters.getOrDefault(methodKey, setters.get(methodKey));
    }

    /**
     * Check whether a particular Method discovered through reflection is a getter with a List type.
     *
     * @param method the method to check.
     * @return true if the Method has a return type of List.
     */
    public boolean isList(Method method) {
        return method.getReturnType().getCanonicalName().equals("java.util.List");
    }

    /**
     * Check whether a particular Method name discovered through reflection is a getter with a List type.
     *
     * @param method the name of the method to check.
     * @return true if the Method has a return type of List.
     */
    public boolean isList(String method) {
        return getters.get(method).getReturnType().getCanonicalName().equals("java.util.List");
    }

    /**
     * Internal utility to march up the class hierarchy, collecting Methods whose names match a given pattern.
     *
     * @param clazz   the Class whose methods are returned.
     * @param pattern regex pattern to match method names on.
     * @return a map of all Methods found.
     */
    private Map<String, Method> getAllMethods(Class<?> clazz, String pattern) {

        // Base case
        if (clazz == null) {
            return new HashMap<>();
        }

        Map<String, Method> methodMap = new HashMap<>();
        Map<String, Method> parentMethods = getAllMethods(clazz.getSuperclass(), pattern);

        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        methods.stream()
                .filter(m -> m.getName().matches(pattern))
                .forEach(m -> methodMap.put(m.getName(), m));

        // Pull in all parent methods (skipping overridden methods)
        parentMethods
                .keySet()
                .forEach(k -> methodMap.putIfAbsent(k, parentMethods.get(k)));

        return methodMap;
    }

    private Map<String, Method> getters = new HashMap<>();
    private Map<String, Method> setters = new HashMap<>();
}
