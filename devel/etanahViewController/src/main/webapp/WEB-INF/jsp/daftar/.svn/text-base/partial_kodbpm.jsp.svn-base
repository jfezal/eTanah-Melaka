<%-- 
    Document   : partial_kodbpm
    Created on : Jan 8, 2010, 12:36:10 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {
        $("#namaDaerah").val($("#kodDaerah").val());

        $("#namaDaerah").change(function() {
            var valueDaerah = $("#namaDaerah").val();
            $("#kodDaerah").val(valueDaerah);
        });
        $("#kodDaerah").blur(function() {
            var valueDaerah = $("#kodDaerah").val();
            $("#namaDaerah").val(valueDaerah);
        });
        $("#kodBPM").blur(function() {
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
        $('#selectKodSeksyen').val($('#kodSeksyen').val());

    });

    function filterKodSeksyen() {
        var kodBPM = $("#namaBPM").val();
        //            alert(katTanah);
        var kd = $("#kodDaerah").val();
//        alert(kd);
//        alert(kodBPM);


        $.post('${pageContext.request.contextPath}/pendaftaran/kemasukan_perincian_hakmilik?senaraiSeksyenByBPM&kodBpm=' + kodBPM + '&kodDaerah=' + kd,
                function(data) {
                    if (data != '') {
                        $('#partialKodSeksyen').html(data);
                        //                    $.unblockUI();
                    }
                }, 'html');

    }

//    $("#loadingbar").bind("ajaxSend", function() {
//        $(this).show();
//        $("#partialKodSeksyen").hide();
//
//    }).bind("ajaxComplete", function() {
//        $(this).hide();
//        $("#partialKodSeksyen").show();
//
//    });




</script>
<s:form partial="true" beanclass="etanah.view.stripes.KemasukanPerincianHakmilikActionBean">
    <p>
        <label>Bandar / Pekan / Mukim</label>
        <s:text name="kodBPM" size="4" id="kodBPM" readonly="true"/> -
        <s:select name="namaBPM" id="namaBPM" onchange="filterKodSeksyen();">
            <s:option value="">-- Sila Pilih --</s:option>
            <s:options-collection collection="${actionBean.listBandarPekanMukim}" label="nama" value="bandarPekanMukim"/>
        </s:select>

    </p>
    <!--<center><div id="loadingbar"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/></div></center>-->
    <div id="partialKodSeksyen">
    </div>
</s:form>