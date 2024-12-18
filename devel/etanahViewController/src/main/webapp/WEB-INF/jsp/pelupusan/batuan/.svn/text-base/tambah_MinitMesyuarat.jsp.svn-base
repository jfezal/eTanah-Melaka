<%-- 
    Document   : tambah_PerakuanPTD
    Created on : Friday June 10 2011, 11:28AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    
    $(document).ready( function(){
       
        //maximizeWindow();
        <%-- alert("pemohon");--%>
    <c:if test="${!flag}">
            opener.refreshDrafJKBB();
            <%-- alert("selfPemohon");--%>
            self.close();
    </c:if>
            });
         function addSeterusnya(){
             window.open("${pageContext.request.contextPath}/pelupusan/maklumat_pemohon1?showTambahLatarbelakangPemohon", "eTanah",
             "status=0,toolbar=0,location=0,menubar=0,width=710,height=400,scrollbars=yes");
         }
</script>
<style type="text/css">
    input.error { background-color: yellow; }
</style>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MinitMesyuaratBatuan">
        <fieldset class="aras1">
            <legend><b><font style="text-transform: capitalize">MINIT KEPUTUSAN Y.A.B KETUA MENTERI</font></b></legend>
            <table border="0" width="100%" id="tblhuraian" cellspacing="0" align="center">
                <table>
                    <tr><td>
                        </td>
                        <td
                                <label><tr><td valign="top">${actionBean.subTajukPopUp} </td>
                                        <s:hidden name="subTajukPopUp" id="subTajukPopUp"/>
                                        <td><p><s:textarea name="kandunganPopUp" id="kandunganPopUp" cols="80" rows="5"/>
                                            </p></td>
                                    </tr>
                                </label>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td><center>
                            <s:submit name="simpanMinitMesyPopUp" id="simpan" value="Simpan" class="btn" />
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></center>
                        </td>
                    </tr>
                </table>
            </table>
        </fieldset>
        
    </s:form>
</div>
