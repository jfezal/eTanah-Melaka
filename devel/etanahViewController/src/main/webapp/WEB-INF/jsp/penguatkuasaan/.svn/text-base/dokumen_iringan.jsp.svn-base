<%-- 
    Document   : dokumen_iringan
    Created on : Apr 14, 2011, 4:23:46 PM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<script type="text/javascript">
    function validateForm(){
        
        var kodPilih = $('#kodPilih').val();
        //alert("kod pilih :"+kodPilih);
        // return false;

        if($('#kodPilih').val() == ''){
            alert("Sila pilih dokumen iringan.");
            return false;
        }

        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++){
            var nama = document.getElementById('kodDok'+i);
            if(nama.value == kodPilih ){
                alert("Dokumen iringan ini telah wujud. Sila pilih dokumen iringan yang lain.");
                $('#kodPilih').focus();
                return false;
            }
        }

        return true;
    }

    function removeDok(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/dokumen_iringan?delete&idMohonDokIringan='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function findKodIringan(id){
        alert("id lalala: "+ id);

        if(id != ""){
            $.get('${pageContext.request.contextPath}/penguatkuasaan/dokumen_iringan?findDokIringan&id='+id,
            function(data){
                alert(data);
                if(data != "Exist"){
                    alert("Dokumen iringan tidak wujud. Sila lampirkan/muat turun dahulu dokumen tersebut di tab dokumen");
                    return false;
                }
            }, 'html');
        }
    }
 


</script>
<s:form beanclass="etanah.view.penguatkuasaan.DokumenIringanActionBean" name="form1">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Dokumen Iringan
            </legend>
            <div class="content">
                <p>
                    <label>Dokumen Iringan :</label>
                    <s:select name="kodDokumen.kod" value="5000" id="kodPilih">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.listDokIringan}" label="nama" value="kod" />
                    </s:select>&nbsp;
                    <s:button class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Tambah"/>
                </p>
            </div>
            <br/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Senarai Dokumen Iringan
                    </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listMohonDokIringan}" cellpadding="0" cellspacing="0"
                                       id="line">
                            <display:column title="Bil" sortable="true" style="vertical-align:baseline" class="rowCount">${line_rowNum}</display:column>
                            <display:column title="Kod Dokumen" style="vertical-align:baseline" class="${line_rowNum}">
                                ${line.kodDokumen.kod}
                                <s:hidden name="listMohonDokIringan[${line_rowNum-1}].kodDokumen.kod" id="kodDok${line_rowNum-1}" value="${line.kodDokumen.kod}"/>
                            </display:column>
                            <display:column property="kodDokumen.nama" title="Keterangan" style="vertical-align:baseline"/>
                            <display:column title="Tarikh" class="${line_rowNum}">
                                <fmt:formatDate pattern="dd/MM/yyyy" value="${line.infoAudit.tarikhMasuk}"/>
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeDok('${line.idMohonDokIringan}');"/>
                                </div>
                            </display:column>
                        </display:table>
                    </div>
                </fieldset>
            </div>
        </fieldset>

    </div>
</s:form>
