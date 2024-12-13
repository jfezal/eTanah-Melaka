

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<s:form beanclass="etanah.view.stripes.pelupusan.PembaharuanLPS_pejabatTanah_ActionBean">
    <s:messages/>
    
    
        <%--<fieldset class="aras1">
            <c:if test="${!status}">
            <legend>Carian No. Lesen </legend>
            <table border="0">
                    <tr>
                        <td>No.Permit</td>
                        <td>:</td>
                        <td><s:text name="noPermit" id="noPermit"/> </td>
                    </tr>
                   
            </table>
                        <center><s:button name="cariLesen" id="cari" value="Cari" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> </center>

                               
            </fieldset>
            </c:if>--%>
         <%--   <c:if test="${status}">--%>
                <fieldset class="aras1">
                <legend>Maklumat Lesen</legend>

                 <div class="content" align="center">

                <display:table class="tablecloth" name="${actionBean.permitTemp}" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_lesen">

                    <display:column title="ID Permohonan">${actionBean.permitTemp.permohonan.idPermohonan}</display:column>
                    <display:column title="No.Lesen">${actionBean.permitTemp.noPermit}</display:column>
                    <%--<display:column title="Jenis Permit" property=""/>--%>

                    <display:column title="No.Siri">${actionBean.permitSahLakuAsal.noSiri}</display:column>
                    <display:column title="Tarikh Dikeluarkan"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                    <display:column title="Tarikh Mula"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                    <display:column title="Tarikh Tamat"><s:format value="${actionBean.permitSahLakuAsal.tarikhPermit}" formatPattern="dd/MM/yyyy"/></display:column>
                    <display:column title="No Resit Bayaran">${actionBean.noResitBayaran}</display:column>
                    <display:column title="Tarikh Bayaran"><s:format value="${actionBean.tarikhBayaran}" formatPattern="dd/MM/yyyy"/></display:column>
                 <%--   <display:column title="Tempoh Sah Laku">${actionBean.tempohSahLaku}</display:column>--%>

                    <display:column title="Peruntukan Tambahan" >${actionBean.permitTemp.peruntukanTambahan}</display:column>

                   <%-- <display:column title="Tahun Akhir">${actionBean.tahunAkhir}</display:column>--%>
                   <display:column title ="Tujuan">${actionBean.permitItem.kodItemPermit.nama}</display:column>
                    

                </display:table>
                 </div>
                </fieldset>
                <fieldset class="aras1">
                    <legend>Perihal Mengenai Tanah</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.permitTemp}" cellpadding="0" cellspacing="0" id="line"
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

                <display:table name="${actionBean.pihakList}" id="line2" class="tablecloth" requestURI="${pageContext.request.contextPath}/pelupusan/maklumat_lesen">

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
                <%--${actionBean.i}--%>
                <fieldset class="aras1">
                    <legend>Pembaharuan Lesen Pendudukan Sementara</legend>
                    <div class="content" align="center">
                    
                           
                            <c:if test="${fn:length(actionBean.sahLakuList)< 5}">
                                <table align="center" class="tablecloth">
                                    <tr>
                                    <th>ID Permohonan</th>
                                    <th>No. Siri</th>
                                    <th>Tarikh Dikeluarkan</th>
                                    <th>Tarikh Mula</th>
                                    <th>Tarikh Tamat</th>
                                    <th>No Resit Bayaran</th>
                                    <th>Tarikh Bayaran</th>
                                    </tr>
                            
                                 <c:if test="${fn:length(actionBean.sahLakuList) eq 2}" >
                                     <%--ada satu data, baru nak renew--%>
                                <tr>
                                <td>${actionBean.permitSahLaku1.permohonan.idPermohonan}<s:hidden name="idMohon1"/></td>
                                <td><%--<c:if test="${actionBean.permitSahLaku1.noSiri eq null}">--%>
                                    ${actionBean.permitSahLaku1.noSiri}
                                    <%--</c:if>--%>
                                   <%-- <c:if test="${actionBean.permitSahLaku1.noSiri ne null}">
                                        ${actionBean.permitSahLaku1.noSiri}
                                    </c:if>--%>
                                </td>

                                <td><%--<c:if test="${actionBean.permitSahLaku1.tarikhPermit eq null}">
                                    <s:text name="tarikhPermit"  class="datepicker" formatPattern="dd/MM/yyyy" value="--%>
                                    <s:format value="${actionBean.permitSahLaku1.tarikhPermit}" formatPattern="dd/MM/yyyy"/> 
                                    <%--</c:if>
                                     <c:if test="${actionBean.permitSahLaku1.tarikhPermit ne null}">
                                        ${actionBean.permitSahLaku1.tarikhPermit}
                                    </c:if>--%>
                                </td>
                                <td><%--<c:if test="${actionBean.permitSahLaku1.tarikhPermitMula eq null}">--%>
                                    <s:text name="tarikhPermitMula"  class="datepicker" formatPattern="dd/MM/yyyy" value="${actionBean.permitSahLaku1.tarikhPermitMula}"/>
                                    <%--</c:if>--%>
                                 <%--    <c:if test="${actionBean.permitSahLaku1.tarikhPermitMula ne null}">
                                        ${actionBean.permitSahLaku1.tarikhPermitMula}
                                    </c:if>--%>
                                </td>
                                <td><%--<c:if test="${actionBean.permitSahLaku1.tarikhPermitTamat eq null}">--%>
                                    <s:text name="tarikhPermitTamat"  class="datepicker" formatPattern="dd/MM/yyyy" value="${actionBean.permitSahLaku1.tarikhPermitTamat}"/>
                                   <%-- </c:if>--%>
                                  <%--  <c:if test="${actionBean.permitSahLaku1.tarikhPermitTamat ne null}">
                                    
                                    </c:if>--%>

                                </td>
                                <td>
                                    ${actionBean.noResitBayaran1}
                                </td>
                                <td>
                                    <s:format value="${actionBean.tarikhBayaran1}" formatPattern="dd/MM/yyyy"/>
                                </td>

                                 </c:if>
                                                              
                            </tr>
                                   

                            

                            <c:if test="${fn:length(actionBean.sahLakuList) eq 3}" >
     
                            <tr>

                                <td>${actionBean.permitSahLaku1.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku1.noSiri}</td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>
                                <td>${actionBean.noResitBayaran1}</td>
                                <td><s:format value="${actionBean.tarikhBayaran1}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>

                                
                                <td>${actionBean.permitSahLaku2.permohonan.idPermohonan}
                                <s:hidden name="idMohon1"/></td>
                                <%--<c:if test="${actionBean.permitSahLaku2.noSiri eq null}">--%>
                                <td>${actionBean.permitSahLaku2.noSiri}</td>
                                <td> <s:format value="${actionBean.permitSahLaku2.tarikhPermit}" formatPattern="dd/MM/yyyy"/>
                                    <%--</c:if>--%></td>
                                
                                <%--<td><c:if test="${actionBean.permitSahLaku2.tarikhPermit eq null}">
                                        <s:text name="tarikhPermit"  class="datepicker" formatPattern="dd/MM/yyyy" value="
                                       ${actionBean.permitSahLaku2.tarikhPermit}"/>
                                    </c:if></td>--%>
                                <td><%--<c:if test="${actionBean.permitSahLaku2.tarikhPermitMula eq null}">--%>
                                        <s:text name="tarikhPermitMula"  class="datepicker"  formatPattern="dd/MM/yyyy" value="${actionBean.permitSahLaku2.tarikhPermitMula}"/>
                                    <%--</c:if>--%></td>
                                <td><%--<c:if test="${actionBean.permitSahLaku2.tarikhPermitTamat eq null}">--%>
                                        <s:text name="tarikhPermitTamat"  class="datepicker" formatPattern="dd/MM/yyyy" value="${actionBean.permitSahLaku2.tarikhPermitTamat}"/>
                                    <%--</c:if>--%></td>
                                <td>
                                    ${actionBean.noResitBayaran2}
                                </td>
                                <td>
                                    <s:format value="${actionBean.tarikhBayaran2}" formatPattern="dd/MM/yyyy"/>
                                </td>
                            </tr>
                            </c:if>
                            <c:if test="${fn:length(actionBean.sahLakuList) eq 4}">
        
                            <tr>
                                <td>${actionBean.permitSahLaku1.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku1.noSiri}</td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku1.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>
                                <td>${actionBean.noResitBayaran1}</td>
                                <td><s:format value="${actionBean.tarikhBayaran1}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <td>${actionBean.permitSahLaku2.permohonan.idPermohonan}</td>
                                <td>${actionBean.permitSahLaku2.noSiri}</td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermit}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermitMula}" formatPattern="dd/MM/yyyy"/></td>
                                <td><s:format value="${actionBean.permitSahLaku2.tarikhPermitTamat}" formatPattern="dd/MM/yyyy"/></td>
                                <td>${actionBean.noResitBayaran2}</td>
                                <td><s:format value="${actionBean.tarikhBayaran2}" formatPattern="dd/MM/yyyy"/></td>
                            </tr>
                            <tr>
                                <td>${actionBean.permitSahLaku3.permohonan.idPermohonan} <s:hidden name="idMohon1"/></td>
                                <%--<c:if test="${actionBean.permitSahLaku3.noSiri eq null}">--%>
                                <td>${actionBean.permitSahLaku3.noSiri}
                                    <%--</c:if>--%></td>

                                <td><%--<c:if test="${actionBean.permitSahLaku3.tarikhPermit eq null}">--%>
                                        <%--<s:text name="tarikhPermit"  class="datepicker"  formatPattern="dd/MM/yyyy" value="--%>
                                        <s:format value="${actionBean.permitSahLaku3.tarikhPermit}" formatPattern="dd/MM/yyyy"/>
                                    <%--</c:if>--%></td>
                                <td><%--<c:if test="${actionBean.permitSahLaku3.tarikhPermitMula eq null}">--%>
                                    <s:text name="tarikhPermitMula"  class="datepicker" formatPattern="dd/MM/yyyy" value="${actionBean.permitSahLaku3.tarikhPermitMula}"/>
                                    <%--</c:if>--%></td>
                                <td><%--<c:if test="${actionBean.permitSahLaku3.tarikhPermitTamat eq null}">--%>
                                    <s:text name="tarikhPermitTamat"  class="datepicker"  formatPattern="dd/MM/yyyy" value="${actionBean.permitSahLaku3.tarikhPermitTamat}"/>
                                    <%--</c:if>--%></td>
                                    <td>
                                    ${actionBean.noResitBayaran3}
                                </td>
                                <td>
                                    <s:format value="${actionBean.tarikhBayaran3}" formatPattern="dd/MM/yyyy"/>
                                </td>
                            </tr>
                            
                            </c:if>
                             </table>
                                 <s:button name="simpanUpdateLesen" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </c:if>
                            
                            
                        

                    </div>
                </fieldset>
            
                        
           

  

    

</s:form>
