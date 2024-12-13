<%--
    Document   : surat_perintah_denda
    Created on : Jul 20, 2010, 9:29:13 PM
    Author     : Programmer
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.*,java.util.*" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">
    function changeDate()
    {
 
        //var tempohBayaran = document.surat.aduanTindakan.tempohHari.value;
        var tempohBayaran = document.getElementById("tempohBayaran").value;

        if(tempohBayaran == "" || isNaN(tempohBayaran))
        {
            alert("Sila Masukkan Tempoh Bayaran");
            document.surat.tempohBayaran.focus();
        }
        else
        {
            var nodays = parseInt(tempohBayaran);
            var todates = new Date();
            todates.setDate(todates.getDate()+nodays);
            //alert(todates);

            var _date = todates.getDate();
            var _month = todates.getMonth()+1;
            var _year = todates.getFullYear();

            if(_date < 10)
            {
                _date = '0'+_date;
            }
            if(_month < 10)
            {
                _month = '0'+_month;
            }
            //document.surat.aduanTindakan.tarikhTamat.value = _date+"/"+_month+"/"+_year;
            document.getElementById("tarikhTamat").value=_date+"/"+_month+"/"+_year;
        }
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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
        document.surat.jumlahDenda.value = parseFloat(document.surat.jumlahDenda.value).toFixed(2);
    }

    function validateForm(){
        if(document.surat.jumlahDenda.value=="" )
        {
            alert("Sila isikan Jumlah Denda terlebih dahulu");
            return false;
        }
        if (document.surat.tempohBayaran.value=="")
        {
            alert("Sila isikan Tempoh Bayaran terlebih dahulu");
            return false;
        }
        return true;
    }
    function test(f) {
        // $(f).clearForm();
        document.surat.jumlahDenda.value="";
        document.surat.tempohBayaran.value="";
        document.surat.tarikhTamat.value="";
    }
</script>

<s:form name="surat" beanclass="etanah.view.penguatkuasaan.SuratPerintahActionBean">
    <s:messages/>
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">
            <c:if test="${edit}">
                <legend>Surat Perintah Denda</legend>
                <div class="content">
                    <p>
                        <label> Tarikh Surat : </label>
                        <s:text name="tarikhSurat" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" value="<%=new java.util.Date()%>"/>
                    </p>
                    <p>
                        <label> Jumlah Denda (RM):</label>
                        <s:text name="aduanTindakan.amaun"   id ="jumlahDenda" value="" formatPattern="#,##0.00" onkeyup="validateNumber(this,this.value);" onblur="javascript:updateTotal(this, this.value);"/><em>*</em>
                    </p>
                    <p>
                        <label> Tempoh Bayaran (Hari):</label>
                        <s:text name="aduanTindakan.tempohHari" id="tempohBayaran"  onkeyup="validateNumber(this,this.value);" onchange="javascript:changeDate();"/>
                    </p>
                    <p>
                        <label> Tarikh Akhir Bayar : </label>
                        <s:text name="aduanTindakan.tarikhTamat" id="tarikhTamat" formatType="date"  readonly="true" formatPattern="dd/MM/yyyy" value="" />
                    </p>
                    <p align="right">
                        <s:button name="lsiSemula" value="lsi Semula" class="btn" onclick="test();"/>
                        <s:button name="suratSave" value="Simpan" class="btn" onclick="if (validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                        <s:button class="btn" name="janaSurat" value="Jana Dokumen"/>

                    </p>

                </div>
            </c:if>
            <c:if test="${view}">
                <legend>Surat Perintah Denda</legend>
                <div class="content">
                    <p>
                        <label> Tarikh Surat : </label>
                        <fmt:formatDate value="${actionBean.aduanTindakan.tarikhMula}" pattern="dd/MM/yyyy" />

                    </p>
                    <p>
                        <label> Jumlah Denda (RM):</label>
                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.aduanTindakan.amaun}"/>
                    </p>
                    <p>
                        <label> Tempoh Bayaran (Hari):</label>
                        ${actionBean.aduanTindakan.tempohHari}

                    </p>
                    <p>
                        <label> Tarikh Akhir Bayar : </label>
                        <fmt:formatDate value="${actionBean.aduanTindakan.tarikhTamat}" pattern="dd/MM/yyyy" />
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




