<%-- 
    Document   : paparan_pihakberkepentingan
    Created on : Apr 9, 2010, 6:21:29 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<div class="subtitle">
  <fieldset class="aras1">
    <legend>Maklumat Pihak Berkepentingan </legend>   
    <p>
      <label>Nama :</label>
      ${actionBean.hpk.nama}&nbsp;
    </p>
    <p>
      <label>Jenis No Pengenalan :</label>
      ${actionBean.hpk.jenisPengenalan.nama}&nbsp;
    </p>
    <p>
      <label>No Pengenalan :</label>
      ${actionBean.hpk.noPengenalan}&nbsp;
    </p>
    <p>
      <label>Jenis Pihak Berkepentingan :</label>
      ${actionBean.hpk.jenis.nama} &nbsp;
    </p>
    <p>
      <label>Syer :</label>
      ${actionBean.hpk.syerPembilang} / ${actionBean.hpk.syerPenyebut}&nbsp;
    </p>
    <p>
      <label>Warganegara :</label>
      <c:if test="${actionBean.hpk.wargaNegara.nama ne null}">
        ${actionBean.hpk.wargaNegara.nama}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.wargaNegara.nama eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>Bangsa :</label>
      <c:if test="${actionBean.hpk.pihak.bangsa.nama ne null}">
        ${actionBean.hpk.pihak.bangsa.nama}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.pihak.bangsa.nama eq null}">
        -
      </c:if>
    </p>
    <%--<p>
        <label>Jantina :</label>
        ${actionBean.hpk.pihak.kodJantina}
        &nbsp;
    </p>--%>
    <br>
    <p>
      <label>Alamat Tetap :</label>
      <c:if test="${actionBean.hpk.alamat1 ne null}">
        ${actionBean.hpk.alamat1}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.alamat1 eq null}">
        -
      </c:if>
    </p>
    <c:if test="${actionBean.hpk.alamat2 ne null}">
      <p>
        <label>&nbsp;</label>
        ${actionBean.hpk.alamat2}&nbsp;
      </p>
    </c:if>
    <c:if test="${actionBean.hpk.alamat3 ne null}">
      <p>
        <label>&nbsp;</label>
        ${actionBean.hpk.alamat3}&nbsp;
      </p>
    </c:if>
    <p>
      <label>Bandar :</label>
      <c:if test="${actionBean.hpk.alamat4 ne null}">
        ${actionBean.hpk.alamat4}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.alamat4 eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>Poskod : </label>
      <c:if test="${actionBean.hpk.poskod ne null}">
        ${actionBean.hpk.poskod}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.poskod eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>Negeri : </label>
      <c:if test="${actionBean.hpk.negeri.nama ne null}">
        ${actionBean.hpk.negeri.nama}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.negeri.nama eq null}">
        -  
      </c:if>
    </p>
    <br>
    <p>
      <label>Alamat Surat Menyurat :</label>
      <c:if test="${actionBean.hpk.alamatSurat.alamatSurat1 ne null}">
        ${actionBean.hpk.alamatSurat.alamatSurat1}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.alamatSurat.alamatSurat1 eq null}">
        -
      </c:if>
    </p>
    <c:if test="${actionBean.hpk.alamatSurat.alamatSurat2 ne null}">
      <p>
        <label>&nbsp;</label>
        ${actionBean.hpk.alamatSurat.alamatSurat2}&nbsp;
      </p>
    </c:if>
    <c:if test="${actionBean.hpk.alamatSurat.alamatSurat3 ne null}">
      <p>
        <label>&nbsp;</label>
        ${actionBean.hpk.alamatSurat.alamatSurat3}&nbsp;
      </p>
    </c:if>
    <p>
      <label>Bandar :</label>
      <c:if test="${actionBean.hpk.alamatSurat.alamatSurat4 ne null}">
        ${actionBean.hpk.alamatSurat.alamatSurat4} &nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.alamatSurat.alamatSurat4 eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>Poskod : </label>
      <c:if test="${actionBean.hpk.alamatSurat.poskodSurat ne null}">
        ${actionBean.hpk.alamatSurat.poskodSurat}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.alamatSurat.poskodSurat eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>Negeri : </label>
      <c:if test="${actionBean.hpk.alamatSurat.negeriSurat.nama ne null}">
        ${actionBean.hpk.alamatSurat.negeriSurat.nama}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.alamatSurat.negeriSurat.nama eq null}">
        -
      </c:if>
    </p>
    <br>
    <p>
      <label>No Telefon Bimbit : </label>
      <c:if test="${actionBean.hpk.pihak.noTelefonBimbit ne null}">
        ${actionBean.hpk.pihak.noTelefonBimbit}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.pihak.noTelefonBimbit eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>No Telefon (Rumah) : </label>
      <c:if test="${actionBean.hpk.pihak.noTelefon1 ne null}">
        ${actionBean.hpk.pihak.noTelefon1}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.pihak.noTelefon1 eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>No Telefon (Pejabat) : </label>
      <c:if test="${actionBean.hpk.pihak.noTelefon2 ne null}">
        ${actionBean.hpk.pihak.noTelefon2}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.pihak.noTelefon2 eq null}">
        -
      </c:if>
    </p>
    <p>
      <label>Email : </label>
      <c:if test="${actionBean.hpk.pihak.email ne null}">
        ${actionBean.hpk.pihak.email}&nbsp;
      </c:if>
      <c:if test="${actionBean.hpk.pihak.email eq null}">
        -
      </c:if>
    </p>

    <c:if test="${actionBean.hpk.jenis.kod eq 'PM' && actionBean.permohonanPemilikan ne null}">
      <p>
        <c:if test="${actionBean.permohonanPemilikan.kodUrusan.kod eq 'PMT' || actionBean.permohonanPemilikan.kodUrusan.kod eq 'PMTM' || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAME'
                      || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAMF' || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAMD' || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAMG'
                      || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAML' || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAMT' || actionBean.permohonanPemilikan.kodUrusan.kod eq 'TMAMW'}">
              <label>Cara Pemilikan :</label>      
              Hakmilik ini diberimilik daripada urusan ${actionBean.permohonanPemilikan.kodUrusan.nama} dan ID Perserahan : ${actionBean.permohonanPemilikan.idPermohonan}
        </c:if>
      </p>
    </c:if>
      <br>
  </fieldset>
</div>