<%-- 
    Document   : rekodBayaranSediaSurat
    Created on : Jul 17, 2020, 1:17:04 PM
    Author     : NURBAIZURA
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">



</script>
        
        
        <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

        <s:form beanclass="etanah.view.stripes.pengambilan.aduan.RekodBayaranSediaSuratActionBean">

            <fieldset class="aras1">

        <p align="center">
             <table class="tablecloth">
                <tr>
                   <th>Maklumat Hakmilik</th>
                
                <tr>
                <tr>
                    <td width="60%">
                        <div>
                        <display:table class="tablecloth" name="${actionBean.listHakmilikPermohonan}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_borang_BantahanMLK" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                             <display:column title="Hakmilik" sortable="true">${line.idHakmilik}</display:column>
                             <display:column title="Jumlah Penerima" sortable="true">${line.jumPihak}</display:column>
                                                               <display:column title="Jumlah Tuntutan" sortable="true">${line.jumlahTuntutan}</display:column>

                        </display:table>
              
                        </div>
                    </td>
                </tr>
                
            </table>
        </p> 

    </fieldset>

</s:form>



