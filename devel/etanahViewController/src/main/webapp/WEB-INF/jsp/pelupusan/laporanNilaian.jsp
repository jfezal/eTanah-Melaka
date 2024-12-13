<%-- 
    Document   : laporanNilaian
    Created on : Jul 12, 2010, 10:10:30 AM
    Author     : sitifariza.hanim
    Modified By : Murali May 19, 2011
    Modified By : Shazwan June 08, 2011
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
    function formatCurrency(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
        cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
            num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }
    function checkIt(evt) {
    evt = (evt) ? evt : window.event
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        status = "This field accepts numbers only."
        return false
    }
    status = ""
    return true
}
    
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.pelupusan.LaporanNilaianActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
           <legend>Laporan Nilaian</legend>
            <br>
            <p>
                <label><font color="red">*</font>Tarikh Nilaian :</label>
                <c:if test="${!actionBean.viewOnly}">
                    <s:text name="tarikhRujukan"  id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                </c:if>
                <c:if test="${actionBean.viewOnly}">
                    <s:format formatPattern="MM/dd/yyyy" value="${actionBean.tarikhRujukan}"/>
                </c:if>
            </p>
            <p>
                <label>No.Lot/Plot :</label>
                ${actionBean.hakmilikPermohonan.noLot}&nbsp;
            </p> 
           <%-- <p>
                <label>Keluasan :</label>
                ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
            </p>--%>
            
            <%--<p>
                <label>Keluasan diluluskan:</label>
                ${actionBean.noPt.luasDilulus} &nbsp; ${actionBean.noPt.kodUOM.nama}
            </p>--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PHLP' && actionBean.permohonan.kodUrusan.kod ne 'PHLA'}  ">
                <p>
                    <label>Keluasan dimohon:</label>
                    ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                </p>
                <p>
                    <label>Keluasan diluluskan:</label>

                    <s:hidden name="noPt.idPt" /><%--
                    <s:text name="noPt.hakmilikPermohonan.id" value="${actionBean.hakmilikPermohonan.id}"/>
                    <s:text name="noPt.luasDilulus"  id="noPt.luasDilulus" />
                      <s:select name="noPt.kodUOM.kod" value="${actionBean.noPt.kodUOM.kod}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:option value="H">Hektar</s:option>
                            <s:option value="M">Meter Persegi</s:option>
                        </s:select>--%>
                    ${actionBean.noPt.luasDilulus} 
                    ${actionBean.noPt.kodUOM.nama}
                </p>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PHLP'}">
                <p>
                    <label>Keluasan dipohon:</label>
                      ${actionBean.hakmilikPermohonan.luasTerlibat}
                      ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                </p>
            </c:if>

            <p>
                <label>Kegunaan Tanah/Tapak :</label>
               <%-- ${actionBean.hakmilikPermohonan.kodKegunaanTanah.kod}  ${actionBean.hakmilikPermohonan.kodKegunaanTanah.nama}&nbsp;--%>
                ${actionBean.kodgunatanah}&nbsp;
            </p>

            <p>
                <label>Daerah :</label>
                ${actionBean.permohonan.cawangan.daerah.nama}
            </p>
            <p>
                <label>Mukim/Pekan/Bandar :</label>
               ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
            </p>
            <p>
                <label>Tempat :</label>
                <c:if test="${!empty actionBean.hakmilikPermohonan.lokasi}">
                    ${actionBean.hakmilikPermohonan.lokasi}
                </c:if>
                <c:if test="${empty actionBean.hakmilikPermohonan.lokasi}">
                    &nbsp;
                </c:if>
            </p>
             <p>
                <label>Nilai Pasaran (RM) :</label>
                <c:if test="${!actionBean.viewOnly}">
                    <s:text name="nilai" formatPattern="#,##0.00" onblur="this.value=formatCurrency(this.value);" onkeypress="return checkIt(event)" id="nilai" value="${actionBean.permohonanRujukanLuar.nilai}"/>
                </c:if>
                <c:if test="${actionBean.viewOnly}">
                    ${actionBean.nilai}
                </c:if>
            </p>
             <br>
                <p>
                    <label>&nbsp;</label>
                  <c:if test="${!actionBean.viewOnly}">  
                    <s:button name="simpan3" id="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                  </c:if>
                </p>
           
        </fieldset>
    </div>
</s:form>