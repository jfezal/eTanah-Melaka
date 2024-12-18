<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<style type="text/css">
  #tdLabel {
    color:#003194;
    display:block;
    float:left;
    font-family:Tahoma;
    font-size:13px;
    font-weight:bold;
    margin-left:100px;
    margin-right:0.5em;
    text-align:right;
    width:15em;
    vertical-align:top;
  }

  #tdDisplay {
    color:black;
    font-size:13px;
    font-weight:normal;
    float:left;
    width:40em;
  }
    #tdDisplay2 {
    color:black;
    font-size:13px;
    font-weight:normal;
    float:left;
    width:50em;
  }
</style>
<s:form beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklumat Permohonan</legend>
      <table border="0">
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td id="tdLabel">Permohonan :&nbsp;</td>
          <td id="tdDisplay">${actionBean.permohonanSebelum.kodUrusan.nama}&nbsp;</td>
        </tr>
        <tr>
          <td id="tdLabel">ID Permohonan :&nbsp;</td>
          <td id="tdDisplay">${actionBean.permohonanSebelum.idPermohonan}&nbsp;</td>
        </tr>
      </table>

      <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PJT'
                    || actionBean.permohonanSebelum.kodUrusan.kod eq 'PJLT'}">
            <br>
            <table border="0">
              <tr>
                <td id="tdLabel">Tarikh Mula Pajak :&nbsp;</td>
                <td id="tdDisplay"><fmt:formatDate value="${actionBean.hu.tarikhDaftar}" pattern="dd/MM/yyyy hh:mm:ss"/>&nbsp;</td>
              </tr>
              <tr>
                <td id="tdLabel">Tarikh Akhir Pajak :&nbsp;</td>
                <td id="tdDisplay"><fmt:formatDate value="${actionBean.hu.tarikhTamat}" pattern="dd/MM/yyyy hh:mm:ss"/>&nbsp;</td>
              </tr>
              <tr>
                <td id="tdLabel">Tempoh Pajak :&nbsp;</td>
                <td id="tdDisplay">
                  <c:if test="${actionBean.hu.tempohHari ne 0}">
                    ${actionBean.hu.tempohHari} Hari&nbsp;                    
                  </c:if> 
                  <c:if test="${actionBean.hu.tempohBulan ne 0}">
                    ${actionBean.hu.tempohBulan} Bulan&nbsp;                    
                  </c:if>
                  <c:if test="${actionBean.hu.tempohTahun ne 0}">
                    ${actionBean.hu.tempohTahun} Tahun                    
                  </c:if>
                </td>
              </tr>
            </table>
            <%--<p>
              <label for="Pajak Mula">Tarikh Mula Pajak :</label>
              <fmt:formatDate value="${actionBean.hu.tarikhDaftar}" pattern="dd/MM/yyyy hh:mm:ss"/>&nbsp;
            </p>
            <p>
              <label for="Pajak Tamat">Tarikh Akhir Pajak :</label>
              <fmt:formatDate value="${actionBean.hu.tarikhTamat}" pattern="dd/MM/yyyy hh:mm:ss"/>&nbsp;
            </p>            
            <p>
              <label for="Tempoh Pajak">Tempoh Pajak :</label>
              <c:if test="${actionBean.hu.tempohHari ne 0}">
                ${actionBean.hu.tempohHari} Hari&nbsp;                    
              </c:if> 
              <c:if test="${actionBean.hu.tempohBulan ne 0}">
                ${actionBean.hu.tempohBulan} Bulan&nbsp;                    
              </c:if>
              <c:if test="${actionBean.hu.tempohTahun ne 0}">
                ${actionBean.hu.tempohTahun} Tahun                    
              </c:if>
            </p>--%>
      </c:if>
      <br>
    </fieldset>
  </div>
  <br>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklumat Penyerah</legend>
      <table border="0">
        <tr><td>&nbsp;</td></tr>
        <tr>
          <td id="tdLabel">Nama Penyerah / Pengadu :&nbsp;</td>
          <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahNama}&nbsp;</td>
        </tr>
        <tr>
          <td id="tdLabel">Nombor Rujukan Penyerah :&nbsp;</td>
          <c:if test="${actionBean.permohonanSebelum.penyerahNoRujukan ne null}">
            <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahNoRujukan}&nbsp;</td>
          </c:if>
          <c:if test="${actionBean.permohonanSebelum.penyerahNoRujukan eq null}">
            <td id="tdDisplay"> - </td>
          </c:if>
        </tr>
        <tr>
          <td id="tdLabel">Alamat Penyerah :&nbsp;</td>
          <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat1}&nbsp;</td>
        </tr>
        <c:if test="${actionBean.permohonanSebelum.penyerahAlamat2 ne null}">
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat2}&nbsp;</td>
          </tr>
        </c:if>
        <c:if test="${actionBean.permohonanSebelum.penyerahAlamat3 ne null}">
          <tr>
            <td id="tdLabel">&nbsp;</td>
            <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat3}&nbsp;</td>
          </tr>
        </c:if>
        <tr>
          <td id="tdLabel">Bandar :&nbsp;</td>
          <c:if test="${actionBean.permohonanSebelum.penyerahAlamat4 ne null}">
            <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahAlamat4}&nbsp;</td>
          </c:if>
          <c:if test="${actionBean.permohonanSebelum.penyerahAlamat4 eq null}">
            <td id="tdDisplay"> - </td>
          </c:if>
        </tr>
        <tr>
          <td id="tdLabel">Poskod :&nbsp;</td>
          <c:if test="${actionBean.permohonanSebelum.penyerahPoskod ne null}">
            <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahPoskod}&nbsp;</td>
          </c:if>
          <c:if test="${actionBean.permohonanSebelum.penyerahPoskod eq null}">
            <td id="tdDisplay"> - </td>
          </c:if>
        </tr>
        <tr>
          <td id="tdLabel">Negeri :&nbsp;</td>
          <c:if test="${actionBean.permohonanSebelum.penyerahNegeri.nama ne null}">
            <td id="tdDisplay">${actionBean.permohonanSebelum.penyerahNegeri.nama}&nbsp;</td>
          </c:if>
          <c:if test="${actionBean.permohonanSebelum.penyerahNegeri.nama eq null}">
            <td id="tdDisplay"> - </td>
          </c:if>
        </tr>
      </table>      
      <br>
    </fieldset>
  </div>
  <br>
  <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PLT'}">
    <div class="subtitle">
      <fieldset class="aras1">
        <legend>Maklumat Mahkamah</legend>
        <p>
          <label>Perintah Larangan Atas Tanah bagi tempoh selama :</label>
          ${actionBean.hu.tempohHari} hari, ${actionBean.hu.tempohBulan} bulan, ${actionBean.hu.tempohTahun} tahun&nbsp;
        </p>
        <p>
          <label>mulai dari :</label>
          ${actionBean.hu.tarikhMula}&nbsp;
        </p>
        <p>
          <label>No. Perintah Mahkamah :</label>
          ${actionBean.hu.noSidang}&nbsp;
        </p>
        <p>
          <label>Bertarikh :</label>
          <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhSidang}"/>&nbsp;
        </p>
        <br>
      </fieldset >
    </div>
  </c:if>
  <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PLK'}">
    <div class="subtitle">
      <fieldset class="aras1">
        <legend>Maklumat Mahkamah</legend> 
        <p>
          <label>Perintah Larangan Atas Kepentingan ke atas :</label>
          ${actionBean.husblm.idPerserahan} - ${actionBean.husblm.kodUrusan.nama}&nbsp;
        </p>
        <p>
          <label>selama :</label>
          ${actionBean.hu.tempohHari} hari, ${actionBean.hu.tempohBulan} bulan, ${actionBean.hu.tempohTahun} tahun&nbsp;
        </p>
        <p>
          <label>mulai dari :</label>
          ${actionBean.hu.tarikhMula}&nbsp;
        </p>
        <p>
          <label>No. Perintah Mahkamah :</label>
          ${actionBean.hu.noSidang}&nbsp;
        </p>
        <p>
          <label>Bertarikh :</label>
          <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hu.tarikhSidang}"/>&nbsp;
        </p>
        <br>
      </fieldset >
    </div>
  </c:if>
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklumat Pihak Berkepentingan</legend>
      <div class="content" align="center">
        <display:table class="tablecloth" name="${actionBean.hpkList}"  cellpadding="0" cellspacing="0"
                       requestURI="/common/maklumat_hakmilik_permohonan" id="line">
          <display:column title="No">${line_rowNum}</display:column>
          <display:column property="pihak.nama" title="Nama"/>
          <display:column title="Jenis Pihak">
            ${line.jenis.nama}
            <c:if test="${line.jenis.kod eq 'PA'}">
              <br/>
              bagi pihak
              <br/>
              <c:forEach items="${line.senaraiWaris}" var="waris">
                ${waris.nama}<br/>
              </c:forEach>
            </c:if>
          </display:column>                    
        </display:table>
      </div>
    </fieldset >
  </div>
  <br>
  <!--start:paparkan pemberi dan penerima-->
  <c:if test="${actionBean.permohonanSebelum.kodUrusan.kod eq 'PMT'
                    || actionBean.permohonanSebelum.kodUrusan.kod eq 'GD'}">
  
       <div class="content">
          <fieldset class="aras1">
          <legend>Maklumat Pemberi dan Penerima</legend>
         <table border="0">
              <tr><td>&nbsp;</td></tr>
              <tr>
                  <td id="tdLabel">Pemberi :&nbsp;</td>
                  <td id="tdDisplay2" ><c:forEach items="${actionBean.pemohonList}" var="item">
                         -${item.nama}
                          <c:if test="${item.noPengenalan ne null}">
                              ,
                          </c:if>${item.noPengenalan}
                          <c:if test="${item.alamat.alamat1 ne null}">
                              ,
                          </c:if> ${item.alamat.alamat1}
                          <c:if test="${item.alamat.alamat2 ne null}">
                              ,
                          </c:if>${item.alamat.alamat2}
                          <c:if test="${item.alamat.alamat3 ne null}">
                              ,
                          </c:if>${item.alamat.alamat3}                         
                          <c:if test="${item.alamat.alamat4 ne null}">
                              ,
                          </c:if>${item.alamat.alamat4}                          
                          <c:if test="${item.alamat.poskod ne null}">
                              ,
                          </c:if>${item.alamat.poskod}                         
                        <c:if test="${item.alamat.negeri.nama ne null}">
                              ,
                          </c:if>${item.alamat.negeri.nama}&nbsp;                          
                          <br></c:forEach></td>
                  </tr>
              </table>
              <br>
              <table border="0">
                  <tr>
                      <td id="tdLabel">Penerima :&nbsp;</td>
                      <td id="tdDisplay2"><c:forEach items="${actionBean.permohonanPihakList}" var="item">
                              -${item.nama}
                              <c:if test="${item.noPengenalan ne null}">
                                  ,
                              </c:if>${item.noPengenalan}
                              <c:if test="${item.alamat.alamat1 ne null}">
                                  ,
                              </c:if> ${item.alamat.alamat1}
                              <c:if test="${item.alamat.alamat2 ne null}">
                                  ,
                              </c:if>${item.alamat.alamat2}
                              <c:if test="${item.alamat.alamat3 ne null}">
                                  ,
                              </c:if>${item.alamat.alamat3}                         
                              <c:if test="${item.alamat.alamat4 ne null}">
                                  ,
                              </c:if>${item.alamat.alamat4}                          
                              <c:if test="${item.alamat.poskod ne null}">
                                  ,
                              </c:if>${item.alamat.poskod}                         
                              <c:if test="${item.alamat.negeri.nama ne null}">
                                  ,
                              </c:if>${item.alamat.negeri.nama}&nbsp;   
                              <br></c:forEach></td>
              </tr>
          </table>  
          <br>
      </fieldset>
  </div>
  </c:if>
   <!--end:paparkan pemberi dan penerima-->
  <p>
    <label>&nbsp;</label>
    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
  </p>
</s:form>
