<%--
    Document   : surat_kelulusan_PJBTR
    Created on : Oct 18, 2011, 10:12:42 PM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
 function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
</script>
<s:form  beanclass="etanah.view.stripes.pelupusan.KeputusanPermohonanPJBTRActionBean">
    <s:messages/>

<%--    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Permohonan</legend>
            <p>
                <label for="Permohonan">Permohonan :</label>
                ${actionBean.permohonan.kodUrusan.nama}&nbsp;
            </p>
            <p>
                <label for="ID Permohonan">ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>
        </fieldset >
        <br>
    </div>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Penyediaan Surat Kelulusan</legend>
            <br>
            <p>
                <label><font color="red">*</font>Bayaran (RM) :</label>
                <s:text name="bayaran" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>

            </p>
<!--            <p>
                <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                <s:text name="tarikhMulaLesen" value="${actionBean.tarikhMulaLesen}"class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>-->
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJBTR'}">
                    <label>Keluasan Diluluskan:</label>
                    <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                    <s:select name="kodU" id="koduom">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="MP">Meter Padu</s:option>
                   </s:select>
                </c:if>
                 
            </p>
            <p>
                <label>Tempoh :</label>
                <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                Tahun. 
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanKelulusan" value="Sahkan Bayaran" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>

