<%--
    Document   : surat_kelulusan_lps
    Created on : May 11, 2010, 7:59:42 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }
</script>
<s:form  beanclass="etanah.view.stripes.pelupusan.KeputusanPermohonanActionBean">
    <s:messages/>
    <s:errors/>
    <%--    <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan</legend>
                <p>
                    <label for="Permohonan">Permohonan :</label>
                    ${actionBean.permohonan.kodUrusan.nama}&nbsp;
                </p>
                <p>
                    <label for="ID Permohonan">ID Permohonan :</label>
                    ${actionBean.permohonan.idPermohonan}&nbsp;
                </p>
            </fieldset >
            <br>
        </div>--%>

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP'}">
                <legend>Penyediaan Surat Kelulusan</legend>
            </c:if>

            <legend>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD' || actionBean.permohonan.kodUrusan.kod eq 'PLPS' || actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">
                    Penyediaan Surat Keputusan
                </c:if>
            </legend>
            <br>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PLPTD' && actionBean.permohonan.kodUrusan.kod ne 'PLPS' && actionBean.permohonan.kodUrusan.kod ne 'RLPS' && actionBean.permohonan.kodUrusan.kod ne 'PTPBP'}">
                <p>
                    <label><font color="red">*</font>Maksud Pendudukan  :</label>
                    <s:select name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p>
                    <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                    <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="40" value="${actionBean.permohonan.catatan}"/>
                </p>
                <p>
                    <c:if test="${actionBean.kodkpsn ne 'T'}">
                        <label><font color="red">*</font>Bayaran (RM) :</label>
                        <s:text name="bayaran" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>

                    </p>

                    <p>
                        <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                        <s:text name="tarikhPermitMula"   value="${actionBean.sahLaku.tarikhPermitMula}"class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>
                </c:if>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPTD'}">


                <c:if test="${actionBean.stageId ne '18SedSrtKptsn'}">
                    <p>
                        <label>Keputusan  :</label>
                        ${actionBean.permohonan.keputusan.nama}
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq '18SedSrtKptsn'}">
                    <p>
                        <label><font color="red">*</font>Maksud Pendudukan  :</label>
                        <s:select name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}" >
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                        </s:select>
                    </p>
                </c:if>


                <c:if test="${actionBean.permohonan.keputusan.kod ne 'T'}">
                    <p>
                        <label><font color="red">*</font>Bayaran (RM) :</label>
                        <s:text name="bayaran" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>

                    </p>

                    <p>
                        <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                        <s:text name="tarikhPermitMula"   value="${actionBean.sahLaku.tarikhPermitMula}"class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>
                    <c:if test="${actionBean.stageId eq '18SedSrtKptsn'}">
                        <p>
                            <label>&nbsp;</label>
                            <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </p>
                    </c:if>
                </c:if>

            </c:if>

            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS'}">

                <c:if test="${actionBean.stageId ne '11SedSrtKptsn'}">
                    <p>
                        <label><font color="red">*</font>Maksud Pendudukan  :</label>
                        ${actionBean.ppi.kodItemPermit.nama}
                    </p>
                    <p>
                        <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                        <s:textarea name="permohonan.catatan" id="catatan"  rows="5" readonly="true"  cols="40" value="${actionBean.permohonan.catatan}"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq '11SedSrtKptsn'}">
                    <p>
                        <label><font color="red">*</font>Maksud Pendudukan :</label>
                        <s:select name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                        <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="40" value="${actionBean.permohonan.catatan}"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.kodkpsn ne 'T'}">
                    <p>
                        <label><font color="red">*</font>Bayaran (RM) :</label>
                        <s:text name="bayaran" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>

                    </p>

                    <p>
                        <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                        <s:text name="tarikhPermitMula"   value="${actionBean.sahLaku.tarikhPermitMula}"class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq '11SedSrtKptsn'}">
                    <p>
                        <label>&nbsp;</label>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' and actionBean.permohonan.cawangan.kod ne '04'}">
                            <s:button name="simpanKelulusanRLPS" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPS' and actionBean.permohonan.cawangan.kod eq '04'}">
                            <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </c:if>
                    </p>
                </c:if>
            </c:if>

            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PLPS'}">

                <c:if test="${actionBean.stageId ne '36SedSrtKptsnLulus' and actionBean.stageId ne '54SedSrtLulus'}">
                    <p>
                        <label><font color="red">*</font>Maksud Pendudukan  :</label>
                        <s:hidden name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}"/>
                        ${actionBean.ppi.kodItemPermit.nama}
                    </p>
                    <p>
                        <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                        <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="40" value="${actionBean.permohonan.catatan}"/><%--readonly="true"--%>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq '36SedSrtKptsnLulus' or actionBean.stageId eq '54SedSrtLulus'}">
                    <p>
                        <label><font color="red">*</font>Maksud Pendudukan  :</label>
                        <s:select name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${actionBean.senaraiKodItemPermit}" label="nama" value="kod" />
                        </s:select>
                    </p>
                    <p>
                        <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                        <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="40" value="${actionBean.permohonan.catatan}"/>
                    </p>
                </c:if>

                <c:if test="${actionBean.permohonan.keputusan.kod ne 'T'}">
                    <p>
                        <label><font color="red">*</font>Bayaran (RM) :</label>
                        <s:text name="bayaran" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>

                    </p>

                    <p>
                        <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                        <s:text name="tarikhPermitMula"   value="${actionBean.sahLaku.tarikhPermitMula}"class="datepicker" formatPattern="dd/MM/yyyy"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId eq '36SedSrtKptsnLulus' or actionBean.stageId eq 'sedia_surat_kelulusan' or actionBean.stageId eq 'semak_surat_kelulusan' or actionBean.stageId eq '54SedSrtLulus'}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTPBP'}">

                <p>
                    <label><font color="red">*</font>Maksud Pendudukan  :</label>
                    ${actionBean.ppi.kodItemPermit.nama}

                    <s:hidden name="kodPermit.kod" value="${actionBean.ppi.kodItemPermit.kod}"/>
                </p>
                <c:if test="${actionBean.stageId eq '32SedSrtLulus'}">
                    <p>
                        <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                        <s:textarea name="permohonan.catatan" id="catatan"  rows="5"  cols="40" value="${actionBean.permohonan.catatan}"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.stageId ne '32SedSrtLulus'}">
                    <p>
                        <label><font color="red"></font>Jika Lain-lain (Sila Nyatakan) :</label>
                        <s:textarea name="permohonan.catatan" id="catatan"  rows="5"  cols="40" value="${actionBean.permohonan.catatan}" readonly="true"/>
                    </p>
                </c:if>
                <c:if test="${actionBean.permohonan.keputusan.kod ne 'T'}">
                    <p>
                        <label><font color="red">*</font>Bayaran (RM) :</label>
                        <s:format formatPattern="#,##.00" value="${actionBean.bayaran}"/> 
                    </p>
                    <c:if test="${actionBean.stageId eq '32SedSrtLulus'}">
                        <p>
                            <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                            <s:text name="tarikhPermitMula"   value="${actionBean.sahLaku.tarikhPermitMula}"class="datepicker" formatPattern="dd/MM/yyyy"/>
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId ne '32SedSrtLulus'}">
                        <p>
                            <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                            <s:text name="tarikhPermitMula"   value="${actionBean.sahLaku.tarikhPermitMula}"class="datepicker" formatPattern="dd/MM/yyyy" disabled="true"/>
                        </p>
                    </c:if>

                </c:if>
                <c:if test="${actionBean.stageId eq '32SedSrtLulus'}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </c:if>
            </c:if>
        </fieldset >
    </div>
</s:form>

