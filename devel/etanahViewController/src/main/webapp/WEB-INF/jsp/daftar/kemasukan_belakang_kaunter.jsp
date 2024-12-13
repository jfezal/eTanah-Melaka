<%--
    Document   : ABKO1
    Created on : 01 Oktober 2009, 10:39:23 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<script type="text/javascript">
// only for demo purposes
$.validator.setDefaults({
	submitHandler: function() {
		alert("submitted!");
	}
});

$.metadata.setType("attr", "validate");

$(document).ready(function() {
	$("#form1").validate();
	$("#fail").validate();
});
</script>--%>
<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.blockUI.js"></script>--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        
        $('#displayBox').hide();
        $("#daftar").attr("disabled", "true");
        $("#hakmilik").blur(function(){
            frm = this.form;            
            $.get("${pageContext.request.contextPath}/pendaftaran/check_idhakmilik?doCheckHakmilik&idHakmilik="+$("#hakmilik").val(),
            function(data){
                if(data == '1'){
                    $("#msg").html("ID Hakmilik Wujud. Lihat <a href='#' id='popup' >Data</a>.");
                    $("#daftar").removeAttr("disabled");
                    $("#popup").click( function() {
                        window.open(frm.action + "?hakmilikDetail&idHakmilik="+$("#hakmilik").val(), "eTanah",
                        "status=0,toolbar=0,location=1,menubar=0,width=900,height=600");
                    });
                }
                else if(data =='0'){
                    $("#msg").html("ID Hakmilik Tidak Wujud");
                    $("#hakmilik").val("");
                    $("#daftar").attr("disabled", "true");
                }
            });
        });
        $("#add").click( function() {            
            window.open("hakmilik", "eTanah",
            "status=1,toolbar=0,location=0,menubar=0,width=900,height=600");
        });
        $('.empty').remove();

        $('#myform input:text').each( function(el){            
            $(this).blur( function() {
                $(this).val( $(this).val().toUpperCase());
            });
        });
    });
    
    function addRows(idHakmilik, cawangan ,daerah, lot, luas, noLitho){
        //TODO: to be complete
        var rowNo = $('table#line tr').length;
        $('table#line > tbody').append("<tr id='x"+rowNo+"' class='x'><td class='rowNo'>"+rowNo
            +"</td><td><input type='hidden' name='idHakmilikArray' value='"+idHakmilik+"'/><a href='#' onclick=\"doPopup('"+idHakmilik+"');\">"+
            idHakmilik+"</a></td><td>"+cawangan+"</td><td>"+daerah+"</td><td>"+lot
            +"</td><td>"+luas+"</td><td>"+noLitho+"</td><td>"+
            "<img alt='Click to Delete' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem' id='rem"+
            rowNo+"' onclick=\"remove(this.id,'line')\"></td></tr>");
    }

    function remove(id, idTable){
        $('#'+id).parent().parent().remove();
        populate(idTable);
    }

    function populate(id){
        var x = $('#' +id).find('tbody tr').get();
        $(x).each( function(i) {
            var y = $(this).find('.rowNo' ).get();
            var z = $(this).find('.x' ).get();
            var u = $(this).find('.rem').get();
            var a = (i+1);
            $(y).text(a);
            $(z).removeAttr('id');
            $(z).attr('id', 'x'+a);
            $(u).removeAttr('id');
            $(u).attr('id', 'rem'+a);
        });
    }

    function doSubmit(frm, event, id){

        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });


        var queryString = $(frm).formSerialize();
        url = frm.action + '?' + event;
        $.post(url,queryString,
        function(data){
            $('#'+id).html(data);
        });

        setTimeout($.unblockUI, 1000);
    }

    function doPopup(hakmilik){
        window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+hakmilik, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }    
  
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/images/logo.gif" width="50" height="50"/>
<table width="100%" bgcolor="#00FFFF">
    <tr>
        <td width="50%" ><div style="float:left;" class="formtitle">Awalan Belakang Kaunter</div></td>
        <td width="50%" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<s:useActionBean beanclass="etanah.view.ListUtil" var="state"/>
<s:form beanclass="etanah.view.daftar.DaftarBelakangKaunterActionBean" id="myform">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Urusan
            </legend>
            <p>
                <label for="Kategori Urusan">Kategori Urusan :</label>
                Nota Pembetulan
            </p>
            <p>
                <label for="Kategori Urusan">Kod Urusan :</label>
                BETUL
            </p>
            <p>
                <label for="Kategori Urusan">Urusan :</label>
                Pembetulan di bawah Seksyen 380KTN
            </p>
            <p>
                <label for="Kategori Urusan">Catatan :</label>
                Sila pastikan dokumen fizikal disertakan
            </p>

        </fieldset>
    </div>
    <br/>
    <%--<div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend>
            <p>
                <label>&nbsp;</label>
                <s:button name="add_hakmilik" id="add" value="Tambah Hakmilik" class="btn"/>
            </p>

            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikList}" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                    <display:column property="hakmilik.cawangan.name" title="Cawangan"/>
                    <display:column property="hakmilik.daerah.nama" title="Daerah"/>
                    <display:column property="hakmilik.lot.nama" title="Lot"/>
                    <display:column property="hakmilik.luas" title="Luas"/>
                    <display:column property="hakmilik.noLitho" title="No Litho"/>
                    <display:column title="padam"></display:column>
                </display:table>
            </div>

        </fieldset>
    </div>--%>

    <br/>   

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Penyerah</legend>
            <p>
                <label id="msg" for="">&nbsp;</label>
                &nbsp;
            </p>
            <p>
                <label for="Nama">Nama :</label>
                <s:text name="permohonan.penyerahNama" size="40"/>
            </p>            
            <p>
                <label for="ID Hakmilik">ID Hakmilik :</label>
                <s:text name="idHakmilik" id="hakmilik"/>
            </p>
            <p>
                <label for="No Rujukan">Nombor Rujukan Penyerah :</label>
                <s:text name="permohonan.penyerahNoRujukan"/>
            </p>
            <p>
                <label for="No Rujukan">Jenis Pengenalan :</label>
                <s:select name="jnsKP">
                    <s:option >Baru</s:option>
                    <s:option >Lama</s:option>
                    <s:option >Tentera</s:option>
                </s:select>
            </p>
            <p>
                <label for="Nombor Pengenalan">Nombor Pengenalan :</label>
                <s:text name="noKP"/>
            </p>
            <p>
                <label for="Bangsa">Bangsa :</label>
                <s:select name="bangsa">
                    <s:option >Melayu</s:option>
                    <s:option >Cina</s:option>
                    <s:option >India</s:option>
                </s:select>
            </p>
            <p>
                <label for="Alamat">Alamat :</label>
                <s:text name="permohonan.penyerahAlamat1" />
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="permohonan.penyerahAlamat2" />
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="permohonan.penyerahAlamat3" />
            </p>
            <p>
                <label for="">&nbsp;</label>
                <s:text name="permohonan.penyerahAlamat4" />
            </p>
            <p>
                <label for="Poskod">&nbsp;</label>
                <s:text name="permohonan.penyerahPoskod" size="7" maxlength="5"/>
            </p>
            <p>
                <label for="negeri">Negeri :</label>
                <s:select name="permohonan.penyerahNegeri.kod">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${state.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <%--<s:button name="daftar" id="daftar" value="Daftar" class="btn" onclick="doSubmit(this.form, this.name, 'myform')"/>--%>
                <s:submit name="save" id="daftar" value="Daftar" class="btn" />
            </p>

        </fieldset>

    </div>
</s:form>