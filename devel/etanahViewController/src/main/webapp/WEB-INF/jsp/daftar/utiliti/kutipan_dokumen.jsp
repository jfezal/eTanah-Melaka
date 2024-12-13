<%-- 
    Document   : kutipan_dokumen
    Created on : Nov 26, 2012, 11:47:14 AM
    Author     : (╯°□°)╯︵ tapitapeh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
  <head><title>.:eTanah:. Kutipan Dokumen</title>        
    <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>        

    <script type="text/javascript">
      $(document).ready(function() {
        var serah = $('#cariJenisPenyerah').val();
        if (serah != "") {
          $('#cariIdPermohonan').val("");
          $('#cariIdHakmilik').val("");
          $('#noPenyerah').show();
        } else {
          $('#noPenyerah').hide();
          $('#idPenyerah').val("");
        }

        $('#isiSemula').click(function() {
          $('#cariIdPermohonan').val("");
          $('#cariIdHakmilik').val("");
          $('#cariJenisPenyerah').val("");
          $('#noPenyerah').hide();
          $('#idPenyerah').val("");
        });

        $('#cariIdPermohonan').click(function() {
          $('#cariIdHakmilik').val("");
          $('#cariJenisPenyerah').val("");
          $('#noPenyerah').hide();
          $('#idPenyerah').val("");
        });

        $('#cariIdHakmilik').click(function() {
          $('#cariIdPermohonan').val("");
          $('#cariJenisPenyerah').val("");
          $('#noPenyerah').hide();
          $('#idPenyerah').val("");
        });
      });

      function padam3(id) {
        var serah = $('#cariJenisPenyerah').val();
        if (serah != "") {
          $('#cariIdPermohonan').val("");
          $('#cariIdHakmilik').val("");
          $('#idPenyerah').val("");
          $('#noPenyerah').show();
        } else {
          $('#noPenyerah').hide();
          $('#idPenyerah').val("");
        }
      }

      function paparDokumen(f, e) {
        f.action = f.action + '?' + e;
        f.submit();
      }
    </script> 
  </head>    
  <body>           
    <s:messages />
    <s:errors />        
    <div id="kutipan">
      <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
      <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen" name="form">
        <div class="subtitle">                
          <fieldset class="aras1">         
            <s:hidden name="permohonan.idPermohonan"/>
            <legend>Kutipan Dokumen (Pertanyaan dan Cetakan Serahan Dokumen)</legend><br>
            <p>
              <label>ID Permohonan :</label>
              <s:text name="idPermohonan" id="cariIdPermohonan" onkeyup="this.value=this.value.toUpperCase();" style="width:210px;" /><em>atau</em>
            </p>  
            <p>
              <label>ID Hakmilik :</label>
              <s:text name="idHakmilik" id="cariIdHakmilik" onkeyup="this.value=this.value.toUpperCase();" style="width:210px;"/><em>atau</em>
            </p>
            <p>
              <label>Jenis Penyerah :</label>
              <s:select name="jenisPenyerah" id="cariJenisPenyerah" style="width:210px;" onchange="padam3(this.id)">
                <s:option value=""> --- Sila Pilih --- </s:option>
                <s:options-collection collection="${listUtil.senaraiKodPenyerah}" label="nama" value="kod"/>
              </s:select>
            </p>
            <div id="noPenyerah">
              <p>
                <label>No Penyerah :</label>
                <s:text name="idPenyerah" id="idPenyerah" onkeyup="this.value=this.value.toUpperCase();" style="width:210px;"/>
              </p>
            </div>
            <br>
            <p>
              <label>&nbsp;</label>
              <s:submit name="searchDokumen" value="Cari Dokumen" class="btn" id="searchDokumen" 
                        title="Klik Untuk Cari" onmouseover="this.style.cursor='pointer';"/>
              <s:button name="reset" id="isiSemula" value="Isi Semula" class="btn" 
                        title="Klik Untuk Isi Semula" onmouseover="this.style.cursor='pointer';"/>                        
            </p>
            <br><br>                    
          </fieldset>
        </div>
        <br>
        <c:if test="${actionBean.idPermohonan ne null}">                    
          <div class="subtitle" id="carianHakmilik" align="center">
            <fieldset class="aras1">
              <legend>Senarai Carian Id Permohonan : ${actionBean.idPermohonan}</legend><br>
              <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}" id="line"
                             pagesize="15" cellpadding="0" cellspacing="0" 
                             requestURI="/utiliti/kutipan_dokumen">  
                <display:column title="Bil" value="${line_rowNum}" style="text-align:center;"/> 
                <display:column title="ID Hakmilik" property="hakmilik.idHakmilik" style="width:200px; text-align:center;"/>
                <display:column title="ID Permohonan" property="permohonan.idPermohonan" style="width:180px; text-align:left;"/>
                <display:column title="Urusan" style="text-align:left;">
                  <b>${line.permohonan.kodUrusan.kod}</b> - ${line.permohonan.kodUrusan.nama} - ${line.hakmilik.idHakmilik}
                </display:column>                            
                <display:column title="Papar" style="text-align:center;">
                  <c:if test="${line.permohonan.keputusan.kod eq 'D' && line.permohonan.status eq 'SL'}">
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod eq 'B' 
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SC'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'N'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'NB'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'CR'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SW'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SA'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SB'}">                                          
                      <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen" name="form1">
                        <s:hidden name="backMohon" value="${actionBean.idPermohonan}"/>
                        <s:hidden name="backHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:hidden name="backPenyerah" value=""/>
                        <%--<s:text name="backIdPenyerah" value=""/>--%>
                        <s:hidden name="idPermohonan2" value="${line.permohonan.idPermohonan}"/>
                        <s:hidden name="idHakmilik2" value="${line.hakmilik.idHakmilik}"/>                                          
                        <s:submit name="paparDokumen" value="Papar" class="btn" id="paparDokumen" 
                                  title="Klik Untuk Papar" onmouseover="this.style.cursor='pointer';"/> 
                      </s:form>
                    </c:if>
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod ne 'B' 
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SC'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'N'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'NB'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'MH'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'CR'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SW'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SA'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SB'}">
                      <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/> 
                    </c:if>
                  </c:if>
                  <c:if test="${line.permohonan.keputusan.kod eq 'T' && line.permohonan.status eq 'SL'}">
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod eq 'B' 
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SC'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'N'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'NB'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'CR'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SW'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SA'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SB'}">                                          
                      <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen" name="form1">
                        <s:hidden name="backMohon" value="${actionBean.idPermohonan}"/>
                        <s:hidden name="backHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:hidden name="backPenyerah" value=""/>
                        <%--<s:text name="backIdPenyerah" value=""/>--%>
                        <s:hidden name="idPermohonan2" value="${line.permohonan.idPermohonan}"/>
                        <s:hidden name="idHakmilik2" value="${line.hakmilik.idHakmilik}"/>                                          
                        <s:submit name="paparDokumen" value="Papar" class="btn" id="paparDokumen" 
                                  title="Klik Untuk Papar" onmouseover="this.style.cursor='pointer';"/> 
                      </s:form>
                    </c:if>
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod ne 'B' 
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SC'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'N'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'NB'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'MH'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'CR'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SW'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SA'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SB'}">
                      <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/> 
                    </c:if>
                  </c:if>
                  <c:if test="${line.permohonan.keputusan.kod ne 'D' && line.permohonan.status ne 'SL'}">
                    <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/>
                  </c:if>
                </display:column>
              </display:table>
              <br><br>
            </fieldset>
          </div>
        </c:if> 
        <c:if test="${actionBean.idHakmilik ne null}">
          <div class="subtitle" id="carianHakmilik" align="center">
            <fieldset class="aras1">
              <legend>Senarai Carian Id Hakmilik : ${actionBean.hakmilik.idHakmilik}</legend><br>
              <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}" id="line"
                             pagesize="15" cellpadding="0" cellspacing="0" 
                             requestURI="/utiliti/kutipan_dokumen">  
                <display:column title="Bil" value="${line_rowNum}" style="text-align:center;"/> 
                <display:column title="ID Hakmilik" property="hakmilik.idHakmilik" style="width:200px; text-align:center;"/>
                <display:column title="ID Permohonan" property="permohonan.idPermohonan" style="width:180px; text-align:left;"/>
                <display:column title="Urusan" style="text-align:left;">
                  <b>${line.permohonan.kodUrusan.kod}</b> - ${line.permohonan.kodUrusan.nama}
                </display:column>                            
                <display:column title="Papar" style="text-align:center;">
                  <c:if test="${line.permohonan.keputusan.kod eq 'D' && line.permohonan.status eq 'SL'}">
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod eq 'B' 
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SC'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'N'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'NB'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'CR'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SW'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SA'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SB'}">  
                      <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen" name="form1">
                        <s:hidden name="backMohon" value="${actionBean.idPermohonan}"/>
                        <s:hidden name="backHakmilik" value="${actionBean.idHakmilik}"/>
                        <s:hidden name="backPenyerah" value=""/>
                        <%--<s:text name="backIdPenyerah" value=""/>--%>
                        <s:hidden name="idPermohonan2" value="${line.permohonan.idPermohonan}"/>
                        <s:hidden name="idHakmilik2" value="${line.hakmilik.idHakmilik}"/>                                          
                        <s:submit name="paparDokumen" value="Papar" class="btn" id="paparDokumen" 
                                  title="Klik Untuk Papar" onmouseover="this.style.cursor='pointer';"/> 
                      </s:form>
                    </c:if>

                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod ne 'B' 
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SC'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'N'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'NB'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'MH'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'CR'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SW'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SA'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SB'}">
                      <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/> 
                    </c:if>
                  </c:if>
                  <c:if test="${line.permohonan.keputusan.kod ne 'D' && line.permohonan.status ne 'SL'}">
                    <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/>
                  </c:if>
                </display:column>
              </display:table>
              <br><br>
            </fieldset>
          </div>
        </c:if> 
        <c:if test="${actionBean.jenisPenyerah ne null}">
          <div class="subtitle" id="carianPenyerah" align="center">
            <fieldset class="aras1">
              <legend>Senarai Carian ${actionBean.kodPenyerah.nama}</legend><br>
              <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}" id="line" 
                             pagesize="15" cellpadding="0" cellspacing="0" 
                             requestURI="/utiliti/kutipan_dokumen">
                <display:column title="Bil" value="${line_rowNum}" style="text-align:center;"/> 
                <display:column title="No Penyerah" property= "permohonan.idPenyerah" style="text-align:center;"/>
                <display:column title="ID Hakmilik" property="hakmilik.idHakmilik" style="width:200px; text-align:center;"/>
                <display:column title="ID Permohonan" property="permohonan.idPermohonan" style="width:180px; text-align:left;"/>
                <display:column title="Urusan" style="text-align:left;">
                  <b>${line.permohonan.kodUrusan.kod}</b> - ${line.permohonan.kodUrusan.nama}
                </display:column>  
                <display:column title="Papar" style="text-align:center;">
                  <c:if test="${line.permohonan.keputusan.kod eq 'D' && line.permohonan.status eq 'SL'}">
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod eq 'B' 
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SC'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'N'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'NB'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'MH'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'CR'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SW'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SA'
                                  || line.permohonan.kodUrusan.kodPerserahan.kod eq 'SB'}">                                         
                      <s:form beanclass="etanah.view.daftar.utiliti.UtilitiKutipanDokumen" name="form1">
                        <s:hidden name="backMohon" value=" "/>
                        <s:hidden name="backHakmilik" value=""/> 
                        <%--<s:text name="backIdPenyerah" value="${line.permohonan.idPenyerah}"/>--%>
                        <s:hidden name="backPenyerah" value="${actionBean.kodPenyerah.kod}"/>
                        <s:hidden name="idPermohonan2" value="${line.permohonan.idPermohonan}"/>
                        <s:hidden name="idHakmilik2" value="${line.hakmilik.idHakmilik}"/>                                          
                        <s:submit name="paparDokumen" value="Papar" class="btn" id="paparDokumen" 
                                  title="Klik Untuk Papar" onmouseover="this.style.cursor='pointer';"/> 
                      </s:form>
                    </c:if>
                    <c:if test="${line.permohonan.kodUrusan.kodPerserahan.kod ne 'B' 
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SC'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'N'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'NB'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'MH'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'CR'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SW'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SA'
                                  && line.permohonan.kodUrusan.kodPerserahan.kod ne 'SB'}">
                      <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/> 
                    </c:if>
                  </c:if>
                  <c:if test="${line.permohonan.keputusan.kod ne 'D' && line.permohonan.status ne 'SL'}">
                    <s:button name="paparDokumen" value="Papar" class="btn" disabled="true"/>
                  </c:if>
                </display:column>
              </display:table>
              <br><br>
            </fieldset>
          </div>
        </c:if>
      </s:form>        
    </div>
  </body>
</html>
