<%-- 
    Document   : tiadaGantirugi
    Created on : Aug 4, 2010, 5:52:25 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pengambilan.TiadaGantirugiActionBean">
           <s:messages/>
           <s:errors/>
<c:if test="${form1}">
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Surat Makluman Tiada Gantirugi</legend>
                    <div class="content" align="left">
                        <table border="0" width="80%" >
                            <td>
                                <font color="black">
                                Sila klik pada butang jana dokumen untuk penyediaan surat kepada agensi
                                pemohon dan tuan tanah/ orang berkepentingan untuk mengeluarkan surat makluman bahawa tiada gantirugi untuk jumlah
                                yang dituntut terhadap kerosakan yang berlaku kepada pemilik tanah
                                </font>
                            </td>
                        </table>
                    </div>
                </fieldset>
           </div>

           <div align="right">
           <%-- <table>
                <tr align="right"><td>
                <div style="background-color: white;" align="right">

                </div> <font style="font-family: Arial; font-size: x-small; font-weight: bold;">
                    <s:button name="janaSurat" value="Jana Dokumen" class="longbtn"/></font>
            </td></tr>
        </table>--%>
      </div>
</c:if>
</s:form>