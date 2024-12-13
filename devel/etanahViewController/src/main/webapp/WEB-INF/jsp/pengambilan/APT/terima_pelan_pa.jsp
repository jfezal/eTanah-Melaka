<%--
    Document   : maklumat_hakmilik_pengambilan
    Created on : 12-Jan-2010, 18:31:55
    Author     : nordiyana
--%>

<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript">
    $(document).ready(function() {


        var len = $(".daerah").length;

        for (var i = 0; i <= len; i++) {
            $('.hakmilik' + i).click(function() {
                window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik=" + $(this).text(), "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
            });
        }
    });

    function hapusA(frm, value1, value2) {
        var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?hapusA&idPermohonan=' + value1 + '&id=' + value2;
        if (confirm("Adakah anda pasti untuk hapus dokumen?")) {
            frm.action = url;
            frm.submit();
        }
    }
    function hapusB(frm, value1, value2) {
        var url = '${pageContext.request.contextPath}/pengambilan/borang_a_b?hapusB&idPermohonan=' + value1 + '&id=' + value2;
        if (confirm("Adakah anda pasti untuk hapus dokumen?")) {
            frm.action = url;
            frm.submit();
        }
    }

  
    function popupBorangA(idMohon, idBorangPerPermohonanA) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/borang_a_b?tambahBorangA&idPermohonan=" + idMohon + "&idBorangPerPermohonanA=" + idBorangPerPermohonanA, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function popupBorangB(idMohon, idBorangPerPermohonanB) {
//            alert(idMH);

        window.open("${pageContext.request.contextPath}/pengambilan/borang_a_b?tambahBorangB&idPermohonan=" + idMohon + "&idBorangPerPermohonanB=" + idBorangPerPermohonanB, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=1000,height=700,scrollbars=yes");
    }
    function doViewReport(v) {
        var randomnumber = Math.floor((Math.random() * 100) + 1);
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, randomnumber, "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doPopupDetails(val) {
        var url = '${pageContext.request.contextPath}/common/maklumat_permohonan?view&idPermohonan=' + val + '&idHakmilik=' + idHakmilik;
        window.open(url, "eTanah", "status=0,toolbar=0,location=0,menubar=0,width=1000,height=768,scrollbars=yes");
    }



</script>

<s:form beanclass="etanah.view.stripes.pengambilan.sek8.TerimaPelanPAActionBean" name="AB" id="AB">

    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Perincian Maklumat

            </legend>

            <p>
                <label>No Rujukan : </label>
                <s:text id="noFail" name="noFail" />&nbsp;
            </p>

            <p>
                <label>Tarikh Terima :</label>
                <s:text id="tarikhTerima" name="tarikhTerima" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>

            <s:hidden name="idPermohonan"/>

            <br>                
            <label>&nbsp;</label>&nbsp;&nbsp;           
            <!--<--s:button name="save" class="longbtn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>-->
            <s:submit name="save" id="save" value="Simpan" class="longbtn"/> 
            <s:submit name="hantar" id="hantar" value="Hantar" class="longbtn"/>        
        </fieldset>

    </div>

</s:form>
