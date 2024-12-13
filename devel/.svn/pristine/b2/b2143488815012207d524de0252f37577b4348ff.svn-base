<%--
    Document   : surat_kelulusan_PPRU
    Created on : Oct 18, 2011, 10:12:42 PM
    Author     : Shazwan
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
<s:form  beanclass="etanah.view.stripes.pelupusan.KeputusanPermohonanPPRUActionBean">
    <s:messages/>

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

<c:if test="${view eq false}">
    

    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                <legend>Penyediaan Surat Kelulusan</legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                <legend>Penyediaan Surat Keputusan</legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                <c:if test="${actionBean.stageId eq '27SedSrtLulus' or actionBean.stageId eq '28SmkSrtLulus' or actionBean.stageId eq '29TdtgnSrtLulus'}">
                    <p>
                        <label>Keputusan  :</label>
                        ${actionBean.permohonan.keputusan.nama}<br>
                    </p>
                </c:if>                
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                    <p>
                        <label>Keputusan  :</label>
                        ${actionBean.permohonan.keputusan.nama}<br>
                    </p>              
            </c:if>
            <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">                    
                    <label><font color="red">*</font>Bayaran (RM) :</label>
                    <s:text name="bayaran" formatPattern="#,###,##0.00" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' and actionBean.kodkpsn eq 'L'}">
                    <label><font color="red">*</font>Bayaran (RM) :</label>
                    <s:text name="bayaran" formatPattern="#,###,##0.00" onkeyup="validateNumber(this,this.value);"/> <%--${actionBean.permohonantuntutkos.bayaran}--%>
                </c:if>
            </p>
<!--            <p>
                <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                <s:text name="tarikhMulaLesen" value="${actionBean.tarikhMulaLesen}" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>-->
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' and actionBean.kodkpsn eq 'L'}">                    
                    <label><font color="red">*</font>Keluasan Diluluskan :</label>
                    <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                    <s:select name="kodU" id="koduom">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="M">Meter Persegi</s:option>
                        <s:option value="MP">Meter Padu</s:option>
                   </s:select>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPTR'}">
                    <label>Keluasan Diluluskan:</label>
                    <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000" size="20"/>
                   <s:select name="kodU" id="koduom">
                        <s:option value="">Sila Pilih</s:option>
                        <s:option value="M">Meter Persegi</s:option>
                        <s:option value="H">Hektar</s:option>
                   </s:select>
                </c:if>
                 
            </p>
            <p>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU' and actionBean.kodkpsn eq 'L'}">
                    <label>Tempoh :</label>
                    <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                    Tahun
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' and actionBean.kodkpsn eq 'L'}">
                    <label><font color="red">*</font>Tempoh :</label>
                    <s:text name="hakmilikPermohonan.tempohPajakan" size="3" onkeyup="validateNumber(this,this.value);"/>
                    Tahun
                </c:if>
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                <c:if test="${actionBean.stageId eq '30SedSrtTlk' or actionBean.stageId eq '31SmkSrtTlk' or actionBean.stageId eq '32TdtgnSrtTlk'}">
                    <p>
                        <label>Keputusan  :</label>
                        ${actionBean.permohonan.keputusan.nama}<br><br>
                    </p>
                </c:if>                
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU' and actionBean.stageId eq '27SedSrtLulus'}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanKelulusan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                </p>
            </c:if>
            </c:if>
        </fieldset>
    </div>
    </c:if>
    <c:if test="${view eq true}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Penyediaan Surat Kelulusan</legend>
            </fieldset>
        </div>
         <p>
            <label>Keputusan  :</label>
            ${actionBean.permohonan.keputusan.nama}<br>
        </p>
        <c:if test="${actionBean.permohonan.keputusan.kod eq 'L'}">
            <p>
                <label>Bayaran (RM) :</label>
                ${actionBean.bayaran}${actionBean.tarikhMulaLesen}
            </p>
            <p>             
                    <label>Keluasan Diluluskan :</label>
                    ${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.luasLulusUom.nama} 
            </p>
            <p>
                <label>Tempoh :</label>
                    ${actionBean.hakmilikPermohonan.tempohPajakan} Tahun
            </p>
        </c:if>
            
    </c:if>
</s:form>

