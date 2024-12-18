<%--
    Document   : betulStatusHakmilik
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>

<script type="text/javascript">

    function editPop(idH)
    {
        var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?popupAsas&idH='+idH;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=550,scrollbars=yes");
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Status Hakmilik
            </legend>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'HLPK'}">
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>
            </p>
            </c:if>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                    <display:column property="hakmilik.kodStatusHakmilik.nama"  title="Status Hakmilik" sortable="true" sortName="kodStatusHakmilik.kod"/>
                    <display:column title="Status Baru">
                        <s:select name="statusHakmilikbaru[${line_rowNum-1}]" id="statusHakmilikbaru[${line_rowNum-1}]">
                            <c:if test="${line.hakmilik.kodStatusHakmilik.kod eq 'D'}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.listKodStatusHakmilikD}" value="kod" label="nama"/>
                            </c:if>
                            <c:if test="${line.hakmilik.kodStatusHakmilik.kod eq 'B'}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${actionBean.listKodStatusHakmilikB}" value="kod" label="nama"/>
                            </c:if>
                        </s:select>
                    </display:column>
                </display:table>
                <br>
                <p>
                    <s:button name="saveStatus" value="Simpan" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                </p>
            </div>


            <br/>

        </fieldset>
    </div>

</s:form>