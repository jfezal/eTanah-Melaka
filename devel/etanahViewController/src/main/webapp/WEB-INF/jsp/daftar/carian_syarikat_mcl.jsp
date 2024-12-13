<%-- 
    Document   : carian_syarikat_mcl
    Created on : Dec 12, 2012, 04:57:31 AM
    Author     : Aizuddin
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
    });

    function dodacheck(val) {
        var v = $('#jenisPengenalan').val();

        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                var tst = val.substring(0, (strLength) - 1);
                $('#kp').val(tst);
            }
        }
    }

    function select(id) {
        doBlockUI();
        frm = document.form1;
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?selectPihak&idPihak=' + id;
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

    function doSubmit(form, event){
        var name1 = $('#nama').val();
        var kp1 = $('#kp').val();
        if (name1 == '' && kp1 == '') {
            alert('Sila masukan nama ataupun no pengenalan');
            return;
        }
        form.action = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?' + event;
        form.submit();
    }
    
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<div class="subtitle">
    <s:form beanclass="etanah.view.daftar.PihakKepentinganAction" name="form1">
        <s:hidden name="idHakmilik"/>
        <fieldset class="aras1">
            <legend>Carian Syarikat MCL</legend>
            <span class=instr><em>Sila isi nama syarikat atau no syarikat.</em></span><br/>
            <p>
                <label for="nama">Nama Syarikat:</label>
                <s:text name="pihak.nama" size="40" onkeyup="this.value=this.value.toUpperCase();" id="nama"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                <s:select name="pihak.jenisPengenalan.kod" id="jenisPengenalan">
                    <s:option value="S">NO SYARIKAT</s:option>
                <%--    <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/> --%>
                </s:select>
            </p>

            <p>
                <label>No Syarikat :</label>
                <s:text name="pihak.noPengenalan" id="kp" size="40"
                        onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase();"/>
            </p>
            <br/>
            <p>
                <label><s:button name="showSearchFormMCL" class="btn" value="Isi Semula" onclick="clearForm(this.form);"/>&nbsp;</label>
                <s:hidden name="doSearch" value="true"/>
                <s:button name="searchPihakMCL" class="btn" value="Cari" id="searchPihakMCL" onclick="doSubmit(this.form,this.name);"/>&nbsp;
                <s:submit name="addNewPihakMCL" value="Tambah" class="btn"/>
                <s:button name="close" id="tutup" value="Tutup" class="btn" onclick="self.close()"/> &nbsp;
                
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
                                   requestURI="${pageContext.request.contextPath}/daftar/pihak_kepentingan">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama Syarikat" class="nama">
                            <a href="#" onclick="select('${line.idPihak}');">${line.nama}</a>
                        </display:column>
                        <display:column property="jenisPengenalan.nama" title="Jenis Pengenalan" />
                        <display:column property="noPengenalan" title="Nombor Syarikat" />
                    </display:table>
                </div>
                <br/>
                </fieldset>
        </c:if>
        <c:if test="${addNewPihakMCL}">
            <fieldset class="aras1">
                <legend>Keputusan Carian</legend>
                <p>
                    <label>Tiada rekod dijumpai. Sila tambah baru.</label>&nbsp;<s:submit name="addNewPihakMCL" value="Tambah" class="btn"/>
                </p>                
            </fieldset>
        </c:if>
    </s:form>
</div>
