/*
 * Tệp thử nghiệm D4 (PR Triage), không thuộc Apache Roller.
 */
package org.apache.roller.weblogger.ui.struts2.ajax;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentSearchServlet extends HttpServlet {
    private java.sql.Connection con;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String q = request.getParameter("q");
        try (java.sql.Statement st = con.createStatement()) {
            java.sql.ResultSet rs = st.executeQuery(
                "SELECT id FROM roller_comment WHERE content LIKE '%" + q + "%'");
            while (rs.next()) {
                response.getWriter().println(rs.getString(1));
            }
        } catch (java.sql.SQLException e) {
            throw new IOException(e);
        }
    }
}
