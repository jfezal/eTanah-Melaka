<%-- 
    Document   : Borang_5APBMT
    Created on : Oct 16, 2010, 3:31:36 AM
    Author     : sitifariza.hanim
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

    function calc(){
        var cukai = $("$cukai").val();
        var premium = $("#premium").val();
        var kos = $("#kos").val();
        var daftartetap = $("#daftartetap").val();
        var daftarsementara = $("#daftarsementara").val();
        var pelantetap = $("#pelantetap").val();
        var pelansementara = $("#pelansementara").val();
        var upah = $("#upah").val();
        var notis = $("#notis").val();
        var jumlah = parseFloat(Number(cukai)) + parseFloat(Number(premium)) + parseFloat(Number(kos)) + parseFloat(Number(daftartetap)) + parseFloat(Number(daftarsementara)) + parseFloat(Number(pelantetap)) + parseFloat(Number(pelansementara)) + parseFloat(Number(upah)) + parseFloat(Number(notis));
        $("#jumlah").val(jumlah);
    }

</script>

<s:form beanclass="etanah.view.stripes.pelupusan.Borang5APBMTActionBean">
<s:messages/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 5A
        </legend>
        <div class="content" align="center">
            <table border="0" width="80%">
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                <tr><td><p><label><font color="black">Cukai Tahun Pertama :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="cukai" id="cukai" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                <tr><td><p><label><font color="black">Cukai :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="cukai" id="cukai" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                </c:if>
                    <tr><td><p><label><font color="black">Premium :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="premium" id="premium" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
              
               <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">     
                    <tr><td><p><label><font color="black">Bayaran Ukur(tidak termasuk tanda sempadan) :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="upahUkur"  id="upah" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
              </c:if>
              <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">     
                    <tr><td><p><label><font color="black">Upah Ukur/Tanda Sempadan :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="upahUkur"  id="upah" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
              </c:if>    
              <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">        
                    <tr><td><p><label><font color="black">Kos Sumbangan Infrastruktur :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="kosInfrastruktur" id="kos" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
              </c:if>
              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">        
                    <tr><td><p><label><font color="black">Tanda Sempadan :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="kosInfrastruktur" id="kos" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
              </c:if>      
                    <tr><td><p><label><font color="black">Penyediaan Pelan Suratan Hakmilik Tetap :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="pelanSuratanTetap" id="pelantetap" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                 
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <tr><td><p><label><font color="black">Penyediaan dan Pendaftaran Dokumen Hakmilik Tetap :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanTetap" id="daftartetap" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                </c:if>
                    
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                    <tr><td><p><label><font color="black">Pendaftaran Suratan Hakmilik Tetap :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanTetap" id="daftartetap" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">    
                    <tr><td><p><label></label>
                               </p></td></tr><br>
                </c:if>
                
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">    
                    <tr><td><p><label><font color="black">Penyediaan Pelan Suratan Hakmilik Sementara :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="pelanSuratanSementara" id="pelansementara" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                </c:if>    
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBMT'}">
                    <tr><td><p><label><font color="black">Penyediaan dan Pendaftaran Hakmilik :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanSementara" id="daftarsementara" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                  </c:if>
                    
                  <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PBMT'}">
                    <tr><td><p><label><font color="black">Pendaftaran Suratan Hakmilik Sementara :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="suratanSementara" id="daftarsementara" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                  </c:if>
                    
                    <tr><td><p><label><font color="black">Notis :</font></label>
                                &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="notis" id="notis" onchange="calc();"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                    <tr><td><p><label><font color="black">Jumlah :</font></label>
                            &nbsp;&nbsp;RM&nbsp;&nbsp;<s:text name="jumlah" id="jumlah" disabled="true"
                                        value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:80px;"/></p></td></tr><br>
                </table>
                <p>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                </p>
            </div>
        </fieldset>
    </div>

</s:form>


