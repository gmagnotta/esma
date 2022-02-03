package com.magnotta;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magnotta.model.TransparencyDataReport;

import javax.inject.Inject;

public class TransparencyDataReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicesInterface servicesInterface;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        if (id == null || id.equals("")) {
            id = "%";
        }

        String size = request.getParameter("size");
        if (size == null) {
            size = "100";
        }

        List<TransparencyDataReport> res = servicesInterface.searchId(id, Integer.valueOf(size));

        request.setAttribute("items", res);

        response.setHeader("cache-control", "no-cache");

        getServletContext().getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);

    }

}