<%-- 
    Document   : RekodBayaranPampasanTambahan
    Created on : Sep 29, 2010, 9:47:14 AM
    Author     : Rajesh
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready( function(){


$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
$(".datepicker1").datepicker({dateFormat: 'yy'});
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
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">

function ajaxLink(link, update) {
$.get(link, function(data) {
$(update).html(data);
$(update).show();
});
return false;
}
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pengambilan.sek8.RekodBayaranPampasanTambahanActionBean">
<div  id="hakmilik_details">
    <div align="center">
        <table style="width:99.2%" bgcolor="purple">
            <tr><td width="100%" height="20" ><div style="background-color: purple;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;"><font color="white">REKOD PENERIMAAN BAYARAN PAMPASAN TAMBAHAN </font></font>
            </div></td></tr>
        </table>
    </div><br /><br />

<s:messages/>
<s:errors/>

<fieldset class="aras1"><br/>
    <div align="center">
        <table class="tablecloth">
            <tr>
                <th>Bil</th> <th>Deposit</th><th>Pampasan</th>
            </tr>
            <tr>
                        <td></td>
                    <td><table>
                            <tr><th>Jenis</th><th>Amaun</th></tr>
                                <tr><td>Deposit 50%</td><td>RM ${actionBean.form.deposit50}</td></tr>
                                 <tr><td>Deposint 75%</td><td>RM ${actionBean.form.deposit125}</td></tr>
                        </table></td><td><table>
                                <tr><th>Hakmilik</th><th>Jumlah Tuntutan</th></tr>
                                <c:forEach items="${actionBean.form.listHakmilikTututan}" var="item">
                                    <tr><td>${item.hakmilik.idHakmilik}</td><td>RM ${item.tuntutan}</td></tr>
                                </c:forEach>
                    </table></td>
            </tr>
            <tr><th>Jumlah Deposit 125%</th><td>${actionBean.form.depoTotal}</td><td>${actionBean.form.tuntutanTotal}</td></tr>
        </table>
    </div>
</fieldset>
          <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Deposit Tambahan</legend>
       <br/>
        <p>
            <label>Deposit Tambahan :</label> <s:text name="pampasanTambahan"/>
        </p>
        <p>
            <label>&nbsp;</label> <s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
        </p>

</fieldset>
          </div>
</div>
</s:form>


