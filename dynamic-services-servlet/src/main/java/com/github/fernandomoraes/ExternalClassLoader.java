package com.github.fernandomoraes;

import java.io.File;
import java.io.IOException;

public class ExternalClassLoader extends ClassLoader {

    private final String repository;

    public ExternalClassLoader(final ClassLoader parent, final String repository) {
        super(parent);
        this.repository = repository;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            final byte[] classBytes = IOUtils.fileAsByte(new File(toClassFileName(name)));
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (final IOException ex) {
            throw new ClassNotFoundException();
        }
    }

    private String toClassFileName(final String name) {
        return repository + name.replaceAll("\\.", File.separator) + ".class";
    }

}
