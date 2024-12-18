<%-- 
   
    Author     : afham
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<s:form beanclass="etanah.view.stripes.pelupusan.CarianPermitActionBean">
   <s:messages/>
   <s:errors/>

        <fieldset class="aras1">
            <c:if test="${!status}">
                 
            <legend>Carian Nombor Lesen </legend>

            <br/>
            <br/>
            <table border="0">
                    <tr>
                        <td>No. Lesen</td>
                        <td>:</td>
                        <td><s:text name="noPermit" id="noPermit"/> </td>
                    </tr>

            </table>
                        <center><s:submit name="cariLesen" id="cari" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> </center>
            </fieldset>
            </c:if>
            <c:if test="${status}">
                 
                <fieldset class="aras1">
                <legend>Maklumat Lesen</legend>

                 <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.permit}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_lesen">

                    <display:column title="ID Permohonan">${actionBean.permit.permohonan.idPermohonan}</display:column>
                    <display:column title="No.Lesen">${actionBean.permit.noPermit}</display:column>
                    
                    <display:column title="No.Siri">${actionBean.permitSahLakuAsal.noSiri}</display:column>
                     <display:column title="Tarikh Dikeluarkan"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                    <display:column title="Tarikh Mula"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                    <display:column title="Tarikh Tamat"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                    <%--<display:column title="Jenis Permit" property=""/>--%>
                    
                   <%-- <display:column title="Jenis Permit" property=""/>--%>
                  <%--  <display:column title="Tempoh Sah Laku">${actionBean.tempohSahLaku}</display:column>
--%>
                    <display:column title="Peruntukan Tambahan" >${actionBean.permit.peruntukanTambahan}</display:column>

                  <%--  <display:column title="Tahun Akhir">${actionBean.tahunAkhir}</display:column>--%>
                     <display:column title="Tujuan">${actionBean.permitItem.kodItemPermit.nama}</display:column>


                </display:table>
                 </div>
                </fieldset>
                <fieldset class="aras1">
                    <legend>Perihal Mengenai Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.hakMilikPermohonan}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_lesen">
                            <display:column title="Bandar/Pekan/Mukim">${actionBean.hakMilikPermohonan.bandarPekanMukimBaru.nama}</display:column>
                            <display:column title="Tempat">${actionBean.hakMilikPermohonan.lot.nama} ${actionBean.hakMilikPermohonan.noLot}</display:column>
                            <display:column title="Luas Tanah untuk Diduduki">${actionBean.hakMilikPermohonan.luasTerlibat} ${actionBean.hakMilikPermohonan.kodUnitLuas.nama}</display:column>
                        </display:table>

                    </div>

                </fieldset>
                <fieldset class="aras1">
                    <legend>Maklumat Pihak</legend>

                 <div class="content" align="center">

                <display:table name="${actionBean.pihakList}" id="line2" class="tablecloth" requestURI="${pageContext.request.contextPath}/pelupusan/carian_permit">

                    <display:column  title="Nama">${actionBean.pihakList.nama}</display:column>
                        <display:column  title="Jenis Pengenalan" >${actionBean.pihakList.jenisPengenalan.nama}</display:column>
                        <display:column  title="Nombor Pengenalan">${actionBean.pihakList.noPengenalan}</display:column>
                    <display:column title="Alamat" class="alamat">
                        ${line2.suratAlamat1}<c:if test="${line2.suratAlamat2 ne null}">,</c:if>
                        ${line2.suratAlamat2}<c:if test="${line2.suratAlamat3 ne null}">,</c:if>
                        ${line2.suratAlamat3}<c:if test="${line2.suratAlamat4 ne null}">,</c:if>
                        ${line2.suratAlamat4}<c:if test="${line2.suratPoskod ne null}">,</c:if>
                        ${line2.suratPoskod}<c:if test="${line2.suratNegeri.kod ne null}">,</c:if>
                        ${line2.suratNegeri.nama}
                    </display:column>
                   <display:column  title="Poskod">${actionBean.pihakList.suratPoskod}</display:column>
                        <display:column  title="Negeri" >${actionBean.pihakList.suratNegeri.nama}</display:column>

                </display:table>
                 </div>

                </fieldset>

                <fieldset class="aras1">
                    <legend>Maklumat Pembaharuan Lesen</legend>
                    <div class="content" align="center">
                <%--    ${actionBean.i}--%>
                    <%--<c:if test="${fn:length(actionBean.sahLakuList)> 0}">--%>
                    <c:if test="${fn:length(actionBean.sahLakuList)< 5}">
                                <table align="center" class="tablecloth">
                                    <tr>
                                    <th>ID Permohonan</th>
                                    <th>No. Siri</th>
                                    <th>Tarikh Di Keluarkan</th>
                                    <th>Tarikh Mula</th>
                                    <th>Tarikh Tamat</th>
                                    </tr>

                                     <c:if test="${fn:length(actionBean.sahLakuList) eq 2}" >
                                    <tr>
                                 <td>${actionBean.permitSahLaku1.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku1.noSiri}</td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>


                                    </tr>
                                    </c:if>



                            <c:if test="${fn:length(actionBean.sahLakuList) eq 3}" >
                            <tr>
                                <td>${actionBean.permitSahLaku1.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku1.noSiri}</td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <td>${actionBean.permitSahLaku2.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku2.noSiri}</td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>

                            </tr>
                            </c:if>
                            <c:if test="${fn:length(actionBean.sahLakuList) eq 4}">
                            <tr>
                                <td>${actionBean.permitSahLaku1.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku1.noSiri}</td>
                                 <td><s:format value="${actionBean.permitSahLaku1.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <td>${actionBean.permitSahLaku2.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku2.noSiri}</td>
                               <td><s:format value="${actionBean.permitSahLaku2.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>

                                 <td>${actionBean.permitSahLaku3.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku3.noSiri}</td>
                                 <td><s:format value="${actionBean.permitSahLaku3.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku3.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku3.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>


                            </tr>
                             
                            </c:if>
                           </table>
                                <br/>
                                <br/>
                       
                            </c:if>

<%--</c:if>
--%> <s:submit name="cariSemula" class="btn" value="Cari Semula"/>

                    </div>
                </fieldset>

            </c:if>









</s:form>
