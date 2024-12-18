<%-- 
    Document   : add_borangN_adat
    Created on : Jul 8, 2011, 12:19:41 PM
    Author     : Akmal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<script type="text/javascript">

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.BorangNAdatActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Bicara</legend><br/>
            <table>
            <tr>
               <td><label>Nama :</label></td>
               <td><s:text name="namaTerlibat" size="50"/></td>   
            </tr>
        
            <tr>
                <td><label>Tarikh Bicara :</label></td>
                <td><s:text name="tarikhBicara" id="date1" class="datepicker" formatPattern="dd/MM/yyyy" /></td>   
            </tr>
       
            <tr>
                <td><label>Tempat Bicara :</label></td>
                <td><s:text name="tempatBicara" size="50" /></td>
            </tr>
            </table>

            <p align="center">
                <s:reset name="reset" value="Isi Semula" class="btn"/>
                <s:button name="" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
            </p>
        </fieldset>
    </div>
</s:form>
