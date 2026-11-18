/*
 * Tệp thử nghiệm D4 (PR Triage), không thuộc Apache Roller.
 */
package org.apache.roller.weblogger.ui.struts2.ajax;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExportDownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("file");
        java.io.File f = new java.io.File("/var/roller/exports", name);
        try (java.io.FileInputStream in = new java.io.FileInputStream(f)) {
            in.transferTo(response.getOutputStream());
        }
    }
}
