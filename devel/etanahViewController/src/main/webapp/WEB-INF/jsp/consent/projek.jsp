<%-- 
    Document   : projek
    Created on : Jun 20, 2013, 1:31:15 PM
    Author     : mohd.azhar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('form').submit(function() {
            doBlockUI();
        });
    });

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

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode;
        if (charCode !== 46 && charCode > 31
            && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

    function simpan(event, f) {

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
        function(data) {
        }, 'html');
    }

    function edit(i) {
        var d = $('.x' + i).val();
        window.open("${pageContext.request.contextPath}/consent/projek?showEdit&idProjek=" + d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function hapus(i) {
        doBlockUI();
        var d = $('.x' + i).val();
        if (confirm('Adakah pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/consent/projek?delete&idProjek=' + d;
            $.get(url,
            function(data) {
                $('#page_div').html(data);
            }, 'html');
        }
        $.unblockUI();
    }

    function test(f) {
        $(f).clearForm();
    }

    function clearForm(f) {

        $("#namaPemaju").val('');
        $("#jenisProjek").val('');
        $("#jumlahSemuaUnit").val('');
        $("#maksimumUnit").val('');
    }
</script>

<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.ProjekActionBean">

    <div class="subtitle displaytag" id="page_div">

        <%--    <s:hidden name="projek.idProjek" id="idProjek" />  --%>
        <s:messages/>
        <s:errors/>
        <fieldset class="aras1" id="">
            <legend>Maklumat Projek Hartanah</legend>
            <p>
                <s:hidden name="projek.idProjek" id="idProjek"/>
            </p>

            <p>
                <label><em>*</em>Nama Pemaju :</label>
                <s:text name="projek.namaPemaju" size="50" maxlength="50" id="namaPemaju" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><em>*</em>Nombor Rujukan Projek :</label>
                <s:text name="projek.noRujukanProjek" size="50" maxlength="40" id="namaPemaju" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><em>*</em>Jenis Projek :</label>
                <s:text name="projek.jenisProjek" size="50" maxlength="50" id="jenisProjek" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label><em>*</em>Jumlah unit dalam projek :</label>
                <s:text name="projek.jumlahSemuaUnit" size="50" maxlength="5" id="jumlahSemuaUnit" onkeypress="return isNumberKey(event)"/>
            </p>

            <p>
                <label><em>*</em>Jumlah unit yang layak diberi pertimbangan :</label>
                <s:textarea name="projek.maksimumUnit"  rows="3" cols="51" id="maksimumUnit" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <div class="content" align="center">
                <p>
                    <s:submit name="simpan" id="save" value="Simpan" class="btn" onclick="" />
                    <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
                </p>
            </div>
        </fieldset>
        <br />
        <fieldset class="aras1">
            <legend>
                Senarai Maklumat Projek Hartanah
            </legend>

            <div align="center">
                <display:table style="width:90%;" class="tablecloth" name="${actionBean.listProjek}" cellpadding="0" cellspacing="0" id="line" pagesize="20" requestURI="${pageContext.request.contextPath}/consent/projek">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idProjek}"/>
                    </display:column>

                    <display:column property="namaPemaju" title="Nama Pemaju" />
                    <display:column property="noRujukanProjek" title="Nombor Rujukan" />
                    <display:column property="jenisProjek" title="Jenis Projek" />
                    <display:column property="jumlahSemuaUnit" title="Jumlah Unit" />
                    <display:column property="maksimumUnit" title="Jumlah unit layak diberi pertimbangan" />

                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="edit('${line_rowNum -1}');
                                     return false;" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                    </display:column>

                    <display:column title="Hapus">
                        <p align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="hapus('${line_rowNum -1}');
                                     return false;" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                    </display:column>
                </display:table>
            </div>
            <br/>
        </fieldset>

    </div>
</s:form>