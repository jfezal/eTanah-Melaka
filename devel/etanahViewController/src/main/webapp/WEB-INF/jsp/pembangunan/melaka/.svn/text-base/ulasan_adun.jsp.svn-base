<%-- 
    Document   : ulasan_adun
    Created on : Jul 26, 2011, 5:28:26 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:15em;        
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    function cariUlasan(){
        <%--if(confirm('Adakah anda pasti?')) {--%>
                var val = $('#idMohonAsal').val();
                <%--alert("val = " + val);--%>
                var url = '${pageContext.request.contextPath}/pembangunan/melaka/ulasanAdun?cariUlasan&idMohonAsal='+val;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            <%--}--%>
    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.UlasanAdunActionBean">
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Ulasan  ADUN Kawasan
            </legend>
            <div class="content" align="center">
                <table width="80%" border="0">
                    <tr><td id="tdLabel">ID Permohonan Terdahulu : </td>
                        <td><s:text name="idMohonAsal" maxlength="20" size="25" id="idMohonAsal"/> &nbsp;<s:button name="cari" id="carianUlasan" class="btn" value="Cari Ulasan" onclick="cariUlasan();"/></td>
                    </tr>
                    <tr><td id="tdLabel">ADUN Kawasan :</td>
                        <td id="tdDisplay">${actionBean.ulasanAdun.agensi.nama} </td>
                    </tr>
                    <tr><td id="tdLabel">Ulasan :</td>
                        <td id="tdDisplay">${actionBean.ulasanAdun.ulasan}</td>
                    </tr>
                </table>
              
            </div>
        </fieldset>
  </div>
</s:form>