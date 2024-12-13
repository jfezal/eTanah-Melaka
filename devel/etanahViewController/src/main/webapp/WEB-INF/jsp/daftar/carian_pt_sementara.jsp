<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>


<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Carian
        </legend>
        <div class="content" align="center">

            <s:form beanclass="etanah.view.daftar.SenaraiTugasPendaftaranActionBean">
                <table>                    
                    <tr>
                        <td class="rowlabel1">ID Permohonan :</td>
                        <td class="input1"><s:text name="permohonan.idPermohonan"/> </td>
                    </tr>                    
                    <tr>
                        <td></td>
                        <td><s:submit name="search" value="Cari" class="btn"/></td>
                    </tr>
                </table>

            </s:form>
            <br>

        </div>
    </fieldset>
</div>
<br>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Senarai Tugasan
        </legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.list}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="${pageContext.request.contextPath}/daftar/senarai_tugas" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>                
                <display:column property="idPermohonan" title="ID Mohon" href="${pageContext.request.contextPath}/workflow/kernal_action?stageId=kemasukan" paramId="idPermohonan" paramProperty="idPermohonan" sortable="true" sortName="idPermohonan"/>
                <display:column property="penyerahNama" title="Nama Penyerah" sortable="true" sortName="penyerahNama"/>
                <display:column property="penyerahNoRujukan" title="Penyerah No Rujukan" sortable="true" sortName="penyerahNoRujukan"/>
                <display:column property="kodUrusan.nama" title="Penyerah No Rujukan" sortable="true" sortName="penyerahNoRujukan"/>
            </display:table>

        </div>
    </fieldset>

</div>
<br>

