<%--
    Document   : sijil_pengecualian_ukur_priz
    Created on : Sept 14, 2012, 10:43:49 AM
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

<s:form beanclass="etanah.view.stripes.pelupusan.SijilPengecualianUkurPRIZActionBean">
    <s:messages/>
    <s:errors/>
    <%@page contentType="text/html" import="java.util.*" %>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Sijil Pengecualian Bayaran Ukur</legend>
            <br>

            <p>
                <label>ID Permohonan :</label>
                ${actionBean.permohonan.idPermohonan}
            </p>

            <p>
                <label>Mukim/Pekan/Bandar : </label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}
            </p>

            <p>
                <label>Daerah : </label>
                ${actionBean.hakmilik.daerah.nama}
            </p>

            <p>
                <label>No Lot : </label>
                ${actionBean.hakmilik.noLot}
            </p>

            <p>
                <label>Tujuan Kegunaan Tanah : </label>
                ${actionBean.hakmilik.kegunaanTanah.nama}
            </p>

            <p>
                <label><em>*</em>Tujuan : </label>
                <s:textarea name="ulasan" id ="ulasanSijilPU" rows="5" cols="50" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" />&nbsp;

            </p>


            <p align="center">
                <s:button name="simpan" id="save"  value="Simpan" class="btn"   onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');" />
            </p>

        </fieldset>
    </div>
</s:form>