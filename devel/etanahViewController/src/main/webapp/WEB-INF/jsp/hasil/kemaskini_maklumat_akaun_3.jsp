<%--
    Document   : kemaskini_maklumat_akaun_3
    Created on : Wed 2012 August 01 12:10 AM
    Author     : haqqiem
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        var flag = ${actionBean.addFlg};
        if(flag){
            self.close() ;
            self.opener.refreshRekod($("#noAkaun").val());
        }
    });

    function validate(){
        var kt = document.getElementById('kod');
        var yy = document.getElementById('thn');
        var ty = document.getElementById('type');
        var am = document.getElementById('amt');
        var ct = document.getElementById('msg');
        if(((kt.value)=='')||((yy.value)=='')||((ty.value)=='')||((am.value)=='')||((ct.value)=='')){
            alert('Medan yang bertanda * adalah mandatori. Sila masukkan semula.');
            return false;
        }else{return true;}
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890.";
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

    function autoChange(elmnt, v){
        if(v == '99030'){
            $('#type').val('KR');
        }else{
            $('#type').val('DT');
        }

    }

    function fmtNumber(elmnt,v){
        elmnt.value = parseFloat(v).toFixed(2);;
    }
</script>
<title>..: Tambah Transaksi :..</title>
<s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean" id="kemaskini_akaun_3">
    <s:messages/>
    <s:errors/>
    <s:hidden name="rbtAkaun" id="noAkaun"/>
    <fieldset class="aras1">
        <legend>Maklumat Transaksi</legend>
        <p>
            <label><em>*</em> Perihal Transaksi :</label>
            <s:select name="kodTransaksi" style="width:300px;" id="kod" onblur="autoChange(this,this.value)">
                <s:option value="">Sila Pilih ...</s:option>
                <c:forEach items="${actionBean.senaraiKodTransaksi}" var="i" >
                    <s:option value="${i.kod}" >${i.kod} - ${i.nama}</s:option>
                </c:forEach>
            </s:select>
        </p>
        <p>
            <label><em>*</em> Tahun Transaksi :</label>
            <s:text name="tahun" id="thn" size="6" onkeyup="validateNumber(this, this.value)" maxlength="4"/>
        </p>
        <p>
            <label><em>*</em> Jenis Transaksi :</label>
            <s:select name="jenisTransaksi" style="width:185px;" id="type">
                <s:option value="">Sila Pilih ...</s:option>
                <s:option value="DT">Transaksi Debit</s:option>
                <s:option value="KR">Transaksi Kredit</s:option>
            </s:select>
        </p>
        <p>
            <label><em>*</em> Amaun Transaksi (RM):</label>
            <s:text name="amaun" id="amt" size="7" style="text-align:right;" onkeyup="validateNumber(this, this.value)" onblur="fmtNumber(this, this.value);"/>&nbsp;
        </p>
        <p>
            <label><em>*</em>Catatan :</label>
            <s:textarea name="catatan" cols="38" rows="6" id="msg"/>&nbsp;
        </p>
    </fieldset>
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <s:submit name="Step7" value="Simpan" class="btn" onclick="return validate();"/>
                    <s:button name="" value="Tutup" class="btn" id="ttp" onclick="self.close();"/>&nbsp;
                </td>
            </tr>
        </table>
    </div>
</s:form>
