<%-- 
    Document   : bayaran_balik
    Created on : Feb 1, 2011, 3:38:32 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#btn").hide();
        if(${actionBean.flag}){
            $("#btn").show();
        }
    });
        
    function test(id){
        alert(id);
    }
</script>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Bayaran Balik (Deposit/Amanah)</font>
            </div>
        </td>
    </tr>
</table></div>
<stripes:errors />
<stripes:form beanclass="etanah.view.stripes.hasil.BayaranBalikDepositActionBean" id="bayar_balik">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>Pilih Jenis Akaun :</label>
                <stripes:select name="jenisAkaun" id="jenisAkaun" style="width:265px;">
                    <stripes:option label="Pilih Jenis Akaun..."  value="" />
                    <c:forEach items="${actionBean.senaraiKodAkaun}" var="i" >
                        <stripes:option value="${i.kod}">${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>

            <p>
                <label>Nombor Akaun :</label>
                <stripes:text name="noAkaun" id="noAkaun"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Nama Pendeposit :</label>
                <stripes:text name="nama" id="nama"  onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>Batch No. :</label>
                <stripes:text name="batchNo" id="batchNo"  onkeyup="this.value=this.value.toUpperCase();"/>
                <%--<stripes:text name="batchNo" id="batch"  onkeyup="this.value=this.value.toUpperCase();"/>--%>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" value="Cari" class="btn"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('bayar_balik');"/>
            </p>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/semak_dokumen" id="line">
                        <display:column title="Bil." style="text-align:center">${line_rowNum}.</display:column>
                         <display:column title="Pilih" style="text-align:center">
                             <stripes:radio name="rbtn" value="${line.noAkaun}" id="radio"/>
                         </display:column>
                        <display:column title="Jenis Akaun" property="kodAkaun.nama"/>
                        <display:column title="Nombor Akaun" property="noAkaun"/>
                        <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                        <display:column title="Nama Pendeposit" property="pemegang.nama"/>
                        <display:column title="Batch No." style="text-align:center">
                            <c:if test="${line.kumpulan.idKumpulan ne null}">${line.kumpulan.idKumpulan}</c:if>
                            <c:if test="${line.kumpulan.idKumpulan eq null}">-</c:if>
                        </display:column>
                        <%--<display:column title="Nombor Akaun" style="width:600;" property="noAkaun"/>--%>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>
    <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td width="100%" height="20" >
                <div style="background-color: green;" align="right">
                    <stripes:submit name="Step3" value="Seterusnya" class="btn" id="btn"/>
                </div>
            </td>
        </tr>
    </table></div>
</stripes:form>