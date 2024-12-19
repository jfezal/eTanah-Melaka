<%-- 
    Document   : papar_syarat_nyata
    Created on : Dec 30, 2009, 8:44:25 PM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

 <script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>

<style type="text/css">
    #tdLabel {
            color:#003194;
            display:block;
            float:left;
            font-family:Tahoma;
            font-size:13px;
            font-weight:bold;
            margin-left:30px;
            margin-right:0.5em;
            text-align:right;
            width:10em;
        }

   #tdDisplay {
            color:black;
            font-size:13px;
            font-weight:normal;
            float:left;
            width:50em;
        }
</style>

<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikSingleActionBean">
    <fieldset class="aras1">
        <legend>Penerangan Syarat Nyata</legend>
        <table border="0" width="100%">
            <tr><td colspan="2">&nbsp;</td>
            </tr>
            <tr><td id="tdLabel">Kod :</td>
                <td id="tdDisplay">${actionBean.kodSyaratNyata.kod}&nbsp;</td>
            </tr>
            <tr><td id="tdLabel">Keterangan :</td>
                <td id="tdDisplay">${actionBean.kodSyaratNyata.syarat}&nbsp;</td>
            </tr>
        </table>
    </fieldset>
        <br>
        <table width="100%">
            <tr>
                <%--<s:hidden name="permohonanUlasan.tarikhUlasan" value="<%=new java.util.Date()%>" />--%>
                <td align="right"><s:button name="" id="close" value="Tutup" class="btn"/></td>
            </tr>
        </table>
    </s:form>
</div>

