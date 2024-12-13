<%-- 
    Document   : Borang L
    Created on : 23-Nov-2009, 15:18:17
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanBorang">
   <s:messages/>
<s:errors/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Borang L : Notis Untuk Mengemukakan Dokumen/Dokumen-Dokumen
                    </legend>

                    <div class="content" align="left">
               <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="20" cellpadding="0" cellspacing="0"
                               requestURI="/borangpengambilanmodule" id="line">
                   <s:hidden name="hakmilikPermohonan.id"></s:hidden>
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>

                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot/No PT" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Perbicaraan Pengambilan No." />
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="No.Warta" />
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Tarikh Warta" />
                    <display:column title="Tempoh Penyerahan">
                                <s:text size="5" name="tempohKeteranganBertulis[${line_rowNum - 1}]"/>Hari

                    </display:column>
                    <c:if test="${!edit}">
                    <display:column title="Hapus">
                   <div align="center">
                   <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                    id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                   </div>
                   </display:column>
                    </c:if>&nbsp;
                </display:table>
                        <br>
                            <br>
                            <br>
                            <p>
                            <label>&nbsp;</label>

                             <s:button name="saveborangf" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
                            </p>

                    </div>
                </fieldset>
            </div>
</s:form>
