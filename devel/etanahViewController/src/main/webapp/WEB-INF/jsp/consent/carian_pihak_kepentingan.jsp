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

                var nama = $('#nama').val();
                var noKp = $('#kp').val();
                var jenisKp = $('#jenisPengenalan').val();                         

                if(jenisKp == '' && noKp != ''){
                    alert('Sila Masukkan Jenis Pengenalan.');
                    $.unblockUI();
                    return false;
                }
                else  if(jenisKp != '' && noKp == '' && nama == ''){
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
                }
            });
        });

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
    <s:form beanclass="etanah.view.stripes.consent.PihakKepentinganActionBean" name="form1">
        <s:hidden name="idHakmilik"/>
        <s:hidden name="permohonan.kodUrusan.kod"/>
        <fieldset class="aras1">
            <legend>
                <c:if test="${pemohon}">
                    Carian Maklumat Pemohon
                </c:if>
                <c:if test="${penerimaGadaian}">
                    Carian Maklumat Penerima Gadaian
                </c:if>
                <c:if test="${penerima}">
                    Carian Maklumat
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PCPTD' || actionBean.permohonan.kodUrusan.kod eq 'PGDMB' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PCMMK' || actionBean.permohonan.kodUrusan.kod eq 'GSMMK' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMTMB' || actionBean.permohonan.kodUrusan.kod eq 'PMADT'
                                        || actionBean.permohonan.kodUrusan.kod eq 'RMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RJKTL' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMWNA' || actionBean.permohonan.kodUrusan.kod eq 'PMWWA'  
                                        || actionBean.permohonan.kodUrusan.kod eq 'PJKTL' || actionBean.permohonan.kodUrusan.kod eq 'PMJTL' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMMMK' || actionBean.permohonan.kodUrusan.kod eq 'PMPTD' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PMMMK' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PPTGM' || actionBean.permohonan.kodUrusan.kod eq 'PMKMM'
                                        || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PPTD') 
                                        || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'PMMK')}">
                                Penerima Pindah Milik
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PJPTD' || actionBean.permohonan.kodUrusan.kod eq 'PJMMK'
                                        || actionBean.permohonan.kodUrusan.kod eq 'PJADT' || actionBean.permohonan.kodUrusan.kod eq 'PJKMM' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'PJPTD'}">
                                Penerima Pajakan
                        </c:when>
                        <c:when  test="${actionBean.permohonan.kodUrusan.kod eq 'CGADT' || actionBean.permohonan.kodUrusan.kod eq 'MCGMB' 
                                         || actionBean.permohonan.kodUrusan.kod eq 'MCMMK' || fn:startsWith(actionBean.permohonan.kodUrusan.kod, 'GPTD') }">
                                 Penerima Gadaian
                        </c:when>                        
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TMADT' || actionBean.permohonan.kodUrusan.kod eq 'TMWNA'}">
                            Penerima Turun Milik
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'TTADT'}">
                            Pihak Yang Menuntut
                        </c:when>    
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'MCKMM' || actionBean.permohonan.kodUrusan.kod eq 'MCPTG' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'MCPTD'}">
                            Penerima Cagaran
                        </c:when>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'SWKMM' || actionBean.permohonan.kodUrusan.kod eq 'SWPTD'}">
                            Penerima Sewaan
                        </c:when>    
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMMAT' || actionBean.permohonan.kodUrusan.kod eq 'DPWNA'}">
                            Penerima Turun Milik
                        </c:when>  
                        <c:when test="${(actionBean.permohonan.kodUrusan.kod eq 'PMMK1' && actionBean.kodNegeri eq '04') || (actionBean.permohonan.kodUrusan.kod eq 'PMMK2' && actionBean.kodNegeri eq '04')}">
                            Pemohon/Penerima
                        </c:when>
                        <c:otherwise>
                            Penerima
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </legend>

            <p>
                <label><em>*</em>Jenis Pengenalan :</label>
                <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label><em>*</em>Nombor Pengenalan :</label>
               <%-- <s:text name="pihak.noPengenalan" id="kp" size="40"
                        onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>--%>
                 <s:text name="pihak.noPengenalan" id="kp" size="40"
                        onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>&nbsp;</label>
                atau
            </p>
            <p>
                <label for="nama"><em>*</em>Nama :</label>
                <s:text name="pihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();" id="nama"/>
            </p>
            <br/>
            <p align="center">
                <s:hidden name="doSearch" value="true"/>
                <c:if test="${penerima}">
                    <s:submit name="searchPihak" class="btn" value="Cari"/>
                </c:if>
                <c:if test="${penerimaGadaian}">
                    <s:submit name="searchPihakGadaian" class="btn" value="Cari"/>
                </c:if>
                <c:if test="${pemohon}">
                    <s:submit name="searchPihakPemohon" class="btn" value="Cari"/>
                </c:if>
                <s:button name="showSearchForm" class="btn" value="Isi Semula" onclick="clearForm(this.form);"/>
                <s:button name="close" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br/>
        </fieldset>
        <br/>
        <c:if test="${fn:length(actionBean.senaraiPihak) > 0}">
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
                            <c:if test="${penerimaGadaian}">
                                <a href="#" onclick="select('${line.idPihak}', 'selectPihakGadaian');">${line.nama}</a>
                            </c:if>
                            <c:if test="${pemohon}">
                                <a href="#" onclick="select('${line.idPihak}', 'selectPihakPemohon');">${line.nama}</a>
                            </c:if>
                        </display:column>
                        <display:column title="Jenis Pengenalan" >
                            <font style="text-transform:uppercase;">${line.jenisPengenalan.nama}</font>
                        </display:column>
                        <display:column property="noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Alamat Berdaftar" >
                            <font style="text-transform:uppercase;">
                                ${line.alamat1} 
                                ${line.alamat2}
                                ${line.alamat3}
                                ${line.alamat4}
                                ${line.poskod}
                                ${line.negeri.nama}
                            </font>
                        </display:column>
                    </display:table>
                </div>
                <br/>
            </fieldset>
        </c:if>
        <c:if test="${addNewPihak}">
            <fieldset class="aras1">
                <legend>Keputusan Carian</legend>
                <p align="center">
                    <font color="003194" style="font-size:13px;font-weight:bold;"> Tiada rekod dijumpai. Sila tambah baru.</font><br/><br/>
                    <c:if test="${penerima}">
                        <s:submit name="addNewPihak" value="Tambah" class="btn"/>
                    </c:if>
                    <c:if test="${penerimaGadaian}">
                        <s:submit name="addNewPihakGadaian" value="Tambah" class="btn"/>
                    </c:if>
                    <c:if test="${pemohon}">
                        <s:submit name="addNewPihakPemohon" value="Tambah" class="btn"/>
                    </c:if>
                </p>
                <br/>
            </fieldset>
        </c:if>
    </s:form>
</div>
