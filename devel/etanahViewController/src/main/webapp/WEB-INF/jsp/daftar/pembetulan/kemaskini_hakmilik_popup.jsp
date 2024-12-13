<%-- 
    Document   : kemaskini_hakmilik_popup
    Created on : Oct 5, 2012, 11:51:35 AM
    Author     : ei
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.idHakmilikBaru').each(function(index){
            $(this).blur(function(){
                validateHakmilik(index+1);
            });
        });
    });

    function doCheckHakmilik (hakmilik) {
        var valid = true;
        $('.idhakmilik').each(function(index){            
            if (hakmilik.trim() == $(this).val()) {
                valid = false;
            }
        });
        return valid;
    }
    
    function validateHakmilik(idxHakmilik){        
        var val = $("#hakmilik" + idxHakmilik).val();        
        frm = this.form;
        if (val == null || val == "") return;
        val = val.toUpperCase();        
        if (!doCheckHakmilik (val)) {
            $("#hakmilik" + idxHakmilik).val("");
            alert ('Hakmilik sudah dipilih.');
            return;
        }
        
        $("#hakmilik" + idxHakmilik).val(val);
        $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
        function(data){
            if(data == '1'){
                $("#msg" + idxHakmilik).html("OK");
            } else if(data =='0'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
            } else if(data =='2'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                //$("#hakmilik" + idxHakmilik).val("");
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
            } else if (data == '3'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
            } else{
//                alert(data);
            }
        });
    }

    function doSubmit(event, form){
        var q = $(form).formSerialize();
        doBlockUI();
        var url = form.action + '?' + event;        
        $.ajax ({
            type : 'POST',
            url : url,
            dataType : 'html',
            data : q,
            error : function (xhr, ajaxOptions, thrownError){},
            success : function (data){                         
                $('#body',opener.document).html(data);                
                self.close();
            }
        });        
    }
    
    function doBlockUI() {
      $.blockUI({
        message: $('#displayBox'),
        css: {
          top: ($(window).height() - 50) / 2 + 'px',
          left: ($(window).width() - 50) / 2 + 'px',
          width: '50px'
        }
      });
    }

</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="listUtil"/>
<s:useActionBean beanclass="etanah.view.daftar.UtilitiBetulPerserahanSWSBSA" var="betulSWSBSA" />
<s:form beanclass="etanah.view.daftar.UtilitiBetulPerserahanSWSBSA">
    <s:hidden name="idPermohonan" id="idPemohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <div class="subtitle">
        <c:if test="${fn:length(actionBean.hakmilik.idHakmilik) > 0}"> 
        
        <fieldset class="aras1">
            <legend>Pembetulan Maklumat Hakmilik</legend>
            <font color="red" size="2">Sila kemaskini id hakmilik yang terlibat sahaja.</font><br><br>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilik}"  cellpadding="0" cellspacing="0"
                                   requestURI="/daftar/pembetulanPertanyaanSBSWSA" id="line">
                        <display:column title="ID Hakmilik">
                            <s:hidden name="idhakmilik" id="idhakmilik${line_rowNum}" 
                                      value="${actionBean.hakmilik.idHakmilik}" 
                                      class="hakmilikLama"/>
                            ${actionBean.hakmilik.idHakmilik}
                        </display:column>
                        <display:column title="ID Hakmilik Baru">
                            <s:text name="idHakmilikBaru" id="hakmilik${line_rowNum}" class="idHakmilikBaru" onkeyup="this.value=this.value.toUpperCase();"/>
<!--                            <div id="msg${line_rowNum}"/>-->
                        </display:column>                        
                    </display:table></div>
                <div align="center">
                    <p>
                        <label></label>&nbsp;
                        <s:button name="saveHakmilik" value="Simpan" onclick="doSubmit(this.name, this.form);" class="btn"/>
                        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                    </p>
                </div>
        </fieldset>
        </c:if>        
        <c:if test="${fn:length(actionBean.idPemberi) > 0}">
        <fieldset class="aras1">
            <s:hidden name="idPemberi" id="idPemberi$" value="${line.idPemberi}" class="namaPemberi"/>   
            <legend>Pembetulan Maklumat Pemberi</legend>
            <font color="red" size="2">Sila kemaskini pemberi yang terlibat sahaja.</font><br><br>
                <div class="content" align="center">
                    <table class="tablecloth" name="${actionBean.listWakilKuasaPemberi}" cellpadding="0" cellspacing="0"
                           requestURI="/daftar/pembetulanPertanyaanSBSWSA" id="line">
                        <tr>
                            <td></td>
                            <th>Maklumat Asal</th>
                            <th>Maklumat Baru</th>
                        </tr>
                        <tr>
                            <th>Nama Pemberi :</th>
                            <td><s:hidden name="namaPemberi" id="namaPemberi" 
                                        value="${actionBean.wakilKuasaPemberi.pihak.nama}" 
                                        class="namaPemberi"/>
                                ${actionBean.wakilKuasaPemberi.pihak.nama}</td>
                            <td><s:text name="pemberiBaru" id="pemberiBaru" 
                                        size="40" class="pemberiBaru" 
                                        onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <th>Jenis Pengenalan :</th>
                            <td><s:hidden name="jenisPengenalan" id="jenisPengenalan" 
                                        value="${actionBean.wakilKuasaPemberi.pihak.jenisPengenalan.nama}" 
                                        class="jenisPengenalan"/>
                                ${actionBean.wakilKuasaPemberi.pihak.jenisPengenalan.nama}</td>
                            <td><s:select name="jenisPengenalanBaru" >
                                   <s:option value="">--Sila Pilih--</s:option>
                                   <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select></td>
                        </tr>
                        <tr>
                            <th>No Pengenalan :</th>
                            <td><s:hidden name="noICPenerima" id="noICPenerima" 
                                        value="${actionBean.wakilKuasaPemberi.pihak.noPengenalan}" 
                                        class="noICPenerima"/>
                                ${actionBean.wakilKuasaPemberi.pihak.noPengenalan}</td>
                            <td><s:text name="noICBaru" id="noICBaru" size="20" class="noICBaru" 
                                        onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr> 
                    </table>
                    <%--<display:table class="tablecloth" name="${actionBean.listWakilKuasaPemberi}" cellpadding="0" cellspacing="0"
                                   requestURI="/daftar/pembetulanPertanyaanSBSWSA" id="line">
                        <display:column title="Nama Wakil Kuasa Pemberi">
                            <s:hidden name="namaPemberi" id="namaPemberi${line_rowNum}" 
                                      value="${line.pihak.nama}" 
                                      class="namaPemberi"/>
                            ${line.pihak.nama}
                        </display:column>
                        <display:column title="Nama Wakil Kuasa Pemberi Baru">
                            <s:text name="pemberiBaru" id="pemberi${line_rowNum}" class="pemberiBaru" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                            <div id="msg${line_rowNum}"/>
                        </display:column>                        
                    </display:table>--%>
                </div>
                <div align="center">
                    <p>
                        <label></label>&nbsp;
                        <s:button name="savePemberi" value="Simpan" onclick="doSubmit(this.name, this.form);" class="btn"/>
                        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                    </p>
                </div>
        </fieldset>
        </c:if>
        <c:if test="${fn:length(actionBean.idPenerima) > 0}"> 
            <s:hidden name="idPenerima" id="idPenerima" value="${line.idPenerima}"/> 
        <fieldset class="aras1">
            <legend>Pembetulan Maklumat Penerima</legend>
            <font color="red" size="2">Sila kemaskini penerima yang terlibat sahaja.</font><br><br>
                <div class="content" align="center">
                    <table class="tablecloth" name="${actionBean.listWakilKuasaPenerima}" cellpadding="0" cellspacing="0"
                           requestURI="/daftar/pembetulanPertanyaanSBSWSA" id="line">
                        <tr>
                            <td></td>
                            <th>Maklumat Asal</th>
                            <th>Maklumat Baru</th>
                        </tr>
                        <tr>
                            <th>Nama Penerima :</th>
                            <td><s:hidden name="namaPenerima" id="namaPenerima" 
                                        value="${actionBean.wakilKuasaPenerima.nama}" 
                                        class="idPenerima"/>
                                ${actionBean.wakilKuasaPenerima.nama}</td>
                            <td><s:text name="penerimaBaru" 
                                        id="penerimaBaru" size="40" class="penerimaBaru" 
                                        onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr>
                        <tr>
                            <th>Jenis Pengenalan :</th>
                            <td><s:hidden name="jenisPengenalan" id="jenisPengenalan" 
                                        value="${actionBean.wakilKuasaPenerima.jenisPengenalan.nama}" 
                                        class="jenisPengenalan"/>
                                ${actionBean.wakilKuasaPenerima.jenisPengenalan.nama}</td>
                            <td><s:select name="jenisPengenalanBaru" >
                                   <s:option value="">--Sila Pilih--</s:option>
                                   <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                                </s:select></td>
                        </tr>
                        <tr>
                            <th>No Pengenalan :</th>
                            <td><s:hidden name="noICPenerima" id="noICPenerima" 
                                        value="${actionBean.wakilKuasaPenerima.noPengenalan}" 
                                        class="noICPenerima"/>
                                ${actionBean.wakilKuasaPenerima.noPengenalan}</td>
                            <td><s:text name="noICBaru" id="noICBaru" size="20" class="noICBaru" 
                                        onkeyup="this.value=this.value.toUpperCase();"/></td>
                        </tr> 
                    </table>
                    </div>
                <div align="center">
                    <p>
                        <label></label>&nbsp;
                        <s:button name="savePenerima" value="Simpan" onclick="doSubmit(this.name, this.form);" class="btn"/>
                        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                    </p>
                </div>
        </fieldset>
        </c:if>
    </div>
</s:form>
