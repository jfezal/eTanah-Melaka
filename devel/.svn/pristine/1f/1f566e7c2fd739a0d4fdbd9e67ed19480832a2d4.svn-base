<%-- 
    Document   : maklumat_pemohon_PNSS
    Created on : Sep 13, 2012, 6:07:25 PM
    Author     : murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:form beanclass="etanah.view.strata.KemasukanPemohonActionBean" name="form1">
    <s:messages/>
    <s:errors/>    
    <div class="subtitle">
        <fieldset class="aras1">           
            <legend>Maklumat Pemohon</legend>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PNSS'}">
                <p>
                    <label>Nama :</label>
                    ${actionBean.nama} &nbsp;
                </p>           

                <c:if test="${actionBean.penyerahAdalahPemohon}">
                    <c:if test="${actionBean.permohonan.kodPenyerah ne null}">
                        <p>
                            <label>Jenis Penyerah :</label>
                            ${actionBean.permohonan.kodPenyerah.nama}&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.idPenyerah ne null}">
                        <p>
                            <label>No.Rujukan Penyerah :</label>
                            ${actionBean.permohonan.idPenyerah}&nbsp;
                        </p>
                    </c:if>
                </c:if> 
                <c:if test="${!actionBean.penyerahAdalahPemohon}">
                    <p>
                        <label>Jenis Pengenalan :</label>
                        ${actionBean.jenisPengenalan2} &nbsp;
                    </p>
                    <p>
                        <label>No. Pengenalan :</label>
                        ${actionBean.nomborPengenalan} &nbsp;
                    </p>
                </c:if>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.alamat1} &nbsp;
                </p>
                <c:if test="${actionBean.alamat2 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.alamat2} &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.alamat3 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.alamat3} &nbsp;
                    </p>
                </c:if>
                <p>
                    <label> Bandar :</label>
                    ${actionBean.alamat4} &nbsp;
                </p>
                <p>
                    <label>Poskod :</label>
                    <c:if test="${actionBean.poskod ne null}">
                        ${actionBean.poskod}
                    </c:if>&nbsp;
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <font style="text-transform: uppercase">${actionBean.pihak.negeri.nama} &nbsp;</font>
                </p>
                <br>
                <p>
                    <label for="alamat">Alamat Surat-Menyurat:</label>
                    ${actionBean.suratAlamat1}
                </p>
                <c:if test="${actionBean.suratAlamat2 ne null}"> 
                    <p>
                        <label> &nbsp;</label> 
                        ${actionBean.suratAlamat2}
                    </p>
                </c:if>

                <c:if test="${actionBean.suratAlamat3 ne null}"> 
                    <p>
                        <label> &nbsp;</label> 
                        ${actionBean.suratAlamat3}
                    </p>
                </c:if>
                <p>
                    <label>Bandar :</label>
                    ${actionBean.suratAlamat4}&nbsp;
                </p>
                <p>
                    <label>Poskod :</label>
                    ${actionBean.suratPoskod}&nbsp;
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    ${actionBean.suratNegeri1}&nbsp;
                </p>
                <br>
                <p>&nbsp;</p> 
            </c:if>

            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PNSS'}"> 
                <p>
                    <label>Nama :</label>
                    ${actionBean.badananPengurusan.pihak.nama} &nbsp;
                </p>
                <p>
                    <label>Jenis Pengenalan :</label>
                    ${actionBean.badananPengurusan.pihak.jenisPengenalan.nama} &nbsp;
                </p>
                <p>
                    <label>No.Pengenalan :</label>
                    ${actionBean.badananPengurusan.pihak.noPengenalan}&nbsp;
                </p>
                <p>
                    <label>Alamat :</label>
                    ${actionBean.badananPengurusan.pihak.alamat1} &nbsp;
                </p>
                <c:if test="${actionBean.badananPengurusan.pihak.alamat2 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.badananPengurusan.pihak.alamat2} &nbsp;
                    </p>
                </c:if>
                <c:if test="${actionBean.badananPengurusan.pihak.alamat3 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.badananPengurusan.pihak.alamat3} &nbsp;
                    </p>
                </c:if>
                <p>
                    <label> Bandar :</label>
                    ${actionBean.badananPengurusan.pihak.alamat4} &nbsp;
                </p>
                <p>
                    <label>Poskod :</label>
                    <c:if test="${actionBean.badananPengurusan.pihak.poskod ne null}">
                        ${actionBean.badananPengurusan.pihak.poskod}
                    </c:if>&nbsp;
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <font style="text-transform: uppercase">${actionBean.badananPengurusan.pihak.negeri.nama} &nbsp;</font>
                </p>
                <br />
                <p>
                    <label for="alamat">Alamat Surat-Menyurat:</label>
                    ${actionBean.badananPengurusan.pihak.suratAlamat1}
                </p>
                <c:if test="${actionBean.badananPengurusan.pihak.suratAlamat2 ne null}"> 
                    <p>
                        <label> &nbsp;</label> 
                        ${actionBean.badananPengurusan.pihak.suratAlamat2}
                    </p>
                </c:if>

                <c:if test="${actionBean.badananPengurusan.pihak.suratAlamat3 ne null}"> 
                    <p>
                        <label> &nbsp;</label> 
                        ${actionBean.badananPengurusan.pihak.suratAlamat3}
                    </p>
                </c:if>
                <p>
                    <label>Bandar :</label>
                    ${actionBean.badananPengurusan.pihak.suratAlamat4}&nbsp;
                </p>
                <p>
                    <label>Poskod :</label>
                    ${actionBean.badananPengurusan.pihak.suratPoskod}&nbsp;
                </p>
                <p>
                    <label for="Negeri">Negeri :</label>
                    <font style="text-transform: uppercase">${actionBean.badananPengurusan.pihak.suratNegeri.nama}&nbsp;</font>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>
