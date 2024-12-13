<%-- 
    Document   : rekod_keputusan_jktd
    Created on : Apr 25, 2011, 5:53:58 PM
    Author     : Rohans
--%>


<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanJKTDActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content">
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'RLPS'}">
                    <p>
                        <label><font color="red" size="4">*</font>No. Kertas Mesyuarat :</label>
                        <s:text name="nomborRujukan" size="30" id="nomborRujukan"/>
                        <%--<s:text name="permohonanKertas.nomborRujukan" size="30" id="nomborRujukan"/>--%>
                    </p>

                    <p></p>

                    <p>
                        <label>Tarikh Mesyuarat :</label>
                        <s:text name="permohonanKertas.tarikhSidang" size="30" id="tarikhMesyuarat" class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>

                    <p></p>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">
                        <p>
                        <label><font color="red" size="4">*</font>Keputusan Mesyuarat :</label>
                        <%--<s:hidden name="fasaPermohonan.keputusan.nama"/>--%>
                        <s:select name="keputusan" id="keputusan">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:option value="T"> Tolak </s:option>
                            <s:option value="L"> Lulus </s:option>
                        </s:select>
                    </p>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPS'}">
                        <p>
                        <label><font color="red" size="4">*</font>Keputusan Mesyuarat :</label>
                        <%--<s:hidden name="fasaPermohonan.keputusan.nama"/>--%>
                        <s:select name="keputusan" id="keputusan">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:option value="T"> Tolak </s:option>
                            <s:option value="L"> Lulus </s:option>
                            <s:option value="TQ"> Tangguh </s:option>
                            <s:option value="TX"> Tiada Keputusan </s:option>
                        </s:select>
                    </p>
                    </c:if>
                    

                    <p></p>

                    <p>
                        <label>Catatan :</label>
                        <s:textarea rows="7" cols="60" name="ulasan" id="ulasan"/>
                        <%--<s:textarea rows="7" cols="60" name="fasaPermohonan.ulasan" id="ulasan" ></s:textarea>--%>
                    </p><br>

                    <p></p>

                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD'}">
                    <c:if test="${actionBean.showK}">
                        <p>
                            <label>Keputusan :</label>
                            <s:select name="keputusan" id="keputusan">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="T"> Tolak </s:option>
                                <s:option value="L"> Lulus </s:option>
                            </s:select>
                        </p>
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea rows="7" cols="60" name="ulasan" id="ulasan"/>
                        </p>
                        <c:if test="${actionBean.stageId eq '16Kptsn'}">
                        <p align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.stageId ne '16Kptsn'}">
                    <c:if test="${actionBean.viewK}">
                        <p>
                            <label>Keputusan Mesyuarat :</label>
                            ${actionBean.permohonan.keputusan.nama}
                        </p>
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea rows="7" cols="60" name="ulasan" id="ulasan" readonly="true"/>
                        </p>

                    </c:if>
                    <c:if test="${actionBean.showA}">
                        <p>
                            <label>Keputusan Mesyuarat :</label>
                            ${actionBean.permohonan.keputusan.nama}
                        </p>
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea rows="7" cols="60" name="ulasan" id="ulasan" readonly="false"/>
                        </p>
                        <p>
                            <label>Kertas Mesyuarat No :</label>
                            <s:text name="nomborRujukan" size="30" id="nomborRujukan"/>
                        </p>
                        <p>
                            <label>Tarikh Mesyuarat :</label>
                            <s:text name="permohonanKertas.tarikhSidang" size="30" id="tarikhMesyuarat" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p>
                        <p align="center">
                            <s:button name="simpanKertas" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                    </c:if>
                </c:if> 
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' and actionBean.permohonan.cawangan.kod ne '04'}">
                    
                    <p>
                        <label>Keputusan :</label>
                        ${actionBean.fasaPermohonan.keputusan.nama}
                    </p>
                    <p>
                        <label>Ulasan :</label>
                        ${actionBean.fasaPermohonan.ulasan}
                    </p>
                </c:if> 
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' and actionBean.permohonan.cawangan.kod eq '04'}">
                    <c:if test="${actionBean.showK}">
                        <p>
                            <label>Keputusan Mesyuarat :</label>
                            <s:select name="kpsn" id="kpsn">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="T"> Tolak </s:option>
                                <s:option value="L"> Lulus </s:option>
                                <s:option value="TQ"> Tangguh </s:option>
                                <s:option value="TX"> Tiada Keputusan </s:option>
                            </s:select>
                        </p>
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea rows="7" cols="60" name="ulasan" id="ulasan"/>
                        </p>
                        <c:if test="${actionBean.stageId eq '09Kptsn'}">
                        <p align="center">
                            <s:button name="simpanKeputusan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.stageId ne '09Kptsn'}">
                    <c:if test="${actionBean.viewK}">
                        <p>
                            <label>Keputusan Mesyuarat :</label>
                            ${actionBean.permohonan.keputusan.nama}
                        </p>
                        <p>
                            <label>Ulasan :</label>
                            ${actionBean.permohonan.ulasan}             
                        </p>
                        <p>
                            <label>Kertas Mesyuarat No :</label>
                            ${actionBean.nomborRujukan}                           
                        </p>
                        <p>
                            <label>Tarikh Mesyuarat :</label>
                            <fmt:formatDate value="${actionBean.permohonanKertas.tarikhSidang}" pattern="dd/MM/yyyy" var="trh_msyrt"></fmt:formatDate>
                            ${trh_msyrt}
                        </p>

                    </c:if>
                    <c:if test="${actionBean.showA}">
                        <p>
                            <label>Keputusan Mesyuarat :</label>
                            ${actionBean.permohonan.keputusan.nama}
                        </p>
                        <p>
                            <label>Ulasan :</label>
                            <s:textarea rows="7" cols="60" name="ulasan" id="ulasan" readonly="false"/>
                        </p>
                        <p>
                            <label>Kertas Mesyuarat No :</label>
                            <s:text name="nomborRujukan" size="30" id="nomborRujukan"/>
                        </p>
                        <p>
                            <label>Tarikh Mesyuarat :</label>
                            <s:text name="permohonanKertas.tarikhSidang" size="30" id="tarikhMesyuarat" class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p>
                        
                            <p align="center">
                                <s:button name="simpanKertas" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                            </p>
                        
                    </c:if>
                    </c:if>
                </c:if>



            </div>
        </fieldset>
    </div>
</s:form>
