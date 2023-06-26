package com.heimdall.core.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class GetterAndSetterTester {
    /**
     * Map with default instances for classes.
     */
    private final Map<Class<?>, Object> defaultInstances;

    /**
     * A list of fields that should no be tested.
     */
    private List<String> ignoredFields;


    /**
     * Creates the GetterAndSetterTester.
     */
    public GetterAndSetterTester() {
        defaultInstances = new HashMap<Class<?>, Object>();

        defaultInstances.put(boolean.class, Boolean.TRUE);
        defaultInstances.put(byte.class, (byte) 0);
        defaultInstances.put(char.class, '0');
        defaultInstances.put(short.class, (short) 0);
        defaultInstances.put(int.class, 0);
        defaultInstances.put(long.class, 0L);
        defaultInstances.put(float.class, 0f);
        defaultInstances.put(double.class, 0d);
        defaultInstances.put(List.class, new ArrayList<>());
        defaultInstances.put(LocalDate.class, LocalDate.now());
        defaultInstances.put(LocalDateTime.class, LocalDateTime.now());
        defaultInstances.put(UUID.class, UUID.randomUUID());
        defaultInstances.put(String.class, "TESTE");

        ignoredFields = new LinkedList<String>();
    }

    /**
     * Adds a map of instances to use by default by the tester
     * when an instance of a given class is needed.
     * Every time an instance of a class is needed to
     * be used on a setter or to construct a class, the class
     * will be looked in the map and the value object will be used
     * as instance.
     *
     * @param instances the map with classes and its instances to use.
     */
    public void addDefaultInstances(final Map<Class<?>, Object> instances) {
        defaultInstances.putAll(instances);
    }

    /**
     * Test the accessors of the given instance.
     * Only instantiation through default constructors or primitive types
     * is supported, therefore the tested class accessible fields are expected
     * to be recursively traced to classes that can be instantiated that way,
     * otherwise the test will throw stack overflows while trying to instantiate
     * classes.
     * If complicated instantiations are involved, it's recommended to use
     * the addDefaultInstances method.
     *
     * @param instance the instance to test.
     */
    public void testInstance(final Object instance) {

        Field[] fields = instance.getClass()
            .getDeclaredFields();

        for (Field field : fields) {
            if (hasGetterAndSetter(field)) {
                testGetterAndSetter(field, instance);
            } else {
                if (hasGetter(field)) {
                    testGetter(field, instance);
                }
                if (hasSetter(field)) {
                    testSetter(field, instance);
                }
            }
        }
    }

    /**
     * Test the accessors of the given class, which will be attemped to be
     * instantiated.
     * Only instantiation through default constructors or primitive types
     * is supported, therefore the tested class constructor arguments and
     * accessible fields are expected to be recursively traced to classes
     * that can be instantiated that way, otherwise the test will throw
     * stack overflows while trying to instantiate classes.
     * If complicated instantiations are involved, it's recommended to use
     * the addDefaultInstances method.
     *
     * @param clazz the class to test.
     */
    public void testClass(final Class<?> clazz) {
        testInstance(Objects.requireNonNull(getInstance(clazz)));
    }


    /**
     * Returns the setter of the field or null if there is none.
     *
     * @param field the field to get the setter from.
     * @return the setter method.
     */
    private Method getSetter(final Field field) {
        if (ignoredFields.contains(field.getName())) {
            return null;
        }

        Class<?> theClass = field.getDeclaringClass();
        try {
            return theClass.getMethod("set" + nameWithCapital(field),
                field.getType());
        } catch (NoSuchMethodException e) {
            log.debug("No Such Method set" + nameWithCapital(field));
            return null;
        }
    }


    /**
     * Returns the getter of the field or null if there is none.
     * Both get(FieldName) and is(FieldName) method will be checked.
     * If the method return type is different from field type,
     * the method will be ignored.
     *
     * @param field the field to get the getters from.
     * @return the getter method.
     */
    private Method getGetter(final Field field) {
        Class<?> theClass = field.getDeclaringClass();

        if (ignoredFields.contains(field.getName())) {
            return null;
        }

        Method theMethod = null;
        try {
            theMethod = theClass.getMethod("get" + nameWithCapital(field));
        } catch (NoSuchMethodException e) {
            try {
                theMethod = theClass.getMethod("is" + nameWithCapital(field));
            } catch (NoSuchMethodException e1) {
                return null;
            }
        }

        if (!theMethod.getReturnType().equals(field.getType())) {
            theMethod = null;
        }
        return theMethod;
    }

    /**
     * Returns the name of the field with its first character as
     * a capital letter. useful to append it to a method name such
     * as a getter or setter.
     *
     * @param field the field to get the name of.
     * @return the name.
     */
    private String nameWithCapital(final Field field) {
        String result = field.getName();
        return result.replaceFirst("" + result.charAt(0),
            "\\" + Character.toUpperCase(result.charAt(0)));
    }

    /**
     * Returns true if the given field has a getter method.
     *
     * @param field the field to check.
     * @return if it has a getter.
     */
    private boolean hasGetter(final Field field) {
        return getGetter(field) != null;
    }

    /**
     * Returns true if the given field has a setter method.
     *
     * @param field the field to check.
     * @return if it has a setter.
     */
    private boolean hasSetter(final Field field) {
        return getSetter(field) != null;
    }

    /**
     * Returns true if the given field has both getter and
     * setter methods.
     *
     * @param field the field to check.
     * @return if it has getter and setter.
     */
    private boolean hasGetterAndSetter(final Field field) {
        return hasGetter(field) && hasSetter(field);
    }

    /**
     * Test the getter and setter method of the field, using the given
     * instance of the class.
     *
     * @param field    the field to test the getter and setter.
     * @param instance the instance to use to test.
     */
    private void testGetterAndSetter(final Field field, final Object instance) {
        Object value = getInstance(field.getType());
        Method getter = getGetter(field);
        Method setter = getSetter(field);

        try {
            assertNotNull(setter);
            setter.invoke(instance, value);
            assertNotNull(getter);
            assertEquals(value, getter.invoke(instance),
                "Failed getter and setter test of field "
                        + field.getName() + " on classs "
                        + field.getDeclaringClass().getName());

        } catch (Exception e) {
            e.printStackTrace();
            fail(" exception thrown on field: " + field.getName());
        }

    }

    /**
     * Test the setter method of the field, using the given
     * instance of the class.
     *
     * @param field    the field to test the setter.
     * @param instance the instance to use to test.
     */
    private void testSetter(final Field field, final Object instance) {
        Object value = getInstance(field.getType());
        Method setter = getSetter(field);

        try {
            assertNotNull(setter);
            setter.invoke(instance, value);

            try { //Maybe this helps XD
                field.setAccessible(true);
            } catch (Exception ignored) {
            }

            assertEquals(value, field.get(instance),
                "Failed setter test of field "
                    + field.getName() + " on classs "
                    + field.getDeclaringClass().getName());

        } catch (Exception e) {
            e.printStackTrace();
            fail("exception thrown on field:" + field.getName());
        }

    }

    /**
     * Test the getter method of the field, using the given
     * instance of the class.
     *
     * @param field    the field to test the getter.
     * @param instance the instance to use to test.
     */
    private void testGetter(final Field field, final Object instance) {
        Object value = getInstance(field.getType());
        Method getter = getGetter(field);

        try {

            try { //Maybe this helps XD
                field.setAccessible(true);
            } catch (Exception ignored) {
            }

            field.set(instance, value);
            assertNotNull(getter);
            assertEquals(value, getter.invoke(instance),
                "Failed getter test of field "
                    + field.getName() + " on classs "
                    + field.getDeclaringClass().getName());

        } catch (Exception e) {
            e.printStackTrace();
            fail("exception thrown on field: " + field.getName());
        }

    }

    /**
     * Recursive method to instantiate a given class.
     * First the map of default instances will be checked
     * for an instance. If not existent, then if a default
     * (no arguments) constructor exists, it will be used,
     * otherwise any other constructor will be tried until
     * the class can be instantiated.
     * The last choice will be returning null.
     *
     * @param aClass the class to instantiate.
     * @return the new instance or null.
     */
    //TODO move this to a new class and refactor
    //TODO test if enums are working
    //TODO return mock instead of null?
    //TODO instead of using get in the map test each class using isAssignableFrom.
    private Object getInstance(final Class<?> aClass) {
        Object instance = defaultInstances.get(aClass);

        if (instance != null) {
            return instance;
        }

        if (aClass.isEnum()) {
            return aClass.getEnumConstants()[0];
        }

        try {
            return aClass.newInstance();

        } catch (Exception e) {
            Constructor<?>[] constructors = aClass.getDeclaredConstructors();
            for (Constructor<?> current : constructors) {

                try { //Maybe this helps XD
                    current.setAccessible(true);
                } catch (Exception ignored) {
                }

                //Class[] parameterTypes = current.getParameterTypes();

//                //generates an array of instances of the arguments:
//                Object[] parameters = new Object[parameterTypes.length];
//                for (int j = 0; j < parameterTypes.length; j++) {
//                    parameters[j] = getInstance(parameterTypes[j]);
//                }

                //generates an array of instances of the arguments:
                Class<?>[] arrayParameterTypes = Arrays
                    .stream(current.getParameterTypes())
                    .filter(aParameter -> !aParameter.isInterface())
                    .toArray(Class[]::new);
                Object[] parameters = new Object[arrayParameterTypes.length];
                for (int j = 0; j < arrayParameterTypes.length; j++) {
                    parameters[j] = getInstance(arrayParameterTypes[j]);
                }

                try {
                    return current.newInstance(parameters);
                } catch (Exception e1) {
                    //If the construction fails,
                    //continue with the next constructor.
                }
            }
        }

        return null;
    }

    /**
     * Sets a list of the names of the fields that should be ignored.
     * All the fields in the list won't be tested.
     *
     * @param fields the ignoredFields to set.
     */
    public void setIgnoredFields(final String... fields) {
        ignoredFields = Arrays.asList(fields);
    }
}
