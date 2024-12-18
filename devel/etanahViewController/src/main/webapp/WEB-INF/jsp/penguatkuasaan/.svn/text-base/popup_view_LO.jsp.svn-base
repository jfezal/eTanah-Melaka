<%-- 
    Document   : popup_view_LO
    Created on : Nov 18, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanOperasiActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Laporan Operasi
            </legend>
            <div class="content">
                <table>
                    <tr>
                        <td valign="top">
                            <p><label>Jenis Operasi :</label></p></td>
                        <td valign="top">
                            <font size="2px;" color="black" style="font-style: normal; font-weight: normal" >
                                <c:if test="${actionBean.operasiPenguatkuasaan.jenisTindakan ne null}">${actionBean.operasiPenguatkuasaan.jenisTindakan}</c:if>
                                <c:if test="${actionBean.operasiPenguatkuasaan.jenisTindakan eq null}"> Tiada Data </c:if></font></td>
                    </tr>
                </table>
                <p>
                    <label>Tarikh Laporan:</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>&nbsp;</c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Masa Laporan :</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}">
                        <fmt:formatDate pattern="hh:mm" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>
                        <fmt:formatDate pattern="aaa" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}" var="time"/>
                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                    </c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Tarikh Penahanan : </label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhPenahanan ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.operasiPenguatkuasaan.tarikhPenahanan}"/>&nbsp;</c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhPenahanan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Masa Penahanan :</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhPenahanan ne null}">
                        <fmt:formatDate pattern="hh:mm" value="${actionBean.operasiPenguatkuasaan.tarikhPenahanan}"/>
                        <fmt:formatDate pattern="aaa" value="${actionBean.operasiPenguatkuasaan.tarikhPenahanan}" var="time"/>
                        <c:if test="${time eq 'AM'}">PAGI</c:if>
                        <c:if test="${time eq 'PM'}">PETANG</c:if>
                    </c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.tarikhPenahanan eq null}"> Tiada Data </c:if>

                </p>
                <p>
                    <label>Kawasan Rondaan :</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.lokasi ne null}">${actionBean.operasiPenguatkuasaan.lokasi}&nbsp;</c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.lokasi eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Kenderaan Operasi :</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.kenderaan ne null}">${actionBean.operasiPenguatkuasaan.kenderaan}&nbsp;</c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.kenderaan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Jumlah Tangkapan :</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.jumlahTangkapan ne null}">${actionBean.operasiPenguatkuasaan.jumlahTangkapan}&nbsp;Orang</c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.jumlahTangkapan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Nilai Kecurian(RM):</label>
                    <c:if test="${actionBean.operasiPenguatkuasaan.nilaiKecurian ne null}">
                        <s:format formatPattern="#,##0.00" value="${actionBean.operasiPenguatkuasaan.nilaiKecurian}"/>
                    </c:if>
                    <c:if test="${actionBean.operasiPenguatkuasaan.nilaiKecurian eq null}"> Tiada Data </c:if>
                </p>
                <table>
                    <tr>
                        <td valign="top">
                            <p><label>Laporan Operasi Pemantauan :</label></p></td>
                        <td valign="top">
                            <font size="2px;" color="black" style="font-style: normal; font-weight: normal">
                                <c:if test="${actionBean.operasiPenguatkuasaan.catatan ne null}">${actionBean.operasiPenguatkuasaan.catatan}&nbsp;</c:if>
                                <c:if test="${actionBean.operasiPenguatkuasaan.catatan eq null}"> Tiada Data </c:if></font></td>
                    </tr>
                </table>

                <br/>
            </div>

        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pasukan
            </legend>
            <br/>

            <div class="content">

                <p align="center"><label><u>Ketua Operasi/Ketua Serbuan</u></label></p><br/>
                <p>
                    <label>Ketua Operasi :</label>
                    ${actionBean.namaKetua}&nbsp;
                </p>
                <p>
                    <label>Jawatan :</label>
                    ${actionBean.tempatKerjaKetua}&nbsp;
                </p>
                <p>
                    <label>No.pengenalan :</label>
                    ${actionBean.noPengenalanKetua}&nbsp;
                </p>
                <p>
                    <label>No.Kad Kuasa :</label>
                    ${actionBean.kadKuasaKetua}&nbsp;
                </p>

                <fieldset class="aras2">
                    <legend>
                        Senarai Pasukan
                    </legend>

                    <div align="center" >
                        <display:table name="${actionBean.senaraiPasukanWithoutKetua}" id="line" class="tablecloth" pagesize="10">
                            <display:column title="Bil">
                                ${line_rowNum}
                            </display:column>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noKp" title="No. Pengenalan" />
                            <display:column title="Peranan" property="kodPerananOperasi.nama"/>
                            <display:column property="kadKuasa" title="Kad Kuasa" />
                            <display:column property="tempatKerja" title="Jawatan" />
                        </display:table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>

            </div>
        </fieldset>
    </div>
    <p><label>&nbsp;</label>
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </p>
</s:form>

