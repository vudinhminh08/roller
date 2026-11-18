/*
 * Tệp thử nghiệm D4 (PR Triage), không thuộc Apache Roller.
 */
package org.apache.roller.weblogger.ui.struts2.ajax;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SafeCommentSearchServlet extends HttpServlet {
    private java.sql.Connection con;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String q = request.getParameter("q");
        try (java.sql.PreparedStatement st = con.prepareStatement(
                "SELECT id FROM roller_comment WHERE content LIKE ?")) {
            st.setString(1, "%" + q + "%");
            java.sql.ResultSet rs = st.executeQuery();
            while (rs.next()) {
                response.getWriter().println(rs.getString(1));
            }
        } catch (java.sql.SQLException e) {
            throw new IOException(e);
        }
    }
}
