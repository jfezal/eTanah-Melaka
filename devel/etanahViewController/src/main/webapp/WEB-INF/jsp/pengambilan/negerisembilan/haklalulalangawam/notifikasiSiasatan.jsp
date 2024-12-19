<%-- 
    Document   : notifikasiSiasatan
    Created on : Aug 4, 2010, 5:52:25 PM
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

        <s:form beanclass="etanah.view.stripes.pengambilan.NotifikasiSiasatanActionBean">
           <s:messages/>
           <s:errors/>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                       Notifikasi
                    </legend>
                    <div class="content" align="">
                        <table border="0">
                            <font color="black" style=" font-size: x-small">
                            Sila adakan siasatan untuk permohonan Hak Lalu Awam
                            </font>
                        </table>
                    </div>
                </fieldset>
           </div><br />
           <div align="right">
            <table>
                <tr align="right"><td>
                <font style="font-family: Arial; font-size: x-small; font-weight: bold;"><s:button name="janaSurat" value="Jana Dokumen" class="longbtn"/></font>
            </td></tr>
            </table>
      </div>
       </s:form>
