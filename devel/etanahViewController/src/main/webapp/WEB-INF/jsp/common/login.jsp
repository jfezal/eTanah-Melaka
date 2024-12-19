<%--
    Document   : login
    Created on : Oct 5, 2009, 2:28:39 PM
    Author     : abu.mansur
--%>

<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>.: e-Tanah :.</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
        <%--<link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/menu.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/qt.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/sm.css"/>--%>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquerycornerv1.99.js"></script>
        <%--<script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquery.tooltip.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/ui.core.js"></script>--%>

         <script type="text/javascript">
            $(function() {
               <%-- $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});--%>
                $('.subtitle').corner("20px");

            });

        </script>
    </head>
    <br><br><br><br><br><br><br><br><br><br><br><br><br>
    <body>
        <center>
            <div class="subtitle" style="width:50%;background:red;">
                <fieldset class="aras1">
                    <%--<legend>
                        Login
                    </legend>--%>
                    <div class="content" align="center" >
                        <stripes:errors/>
                        <stripes:form action="/WSlogin" title="Login">
                            <table border="0" align="center">
                                <tr align="center">
                                    <td colspan="2"><p><p>Selamat Datang ke Sistem e-Tanah.<br>Sila masukkan Nama Pengguna dan Kata Laluan <br>di dalam ruangan yang disediakan dengan betul.<p></td>
                                </tr>
                                <tr>
                                    <td align="right">Id Pengguna :</td>

                                    <td class="input1"><stripes:text name="pengguna.idPengguna" size="30"/></td>
                                </tr>
                                <tr>
                                    <td align="right">Kata Laluan :</td>
                                    <td class="input1"><stripes:password size="30" name="pengguna.password"/></td>
                                </tr>
                                <tr align="center">
                                    <td colspan="2"><p><stripes:submit name="auth" value="Log Masuk" class="btn"/>&nbsp;&nbsp;<stripes:reset value="Isi Semula" name="reset" class="btn"/></td>
                                </tr>
                            </table>
                        </stripes:form>
                    </div>
                </fieldset>
            </div>
        </center>
    </body>
</html>