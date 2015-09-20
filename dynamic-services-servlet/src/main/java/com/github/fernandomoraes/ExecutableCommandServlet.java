package com.github.fernandomoraes;

import com.github.fernandomoraes.services.api.Executable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/execute")
public class ExecutableCommandServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        final String repository = req.getParameter("repository");
        final String impl = req.getParameter("impl");

        if (repository != null && impl != null) {
            try {
                final Executable executable = getImplementation(repository, impl);
                resp.getWriter().write(executable.execute());
            } catch (ClassNotFoundException ex) {
                resp.sendError(400);
            } catch (InstantiationException ex) {
                resp.sendError(500);
            } catch (IllegalAccessException ex) {
                resp.sendError(500);
            }
        } else {
            resp.sendError(400);
        }

    }

    private Executable getImplementation(final String repository, final String impl)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {

        final ClassLoader parent = Thread.currentThread().getContextClassLoader();
        return (Executable) new ExternalClassLoader(parent,
                repository).loadClass(impl).newInstance();

    }

}
