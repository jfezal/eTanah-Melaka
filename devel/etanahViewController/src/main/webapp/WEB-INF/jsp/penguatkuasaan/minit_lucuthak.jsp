<%--
    Document   : minit_lucuthak
    Created on : 04-Feb-2010, 12:27:21
    Author     : farah.shafilla
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<s:form beanclass="etanah.view.penguatkuasaan.MinitLucuthakActionBean">
    <s:messages/>
    <s:errors/>
   <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Barang Rampasan
            </legend>
            <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.senaraiBarangRampasan}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="Barang yang Dirampas" property="item"></display:column>
                    <display:column title="Kuantiti" property="kuantiti"></display:column>
                    <%--<display:column title="Tarikh Rampasan" property="kuantiti"></display:column>--%>
                    <display:column title="Tempat Simpanan" property="tempatSimpanan"></display:column>
                    <display:column title="Status" >
                      <c:if test="${line.status.kod eq 'DC' || line.status.kod eq 'DT' || line.status.kod eq 'DS' || line.status.kod eq 'LH' }">
                    <s:select name="barangRampasanStatus${line_rowNum}" id="barangRampasanStatus" value="${line.status.kod}">
                       <s:option value="">Sila Pilih</s:option>
                       <s:option value="DC">Dalam Simpanan(Sudah Dituntut)</s:option>
                       <s:option value="DS">Dalam Simpanan(Belum Dituntut)</s:option>
                        <s:option value="DT">Dalam Simpanan(Tidak akan dilepaskan)</s:option>
                         <s:option value="LH">Lucut hak</s:option>
                          </s:select></c:if>
                        <c:if test="${line.status.kod ne 'DC' && line.status.kod ne 'DT' && line.status.kod ne 'DS' && line.status.kod ne 'LH' }">
                            ${line.status.nama}
                         </c:if>
                </display:column>
                         <display:column title="Catatan" >
                              <c:if test="${line.status.kod eq 'DC' || line.status.kod eq 'DT' || line.status.kod eq 'DS' || line.status.kod eq 'LH' }">
                                  <s:textarea cols="50" rows="5" name="catatan${line_rowNum}" id="catatan">${line.catatan}</s:textarea></c:if>
                                <c:if test="${line.status.kod ne 'DC' && line.status.kod ne 'DT' && line.status.kod ne 'DS' && line.status.kod ne 'LH' }">
                            ${line.catatan}
                         </c:if>
                    </display:column>        
            </display:table>
                 <table>
                        <tr>
                            <td align="right">
                               <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="edit" value="Simpan"/>
                            </td>
                        </tr>
                 </table>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
