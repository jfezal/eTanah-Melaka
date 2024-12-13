<%-- 
    Document   : edit_penyerah
    Created on : Apr 14, 2010, 4:33:13 PM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>-->

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        

<script type="text/javascript">

    $(document).ready(function(){
        var r = '${edit}';
        if(r == 'true'){
            $('.penyerah_detail').show();
        }        
    });


    var DELIM = "__^$__";
    function cariPenyerah(e,f) {       
        var k = $('#penyerahKod').val();
        if(k == '0') {
            alert('Sila Pilih Penyerah');
            $('#penyerahKod').focus();
            return;
        }
//        var i = $('#namaPenyerah').val();
//        if(i == ''){
//            alert('Sila Isi nama Penyerah');
//            $('#penyerahKod').focus();
//            return;
//        }
        f.action = f.action + '?' + e;
        f.submit();
        //i = '';
    }
    
    function pilihPenyerah(){
        var jenis = $('#penyerahKod').val();
        var url;
        $('.penyerah').each(function(){
            if($(this).is(':checked')){
                var va = $(this).val();
                
                if(jenis == '0'){
                    alert('Sila pilih Jenis Penyerah');
                    return;
                }else
                    if (jenis == '00'){ // JUBL
                        url = "${pageContext.request.contextPath}/common/req_unit_info?idUnit=" + va;
                    } else
                    if (jenis == '01'){ // PEGUAM
                        url = "${pageContext.request.contextPath}/common/req_peguam_info?idPeguam=" + va;
                    } else
                    if (jenis == '02'){ // JUBL
                        url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + va;
                    } //else 
//                    if (jenis == '04'){ // Jurulelong Berlesen
//                        url = "${pageContext.request.contextPath}/common/req_jubl_info?idJUBL=" + va;
//                    } else
//                    if (jenis == '05') { // Perbadanan Pengurusan
//                        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
//                    } else 
                    if (jenis == '06') { // Jabatan Kerajaan
                        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
                    }
                    
                    if (jenis == '07') { // Badan Berkanun
                        url = "${pageContext.request.contextPath}/common/req_agensi_info?idAgensi=" + va;
                    }
//                        

                $.get(url,
                function(data){
                    if (data == null || data.length == 0){
                        alert("Maklumat tidak dijumpai");
                        return;
                    }
                    var p = data.split(DELIM);
                    //$('#penyerahKod', window.opener.document).val(jenis);
                    $('#idPenyerah').val(va);
                    $('#penyerahJenisPengenalan').val(p[0]);
                    $('#penyerahNoPengenalan').val(p[1]);
                    $("#penyerahNama").val(p[2]);
                    $("#penyerahAlamat1").val(p[3]);
                    $("#penyerahAlamat2").val(p[4]);
                    $("#penyerahAlamat3").val(p[5]);
                    $("#penyerahAlamat4").val(p[6]);
                    $("#penyerahPoskod").val(p[7]);
                    $("#penyerahNegeri").val(p[8]);
                    $("#penyerahNoTelefon").val(p[9]);
                    $("#penyerahEmail").val(p[10]);
                    $('.penyerah_detail').show();
                });
            }
        });
}
 function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#penyerahJenisPengenalan').val();
        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#penyerahNoPengenalan').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#penyerahNoPengenalan').val(tst);
            }
        }
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form action="kaunter/edit_penyerah" beanclass="etanah.view.kaunter.RequestPenyerahInfoForm">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Pembetulan maklumat penyerah
            </legend>
            <p>
                <label>Jenis Penyerah :</label>
                <s:select name="jenisPenyerah" id="penyerahKod" onchange="">
                    <s:option value="0">Pilih Jenis ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
                </s:select>
                    
            </p>
            
            <p>
                <label>Nama Penyerah :</label>
                <s:text name="namaPenyerah" size="20" id="namaPenyerah" />
                <em>Atau</em>
            </p>
            <p>
                <label>Id Penyerah :</label>
                <s:text name="idPenyerah" size="20" id="idPenyerah" maxlength="5"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchPenyerahForEdit" value="Cari" class="btn" onclick="cariPenyerah(this.name, this.form);"/>
                
            </p>
        </fieldset>
    </div>
    <br/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Carian Penyerah
            </legend>
            <div class="content" align="center">
                <display:table style="width:100%" class="tablecloth" name="${actionBean.senaraiPenyerah}" pagesize="10"
                               cellpadding="0" cellspacing="0" id="line" requestURI="${pageContext.request.contextPath}/common/req_penyerah_info?searchPenyerahForEdit" excludedParams="searchPenyerahForEdit">
                    <display:column>
                        <c:if test="${line.aktif eq 'Y'}">
                                <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idPenyerah}"/>
                            </c:if>
                            <c:if test="${line.aktif eq 'T'}">
                                <s:radio name="__pilihPenyerah" class="penyerah" id="pilihPenyerah${line_rowNum}" value="${line.idPenyerah}" disabled="true"/>
                        </c:if>
                    </display:column>
                    <display:column property="idPenyerah" title="ID Penyerah"/>
                    <display:column property="nama" title="Nama Penyerah"/>
                    <display:column title="Alamat">
                        ${line.alamat1}
                        ${line.alamat2}
                        ${line.alamat3}
                        ${line.alamat4}
                        ${line.poskod}
                        ${line.negeri.nama}
                    </display:column>
                </display:table>
            </div>
            <br/>
            <c:if test="${fn:length(actionBean.senaraiPenyerah)>0}">
                <p>
                    <label>&nbsp;</label><s:button name="_pilih" value="Pilih" class="btn" onclick="pilihPenyerah();"/>
                </p>
            </c:if>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle penyerah_detail" style="display: none">
        <s:hidden name="idPenyerah" id="idPenyerah"/>
        <fieldset class="aras1">
            <legend>
                Kemaskini Penyerah
            </legend>
            <c:if test="${actionBean.jenisPenyerah eq '00' or actionBean.jenisPenyerah eq '01' or actionBean.jenisPenyerah eq '02' or actionBean.jenisPenyerah eq '03'
                  or actionBean.jenisPenyerah eq '04' or actionBean.jenisPenyerah eq '05' or actionBean.jenisPenyerah eq '06' or actionBean.jenisPenyerah eq '07'}">
            <p>
                <label for="Jenis Pengenalan">No. Pengenalan :</label>
                <s:select name="penyerahJenisPengenalan" id="penyerahJenisPengenalan" onchange="clearNoPengenalan();">
                    <s:option value="0">Pilih Jenis...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
                <s:text name="penyerahNoPengenalan" id="penyerahNoPengenalan" onkeyup="dodacheck(this.value);"
                        onblur="doUpperCase(this.id)"/>

            </p>
            </c:if>
            
            <p>
                <label>Nama :</label>
                <s:text name="penyerahNama" id="penyerahNama" size="42"/><em>*</em>
            </p>
            <p>
                <label>Alamat :</label>
                <s:text name="penyerahAlamat1" id="penyerahAlamat1" size="30"/><em>*</em>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat2" id="penyerahAlamat2" size="30"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat3" id="penyerahAlamat3" size="30"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:text name="penyerahAlamat4" id="penyerahAlamat4" size="30"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="penyerahPoskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
            </p>
            <p>
                <label>Negeri</label>
                <s:select name="penyerahNegeri" id="penyerahNegeri" >
                    <s:option value="0">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>No.Telefon</label>
                <s:text name="penyerahNoTelefon" id="penyerahNoTelefon" size="15"/>
            </p>
            <p>
                <label>Email</label>
                <s:text name="penyerahEmail" id="penyerahEmail" size="50"/>
            </p>
            <br/>            
            <p>
                <label>&nbsp;</label><s:submit name="update" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
