package io.citrine.jpif.util;

import io.citrine.jpif.io.JsonDeserializingPifSystemStream;
import io.citrine.jpif.io.PifInputStream;
import io.citrine.jpif.io.PifSystemStream;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * Class used to parse and output a PIF.
 *
 * @author Kyle Michel
 */
public class Parser {

    /**
     * Run the parser.
     *
     * @param args Command line arguments. The only argument should be the path to the file to parse.
     */
    public static void main(String[] args) throws Exception {
        if (checkArgs(args)) {
            if (args.length == 1) {
                java.lang.System.out.println(process(args[0]));
            }
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
            else {
                throw new Exception();
            }
        }
        catch (Exception e) {
            java.lang.System.out.println("Usage: validate path/to/file");
            return false;
        }
    }

    /**
     * Process the list of PIFs at the input location and return a string for it.
     *
     * @param path String with the path to the PIFs.
     * @return String with the content of the PIFs.
     * @throws Exception if thrown during processing.
     */
    private static String process(final String path) throws Exception {
        final PifSystemStream pifSystemStream = new JsonDeserializingPifSystemStream(new FileInputStream(path));
        final PifInputStream pifInputStream = new PifInputStream(pifSystemStream);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length;
        final byte[] buffer = new byte[1024];
        while ((length = pifInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        return byteArrayOutputStream.toString("UTF-8");
    }
}