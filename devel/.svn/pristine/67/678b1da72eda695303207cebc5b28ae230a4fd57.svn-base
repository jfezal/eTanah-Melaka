<%-- 
    Document   : butiran_mesin_jentera
    Created on : Jul 30, 2010, 8:30:31 PM
    Author     : sitifariza.hanim
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    function addJentera(){
        window.open("${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=810,height=300,scrollbars=no");
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.ButiranBahanBatuanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Butir-butir Kenderaan/Jentera Yang Digunakan</legend><br/>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listJentera}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/butiran_bahan_batuan">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <%--<s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>--%>
                    </display:column>
                    <display:column title="Jenis Kenderaan/Jentera">${line.jenisJentera}</display:column>
                    <display:column title="No. Pendaftaran" >${line.noPendaftaranJentera}</display:column>
                    <%--                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="remove('${line.idPemohon}')">
                        </div> 
</display:column> --%>
                </display:table>
            </div>
            <p align="center">
                <s:button name="" id="new" value="Tambah" class="btn" onclick="addJentera();"/>
            </p>

        </fieldset>
    </div>
</s:form>




