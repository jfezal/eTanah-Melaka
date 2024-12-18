<%-- 
    Document   : kemasukanLMCRG
    Created on : Feb 21, 2013, 11:14:35 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEMASUKAN MAKLUMAT TANAH</title>
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
        //maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});     
    }); //END OF READY FUNCTION

    function refreshpage2(type){
        //        alert(type);
        var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
        
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshV2MaklumatTanah();
        self.close();
    }

    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
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
        
     function val1()
     {  if($('#tujuan/sebab').val() == "")
        {   alert("Sila masukkan Tujuan.");
            return false;
        }
        return true;
     }
   
</script>
<body>
    <script type="text/javascript">
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
    <s:form beanclass="etanah.view.stripes.pelupusan.maklumat_tanah.MaklumatTanahV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <fieldset class="aras1">
                <c:choose>
                    <c:when test="${actionBean.negeri eq '04'}">                        
                        <table class="tablecloth" align="center">
                            <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                                    <tr>
                                        <td><font color="red" size="4">*</font>Logam/Mineral yang dicari :</td>
                                        <td>
                                            <s:textarea name="permohonan.catatan" id="catatan"  rows="5" cols="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><font color="red" size="4">*</font>Tujuan:</td>
                                        <td>
                                            <s:textarea name="permohonan.sebab" rows="5" id="tujuan" cols="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <%--<td style="text-align:center;" colspan="3">      
                                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>--%>
                                        <td style="text-align:center;" colspan="3">      
                                    <s:submit name="simpanTujuan" id= "save" value="SimpanTujuan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>   
                                    <tr>
                                        <td>Tujuan:</td>
                                        <td>
                                            <s:textarea name="permohonan.sebab" rows="5" cols="50"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:center;" colspan="3">      
                                            <s:submit name="simpanTujuan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                        
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </table>       
                        <br/>
                    </c:when>
                    <c:when test="${actionBean.negeri eq '05'}">                        
                        <table class="tablecloth" align="center">
                            <tr>
                                <td><font color="red" size="4">*</font>Tujuan:</td>
                                <td>
                                    <s:textarea name="permohonan.sebab" id="tujuan/sebab" rows="5" cols="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="text-align:center;" colspan="3">      
                                    <s:submit name="simpanTujuan" value="Simpan" class="btn" onclick="NoPrompt();"/>
                                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                                </td>
                            </tr>
                        </table>       
                        <br/>
                    </c:when>    
                </c:choose>
            </fieldset>
        </div>
    </s:form>
</body>