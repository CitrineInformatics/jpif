package io.citrine.jpif.util;

import io.citrine.jpif.io.JsonDeserializingPifSystemStream;
import io.citrine.jpif.io.PifSystemStream;
import io.citrine.jpif.obj.system.System;

import java.io.FileInputStream;

/**
 * Class used to validate a PIF.
 *
 * @author Kyle Michel
 */
public class Validator {

    /**
     * Run the validator.
     *
     * @param args Command line arguments. The only argument should be the path to the file to validate.
     */
    public static void main(String[] args) {
        if (checkArgs(args)) {
            validate(args[0]);
        }
    }

    /**
     * Check that the command line arguments are valid.
     *
     * @param args Command line arguments.
     * @return True if the arguments are valid.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static boolean checkArgs(final String[] args) {
        try {
            if (args.length == 1) {
                return true;
            }
            if (args.length == 2) {
                // Make sure the second args is an int
                Integer.valueOf(args[1]);
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e) {
            java.lang.System.out.println("Usage: validate path/to/file [max records to check]");
            return false;
        }
        return true;
    }

    /**
     * Validate the input file.
     *
     * @param path Path to the file to validate.
     */
    private static void validate(final String path) {
        try {
            validate(new JsonDeserializingPifSystemStream(new FileInputStream(path)));
            java.lang.System.out.println("File is valid");
        }
        catch (Exception e) {
            java.lang.System.err.println("[Error] File not validated: " + e.getMessage());
        }
    }

    /**
     * Try to validate the input file. This throws an exception if there are any errors in processing the file.
     *
     * @param pifSystemStream {@link PifSystemStream} to validate.
     * @return True if the stream is valid.
     * @throws Exception if thrown while validating the stream.
     */
    public static boolean validate(final PifSystemStream pifSystemStream) throws Exception {
        return validate(pifSystemStream, Integer.MAX_VALUE);
    }

    /**
     * Try to validate the input file. This throws an exception if there are any errors in processing the file.
     *
     * @param pifSystemStream {@link PifSystemStream} to validate.
     * @return True if the stream is valid.
     * @throws Exception if thrown while validating the stream.
     */
    public static boolean validate(final PifSystemStream pifSystemStream, final int maxToCheck) throws Exception {
        int count = 0;
        for (System i : pifSystemStream) {
            if (++count >= maxToCheck) {
                break;
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException("File does not contain any records");
        }
        return true;
    }
}