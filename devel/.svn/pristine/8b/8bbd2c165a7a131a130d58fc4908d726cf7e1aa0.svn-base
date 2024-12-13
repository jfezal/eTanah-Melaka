<%-- 
    Document   : tandatangan
    Created on : Dec 6, 2012, 4:11:17 PM
    Author     : muhammad.amin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.consent.TandatanganActionBean">
    <s:messages/>
    <s:errors/>
    <c:choose>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMJTL' || actionBean.permohonan.kodUrusan.kod eq 'RMJTL'}">
            <c:if test="${actionBean.stageId eq 'Stage1'}">
                <c:if test="${fn:length(actionBean.permohonan.senaraiRujukanLuar) == 0}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend></legend>
                            <br/><br/>
                            <p align="center"><font size="4"> Sila semak maklumat jabatan teknikal terlebih dahulu.</font></p>
                            <br/><br/><br/><br/>
                        </fieldset>
                    </div>
                </c:if>
                <c:if test="${fn:length(actionBean.permohonan.senaraiRujukanLuar) > 0}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Tandatangan Dokumen Manual </legend>
                        <p>
                                <label><font color="red">*</font>
                                    Ditandatangan Oleh  : 
                                </label>
                                <s:select name="mohonTandatanganDokumen.diTandatangan" id="namapguna">
                                     <s:option label="Sila Pilih" value=""/>                          
                                     <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                              
                                        <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                    </c:forEach>
                                </s:select>
                            </p>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMJTL'}">
                                <p>
                                    <label><font color="red">*</font>Tarikh Akhir Penyedian Ulasan oleh Jabatan Teknikal :</label>
                                        <s:text name="tarikhTamat" id="datepicker" class="datepicker"/>
                                </p>
                            </c:if>
                            <br/>

                            <p align="center">
                                <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                            </p>
                            <br/>
                        </fieldset>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${actionBean.stageId eq 'Stage6' || actionBean.stageId eq 'Stage5'}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Tandatangan Dokumen Manual</legend>
                        <p>
                            <label><font color="red">*</font>
                                Ditandatangan Oleh : 
                            </label>
                            <s:select name="mohonTandatanganDokumen.diTandatangan" id="namapguna">
                                 <s:option label="Sila Pilih" value=""/>                          
                                 <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                              
                                    <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                        <br/>
                        <p align="center">
                            <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                        </p>
                        <br/>
                    </fieldset>
                </div>
            </c:if>

            <c:if test="${actionBean.stageId eq 'Stage8' || actionBean.stageId eq 'Stage7'}">
                <c:if test="${actionBean.permohonan.keputusan.kod ne 'L'}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend></legend>
                            <br/><br/>
                            <p align="center"><font size="4"> Maklumat ini hanya diperlukan untuk permohonan yang LULUS sahaja.</font></p>
                            <br/><br/><br/><br/>
                        </fieldset>
                    </div>
                </c:if>
                <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <legend>
                                Tandatangan Dokumen</legend>

                            <p>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMJTL'}">
                                    <label>
                                        Nombor Sijil : 
                                    </label>
                                    ${actionBean.permohonanKertas.nomborRujukan}&nbsp;
                                </c:if>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RMJTL'}">
                                    <label>
                                        <font color="red">*</font>Nombor Sijil : 
                                    </label>
                                    <s:text name="permohonanKertas.nomborRujukan" size="50" maxlength="50"/>
                                </c:if>
                            </p>
                            <c:if test="${actionBean.permohonanKertas.nomborRujukan ne null}">
                                <p>
                                    <label>
                                        <font color="red">*</font>Tarikh Tandatangan Sijil : 
                                    </label>
                                    <s:text name="tarikhTandatangan" id="datepicker" class="datepicker"/>
                                </p>
                            </c:if>
                            <br/>
                            <p align="center">
                                <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                            </p>

                            <br/>
                        </fieldset>
                    </div>
                </c:if>
            </c:if>
        </c:when>
        <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PMMK1' || actionBean.permohonan.kodUrusan.kod eq 'PMMK2'}">
            <c:if test="${fn:length(actionBean.permohonan.senaraiRujukanLuar) == 0}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend></legend>
                        <br/><br/>
                        <p align="center"><font size="4"> Sila semak maklumat jabatan teknikal terlebih dahulu.</font></p>
                        <br/><br/><br/><br/>
                    </fieldset>
                </div>
            </c:if>
            <c:if test="${fn:length(actionBean.permohonan.senaraiRujukanLuar) > 0}">

                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Tandatangan Dokumen Manual</legend>
                        <p>
                            <label><font color="red">*</font>
                                Ditandatangan Oleh : 
                            </label>
                          
                            <s:select name="mohonTandatanganDokumen.diTandatangan" id="namapguna">
                                 <s:option label="Sila Pilih" value=""/>                          
                                 <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                              
                                    <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
                        </p>
                        <br/>
                        <p align="center">
                            <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                        </p>
                        <br/>
                    </fieldset>
                </div>
            </c:if>
        </c:when>
        <c:otherwise>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Tandatangan Dokumen Manual</legend>
                    <p>
                        <label><font color="red">*</font>
                            Ditandatangan Oleh : 
                        </label>
                         <s:select name="mohonTandatanganDokumen.diTandatangan" id="namapguna">
                             <s:option label="Sila Pilih" value=""/>                          
                             <c:forEach items="${actionBean.penggunaPerananList}" var="i" >                              
                                <s:option value="${i.idPengguna}" >${i.jawatan} - ${i.nama}</s:option>
                            </c:forEach>
                        </s:select>
                    </p>
                    <br/>
                    <p align="center">
                        <s:button name="simpan" class="btn" value="Simpan" onclick="doSubmit(this.form,this.name,'page_div')"/>
                    </p>
                    <br/>
                </fieldset>
            </div>
        </c:otherwise>
    </c:choose>
</s:form>