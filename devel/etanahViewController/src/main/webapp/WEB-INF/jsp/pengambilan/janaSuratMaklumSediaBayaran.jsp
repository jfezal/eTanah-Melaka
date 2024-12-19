<%-- 
    Document   : janaSuratMaklumSediaBayaran
    Created on : Aug 4, 2010, 5:52:25 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Penyediaan Surat Kepada Agensi Pemohon dan Tuan Tanah/ Orang Berkepentingan Untuk Perbicaran Menentukan Kos Gantirugi</title>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pengambilan.JanaSuratMaklumSediaBayaranActionBean">
           <s:messages/>
           <s:errors/>
           <c:if test="${form1}">
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklum Surat Sedia Bayaran
                    </legend>
                    <div class="content" align="">
                        <table border="0">
                            <td>
                            <font color="black" >
                                <td>
                                Sila klik pada butang "Jana Dokumen" untuk penyediaan surat maklum sedia bayaran kepada agensi
                                pemohon dan tuan tanah/orang berkepentingan.
                                </td>
                            </font>
                            </td>
                        </table>
                    </div>
                </fieldset>
           </div><br />
           </c:if>
       </s:form>
    </body>
</html>