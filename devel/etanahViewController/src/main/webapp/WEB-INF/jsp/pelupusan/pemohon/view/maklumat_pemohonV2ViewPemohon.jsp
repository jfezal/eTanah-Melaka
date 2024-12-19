<%-- 
    Document   : maklumat_pemohonV2ViewTanahMilikPemohon
    Created on : Jul 23, 2013, 3:30:32 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Tanah Milik Pemohon</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    function refreshpagePemohon(type) {
        //        NoPrompt() ;
        var url = '${pageContext.request.contextPath}/pelupusan/maklumat_pemohonV2?refreshpage&type=' + type;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
</script>
<body>

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohonV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idPemohon" id="idPemohon"/>
        <div class="content" >
            <fieldset class="aras1">
                <legend>Maklumat Pemohon</legend>
                <br/>
                <table class="tablecloth" border="0" align="center">
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                        <tr>
                            <td>
                                Nama :
                            </td>
                            <td>
                                ${actionBean.pihak.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jenis Pengenalan :
                            </td>
                            <td>
                                ${actionBean.pihak.jenisPengenalan.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Nombor Pengenalan :
                            </td>
                            <td>
                                ${actionBean.pihak.noPengenalan}
                            </td>
                        </tr>
                        <tr>

                            <td>
                                Bangsa :
                            </td>
                            <td>
                                ${actionBean.pihak.bangsa.nama}   
                            </td>
                        </tr>

                        <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B'}">
                            <tr>
                                <td>
                                    Warna Kad Pengenalan :
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${actionBean.pihak.warnaKP eq 'B'}">
                                            Biru
                                        </c:when>
                                        <c:when test="${actionBean.pihak.warnaKP eq 'C'}">
                                            Coklat
                                        </c:when>
                                        <c:when test="${actionBean.pihak.warnaKP eq 'H'}">
                                            Hijau
                                        </c:when>
                                        <c:when test="${actionBean.pihak.warnaKP eq 'M'}">
                                            Merah
                                        </c:when>    
                                        <c:when test="${actionBean.pihak.warnaKP eq 'L'}">
                                            Lain-lain
                                        </c:when>    
                                        <c:otherwise>
                                            Tiada
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Tarikh Lahir :
                            </td>
                            <td>
                                <s:format value="${actionBean.pihak.tarikhLahir}" formatPattern="dd/MM/yyyy"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Umur :
                            </td>
                            <td>
                                <s:hidden name="pemohon.idPemohon"/>
                                ${actionBean.pemohon.umur} Tahun
                            </td>
                        </tr>

                        <tr>
                            <td>Tempat Lahir :</td>
                            <td>
                                ${actionBean.pihak.tempatLahir}
                            </td>
                        </tr>


                        <tr>
                            <td>
                                Jantina :
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${actionBean.pihak.kodJantina eq '1'}">
                                        Lelaki
                                    </c:when>
                                    <c:when test="${actionBean.pihak.kodJantina eq '2'}">
                                        Perempuan
                                    </c:when>
                                    <c:otherwise>
                                        Tiada
                                    </c:otherwise>
                                </c:choose>
                            </td>


                        </tr>

                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <tr>
                                <td>
                                    Anak Tempatan :
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${actionBean.pihak.anakTempatan eq 'Y'}">
                                            Ya
                                        </c:when>
                                        <c:when test="${actionBean.pihak.anakTempatan eq 'T'}">
                                            Tidak
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Tempoh tinggal di Melaka (tahun) : 
                                </td>
                                <td>
                                    ${actionBean.pemohon.tempohMastautin}
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>
                                Kewarganegaraan :
                            </td>
                            <td>
                                ${actionBean.pihak.wargaNegara.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Pekerjaan :
                            </td>
                            <td>
                                ${actionBean.pemohon.pekerjaan} 
                            </td>

                        </tr>
                        <tr>
                            <td>
                                Pendapatan Bulanan (RM) :
                            </td>
                            <td>
                                ${actionBean.pemohon.pendapatan} 
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Status Perkahwinan :
                            </td>
                            <td>
                                ${actionBean.pemohon.statusKahwin}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tanggungan Anak :
                            </td>
                            <td>
                                ${actionBean.pemohon.tanggungan}
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq '0'}">
                        <tr>
                            <td>
                                Nama :
                            </td>
                            <td>
                               ${actionBean.pihak.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Jenis Pengenalan :
                            </td>
                            <td>
                                ${actionBean.pihak.jenisPengenalan.nama}
                            </td>
                        </tr>
                    </c:if>    
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S'}">
                        <tr>
                            <td>
                                Nama Syarikat :
                            </td>
                            <td>
                                ${actionBean.pihak.nama}
                            </td> 
                        </tr>
                        <tr>
                            <td> 
                                Jenis Pengenalan :
                            </td>
                            <td>
                                ${actionBean.pihak.jenisPengenalan.nama}
                            </td>                              
                        </tr>
                        <tr>
                            <td>
                                Nombor Syarikat :
                            </td>
                            <td>
                                ${actionBean.pihak.noPengenalan}
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Jenis Syarikat :
                            </td>
                            <td>
                                ${actionBean.pihak.bangsa.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tarikh Penubuhan :
                            </td>
                            <td>
                                <s:format value="${actionBean.pihak.tarikhLahir}" formatPattern="dd/MM/yyyy"/>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                        <tr>
                            <td>
                                Nama Penubuhan :
                            </td>
                            <td>
                                ${actionBean.pihak.nama}
                            </td> 
                        </tr>
                        <tr>
                            <td> 
                                Jenis Pengenalan :
                            </td>
                            <td>
                                ${actionBean.pihak.jenisPengenalan.nama}
                            </td>                              
                        </tr>
                        <tr>
                            <td>
                                Nombor Penubuhan :
                            </td>
                            <td>
                                ${actionBean.pihak.noPengenalan}
                            </td>
                        </tr>

                        <tr>
                            <td>
                                Jenis Penubuhan :
                            </td>
                            <td>
                                ${actionBean.pihak.bangsa.nama}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Tarikh Penubuhan :
                            </td>
                            <td>
                                <s:format value="${actionBean.pihak.tarikhLahir}" formatPattern="dd/MM/yyyy"/>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            Alamat Berdaftar :
                        </td>
                        <td>
                            ${actionBean.pihak.alamat1}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            ${actionBean.pihak.alamat2}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            ${actionBean.pihak.alamat3}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            ${actionBean.pihak.alamat4}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Poskod :
                        </td>
                        <td>
                            ${actionBean.pihak.poskod}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Negeri :
                        </td>
                        <td>
                            ${actionBean.pihak.negeri.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Alamat Surat-Menyurat :
                        </td>
                        <td>
                            ${actionBean.pihakAlamatTambahan.alamatKetiga1}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            ${actionBean.pihakAlamatTambahan.alamatKetiga2}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            ${actionBean.pihakAlamatTambahan.alamatKetiga3}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            ${actionBean.pihakAlamatTambahan.alamatKetiga4}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Poskod :
                        </td>
                        <td>
                            ${actionBean.pihakAlamatTambahan.alamatKetigaPoskod}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Negeri :
                        </td>
                        <td>
                            ${actionBean.pihakAlamatTambahan.alamatKetigaNegeri.nama}
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                                No. Telefon (Rumah) :
                            </c:if>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'S' || actionBean.pihak.jenisPengenalan.kod eq 'U'}">
                                No. Telefon (Ofis) :    
                            </c:if>
                            <c:if test="${actionBean.pihak.jenisPengenalan.kod eq '0'}">
                                No. Telefon :    
                            </c:if>    
                        </td>
                        <td>
                            ${actionBean.pihak.noTelefon1}
                        </td>
                    </tr>
                    <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'||actionBean.pihak.jenisPengenalan.kod eq 'O'||actionBean.pihak.jenisPengenalan.kod eq 'F'||actionBean.pihak.jenisPengenalan.kod eq 'N'}">
                        <tr>
                            <td>
                                No. Telefon (Bimbit) :
                            </td>
                            <td>
                                ${actionBean.pihak.noTelefonBimbit}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            Emel :
                        </td>
                        <td>
                            ${actionBean.pihak.email}
                        </td>
                    </tr>
                </table>
                        <br/>

            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close();"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>

    </s:form>
</body>
