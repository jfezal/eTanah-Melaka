<%@ page import="etanah.model.Pengguna" %>
<% Pengguna p = (Pengguna)request.getSession().getAttribute("_KEY_USER");
    if(p == null)
        response.sendRedirect("login");
        else
            response.sendRedirect("welcome");

   
%>