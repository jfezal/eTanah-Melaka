<%-- 
    Document   : carian_pihak_kepentingan
    Created on : Nov 3, 2010, 9:41:03 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
    <%-- $('a[title]').tooltip();--%>
            $('form').submit(function() {
                doBlockUI();

                var idMohon = $('#idMohon').val();

                if(idMohon == ''){
                    alert('Sila Masukkan ID Permohonan.');
                    $.unblockUI();
                    return false;
                }
    <%--    else  if(jenisKp != '' && noKp == '' && nama == ''){
               alert('Sila Masukkan Nombor Pengenalan Atau Nama.');
               $.unblockUI();
               return false;
           }
           else  if(jenisKp == '' && noKp != '' && nama != ''){
               alert('Sila masukkan jenis pengenalan');
               $.unblockUI();
               return false;
           }
           else  if(jenisKp != '' && noKp != '' && nama != ''){
               alert('Sila Pastikan Carian Berdasarkan Nombor Pengenalan Atau Nama.');
               $.unblockUI();
               return false;
           }
           else  if(jenisKp == '' && noKp == '' && nama == ''){
               alert('Sila masukkan maklumat carian.');
               $.unblockUI();
               return false;
           }--%>
                        });
                    });

                    function save(event, f){
                        if(doValidation()){
                            doBlockUI();
                            var q = $(f).formSerialize();
                            var url = f.action + '?' + event;
                            $.post(url,q,
                            function(data){
                                $('#page_div',opener.document).html(data);
                                $.unblockUI();
                                self.close();
                            },'html');
                        }
                    }

                    function doValidation(){
    <%--        var noKp = $('#kp').val();
            var jenisKp = $('#jenisKp').val();
            var nama = $('#nama').val();
            var waris = $('#waris').val();
            var umur = $('#umur').val();
            var alamat = $('#alamat1').val();

        if(nama == ''){
            alert('Sila Masukkan Nama.');
            return false;
        }else if(jenisKp == ''){
            alert('Sila Masukkan Jenis Pengenalan.');
            return false;
        }else if(noKp == ''){
            alert('Sila Masukkan Nombor Pengenalan.');
            return false;
        } else if (waris == ''){
            alert('Sila Masukkan Jenis Waris.');
            return false;
        }
        else if (umur == ''){
            alert('Sila Masukkan Umur.');
            return false;
        }
        else if (alamat == ''){
            alert('Sila Masukkan Alamat.');
            return false;
        }--%>

                return true;
            }

            function dodacheck(val) {
                //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
                var v = $('#jenisPengenalan').val();

                if(v == 'B') {
                    var strPass = val;
                    var strLength = strPass.length;
                    //$('#kp').attr("maxlength","12");
                    if(strLength > 12) {
                        var tst = val.substring(0, (strLength) - 1);
                        $('#kp').val(tst);
                    }
                    var lchar = val.charAt((strLength) - 1);
                    if(isNaN(lchar)) {
                        //return false;
                        var tst = val.substring(0, (strLength) - 1);
                        $('#kp').val(tst);
                    }
                }
            }

            function select(id,action) {
                doBlockUI();
                frm = document.form1;
                var url = '${pageContext.request.contextPath}/consent/pihak_kepentingan?'+action+'&idPihak=' + id;
                frm.action = url;
                frm.submit();
            }

            function clearForm(f) {
                $(f).clearForm();
            }

            function doBlockUI (){
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top:  ($(window).height() - 50) /2 + 'px',
                        left: ($(window).width() - 50) /2 + 'px',
                        width: '50px'
                    }
                });
        
            }
    
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.consent.MaklumatPerintahActionBean" name="form1">
        <s:hidden name="idHakmilik"/>
        <s:hidden name="checkPPSHPopup"/>
        <s:hidden name="checkPKPPopup"/>
        <s:hidden name="checkPPAPopup"/>
        <s:messages/>
        <s:errors/>
        <%--<s:text name="stageId"/>--%>
        <fieldset class="aras1">
            <legend>
                Carian Perintah
            </legend>

            <%--            <p>
                            <label><em>*</em>Jenis Pengenalan :</label>
                            <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                            </s:select>
                        </p>
                        <p>
                            <label><em>*</em>Nombor Pengenalan :</label>
                            <s:text name="pihak.noPengenalan" id="kp" size="40"
                                    onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
                        </p>
                        <p>
                            <label>&nbsp;</label>
                            atau
                        </p>--%>
            <p>
                <label for="nama"><em>*</em>ID Permohonan :</label>
                <s:text name="permohonanCarian.idPermohonan" size="40" onkeyup="this.value=this.value.toUpperCase();" id="idMohon"/>
            </p>
            <br/>
            <p align="center">
                <s:hidden name="doSearch" value="true"/>
                <%--<s:button name="showSearchForm" class="btn" value="Isi Semula" onclick="clearForm(this.form);"/>--%>
                <s:submit name="carianPerintah" class="btn" value="Cari"/>
                <%-- <c:if test="${penerima}">
                     <s:submit name="searchPihak" class="btn" value="Cari"/>
                 </c:if>
                 <c:if test="${pemohon}">
                     <s:submit name="searchPihakPemohon" class="btn" value="Cari"/>
                 </c:if>--%>
                <s:button name="close" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br/>
            <c:if test="${actionBean.checkPPSHPopup eq 'Y'}">
                <c:if test="${actionBean.namaPPSH ne null}">
                    <p>
                        <label><font color="red">*</font>Nama Penghuni :</label>
                        <s:text name="namaPPSH" size="40" id="c" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Nombor Perserahan :</label>
                        <s:text name="noSerahanPPSH" size="40" id="d" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tarikh Perserahan :</label>
                        <s:text name="tarikhPPSH"  id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.checkPKPPopup eq 'Y'}">
                <c:if test="${actionBean.namaPKP ne null}">
                    <p>
                        <label><font color="red">*</font>Nama Pemegang Amanah (Dibatalkan) :</label>
                        <s:text name="namaPKP" size="40" id="c" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Nombor Perserahan :</label>
                        <s:text name="noSerahanPKP" size="40" id="d" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tarikh Perserahan :</label>
                        <s:text name="tarikhPKP"  id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.checkPPAPopup eq 'Y'}">
                <c:if test="${actionBean.namaPPA ne null}">
                    <p>
                        <label><font color="red">*</font>Nama Pemegang Amanah (Dibatalkan) :</label>
                        <s:text name="namaPPA" size="40" id="c" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Nombor Perserahan :</label>
                        <s:text name="noSerahanPPA" size="40" id="d" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label><font color="red">*</font>Tarikh Perserahan :</label>
                        <s:text name="tarikhPPA"  id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.namaPPSH ne null || actionBean.namaPKP ne null || actionBean.namaPPA ne null}">
                <div align="center">
                    <s:button name="simpanCarianPopup" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </div>

            </c:if>

            <%--        <div align="center" id="tablePPSH">
                        <display:table style="width:60%;" class="tablecloth" name="${actionBean.listPPSH}" cellpadding="0" cellspacing="0" id="linePPSH"
                                       requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">
                            <display:column title="Bil" sortable="true">${linePPSH_rowNum}
                                <s:hidden name="PPSH" class="PPSH${linePPSH_rowNum -1}" value="${linePPSH.ID}"/>
                            </display:column>
                            <display:column property="NAMA" title="Nama Penghuni" class="namaPPSH"/>
                            <display:column property="SERAHAN" title="Nombor Perserahan"/>
                            <display:column property="TARIKH" title="Tarikh Serahan"/>
                        </display:table>
                    </div>

        &nbsp;Pembatalan Kaveat Pendaftar&nbsp;

        <div align="center" id="tablePKP">
            <display:table style="width:60%;" class="tablecloth" name="${actionBean.listPKP}" cellpadding="0" cellspacing="0" id="linePKP"
                           requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">

                <display:column title="Bil" sortable="true">${linePKP_rowNum}
                    <s:hidden name="PKP" class="PKP${linePKP_rowNum -1}" value="${linePKP.ID}"/>
                </display:column>
                <display:column property="NAMA" title="Nama Penghuni" class="namaPKP"/>
                <display:column property="SERAHAN" title="Nombor Perserahan"/>
                <display:column property="TARIKH" title="Tarikh Serahan"/>
            </display:table>
        </div>

        &nbsp;Pembatalan Pemegang Amanah&nbsp;

        <div align="center" id="tablePPA">
            <display:table style="width:60%;" class="tablecloth" name="${actionBean.listPPA}" cellpadding="0" cellspacing="0" id="linePPA"
                           requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">
                <display:column title="Bil" sortable="true">${linePPA_rowNum}
                    <s:hidden name="PPA" class="PPA${linePPA_rowNum -1}" value="${linePPA.ID}"/>
                </display:column>
                <display:column property="NAMA" title="Nama Penghuni" class="namaPPA"/>
                <display:column property="SERAHAN" title="Nombor Perserahan"/>
                <display:column property="TARIKH" title="Tarikh Serahan"/>

            </display:table>
        </div>--%>


            <%-- <c:if test="${fn:length(actionBean.senaraiPihak) > 0}">
                 <fieldset class="aras1">
                     <legend>Keputusan Carian</legend>
                     <span class=instr><em>Sila klik pada nama untuk pilih pihak.</em></span><br/>
                     <div align="center">
                         <display:table class="tablecloth" name="${actionBean.senaraiPihak}"
                                        cellpadding="0" cellspacing="0" id="line" pagesize="10" style="width:70%;"
                                        requestURI="${pageContext.request.contextPath}/consent/pihak_kepentingan">
                             <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                             <display:column title="Nama" class="nama">
                                 <c:if test="${penerima}">
                                     <a href="#" onclick="select('${line.idPihak}', 'selectPihak');">${line.nama}</a>
                                 </c:if>
                                 <c:if test="${pemohon}">
                                     <a href="#" onclick="select('${line.idPihak}', 'selectPihakPemohon');">${line.nama}</a>
                                 </c:if>
                             </display:column>
                             <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                             <display:column property="noPengenalan" title="Nombor Pengenalan" />
                         </display:table>
                     </div>
                     <br/>
                 </fieldset>
             </c:if>--%>

            <%--        <c:if test="${addNewPihak}">
                <fieldset class="aras1">
                    <legend>Keputusan Carian</legend>
                    <p align="center">
                        <font color="003194" style="font-size:13px;font-weight:bold;"> Tiada rekod dijumpai. Sila tambah baru.</font><br/><br/>
                        <c:if test="${penerima}">
                            <s:submit name="addNewPihak" value="Tambah" class="btn"/>
                        </c:if>
                        <c:if test="${pemohon}">
                            <s:submit name="addNewPihakPemohon" value="Tambah" class="btn"/>
                        </c:if>
                    </p>
                    <br/>
                </fieldset>
            </c:if>--%>
        </fieldset>
    </s:form>
</div>
