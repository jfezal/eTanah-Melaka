<%-- 
    Document   : kutipan_data_urusan
    Created on : Sep 24, 2013, 5:13:24 PM
    Author     : ei
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

  });

  function popupUrusanSC() {
    // GET POPUP URUSAN
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?tambahUrusan&jenisUrusan=" + $("#jenisUrusanSC").val() + "&kumpHm=" + $("#kumpHm").val();
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function popupUrusanN() {
    // GET POPUP URUSAN    
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?tambahUrusan&jenisUrusan=" + $("#jenisUrusanN").val() + "&kumpHm=" + $("#kumpHm").val();
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function popupUrusanB() {
    // GET POPUP URUSAN
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?tambahUrusan&jenisUrusan=" + $("#jenisUrusanB").val() + "&kumpHm=" + $("#kumpHm").val();
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function popupUrusanNB() {
    // GET POPUP URUSAN
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?tambahUrusan&jenisUrusan=" + $("#jenisUrusanNB").val() + "&kumpHm=" + $("#kumpHm").val();
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function hapusUrusan(val) {
    // DELETE URUSAN
    doBlockUI();
//    alert("id urusan : " + val);
    var url = '${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?hapusUrusan&id=' + val;
    frm = document.kutipanUrusan;
    frm.action = url;
    frm.submit();
  }

  function viewUrusan(val, val1) {
//    alert("id urusan : " + val);
//    alert("jenis utusan : " + val1)
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?tambahUrusan&jenisUrusan=" + val1
            + "&kumpHm=" + $("#kumpHm").val() + "&id=" + val;
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function viewPihak(val, val1) {
    var url = "${pageContext.request.contextPath}/daftar/utiliti/kemaskiniData?kemaskiniPihakBerkepentingan&jenisUrusan=" + val1
            + "&kumpHm=" + $("#kumpHm").val() + "&id=" + val;
    window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=800");
  }

  function doBlockUI() {
    $.blockUI({
      message: $('#displayBox'),
      css: {
        top: ($(window).height() - 50) / 2 + 'px',
        left: ($(window).width() - 50) / 2 + 'px',
        width: '50px'
      }
    });
  }
</script>
<!DOCTYPE html>
<div class="a">
  <s:errors/>
  <s:messages/>
  <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
  <s:form beanclass="etanah.view.daftar.utiliti.KemaskiniDataActionBean" name="kutipanUrusan" id="kutipanUrusan">
    <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
    <div class="subtitle">
      <fieldset class="aras1">
        <s:hidden name="kumpHm" id="kumpHm" value="${actionBean.kumpHm}"/> 
        <s:hidden name="idHakmilik" id="idHakmilik" value="${actionBean.idHakmilik}"/> 
        <lagend>Maklumat Urusan</lagend><br>

        <table class="tablecloth" width="98%" style="margin-left: auto; margin-right: auto;">
          <!---------------------------------- URUSAN SC -------------------------------------->
          <tr><th><span class="arrow">Senarai Urusan Suratcara (SC)</span></th>
          </tr>
          <tr id="SC">
            <td>
              <fieldset class="aras1">
                <legend></legend>
                <p style="color:red">
                  *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk mengemaskini maklumat urusan.<br/>
                </p>
                <div class="content" align="center">
                  <display:table class="tablecloth" style="width:98%;" cellpadding="0" cellspacing="0" id="line" 
                                 requestURI="/daftar/utiliti/kutipanData" name="${actionBean.listHakmilikUrusanSC}">
                    <display:column title="Bil" style="width:1%;"><p align='right'>${line_rowNum}</p></display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" sortable="true" style="width:17%;"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true" style="width:17%;"/>
                    <display:column title="Kod Urusan" sortable="true" style="width:8%;">
                      <div align='center'>${line.kodUrusan.kod}</div>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>
                    <display:column title="Pihak Berkepentingan" style="width:5%;">
                      <c:if test="${line.kodUrusan.kod eq 'PJT' 
                                    or line.kodUrusan.kod eq 'PJBT'
                                    or line.kodUrusan.kod eq 'GD'
                                    or line.kodUrusan.kod eq 'GDWM'
                                    or line.kodUrusan.kod eq 'GDCE'
                                    or line.kodUrusan.kod eq 'GDPJ'
                                    or line.kodUrusan.kod eq 'GDPJK'
                                    or line.kodUrusan.kod eq 'PJKT'
                                    or line.kodUrusan.kod eq 'PJKBT'
                                    or line.kodUrusan.kod eq 'TEN'
                                    or line.kodUrusan.kod eq 'TENBT'
                                    or line.kodUrusan.kod eq 'PMT'}">
                            <div align="center">
                              <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewPihak('${line.idUrusan}', 'SC');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                              </p>
                            </div>
                      </c:if>
                    </display:column>
                    <display:column title="Kemaskini" style="width:5%;">
                        <c:if test="${line.kodUrusan.kod ne 'GDWM'
                                    and line.kodUrusan.kod ne 'GDCE'}"> <!line.kodUrusan.kod ne 'GD' and !>
                            <div align="center">
                              <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewUrusan('${line.idUrusan}', 'SC');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                              </p>
                            </div>
                      </c:if>
                    </display:column>
                    <display:column title="Hapus" style="width:5%;">
                      <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem${line_rowNum}' onclick="hapusUrusan('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                      </div>
                    </display:column>
                  </display:table>
                  <br>
                  <p align="center">          
                    <s:hidden name="jenisUrusanSC" id="jenisUrusanSC" value="SC"/> 
                    <s:button class="btn" value="Tambah" name="" onclick="popupUrusanSC();"/>&nbsp;                
                  </p>
                </div>
              </fieldset>
            </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
          <!---------------------------------- URUSAN NOTA -------------------------------------->
          <tr><th><span class="arrow">Senarai Urusan Nota (N)</span></th>
          </tr>
          <tr id="N">
            <td>
              <fieldset class="aras1">
                <legend></legend>
                <p style="color:red">
                  *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk mengemaskini maklumat urusan.<br/>
                </p>
                <div class="content" align="center">
                  <display:table class="tablecloth" style="width:98%;" cellpadding="0" cellspacing="0" id="line" 
                                 requestURI="${pageContext.request.contextPath}/common/pihak" name="${actionBean.listHakmilikUrusanN}">
                    <display:column title="Bil" style="width:1%;"><p align='right'>${line_rowNum}</p></display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" sortable="true" style="width:15%;"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true" style="width:17%;"/>
                    <display:column title="Kod Urusan" sortable="true" style="width:8%;">
                      <div align='center'>${line.kodUrusan.kod}</div>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>
                    <display:column title="Pihak Berkepentingan" style="width:5%;">
                      <c:if test="${line.kodUrusan.kod eq 'HTB'}">
                            <div align="center">
                              <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewPihak('${line.idUrusan}', 'N');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                              </p>
                            </div>
                      </c:if>
                    </display:column>
                    <display:column title="Kemaskini" style="width:5%;">
                      <div align="center">
                        <p align="center">
                          <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                               onclick="viewUrusan('${line.idUrusan}', 'N');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                      </div>
                    </display:column>
                    <display:column title="Hapus" style="width:5%;">
                      <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem${line_rowNum}' onclick="hapusUrusan('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                      </div>
                    </display:column>
                  </display:table>
                  <br>
                  <p align="center">       
                    <s:hidden name="jenisUrusanN" id="jenisUrusanN" value="N"/> 
                    <s:button class="btn" value="Tambah" name="" onclick="popupUrusanN();"/>&nbsp;                
                  </p>
                </div>
              </fieldset>
            </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
          <!---------------------------------- URUSAN BORANG -------------------------------------->
          <tr><th><span class="arrow">Senarai Urusan Borang (B)</span></th>
          </tr>
          <tr id="B">
            <td>
              <fieldset class="aras1">
                <legend></legend>
                <p style="color:red">
                  *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk mengemaskini maklumat urusan.<br/>
                </p>
                <div class="content" align="center">
                  <display:table class="tablecloth" style="width:98%;" cellpadding="0" cellspacing="0" id="line" 
                                 requestURI="/daftar/utiliti/kutipanData" name="${actionBean.listHakmilikUrusanB}">
                    <display:column title="Bil" style="width:1%;"><p align='right'>${line_rowNum}</p></display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" sortable="true" style="width:15%;"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true" style="width:17%;"/>
                    <display:column title="Kod Urusan" sortable="true" style="width:8%;">
                      <div align='center'>${line.kodUrusan.kod}</div>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>
                    <display:column title="Pihak Berkepentingan" style="width:5%;">
                      <c:if test="${line.kodUrusan.kod eq 'KVST' 
                                    or line.kodUrusan.kod eq 'KVAT'
                                    or line.kodUrusan.kod eq 'KVLT'
                                    or line.kodUrusan.kod eq 'KVPT'
                                    or line.kodUrusan.kod eq 'KVSS'
                                    or line.kodUrusan.kod eq 'KVAS'
                                    or line.kodUrusan.kod eq 'KVLS'
                                    or line.kodUrusan.kod eq 'KVPS'
                                    or line.kodUrusan.kod eq 'PLS'                            
                                    or line.kodUrusan.kod eq 'KVSK'
                                    or line.kodUrusan.kod eq 'KVAK'
                                    or line.kodUrusan.kod eq 'KVPK'
                                    or line.kodUrusan.kod eq 'KVLK'
                                    or line.kodUrusan.kod eq 'PNPA'}">
                            <div align="center">
                              <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewPihak('${line.idUrusan}', 'SC');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                              </p>
                            </div>
                      </c:if>
                    </display:column>
                    <display:column title="Kemaskini" style="width:5%;">
                      <c:if test="${ line.kodUrusan.kod ne 'KVAT'
                                    and line.kodUrusan.kod ne 'KVSS'
                                    and line.kodUrusan.kod ne 'KVAS'
                                    and line.kodUrusan.kod ne 'KVPS'}">              <!line.kodUrusan.kod ne 'KVST' and     !>                 
                            <div align="center">
                              <p align="center">
                                <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                     onclick="viewUrusan('${line.idUrusan}', 'B');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                              </p>
                            </div>
                      </c:if>
                    </display:column>
                    <display:column title="Hapus" style="width:5%;">
                      <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem${line_rowNum}' onclick="hapusUrusan('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                      </div>
                    </display:column>
                  </display:table>
                  <br>
                  <p align="center">       
                    <s:hidden name="jenisUrusanB" id="jenisUrusanB" value="B"/> 
                    <s:button class="btn" value="Tambah" name="" onclick="popupUrusanB();"/>&nbsp;                
                  </p>
                </div>
              </fieldset>
            </td>
          </tr>         
          <tr><td>&nbsp;</td></tr>
                    <!---------------------------------- URUSAN NOTA BETUL -------------------------------------->
          <tr><th><span class="arrow">Senarai Urusan Nota Betul (NB)</span></th>
          </tr>
          <tr id="NB">
            <td>
              <fieldset class="aras1">
                <legend></legend>
                <p style="color:red">
                  *Klik butang (<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif'/>) untuk mengemaskini maklumat urusan.<br/>
                </p>
                <div class="content" align="center">
                  <display:table class="tablecloth" style="width:98%;" cellpadding="0" cellspacing="0" id="line" 
                                 requestURI="${pageContext.request.contextPath}/common/pihak" name="${actionBean.listHakmilikUrusanNB}">
                    <display:column title="Bil" style="width:1%;"><p align='right'>${line_rowNum}</p></display:column>
                    <display:column property="idPerserahan" title="ID Perserahan" sortable="true" style="width:15%;"/>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" sortable="true" style="width:17%;"/>
                    <display:column title="Kod Urusan" sortable="true" style="width:8%;">
                      <div align='center'>${line.kodUrusan.kod}</div>
                    </display:column>
                    <display:column property="kodUrusan.nama" title="Urusan" sortable="true"/>
                    <display:column title="Kemaskini" style="width:5%;">
                      <div align="center">
                        <p align="center">
                          <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                               onclick="viewUrusan('${line.idUrusan}', 'NB');
    return false;" onmouseover="this.style.cursor = 'pointer';">
                        </p>
                      </div>
                    </display:column>
                    <display:column title="Hapus" style="width:5%;">
                      <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem${line_rowNum}' onclick="hapusUrusan('${line.idUrusan}')" onmouseover="this.style.cursor = 'pointer';" >
                      </div>
                    </display:column>
                  </display:table>
                  <br>
                  <p align="center">       
                    <s:hidden name="jenisUrusanNB" id="jenisUrusanNB" value="NB"/> 
                    <s:button class="btn" value="Tambah" name="" onclick="popupUrusanNB();"/>&nbsp;                
                  </p>
                </div>
              </fieldset>
            </td>
          </tr>
          <tr><td>&nbsp;</td></tr>

        </table>
      </fieldset>
    </div>
  </s:form>        
</div>

