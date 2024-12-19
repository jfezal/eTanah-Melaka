<%--
    Document   : notis7G
    Created on : Feb 21, 2011, 12:24:40 PM
    Author     : NageswaraRao
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<script type="text/javascript">
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

    function removeCommas(val){
        return val.replace(/\,/g,'');
    }


    function calc(){
        var premium = $("#premium").val();
        var premiumTambahan = $("#premiumTambahan").val();
        var cukai = $("#cukai").val();
        var premiumDenda = $("#premiumDenda").val();
        var sumbangan = $("#sumbangan").val();
        var pendaftaran = $("#pendaftaran").val();
        var hasil = $("#hasil").val();
        var notis = $("#notis").val();
        var pendaftaranSahaja = $("#pendaftaranSahaja").val();
        var pelan = $("#pelan").val();
        var jumlah =  parseFloat(Number(removeCommas(premium))) + parseFloat(Number(removeCommas(premiumTambahan))) +parseFloat(Number(removeCommas(cukai)))  + parseFloat(Number(removeCommas(premiumDenda)))
            + parseFloat(Number(removeCommas(sumbangan)))+ parseFloat(Number(removeCommas(pelan)))+ parseFloat(Number(removeCommas(pendaftaran)))+parseFloat(Number(removeCommas(hasil)))+parseFloat(Number(removeCommas(notis)))+parseFloat(Number(removeCommas(pendaftaranSahaja)));
        $("#jumlah").val(jumlah);
    }

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.Notis7GActionBean">
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Borang 7G
            </legend>
            <div class="content" align="center">

                <c:if test="${edit}">
                    <table border="0" width="35%">
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td><s:text name="premium" id="premium" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                        </tr>
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Tambahan</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td><s:text name="premiumTambahan" id="premiumTambahan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                        </tr> 
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Denda</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td><s:text name="premiumDenda" id="premiumDenda" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                        </tr> 
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Sumbangan Saliran</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td><s:text name="sumbangan" id="sumbangan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                        </tr> 

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSBSN'
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">

                              <tr>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                  <td><s:text name="notis"  id="notis" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                              </tr>
                        </c:if>

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                              <tr>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran</font></td>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                  <td><s:text name="pendaftaranSahaja"  id="pendaftaranSahaja" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                              </tr>
                        </c:if>

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">                   
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Hakmilik Sementara</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td><s:text name="pendaftaran" id="pendaftaran" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                            </tr>
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Hakmilik Kekal</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td><s:text name="cukai" id="cukai" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                            </tr>
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pelan</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td><s:text name="pelan"  id="pelan" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                            </tr>
                        </c:if>

                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td><s:text name="jumlah" id="jumlah" readonly="readonly" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                        </tr>             
                    </table><br>

                    <p>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                    </p>


                    </br>               
                    <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' 
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' 
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                          <tr>
                              <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hasil Tahun Pertama</font></td>
                              <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                              <td><s:text name="hasil"  id="hasil" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                              <!--<td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasil}"/></td>-->
                          </tr>
                    </c:if>
                </c:if>

                <c:if test="${!edit}">
                    <table border="0" width="28%">
                        <tr>
                            <td width="55%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium </font></td>
                            <td width="10%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td width="25%" align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/> </td>
                        </tr>  
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Tambahan</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premiumTambahan}"/></td>
                        </tr>
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Denda</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premiumDenda}"/></td>
                        </tr>
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Sumbangan Saliran</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.sumbangan}"/></td>
                        </tr>                   

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hasil Tahun Pertama</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasil}"/></td>
                            </tr>
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Hakmilik Sementara</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.pendaftaran}"/></td>
                            </tr>

                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran Hakmilik Kekal</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.cukai}"/></td>
                            </tr>
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pelan</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.pelan}"/></td>
                            </tr>
                        </c:if>

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSBSN'
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">

                              <tr>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Notis</font></td>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                  <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.notis}"/></td>
                              </tr>
                        </c:if>

                        <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' 
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'
                                        || actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                              <tr>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Pendaftaran</font></td>
                                  <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                                  <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.pendaftaranSahaja}"/></td>
                              </tr>
                        </c:if>

                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jumlah</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                            <td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlah}"/></td>
                        </tr>
                    </table><br>     
                    <br/>

                    <c:if test = "${actionBean.permohonan.kodUrusan.kod eq 'TSKKT' 
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSPSN' 
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSKSN'
                                    || actionBean.permohonan.kodUrusan.kod eq 'TSBSN'}">
                          <tr>
                              <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Hasil Tahun Pertama</font></td>
                              <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> : RM</font></td>
                              <td><s:text name="hasil"  id="hasil" onchange="calc();" value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;text-align:right"/></td>
                              <!--<td align="right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasil}"/></td>-->
                          </tr>
                    </c:if>
                </c:if>

            </div>
        </fieldset>
    </div>

</stripes:form>


