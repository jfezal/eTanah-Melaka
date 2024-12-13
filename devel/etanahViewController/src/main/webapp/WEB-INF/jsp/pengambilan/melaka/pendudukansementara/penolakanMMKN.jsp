<%-- 
    Document   : penolakanMMKN
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
        <title>Penyediaan Surat Makluman Keputusan MB Kepada Agensi Pemohon dan Tuan Tanah/ Orang Berkepentingan</title>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pengambilan.PenolakanMMKNActionBean">
           <s:messages/>
           <s:errors/>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Penyediaan Surat Makluman Keputusan Permohonan kepada agensi pemohon
                    </legend>
                    <div class="content" align="">
                        <table border="0">
                            <td>
                            <font color="black">
                            Sila klik pada butang "jana dokumen" untuk penyediaan surat makluman  keputusan MB kepada agensi
                            pemohon dan tuan tanah/pihak berkepentingan
                            </font>
                            </td>
                        </table>
                    </div>
                </fieldset>
           </div><br />
      <%--     <div align="right">
            <table>
                <tr align="right"><td>
                <font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="janaSurat" value="Jana Dokumen" class="longbtn"/></font>
            </td></tr>
            </table>
      </div>--%>
            <%--<p align="right">
                <s:button name="janaSurat" value="Jana Surat" class="longbtn"/>&nbsp;
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
           </p>--%>
       </s:form>
    </body>
</html>