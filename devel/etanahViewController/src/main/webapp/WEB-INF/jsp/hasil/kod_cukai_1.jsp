<%-- 
    Document   : kod_cukai_1
    Created on : Aug 18, 2014, 12:00:53 PM
    Author     : haqqiem
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#sn").hide();
    });
    
    function filterBpm(f,kod){
        $.get("${pageContext.request.contextPath}/hasil/kod_cukai?filterByBpm&kod=" + kod,
        function(data){
            $('#jab').html(data);
            $("#sn").hide();
        },'html');
    }
    
    function closed(){
        self.opener.refreshRekod();
        self.close();
    }
    
    function updateTotal (inputTxt,id){
        var a = document.getElementById(id);
        inputTxt.value = RemoveNonNumeric(a.value);
    }
        
    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "0123456789.";
        var strReturn = "";
        var strBuffer = "0";
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
        
    function displayDesc(syarat){
        $.get("${pageContext.request.contextPath}/hasil/kod_cukai?getPerihalSyaratNyata&kod=" + syarat,
        function(data){
            if(data=="0"){
                $("#sn").hide();
            }else{
                $("#sn").show();
                $("#perihal").val(data);
            }
        });
    }
</script>
<div id="jab">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.hasil.KodCukaiActionBean" id="kod_cukai">
    <s:text name="flag" value="${actionBean.flag}" id="flag" style="display:none;"/>
    <s:messages/>
    <fieldset class="aras1">
        <legend>Maklumat Kod Kadar Cukai</legend>
        <c:if test="${actionBean.flag eq 'edit'}">
            <p>
                <label>ID Kadar :</label>
                ${actionBean.kodKadarCukai.kod} &nbsp;
               <s:text name="kodKadarCukai.kod" value="${kodKadarCukai.kod}" style="display:none;"/>
            </p>
        </c:if>
        
        <p>
            <label>Bandar/Pekan/Mukim :</label>
            <c:if test="${actionBean.flag eq 'add'}">
                <s:select name="bpmBaru" id="bpm" value="${actionBean.bpmBaru}" onchange="filterBpm(this.form, this.value);">
                    <s:option label="Sila Pilih..." value="" />
                    <s:options-collection collection="${actionBean.senaraiBpm}" label="nama" value="kod" sort="nama"/>
                </s:select>
            </c:if>
            <c:if test="${actionBean.flag eq 'edit'}">
                ${actionBean.kodKadarCukai.bandarPekanMukim.nama}
            </c:if>
            <s:text name="bpmBaru" value="${actionBean.kodKadarCukai.bandarPekanMukim.kod}" style="display:none;"/>
        </p>
        
        <p>
            <label><em>*</em>Kod Guna Tanah :</label>
            <s:select name="gunaTanahBaru" id="gt" value="${actionBean.gunaTanahBaru}">
                <s:option label="Sila Pilih..." value="" />
                <s:options-collection collection="${actionBean.senaraiGunaTanah}" label="nama" value="kod" sort="nama"/>
            </s:select>
        </p>
        
        <p>
            <label><em>*</em>Kadar Meter <sup>2</sup> :</label>
            <s:text name="kodKadarCukai.kadarMeterPersegi" value="${actionBean.kodKadarCukai.kadarMeterPersegi}" 
                    onkeyup="updateTotal(this,'kadarP')" id="kadarP" formatPattern="#,##0.000"/>
        </p>
        
        <p>
            <label><em>*</em>Kadar Minima :</label>
            <s:text name="kodKadarCukai.kadarMinimum" value="${actionBean.kodKadarCukai.kadarMinimum}" 
                    onkeyup="updateTotal(this,'kadarM')" id="kadarM" formatPattern="#,###,##0.00"/>
        </p>
        
        <%--p>
            <label>Kod Cawangan :</label>
            <s:select name="kodCaw" id="kc" value="${actionBean.kodCaw}">
                <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod" sort="name"/>
            </s:select>
        </p--%>
        
        <p>
            <label>Kod Syarat Nyata :</label>
            <s:select name="gunaSyaratBaru" id="ksn" style="width:50%" onchange="displayDesc(this.value)">
                <s:option label="Sila Pilih..." value="" />
                <c:forEach items="${actionBean.senaraiSyarat}" var="i" >
                    <s:option value="${i.kod}">${i.kod} - ${i.syarat}</s:option>
                </c:forEach>
            </s:select>
        </p>
        
        <p id="sn">
            <label>&nbsp;</label>
            <s:textarea name="" id="perihal" rows="5" cols="60"/>&nbsp;
        </p>
        
        <p>
            <label>Aktif :</label>
            <s:select name="status" id="stat" value="${actionBean.status}">
                <s:option label="Aktif" value="Y" />
                <s:option label="Tidak Aktif" value="T" />
            </s:select>
        </p>
        
        <table border="0" bgcolor="green" width="100%">
            <tr>
                <td align="right">
                    <c:if test="${actionBean.flag eq 'add'}">
                        <s:submit name="Step3" value="Simpan" class="btn" id="simpan"/>
                        <s:button name=" " value="Tutup" class="btn" id="close" onclick="self.close();"/>
                    </c:if>
                    <c:if test="${actionBean.flag eq 'edit'}">
                        <s:submit name="Step4" value="Kemaskini" class="btn" id="kemas"/>
                        <s:button name=" " value="Tutup" class="btn" id="close" onclick="closed();"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </fieldset>

</s:form>
</div>