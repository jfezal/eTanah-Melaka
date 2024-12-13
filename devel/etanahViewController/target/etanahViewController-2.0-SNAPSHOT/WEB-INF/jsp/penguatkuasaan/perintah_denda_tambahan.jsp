<%-- 
    Document   : perintah_denda_tambahan
    Created on : Jul 27, 2010, 10:24:16 AM
    Author     : Programmer
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=iso-8859-1" language="java"
         import="java.util.*,java.text.*,java.util.Date" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>


<script language="javascript" type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
    function updateTotal(){
        document.perintah.jumlahDendaTambahan.value = parseFloat(document.perintah.jumlahDendaTambahan.value).toFixed(2);
    }
    function validateForm(){
        if(document.perintah.jumlahDendaTambahan.value=="")
        {
            alert("Sila isikan Jumlah Denda Tambahan terlebih dahulu");
            return false;
        }
        //document.surat.submit();
        return true;
    }
    function test(f) {
        $(f).clearForm();
    }
</script>

<s:form name="perintah" beanclass="etanah.view.penguatkuasaan.PerintahDendaTambahanActionBean">
    <s:messages/>
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <c:if test="${edit}">
                <div class="subtitle">
                    <br>
                    <p>
                        <label> Tarikh Surat : </label>
                        <fmt:formatDate value="${actionBean.aduanTindakan2.tarikhMula}" pattern="dd/MM/yyyy" />

                        <%--<display:column property="aduanTindakan.tarikhMula"  class="cawangan${line_rowNum}" format="{0,date,dd/MM/yyyy,hh:mm aa}"/>--%>
                        <%--<s:text name="aduanTindakan.tarikhMula" formatType="date" formatPattern="dd/MM/yyyy" readonly="true"/>--%>
                    </p>
                    <p>
                        <label> Jumlah Denda (RM):</label>
                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.aduanTindakan2.amaun}"/>
                        <%--${actionBean.aduanTindakan2.amaun}--%>
                    </p>
                    <p>
                        <label> Tempoh Bayaran (Hari) :</label>
                        ${actionBean.aduanTindakan2.tempohHari}
                        <%-- <s:text name="aduanTindakan.tempohHari" id="tempohBayaran" onchange="javascript:changeDate();"/>--%>
                    </p>
                    <p>
                        <label> Tarikh Akhir Bayar : </label>
                        <fmt:formatDate value="${actionBean.aduanTindakan2.tarikhTamat}" pattern="dd/MM/yyyy" />

                        <%--<s:text name="aduanTindakan.tarikhTamat" id="tarikhAkhirBayar" formatType="date"  readonly="true" formatPattern="dd/MM/yyyy" value="" />--%>
                    </p>
                    <p>
                        <label> Jumlah Denda Tambahan (RM) : </label>
                        <s:text name="aduanTindakan.amaun"   id ="jumlahDendaTambahan" value="${actionBean.aduanTindakan.amaun}" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" onblur="javascript:updateTotal(this, this.value);"/><em>*</em>
                    </p>
                    <p align="right">
                        <s:button name="lsiSemula" value="lsi Semula" class="btn" onclick="return test();"/>
                        <s:button name="perintahSave" value="Simpan" class="btn" onclick="if (validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                        <s:button name="jana" value="Jana Dokumen" class="btn"/>
                    </p>

                </div>
            </c:if>
            <c:if test="${view}">
                <div class="content"><p>
                        <label> Tarikh Surat : </label>
                        <fmt:formatDate value="${actionBean.aduanTindakan2.tarikhMula}" pattern="dd/MM/yyyy" />

                    </p>
                    <p>
                        <label> Jumlah Denda (RM):</label>
                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.aduanTindakan2.amaun}"/>
                    </p>
                    <p>
                        <label> Tempoh Bayaran (Hari):</label>
                        ${actionBean.aduanTindakan2.tempohHari}

                    </p>
                    <p>
                        <label> Tarikh Akhir Bayar : </label>
                        <fmt:formatDate value="${actionBean.aduanTindakan2.tarikhTamat}" pattern="dd/MM/yyyy" />
                    </p>
                    <p>
                        <label> Jumlah Denda Tambahan (RM): </label>
                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.aduanTindakan.amaun}"/>&nbsp;
                    </p>
                    <c:if test="${status}">
                        <c:if test="${actionBean.noResit ne null}">
                            <p>
                                <label> Status Bayaran :</label>
                                Sudah dijelaskan
                            </p>
                            <p>
                                <label> No. Resit :</label>
                                ${actionBean.noResit}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.noResit eq null}">
                            <p>
                                <label> Status Bayaran :</label>
                                Belum dijelaskan
                            </p>
                        </c:if>
                    </c:if>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>




