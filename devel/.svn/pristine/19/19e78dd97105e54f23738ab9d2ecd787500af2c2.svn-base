<%-- 
    Document   : lesen_terdahulu.jsp
    Created on : Oct 3, 2011, 3:50 PM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    $(document).ready( function() {
        //ADD CODES HERE
    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.LesenTerdahuluMPJLTActionBean">
    <s:messages/>
    <s:hidden name="idMohonDulu" value="${actionBean.idMohonDulu}"/>
    <fieldset class="aras1">

        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MPJLT'}">
        <legend>Maklumat Lesen Pajakan Lombong/Lesen Lombong Tuan Punya</legend>
        </c:if>
<!--        End of addition-->
        <c:if test="${!actionBean.viewOnly}">
            <div align="center">
                <table width="70%" border="0">       
                    <tr>
                        <td colspan="4">&nbsp;</td>             
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLT'}">
                                 <font color="red" style="text-align: right">*</font>
                            </c:if>
                           No. lesen :
                        </td>
                        <td colspan="2">
                            <s:text name="noLesen" id="kp" size="40"/> 
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.cari}">
                        <td colspan="2">
                            <s:button name="cariLesen" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">
                            <display:table class="tablecloth" name="${actionBean.listLesen}" pagesize="5" cellpadding="0" cellspacing="0"
                                           requestURI="/pelupusan/lesen_terdahulu"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="No. Lesen" sortable="true" property="noPermit"/>
                                <display:column title="No.Siri">${actionBean.permitSahLakuAsal.noSiri}</display:column>
                                <display:column title="Tarikh Dikeluarkan"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                                <display:column title="Tarikh Mula"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></display:column>
                                <display:column title="Tarikh Tamat"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></display:column>
                                <display:column title="Mineral" sortable="true" property="permohonan.catatan"/>
                                <display:column title="Tempoh Pajakan" sortable="true" value="${actionBean.hakmilikPermohonan.tempohPajakan}"/>
                                <display:column title="Jenis Hakmilik" sortable="true" value=""/>
                            </display:table>
                        </td>
                    </tr>
                </table>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLT'}">
                <!--fieldset class="aras1">
                    <legend>Perihal Mengenai Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/pelupusan/lesen_terdahulu">
                            <display:column title="Daerah">${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</display:column>
                            <display:column title="Bandar/Pekan/Mukim">${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}</display:column>
                            <display:column title="Lokasi" property="lokasi"></display:column>
                            <display:column title="Tempat">${actionBean.hakmilikPermohonan.lot.nama} ${actionBean.hakmilikPermohonan.noLot}</display:column>
                            <display:column title="Luas Tanah untuk Diduduki">${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</display:column>
                            <display:column title="No Helaian Peta" property="keteranganKadarUkur"></display:column>
                        <display:column title="Penandaan Blok" property="noUnitPetak"></display:column>
                        </display:table>

                    </div>

                </fieldset-->

                <fieldset class="aras1">
                    <legend>Maklumat Pemegang Lesen</legend>

                    <div class="content" align="center">

                        <display:table name="${actionBean.pihak}" id="line2" class="tablecloth" requestURI="${pageContext.request.contextPath}/pelupusan/lesen_terdahulu">

                            <display:column  title="Nama">${actionBean.pihak.nama}</display:column>
                            <display:column  title="Jenis Pengenalan" >${actionBean.pihak.jenisPengenalan.nama}</display:column>
                            <display:column  title="Nombor Pengenalan">${actionBean.pihak.noPengenalan}</display:column>
                            <display:column title="Alamat" class="alamat">
                                ${line2.suratAlamat1}<c:if test="${line2.suratAlamat2 ne null}">,</c:if>
                                ${line2.suratAlamat2}<c:if test="${line2.suratAlamat3 ne null}">,</c:if>
                                ${line2.suratAlamat3}<c:if test="${line2.suratAlamat4 ne null}">,</c:if>
                                ${line2.suratAlamat4}<c:if test="${line2.suratPoskod ne null}">,</c:if>
                                ${line2.suratPoskod}<c:if test="${line2.suratNegeri.kod ne null}">,</c:if>
                                ${line2.suratNegeri.nama}
                            </display:column>
                            <display:column  title="Poskod">${actionBean.pihak.suratPoskod}</display:column>
                            <display:column  title="Negeri" >${actionBean.pihak.suratNegeri.nama}</display:column>

                        </display:table>
                    </div>

                </fieldset>
                </c:if>
                <c:if test="${actionBean.simpan}">
                        <center>
                            <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                      </center>
                </c:if>
            </div>
        </c:if>
        <c:if test="${actionBean.viewOnly}">
            <div align="center">
                <table width="50%" border="0">    
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td colspan="2">
                            <display:table class="tablecloth" name="${actionBean.listLesen}" pagesize="5" cellpadding="0" cellspacing="0"
                                           requestURI="/pelupusan/lesen_terdahulu"  id="line">
                                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                                <display:column title="No. Lesen" sortable="true" property="noPermit"/>
                                <display:column title="No.Siri">${actionBean.permitSahLakuAsal.noSiri}</display:column>
                                <display:column title="Tarikh Dikeluarkan"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                                <display:column title="Tarikh Mula"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                                <display:column title="Tarikh Tamat"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                                <display:column title="Mineral" sortable="true" property="permohonan.catatan"/>
                            </display:table>
                        </td>
                    </tr>
                </table>
            </div>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'MPJLT'}">
            <!--fieldset class="aras1">
                <legend>Perihal Mengenai Tanah</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="${pageContext.request.contextPath}/pelupusan/lesen_terdahulu">
                        <display:column title="Daerah">${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama}</display:column>
                        <display:column title="Bandar/Pekan/Mukim">${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}</display:column>
                        <display:column title="Lokasi" property="lokasi"></display:column>
                        <display:column title="Tempat">${actionBean.hakmilikPermohonan.lot.nama} ${actionBean.hakmilikPermohonan.noLot}</display:column>
                        <display:column title="Luas Tanah untuk Diduduki">${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</display:column>
                        <display:column title="No Helaian Peta" property="keteranganKadarUkur"></display:column>
                        <display:column title="Penandaan Blok" property="noUnitPetak"></display:column>
                    </display:table>

                </div>

            </fieldset-->

            <fieldset class="aras1">
                <legend>Maklumat Pemegang Lesen</legend>

                <div class="content" align="center">

                    <display:table name="${actionBean.pihak}" id="line2" class="tablecloth" requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_lesen">

                        <display:column  title="Nama">${actionBean.pihak.nama}</display:column>
                        <display:column  title="Jenis Pengenalan" >${actionBean.pihak.jenisPengenalan.nama}</display:column>
                        <display:column  title="Nombor Pengenalan">${actionBean.pihak.noPengenalan}</display:column>
                        <display:column title="Alamat" class="alamat">
                            ${line2.suratAlamat1}<c:if test="${line2.suratAlamat2 ne null}">,</c:if>
                            ${line2.suratAlamat2}<c:if test="${line2.suratAlamat3 ne null}">,</c:if>
                            ${line2.suratAlamat3}<c:if test="${line2.suratAlamat4 ne null}">,</c:if>
                            ${line2.suratAlamat4}<c:if test="${line2.suratPoskod ne null}">,</c:if>
                            ${line2.suratPoskod}<c:if test="${line2.suratNegeri.kod ne null}">,</c:if>
                            ${line2.suratNegeri.nama}
                        </display:column>
                        <display:column  title="Poskod">${actionBean.pihak.suratPoskod}</display:column>
                        <display:column  title="Negeri" >${actionBean.pihak.suratNegeri.nama}</display:column>

                    </display:table>
                </div>

            </fieldset>
            </c:if>
        </c:if>
    </fieldset>
</s:form>