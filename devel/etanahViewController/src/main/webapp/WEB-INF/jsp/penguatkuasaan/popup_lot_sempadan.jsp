<%-- 
    Document   : popup_lot_sempadan
    Created on : Mar 4, 2010, 11:28:36 AM
    Author     : farah.shafilla
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.alphanumeric.js"></script>

<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
     <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Lot-lot Sempadan
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1">Arah :</td>
                        <td class="input1"><s:text name="no_pengenalan"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">No.Lot / Plot / PT :</td>
                        <td class="input1"><s:text name="nama"/></td>
                    </tr>
                     <tr>
                        <td class="rowlabel1">Bandar / Pekan / Mukim :</td>
                        <td class="input1"><s:text name="alamat"/></td>

                    </tr>
                    <tr>
                        <td class="rowlabel1">Status :</td>
                        <td class="input1"><s:text name="alamat2"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Kategori Kegunaan Tanah :</td>
                        <td class="input1"><s:text name="alamat3"/></td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Guna Tanah Semasa :</td>
                        <td class="input1"><s:text name="poskod"/></td>
                    </tr>                    
                </table>

                <br>
            </div>
        </fieldset>
     </div>
</s:form>