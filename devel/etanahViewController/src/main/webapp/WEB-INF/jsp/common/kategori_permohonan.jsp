<%-- 
    Document   : kategori_permohonan
    Created on : 15-Sep-2009, 12:50:34
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <form:form beanclass="com.etanah.stripes.ConsentTest">          
            <fieldset>
                <legend>
                    Kategori Permohonan
                </legend>

                <table>
                    <tr>
                        <td align="right">Jenis Permohonan :</td>
                        <td>Consent &nbsp;</td>
                    </tr>

                    <tr>
                        <td align="right">ID Permohonan :</td>
                        <td>121313412&nbsp;</td>
                    </tr>

                    <tr>
                        <td align="right">Nama Penyerah :</td>
                        <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td align="right">Nombor Rujukan Peguam :</td>
                        <td><s:text name=""/></td>
                    </tr>

                    <tr>
                        <td align="right">Nombor Rujukan Pihak Ketiga :</td>
                        <td><s:text name=""/></td>
                    </tr>


                </table>

            </fieldset>
        </form:form>
    </body>
</html>
