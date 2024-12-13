<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">
    function validation() {
        if ($("#idH").val() == "") {
            alert('Sila masukkan " ID Hakmilik " terlebih dahulu.');
            $("#idH").focus();
            return true;
        }
    }


    function save1(event, f) {
        // if(validation()){

        // }
        // else{
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
//                    self.opener.refreshPageHakmilik();
                    self.close();
                }, 'html');
        // }
    }
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click(function() {
            setTimeout(function() {
                self.close();
            }, 100);
        });
    });

    function filterBandarPekanMukim() {
        var kodDaerah = $("#daerah").val();
        alert(kodDaerah);
        $.post('${pageContext.request.contextPath}/pelupusan/utilitiReportNs?senaraiKodBPM&kodDaerah=' + kodDaerah,
                function(data) {
                    if (data != '') {
                        $('#partialKodBPM').html(data);
                        //                    $.unblockUI();
                    }
                }, 'html');

    }

    function moduledet(val) {
        //alert("TESTING");
        $.get('${pageContext.request.contextPath}/pengambilan/maklumat_lotpengambilan?findKodSeksyen&kod=' + val,
                function(data) {
                    $("#kodS").replaceWith($('#kodS', $(data)));
                }, 'html');
    }

    function copyAdd(id) {
//        alert("Testing");
//        alert(id);
        var text = document.getElementById("area");
        var text1 = document.getElementById("area2");
//        alert(text1);
        if ($('input[name=add1]').is(':checked')) {

//            $('#noLotView').hide();
//            $('#noLot').show();
            text1.style.display = "block";
            text.style.display = "none";
//            alert("Testing2");
//            $('.noLot_' + id).attr('disabled', '');
        } else {
//            alert("test");
            text.style.display = "block";
            text1.style.display = "none";
        }
    }



</script>


<div class="subtitle">
    <%--<s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean" >--%>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pengambilan.share.common.RekodKpsnMahkamahActionBean" > <fieldset class="aras1">
            <legend>
                Kemasukan Maklumat Keputusan Mahkamah
            </legend>

            <br>
            <div class="row" id="area" style="display: none;" >
                <p>
                    <label for="Keputusan Mahkamah">Keputusan Mahkamah :</label>
                    <s:select name="kodLot" disabled="true" class="kodLot" value="${actionBean.permohonanMahkamah.kodKeputusanMahkamah}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKpsnMahkamah}" label="nama" value="kod" />
                    </s:select>                   
                </p> 
            </div>
            <p>
                <label for="tarikhBicara">Tarikh Bicara :</label>
                <s:text id="tarikhBicara" name="tarikhBicara" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label for="tarikhKeputusan">Tarikh Keputusan :</label>
                <s:text id="tarikhKeputusan" name="tarikhKeputusan" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label for="ulasanMahkamah">Ulasan Perbicaraan :</label>

                <s:textarea name="ulasanMahkamah" id="ulasanMahkamah" rows="5" cols="45"/>

            </p>

            <br>


            <p>
                <label>&nbsp;</label>
                <s:hidden name="idMohonMahkamah" id="idMohonMahkamah" value="${actionBean.permohonanMahkamah.idMm}"/>
                <s:button name="simpanMahkamah" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


    </s:form>
</div>