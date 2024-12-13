<%-- 
    Document   : kutipan_data_partialBPM
    Created on : Sep 25, 2013, 12:11:56 PM
    Author     : ei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {
        $("#namaDaerah").val($("#kodDaerah").val());
        $("#namaBPM").val($("#kodBPM").val());

        $("#namaDaerah").change(function() {
            var valueDaerah = $("#namaDaerah").val();
            $("#kodDaerah").val(valueDaerah);
        });
        $("#kodDaerah").blur(function() {
            var valueDaerah = $("#kodDaerah").val();
            $("#namaDaerah").val(valueDaerah);
        });
        $("#kodBPM").blur(function() {
//      alert("masuk123");
            var valueBPM = $("#kodBPM").val();
            $("#namaBPM").val(valueBPM);
            // $("#bandarPekanMukim").val(valueBPM);
        });
        $("#namaBPM").change(function() {
            var valueBPM = $("#namaBPM").val();
            $("#kodBPM").val(valueBPM);
            //$("#bandarPekanMukim").val(valueBPM);
        });
        $("#namaJenisHakmilik").change(function() {
            var valueJenisHakmilik = $("#namaJenisHakmilik").val();
            $("#kodJenisHakmilik").val(valueJenisHakmilik);
        });
        $("#kodJenisHakmilik").blur(function() {
            var valueJenisHakmilik = $("#kodJenisHakmilik").val();
            $("#namaJenisHakmilik").val(valueJenisHakmilik);
        });
    });

    function checkKod(elmnt, inputTxt) {
        var a = document.getElementById('kodBPM');
        if (isNaN(a.value)) {
            elmnt.value = RemoveNonNumeric(inputTxt);
            return;
        }
    }

    function RemoveNonNumeric(strString) {
        var strValidCharacters = "A";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for (intIndex = 0; intIndex < strString.length; intIndex++) {
            strBuffer = strString.substr(intIndex, 1);
            // Is this a number
            if (strValidCharacters.indexOf(strBuffer) > -1) {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function filterKodSeksyen() {

        var kodBPM = $("#namaBPM").val();
      
        //            alert(katTanah);
        var kd = $("#kodDaerah").val();
//        alert(kd);
//        alert(kodBPM);


        $.post('${pageContext.request.contextPath}/daftar/utiliti/kutipanData?senaraiSeksyenByBPM&kodBpm=' + kodBPM + '&kodDaerah=' + kd,
                function(data) {
                    if (data != '') {
                        $('#partialKodSeksyen').html(data);
                        //                    $.unblockUI();
                    }
                }, 'html');

    }
</script>
<s:form partial="true" beanclass="etanah.view.daftar.utiliti.KutipanDataActionBean">
    <c:if test="${actionBean.listKodSeksyenByBpm eq null}">
        <p>
            <label>Bandar / Pekan / Mukim</label>
            <s:text name="bpm" size="4" id="kodBPM" readonly="true"/> -&nbsp;
            <s:select name="namaBPM" id="namaBPM" onchange="filterKodSeksyen();" style="width:200">
               <s:option value="">-- Sila Pilih --</s:option>
                <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="bandarPekanMukim"/>
            </s:select>

        </p>
    </c:if>
    <div id="partialKodSeksyen">
    </div>
    <c:if test="${actionBean.seksyen eq '1'}">
        <p>
            <label>Seksyen :</label>
            
            <s:select name="hakmilik.seksyen.kod" value="${actionBean.hakmilik.seksyen.kod}" id="selectKodSeksyen" style="width:199pt">
                <s:option value="">Sila Pilih</s:option>
                <s:options-collection collection="${actionBean.listKodSeksyenByBpm}" label="nama" value="kod"/>
            </s:select>
        </p>
    </c:if>

</s:form>



