<%-- 
    Document   : maklumat_dokumen_pemohon
    Created on : Dec 4, 2012, 1:12:10 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">

</script>
<!DOCTYPE html>
<div class="a">
  <s:errors/>
  <s:messages/>
  <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen">
    <div class="subtitle">
      <fieldset class="aras1">
        <lagend>Maklumat Permohonan</lagend>
        <p><br>
        <table>
          <tr>
            <td><label>ID Hakmilik :</label></td>
            <td>${actionBean.idHakmilik}&nbsp;</td>
          </tr>
          <tr>
            <td><label>Kod Urusan / Urusan :</label></td>
            <td>
              <c:if test="${actionBean.permohonan.kodUrusan.kod ne null}">
                ${actionBean.permohonan.kodUrusan.kod} / ${actionBean.permohonan.kodUrusan.nama}
              </c:if>
              &nbsp;
            </td>
          </tr>    
          <tr>
            <td><label>Tarikh Perserahan :</label></td>
            <td><fmt:formatDate value="${actionBean.permohonan.tarikhKeputusan}" pattern="dd/MM/yyyy - hh:mm:ss a"/>&nbsp;</td>
          </tr> 
        </table>
        </p>
        <br>                
        <p>
          <label>Dokumen :</label>
          <display:table class="tablecloth" name="${actionBean.senaraiFolder}" id="line" cellpadding="0" cellspacing="0" requestURI="/utiliti/kutipan_dokumen" >  
            <display:column title="Bil" value="${line_rowNum}" style="text-align:center;" />
            <display:column title="Kod" style="width:100px; text-align:center;" property="dokumen.kodDokumen.kod"/>
            <display:column title="Nama / Tajuk Dokumen" style="width:300px;" property="dokumen.tajuk" />                    
          </display:table>  
        </p>
        <%--<c:if test="${actionBean.kodSerah eq 'N' 
                      || actionBean.kodSerah eq 'NB' 
                      || actionBean.kodSerah eq 'MH'}">
              <p>
                  <label>Dokumen :</label>
                  <display:table class="tablecloth" name="${actionBean.senaraiFolder}" id="line" cellpadding="0" cellspacing="0" 
                                 requestURI="/utiliti/kutipan_dokumen">                                                 
        <%--<display:column title="Bil" style="text-align:center;">
            <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE'}">
                ${line_rowNum}
            </c:if>
        </display:column> 
        <display:column title="Kod" style="width:100px; text-align:center;">
            <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' || line.dokumen.kodDokumen eq '4K'}">
                ${line.dokumen.kodDokumen.kod}
            </c:if>
        </display:column>                        
        <display:column title="Nama / Tajuk Dokumen" style="width:300px;">
            <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' || line.dokumen.kodDokumen eq '4K'}"> ${line.dokumen.tajuk}</c:if>
        </display:column>                        
    </display:table>
</p>
</c:if>--%>
        <%--<c:if test="${actionBean.kodSerah eq 'SC'}">
            <p>
                <label>Dokumen :</label>
                <display:table class="tablecloth" name="${actionBean.senaraiFolder}" id="line" cellpadding="0" cellspacing="0" 
                               requestURI="/utiliti/kutipan_dokumen"> 
                    <display:column title="Kod" style="width:100px; text-align:center;">
                        <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' 
                                      || line.dokumen.kodDokumen.kod eq 'SB' 
                                      || line.dokumen.kodDokumen.kod eq 'SW'
                                      || line.dokumen.kodDokumen.kod eq 'GD'
                                      || line.dokumen.kodDokumen.kod eq 'PJ'
                                      || line.dokumen.kodDokumen eq '4K'}">
                                  ${line.dokumen.kodDokumen.kod}
                              </c:if>
                        </display:column>                        
                        <display:column title="Nama / Tajuk Dokumen" style="width:300px;">
                            <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' 
                                          || line.dokumen.kodDokumen.kod eq 'SB' 
                                          || line.dokumen.kodDokumen.kod eq 'SW'
                                          || line.dokumen.kodDokumen.kod eq 'GD'
                                          || line.dokumen.kodDokumen.kod eq 'PJ'
                                          || line.dokumen.kodDokumen eq '4K'}">
                                      ${line.dokumen.tajuk}
                                  </c:if>
                            </display:column>                        
                        </display:table>
                    </p>
                </c:if>--%>
        <%--<c:if test="${actionBean.kodSerah eq 'B'}">
            <p>
                <label>Dokumen :</label>
                <display:table class="tablecloth" name="${actionBean.senaraiFolder}" id="line" cellpadding="0" cellspacing="0" 
                               requestURI="/utiliti/kutipan_dokumen"> 
                    <display:column title="Kod" style="width:100px; text-align:center;">
                        <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' 
                                      || line.dokumen.kodDokumen.kod eq 'SB' 
                                      || line.dokumen.kodDokumen.kod eq 'SW'
                                      || line.dokumen.kodDokumen eq '4K'}">
                                  ${line.dokumen.kodDokumen.kod}
                              </c:if>
                        </display:column>                        
                        <display:column title="Nama / Tajuk Dokumen" style="width:300px;">
                            <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' 
                                          || line.dokumen.kodDokumen.kod eq 'SB' 
                                          || line.dokumen.kodDokumen.kod eq 'SW'
                                          || line.dokumen.kodDokumen eq '4K'}">
                                      ${line.dokumen.tajuk}
                                  </c:if>
                            </display:column>                        
                        </display:table>
                    </p>
                </c:if>--%>
        <%--<c:if test="${actionBean.kodSerah eq 'CR'}">
                    <p>
                        <label>Dokumen :</label>
                        <display:table class="tablecloth" name="${actionBean.senaraiFolder}" id="line" cellpadding="0" cellspacing="0" requestURI="/utiliti/kutipan_dokumen"> 
                            <display:column title="Kod" style="width:100px; text-align:center;">
                                <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' 
                                              || line.dokumen.kodDokumen.kod eq 'CR' 
                                              || line.dokumen.kodDokumen.kod eq 'SCRH'
                                              || line.dokumen.kodDokumen.kod eq 'DHSCR'
                                              || line.dokumen.kodDokumen.kod eq 'SDSCR'
                                              || line.dokumen.kodDokumen.kod eq 'SCR'
                                              || line.dokumen.kodDokumen.kod eq 'CRH'
                                              || line.dokumen.kodDokumen eq '4K'}">
                                          ${line.dokumen.kodDokumen.kod}
                                      </c:if>
                                </display:column>                        
                                <display:column title="Nama / Tajuk Dokumen" style="width:300px;">
                                    <c:if test="${line.dokumen.kodDokumen.kod eq 'DHKE' 
                                                  || line.dokumen.kodDokumen.kod eq 'CR' 
                                                  || line.dokumen.kodDokumen.kod eq 'SCRH'
                                                  || line.dokumen.kodDokumen.kod eq 'DHSCR'
                                                  || line.dokumen.kodDokumen.kod eq 'SDSCR'
                                                  || line.dokumen.kodDokumen.kod eq 'SCR'
                                                  || line.dokumen.kodDokumen.kod eq 'CRH'
                                                  || line.dokumen.kodDokumen eq '4K'}">
                                              ${line.dokumen.tajuk}
                                          </c:if>
                                    </display:column>                        
                                </display:table>
                            </p>
                        </c:if> --%>
        <br><br>
      </fieldset>
    </div>
  </s:form>        
</div>
