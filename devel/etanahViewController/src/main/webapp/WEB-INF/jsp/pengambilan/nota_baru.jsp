<%-- 
    Document   : nota_baru
    Created on : May 17, 2011, 9:16:14 PM
    Author     : sitifariza.hanim
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function removeDok(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/notaBaru?delete&idMohonNota='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.pengambilan.NotaBaruActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">

            <div>
                <legend>Nota/Kertas Minit</legend>

                <label >Nota :</label>
                <s:textarea name="nota" cols="60" rows="20" class="normal_text" onkeydown="limitText(this,499);" onkeyup="limitText(this,499);"/>
            </div>
        </fieldset>
        <br/>
        <label>&nbsp</label>
        <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div');"/>
        <s:button name="delete" class="btn" value="Hapus" onclick="removeDok('${actionBean.idMohonNota}');"/>
        <div>
            <fieldset class="aras1">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listNota}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="No" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >
                        <display:column title="Tarikh" style="width:90px;vertical-align:top;">
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                        </display:column>
                        <display:column title="Nama" property="infoAudit.dimasukOleh.nama" style="vertical-align:top;"/>
                        <display:column title="Jawatan" property="infoAudit.dimasukOleh.jawatan" style="vertical-align:top;"/>
                        <display:column title="Nota" style="width:80px;vertical-align:top;">
                            <textarea name="nota" style="background: transparent; border: 0px" cols="80" rows="5" readonly="readonly" id="text">${line.nota}</textarea>
                        </display:column>
                    </display:table>
                </div>

            </fieldset>
        </div>

    </div>
</s:form>