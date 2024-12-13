<%--
    Document   : maklumat_tanah
    Created on : Jul 1, 2010, 11:43:48 AM
    Author     : User
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.strata.MaklumatTanahActionBean">
    <s:messages/>
    <s:errors/>
    

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Tanah</legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td width="20%"><b>Lot :</b></td> ${actionBean.hakmilikPermohonan.hakmilik.noLot}</tr>
                    <tr>
                        <td><b>Id Hakmilik :</b></td>${actionBean.hakmilikPermohonan.hakmilik.idHakmilik}
                    </tr>
                    <tr>
                        <td><b>Tarikh Luput Pajakan :</b></td><fmt:formatDate value="${actionBean.hakmilikPermohonan.hakmilik.tarikhDaftar}" pattern="dd/MM/yyyy" />
                     </tr>
                    <tr>
                        <td><b>Luas :</b></td>${actionBean.hakmilikPermohonan.hakmilik.luas}
                    </tr>
                    <tr>
                        <td>
                            <b>Mukim :</b></td>${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}

                    </tr>
                    <tr>
                        <td><b>Daerah :</b></td>${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}

                    </tr>
                    <tr>
                        <td><b>Hasil Tahunan :</b></td>${actionBean.hakmilikPermohonan.hakmilik.cukai}
                    </tr>
                    <tr>
                        <td><b>Kategori Tanah :</b></td>${actionBean.hakmilikPermohonan.hakmilik.kodTanah.nama}
                    </tr>
                    <tr>
                        <td><b>Syarat Nyata :</b></td>${actionBean.hakmilikPermohonan.hakmilik.syaratNyata.syarat}
                    </tr>
                    <tr>
                        <td><b>Sekatan Kepentingan :</b></td>${actionBean.hakmilikPermohonan.hakmilik.sekatanKepentingan.sekatan}
                    </tr>
                    <tr>
                        <td><b>Jarak Dari Pusat Bandar :</b></td>
                    </tr>
                    <tr>
                        <td><b>Jenis Tanah/Bangunan :</b></td>
                    </tr>
                    <tr>
                        <td><b>Lokasi Tanah :</b></td>${actionBean.hakmilikPermohonan.hakmilik.lokasi}
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
  </s:form>