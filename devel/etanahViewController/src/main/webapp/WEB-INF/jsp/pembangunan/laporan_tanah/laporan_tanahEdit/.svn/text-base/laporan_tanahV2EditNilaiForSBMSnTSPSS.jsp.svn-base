<%-- 
    Document   : laporan_tanahV2EditNilaiForSBMSnTSPSS
    Created on : Apr 17, 2014, 4:53:58 PM
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
    
    function searchKodSyaratNyata(idPlot){
        var url = '${pageContext.request.contextPath}/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS?showFormCariKodSyaratNyata&idPlot='+idPlot;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }

    function searchKodSekatan(index){
        var url = '${pageContext.request.contextPath}/pembangunan/carianSyaratSekatan?showFormCariKodSekatan&index='+index;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }
    
    function save1(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPopup();
            self.close();
        },'html');       
    }
    
    function save2(){
        self.opener.refreshPopup();
        self.close();    
    }
</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>         
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahV2ActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">


            <table border="0" width="85%" align="center" cellspacing="5">
                <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
                <s:hidden name="idPlot" value="${actionBean.idPlot}"/>
                <%--<s:hidden name="foredit" value="${actionBean.foredit}"/>--%>
                <%--<s:hidden name="kodPemilikan" value="${actionBean.kodPemilikan}"/>--%>


                <tr>
                    <td><label>Premium Tambahan (jika ada) :</label></td>
                    <td>
                        <c:if test="${actionBean.mpp.keteranganKadarPremium ne null}">
                            <s:textarea name="premiumTambahan" rows="2" cols="30"/>
                        </c:if>
                        <c:if test="${actionBean.mpp.keteranganKadarPremium eq null}">
                            <s:textarea name="premiumTambahan" rows="2" cols="30"/>
                        </c:if>
                    </td>
                </tr>

                <tr>
                    <td><label>Denda Premium (jika ada):</label></td>
                    <td>
                        <c:if test="${actionBean.mpp.keteranganKadarDaftar ne null}">
                            <s:textarea name="dendaPremium" rows="2" cols="30"/>
                        </c:if>
                        <c:if test="${actionBean.mpp.keteranganKadarDaftar eq null}">
                            <s:textarea name="dendaPremium" rows="2" cols="30"/>
                        </c:if>
                    </td>
                </tr>

                <tr>
                    <td><label>Sewa Tahunan Baru :</label></td>
                    <td>
                        <c:if test="${actionBean.mpp.keteranganCukaiBaru ne null}">
                            <s:textarea name="sewaTahunan" rows="2" cols="30"/>
                        </c:if>
                        <c:if test="${actionBean.mpp.keteranganCukaiBaru eq null}">
                            <s:textarea name="sewaTahunan" rows="2" cols="30"/>
                        </c:if>
                    </td>
                </tr>
            </table>

    </div>

    <br><br>       
    <tr>
    <div align="center">
        <td><s:submit name="simpanPopup" id="save" value="Simpan" class="btn"/> </td>
        <td><s:button name="" id="tutup" value="Tutup" class="btn" onclick="save2()"/></td>                         
    </div>
</tr>                   
</s:form>

