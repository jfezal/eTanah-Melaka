<%--
    Document   : sijilPengecualianUkur
    Created on : May 31, 2011, 11:10:49 AM
    Author     : ctzainal
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/src/main/webapp/pub/js/datetimepicker.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/datetimepicker.js"></script>

<script type="text/javascript">
    
    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }

    function validateForm(){

        if($('#ulasanSijilPU').val() == ''){
            alert("Sila masukkan tujuan");
            return false;
        }
        return true;
    }
</script>

<s:form beanclass="etanah.view.penguatkuasaan.SijilPengecualianUkurActionBean">
    <s:messages/>
    <s:errors/>
    <%@page contentType="text/html" import="java.util.*" %>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <br>

            <p>
                <label>Permohonan Ukur No. :</label>
                <c:if test = "${actionBean.permohonanUkur.noPermohonanUkur ne null}">
                    ${actionBean.permohonanUkur.noPermohonanUkur} &nbsp;&nbsp;
                    <s:hidden name="permohonanUkur.idMohonUkur"/>
                </c:if>
                <c:if test = "${actionBean.permohonanUkur.noPermohonanUkur eq null}">
                    -
                </c:if>                
            </p>
            <p>
                <label>ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}&nbsp;
            </p>

            <p>
                <label>Mukim/Pekan/Bandar : </label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>

            <p>
                <label>Daerah : </label>
                ${actionBean.hakmilik.daerah.nama}&nbsp;
            </p>

            <p>
                <label>No Lot : </label>
                ${actionBean.hakmilik.noLot}&nbsp;
            </p>

            <p>
                <label>Tujuan Kegunaan Tanah : </label>
                ${actionBean.hakmilik.kegunaanTanah.nama}&nbsp;
            </p>

            <p>
                <label><em>*</em>Tujuan :</label>
                <s:textarea name="ulasan" id ="ulasanSijilPU" rows="5" cols="50" class="normal_text" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" />&nbsp;
            </p>
            
            <c:choose>
                <c:when test="${actionBean.pguna.kodCawangan.kod ne '00'}">
                    <p align="center">
                        <s:button name="simpan" id="save"  value="Simpan" class="btn"   onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" />
                    </p>
                </c:when>
                <c:otherwise>
                    <p>
                        <label><em>*</em>Tarikh Perakuan : </label>
                        <s:text name="tarikhPerakuan" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" size="12"/>
                    </p>
                    <p>
                        <label><em>*</em>No. Fail Pejabat Tanah dan Galian : </label>
                        <s:text name="failPTG" id="failPTG" size="30" class="normal_text"/>
                    </p>
                    <p align="center">
                        <s:button name="simpanMohonUkur" id="save"  value="Simpan" class="btn"   onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" />
                    </p>
                </c:otherwise>
            </c:choose>
        </fieldset>
    </div>
</s:form>