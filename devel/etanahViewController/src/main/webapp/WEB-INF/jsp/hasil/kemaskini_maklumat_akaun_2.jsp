<%-- 
    Document   : kemaskini_maklumat_akaun_2
    Created on : Fri 2012 June 22 10:55 AM
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
        var amt = ${actionBean.transaksi.amaun};
        var flag = ${actionBean.saveFlg};
        $('#amt').val(amt.toFixed(2));
            if(flag){
                self.close() ;
                self.opener.refreshRekod($("#noAkaun").val());
            }
    });
</script>
<script type="text/javascript">
    function openDialogBox(f){
        var notes = document.getElementById('msg');
        alert(notes.value);
        if(notes.value == ''){alert('Sila Masukkan Catatan.');$('#msg').focus();return false;}
        else{return true;}
    }

    function tutup(){
        self.close() ;
        opener.confirmRefresh() ;
    }

    function validate(){
        var am = document.getElementById('amt');
        var ct = document.getElementById('msg');
        if(((am.value)=='')||((ct.value)=='')){
            alert('Medan yang bertanda * adalah mandatori. Sila masukkan semula.');
            return false;
        }else{return true;}
    }

    function fmtNumber(elmnt,v){
        elmnt.value = parseFloat(v).toFixed(2);;
    }
    
    
  function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
</script>

<title>..: Maklumat Transaksi :..</title>
<s:form beanclass="etanah.view.stripes.hasil.KemaskiniMaklumatAkaunActionBean" id="kemaskini_akaun" name="forms1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="rbtAkaun" id="noAkaun"/>
    <s:text name="idTransaksi" value="${actionBean.idTransaksi}" style="display:none;"/>
    <fieldset class="aras1">
        <legend>Maklumat Transaksi</legend>
        <p>
            <label> Perihal Transaksi :</label>
            ${actionBean.transaksi.kodTransaksi.kod} - ${actionBean.transaksi.kodTransaksi.nama}&nbsp;
        </p>
        <p>
            <label> Tarikh Transaksi :</label>
            <fmt:formatDate value="${actionBean.transaksi.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss a"/>&nbsp;
        </p>
        <p>
            <label> Tahun Transaksi :</label>
            <s:text name="transaksi.untukTahun" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
            &nbsp;
        </p>
        <p>
            <label> Status Transaksi :</label>
            ${actionBean.transaksi.status.nama}&nbsp;
        </p>
        <p>
            <label> Amaun Transaksi (RM):</label>
            <s:text name="cukai" value="${actionBean.transaksi.amaun}" id="amt" size="7" style="text-align:right;" onblur="fmtNumber(this, this.value);"/>&nbsp;
        </p>
        <p>
            <label><em>*</em>Catatan :</label>
            <s:textarea name="catatan" cols="38" rows="6" id="msg"/>&nbsp;
        </p>
    </fieldset>
    <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <s:submit name="Step6" value="Simpan" class="btn" onclick="return validate();"/>
                    <s:button name="" value="Tutup" class="btn" id="ttp" onclick="self.close();"/>&nbsp;
                </td>
            </tr>
        </table></div>
</s:form>