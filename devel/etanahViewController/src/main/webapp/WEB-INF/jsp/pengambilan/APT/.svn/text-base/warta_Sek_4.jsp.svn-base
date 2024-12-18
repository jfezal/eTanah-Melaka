<%-- 
    Document   : warta_Sek_4
    Created on : Feb 13, 2020, 4:44:17 PM
    Author     : zipzap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {

        var kodNegeri = $('#kodNegeri').val();
        var stageId = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        var resultCase = $('#keputusanKes').val();

        if (kodNegeri == '04' && (stageId == '17ArahanMaklumanKpsn')) {
            if (kodUrusan == 'PTPT') {
                if ($("#keputusanId").val() == 'PV') {
                    $(".warning").html("Arahan : Terdapat " + $("#keputusanName").val() + ". Sila rujuk Tab Laporan Kerosakan Tanah");
                } else {
                    $(".warning").html("Arahan : " + $("#keputusanName").val() + ". Tiada perubahan");
                }
            }
        }
        else if (resultCase == "T") {
            if (kodUrusan == 'JMRE') {
                $('#page_id_006').hide();//hide tab draf MMK
            }
        }

    });
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form  beanclass="etanah.view.stripes.pengambilan.share.common.WartaSeksyen4ActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="kodKeputusan.kod" id="keputusanId" />
    <s:hidden name="kodKeputusan.nama" id="keputusanName" />
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <s:hidden name="idPermohonan" id="idMohon" value="${actionBean.idPermohonan}"/>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>Kemasukan Warta Seksyen 4</legend>
            
            <p>
                <label for="noFail">No Fail JKPTG :</label>
                <s:text name="failJKPTG" value= "${actionBean.failJKPTG}" size="20"/>
            </p>
            <p>
                <label for="noWarta">No warta :</label>
                <s:text name="noWarta" value= "${actionBean.noWarta}" size="20"/>
            </p>
            <p>
                <label for="trhWarta">Tarikh Warta :</label>
                <s:text name="trhWarta" size="30" id="trhLahir" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label for="keterangan">Keterangan Warta :</label>
                <s:textarea name="keteranganWarta" value="${actionBean.keteranganWarta}" rows="7"cols="50"/>
            </p>
        </fieldset>
        <br>
        <center><s:button name="simpanWarta" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/><s:submit name="hantar" id="save" value="Hantar" class="longbtn" /></center>
    </div>
</s:form>
