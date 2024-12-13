<%-- 
    Document   : tambahDocJTEK
    Created on : Nov 14, 2013, 6:32:50 PM
    Author     : nurashidah
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    function selectCheckBox()
    {
        var e = $('#sizeKod').val();
        var cnt = 0;
        var data = new Array();
        for (cnt = 0; cnt < e; cnt++)
        {
            if (e == '1') {
                if (document.frm.checkmate.checked) {
//                                             alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate.value;
                }

            }
            else {
                if (document.frm.checkmate[cnt].checked) {
//                                             alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate[cnt].value;
                }
                else {
                    data[cnt] = "T";
                }
            }
        }
//                    alert(data) ;
        var idRuj = $('#idRuj').val();
        if (confirm("Adakah anda pasti?")) {
            var url = '${pageContext.request.contextPath}/utility/tambah_dok_JTEK?simpanDok&doc=' + data + '&idRuj=' + idRuj;
//                    alert(url);
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        alert("Rekod telah berjaya di masukkan");

                    }, 'html');
        }
    }
</script>

<s:form beanclass="etanah.view.utility.JTEKTambahDocActionBean" name="frm">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Senarai Ulasan dari Jabatan Teknikal
            </legend>
            <div class="content" align="center">
                <display:table name="${actionBean.senaraiDokumenSerta}" id="line" class="tablecloth" pagesize="30" cellpadding="0" cellspacing="0"
                               requestURI="${pageContext.request.contextPath}/utility/tambah_dok_JTEK">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="dokumen.kodDokumen.kod" title="Kod Dokumen" style="vertical-align:baseline"/>
                    <display:column property="dokumen.tajuk" title="Tajuk" style="vertical-align:baseline"/>

                </display:table>
            </div>
        </fieldset>

    </div>

    <div class="subtitle" align="center">

        <fieldset class="aras1">
            <div id="dok">
                <s:hidden name="${actionBean.sizeKod}" id="sizeKod" value="${actionBean.sizeKod}"/>
                <p>
                    <display:table style="width:50%" class="tablecloth" name="${actionBean.senaraiDokumenFolder}" pagesize="50" cellpadding="0" cellspacing="0" requestURI="/utility/tambah_dok_JTEK" id="line">
                        <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.idKandunganFolder}"/></display:column>
                        <display:column title="Kod Dokumen" property="dokumen.kodDokumen.kod"/>
                        <display:column title="Nama Dokumen" property="dokumen.tajuk" style="text-transform:uppercase;"/>
                    </display:table>
                </p>

                <s:hidden name="kodCaw" value="${actionBean.kodCaw}" id="kodCaw"/>
                <s:hidden name="idRuj" value="${actionBean.idRujukan}" id="idRuj" />
                <s:button name="simpanDok" value="Simpan" class="btn" onclick="javascript:selectCheckBox();"/>
                </p>


            </div>
    </div>  
</s:form>