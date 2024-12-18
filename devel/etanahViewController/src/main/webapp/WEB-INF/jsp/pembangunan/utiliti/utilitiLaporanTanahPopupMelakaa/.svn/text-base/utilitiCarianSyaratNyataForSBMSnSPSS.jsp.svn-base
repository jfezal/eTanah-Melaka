<%-- 
    Document   : laporan_tanahV2PopupSyarat_SBMS_TSPSS
    Created on : Apr 17, 2014, 5:28:51 PM
    Author     : khairul.hazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    
    function searchKodSyaratNyata(idPlot,forEdit,id){
        var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?showFormCariKodSyaratNyataSBMSnTSPSS&idPlot='+idPlot+'&forEdit='+forEdit+'&idPermohonan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000px,height=1000px,scrollbars=yes");
    }

    function searchKodSekatan(idPlot,forEdit,id){
        var url = '${pageContext.request.contextPath}/utiliti/laporanTanah?showFormCariKodSekatanSBMSnTSPSS&idPlot='+idPlot+'&forEdit='+forEdit+'&idPermohonan='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }
    
</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>         
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.utiliti.UtilitiLtActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">

            <div id ="refresh">
                <table border="0" width="85%" align="center" cellspacing="5">
                    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
                    <s:hidden name="idPlot" value="${actionBean.idPlot}"/>
                    <s:hidden name="forEdit" value="${actionBean.forEdit}"/>

                    <c:if test = "${actionBean.forEdit eq 'NY'}">                                   
                        <tr>
                            <td> &nbsp; Syarat Nyata   </td>
                            <td><b>:  </b></td>
                            <td>
                                <s:textarea name="kodSyaratNyataBaru" id="syaratNyata" readonly="readonly" rows="5" cols="80"/>                                                                             
                            </td>
                        </tr>
                    </c:if>

                    <c:if test = "${actionBean.forEdit eq 'SK'}">  
                        <tr>
                            <td> &nbsp; Sekatan Kepentingan  </td>
                            <td><b>:  </b></td>
                            <td>
                                <s:textarea name="kodSekatanKepentinganBaru" readonly="readonly" id="sekatan" rows="8" cols="80"/>                                                                                                        
                            </td>
                        </tr>
                    </c:if>
                </table>


                <br><br>         
                <tr>
                <div align="center">
                    <c:if test = "${actionBean.forEdit eq 'NY'}">  
                        <td align="center"><s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSyaratNyata('${actionBean.idPlot}','${actionBean.forEdit}','${actionBean.idPermohonan}')" /></td>
                    </c:if>
                    <c:if test = "${actionBean.forEdit eq 'SK'}">  
                        <td align="center"><s:button class="btn" value="Cari" name="add" onclick="javascript:searchKodSekatan('${actionBean.idPlot}','${actionBean.forEdit}','${actionBean.idPermohonan}')" /></td>
                    </c:if>
                    <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/></td>                            
                </div>
                </tr>
            </div>
        </s:form>

