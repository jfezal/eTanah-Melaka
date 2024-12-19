<%--
    Document   :  laporan_tanahV2Sempadan.jsp
    Created on :  Feb 29, 2012, 1:18:13 PM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEMPADANAN</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
    $(document).ready(function() {
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
    <%--alert("j=hihi");--%>
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
         
          function tutupRefresh(){
        //        alert('aa');
        NoPrompt();
            
        self.close();
        opener.refreshUtilitiLaporanTanah();
    }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiLaporanTanahActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/> 
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <s:hidden name="noPtSementara" id="noPtSementara"/>
        <s:hidden name="idLapor" id="idLapor" value="${actionBean.laporanTanah.idLaporan}"/>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Bersempadan</legend>
                <br>
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>Bersempadan</th><th>Nama</th><th>Jarak (KM)</th>
                        </tr>
                        <tr>
                            <td>
                                Jalan Raya
                            </td>                        
                            <td>
                                <s:text name="laporanTanah.namaSempadanJalanraya" id="namaSempadanJalanraya"/>
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanJalanraya" id="jarakSempadanJalanraya" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Landasan Keretapi
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanKeretapi" id="namaSempadanKeretapi" />                            
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanKeretapi" id="jarakSempadanKeretapi" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>                            
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Laut
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanLaut" id="namaSempadanLaut" />                            
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanLaut" id="jarakSempadanLaut"  formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>                            
                            </td>
                        </tr><tr>
                            <td>
                                Sungai
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanSungai" id="namaSempadanSungai" />                            
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanSungai" id="jarakSempadanSungai" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>                            
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Lain-lain
                            </td>
                            <td>
                                <s:text name="laporanTanah.namaSempadanlain" id="namaSempadanlain"/>                           
                            </td>
                            <td>
                                <s:text name="laporanTanah.jarakSempadanLain" id="jarakSempadanLain" formatPattern="#,###,##0.0000" maxlength="4" onkeyup="validateNumber(this,this.value);"/>                           
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tablecloth">
                        <tr>
                            <td>
                                <font color="red" size="4">*</font>Jenis Jalan :
                            </td>
                            <td>
                                <s:select name="laporanTanah.jenisJalan" style="width:230px" id="jenisJalan">
                                    <s:option value="0"> Sila Pilih </s:option>
                                    <s:option value="Jalan Berturap">Jalan Berturap</s:option>
                                    <s:option value="Jalan Leterite">Jalan Laterite/Tanah Merah</s:option>
                                    <%--<s:option value="Jalan Tanah Merah">Jalan Tanah Merah</s:option>--%>
                                    <s:option value="Jalan Tanah">Jalan Tanah</s:option>
                                    <s:option value="Jalan Kasaran (Batu)">Jalan Kasaran (Batu)</s:option>
                                    <s:option value="Denai/Lorong">Denai/Lorong</s:option>
                                </s:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <font color="red" size="4">*</font>Jalan Masuk :
                            </td>
                            <td>
                                <s:radio name="laporanTanah.adaJalanMasuk" value="Y" id="adaJalanMasuk"/>&nbsp;Ada
                                <s:radio name="laporanTanah.adaJalanMasuk" value="T" id="adaJalanMasuk"/>&nbsp;Tiada
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Catatan :
                            </td>
                            <td>
                                <s:textarea name="laporanTanah.catatanJalanMasuk" id="catatanJalanMasuk" rows="5" cols="50"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>
        <fieldset class="aras1">
            <table  width="100%" border="0">
                <tr>
                    <td align="center">
                        <s:submit name="simpanBersempadanan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="tutupRefresh();"/>
                    </td>
                </tr>
            </table>   
        </fieldset>
    </s:form>
</body>

