<%-- 
    Document   : siasatnHLLP
    Created on : May 14, 2010, 1:57:48 PM
    Author     : massita
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript">
<%--function validateForm(){
    self.opener.refreshPageganti();
    self.close();
}
function test(f) {
        $(f).clearForm();
    }
--%>function updateTotal (inputTxt)
    {
        var total = 0;
        var bil = ${actionBean.jumlahPampasan};
        for (var i = 0; i <bil; i++){
    	var a = document.getElementById('form1' + i)
            if (a == null) break;
            if ((isNaN(a.value))||((a.value) ==""))
            {
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#form1').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('form1');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }

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


    function test(){
           document.getElementById("jumlahPampasan").value ="";
           document.getElementById("caraBayaran").value ="";
           document.getElementById("perincianKerosakan").value ="";
       }
</script>
    <s:form beanclass="etanah.view.stripes.pengambilan.SiasatanActionBean" id="form1">
        <s:messages />
        <s:errors/>
        <fieldset class="aras1">
            <legend>Siasatan Hak Lalu Lalang</legend><br />
            <div class="content" align="left">
                <table border="0" cellspacing="8" width="60%">

                <%--<c:if test="${tempatSiasatan}">--%>
                    <tr>
                        <td><label> Tempat Siasatan: </label></td>
                        <td><s:text name="tempatSiasatan" id="tempatSiasatan" onkeyup="validateNumber(this,this.value);"
                                  style="text-align:left"/></td>
                    </tr>
                <%--</c:if>--%>
                <%--formatPattern="#,##0.00"--%>

                <%--<c:if test="${tarikh}">--%>
                    <tr>
                            <td><label>Tarikh :  </label></td>
                            <td><s:text name="tarikh" id="tarikh" class="datepicker"/></td>
                    </tr>
                <%--</c:if>--%>
                <br />

               <%--<c:if test="${waktu}">--%>
                <%--<tr>
                    <td class="waktuHadir"><label>Waktu:</label></td>
                        <td class="waktuHadir">
                            <table id="waktuHadir">
                            <tr>
                                <div align="left">
                                    <td>
                                        <s:select name="waktu" id="waktu">
                                            <s:option>Sila Pilih Waktu Pagi/Petang</s:option>
                                            <s:option value="a" id="waktu">PAGI</s:option>
                                            <s:option value="p" id="waktu">PETANG</s:option>
                                        </s:select>
                                    </td>
                                </div>
                            </tr>
                        </table>
                        </td>
                    </tr>--%>
                <%--</c:if>--%>

                <tr>
                    <td><label>jam :</label></td>
                    <%--<td><s:text name="jam1" size="5"/> : <s:text name="jam2" size="5"/>:
                        <s:select name="jam4" style="width:61px">
                            <s:option value="0">Pilih</s:option>
                            <s:option value="01">PM</s:option>
                            <s:option value="02">AM</s:option>
                        </s:select>
                    </td>--%>
                    <td><s:select name="jam[${line_rowNum - 1}]" style="width:61px" id="jam${line_rowNum - 1}">
                        <s:option value="">Jam</s:option>
                        <s:option value="01">01</s:option>
                        <s:option value="02">02</s:option>
                        <s:option value="03">03</s:option>
                        <s:option value="04">04</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="06">06</s:option>
                        <s:option value="07">07</s:option>
                        <s:option value="08">08</s:option>
                        <s:option value="09">09</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="11">11</s:option>
                        <s:option value="12">12</s:option>
                    </s:select>
                    <s:select name="minit[${line_rowNum - 1}]" style="width:72px" id="minit${line_rowNum - 1}">
                        <s:option value="">Minit</s:option>
                        <s:option value="00">00</s:option>
                        <s:option value="05">05</s:option>
                        <s:option value="10">10</s:option>
                        <s:option value="15">15</s:option>
                        <s:option value="20">20</s:option>
                        <s:option value="25">25</s:option>
                        <s:option value="30">30</s:option>
                        <s:option value="35">35</s:option>
                        <s:option value="40">40</s:option>
                        <s:option value="45">45</s:option>
                        <s:option value="50">50</s:option>
                        <s:option value="55">55</s:option>
                    </s:select>
                    <s:select name="ampm[${line_rowNum - 1}]" style="width:61px" id="ampm${line_rowNum - 1}">
                        <s:option value="">Pilih</s:option>
                        <s:option value="AM">AM</s:option>
                        <s:option value="PM">PM</s:option>
                    </s:select></td>
                </tr>
                <br />

                <tr>
                    <%--<s:hidden name="idSyaratPendudukan" value="${syaratPendudukan.idSyaratPendudukan}"/>--%>
                    <td align="right"><font color="black" style=" font-size: x-small"><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></font></td>&nbsp;&nbsp;&nbsp;&nbsp;
                    <td align="left"><font color="black" style=" font-size: x-small"><s:button name="reset" value="Isi Semula" class="btn" onclick="test();"/></font></td>
                </tr>
                </table>
           </div>
        </fieldset>
                <br />
</s:form>
<%--onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"--%>