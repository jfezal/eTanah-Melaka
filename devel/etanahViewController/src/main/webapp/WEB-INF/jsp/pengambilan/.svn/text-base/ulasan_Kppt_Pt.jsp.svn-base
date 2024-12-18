<%-- 
    Document   : ulasan_Kppt_Pt
    Created on : Jul 3, 2014, 1:56:53 AM
    Author     : nurashidah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function removeDok(id) {
        if (confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/notaBaru?delete&idMohonNota=' + id;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.pengambilan.ulasanKpptPtEtapp">
    <s:messages/>
    <c:if test="${edit}">
        <div class="subtitle">

            <fieldset class="aras1">

                <div>
                    <label>Ulasan Ketua Penolong Pegawai tanah (PTD)</label>

                    <s:textarea name="kppt" id="kppt" cols="60" rows="10" class="normal_text">${actionBean.kppt}</s:textarea>

                        <br/>
                        <br/>

                        <br/>
                        <label>Ulasan Pentadbir Tanah (PTD)</label>

                    <s:textarea name="pt" id="pt" cols="60" rows="10" class="normal_text" >${actionBean.pt}</s:textarea>

                        <br/>
                        <label>&nbsp</label>

                        <br/>

                        <br/>
                        <br/>

                        <label>&nbsp</label>  <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                </div>
            </fieldset>
        </c:if>
        <c:if test="${show}">
            <fieldset class="aras1">

                <div>
                    <legend>Ulasan</legend>

                    <label >Ulasan Ketua Penolong Pegawai tanah (PTD):</label>
                    <s:textarea name="kppt" id="kppt" cols="60" rows="20" class="normal_text" disabled="true">${actionBean.kppt}</s:textarea>

                        <br/>
                        <br/>
                        <br/>

                        <label >Ulasan Pentadbir Tanah (PTD):</label>
                    <s:textarea name="pt" id="pt" cols="60" rows="20" class="normal_text" >${actionBean.pt}</s:textarea>
                    </div>
                </fieldset>
                <br/>
                <label>&nbsp</label>

        </c:if>

    </div>
</s:form>
