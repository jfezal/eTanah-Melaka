<%-- 
    Document   : utiliti_bayar_8A
    Created on : Mar 22, 2011, 12:24:53 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Utiliti Pembayaran Notis 8A</font>
                </div>
            </td>
        </tr>
    </table></div>
<script type="text/javascript">
    function checking(){
        var akaun = document.getElementById('noAkaun');
        var hakmilik = document.getElementById('idHakmilik');
        var negeri = document.getElementById('negeri');
        if(negeri.value == 'melaka'){
            if((akaun.value == "" )&&(hakmilik.value == "")){
                alert('Sila Masukkan Id Hakmilik atau Nombor Akaun untuk membuat carian.');
                return false;
            }else{return true}
        }else{
            if(hakmilik.value == ""){
                alert('Sila Masukkan Id Hakmilik untuk membuat carian.');
                return false;
            }else{return true}
        }
    }
</script>

<stripes:form beanclass="etanah.view.stripes.hasil.UtilitiPembayaranNotis8A" id="utiliti_bayar_8A">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Akaun</legend>
            <stripes:text name="kodNegeri" id="negeri" style="display:none;"/>
           
            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p id="akaun">
                    <label>Nombor Akaun :</label>
                    <stripes:text name="noAkaun" id="noAkaun"/>
                </p>
            </c:if>

            <p id="hakmilik">
                <label>ID Hakmilik :</label>
                <stripes:text name="idHakmilik" id="idHakmilik"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="carian" value="Cari" class="btn" onclick="return checking();"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('utiliti_bayar_8A');"/>
            </p><br>
            
        </fieldset>
    </div>
</stripes:form>
