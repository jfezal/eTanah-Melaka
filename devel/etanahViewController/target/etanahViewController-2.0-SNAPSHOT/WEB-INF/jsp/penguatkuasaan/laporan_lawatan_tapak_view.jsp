<%-- 
    Document   : laporan_lawatan_tapak
    Created on : Jan 18, 2010, 2:43:10 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.penguatkuasaan.AduanSiasatanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Lawatan Tapak Dan Siasatan
            </legend>
            <div class="content">
                <p>
                    <label>Tarikh Lawatan :</label>
                    <fmt:formatDate value="${actionBean.aduanSiasatan.tarikhSiasat}" pattern="dd/MM/yyyy" />&nbsp;
                </p>
                <p>
                    <label>Tanah Pekan / Bandar / Desa :</label>
                 ${actionBean.aduanSiasatan.laporanTanah}&nbsp;
                </p>
                <p>
                    <label>Lokasi Tanah :</label>
                    ${actionBean.aduanSiasatan.laporanLokasi}&nbsp;
                </p>
                <p>
                    <label>Keadaan Geografi Tanah :</label>
                    ${actionBean.aduanSiasatan.laporanGeografi}&nbsp;
                </p>
                <p>
                    <label>Jenis Perusahaan :</label>
                   ${actionBean.aduanSiasatan.aktiviti}&nbsp;
                </p>

                <p>
                    <label>Jalan Keluar Masuk :</label>
                    ${actionBean.aduanSiasatan.laporanJalan}&nbsp;
                </p>
                <p>
                    <label>Kemudahan Asas :</label>
                    ${actionBean.aduanSiasatan.kemudahanAsas}&nbsp;
                </p>
                <p>
                    <label>Pembangunan kawasan sekeliling (lingkungan 1.0km) :</label>
                    <c:if test="${actionBean.aduanSiasatan.adaPembangunan ne 'T'}">Ada</c:if>
                    <c:if test="${actionBean.aduanSiasatan.adaPembangunan eq 'T'}">Tiada</c:if>&nbsp;
                </p>
                <br>

                <p>
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne '426'}">
                    <label>Catatan :</label></c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '426'}">
                    <label>Jenis Bahan Batuan yang Dipindah/Digali:</label></c:if>
                    ${actionBean.aduanSiasatan.catatan}&nbsp;
                    <s:textarea name="aduanSiasatan.catatan" rows="5" cols="50" />
                
                </p>

                <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '426'}">
                  </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '425A'}">
                    <label>Catatan:</label>
                  </c:if>
                    <label>Jenis Bahan Batuan yang Dipindah/Digali:</label>
                      <s:textarea name="aduanSiasatan.catatan" rows="5" cols="50"/>&nbsp;--%>
                
                <br><br>
                <fieldset class="aras1">
                    <legend>
                       Tanah Sekeliling
                    </legend>
                    <div class="content" align="center">
                        <table class="tablecloth">
                        <tr>
                            <th>&nbsp;</th><th>Jenis Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                        </tr>
                        <tr>
                            <th>
                                Utara
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanUtaraNoLot}&nbsp;

                            </td>
                            <td>
                               ${actionBean.aduanSiasatan.sempadanUtaraKegunaan}&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Selatan
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanSelatanNoLot}&nbsp;
                               
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanSelatanKegunaan}&nbsp;
                                
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Timur
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanTimurNoLot}&nbsp;
                               
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanTimurKegunaan}&nbsp;
                                
                            </td>
                        </tr><tr>
                            <th>
                                Barat
                            </th>
                            <td>
                                 <c:if test="${actionBean.aduanSiasatan.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                <c:if test="${actionBean.aduanSiasatan.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanBaratNoLot}&nbsp;
                               
                            </td>
                            <td>
                                ${actionBean.aduanSiasatan.sempadanBaratKegunaan}&nbsp;
                               
                            </td>
                        </tr>
                    </table></div>
                    <br>

                </fieldset>
            </div>
        </fieldset>
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <p align="right">            
            <s:submit name="senaraitugasan" value="Keluar" class="btn"/>
        </p>
    </div>
</s:form>