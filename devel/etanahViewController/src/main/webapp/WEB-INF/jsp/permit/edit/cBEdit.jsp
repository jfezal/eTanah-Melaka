<%-- 
    Document   : carianBayaran
    Created on : Sep 14, 2015, 2:02:46 PM
    Author     : Hazwan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    
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
    $(document).ready(function(){
        $('#page_effect').fadeIn(2000);
    });
    
    function cBayar(id,jb,nop){ 
        var url = '${pageContext.request.contextPath}/digiSignPermit?cariPemilikNoIc&noFail='+id+'&jenisBorang='+jb+'&noPermit='+nop;         
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function submitBayaran(id,jb,nop){
        var url = '${pageContext.request.contextPath}/digiSignPermit?simpanCarianBayaran&noFail='+id+'&jenisBorang='+jb+'&noPermit='+nop;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            self.opener.refreshBayaran($('#noResit').val(),$('#ama').val());
        },'html');
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:messages/>
<div class="subtitle" id="page_effect" style="display:none;">
    <s:form beanclass="etanah.view.stripes.permit.DSPermitActionBean" >       
        <s:hidden name="noFail" id="noFail"/>
        <s:hidden name="jenisBorang" id="jenisBorang" />
        <s:hidden name="noPermit" id="noPermit" />

        <fieldset class="aras1">
            <legend>
                Carian Bayaran
            </legend>
            <div align="center">
                <p>
                    <b>No Resit :</b>
                    <s:text name="noResitCarian" size="20" id="noResitCarian" class="normal_text"/>
                </p>

                <p>

                    <s:submit name="cariBayaran" value="cari" class="btn" onclick="cBayar('${actionBean.noFail}','${actionBean.jenisBorang}','${actionBean.noPermit}')"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>       
                </p>
            </div>
            <br>
        </fieldset>
        <br/> <br/>
        <c:if test="${find}">
            <fieldset class="aras1">
                <legend>
                    Hasil Carian Bayaran
                </legend>
                <c:if test="${found}">
                    <div class="content" align="center">
                        <table border="0" width="50%">
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Resit</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                <td><s:text name="noResit" size="20" id="noResit" class="normal_text" readonly="true"/></td>
                            </tr>
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Amaun (RM) </font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                                <td><s:text name="ama" size="20" id="ama" class="normal_text" readonly="true"/></td>
                            </tr>
                        </table>
                    </div>

                    <p>
                        <c:if test="${actionBean.noResit != null && actionBean.ama != null}">
                        <div align="center">
                            <s:submit name="simpanCarianBayaran" value="Simpan" class="btn" onclick="submitBayaran('${actionBean.noFail}','${actionBean.jenisBorang}','${actionBean.noPermit}')"/>
                            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                        </div>
                    </c:if>
                    <c:if test="${actionBean.noResit == null && actionBean.ama == null}">
                        <div align="center"><em>Tiada Transaksi Bayaran Bagi No Resit Tersebut</em></div>
                    </c:if>
                    </p>
                </c:if>
            </fieldset>
        </c:if>
    </s:form>
</div>

