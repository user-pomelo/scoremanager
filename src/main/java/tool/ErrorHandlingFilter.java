package tool;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class ErrorHandlingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {
            chain.doFilter(request, response);

        } catch (Throwable e) {
            e.printStackTrace();

            RequestDispatcher rd =
                request.getRequestDispatcher("/scoremanager/error.jsp");
            rd.forward(request, response);
        }
    }
}
