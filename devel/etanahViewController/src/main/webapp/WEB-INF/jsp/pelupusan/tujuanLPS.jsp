<%--
    Document   : surat_kelulusan_lps
    Created on : May 11, 2010, 7:59:42 PM
    Author     : wan.fairul
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
<s:form  beanclass="etanah.view.stripes.pelupusan.TujuanLPSActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tujuan LPS</legend>
            <br>
            <c:if test="${actionBean.stageId eq '01Kemasukan'}">
            <p>
                <label>
                    <font color="red">*</font>Tujuan  :
                </label>
                
                    <s:select name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                    </s:select>
                
                
            </p>
            <br>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
            </p>
            </c:if>
            <c:if test="${actionBean.stageId ne '01Kemasukan'}">
                 <p>
                <label>
                    <font color="red">*</font>Tujuan  :
                </label>
                    ${actionBean.ppi.kodItemPermit.nama}
                    </p>
                    <br>
                </c:if>
            
        </fieldset >
    </div>
</s:form>

