package com.github.fernandomoraes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    public static byte[] fileAsByte(final File file) throws IOException {
        final InputStream in = new FileInputStream(file);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int read; (read = in.read()) != -1;) {
            out.write(read);
        }
        return out.toByteArray();
    }

}
