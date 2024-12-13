<%-- 
    Document   : utiliti_ringkasan_kes
    Created on : Mar 13, 2014, 10:00:17 AM
    Author     : Siti Fariza
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script language="javascript" type="text/javascript">
    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function validateForm(){
        alert("aaaaaa");
        if($('#ulasanRingkasanKes').val()=="")
        {
            alert('Sila masukkan ringkasan kes terlebih dahulu');
            $('#ulasanRingkasanKes').focus();
            return false;
        }
        return true;
    }
    
    function validateForm2(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idPermohonanCarian').focus();
            return false;
        }

        return true;
    }

//    function papar(){     
//       var idp = $("#idPermohonan").val();
//       
//       // alert("---idmohon---:"+idp);
////       var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
//       var url = 'http://10.66.128.30:9001/reports/rwservlet?negeri1&ENFRK_NS.rdf&desname=1395827659437&p_id_mohon=' +idp;
//        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");    
//    }
    
     function doViewReport(v) {
//        alert("--dok id--:"+v);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
</script>
<style type="text/css">
    p{
        color:black;
        font-weight:normal;
        font-size:13px;
        padding-top:2px;
        padding-bottom:2px;
        line-height: 15pt ;
        text-align: justify;
    }


</style>


<s:form beanclass="etanah.view.penguatkuasaan.utiliti.UtilitiRingkasanKesActionBean">
    <s:messages/>
    <s:errors/>

    <%--<s:text name="permohonan.idPermohonan" />--%>

    <div class="subtitle">
        <fieldset class="aras1">


            <legend>Carian Id Permohonan</legend>
            <br>
            <p><label for="idPermohonan"><em>*</em>Id Permohonan:</label>
                <input type="text" name="idPermohonan" id="idPermohonanCarian" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchNoSerahan" value="Cari" class="btn" onclick="return validateForm2()"/>
            </p><br/>


            <legend>
                Ringkasan Kes
            </legend>
            <p>
                <label>Id Permohonan :</label>
                ${actionBean.idPermohonan} &nbsp;
                <s:hidden name="idPermohonan" id="idPermohonan"/>
                <s:hidden name="permohonanLaporanUlasan.idLaporUlas" id="idLaporUlas"/> 
            </p><br/>
            <p>
                <label><em>*</em>Ringkasan Kes :</label>
                <s:textarea name="ulasan" id="ulasanRingkasanKes" cols="50" rows="10" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text"/>
            </p>
            <br/>
            <table border="0" width="100%">
                <tr>
                    <td align="center">
                        <%--<s:button name="reset" value="Isi Semula" class="btn" onclick="return test(this.form);"/>--%>
                        <s:submit name="simpan" class="btn" value="Simpan"/>  
                        <s:submit name="genReport" id="report" value="Jana Dokumen" class="longbtn"/>&nbsp;
                        <s:button name="viewReport" value="Papar Dokumen" class="longbtn" onclick="doViewReport('${actionBean.senaraiKandungan[0].dokumen.idDokumen}');"/>&nbsp;
                    </td>
                    
                </tr>
            </table>
                       

            

        </fieldset>
    </div>



</s:form>


