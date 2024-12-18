<%--

<%@ page contentType="text/html;charset=windows-1252" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>.: e-Tanah :.</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
        <script type="text/javascript"
        src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/scripts/jquerycornerv1.99.js"></script>
        <script type="text/javascript">
            $(function() {        
                $('#login').corner("20px");
                $('#login2').corner("10px");
                $('#IDPengguna').focus().select();
            });
            
        </script>
        <style type="text/css">
            input.error, textarea.error {
                color: red;
                background-color: yellow;
            }
        </style>

    </head>
    <body>
        <center>
            <br><br><br><br><br><br><br><br>
            <div class="subtitle" style="width:50%;">
                <stripes:errors/>
                <br>
                <div id="login" style="background-image:url('<%= request.getContextPath()%>/pub/images/bgfront.gif');background-position: center">

                    <stripes:form beanclass="etanah.view.stripes.FirstTimeLoginBean" id="loginform">
                        <table border="0" align="center">

                            <tr align="center">
                                <td colspan="2"><p></p></td>
                            </tr>
                            <tr align="center">
                                <td colspan="2"><p>Selamat Datang ke Sistem e-Tanah.<br>Sila masukkan ID Pengguna dan Kata Laluan <br>di dalam ruangan yang disediakan dengan betul.</td>
                            </tr>
                            <tr>
                                <td align="right">ID Pengguna :</td>
                                <td class="input1">
                                    <stripes:text name="IDPengguna" size="30" id="IDPengguna"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">Kata Laluan :</td>
                                <td class="input1"><stripes:password size="30" name="kataLaluan" id="password"/></td>
                            </tr>
                            <tr>
                                <td align="right">Kad Pengenalan :</td>
                                <td class="input1"><stripes:text name="noPengenalan" size="30" id="kadPengenalan"/></td>
                            </tr>
                            <tr align="center">
                                <td colspan="2"><p><stripes:submit name="login" value="Log Masuk" class="btn"/>&nbsp;&nbsp;<stripes:button id="reset" value="Isi Semula" name="" class="btn" onclick="clearText('loginform');"/>&nbsp;&nbsp;</td>
                            </tr>
                            <tr><td>&nbsp;</td></tr>
                            <tr align="center">
                                <td colspan="2">
                                    <p>Daftar sebagai pengguna baru. Sila<a href="daftar_pengguna"> klik </a>disini</p>
                                </td>
                            </tr>
                        </table>
                        <br>
                    </stripes:form>

                </div>
                <br>
                <div id="login2" class="aras1">
                    <font color="red">Perhatian :</font>
                    <h5>Penggunaan tanpa kebenaran sistem ini adalah dilarang dan boleh <br>mengakibatkan anda didakwa di atas pendakwaan awam dan jenayah.</h5>
                </div>


            </div>
        </center>
    </body>
</html>--%>