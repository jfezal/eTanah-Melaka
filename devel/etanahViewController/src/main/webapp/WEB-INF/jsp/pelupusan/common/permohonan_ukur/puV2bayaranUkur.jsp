<%--
    Document   :  puV2bayaranUkur.jsp
    Created on :  November 15, 2012, 02:52:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BAYARAN UKUR</title>
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
        <c:choose>
            <c:when test="${actionBean.permohonanUkur.jumlahBayaranUkur eq null and actionBean.permohonanUkur.jumlahPengecualian eq null and actionBean.permohonanUkur.diUkurOleh eq null or actionBean.jenisBayarUkur eq 'DP'}">
                $('#DP').show();            
                $('#DPKTN').show();
                $('#DPR').show();            
                $('#DS').hide();
                $('#DSJ').hide();
                $('#DSK').hide();            
                $('#DSR').hide();
                $('#BU').hide();
                $('#BUR').hide();
                $('#BUT').hide();
                $("#JUKUR").hide();
            </c:when>
            <c:when test="${actionBean.permohonanUkur.jumlahPengecualian ne null or actionBean.jenisBayarUkur eq 'DS'}"> 
                $('#DP').hide();            
                $('#DPKTN').hide();
                $('#DPR').hide();            
                $('#DS').show();
                $('#DSJ').show();
                $('#DSK').show();            
                $('#DSR').show();
                $('#BU').hide();
                $('#BUR').hide();
                $('#BUT').hide();
                $("#JUKUR").hide();
            </c:when>
            <c:when test="${actionBean.permohonanUkur.jumlahBayaranUkur ne null or actionBean.jenisBayarUkur eq 'BU'}">  
                $('#DP').hide();            
                $('#DPKTN').hide();
                $('#DPR').hide();            
                $('#DS').hide();
                $('#DSJ').hide();
                $('#DSK').hide();            
                $('#DSR').hide();
                $('#BU').show();
                $('#BUR').show();
                $('#BUT').show();
                $("#JUKUR").hide();
            </c:when>
             <c:when test="${actionBean.permohonanUkur.diUkurOleh ne null or actionBean.jenisBayarUkur eq 'JU'}">  
                $('#DP').hide();            
                $('#DPKTN').hide();
                $('#DPR').hide();            
                $('#DS').hide();
                $('#DSJ').hide();
                $('#DSK').hide();            
                $('#DSR').hide();
                $('#BU').hide();
                $('#BUR').hide();
                $('#BUT').hide();
                $("#JUKUR").show();
            </c:when>   
        </c:choose>
            
    }); //END OF READY FUNCTION
    function openFieldBayaranUkur(val){
        if(val == 'DP'){
            $('#DP').show();            
            $('#DPKTN').show();
            $('#DPR').show();            
            $('#DS').hide();
            $('#DSJ').hide();
            $('#DSK').hide();            
            $('#DSR').hide();
            $('#BU').hide();
            $('#BUR').hide();
            $('#BUT').hide();
            $("#JUKUR").hide();
        }else if(val == 'DS'){
            $('#DP').hide();            
            $('#DPKTN').hide();
            $('#DPR').hide();            
            $('#DS').show();
            $('#DSJ').show();
            $('#DSK').show();            
            $('#DSR').show();
            $('#BU').hide();
            $('#BUR').hide();
            $('#BUT').hide();
            $("#JUKUR").hide();
        }else if(val == 'BU'){
            $('#DP').hide();            
            $('#DPKTN').hide();
            $('#DPR').hide();            
            $('#DS').hide();
            $('#DSJ').hide();
            $('#DSK').hide();            
            $('#DSR').hide();
            $('#BU').show();
            $('#BUR').show();
            $('#BUT').show();
            $("#JUKUR").hide();
        }else if(val == 'JU'){
            $('#DP').hide();            
            $('#DPKTN').hide();
            $('#DPR').hide();            
            $('#DS').hide();
            $('#DSJ').hide();
            $('#DSK').hide();            
            $('#DSR').hide();
            $('#BU').hide();
            $('#BUR').hide();
            $('#BUT').hide();
            $("#JUKUR").show();
        }
    }         
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
            
    function refreshpage(){
        //            alert('aa');
        NoPrompt();
        opener.refreshV2('main');
        self.close();
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
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.common.PUV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <s:hidden name="idHakmilik" id="idHakmilik"/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">

                <div id="perihaltanah">
                    <legend>BAYARAN UKUR</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <td>Jenis Bayaran Ukur</td>
                                <td>:</td>
                                <td>
                                    <s:select name="jenisBayarUkur" value="${actionBean.jenisBayarUkur}" id="jenisBayarUkur" onchange="openFieldBayaranUkur(this.value)">
                                        <s:option value="">Sila Pilih</s:option>
                                        <%--<s:option value="JK">Kaki</s:option>--%>
                                        <s:option value="DP">Dikecuali Penuh</s:option>
                                        <s:option value="DS">Dikecuali Setakat</s:option>
                                        <s:option value="BU">Bayaran Ukur</s:option>
                                        <s:option value="JU">Juruukur Berlesen</s:option>
                                    </s:select>
                                </td>
                            </tr>
                            <tr id="DP">
                                <td>Dikecuali penuh oleh</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.pemberiPengecualian" value="${actionBean.permohonanUkur.pemberiPengecualian}" id="textDP"/>
                                </td>
                            </tr>
                            <tr id="DPKTN">
                                <td>Dibawah Perenggan Kanun Tanah Negara (Bayaran Ukur) 1965</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perengganKTN" value="${actionBean.permohonanUkur.perengganKTN}" id="textDPKTN"/>
                                </td>
                            </tr>
                            <tr id="DPR">
                                <td>Rujukan</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.rujukanKTN" value="${actionBean.permohonanUkur.rujukanKTN}" id="textDPR" maxlength="10"/>
                                </td>
                            </tr>
                            <tr id="DS">
                                <td>Dikecuali Sekatan Oleh</td>
                                <td>:</td>
                                <td>
                                   <s:text name="permohonanUkur.pemberiPengecualian2" value="${actionBean.permohonanUkur.pemberiPengecualian}" id="textDS"/>
                                </td>
                            </tr>
                            <tr id="DSJ">
                                <td>Jumlah Pengecualian Sekatan (RM)</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.jumlahPengecualian"  formatPattern="#,#00.00" onkeyup="validateNumber(this,this.value);" size="15" id="textDSJ"/>
                                </td>
                            </tr>
                            <tr id="DSK">
                                <td>Dibawah Perenggan Kanun Tanah Negara (Bayaran Ukur) 1965</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.perengganKTN2" value="${actionBean.permohonanUkur.perengganKTN}" id="textDSK"/>
                                </td>
                            </tr>
                            <tr id="DSR">
                                <td>Rujukan</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.rujukanKTN2" value="${actionBean.permohonanUkur.rujukanKTN}" id="textDSR"/>
                                </td>
                            </tr>
                            <tr id="BU">
                                <td>Jumlah Bayaran Ukur (RM)</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.jumlahBayaranUkur" size="30" formatPattern="#,#00.00" onkeyup="validateNumber(this,this.value);" id="textBU"/>
                                </td>
                            </tr>
                            <tr id="BUR">
                                <td>No Resit</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.noResit" value="${actionBean.permohonanUkur.noResit}" id="textBUR"/>
                                </td>
                            </tr>
                            <tr id="BUT">
                                <td>Tarikh Resit</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.tarikhResit" class="datepicker" formatPattern="dd/MM/yyyy" size="30" id="textBUT"/>
                                </td>
                            </tr>
                            <tr id="JUKUR">
                                <td>Bayaran Ukur dibayar oleh :</td>
                                <td>:</td>
                                <td>
                                    <s:text name="permohonanUkur.diUkurOleh" value="${actionBean.permohonanUkur.diUkurOleh}" id="textJUKUR"/>
                                </td>
                            </tr>
                        </table>        
                    </div>
                    <br/>
                </div>
            </fieldset>

            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:submit name="simpanbayaranUkur" value="Simpan" class="longbtn" onclick="NoPrompt();"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>

