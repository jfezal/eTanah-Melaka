<%--
    Document   : viewSejarahWartaShj
    Created on : 18-May-2011, 18:39:29
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.pengambilan.wartaNewActionBean">

             <%--<div class="content">
                <fieldset class="aras1">
                    <legend>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4'}">Warta Seksyen 4</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">Warta Seksyen 831(A)-Borang D</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq '831BC'}">Warta Borang 831(B)(C)-Borang D</c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PB'}">Paparan Sejarah Warta Terdahulu</c:if>
                    </legend>
                </fieldset>
     </div>--%>
     <div class="subtitle displaytag">
        <fieldset class="aras1" id="locate">
            <legend>
                Paparan Sejarah Warta Terdahulu
            </legend>
            <p align="center"><label></label>
                <display:table class="tablecloth" name="${actionBean.permohonanRujukanLuarList_sebelum}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column title="Id Permohonan Terdahulu" style="vertical-align:top;">
                        ${line.permohonan.idPermohonan}
                    </display:column>
                    <display:column title="Nama Urusan" style="vertical-align:top;">
                       ${line.permohonan.kodUrusan.nama}
                    </display:column>
                    <display:column property="catatan" title="Jenis" />
                    <display:column property="item" title="No. Warta" />
                    <display:column title="Tarikh Warta" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhLulus}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <display:column title="Tarikh Terima" style="vertical-align:top;">
                            <fmt:formatDate value="${line.tarikhDisampai}" pattern="dd/MM/yyyy"/>
                        </display:column>
                    <display:column property="ulasan" title="Ulasan" />
                     </display:table>
            <br>
        </fieldset>
    </div>
   </s:form>
